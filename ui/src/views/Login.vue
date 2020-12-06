<template>
  <div class="login-bg">
    <div class="login-bg2">
      <div class="login-box" id="login">
        <div class="title">
          <a class="active">账号登录</a>
        </div>
        <div class="padding-cont">
          <el-input type="text" class="input" placeholder="邮箱/账号/手机号" v-model="user.username"/>
          <el-input type="password" class="input" placeholder="密码" v-model="user.password"/>
          <div class="opt">
            <div>
              <el-checkbox>下次自动登录</el-checkbox>
            </div>
            <a class="forget">忘记密码？</a>
          </div>
          <a class="btn-btn" @click="readyLogin">登录</a>
          <a class="btn-register" @click="enterRegister">
            <span class="str1">还没有账号？</span>
            <span class="str2">去注册</span>
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapActions} from "vuex";

export default {
  name: "Login",
  data(){
    return {
      user:{
        username:"12345678901",
        password:"123456"
      }
    }
  },
  methods:{
    readyLogin(){
      if (/^\d{11}$/.test(this.user.username)&&this.user.password.length>0){
        this.loginAction(this.user);
      }else {
        this.$message.error("请输入登录信息");
      }
    },
    enterRegister(){
      this.$router.push("/register")
    },
    ...mapActions(["loginAction"])
  }
}
</script>

<style scoped>
.login-bg {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  min-width: 1000px;
  z-index: -10;
  zoom: 1;
  background-size: cover;
  -webkit-background-size: cover;
  -o-background-size: cover;
  background: #fff url(../assets/login-bg2-01.jpg) no-repeat center 0;
}

.login-bg2 {
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  color: #2d2d2d;
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  background: rgba(20, 27, 47, .6);
}
.login-box{
  width: 31%;
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  color: #2d2d2d;
  padding: 0;
  margin-left: auto;
  margin-right: auto;
  margin-top: 40px;
  position: relative;
  background: #FFF;
  border-radius: 4px;
  display: block;
  box-shadow: 0 3px 5px rgba(0,0,0,.2);
}
.title{
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  color: #2d2d2d;
  margin: 0;
  font-size: 0;
  padding: 47px 41px 58px 41px;
  display: flex;
  justify-content: center;
}
.active{
  text-decoration: none;
  outline: none;
  display: inline-block;
  text-align: center;
  box-sizing: border-box;
  padding: 0 15px 0 0;
  font-size: 26px;
  font-family: PingFangSC-Medium;
  font-weight: 500;
  color: rgba(59,61,69,1);
}

.padding-cont{
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  color: #2d2d2d;
  margin: 0;
  padding: 0 30px 60px;
}
.input{
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  color: #2d2d2d;
  margin: 0 0 24px 0;
  padding: 0;
  height: 64px;
  border-radius: 4px;
  position: relative;
}

.opt{
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  color: #818181;
  margin: 0;
  zoom: 1;
  padding: 20px 0;
  height: 24px;
  line-height: 24px;
  display: flex;
  justify-content: space-between;
}
.btn-btn{
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  text-decoration: none;
  outline: none;
  display: block;
  height: 50px;
  line-height: 50px;
  background: #32BAF0;
  text-align: center;
  border-radius: 3px;
  color: #fff;
  font-size: 16px;
  margin-top: 25px;
}
.btn-register{
  margin-top: 15px;
  display: flex;
  justify-content: flex-end;
  cursor:default;
}
.str1{
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  text-align: right;
  color: #AFB1B3;
  text-decoration:none;
}
.str2{
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  text-align: right;
  color: #32BAF0;
  text-decoration:none;
}

>>> .el-input__inner{
  height: 64px;
}

</style>