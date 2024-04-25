<template>
  <div>
    <!-- logo -->
    <div style="text-align: left; margin-left: 17%; margin-top: 40px">
      <el-image
        style="width: 200px; height: 66px"
        src="/static/log.png"
        fit="fit"
      >
      </el-image>
    </div>
    <el-row>
      <el-col :span="13">
        <!-- 图片 -->
        <div style="text-align: left; margin-left: 30%; margin-top: 40px">
          <img
            style="width: 474px; height: 524px"
            src="/static/userLogin.png"/>
        </div>
      </el-col>

      <el-col :span="11">

        <router-view/>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {

};
</script>

<style scoped>

</style>

