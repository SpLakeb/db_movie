<template>
  <div>
    <div style="background: #545c64;position: fixed;left: 0;top: 61px;;right: 0;width: 100%;z-index: 998">
      <el-menu
        :default-active="activeIndex2"
        class="el-menu-demo"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ff0000"
        :router="true"
        style="margin: 0 auto; width: 1518px">
        <el-menu-item index="/ranking/hotRank" style="margin-left: 282px"><span style="font-size: 18px">热映口碑榜</span>
        </el-menu-item>
        <el-menu-item index="/ranking/expectedRank" style="margin-left: 282px"><span
          style="font-size: 18px">最受期待榜</span></el-menu-item>
        <el-menu-item index="/ranking/boxRank" style="margin-left: 282px"><span style="font-size: 18px">国内票房榜</span>
        </el-menu-item>
      </el-menu>
    </div>

    <router-view/>
  </div>

</template>

<script>
export default {
  name: "ranking",
  data() {
    return {
      activeIndex2: '/ranking/hotRank',
    };
  },
  methods: {},
  mounted() {
  }
}
</script>

<style scoped>
.main {
  color: #999;
}

.update-time {
  font-size: 12px;
  text-align: center;
}

.has-fresh-text {
  color: #fc9928;
}

.board-content {
  font-size: 12px;
  color: #999;
}

.board-item-content {
  margin-top: 40px;
  height: 84px;
  display: inline-block;
  vertical-align: middle;
  width: 680px;
  font-size: 0;
  line-height: 1;
}

.movie-item-info {
  text-align: left;
  font-size: 16px;
  display: inline-block;
  width: 420px;
  vertical-align: middle;
}

.board-item-content .name a {
  font-size: 26px;
  color: #333;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 420px;
  display: block;
}

.board-item-content .star {
  margin-top: 18px;
  color: #333;
}

.board-item-content .releaseTime {
  margin-top: 8px;
  color: #999;
}

.movie-item-info .star {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.score-num {
  color: #ffb400;
}

.movie-item-number {
  display: inline-block;
  text-align: right;
  width: 260px;
  vertical-align: top;
  margin-top: 10px;
  font-size: 16px;
}

.score-num .score {
  padding-right: 4px;
}

.score-num .integer {
  font-size: 56px;
  font-weight: 700;
}

.score-num .fraction {
  font-size: 26px;
  font-weight: 700;
}

</style>
