<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="影院列表"><cinema-list></cinema-list></el-tab-pane></el-tabs>
  </div>
</template>

<script>
  import cinemaList from "./children/cinema/cinemaList";

export default {
  name: "cinemaManage",
  components:{
    cinemaList
  },
}
</script>

<style scoped>

</style>
