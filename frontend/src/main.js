import {createApp} from 'vue'
import store from "@/scripts/store"
import router from "@/scripts/router"
import App from './App.vue'
//여기있던 라우터 분리해줌으로써 다른 컴포넌트에서도 라우터 쓸 수 있따.

createApp(App).use(store).use(router).mount('#app')
