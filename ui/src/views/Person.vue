<template>
<div>
  <el-row type="flex" justify="space-between" align="middle" class="person_head">
    <div class="title" @click="$router.push('/')"><i class="el-icon-back" style="margin-right: 10px"></i>课堂</div>
    <div style="font-size: 26px">个人中心</div>
    <PersonTool/>
  </el-row>
  <el-row type="flex" justify="center" style="margin-top: 25px">
    <el-upload
        class="avatar-uploader"
        :action="BASE_URL+'/user/updateHeader'"
        :data="{'uid':user.uid}"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
      <img v-if="user.header" :src="BASE_URL+'/user/downloadHeader?name='+user.header+'&rand='+new Date().getTime()" class="avatar">
      <img v-else class="avatar" src="../assets/default_header.jpg"/>
    </el-upload>
  </el-row>
  <el-row type="flex" justify="center">
    <h1>{{user.name}}</h1>
  </el-row>
  <hr/>
  <el-row style="width: 300px;margin-left: 20%;">
    <el-form ref="person" :model="person" label-width="80px">
      <el-form-item label="电话">
        <el-input v-model="person.username"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="person.name"></el-input>
      </el-form-item>
      <el-form-item label="学校">
        <el-input v-model="person.school"></el-input>
      </el-form-item>
      <el-form-item label="学号">
        <el-input v-model="person.number"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">保存</el-button>
        <el-button @click="cancel">取消</el-button>
      </el-form-item>
    </el-form>
  </el-row>
</div>
</template>

<script>
import PersonTool from "@/components/PersonTool";
import store from '../store'
import {mapActions, mapMutations, mapState} from "vuex";

export default {
  name: "Person",
  components:{
    PersonTool
  },
  data(){
    return {
      person:{
        uid:"",
        username:"",
        name:"",
        school:"",
        number:"",
        header:null
      },
    }
  },
  computed:{
    ...mapState(["user","BASE_URL"])
  },
  methods:{
    handleAvatarSuccess(res, file) {
      if (res === "successful"){
        this.$message.success("上传成功");
        this.findUserAction();
      }else{
        this.$message.error("上传失败");
      }
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'|| file.type === "image/png";

      if (!isJPG) {
        this.$message.error('只能上传图片jpg或者png');
      }
      return isJPG;
    },
    onSubmit(){
      this.person.uid = this.user.uid;
      this.updateUserAction(this.person);
    },
    cancel(){
      this.person = JSON.parse(JSON.stringify(this.user));
    },
    ...mapActions(["findUserAction","updateUserAction"])
  },

  created() {
    this.person = JSON.parse(JSON.stringify(this.user));
  },
  store
}
</script>

<style scoped>
.person_head {
  padding: 10px 40px;
  border-bottom: solid 1px #e6e6e6;
}

.title {
  font-size: 20px;
  cursor: default;
}
.avatar-uploader{
  width: 100px;
  height: 100px;
  margin:0;
}
.avatar-uploader .el-upload {
  width: 100px;
  height: 100px;
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 100px;
  line-height: 100px;
  text-align: center;
}
.avatar {
  width: 100px;
  height: 100px;
  display: block;
  border-radius: 50%;
}
</style>