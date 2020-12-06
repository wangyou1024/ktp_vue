<template>
  <div class="course">
    <el-row style="min-height: 28px">
      <div class="bigDiv">
        <div class="squr"></div>
        <div class="ju">{{ role }}</div>
      </div>
    </el-row>
    <router-link :to="'/detail/'+cid" class="name">
      {{ title }}
    </router-link>
    <p class="name" style="font-size: 16px">
      {{ className }}
    </p>
    <el-row type="flex" justify="space-between">
      <el-col :span="12">
        <div class="invitecode">
          加课码：{{ inviteCode }}
        </div>
      </el-col>
      <el-col :span="10">
        <el-row class="time">{{ semester }}</el-row>
        <el-row class="time">{{ term === 1 ? "第一学期" : "第二学期" }}</el-row>
      </el-col>
    </el-row>
    <p class="last">近期作业</p>
    <div style="min-height:75px">
      <p class="homework" v-if="homeworks == null||homeworks.length==0">暂无作业</p>
      <p class="homework" v-for="homework in homeworks" @click="handleHomework(homework.hid)">{{ homework.title }}</p>
    </div>
    <div class="line"></div>
    <el-row type="flex" align="middle" style="margin: 5px 18px">
      <el-col :span="3">
        <el-avatar v-if="teacherHeader !== null || teacherHeader !== '' || teacherHeader !== undefined" size="small" icon="el-icon-user-solid" :src="BASE_URL+'/user/downloadHeader?name='+teacherHeader"/>
      </el-col>
      <el-col :span="4">
        <div style="font-size: 12px;font-weight: 500;color: rgba(95, 99, 104, 1);cursor: default">{{
            teacherName
          }}
        </div>
      </el-col>
      <el-col :span="8"></el-col>
      <el-col :span="6">
        <div style="font-size: 12px;font-weight: 500;color: rgba(95, 99, 104, 1);cursor: default"
             @click="setTopAction(cid)">
          {{ top ? "取消置顶" : "置顶" }}
        </div>
      </el-col>
      <el-col :span="3">
        <el-dropdown>
            <span class="el-dropdown-link">
                <div style="font-size: 12px;font-weight: 500;color:#1da3c5;cursor: default">更多</div>
           </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item style="cursor: default;font-size: 20px">
              <div @click="setQuitAction(cid)">退课</div>
            </el-dropdown-item>
            <el-dropdown-item style="cursor: default;font-size: 20px">
              <div @click="setReorganizeAction(cid)">归档</div>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {mapActions, mapMutations, mapState} from "vuex";
import store from '../store'


export default {
  name: "Course",
  props: {
    cid: "",
    title: "",
    className: null,
    inviteCode: null,
    semester: null,
    term: null,
    role: null,
    homeworks: null,
    teacherHeader: null,
    teacherName: null,
    top: null,
    reorganize: null,
  },
  store,
  computed:{
    ...mapState(["BASE_URL"])
  },
  methods: {
    handleHomework(hid){
      if (this.role === "教"){
        this.$router.push("/correct/"+this.cid+"/"+hid);
      }else{
        this.$router.push("/submitHomework/"+this.cid+"/"+hid);
      }
    },
    ...mapMutations(["setTop", "setReorganize", "setQuit"]),
    ...mapActions(["setTopAction", "setReorganizeAction", "setQuitAction"]),
  }
}
</script>

<style scoped>
.course {
  width: 100%;
  min-height: 275px;
  margin-top: 20px;
  border: solid 1px #e6e6e6;
  border-radius: 10px;
  background: url("../assets/30.png") no-repeat;
}

.bigDiv {
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  color: #2d2d2d;
  outline: 0;
  margin: 0;
  padding: 0;
  height: 28px;
  right: 25px;
  position: absolute;
  width: 24px;
  top: 0;
}

.squr {
  font-size: 14px;
  font-family: 'PingFang SC', tahoma, arial, 'helvetica neue', 'hiragino sans gb', 'microsoft yahei ui', 'microsoft yahei', simsun, sans-serif;
  color: #2d2d2d;
  outline: 0;
  margin: 0;
  padding: 0;
  width: 0;
  height: 0;
  border-left: 10px solid #32BAF0;
  border-right: 10px solid #32BAF0;
  border-bottom: 4px solid transparent;
  position: absolute;
  top: 21px;
  left: 15px;
}

.ju {
  outline: 0;
  margin: 0;
  padding: 0;
  line-height: 22px;
  left: 15px;
  font-size: 14px;
  font-family: PingFangSC-Medium;
  font-weight: 500;
  width: 20px;
  position: absolute;
  text-align: center;
  background: #32BAF0;
  color: #fff;
}

.name {
  margin: 5px 18px;
  word-break: keep-all;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  font-size: 20px;
  color: #FFFFFF;
  cursor: default
}

.invitecode {
  outline: 0;
  height: 22px;
  background: rgba(255, 255, 255, .2);
  border-radius: 2px;
  font-size: 12px;
  font-family: PingFang-SC-Medium;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  line-height: 22px;
  text-align: center;
  padding: 0 4px 0 2px;
  margin: 13px 0 0 18px;
  display: inline-block;
}

.time {
  margin: 0;
  outline: 0;
  float: right;
  font-size: 12px;
  font-family: PingFangSC-Medium;
  font-weight: 500;
  color: rgba(255, 255, 255, 1);
  padding: 0 14px 0 0;
  opacity: .5;
  display: flex;
  align-items: flex-end;
}

.last {
  margin: 40px 0 15px 18px;
  font-size: 12px;
  font-weight: 500;
  color: rgba(95, 99, 104, 1);
}

.homework {
  margin: 0px 18px 15px 18px;
  font-size: 14px;
  color: rgba(31, 32, 35, 1);
  font-weight: 500;
  word-break: keep-all;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  cursor: default;
}

.homework:hover {
  color: #1da3c5;
}

.line {
  height: 0px;
  background: #e6e6e6;
  border: solid 1px #e6e6e6;

}
</style>