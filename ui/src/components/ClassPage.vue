<template>
  <div>
    <el-row type="flex" justify="space-between" align="middle"
            style="padding:30px 0 14px 0;border-bottom: solid 1px #e6e6e6;">
      <el-col :span="3" style="font-size: 14px">
        置顶课程
      </el-col>
      <el-col :span="9">
        <el-row :gutter="18" type="flex" justify="end" align="middle">
          <el-col>
            <el-row type="flex" align="middle" style="font-size: 14px">
              <img src="../assets/course_sort.png" height="17"/>
              课程排序
            </el-row>
          </el-col>
          <el-col>
            <el-row type="flex" align="middle" style="font-size: 14px;cursor: default;">
              <img src="../assets/archive.png" height="17" @click="handleReorganize"/>
              <div @click="handleReorganize">课程归档</div>
            </el-row>
          </el-col>
          <el-col>
            <el-dropdown>
              <el-button type="primary">+ 创建/加入课程</el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item><div @click="handleCreateCourse">创建课程</div></el-dropdown-item>
                <el-dropdown-item><div @click="handleEnterCourse">加入课程</div></el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-col>
          <el-col>
            <el-button type="primary">+ 快速发布活动</el-button>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-row :gutter="79" style="margin-top: 15px;">
      <el-col :span="6" v-for="course in courses" :key="course.cid" v-if="course.top === 1 && course.reorganize != 1">
        <Course :cid="course.cid"
                :title="course.title"
                :className="course.className"
                :inviteCode="course.inviteCode"
                :semester="course.semester"
                :term="course.term"
                :role="course.role"
                :homeworks='course.homeworks'
                :teacherHeader="course.teacherHeader"
                :teacherName="course.teacherName"
                :top="course.top"
                :reorganize="course.reorganize"
        />
      </el-col>
    </el-row>
    <el-row type="flex" align="middle" style="padding:30px 0 14px 0;border-bottom: solid 1px #e6e6e6;">
      其他课程
    </el-row>
    <el-row :gutter="79" style="margin-top: 15px;">
      <el-col :span="6" v-for="course in courses" :key="course.cid" v-if="course.top === 0 && course.reorganize != 1">
        <Course :cid="course.cid"
                :title="course.title"
                :className="course.className"
                :inviteCode="course.inviteCode"
                :semester="course.semester"
                :term="course.term"
                :role="course.role"
                :homeworks='course.homeworks'
                :teacherHeader="course.teacherHeader"
                :teacherName="course.teacherName"
                :top="course.top"
                :reorganize="course.reorganize"/>
      </el-col>
    </el-row>
    <el-dialog
        title="归档管理"
        :visible="reorganize"
        width="45%"
        center
        :before-close="handleReorganize">
      <el-row :gutter="10" style="margin-left:20px">
        <el-col style="margin-bottom: 15px" :span="12" v-for="(course,index) in courses" v-if="course.reorganize === 1 "
                :key="index">
          <div class="reorganize">
            <el-row style="width: 100%;margin-left: 18px" type="flex" justify="space-around" align="middle">
              <el-col :span="20">
                <el-row class="title" style="margin-bottom: 12px">{{ course.title }}</el-row>
                <el-row type="flex" justify="start" class="title" style="color: #fff;font-size: 12px">
                  <el-col :span="8">角色：{{ course.role === '教' ? '老师' : '学生' }}</el-col>
                  <el-col :span="2"></el-col>
                  <el-col class="title" style="color: #fff;font-size: 12px" :span="14">老师：{{
                      course.teacherName
                    }}
                  </el-col>
                </el-row>
              </el-col>
              <el-col :span="4">
                <el-dropdown>
                  <span class="el-dropdown-link">
                    <img src="../assets/kczt_23.png"/>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item style="cursor: default;font-size: 20px">
                      <div @click="setReorganizeAction(course.cid)">恢复</div>
                    </el-dropdown-item>
                    <el-dropdown-item style="cursor: default;font-size: 20px">
                      <div @click="setQuitAction(course.cid)">退课</div>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </el-col>
            </el-row>
          </div>
        </el-col>
      </el-row>
    </el-dialog>
    <el-dialog title="新建课程"
               :visible="createCourse"
               width="30%"
               :before-close="handleCreateCourse"
    >
      <el-form ref="createForm" :model="course" :rules="courseRules" label-width="80px">
        <el-form-item label="课程名称" prop="title" >
          <el-input v-model="course.title"></el-input>
        </el-form-item>
        <el-form-item label="班级名称" prop="className">
          <el-input v-model="course.className" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="学年" prop="semester">
          <el-input v-model="course.semester" placeholder="2020-2021"></el-input>
        </el-form-item>
        <el-form-item label="学期" prop="term">
          <el-select v-model="course.term" placeholder="请选择学期" style="width: 100%">
            <el-option label="第一学期" value="1"></el-option>
            <el-option label="第二学期" value="2"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleCreateCourse">取 消</el-button>
        <el-button type="primary" @click="readyCreateCourse">创建</el-button>
      </span>
    </el-dialog>
    <el-dialog title="加入课程"
               :visible="enterCourse"
               width="20%"
               :before-close="handleEnterCourse"
               center
    >
      <el-input v-model="inviteCode" placeholder="请输入课程码"></el-input>
      <span slot="footer" class="dialog-footer">
        <el-button @click="handleEnterCourse">取 消</el-button>
        <el-button type="primary" @click="readyEnterCourse">加入</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Course from "@/components/Course.vue";
