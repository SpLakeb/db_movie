<template>

  <div style="padding-top: 100px;width: 1500px">

<!--    分成左右两部分-->
    <el-row>
      <el-col :span="14" :offset="1">

        <p style="margin-bottom: 20px;text-align: left;position: relative">
          <span style="font-size: 35px;color: red">正在热映</span>
          <span style="font-size: 35px;color: red">（{{ hotMoviesNums }}部）</span>

          <span class="movie"
                style="font-size: 20px;color: red;position: absolute;right: 100px;top: 15px"
          @click="toAllHotShowing">全部
            <i class="el-icon-d-arrow-right"></i></span>
        </p>

<!--        热映电影排版-->
        <el-row>
          <el-col v-for="(item,index) in movies" :span="6" :key="index" style="margin-bottom: 20px">

            <div>
              <div style="position: relative;height: 250px;width: 180.11px;margin: 0 auto"
                   @click="movieDetails(item.movieId)">
                <el-image
                  :src="item.imgUrl" style="height: 250px" lazy class="movie">
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
<!--                <span class="movie"-->
<!--                      v-if="item.commentsNumber===0"-->
<!--                      style="color: yellow;position: absolute;bottom: 5px;-->
<!--              right: 3px;z-index: 998;font-size: 10px">-->
<!--                    暂无评分-->
<!--                  </span>-->

                <span class="movie"
                      style="color: yellow;position: absolute;bottom: 5px;
              right: 3px;z-index: 998;font-size: 20px;font-style: italic">
                    {{ (item.score).toFixed(1) }}
                  </span>

              </div>

              <el-button style="width: 180.11px" type="danger" @click="toChooseCinema(item.movieId)"> 购
                票</el-button>
            </div>


          </el-col>
        </el-row>

        <!--        即将上映-->

        <p style="margin-bottom: 20px;margin-top: 50px;text-align: left;position: relative">
          <span style="font-size: 35px;color: #3fa7f8;">即将上映</span>
          <span style="font-size: 35px;color: #3fa7f8;">（{{ comingSoonNums }}部）</span>

          <span class="movie"
                style="font-size: 20px;color: #3fa7f8;position: absolute;right: 100px;top: 15px"
          @click="toAllComingSoon">全部
            <i class="el-icon-d-arrow-right"></i></span>
        </p>

        <el-row>
          <el-col v-for="(item,index) in comingSoonMovies" :span="6" :key="index+'a'"
                  style="margin-bottom: 20px">

            <div>
              <div style="position: relative;height: 250px;width: 180.11px;margin: 0 auto"
                   @click="movieDetails(item.movieId)">
                <el-image
                  :src="item.imgUrl" style="height: 250px" lazy class="movie">
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
                  <span class="movie-name movie" style="width: 150px !important;">
                    {{ item.movieName }}
                </span>
                </el-tooltip>

              </div>

              <div style="height: 30px;width: 180.11px;text-align: center;margin: 0 auto;
              border: 1px solid #ece8e8;background: #eaebee">
                <span style="color: #26a0f8;line-height: 30px" v-if="item.expect < 10000">
                  {{ item.expect }}人想看
                </span>
                <span style="color: #26a0f8;line-height: 30px" v-else>
                  {{ (item.expect / 10000).toFixed(1) }}万人想看
                </span>
              </div>

              <el-button style="width: 180.11px" type="primary"
                         @click="toChooseCinema(item.movieId)"> 预 售</el-button>
              <p style="font-size: 15px;color: #b0b1b2">
                {{ $format(item.releaseTime,'MM月DD日上映') }} </p>
            </div>


          </el-col>
        </el-row>

      </el-col>

      <el-col :span="7" :offset="1" style="position: relative">

        <!--        评分最高-->

        <p style="text-align: left;margin-bottom: 30px;position: relative">
          <span style="color: red;font-size: 30px">Top 10</span>
          <span style="color: red;font-size: 16px;position: absolute;right: 100px;top: 8px"
                class="movie" @click="$router.push('/ranking/hotRank')">
            查看完整榜单 >
          </span>
        </p>

        <div v-for="(item,index) in topMovies" :key="index+'b'" style="text-align: left;"
             class="movie handleHover" @click="movieDetails(item.movieId)">
          <div style="width: 90%;height: 100px;border: 1px solid #e8e3e3" v-if="index === 0">

            <el-row>
              <el-col :span="8" style="position: relative">
                <el-image :src="item.imgUrl" lazy fit="fill" class="img"
                          style="width: 100%;height: 100px" ></el-image>

                <el-image src="/static/奖杯.png"
                          style="height: 30px;position: absolute;top: -10px;left: -10px;
                          transform:rotate(-30deg);
                          z-index: 998"/>
              </el-col>
              <el-col :span="15" :offset="1">
                <div style="margin-top: 20px">
                  <span style="font-size: 20px"> {{ item.movieName }} </span>
                </div>
                <div>
                  <span style="color: #fc9928;font-size: 20px;font-style: italic">
                    {{ (item.score).toFixed(1) }}
                  </span>
                </div>
              </el-col>
            </el-row>
          </div>

          <div style="width: 90%;height: 55px;position: relative" v-if="index !== 0">
            <span style="font-size: 20px;color: #ff9900" v-if="index === 1 || index ===2"
                  class="top-text">
              {{ index + 1 }}
            </span>
            <span style="font-size: 20px" v-if="!(index === 1 || index ===2)"
                  class="top-text">
              {{ index + 1 }}
            </span>
            <span class="top-text"> {{ item.movieName }} </span>

            <span style="color: #ffa600;font-size: 20px;font-style: italic"
                  class="top-text rate">
                    {{ (item.score).toFixed(1) }}
                  </span>
          </div>
        </div>

