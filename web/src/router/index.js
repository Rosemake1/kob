import { createRouter, createWebHistory } from 'vue-router'
import PkIndexView from '@/views/pk/PkIndexView.vue'
import RanklistView from '@/views/ranklist/RanklistIndexView.vue'
import UserBotIndexView from '@/views/user/bot/UserBotIndexView.vue'
import RecordView from '@/views/record/RecordIndexView.vue'
import NotFound from '@/views/error/NotFound.vue'
import UserAccountLoginView from '@/views/user/account/UserAccountLoginView.vue'
import UserAccountRegisterView from '@/views/user/account/UserAccountRegisterView.vue'
import store from '@/store/index.js'

const routes = [
  {
     path: '/',
    name: 'home',
    redirect: '/pk/',
    meta:{
      requiresAuth:true,
    }
  },
   {
    path: '/pk/',
    name: 'Pk_index',
    component: PkIndexView,
    meta:{
      requiresAuth:true,
    }
  },
     {
    path: '/record/',
    name: 'Record_index',
    component: RecordView,
    meta:{
      requiresAuth:true,
    }
  },
   {
    path: '/ranklist/',
    name: 'Ranklist_index',
    component: RanklistView,
    meta:{
      requiresAuth:true,
    }
  },

  {
    path:'/user/bot/',
    name:'UserBot_index',
    component:UserBotIndexView,
    meta:{
      requiresAuth:true,
    }
  },
  {
    path:'/user/account/login/',
    name:'user_account_login',
    component:UserAccountLoginView,
    meta:{
      requiresAuth:false,
    }
  },
  {
    path:'/user/account/register/',
    name:'user_account_register',
    component:UserAccountRegisterView,
    meta:{
      requiresAuth:false,
    }
  },
  {
    path: '/404/',
    name: 'NotFound',
    component: NotFound,
    meta:{
      requiresAuth:false,
    }
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
router.beforeEach((to,from,next) => {
    if(to.meta.requiresAuth && !store.state.user.is_login){
      next('/user/account/login/');
    }
    else{
      next();
    }
})

export default router
