<template>
  <div>
    <el-row type="flex" align="middle" style="margin-top: 15px">
      <el-col :span="1" style="height: 1px"></el-col>
      <el-col :span="1" style="font-size: 22px;color: #5F6368;">
        <i class="el-icon-s-unfold"></i>
      </el-col>
      <el-col :span="5" style="font-size: 22px;color: #5F6368;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">课堂</el-breadcrumb-item>
          <el-breadcrumb-item>课程名称（班级名称）</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
      <el-col :span="14"/>
      <el-col :span="2" style="display: flex;justify-content: flex-end">
        <PersonTool/>
      </el-col>
    </el-row>
    <el-row :gutter="10" type="flex" justify="center">
     <el-col :span="20">
       <el-row :gutter="0" type="flex" align="bottom">
        <el-col :span="24">
          <img style="margin-top:50px;margin-bottom:-4px;border-radius: 8px 8px 0 0;" src="../assets/20.png" width="100%">
          <div style="width:100%;position: absolute;top: 98px;left: 40px">
            <p style="margin:0;font-size: 36px;font-weight: 500;color: #FFFFFF">{{ detailCourse.title }}</p>
            <h2 style="margin: 0;font-size:20px;color: #FFFFFF">{{detailCourse.className}}</h2>
            <el-row type="flex" justify="start" style="width: 100%">
              <div class="invitecode">加课码：{{detailCourse.inviteCode}}</div>
              <div class="invitecode"><i class="el-icon-user-solid"></i>同学：{{detailCourse.total}}</div>
              <div class="invitecode"><i class="el-icon-s-data"></i>成绩</div>
            </el-row>
          </div>
        </el-col>
       </el-row>
       <el-row :gutter="0">
         <el-col :span="1">
           <div style="width:100%;height: 61px;background:#F1F3F4;border-radius: 0 0 0 8px"></div>
         </el-col>
         <el-col :span="20">
           <el-menu
           mode="horizontal"
           :default-active="activeIndex"
           background-color="#F1F3F4"
            @select="handleSelect"
           >
             <el-menu-item index="1" style="width: 10%;text-align: center">课堂互动</el-menu-item>
             <el-menu-item index="2" style="width: 10%;text-align: center">作业</el-menu-item>
             <el-menu-item index="3" style="width: 10%;text-align: center">话题</el-menu-item>
             <el-menu-item index="4" style="width: 10%;text-align: center">资料</el-menu-item>
             <el-menu-item index="5" style="width: 10%;text-align: center">测试(考试)</el-menu-item>
             <el-menu-item index="6" style="width: 10%;text-align: center">公告</el-menu-item>
           </el-menu>
         </el-col>
         <el-col :span="3">
           <div style="width: 100%;height: 61px;background:#F1F3F4;border-radius: 0 0 8px 0"></div>
         </el-col>
       </el-row>
       <el-row>
         <el-col :span="24">
           <HelloWorld msg="课堂互动" v-if="activeIndex==='1'"/>
           <Homeworks msg="作业" v-if="activeIndex==='2'"/>
           <HelloWorld msg="话题" v-if="activeIndex==='3'"/>
           <HelloWorld msg="资料" v-if="activeIndex==='4'"/>
           <HelloWorld msg="测试(考试)" v-if="activeIndex==='5'"/>
           <HelloWorld msg="公告" v-if="activeIndex==='6'"/>
         </el-col>
       </el-row>
     </el-col>
    </el-row>
  </div>
</template>

<script>
import PersonTool from "@/components/PersonTool";
import HelloWorld from "@/components/HelloWorld";
import {mapActions, mapState} from "vuex";
import store from "../store";
import Homeworks from "@/components/Homeworks";

export default {
  name: "Detail",
  components: {
    PersonTool,
    HelloWorld,
    Homeworks,
  },
  created() {
    this.setDetailCourseAction(this.$route.params.id);
    this.initHomeworkAction(this.$route.params.id);
  },
  data(){
    return {
      activeIndex:"1"
    }
  },
  computed:{
    ...mapState(["detailCourse"])
  },
  methods:{
    handleSelect(key, keyPath) {
      this.activeIndex = key;
    },
    ...mapActions(["setDetailCourseAction", "initHomeworkAction"])
  },
  store
}
</script>

<style scoped>
.invitecode {
  outline: 0;
  height: 24px;
  background: rgba(255, 255, 255, .2);
  border-radius: 2px;
  font-size: 14px;
  font-family: PingFang-SC-Medium;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  line-height: 22px;
  text-align: center;
  padding: 0 4px 0 2px;
  margin: 5px 10px 0 0;
  display: inline-block;
}
</style>