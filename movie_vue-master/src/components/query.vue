<template>
  <div style="padding-top: 100px;width: 1500px">
    <div
      style="width: 100%;background: #545c64;
        position: fixed;left: 0;top: 60px;right: 0;z-index: 998">
      <el-menu
        default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        @select="handleSelect"
        active-text-color="#ff0000" style="width: 1518px;margin: 0 auto">
        <el-menu-item index="1" style="margin-left: 280px">
          <span class="text">影视剧</span>
        </el-menu-item>
        <el-menu-item index="2"
                      style="margin-left: 300px">
          <span class="text">影人</span>
        </el-menu-item>
        <el-menu-item index="3"
                      style="margin-left: 300px">
          <span class="text">影院</span>
        </el-menu-item>
      </el-menu>
    </div>

    <el-row>
      <el-col :offset="1">
        <div v-show="this.activeIndex === '1'">
          <span>123</span>
          <!--电影-->
          <p style="margin-bottom: 20px;text-align: left;position: relative" v-show="this.movies.length !== 0">
            <span style="font-size: 35px;color: red">影视剧</span>
            <span style="font-size: 35px;color: red">（{{ this.movies.length }}部）</span>
          </p>

          <el-row>
            <el-col v-for="(item,index) in this.movies" :span="6" :key="index" style="margin-bottom: 20px">
              <div>
                <div style="position: relative;height: 250px;width: 180.11px;margin: 0 auto"
                     @click="movieDetails(item.movieId)">
                  <el-image
                    :src="item.imgUrl" style="height: 250px" class="movie">
                    <div slot="error">
                      <i class="el-icon-picture-outline"></i>
                    </div>
                  </el-image>

                  <div
                    class="movie"
                    style="background: linear-gradient(to top, rgb(38 36 36 / 95%), #ffffff0a);
                  width: 180.11px;height: 125px;position: absolute;bottom: 0">
                  </div>

                  <el-tooltip effect="light" :content="item.movieName" placement="bottom-start">
                  <span class="movie-name movie">{{ item.movieName }}
                </span>
                  </el-tooltip>

                  <!--评分-->
                  <span class="movie"
                        v-if="item.commentsNumber===0"
                        style="color: yellow;position: absolute;bottom: 5px;
              right: 3px;z-index: 998;font-size: 10px">
                    暂无评分
                  </span>

                  <span class="movie"
                        v-if="item.commentsNumber!==0"
                        style="color: yellow;position: absolute;bottom: 5px;
              right: 3px;z-index: 998;font-size: 20px;font-style: italic">
                    {{ (item.score).toFixed(1) }}
                  </span>

                </div>

                <el-button style="width: 180.11px" type="danger"
                           @click="toChooseCinema(item.movieId)"> 购 票
                </el-button>
              </div>
            </el-col>
          </el-row>

          <div v-show="this.movies.length === 0" class="empty">
            <h3>没有查询到相关的信息!</h3>
          </div>
        </div>

        <div v-show="activeIndex === '2'">
          <!--演员-->
          <p style="margin-bottom: 20px;text-align: left;position: relative" v-show="this.performers.length">
            <span style="font-size: 35px;color: red">影人</span>
            <span style="font-size: 35px;color: red">（{{ this.performers.length }}）</span>
          </p>
          <div>
            <el-row>
              <el-col v-for="(item,index) in this.performers" :span="8" :key="index"
                      style="margin-bottom: 20px">
                <div @click="performerDetails(item.performerId)">
                  <div style="position: relative;height: 250px;width: 180.11px;margin: 0 auto">
                    <el-image
                      :src="item.imgUrl" style="height: 250px" class="movie">
                      <div slot="error">
                        <i class="el-icon-picture-outline"></i>
                      </div>
                    </el-image>
                  </div>
                  <div style="font: 16px Medium">
                    <span>{{ item.name }}</span>
                  </div>
                </div>
              </el-col>
            </el-row>
          </div>

          <div v-show="this.performers.length === 0" class="empty">
            <h3>没有查询到相关的信息!</h3>
          </div>
        </div>

        <div v-show="activeIndex === '3'">
          <!--影院-->
          <p style="margin-bottom: 20px;text-align: left;position: relative" v-show="this.cinemas.length">
            <span style="font-size: 35px;color: red">影院</span>
            <span style="font-size: 35px;color: red">（{{ this.cinemas.length }}）</span>
          </p>

          <el-row>
            <el-col v-for="(item,index) in this.cinemas" :key="index" style="margin-bottom: 20px">

              <div style="text-align: left">
                <div class="cinema" style="position: relative;height: 100px;margin: 0 auto 0 150px;"
                     @click="cinemaDetails(item.cinemaId)">
                  <span style="font: 20px Extra large">{{ item.cinemaName }}</span><br>
                  <br>
                  <span style="font: 14px Base;">{{ item.address }}</span><br>
                  <br>
                  <el-tag>影院</el-tag>
                </div>
                <el-divider></el-divider>
              </div>
            </el-col>
          </el-row>
          <div v-show="this.pagination.total === 0" class="empty">
            <h3>没有查询到相关的信息!</h3>
          </div>

        </div>

      </el-col>
    </el-row>
    <div class="block" style="margin-bottom: 50px;margin-top: 20px" v-show="this.pagination.total > this.pagination.pageSize">
      <el-pagination
        background
        layout="prev, pager, next"
        @current-change="handleMovieCurrentChange"
        :current-page="pagination.currentPage"
        :page-size="pagination.pageSize"
        :total="pagination.total">>
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "query",
  props: ['keyWord'],
  data() {
    return {
      pagination: {
        currentPage: 1, //当前页
        pageSize: 8,   //每页条数
        total: 0,       //总条数
      },
      activeIndex: '1',
      movies: [{
        length
      }],
      cinemas: [{
        length
      }],
      performers: [{
        length
      }],
    }
  },
  methods: {
    toChooseCinema(id) {
      this.$router.push('/cinema/' + id)
    },
    movieDetails(id) {
      this.$router.push('/movieDetails/' + id)
    },
    performerDetails(id) {
      this.$router.push('/performerDetails/' + id)
    },
    cinemaDetails(id) {
      this.$router.push('/cinemaDetails/' + id)
    },
    getMovies() {
      this.pagination.pageSize = 8;
      this.$axios({
        url: '/movie/getAllMovies',
        params: {
          currentPage: this.pagination.currentPage,
          pageSize: this.pagination.pageSize,
          movieKeyword: this.keyWord
        }
      }).then(res => {
        this.movies = res.data.data.records;
        this.pagination.total = res.data.data.total;
      })
    },
    getPerformers() {
      this.pagination.pageSize = 9;
      this.$axios({
        url: '/performer/listPerformers/' + this.pagination.currentPage + "/" + this.pagination.pageSize,
        params: {
          performerKeyword: this.keyWord
        }
      }).then(res => {
        this.performers = res.data.data.records;
        console.log(this.performers)
        this.pagination.total = res.data.data.total;
      })
    },
    getCinemas() {
      this.pagination.pageSize = 8;
      this.$axios({
        url: '/cinema/getAllCinemaBySearch',
        params: {
          currentPage: this.pagination.currentPage,
          pageSize: this.pagination.pageSize,
          cinemaKeyword: this.keyWord
        }
      }).then(res => {
        this.cinemas = res.data.data.records;
        this.pagination.total = res.data.data.total;
      })
    },
    handleSelect(index) {
      this.pagination.currentPage = 1;
      this.activeIndex = index;
      if (index === "1") {
        console.log(1)
        this.getMovies()
      } else if (index === "2") {
        console.log(2)
        this.getPerformers();
      } else {
        console.log(3)
        this.getCinemas();
      }
    },
    handleMovieCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage;
      var index = this.activeIndex;
      if (index === "1") {
        console.log(1)
        this.getMovies()
      } else if (index === "2") {
        console.log(2)
        this.getPerformers();
      } else {
        console.log(3)
        this.getCinemas();
      }
    }
  },
  mounted() {
    this.getMovies();
  }
}
</script>

<style scoped>
.el-row {
  display: flex;
  flex-wrap: wrap;
}

.el-button {
  border-radius: 0 0 10px 10px;
}

.cinema {
  cursor: pointer;
}

.movie-name {

  width: 130px;
  position: absolute;
  bottom: 10px;
  left: 5px;
  z-index: 998;
  color: white;

  overflow: hidden;
  /* 强制文本在一行内显示 */
  white-space: nowrap;
  /* 当对象内文本溢出时显示省略标记  */
  text-overflow: ellipsis;
}

.movie:hover {
  cursor: pointer;
}

.top-text {
  line-height: 55px;
}

.rate {
  position: absolute;
  right: 20px;
}

.handleHover:hover {
  background: #e7e9ea;
}

.el-divider--horizontal {
  display: block;
  height: 1px;
  width: 70%;
  margin: 24px 0 24px 10%;
}

.empty {
  margin: 0 35px;
}

.empty h3 {
  margin-top: 98px;
  font-size: 26px;
  color: #999;
  font-weight: 400;
}
</style>
