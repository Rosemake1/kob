<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <router-link  class="navbar-brand" :to="{name:'home'}">King Of Bot</router-link>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link  :class="route_name == 'Pk_index'?'nav-link active':'nav-link' " :to="{name:'Pk_index'}">对战</router-link>
        </li>
        <li class="nav-item">
          <router-link :class="route_name =='Record_index'?'nav-link active':'nav-link'" :to="{name:'Record_index'}">对局列表</router-link>
        </li>
        <li class="nav-item">
          <router-link :class="route_name =='Ranklist_index'?'nav-link active':'nav-link'" :to="{name:'Ranklist_index'}">排行榜</router-link>
        </li>
      </ul>
      <ul class="navbar-nav" v-if = "$store.state.user.is_login">
      <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            {{$store.state.user.username}}
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li>
            <router-link class="dropdown-item" :to="{name:'UserBot_index'}">我的bot</router-link>
            </li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#" @click="logout">退出登录</a></li>
          </ul>
        </li>
      </ul>
        <ul class="navbar-nav" v-else-if="!$store.state.user.pulling_info">
          <li class="nav-item ">
            <router-link class="nav-link " :to="{name:'user_account_login'}" role="button" >
              登录
            </router-link>
          </li>
          <li class="nav-item ">
            <router-link class="nav-link " :to="{name:'user_account_register'}" role="button" >
              注册
            </router-link>
          </li>
      </ul>
    </div>
  </div>
</nav>
</template>
<script>
import { useRoute } from 'vue-router';
import { computed } from 'vue';
import { useStore } from 'vuex';
export default {
    setup (){ 
      const route = useRoute();
      const store = useStore();
      let route_name = computed(() => route.name);
      const logout = () => {
        store.dispatch('logout');
      }
      return { route_name,logout };
    }
}
</script>

<body scoped>
    
</body>