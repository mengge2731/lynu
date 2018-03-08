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

// 管理权限 发布信息管理 ， 用户管理
import infoManage from '@/components/pages/user/infoManage'
import Manage from '@/components/pages/user/manage'

// 数据市场内容
import Market from '@/components/pages/market/market'
import martetApply from '@/components/pages/market/apply'

// 共享中心内容
import Center from '@/components/pages/share/share'

// 登录页面
import Login from '@/components/common/login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index,
      // meta: { requiresAuth: true }
    },
    {
      path: '/user',
      name: 'user',
      component: User,
      children:[
        { name:'info', path: 'info',component: Info ,meta: { requiresAuth: true } },
        { name:'share', path: 'share',component: Share ,meta: { requiresAuth: true } },
        { name:'userApply', path: 'apply',component: Apply ,meta: { requiresAuth: true } },
        { name:'password', path: 'password',component: Password  },
        { name:'detail', path: 'detail',component: Detail ,meta: { requiresAuth: true } },
        {name:'manage', path: 'manage',component: Manage ,meta: { requiresAuth: true } },
        {name:'infoManage', path: 'infoManage',component: infoManage ,meta: { requiresAuth: true } }
      ],
      
    },
    {
      path: '/market',
      name: 'market',
      component: Market,
      meta: { requiresAuth: true },
      children:[]
    },
    { name:'marketApply', path: '/apply',component: martetApply ,meta: { requiresAuth: true } },

    {
      path: '/center',
      name: 'center',
      component: Center,
      meta: { requiresAuth: true }
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
      // meta: { requiresAuth: true }
    }
  ]
})
