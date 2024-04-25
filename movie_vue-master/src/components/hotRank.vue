<template>
  <div>
    <div class="main" style="width: 1000px;margin: 180px auto 0 auto">

      <div style="width: 100%">
        <p class="update-time">
          <span class="has-fresh-text">已更新</span>
        </p>
        <p class="board-content">
          榜单规则: 将昨日国内热映的影片，按照评分从高到低排列取前10名，每天上午10点更新。相关数据来源于“天天专业版”及“天天电影库”。
        </p>
      </div>
      <dl style="width: 1194px; margin-top: 50px">
        <dd style="width: 100%">
          <el-row v-for="(item,index) in this.topMovies" :key="index" style="margin: 20px">
            <div>
              <el-col :span="2" style="height: 232px">
                <div v-if="index === 0"
                     style="width: 50px; height: 50px; margin: 80px auto 0 auto;background-size: 100%;background: url('/static/榜一.png')">
                  <span style="font-size: 20px; font-style: italic;color: #fc9928; line-height: 50px;"></span>
                </div>
                <div v-else-if="index === 1 ||index === 2 "
                     style="width: 50px; height: 50px; margin: 80px auto 0 auto;border: 1px #ffb400 solid;background-size: 100%;background-color: #ffb400">
                <span
                  style="font-size: 20px; font-style: italic;color: #ffffff; line-height: 50px;">{{ index + 1 }}</span>
                </div>
                <div v-else
                     style="width: 50px; height: 50px; margin: 80px auto 0 auto;border: 1px #f7f7f7 solid;background-size: 100%;background-color: #f7f7f7;">
                <span
                  style="font-size: 20px; font-style: italic;color: #999999; line-height: 50px;">{{ index + 1 }}</span>
                </div>
              </el-col>

              <el-col :span="4" :offset="1" style="background: #fff;height: 232px">
                <el-image :src="item.imgUrl" @click="movieDetails(item.movieId)" style="cursor: pointer;width: 160px;height: 220px"></el-image>
              </el-col>

              <el-col :span="16" :offset="1" style="background: #fff;height: 220px;
            border-bottom: 1px solid #c9c6c6">
                <div class="board-item-content">
                  <div class="movie-item-info">
                  <span class="name" @click="movieDetails(item.movieId)"
                        style="font-size: 26px;color: #333;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;width: 420px;display: block;cursor: pointer">
                    {{ item.movieName }}
                  </span>
                    <p class="star">出品地: {{ item.country }}</p>
                    <p class="releaseTime">上映时间: {{ $format(item.releaseTime, 'YYYY-MM-DD') }}</p>
                  </div>
                  <div class="movie-item-number score-num">
                    <p class="score">
                      <i class="integer">{{ parseInt(item.score) }}.</i>
                      <i class="fraction">{{
                          parseInt((((item.score).toFixed(1) - parseInt(item.score)) * 10).toFixed(1))
                        }}</i>
                    </p>
                  </div>
                </div>
              </el-col>
            </div>

          </el-row>

        </dd>
      </dl>
    </div>
  </div>
</template>

<script>
export default {
  name: "hotRank",
  data() {
    return {
      topMovies: [],
    }
  },
  methods: {
    movieDetails(id) {
      this.$router.push('/movieDetails/' + id)
    }
  },
  mounted() {
    this.$axios({
      url: '/rank/getTop'
    }).then(res => {
      this.topMovies = res.data.data
    })
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
