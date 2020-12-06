<template>
  <div>
    <div v-if="detailCourse.role === '学'" v-for="(item,index) in homeworks" :key="index">
      <StudentHomework :hid="item.hid"
                       :title="item.title"
                       :content="item.content"
                       :fileList="item.fileList"
                       :startDate="item.startDate"
                       :startTime="item.startTime"
                       :overDate="item.overDate"
                       :overTime="item.overTime"/>
    </div>
    <el-button style="margin-top: 15px" type="primary" v-if="detailCourse.role === '教'" @click="handleCreate">
      添加个人作业
    </el-button>
    <div class="homework-box" v-if="detailCourse.role === '教' && condition === 1 ">
      <el-row style="margin-bottom: 10px">
        <el-input type="text" placeholder="请输入作业名称" v-model="homework.title"></el-input>
      </el-row>
      <el-row style="margin-bottom: 10px">
        <el-input type="textarea" :autosize="{ minRows:5}" placeholder="请输入作业要求" v-model="homework.content"></el-input>
      </el-row>
      <el-row style="margin-bottom: 10px">
        <el-upload
            class="upload-demo"
            :action="BASE_URL+'/file/uploadFile'"
            :data="{'hid':null,'aid':null}"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            :on-success="uploadSuccess"
            :before-upload="handleUpload"
            multiple
            :file-list="homework.fileList">
          <el-button size="small" type="primary">上传附件</el-button>
        </el-upload>
      </el-row>
      <el-row style="margin-bottom: 10px" type="flex" align="middle">
        <div style="margin-right: 15px">截止时间</div>
        <div style="margin-right: 15px">
          <el-date-picker
              v-model="homework.overDate"
              type="date"
              value-format="yyyy-MM-dd"
              format="yyyy-MM-dd"
              placeholder="选择日期">
          </el-date-picker>
        </div>
        <div>
          <el-time-select
              v-model="homework.overTime"
              :picker-options="{
              start: '00:00',
              step: '00:30',
              end: '24:00'
            }"

              placeholder="选择时间">
          </el-time-select>
        </div>
      </el-row>
      <el-row type="flex" justify="end">
        <el-button plain @click="cancel">取消</el-button>
        <el-button type="primary" @click="ok">保存</el-button>
      </el-row>
    </div>
    <div v-if="detailCourse.role === '教'" v-for="(item,index) in homeworks" :key="index">
      <TeacherHomework :hid="item.hid"
                       :title="item.title"
                       :content="item.content"
                       :fileList="item.fileList"
                       :startDate="item.startDate"
                       :startTime="item.startTime"
                       :overDate="item.overDate"
                       :overTime="item.overTime"
                       :corrected="item.corrected"
                       :correcting="item.correcting"
                       :submit="item.submit"
                       :status="0"
      />
    </div>

  </div>
</template>

<script>
import StudentHomework from "@/components/StudentHomework";
import TeacherHomework from "@/components/TeacherHomework";
import store from "../store"
import {mapActions, mapState} from "vuex";

export default {
  name: "HomeWork",
  components: {
    StudentHomework,
    TeacherHomework
  },
  data() {
    return {
      homework: {
        hid: "",
        cid: "",
        title: "",
        content: "",
        fileList: [],
        startDate: "",
        startTime: "",
        overDate: "",
        overTime: "",
        corrected: 0,
        correcting: 0,
        submit: 0
      },
      // 0为不可见状态，1为新增状态
      condition: 0
    };
  },
  computed: {
    ...mapState(["detailCourse", "homeworks", "BASE_URL"])
  },
  methods: {
    handleCreate() {
      this.condition = 1;
    },
    handleRemove(file, fileList) {
      // 删除文件
      this.$axios.get(this.BASE_URL + "/file/deleteFile",
          {params:{'fid':file.fid}})
          .then(res => {
            if (res.data === "successful") {
              this.homework.fileList.forEach((value, index) => {
                if (value.fid === file.fid) {
                  this.homework.fileList.splice(index, 1);
                }
              })
            }
          })
          .catch(error => {
            console.log("handleRemove", error);
          })
    },
    handlePreview(file) {
      window.open(this.BASE_URL + "/file/downloadFile?fid=" + file.fid);
    },
    handleUpload(file) {
      // if (this.condition === 1) {
      //   this.homework.fileList.push({fid: "123", name: "实验一"})
      // }
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    uploadSuccess(response, file, fileList) {
      // 上传成功，加入文件列表
      if (this.condition === 1) {
        this.homework.fileList.push(response)
      }
    },
    cancel() {
      if (this.condition === 1) {
        // 新增状态，删除所有以上传文件
        this.homework.fileList = [];
        this.homework.title = "";
        this.homework.content = "";
        this.homework.overDate = "";
        this.homework.overTime = "";
        this.condition = 0;
      }
    },
    ok() {
      if (this.condition === 1) {
        // 新增状态，保存信息
        const time = new Date();
        this.homework.startDate = time.getFullYear()+"-"+(time.getMonth()+1)+"-"+time.getDate();
        this.homework.startTime = time.getHours()+":"+time.getMinutes();
        this.homework.cid = this.$route.params.id;
        // 提交到vuex中
        this.createHomeworkAction(this.homework);
        this.homework.fileList = [];
        this.homework.title = "";
        this.homework.content = "";
        this.homework.overDate = "";
        this.homework.overTime = "";
        this.homework.startTime = "";
        this.homework.startDate = "";
        this.condition = 0;
      }
    },
    ...mapActions(["createHomeworkAction", "deleteHomeworkFileAction"])
  },
  store
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

.file {
  margin: 0;
  color: #707070;
  line-height: 1.8;
  font-size: 14px;
  cursor: default;
}

.file:hover {
  color: rgb(77, 144, 254)
}

.foot {
  margin-right: 20px;
  color: #A0A0A0;
}

.number {
  font-size: 30px;
  color: #707070;
  text-align: center;
}

.state {
  font-size: 14px;
  text-align: center;
  color: #707070;
}
</style>