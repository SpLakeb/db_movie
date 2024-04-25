<template>
  <div style="padding-top: 60px">

    <div style="height: 376px;text-align: left;position: relative"
         class="performerDetailsBackground">
      <el-image :src="performerDetails.imgUrl" style="width: 240px;height: 330px;
                position: absolute;bottom: -40px;left: 200px;border: 5px solid #fdfdd8"/>

      <span style="color: white;font-size: 30px;font-weight: bolder;
            position: absolute;bottom: 270px;left: 470px">
        {{ performerDetails.name }}
      </span>

      <span style="font-size: 20px;color: white;
            position: absolute;bottom: 240px;left: 470px">
        {{ performerDetails.englishName }}
      </span>

      <span style="font-size: 16px;color: white;
            position: absolute;bottom: 210px;left: 470px">
        生日:{{$format(performerDetails.birthday,'YYYY年MM月DD日')  }}
      </span>

      <span style="font-size: 16px;color: white;
            position: absolute;bottom: 180px;left: 470px">
       代表作:
      </span>
      <el-row>
        <el-col v-for="(item,index) in this.masterpiece" :key="index" :span="2">
          <div style="position: relative;height: 100px;width: 138px;margin: 210px auto 0 470px"
               @click="movieDetails(item.movieId)">
            <el-image
              :src="item.imgUrl" style="width: 100px;height: 138px" lazy class="movie">
              <div slot="error">
                <i class="el-icon-picture-outline"></i>
              </div>
            </el-image>

            <div
              class="movie"
              style="background: linear-gradient(to top, rgb(38 36 36 / 95%), #ffffff0a);
                  width: 100px;position: absolute">
            </div>

            <el-tooltip effect="light" :content="item.movieName" placement="bottom-start" style="margin-top: 200px">
                  <span class="movie-name movie" style="!important;width: 100px">
                    {{ item.movieName }}
                </span>
            </el-tooltip>

          </div>
        </el-col>
      </el-row>


      <div style="height: 150px;width: 200px;position: absolute;bottom: 2px;left: 900px">
        <!--        累计票房-->
        <div style="margin-top: 10px">
          <span style="color: white;font-size: 10px"> 累计票房 </span>

          <div v-if="boxOffice >= 1000000000000" style="margin-top: 10px">
            <span style="font-size: 30px;color: white">
              {{ (boxOffice / 1000000000000).toFixed(1) }}</span>
            <span style="font-size: 15px;color: white">万亿</span>
          </div>

          <div v-else-if="boxOffice >= 100000000" style="margin-top: 10px">
            <span style="font-size: 30px;color: white">
              {{ (boxOffice / 100000000).toFixed(1) }}</span>
            <span style="font-size: 15px;color: white">亿</span>
          </div>

          <div v-else-if="boxOffice >= 10000" style="margin-top: 10px">
            <span style="font-size: 30px;color: white">
              {{ (boxOffice / 10000).toFixed(1) }}</span>
            <span style="font-size: 15px;color: white">万</span>
          </div>

          <div v-else style="margin-top: 10px">
            <span style="font-size: 30px;color: white">{{ boxOffice }}</span>
          </div>

        </div>
      </div>
    </div>

    <div class="module" style="width: 1200px;margin-top: 60px">
      <div class="mod-title" style="width: 730px;height: 48px">
        <h2 style="margin-right: 400px">
          简介
        </h2>
      </div>

      <div class="cele-desc">
        <p style="margin-left: 140px;text-align: left;line-height: 30px;font: 16px Medium">
          {{ this.performerDetails.description }}</p>
      </div>

      <div class="mod-title" style="width: 730px;height: 48px">
        <h2 style="margin-right: 340px">
          作品 （{{ this.movies.length }}部）
        </h2>
      </div>

      <div style="margin-left: 200px">
        <el-row>
          <el-col v-for="(item,index) in this.movies" :key="index" :span="8">
            <div  @click="movieDetails(item.movieId)">
              <el-image
                :src="item.imgUrl" style="width: 160px;height: 220px" lazy class="movie">
                <div slot="error">
                  <i class="el-icon-picture-outline"></i>
                </div>
              </el-image>
            </div>

            <div>
              <span>上架时间:{{$format(item.releaseTime,'YYYY年MM月DD日上映')}}</span>
            </div>

            <h3>
              <span>{{ item.movieName }}</span>
            </h3>

          </el-col>
        </el-row>

      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "performerDetails",
  props: ['performerId'],
  data() {
    return {
      editDialogVisible: false,
      performerDetails: {
        performerId: '',
        /**演员姓名*/
        name: '',
        /**演员图片*/
        imgUrl: '',
        /**简介*/
        description: '',
        /**出生日期*/
        birthday: '',
      },
      masterpiece: [],
      boxOffice: 0,
      movies: {
        /**电影唯一标识符ID*/
        movieId: '',
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
    }
  },
  methods: {
    movieDetails(id) {
      this.$router.push('/movieDetails/' + id)
    },
  },
  mounted() {
    this.$axios({
      url: '/performer/performerDetails',
      params: {performerId: this.performerId}
    }).then((res) => this.performerDetails = res.data.data)

    this.$axios({
      url: '/performer/getBoxOffice',
      params: {performerId: this.performerId}
    }).then((res) => this.boxOffice = res.data.data)

    this.$axios({
      url: '/performer/getMasterpiece',
      params: {performerId: this.performerId}
    }).then((res) => this.masterpiece = res.data.data)

    this.$axios({
      url: '/performer/getAllMovie',
      params: {performerId: this.performerId}
    }).then((res) => {
      console.log(res.data.data)
      this.movies = res.data.data
    })
  }
}
</script>

<style scoped>
.performerDetailsBackground {
  background: url("/static/渐变.jpg");
  background-size: 100% 100%;
}

.mouseToFinger:hover {
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

.module .mod-title h2 {
  display: inline-block;
  font-weight: 400;
  font-size: 18px;
  color: #333;
  line-height: 18px;
}

.module .mod-title h2:before {
  float: left;
  content: "";
  display: inline-block;
  width: 4px;
  height: 18px;
  margin-right: 6px;
  background-color: #ef4238;
}

.module .mod-content {
  margin-top: 20px;
  color: #333;
}

.introduce .folding-content {
  max-height: 149px;
  overflow: hidden;
}

.cele-desc {
  line-height: 35px;
}
</style>
