import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/index'
import Info from '@/components/pages/user/info'

import Market from '@/components/pages/market/market'

import Share from '@/components/pages/share/share'

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
    },
    {
      path: '/market',
      name: 'market',
      component: Market
    },
    {
      path: '/share',
      name: 'share',
      component: Share
    }
  ]
})
