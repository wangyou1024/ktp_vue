<template>
  <div>
    <div class="homework-box" v-if="condition === 0">
      <el-row type="flex" justify="space-between">
        <el-col :span="10">
          <el-row type="flex">
            <div class="header">发布日期</div>
            <div class="header">{{ startDate }}</div>
            <div class="header">{{ startTime }}</div>
          </el-row>
        </el-col>
        <el-col :span="1" style="margin-right: -40px">
          <el-dropdown>
            <i class="el-icon-more" style="color: #5F6368"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="handleEdit">编辑</el-dropdown-item>
              <el-dropdown-item @click.native="handleDelete">删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
      </el-row>
      <el-row>
        <div class="title" @click="$router.push('/correct/'+detailCourse.cid+'/'+hid)">{{ title }}</div>
      </el-row>
      <el-row>
        <el-col :span="17">
          <el-row>
            <div class="content">
              {{ homework.content }}
            </div>
          </el-row>
          <el-row v-for="file in fileList" :key="file.fid">
            <div class="file" @click="handlePreview(file)">{{ file.name }}</div>
          </el-row>
        </el-col>
        <el-col :span="7">
          <el-row type="flex" justify="space-around" align="middle">
            <el-col :span="8" class="number">{{ corrected }}</el-col>
            <el-col :span="8" class="number">{{ correcting }}</el-col>
            <el-col :span="8" class="number">{{ submit }}</el-col>
          </el-row>
          <el-row style="margin-top: 15px">
            <el-col :span="8" class="state">已批</el-col>
            <el-col :span="8" class="state">未批</el-col>
            <el-col :span="8" class="state">未交</el-col>
          </el-row>
        </el-col>
      </el-row>
      <el-row type="flex" style="margin-top: 15px">
        <el-col :span="20">
          <el-row type="flex" align="middle">
            <i class="el-icon-time" style="color: #A0A0A0"></i>
            <div class="foot">截止日期</div>
            <div class="foot">{{ overDate }}</div>
            <div class="foot">{{ overTime }}</div>
          </el-row>
        </el-col>
      </el-row>
    </div>
    <div class="homework-box" v-if="condition === 1">
      <el-row style="margin-bottom: 10px">
        <el-input type="text" placeholder="请输入作业名称" v-model="homework.title"></el-input>
      </el-row>
      <el-row style="margin-bottom: 10px">
        <el-input type="textarea" :autosize="{ minRows:5}" placeholder="请输入作业内容" v-model="homework.content"></el-input>
      </el-row>
      <el-row style="margin-bottom: 10px">
        <el-upload
            class="upload-demo"
            :action="BASE_URL+'/file/uploadFile'"
            :data="{'hid':homework.hid,'aid':null}"
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
  </div>
</template>

<script>
import store from '../store';
import {mapActions, mapState} from "vuex";

export default {
  name: "TeacherHomeWork",
  props: {
    hid: "",
    cid: "",
    title: "",
    content: "",
    fileList: null,
    startDate: "",
    startTime: "",
    overDate: "",
    overTime: "",
    corrected: "",
    correcting: "",
    submit: "",
    status: ""
  },
  data() {
    return {
      homework: {
        hid: this.hid,
        cid: this.cid,
        title: this.title,
        content: this.content,
        fileList: this.fileList,
        startDate: this.startDate,
        startTime: this.startTime,
        overDate: this.overDate,
        overTime: this.overTime,
        corrected: this.corrected,
        correcting: this.correcting,
        submit: this.submit
      },
      // 0为查看状态，1为修改状态
      condition: this.status
    };
  },
  computed: {
    ...mapState(["detailCourse", "BASE_URL"])
  },
  methods: {
    handleRemove(file, fileList) {
      // 删除文件
      this.$axios.get(this.BASE_URL + "/file/deleteFile", {params:{'fid': file.fid}})
          .then(res => {
            if (res.data === "successful") {
              this.deleteHomeworkFileAction({hid: this.hid, fid: file.fid});
            }
          })
          .catch(error => {
            console.log("handleRemove", error);
          })
    },
    handlePreview(file) {
      // 下载文件
      window.open(this.BASE_URL + "/file/downloadFile?fid=" + file.fid);
    },
    handleUpload(file) {
    },

    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    uploadSuccess(response, file, fileList) {
      // 上传成功，加入文件列表
      if (this.condition === 1) {
        // 修改状态，将传回的文件更新的vuex中
        this.addHomeworkFileAction({hid: this.hid, file: response.data})
      }
    },
    handleEdit() {
      this.condition = 1;
    },
    handleDelete() {
      // 提交给vuex
      this.deleteHomeworkAction(this.hid);
    },
    cancel() {
      if (this.condition === 1) {
        // 恢复信息
        this.homework.title = this.title;
        this.homework.content = this.content;
        this.homework.overDate = this.overDate;
        this.homework.overTime = this.overTime;
        // 修改状态
        this.condition = 0;
      }
    },
    ok() {
      if (this.condition === 1) {
        // 修改状态，将修改提交到vuex中
        this.condition = 0;
        this.editHomeworkAction(this.homework);
      }
    },
    ...mapActions(["editHomeworkAction", "deleteHomeworkAction", "addHomeworkFileAction", "deleteHomeworkFileAction"])
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