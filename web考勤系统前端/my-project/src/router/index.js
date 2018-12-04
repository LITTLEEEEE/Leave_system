import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import User from '@/components/员工/user'
import Punch from '@/components/员工/punch_yuangong'
import Leave from '@/components/员工/leave_yuangong'
import getPunch from '@/components/员工/getPunch'
import getLeave from '@/components/员工/getLeave'
import Others from '@/components/员工/get_other_message'
import Messager from '@/components/部门经理/messager'
import Out from '@/components/员工/out'
import Out_deals from '@/components/部门经理/out_deals'
import Out2 from '@/components/部门经理/out'
import Leave2 from '@/components/部门经理/leave'
import leave_deals from '@/components/部门经理/leave_deals'
import check from '@/components/部门经理/check_punch'
import Messager2 from '@/components/副总经理/messager'
import Out_deals2 from '@/components/副总经理/out_deals'
import Out3 from '@/components/副总经理/out'
import Leave3 from '@/components/副总经理/leave'
import leave_deals2 from '@/components/副总经理/leave_deals'
import check2 from '@/components/副总经理/check_punch'
import check3 from '@/components/总经理/check_punch'
import leave_deals3 from '@/components/总经理/leave_deals'
import out_deals3 from '@/components/总经理/out_deals'
import Messager3 from '@/components/总经理/messager'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/user',
      name: 'user',
      component: User
    },
    {
      path: '/punch',
      name: 'punch',
      component: Punch
    },
    {
      path: '/leave',
      name: 'leave',
      component: Leave
    },
    {
      path: '/getPunch',
      name: 'getpunch',
      component: getPunch
    },
    {
      path: '/getLeave',
      name: 'getleave',
      component: getLeave
    },
    {
      path: '/others',
      name: 'getothers',
      component: Others
    },
    {
      path: '/messager',
      name: 'messager',
      component: Messager
    },
    {
      path: '/out',
      name: 'out',
      component: Out
    },
    {
      path: '/outdeals',
      name: 'outdeals',
      component: Out_deals
    },
    {
      path: '/out2',
      name: 'out2',
      component: Out2
    },
    {
      path: '/leave2',
      name: 'leave2',
      component: Leave2
    },
    {
      path: '/check',
      name: 'check',
      component: check
    },
    {
      path: '/leavedeals',
      name: 'leavedeals',
      component: leave_deals
    },
    {
      path: '/leavedeals2',
      name: 'leavedeals2',
      component: leave_deals2
    },
    {
      path: '/messager2',
      name: 'messager2',
      component: Messager2
    },
    {
      path: '/outdeals2',
      name: 'outdeals2',
      component: Out_deals2
    },
    {
      path: '/out3',
      name: 'out3',
      component: Out3
    },
    {
      path: '/leave3',
      name: 'leave3',
      component: Leave3
    },
    {
      path: '/check2',
      name: 'check2',
      component: check2
    },
    {
      path: '/check3',
      name: 'check3',
      component: check3
    },
    {
      path: '/leavedeals3',
      name: 'leavedeals3',
      component: leave_deals3
    },
    {
      path: '/messager3',
      name: 'messager3',
      component: Messager3
    },
    {
      path: '/outdeals3',
      name: 'outdeals3',
      component: out_deals3
    },

  ]
})
