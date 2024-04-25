<template>
  <div>
    <el-container style="width: 85%;border: 1px solid #e1e1e1;margin: 150px auto 0 auto;
    height: 570px">
      <el-container style="height: 100%">
        <el-aside width="200px">
          <el-menu
            :router="true"
            :default-active="this.$route.path.split('/').pop()"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#f4f3f4"
            text-color="#000"
            style="height: 100%;box-shadow: 0 0 20px #f4f3f4"
            active-text-color="#3fa7f8">
            <el-menu-item index="information">
              <i class="el-icon-s-data"></i>
              <span slot="title" style="color: black">基本信息</span>
            </el-menu-item>

            <el-submenu>
              <template slot="title">
                <i class="el-icon-video-camera"></i>我的订单
              </template>
              <el-menu-item index="orders">
                <span slot="title" style="color: black">已支付</span>
              </el-menu-item>

              <el-menu-item index="ordersNoHandle">
                <span slot="title" style="color: black">未支付</span>
              </el-menu-item>
            </el-submenu>

          </el-menu>
        </el-aside>
        <el-main style="padding-top: 0;">
          <el-row style="border-bottom: #e1e1e1 1px solid;">
            <el-col :span="2">
              <h2 style="width: 100px">{{ title }}</h2>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <router-view></router-view>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "userHomepage",
  computed: {
    title() {
      let path = this.$route.path.split('/').pop();
      switch (path) {
        case 'information':
          return '基本信息';
        case 'orders':
          return '我的订单(已支付)';
        default:
          return '我的订单(未支付)';
      }
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style scoped>

</style>
