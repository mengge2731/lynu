// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/common.css'
Vue.use(ElementUI)

import Axios from 'axios'
// Axios.defaults.baseURL = 'http://rapapi.org/mockjsdata/31586'

// Axios.defaults.baseURL = 'http://120.27.37.129:8080/lynu/function';

Axios.defaults.baseURL = 'http://localhost:8080/function';




// 请求拦截器  只要解除注释就能显示
// Axios.interceptors.request.use(defaults => {
//     ElementUI.Loading.service();
//     return defaults;
// })
// 响应拦截器
// Axios.interceptors.response.use(config => {
//     ElementUI.loadingInstance.close();
//     return config;
// })

Vue.prototype.$axios = Axios


// 路由守卫，只有当用户登录以后，路由才会跳转，或者放行
router.beforeEach((to, from, next) => {
if (to.matched.some(record => record.meta.requiresAuth)) {

    //这里判断用户是否登录，我例子中是验证本地存储是否有token
    if(!sessionStorage.isLogin) {
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




Vue.use(router)





Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
