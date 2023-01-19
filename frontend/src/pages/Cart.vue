<template>
<div class="cart">
  <div class="container">
    <ul>
      <li v-for="(i,idx) in state.items" :key="idx">
        <img :src="i.imgPath" />
        <span class="name">{{i.name}}</span>
        <span class="price">{{ lib.getNumberFormatted(i.price - i.price * i.discountPer / 100) }}원</span>
        <i class="fa fa-trash" @click="remove(i.id)"></i>
      </li>
    </ul>
    <router-link to="/order" class="btn btn-primary">구입하기</router-link><!-- html 자동완성은 tab/ 버튼말고 그냥 라우터링크로 보내자-->


  </div>
</div>


</template>

<script>
import axios from "axios";
import {reactive} from "vue";
import lib from "@/scripts/lib";

export default { //401 에러는 로그인 풀린거.
  setup(){
    const state = reactive({
      items:[]//바구니 생성
    })

    const load = ()=>{
      axios.get("/api/cart/items").then(({data})=>{
        console.log(data);
        state.items = data; //바구니에 데이터 담아주기.
      })
    };

    const remove = (itemId) =>{
      axios.delete(`/api/cart/items/${itemId}`).then(()=>{
        load();//여서 쓸라고 load 로 함수화 해줌
      })
    }

    load(); //맨처음에 로드 한번깔아줘야 데이터들 불러온다. 함수화 했기 떄문에 실행해줘야함.

    return {state, lib, remove};
  }

}//setup
</script>

<style scoped>
.cart ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
.cart ul li {
  border: 1px solid #eee;
  margin-top: 25px;
  margin-bottom: 25px;
}

.cart ul li img {
  width: 150px;
  height: 150px;
}
.cart ul li .name {
  margin-left: 25px;

}
.cart ul li .price {
  margin-left: 25px;
}
.cart ul li i {
  float: right;
  font-size: 20px;
  margin-top: 65px;
  margin-right: 50px;
}
.cart .btn {
  width: 300px;
  display: block;
  margin: 0 auto; /*가운데 보내기*/
  padding: 30px 50px;
  font-size: 20px;
}
</style>