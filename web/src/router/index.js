import { createRouter, createWebHistory } from 'vue-router'
import PkIndexView from '@/views/pk/PkIndexView.vue'
import RanklistView from '@/views/ranklist/RanklistIndexView.vue'
import UserBotIndexView from '@/views/user/bot/UserBotIndexView.vue'
import RecordView from '@/views/record/RecordIndexView.vue'
import NotFound from '@/views/error/NotFound.vue'
import UserAccountLoginView from '@/views/user/account/UserAccountLoginView.vue'
import UserAccountRegisterView from '@/views/user/account/UserAccountRegisterView.vue'
const routes = [
  {
     path: '/',
    name: 'home',
    redirect: '/pk/'
  },
   {
    path: '/pk/',
    name: 'Pk_index',
    component: PkIndexView
  },
     {
    path: '/record/',
    name: 'Record_index',
    component: RecordView
  },
   {
    path: '/ranklist/',
    name: 'Ranklist_index',
    component: RanklistView
  },

  {
    path:'/user/bot/',
    name:'UserBot_index',
    component:UserBotIndexView
  },
  {
    path:'/user/account/login/',
    name:'user_account_login',
    component:UserAccountLoginView
  },
  {
    path:'/user/account/register/',
    name:'user_account_register',
    component:UserAccountRegisterView
  },
  {
    path: '/404/',
    name: 'NotFound',
    component: NotFound
  },
  {
    path: '/:catchAll(.*)',
    redirect: '/404'

  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
