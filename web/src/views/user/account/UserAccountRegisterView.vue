<template>
    <ContentField>
        <div class="row justify-content-center">
            <div class="col-3">
             <form @submit.prevent="register">
                 <div class="mb-3">
                <label for="username" class="form-label">用户名</label>
                <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                </div>
                 <div class="mb-3">
                    <label for="password" class="form-label">密码</label>
                    <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                 </div>
                <div class="mb-3">
                    <label for="confirmedPassword" class="form-label">确认密码</label>
                    <input v-model="confirmedPassword" type="password" class="form-control" id="confirmedPassword" placeholder="请再次输入密码">
                 </div>
                 <div class="error-message">{{ error_message }}</div>
                 <button type="submit" class="btn btn-primary">提交</button>
            </form>
            </div> 
        </div> 
    </ContentField>
</template>


<script>
import ContentField from "../../../components/ContentField.vue";
import { ref } from "vue";
import $ from "jquery";
import router from "@/router";
export default{
    components:{
        ContentField
    },
    setup(){
        let username = ref('');
        let password = ref('');
        let confirmedPassword = ref('');
        let error_message = ref('');
        const register = () => {
            $.ajax({
                url:'http://127.0.0.1:3000/user/account/register/',
                type:'post',
                data:{
                    username: username.value,
                    password: password.value,
                    confirmedPassword: confirmedPassword.value,
                },
                success(resp){
                    if(resp.error_message === "success")
                {
                    router.push('/user/account/login');
                }else{
                    error_message.value = resp.error_message;
                }
                },
                error(resp){
                    console.log(resp);
                    
                }
            })
        }
        return {
            username,
            password,
            confirmedPassword,
            error_message,
            register
        }
    }
}
</script>   



<style scoped>
button{
    width: 100%;
}
.error-message{
    color: red;
}
</style>