<template>
  <div>
    <el-row type="flex" justify="space-between" class="message_head">
      <div class="title" @click="$router.push('/')"><i class="el-icon-back" style="margin-right: 10px"></i>课堂</div>
      <div style="font-size: 26px">消息</div>
      <PersonTool/>
    </el-row>
    <el-row type="flex" justify="center" style="margin-top: 25px">
      <el-col :span="18">
        <el-table
            :data="messages"
            style="width: 100%"
            :row-class-name="tableRowClassName">
          <el-table-column
              prop="classify"
              label="类别">
            <template slot-scope="scope">
              <div v-if="scope.row.classify===0">【发布了新作业】</div>
              <div v-else>【催交通知】</div>
            </template>
          </el-table-column>
          <el-table-column
              prop="course"
              label="课程"
              width="180">
          </el-table-column>
          <el-table-column
              prop="title"
              label="作业"
              width="180">
          </el-table-column>
          <el-table-column
              label="操作">
            <template slot-scope="scope">
              <div>
                <el-button @click="read(scope.row)" size="mini">提交</el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import store from "../store"
import PersonTool from "@/components/PersonTool";
import {mapActions, mapState} from "vuex";
export default {
  name: "Messages",
  components:{
    PersonTool
  },
  computed:{
    ...mapState(["messages"])
  },
  methods:{
    tableRowClassName({row, rowIndex}) {
      if (row.isRead === 0) {
        return 'warning-row';
      }
      return '';
    },
    read(row){
      this.setReadAction(row.mid);
      this.$router.push('/submitHomework/'+row.cid+'/'+row.hid)

    },
    ...mapActions(["setReadAction"])
  },
  created() {
  },
  store
}
</script>

<style>
.message_head {
  padding: 10px 40px;
  border-bottom: solid 1px #e6e6e6;
}

.title {
  font-size: 20px;
  cursor: default;
}
.el-table .warning-row {
  background: oldlace !important;
}

</style>