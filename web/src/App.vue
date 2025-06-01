<template>
<div>
<div>Bot昵称：{{ bot_name }}</div>
<div>Bot战力：{{ bot_rating }}</div>
</div>
  <router-view></router-view>  
</template>

<script>
import $ from 'jquery';
// import axios from 'axios';
import {ref} from 'vue';
  export default {
    name: 'App',
    setup(){
      let bot_name= ref("");
      let bot_rating= ref("");
      // axios.get("http://localhost:3000/pk/getbotinfo/")
      // .then(res=>{
      //   bot_name.value=res.data.name;
      //   bot_rating.value=res.data.rating;
      // })
      // .catch(err=>{
      //   console.log("请求失败",err);
      // })
      $.ajax({
        url:"http://localhost:3000/pk/getbotinfo/",
        type:"GET",
        success:resp => {
          bot_name.value=resp.name;
          bot_rating.value=resp.rating;
        }
      })
      return {
        bot_name,
        bot_rating
      }
    }
  }
</script>
<style>
body{
  background-image: url("@/assets/background.png");
  background-size: cover;
}
</style>
