<template>
  <div>
    <el-row type="flex" justify="space-between" class="submit_head">
      <div class="title" @click="$router.push('/detail/'+detailCourse.cid)"><i class="el-icon-back"
                                                                               style="margin-right: 10px"></i>{{
          detailCourse.title
        }}
      </div>
      <div style="font-size: 26px">{{ homework.title }}</div>
      <PersonTool/>
    </el-row>
    <el-row type="flex" justify="center">
      <el-col :span="18">
        <el-row style="margin:20px 40px">
          {{ homework.content }}
        </el-row>
        <el-row style="margin:20px 40px">
          <el-upload
              class="upload-demo"
              :action="BASE_URL+'/file/uploadFile'"
              :data="fileData"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :before-remove="beforeRemove"
              :on-success="handleSuccess"
              multiple
              :file-list="answer.fileList">
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-row>
        <el-row style="margin:20px 40px">留言</el-row>
        <el-row style="margin:20px 40px">
          <el-input type="textarea" :autosize="{minRows:10,maxRows:10}" v-model="answer.content"/>
        </el-row>
        <el-row style="margin:20px 40px">
          <el-button type="primary" @click="submitAnswer">提交</el-button>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import PersonTool from "@/components/PersonTool";

export default {
  name: "SubmitHomework",
  components: {
    PersonTool
  },
  data() {
    return {
      hid: this.$route.params.hid,
      homework: {title: "题目", content: "内容"},
      fileData: {"aid":null,"hid":null},
      correct: false,
      answer: {
        aid: null,
        content: "你好",
        fileList: [{fid: "124", name: "答案.doc"}, {fid: "123", name: "答案.doc"}],
        grade: -1
      }
    }
  },
  created() {
    this.setDetailCourseAction(this.$route.params.cid);
    // if (this.detailCourse.role !== "学") {
    //   this.$router.push("/login");
    // }
    this.initHomework();
    this.initAnswer();
  },
  computed: {
    ...mapState(["user", "detailCourse", "BASE_URL"])
  },
  methods: {
    initAnswer() {
      this.$axios.get(this.BASE_URL + "/answer/findAnswerByUidAndHid", {
        params: {
          'uid': JSON.parse(window.sessionStorage.getItem("user")).uid,
          'hid': this.$route.params.hid
        }
      })
          .then(res => {
            if (res.data !== "") {
              this.answer = res.data;
              this.fileData = {'aid':res.data.aid,'hid':this.$route.params.hid};
            } else {
              this.answer = {
                aid: null,
                hid: this.$route.params.hid,
                content: "你好",
                fileList: [],
                grade: -1}
            }
          })
          .catch(error=>{
            console.log("initAnswer",error);
          })
    },
    initHomework(){
      this.$axios.get(this.BASE_URL+"/homework/findHomeworkByHid",{
        params: {
          'hid':this.$route.params.hid
        }
      }).then(res=>{
        this.homework = res.data;
      })
      .catch(error=>{
        console.log("initHomework", error);
      })
    },
    handleRemove(file, fileList) {
      this.$axios.get(this.BASE_URL + "/file/deleteFile", {params:{"fid":file.fid}})
          .then(res => {
            if (res.data === "successful") {
              this.answer.fileList.forEach((item, index) => {
                if (item.fid === file.fid) {
                  this.answer.fileList.splice(index, 0);
                }
              });
            }
          })
          .catch(error => {
            console.log("handleRemove", error);
          })
    },
    handlePreview(file) {
      window.open(this.BASE_URL + "/file/downloadFile?fid=" + file.fid);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    handleSuccess(response, file, fileList) {
      this.answer.fileList.push(response);
    },
    submitAnswer(){
      const time = new Date();
      this.answer.dateTime = time.getFullYear()+"-"+(time.getMonth()+1)+"-"+time.getDate() + " "+ time.getHours()+":"+time.getMinutes();;
      if (this.answer.aid != null){
        this.$axios.post(this.BASE_URL+"/answer/updateContent",{'content':this.answer.content,'aid':this.answer.aid})
        .then(res=>{
          if (res.data === "successful"){
            this.$message.success("修改成功");
            this.initAnswer();
          }
        })
        .catch(error=>{
          console.log("submitAnswer",error);
        })
      }else{
        this.answer.uid = this.user.uid;
        this.answer.name = this.user.name;
        this.answer.number = this.user.number;
        this.$axios.post(this.BASE_URL+"/answer/submitAnswer",this.answer)
        .then(res=>{
          if (res.data === "successful"){
            this.$message.success("提交成功");
          }
        })
        .catch(error=>{
          console.log("submitAnswer",error);
        })
      }
    },
    ...mapActions(["setDetailCourseAction"])
  }
}
</script>

<style scoped>
.submit_head {
  padding: 10px 40px;
  border-bottom: solid 1px #e6e6e6;
}

.title {
  font-size: 20px;
  cursor: default;
}
</style>