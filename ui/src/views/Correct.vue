<template>
  <div>
    <el-row type="flex" justify="space-between" class="correct_head">
      <div class="title" @click="$router.push('/detail/'+detailCourse.cid)"><i class="el-icon-back" style="margin-right: 10px"></i>{{ detailCourse.title }}</div>
      <div style="font-size: 26px">{{ homework==null?hid:homework.title }}</div>
      <PersonTool/>
    </el-row>
    <el-row type="flex" justify="center" style="margin-top: 20px">
      <el-col :span="18">
        <el-table
            :border="true"
            :data="answers"
            style="width: 100%">
          <el-table-column
              prop="number"
              label="学号"
          >
          </el-table-column>
          <el-table-column
              prop="name"
              label="姓名"
          >
          </el-table-column>
          <el-table-column
              prop="dateTime"
              label="提交时间">
            <template slot-scope="scope">
              <div
                  v-if="scope.row.dateTime === null || scope.row.dateTime.length === 0 || scope.row.dateTime === undefined">
                未交
              </div>
              <div v-else>{{ scope.row.dateTime }}</div>
            </template>
          </el-table-column>
          <el-table-column
              prop="grade"
              label="分数">
            <template slot-scope="scope">
              <div v-if="scope.row.grade === -1">未批</div>
              <div v-else-if="scope.row.grade === null">_ _</div>
              <div v-else>{{ scope.row.grade }}</div>
            </template>
          </el-table-column>
          <el-table-column
              label="操作">
            <template slot-scope="scope">
              <div
                  v-if="scope.row.dateTime === null || scope.row.dateTime.length === 0 || scope.row.dateTime === undefined">
                <el-button @click="pressed(scope.$index,scope.row)" size="mini" type="danger">催交</el-button>
              </div>
              <div v-else>
                <el-button @click="correcting(scope.$index,scope.row)" size="mini">批改</el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-dialog title="批改作业"
               :visible="correct"
               :before-close="closeCorrect"
               width="80%"
    >
      <el-row :gutter="10">
       <el-col :span="20">
         <el-input type="textarea" :autosize="{minRows:10,maxRows:10}" v-model="answer.content" :disabled="true">
         </el-input>
         <div v-for="(item,index) in answer.fileList" :key="index">
           <div class="file" @click="handlePreview(item)">{{item.name}}</div>
         </div>
       </el-col>
       <el-col :span="4">
         <el-input-number v-model="answer.grade"></el-input-number>
       </el-col>
      </el-row>
      <span slot="footer" class="dialog-footer">
          <el-button @click="closeCorrect">取 消</el-button>
          <el-button type="primary" @click="submitGrade">评 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import PersonTool from "@/components/PersonTool";
import store from "../store"
import {mapActions, mapState} from "vuex";

export default {
  name: "Correct",
  components: {
    PersonTool
  },
  data() {
    return {
      hid: this.$route.params.hid,
      homework:null,
      correct: false,
      answer:{
        aid:"123",
        hid: "123",
        content:"你好",
        fileList:[{fid:"124",name:"答案.doc"},{fid:"123",name:"答案.doc"}],
        grade:null
      }
    }
  },
  created() {
    this.setDetailCourseAction(this.$route.params.cid);
    // if (this.detailCourse.role !== "教"){
    //   this.$router.push("/login");
    // }
    this.initAnswersAction(this.$route.params.hid);
    this.$axios.get(this.BASE_URL+"/homework/findHomeworkByHid",{params:{"hid":this.$route.params.hid}})
    .then(res=>{
      this.homework = res.data;
    })
    .catch(error=>{
      console.log("initHomework",error);
    })
  },
  computed: {
    ...mapState(["detailCourse", "answers","BASE_URL"])
  },
  methods: {
    correcting(index, row) {
      this.answer.aid = row.aid;
      this.answer.hid = row.hid;
      this.answer.content = row.content.length === 0?"暂无留言":row.content;
      this.answer.fileList = row.fileList;
      this.answer.grade = row.grade==-1?0:row.grade;
      this.correct = true;
    },
    closeCorrect() {
      this.correct = false;
    },
    submitGrade(){
      console.log("submitGrade",this.answer);
      this.setGradeAction(this.answer);
      this.correct = false;
    },
    pressed(index, row) {
      this.bindCall({'uid':row.uid,'hid':this.$route.params.hid});
    },
    handlePreview(file) {
      // 下载文件
      window.open(this.BASE_URL + "/file/downloadFile?fid=" + file.fid);
    },
    ...mapActions(["setDetailCourseAction","setGradeAction","initAnswersAction","bindCall"])
  },
  store
}
</script>

<style scoped>
.correct_head {
  padding: 10px 40px;
  border-bottom: solid 1px #e6e6e6;
}

.title {
  font-size: 20px;
  cursor: default;
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
</style>