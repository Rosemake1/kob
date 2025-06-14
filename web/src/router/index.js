import { createRouter, createWebHistory } from 'vue-router'
import PkIndexView from '@/views/pk/PkIndexView.vue'
import RanklistView from '@/views/ranklist/RanklistIndexView.vue'
import UserBotIndexView from '@/views/user/bot/UserBotIndexView.vue'
import RecordView from '@/views/record/RecordIndexView.vue'
import NotFound from '@/views/error/NotFound.vue'


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
