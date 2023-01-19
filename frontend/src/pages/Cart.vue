<template>

  <div class="form-signin w-100 m-auto">
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
  </div>

</template>

<script>
import axios from "axios";

export default {
  setup(){
    axios.get("/api/cart/items").then(({data})=>{
      console.log(data);
    })
  }
}
</script>

<style scoped>
</style>