<template>
  <div class="login-bg">
    <div class="login-bg2">
      <div class="reg-logs">
        <div class="identity-box" v-if="role===''">
          <h3 style="font-size: 32px;margin: 0;font-weight: 400">注册账号</h3>
          <p style=" margin-top: 20px; color: #707070;">请选择您在日常教学中的实际身份</p>
          <div class="choose">
            <div style="display: flex;flex-direction: row;justify-content: space-between">
              <img src="../assets/teacher.png"/>
              <el-button type="primary" @click="changeRole('teacher')">我是老师/助教</el-button>
            </div>
            <div style="margin-top:60px;display: flex;flex-direction: row;justify-content: space-between">
              <img src="../assets/teacher.png"/>
              <el-button type="primary" @click="changeRole('student')">我是学生</el-button>
            </div>
          </div>
          <a class="btn-login" @click="enterLogin">
            <span class="str1">已有账号？</span><span class="str2">去登录</span>
          </a>
        </div>
        <div class="login-box" id="tea-reg" style="display: block;" v-else>
          <div class="title">
            <img src="../assets/returnReg.png" style="width: 40px;height: 40px;" @click="changeRole('')"/>
            <h3 style="line-height: 40px;font-size: 32px;color: #3B3D45;margin: 0" v-if="role==='teacher'">
              老师/助教注册</h3>
            <h3 style="line-height: 40px;font-size: 32px;color: #3B3D45;margin: 0" v-if="role==='student'">
              学生注册</h3>
          </div>
          <div class="padding-cont" v-if="role==='teacher'">
            <el-input v-model="user.username" class="input" type="text" placeholder="手机"/>
            <el-input v-model="user.password" class="input" type="password" placeholder="密码"/>
            <el-input v-model="user.rePassword" class="input" type="password" placeholder="再次输入密码"/>
            <el-input v-model="user.name" class="input" type="text" placeholder="姓名"/>
            <el-input v-model="user.school" class="input" type="text"  placeholder="学校"/>
            <a class="btn-btn" @click="register">注册为老师/助教</a>
            <a class="btn-login" style="width: 400px;margin-bottom: 15px" @click="enterLogin">
              <span class="str1">已有账号？</span>
              <span class="str2">去登录</span>
            </a>
          </div>
          <div class="padding-cont" v-if="role==='student'">
            <el-input v-model="user.username" class="input" type="text" placeholder="手机"/>
            <el-input v-model="user.password" class="input" type="password" placeholder="密码"/>
            <el-input v-model="user.rePassword" class="input" type="password" placeholder="再次输入密码"/>
            <el-input v-model="user.name" class="input" type="text" placeholder="姓名"/>
            <el-input v-model="user.number" class="input" type="text" placeholder="学号"/>
            <el-input v-model="user.school" class="input" type="text" placeholder="学校"/>
            <a class="btn-btn" @click="register">注册为学生</a>
            <a class="btn-login" style="width: 400px;margin-bottom: 15px" @click="enterLogin">
              <span class="str1">已有账号？</span>
              <span class="str2">去登录</span>
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import store from "../store"
import {mapActions, mapState} from "vuex";
export default {
  name: "Register",
  data(){
    return {
      role:"",
      user:{
        username:"12345678901",
        password:"123456",
        rePassword:"123456",
        name:"李野",
        school:"重庆理工大学",
        number:"11823020220",
      },
    }
  },
  store,
  computed:{
    ...mapState([]),
  },
  methods:{
    changeRole(role){
      this.role = role;
    },
    enterLogin(){
      this.$router.push("/login")
    },
    register(){
      if (/^\d{11}$/.test(this.user.username)
          &&this.user.password.length>0
          &&this.user.password === this.user.rePassword
          &&this.user.name.length>0
          &&this.user.school.length>0
          &&(this.role!=="student"||this.user.number.length>0)
      ){
        this.registerAction(this.user);
      }else {
        this.$message.error("请输入正确信息");
      }
    },
    ...mapActions(["registerAction"])

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

.reg-logs {
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  color: #2d2d2d;
  margin: 40px 0;
  padding: 0;
}

.identity-box {
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  color: #2d2d2d;
  margin: 0 auto;
  width: 480px;
  height: 504px;
  background: #FFF;
  box-sizing: border-box;
  border-radius: 4px;
  padding: 30px 30px 0;
}

.choose {
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  color: #2d2d2d;
  padding: 0;
  margin: 70px 0 0 0;
}

>>> .el-button {
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  text-decoration: none;
  outline: none;
  height: 64px;
  border-radius: 4px;
  width: 330px;
  font-size: 16px;
  color: #fff;
  text-align: center;
  background: #32BAF0;
}

.btn-login {
  margin-top: 15px;
  display: flex;
  justify-content: flex-end;
  cursor:default;
}

.str1 {
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  text-align: right;
  color: #AFB1B3;
  text-decoration: none;
}

.str2 {
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  text-align: right;
  color: #32BAF0;
  text-decoration: none;
}

.login-box {
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
  box-shadow: 0 3px 5px rgba(0, 0, 0, .2);
}

.title {
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  color: #2d2d2d;
  margin: 0;
  zoom: 1;
  padding: 30px 0 0 30px;
  display: flex;
  flex-direction: row;
}

.input {
  width: 400px;
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  color: #2d2d2d;
  margin: 0 0 12px 0;
  padding: 0;
  height: 50px;
  border-radius: 4px;
  position: relative;
}

>>> .el-input__inner {
  height: 50px;
}

.padding-cont {
  margin-top: 30px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.btn-btn{
  width: 400px;
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
  margin-top: 12px;
}
.verifyimg{
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  color: #2d2d2d;
  margin: 0;
  padding: 0;
  border: none;
  width: 157px;
  height: 50px;
  float: right;
}
</style>