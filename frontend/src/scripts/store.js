import { createStore } from 'vuex'

//vuex 소개의 시작 예제 긁어옴.
const store = createStore({
    state () {
        return {
            account:{
                id:0
            }
        }
    },
    mutations: {
        setAccount(state, payload){
            state.account.id = payload;
        }
    }
})

export default store;