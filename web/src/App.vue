<template>
  <NavBar />
  <router-view></router-view>  
</template>

<script>
// import axios from 'axios'
import NavBar from './components/NavBar.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
// import $ from 'jquery'
export default {
    components:{
      NavBar
    },

    // setup(){
    //   axios.get('http://127.0.0.1:3000/user/account/info/',{
    //     params:{
    //       username:'hsy',
    //       password:'hsy20050930',
    //     },
    //     headers: {
    //       'Authorization': "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwYWZjYWM3ZWIzZjc0YWZhOGRiMzg5NjM4ODE5YzBmMiIsInN1YiI6IjIiLCJpc3MiOiJzZyIsImlhdCI6MTc1MDMxODExMiwiZXhwIjoxNzUxNTI3NzEyfQ.nTmNajkN-7FU716bZH1m-RgD1879LWNTbuD51fEOfZ8", // 示例：添加认证令牌
    //     }
    //   }).then(function (response)   {
    //     console.log(response);
    //   }).catch(function (error) {
    //     console.log(error);
    //   }),

    //   $.ajax({
    //     url:'http://127.0.0.1:3000/user/account/register/',
    //     type:'post',
    //     data:{
    //       username:'1234',
    //       password:'1234',
    //       confirmedPassword:'1234'
    //     },
    //     success(resp){
    //       console.log(resp);
    //     },       
    //     error(resp){
    //       console.log(resp);
    //     }
    //   });
    // }
  }
</script>
<style>
body{
  background-image: url("@/assets/images/background.png");
  background-size: cover;
}
</style>
