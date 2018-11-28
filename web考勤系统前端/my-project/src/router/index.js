import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import User from '@/components/user'
import Punch from '@/components/punch_yuangong'
import Leave from '@/components/leave_yuangong'
import Message from '@/components/get_message'
import Others from '@/components/get_other_message'
import Messager from '@/components/部门经理/messager'
import Out from '@/components/out'

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
      path: '/getmessage',
      name: 'getmessage',
      component: Message
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
  ]
})
