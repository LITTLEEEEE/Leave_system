import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const store = new Vuex.Store({
  state:{
    name: 'aoliao',
    age:'18'
  },
  mutations:{
    showPeople(state, msg){
      state.name = msg
    }
  }

})
export default store