<!--        最受期待-->

        <p style="margin-top: 80px; margin-bottom: 20px; text-align: left;position: relative">
          <span style="font-size: 30px;color: #fdc73d"> 最受期待 </span>
          <span class="movie" style="font-size: 15px;color: #f3c614;
          position: absolute;right: 100px;top: 15px"
                @click="$router.push('/ranking/expectedRank')">查看全部榜单
            ></span>
        </p>

        <div v-for="(item,index) in expectMovies" :key="index+'c'" style="text-align: left;"
             class="movie handleHover" @click="movieDetails(item.movieId)">
          <div style="width: 90%;height: 100px;border: 1px solid #e8e3e3" v-if="index === 0">

            <el-row>
              <el-col :span="8" style="position: relative">
                <el-image :src="item.imgUrl" lazy fit="fill" class="img"
                          style="width: 100%;height: 100px"></el-image>

                <el-image src="/static/皇冠.png"
                          style="height: 30px;position: absolute;top: -22px;left: -15px;
                          transform:rotate(-30deg);
                          z-index: 998"/>
              </el-col>
              <el-col :span="15" :offset="1">
                <div style="margin-top: 20px">
                  <span style="font-size: 20px"> {{ item.movieName }} </span>
                </div>
                <div>
                  <span style="color: #fda22a;font-size: 15px" v-if="item.expect < 10000">
                    {{ item.expect }} 人想看
                  </span>
                  <span style="color: #fda22a;font-size: 15px" v-if="item.expect >= 10000">
                    {{ (item.expect/10000).toFixed(1) }}万 人想看
                  </span>
                </div>
              </el-col>
            </el-row>
          </div>

          <div style="width: 90%;height: 55px;position: relative" v-if="index !== 0">
            <span style="font-size: 20px;color: #ff9900" v-if="index === 1 || index ===2"
                  class="top-text">
              {{ index + 1 }}
            </span>
            <span style="font-size: 20px" v-if="!(index === 1 || index ===2)"
                  class="top-text">
              {{ index + 1 }}
            </span>
            <span class="top-text"> {{ item.movieName }} </span>

            &nbsp;
            <span style="font-size: 10px;color: #bcbdbe">
              {{ $format(item.releaseTime,'MM月DD日') }}
            </span>

            <span style="color: #fda22a;font-size: 15px" class="top-text rate"
                  v-if="item.expect < 10000">
                    {{ item.expect }} 人想看
                  </span>
            <span style="color: #fda22a;font-size: 15px" class="top-text rate"
                  v-if="item.expect >= 10000">
                    {{ (item.expect/10000).toFixed(1) }}万 人想看
                  </span>
          </div>
        </div>

      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "homePage",
  data() {
    return {
      hotMoviesNums: 0,
      comingSoonNums: 0,
      movies: [
        {
          /**电影唯一标识符ID*/
          movieId: 0,
          /**电影中文名*/
          movieName: '',
          /**电影英文名*/
          englishName: '',
          /**电影所属国家和地区*/
          country: '',
          /**电影简介*/
          describe: '',
          /**电影时长，单位分钟*/
          duration: '',
          /**电影上映起始时间*/
          releaseTime: '',
          /**电影下架时间*/
          endTime: '',
          /**电影票房*/
          boxOffice: 0,
          /**电影图片存储路径*/
          imgUrl: '',
          /**期待此电影的人数*/
          expect: 0,
          score: 0,
          commentsNumber: 0
        }
      ],
      comingSoonMovies: [
        {
          /**电影唯一标识符ID*/
          movieId: 0,
          /**电影中文名*/
          movieName: '',
          /**电影英文名*/
          englishName: '',
          /**电影所属国家和地区*/
          country: '',
          /**电影简介*/
          describe: '',
          /**电影时长，单位分钟*/
          duration: '',
          /**电影上映起始时间*/
          releaseTime: '',
          /**电影下架时间*/
          endTime: '',
          /**电影票房*/
          boxOffice: 0,
          /**电影图片存储路径*/
          imgUrl: '',
          /**期待此电影的人数*/
          expect: 0,
          score: 0,
          commentsNumber: 0
        }
      ],
      topMovies: [
        {
          /**电影唯一标识符ID*/
          movieId: 0,
          /**电影中文名*/
          movieName: '',
          /**电影英文名*/
          englishName: '',
          /**电影所属国家和地区*/
          country: '',
          /**电影简介*/
          describe: '',
          /**电影时长，单位分钟*/
          duration: '',
          /**电影上映起始时间*/
          releaseTime: '',
          /**电影下架时间*/
          endTime: '',
          /**电影票房*/
          boxOffice: 0,
          /**电影图片存储路径*/
          imgUrl: '',
          /**期待此电影的人数*/
          expect: 0,
          score: 0,
          commentsNumber: 0
        }
      ],
      expectMovies: [
        {
          /**电影唯一标识符ID*/
          movieId: 0,
          /**电影中文名*/
          movieName: '',
          /**电影英文名*/
          englishName: '',
          /**电影所属国家和地区*/
          country: '',
          /**电影简介*/
          describe: '',
          /**电影时长，单位分钟*/
          duration: '',
          /**电影上映起始时间*/
          releaseTime: '',
          /**电影下架时间*/
          endTime: '',
          /**电影票房*/
          boxOffice: 0,
          /**电影图片存储路径*/
          imgUrl: '',
          /**期待此电影的人数*/
          expect: 0,
          score: 0,
          commentsNumber: 0
        }
      ]
    }
  },
  methods: {
    toChooseCinema(id){
      this.$router.push('/cinema/'+id)
    },
    movieDetails(id){
      this.$router.push('/movieDetails/'+id)
    },
    toAllHotShowing(){
      this.$router.push('/movies/hotShowing')
    },
    toAllComingSoon(){
      this.$router.push('/movies/comingSoon')
    },
  },
  mounted() {

    this.$axios({
      url: '/movie/hotShowing'
    }).then(res => {
      this.movies = res.data.data
    })

    this.$axios({
      url: '/movie/showingNumber'
    }).then(res => this.hotMoviesNums = res.data.data)

    this.$axios({
      url: '/movie/comingSoon'
    }).then(res => {
      this.comingSoonMovies = res.data.data
    })

    this.$axios({
      url: '/movie/comingNumber'
    }).then(res => this.comingSoonNums = res.data.data)

    this.$axios({
      url: '/movie/topMovie'
    }).then(res => {
      this.topMovies = res.data.data
      console.log(this.topMovies)
    })

    this.$axios({
      url: '/movie/expected'
    }).then(res => {
      this.expectMovies = res.data.data
    })
  }
}
</script>

<style scoped>

.el-button {
  border-radius: 0 0 10px 10px;
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

.rate{
  position: absolute;
  right: 20px;
}

.handleHover:hover{
  background: #e7e9ea;
}
</style>
