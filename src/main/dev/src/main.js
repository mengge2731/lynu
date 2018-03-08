// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'


import Axios from 'axios'
Axios.defaults.baseURL = 'http://rapapi.org/mockjsdata/31586'
Vue.prototype.$axios = Axios


var login = false;

if(login){
  // 路由守卫，只有当用户登录以后，路由才会跳转，或者放行
  router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        //这里判断用户是否登录，我例子中是验证本地存储是否有token
        if (!localStorage.token) {
            next({
                path: '/login',
                query: { redirect: to.fullPath }
            })
        } else {
            next()
        }
    } else {
        next() // 确保一定要调用 next()
    }
  })
}



Vue.use(router)



import './assets/css/common.css'
Vue.use(ElementUI)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
