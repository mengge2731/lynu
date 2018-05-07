// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/common.css'
import './assets/css/lynu.css'
Vue.use(ElementUI)

// 兼容ie低版本设置
import 'babel-polyfill'

import Axios from 'axios'
// Axios.defaults.baseURL = 'http://rapapi.org/mockjsdata/31586'

Axios.defaults.baseURL = 'http://222.88.73.6:8001/lynu/function';
// Axios.defaults.baseURL = 'http://localhost:8080/function';

// 请求拦截器  只要解除注释就能显示/*  */
Axios.interceptors.request.use(defaults => {
    ElementUI.Loading.service();
    return defaults;
})
// 响应拦截器
Axios.interceptors.response.use(config => {
    ElementUI.Loading.service().close();
    return config;
})

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
