import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI)
Vue.prototype.$axios = axios
Vue.config.productionTip = false

router.beforeEach((to,from,next)=> {
  if(to.path === '/register' || to.path === "/login") {
    next();
  } else {
    if (window.sessionStorage.getItem("user")) {
      next();
    } else {
      next("/login")
    }
  }
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
