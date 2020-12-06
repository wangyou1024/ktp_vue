<template>
  <div style="display: flex;align-items: center">
    <el-dropdown size="medium">
      <el-row style="width: 70px" align="middle" type="flex">
        <el-col :span="10">
          <img src="../assets/tool_icon.png" width="25px" height="25px"/>
        </el-col>
        <el-col :span="14">
          <div style="color:#7f7f7f;cursor: default">工具</div>
        </el-col>
      </el-row>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>
          <el-row type="flex" justify="center" style="height: 32px;width: 145px">
            <el-col :span="5"><img src="../assets/paperrepeaticon.png" height="32" width="32"/></el-col>
            <el-col :span="13" style="font-size: 19px;cursor: default">论文查重</el-col>
          </el-row>
        </el-dropdown-item>
        <el-dropdown-item>
          <el-row type="flex" justify="center" style="height: 32px;">
            <el-col :span="5"><img src="../assets/sjczicon.png" height="32" width="32"/></el-col>
            <el-col :span="13" style="font-size: 19px;cursor: default">学术存证</el-col>
          </el-row>
        </el-dropdown-item>
        <el-dropdown-item>
          <el-row type="flex" justify="center" style="height: 32px;">
            <el-col :span="5"><img src="../assets/addteacher1.png" height="32" width="32"/></el-col>
            <el-col :span="13" style="font-size: 19px;cursor: default">邀请老师</el-col>
          </el-row>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

    <el-popover
        placement="bottom"
        trigger="click"
        width="342"
    >
      <el-row type="flex" justify="center" style="min-height: 250px"
              v-if="messages.length === undefined || messages.length===0">
        暂无新消息
      </el-row>
      <el-row style="min-height: 25px" type="flex" justify="center" align="middle" v-for="(message,index) in messages"
              v-if="message.isRead===0" :key="index">
        <el-col :span="1">
          <div class="noticed"></div>
        </el-col>
        <el-col :span="6">
          <div v-if="message.classify === 0">【作业通知】</div>
          <div v-else-if="message.classify===1">【催交通知】</div>
        </el-col>
        <el-col :span="17">
          <div class="message-content">{{ message.course }}：{{ message.title }}</div>
        </el-col>
      </el-row>
      <el-row type="flex" align="middle">
        <el-button-group style="margin-bottom: -12px;width: 100%">
          <el-button type="info" style="margin: 0 0 0px -12px;width: 54% " @click="setAllReadAction">全部标记已读</el-button>
          <el-button type="info" style="margin: 0 -12px 0px 0;width: 53% " @click="$router.push('/messages')">查看全部
          </el-button>
        </el-button-group>
      </el-row>
      <img slot="reference" src="../assets/bell.png" width="24px" height="24px" style="margin-right: 15px"/>
    </el-popover>

    <el-dropdown>
            <span>
              <el-avatar size="medium" icon="el-icon-user-solid" v-if="this.user.header" :src="BASE_URL+'/user/downloadHeader?name='+this.user.header"></el-avatar>
              <el-avatar size="medium" icon="el-icon-user-solid" v-else></el-avatar>
            </span>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>
          <el-row type="flex" justify="center" align="middle" style="width:150px;height: 30px;">
            <el-col :span="5"><img src="../assets/VIP.png" height="15"/></el-col>
            <el-col :span="9" style="font-size: 14px;cursor: default">开通VIP</el-col>
          </el-row>
        </el-dropdown-item>
        <el-dropdown-item @click.native="$router.push('/person')">
          <el-row type="flex" justify="center" align="middle" style="height: 30px">
            <el-col :span="5"><img src="../assets/set.png" height="15"/></el-col>
            <el-col :span="9" style="font-size: 14px;cursor: default">个人设置</el-col>
          </el-row>
        </el-dropdown-item>
        <el-dropdown-item @click.native="handleLogout">
          <el-row type="flex" justify="center" align="middle" style="height: 30px;">
            <el-col :span="5"><img src="../assets/out.png" height="15"/></el-col>
            <el-col :span="9" style="font-size: 14px;cursor: default">退出账户</el-col>
          </el-row>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
import store from "../store"
import {mapActions, mapState} from "vuex";

export default {
  name: "PersonTool",
  computed: {
    ...mapState(["BASE_URL", "user", "messages"])
  },
  methods: {
    handleLogout() {
      this.$confirm('您确定要退出吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.logoutAction();
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消退出'
        });
      });
    },
    ...mapActions(["logoutAction", "setAllReadAction","findUserAction","initMessagesAction"])
  },
  created(){
    // 刷新后vuex中的内容重置，需要重新加载到vuex中
    this.findUserAction();
    this.initMessagesAction();
  },
  store
}
</script>

<style scoped>
.noticed {
  width: 10px;
  height: 10px;
  color: #fff;
  line-height: 46px;
  font-size: 16px;
  text-align: center;
  border-radius: 50%;
  background: red !important;
}

.message-content {
  word-break: keep-all;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>