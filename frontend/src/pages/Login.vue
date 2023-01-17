<template>

  <div class="form-signin w-100 m-auto">
<!--    <form>-->
      <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

      <div class="form-floating"><!-- 백엔드에서 아이디 관련 작업 해준 뒤에 v-model 달아줌-->
        <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" v-model="state.form.email">
        <label for="floatingInput">Email address</label>
      </div>
      <div class="form-floating">
        <input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="state.form.password">
        <label for="floatingPassword">Password</label>
      </div>

      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>
      <button class="w-100 btn btn-lg btn-primary" @click="submit()">Sign in</button>
      <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
<!--    </form>-->
  </div>

</template>

<script>
import {reactive} from "vue";
import axios from "axios";
import store from "@/scripts/store";

export default {
  setup(){
  const state = reactive({
    form:{
      email:"",
      password:""
    }
  })
  const submit = ()=>{//얘는 뭘 받냐? 위에서 입력한 이메일, 비번 받아줄꺼임.
  axios.post("/api/account/login", state.form).then((res)=>{
    //login 후 아이디와 이메일 같은걸 store 에 저장을 하고 세션관리하겠다는것. vuex 가 전역 변수 관리하는거니까.
    store.commit('setAccount',res.data);
    sessionStorage.setItem("id",res.data);//임시로 세션에 담아준다.
    console.log(res);
    window.alert("로그인했슈");
  }).catch(()=>{// 에러 잡아주는거. backend 에서 throw 처리 해준거 여기서 잡아줌
    window.alert("로그인 정보가 존재하지 않습니다.")
  })
    }


    return {state, submit}
  },
  name: "Login"
}
</script>

<style scoped>
.form-signin {
  max-width: 330px;
  padding: 15px;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>