import {mapActions, mapMutations, mapState} from "vuex";
import store from '../store'


export default {
  name: "ClassPage",
  components: {
    Course
  },
  data() {
    const checkSemester = (rule, value, callback) => {
      if (!/^\d{4}-\d{4}$/.test(value)) {
        return callback(new Error("请输入正确的格式:2020-2021"))
      }else{
        callback();
      }
    }
    return {
      reorganize: false,
      createCourse: false,
      enterCourse: false,
      course: {
        title: "",
        className: "",
        semester: "",
        term: "",
      },
      inviteCode:"",
      courseRules: {
        title: [{required: true, message: "请输入课程名称",trigger:"blur"}],
        className: [{required: true, message: "请输入课程名称", trigger: "blur"}],
        semester: [{required: true, message: "请输入学年", trigger: "blur"}, {validator:checkSemester,trigger: "blur"}],
        term:[{required: true, message: "请输入学期", trigger: "blur"}]
      }
    }
  },
  computed: {
    ...mapState(["courses"])
  },
  store,
  methods: {
    handleReorganize() {
      this.reorganize = this.reorganize ? false : true;
    },
    handleCreateCourse() {
      this.createCourse = this.createCourse ? false : true;
    },
    handleEnterCourse() {
      this.enterCourse = this.enterCourse ? false : true;
    },
    readyCreateCourse(){
      this.$refs["createForm"].validate((valid) => {
        if (valid) {
          this.createCourseAction(this.course);
          this.handleCreateCourse();
          this.$refs["createForm"].resetFields();
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    readyEnterCourse(){
      if (this.inviteCode.length>0){
        this.enterCourseAction(this.inviteCode);
        this.handleEnterCourse();
        this.inviteCode = "";
      }
    },


    ...mapMutations(["setTop", "setReorganize", "setQuit"]),
    ...mapActions(["setTopAction", "setReorganizeAction", "setQuitAction","createCourseAction","enterCourseAction","initCoursesAction"])
  },
  created() {
    this.initCoursesAction();
  }
}
</script>

<style scoped>
.reorganize {
  background: url("../assets/01.jpg");
  width: 268px;
  height: 110px;
  display: flex;
  align-items: center;
}

.title {
  display: block;
  color: #fff;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 20px;
}
</style>