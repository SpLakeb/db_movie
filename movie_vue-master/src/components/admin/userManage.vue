<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="用户列表"><user-list></user-list></el-tab-pane></el-tabs>

  </div>
</template>

<script>
  import userList from "./children/user/userList";
export default {
  name: "userManage",
  components:{
    userList
  }
}
</script>

<style scoped>

</style>
