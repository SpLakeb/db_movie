<template>
    <div style="margin-top: 100px;width: 100%">

      <div
        style="width: 100%;background: #545c64;
        position: fixed;left: 0;top: 60px;right: 0;z-index: 998">
        <el-menu
          :default-active="activeIndex2"
          class="el-menu-demo"
          mode="horizontal"
          background-color="#545c64"
          text-color="#fff"
          :router="true"
          active-text-color="#ff0000" style="width: 1518px;margin: 0 auto">
          <el-menu-item index="/movies/hotShowing" style="margin-left: 280px">
            <span class="text">正在热映</span>
          </el-menu-item>
          <el-menu-item index="/movies/comingSoon"
                        style="margin-left: 300px">
            <span class="text">即将上映</span>
          </el-menu-item>
          <el-menu-item index="/movies/oldMovies"
                        style="margin-left: 300px">
            <span class="text">经典影片</span>
          </el-menu-item>
        </el-menu>
      </div>

      <div style="margin-top: 180px">
        <router-view/>
      </div>

    </div>
</template>

<script>
export default {
  name: "movies",
  data(){
    return{
      activeIndex2: '/movies/hotShowing'
    }
  }
}
</script>

<style scoped>
.text{
  font-size: 18px;
}
</style>
