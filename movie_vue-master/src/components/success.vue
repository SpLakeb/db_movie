<template>

  <div style="margin-top: 150px">
    <div style="margin: 0 auto;width: 100%;">

      <el-steps :active="3" align-center finish-status="success">
        <el-step title="选择电影场次"></el-step>
        <el-step title="选择座位"></el-step>
        <el-step title="支付订单"></el-step>
        <el-step title="支付成功，等待观影"></el-step>
      </el-steps>
    </div>

    <div style="margin-top: 100px">
      <el-result icon="success" title="支付成功">
        <template slot="subTitle">
          请持订单二维码去影院取票观影
        </template>
        <template slot="extra">
          <el-button type="primary" size="medium" @click="$router.push('/')">返回首页</el-button>
        </template>
      </el-result>
    </div>
  </div>
</template>

<script>
export default {
  name: "success"
}
</script>

<style scoped>

</style>
