<template>
  <header>
    <div class="collapse bg-dark" id="navbarHeader">
      <div class="container">
        <div class="row">
          <div class="col-sm-4  py-4">
            <h4 class="text-white">사이트 맵</h4>
            <ul class="list-unstyled">
            <li>
              <router-link to="/" class="text-white">메인 화면</router-link>
            </li>
            <li v-if="$store.state.account.id">
              <router-link to="/orders" class="text-white">주문 내역</router-link>
            </li>
            <li>
              <router-link to="/login" class="text-white" v-if="!$store.state.account.id">로그인</router-link>
              <a to="/login" class="text-white" @click="logout()" v-else>로그아웃</a>
            </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="navbar navbar-dark bg-dark shadow-sm">
      <div class="container">
        <router-link to="/" class="navbar-brand d-flex align-items-center"><!--앨범 누르면 메인으로 가게. router-link 는 href 말고 to 써줌 -->
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" aria-hidden="true" class="me-2" viewBox="0 0 24 24"><path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"/><circle cx="12" cy="13" r="4"/></svg>
          <strong>ShoppingMall</strong>
        </router-link>
        <router-link to="/cart" class="cart btn" v-if="$store.state.account.id">
          <i class="fa fa-shopping-cart" aria-hidden="true"></i>
        </router-link>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      </div>
    </div>
  </header>
</template>

<script>
import router from "@/scripts/router";
import store from "@/scripts/store";
import axios from "axios";

export default {
  name: 'Header',
  setup(){
    const logout = ()=>{// 클라이언트에서 쿠키를 지울 수 없는. http only 쿠키다. 그래서 axios 로 쿠키 지워줌
      axios.post("/api/account/logout").then(()=>{
        store.commit('setAccount',0);
        //sessionStorage.removeItem("id");// 로그아웃 할때는 세션에 담았던것도 지워줌
        router.push({path:'/'});//로그아웃 하고 나선 여기로 보내주겠다는것.
      });
    }

    return {logout};
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
 header .navbar .cart{
   margin-left: auto;
   color: white;
 }

header ul li a{
  cursor: pointer;
}
</style>
