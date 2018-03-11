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

Axios.defaults.baseURL = 'http://120.27.37.129:8080/lynu/function';

// Axios.defaults.baseURL = 'http://localhost:8080/lynu/function';




// 请求拦截器  只要解除注释就能显示
Axios.interceptors.request.use(defaults => {
    ElementUI.Loading.service();
    return defaults;
})
// 响应拦截器
Axios.interceptors.response.use(config => {
    ElementUI.Loading.service().close();
    return config;
})


// router.beforeEach((to, from, next) => {
//     console.log('navigation-guards');
//     // to: Route: 即将要进入的目标 路由对象
//     // from: Route: 当前导航正要离开的路由
//     // next: Function: 一定要调用该方法来 resolve 这个钩子。执行效果依赖 next 方法的调用参数。

//     console.log(from.path)
//     if( from.path != '/'){

//     }




//     // const nextRoute = ['home', 'good-list', 'good-detail', 'cart', 'profile'];
//     // let isLogin = global.isLogin;  // 是否登录
//     // // 未登录状态；当路由到nextRoute指定页时，跳转至login
//     // if (nextRoute.indexOf(to.name) >= 0) {
//     //   if (!isLogin) {
//     //     console.log('what fuck');
//     //     router.push({ name: 'login' })
//     //   }
//     // }
//     // // 已登录状态；当路由到login时，跳转至home
//     // if (to.name === 'login') {
//     //   if (isLogin) {
//     //     router.push({ name: 'home' });
//     //   }
//     // }
//     // next();
//   });

// router.beforeEach(function (to, from, next) {
//     const nextRoute = [ 'account', 'order', 'course'];
//     // const auth = store.state.auth;
//     //跳转至上述3个页面
//     if (nextRoute.indexOf(to.name) >= 0) {
//         //未登录
//         if (!store.state.auth.IsLogin) {
//             vueRouter.push({name: 'login'})
//         }
//     }
//     //已登录的情况再去登录页，跳转至首页
//     if (to.name === 'login') {
//         if (auth.IsLogin) {
//             vueRouter.push({name: 'home'});
//         }
//     }
//     next();
// });


Vue.prototype.$axios = Axios
Vue.use(router)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
