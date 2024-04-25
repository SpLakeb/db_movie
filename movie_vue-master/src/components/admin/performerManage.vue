<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="演员列表"><performer-list></performer-list></el-tab-pane>
      <el-tab-pane label="添加演员"><add-performer></add-performer></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import performerList from "./children/performer/performerList";
import addPerformer from "./children/performer/addPerformer";
export default {
  components: {
    addPerformer,
    performerList,
  },
  name: "performerManage",
  methods: {

  }
}
</script>

<style scoped>

</style>
