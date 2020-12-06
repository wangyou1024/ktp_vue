<template>
  <div>
    {{msg}}
    {{count}}
  </div>
</template>

<script>
import store from '../store'
import {mapState,mapMutations,mapActions} from 'vuex'
export default {
  name: 'HelloWorld',
  props: {
    msg:"",
  },
  created() {
    this.addAction(2);
  },
  data(){
    return {
      // msg:"hello",
    };
  },
  computed:{
    ...mapState(["count"])
  },
  methods:{
    ...mapMutations(["add","reduce"]),
    ...mapActions(["addAction","reduceAction"])
  },
  store
}
</script>


<style scoped>

</style>
