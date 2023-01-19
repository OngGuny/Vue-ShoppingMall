import Home from "@/pages/Home.vue";
import Login from "@/pages/Login.vue";
import Cart from "@/pages/Cart.vue";
import {createRouter, createWebHistory} from "vue-router";

// vue-router 는 설치해야함.
const routes = [
    {path:'/', component: Home},

    //루트 경로로 '/' 들어올때는 컴포넌트를 Home 으로 연결해라는뜻
    {path:'/login', component: Login},

    {path:'/cart', component: Cart}

]

const router = createRouter({
    history:createWebHistory(),
    routes
})

export default router;