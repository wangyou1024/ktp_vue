<template>
  <div id="app">
    <router-view/>
  </div>
</template>
<script>
import store from "./store"
import {mapActions} from "vuex";
export default {
  created() {
  },
  methods:{
    ...mapActions(["findUserAction","initMessagesAction"])
  },
  store
}
</script>
<style>
body{
  margin: 0;
}
</style>
