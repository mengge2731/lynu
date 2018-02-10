import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/index'
import Info from '@/components/pages/user/info'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index
    },
    {
      path: '/user/info',
      name: 'info',
      component: Info
    }
  ]
})
