<template>

<Header/>

<RouterView/> <!--Home 을 직접 넣어주지 않고 router 를 사용해 '/'로 들어왔을때 Home 페이지를 보여줌 -->

<Footer/>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";
import store from "@/scripts/store";
import axios from "axios";
import {useRoute} from "vue-router";
import {watch} from "vue";
export default {
  name: 'App',
  components: {
    Header,
    Footer,
  },
  setup(){

    const check = ()=>{
      axios.get("/api/account/check").then(({data})=>{// 세팅한 api 불러오기.
        console.log(data);
        if(data){ //얘는 그냥 존재자체가 트루네;;
          store.commit("setAccount", data || 0); // 값있으면 데이터 없으면 0 ;;; 이게되나;
        }

      })
    };

    const route = useRoute();
    //경로 바뀔떄마다 감시.
    watch(route, ()=>{
      check();
    })

    /* 얘는 위에 api 쓰면서 depreciated 된거.
    const id = sessionStorage.getItem("id");//아이디 있으면 세션에꺼 가져오고
    if(id){//있따면 vuex에 넣어줘야함.
      store.commit("setAccount",id);//이제 새로고침해도 로그아웃안됨
    }
*/

  }
}
</script>

<style>
 .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
</style>
