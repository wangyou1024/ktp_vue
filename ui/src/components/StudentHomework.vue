<template>
  <div class="homework-box">
    <el-row type="flex" align="bottom">
      <el-col :span="20">
        <el-row type="flex">
          <div class="header">发布日期</div>
          <div class="header">{{homework.startDate}}</div>
          <div class="header">{{homework.startTime}}</div>
        </el-row>
        <el-row>
          <div class="title" @click="$router.push('/submitHomework/'+detailCourse.cid+'/'+homework.hid)">
            {{homework.title}}
          </div>
        </el-row>
        <el-row>
          <el-col :span="24">
            <div class="content">
              {{homework.content}}
            </div>
          </el-col>
        </el-row>
        <el-row v-for="(item,index) in homework.fileList" :key="index">
          <div class="file" @click="handlePreview(item)">{{item.name}}</div>
        </el-row>
        <el-row type="flex" style="margin-top: 15px">
          <el-col :span="20">
            <el-row type="flex" align="middle">
              <i class="el-icon-time" style="color: #A0A0A0"></i>
              <div class="foot">截止日期</div>
              <div class="foot">{{homework.overDate}}</div>
              <div class="foot">{{homework.overTime}}</div>
            </el-row>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="4">
        <el-button type="primary" style="width: 100px;float: right" @click="$router.push('/submitHomework/'+detailCourse.cid+'/'+homework.hid)">提交</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>

import store from "../store";
import {mapState} from "vuex";

export default {
  name: "StudentHomeWork",
  props: {
    hid: "",
    title: "",
    content: "",
    fileList: null,
    startDate: "",
    startTime: "",
    overDate: "",
    overTime: "",
  },
  data() {
    return {
      homework: {
        hid: this.hid,
        title: this.title,
        content: this.content,
        fileList: this.fileList,
        startDate: this.startDate,
        startTime: this.startTime,
        overDate: this.overDate,
        overTime: this.overTime,
      },
    };
  },
  store,
  computed:{
    ...mapState(["detailCourse","BASE_URL"])
  },
  methods:{
    handlePreview(file) {
      // 下载文件
      window.open(this.BASE_URL + "/file/downloadFile?fid=" + file.fid);
    },
  }
}
</script>

<style scoped>
.homework-box {
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  color: #2d2d2d;
  padding: 20px 40px 10px 40px;
  border: 1px solid #E2E6ED;
  border-radius: 8px;
  background: #FFF;
  margin: 20px 0 20px;
}

.header {
  color: #5F6368;
  font-size: 14px;
  margin-right: 15px;
}

.title {
  margin: 15px 0 5px 0;
  color: #3B3D45;
  font-size: 20px;
  font-weight: 400;
  cursor: default;
}

.title:hover {
  color: rgb(77, 144, 254)
}

.content {
  margin: 0;
  color: #707070;
  line-height: 1.8;
  font-size: 14px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.file{
  margin: 0;
  color: #707070;
  line-height: 1.8;
  font-size: 14px;
  cursor: default;
}
.file:hover{
  color: rgb(77, 144, 254)
}
.foot {
  margin-right: 20px;
  color: #A0A0A0;
}
</style>