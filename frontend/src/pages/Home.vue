<template>
  <div class="home">
<!--    <section class="py-5 text-center container">-->
<!--      <div class="row py-lg-5">-->
<!--        <div class="col-lg-6 col-md-8 mx-auto">-->
<!--          <h1 class="fw-light">Album example</h1>-->
<!--          <p class="lead text-muted">Something short and leading about the collection below—its contents, the creator,-->
<!--            etc. Make it short and sweet, but not too short so folks don’t simply skip over it entirely.</p>-->
<!--          <p>-->
<!--            <a href="#" class="btn btn-primary my-2">Main call to action</a>-->
<!--            <a href="#" class="btn btn-secondary my-2">Secondary action</a>-->
<!--          </p>-->
<!--        </div>-->
<!--      </div>-->
<!--    </section>-->

    <div class="album py-5 bg-light">
      <div class="container">

        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
          <!-- col class=card 쪽이 반복되고있으니 컴포넌트 화 한다.-->
          <div class="col" v-for="(item, index) in state.items" :key="index">
            <Card :item="item"/>
          </div><!-- class = col -->


        </div>
      </div>
    </div>
  </div><!-- div home-->
</template>

<script>
import axios from "axios";
import {reactive} from "vue";
import Card from "@/components/Card.vue";

export default {
  name: "Home",
  components: {
    Card
  },
  setup() {//컴포넌트 인스턴스(components 안에것들)가 생성되기 전에. 실행되는 메소드,변수들
    const state = reactive({
      items: []
    })
                                      //이렇게 {data} 해주면 응답받는 객체 안의 특정 데이터를 바로 꺼내 올 수 있다. 이게 새 문법;
    axios.get("/api/items").then(({data}) => {
    state.items = data;
    })

  return {state} // setup 의 state 를 반환하겠따는거. 이러면 위의 템플릿에서도 state 를 사용 할 수 있따.
  }

}
</script>

<style lang="css" scoped>
</style>
