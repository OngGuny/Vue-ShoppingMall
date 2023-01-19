<template>
  <div class="order">
    <div class="container"> <!-- 이거 긁어오고 개발자모드에서 elements 보면서 하나씩 지우고 전체컨테이너div를 F2 누르고 긁어온것... -->
      <main>
        <div class="py-5 text-center"><h2>주문하기</h2>
          <p class="lead">Below is an example form built entirely with Bootstrap’s form controls. Each required form
            group has a validation state that can be triggered by attempting to submit the form without completing
            it.</p></div>
        <div class="row g-5">
          <div class="col-md-5 col-lg-4 order-md-last"><h4
              class="d-flex justify-content-between align-items-center mb-3"><span
              class="text-primary">구입 목록</span>
            <span
              class="badge bg-primary rounded-pill">{{ state.items.length }}</span></h4>
            <ul class="list-group mb-3">
              <li class="list-group-item d-flex justify-content-between lh-sm" v-for="(i,idx) in state.items"
                  :key="idx">
                <div>
                  <h6 class="my-0">{{ i.name }}</h6>
                </div>
                <span class="text-muted">{{ lib.getNumberFormatted(i.price - i.price * i.discountPer / 100) }}원</span>
              </li>
            </ul>
            <h3 class="text-center total-price">{{ lib.getNumberFormatted(computedPrice) }}원</h3>
          </div>
          <div class="col-md-7 col-lg-8"><h4 class="mb-3">주문자 정보</h4>
            <div class="needs-validation" novalidate=""><!--이게 뷰를 쓰니까 form 태그가 필요가없다... 어디 안보내고 axios로 처리하니깐; -->
              <div class="row g-3">
                <div class="col-12"><label for="username" class="form-label">이름</label>
                  <input type="text"
                         class="form-control"
                         id="username"
                         placeholder="이름"
                         v-model="state.form.name">
                </div>
                <div class="col-12"><label for="address" class="form-label">주소</label>
                  <input type="text"
                         class="form-control"
                         id="address"
                         placeholder="1234 Main St"
                         v-model="state.form.address">
                </div>
              </div>
              <hr class="my-4">
              <h4 class="mb-3">결제 수단</h4>
              <div class="my-3">
                <div class="form-check">
                  <input id="card" name="payment" type="radio" class="form-check-input"
                         value="card" v-model="state.form.payment"><!--괜히 checked="" 되있어서 오류뜸 ;;-->
                  <label class="form-check-label" for="card">카드</label>
                </div>
                <div class="form-check">
                  <input id="bank" name="payment" type="radio" class="form-check-input"
                         value="bank" v-model="state.form.payment">
                  <label class="form-check-label" for="bank">무통장 입금</label>
                </div>
              </div>
              <label for="cc-name" class="form-label">카드 번호</label>
              <input
                  type="text" class="form-control" id="cc-number" v-model="state.form.cardNumber">
              <button class="w-100 btn btn-primary btn-lg" type="submit" @click="submit()">결제하기</button>
            </div>
          </div>
        </div>
      </main>
    </div>
  </div>


</template>

<script>
import axios from "axios";
import {computed, reactive} from "vue";
import lib from "@/scripts/lib";
import router from "@/scripts/router";

export default { //401 에러는 로그인 풀린거.
                 // 리무브만 빼고 cart 그대로 불러옴
  setup() {
    const state = reactive({
      items: [],//바구니 생성
      form: {
        name: "",
        address: "",
        payment: "",
        cardNumber: "",
        items: ""
      }
    })

    const load = () => {
      axios.get("/api/cart/items").then(({data}) => {
        console.log(data);
        state.items = data; //바구니에 데이터 담아주기.
      })
    };

    const computedPrice = computed(() => {
      let result = 0;

      for (let i of state.items) {
        result += i.price - i.price * i.discountPer / 100;
      }

      return result;
    });

    const submit = ()=>{
      const args = JSON.parse(JSON.stringify(state.form)); //state.form 의 데이터가 바뀌기 때문에 연결 고리를 끊어주려고함. json parse 를 그래서 붙여준것.
      args.items = JSON.stringify(state.items); // items 를 넣어주기위해 리스트를 json 으로 넣어줌
      axios.post("/api/orders", args).then(() => {
        //console.log('success'); //위의 state.form 을 그대로 던지겠따는것.
        alert('주문 완료');
        router.push({path:"/orders"})
      })
    };


    load(); //맨처음에 로드 한번깔아줘야 데이터들 불러온다. 함수화 했기 떄문에 실행해줘야함.

    return {state, lib, computedPrice, submit};
  }

}//setup
</script>

<style scoped>
</style>