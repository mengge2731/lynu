import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/index'

// 个人中心内容
import User from '@/components/pages/user/index'
import Info from '@/components/pages/user/info'
import Share from '@/components/pages/user/share'
import Apply from '@/components/pages/user/apply'
import Password from '@/components/pages/user/password'

import Detail from '@/components/pages/user/detail'


// 数据市场内容
import Market from '@/components/pages/market/market'


// 共享中心内容
import ShareCenter from '@/components/pages/share/share'







Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index
    },
    {
      path: '/user',
      name: 'user',
      component: User,
      children:[
        { name:'info', path: 'info',component: Info},
        { name:'share', path: 'share',component: Share},
        { name:'apply', path: 'apply',component: Apply},
        { name:'password', path: 'password',component: Password},
        { name:'detail', path: 'detail',component: Detail}
      ]
    },
    {
      path: '/market',
      name: 'market',
      component: Market
    },
    {
      path: '/share',
      name: 'share',
      component: ShareCenter
    }
  ]
})
