<template>
  <div style="padding-top: 60px">

    <!--影院相关信息-->
    <div style="height: 376px;text-align: left;position: relative"
         class="cinemaDetailsBackground">
      <el-image src="../../static/影院.png" style="width: 240px;height: 330px;
                position: absolute;bottom: -40px;left: 200px;border: 5px solid #fdfdd8"/>

      <span style="color: white;font-size: 30px;font-weight: bolder;
            position: absolute;bottom: 270px;left: 470px">
        {{ cinemaDetails.cinemaName }}
      </span>
      <span style="font-size: 16px;color: white;
            position: absolute;bottom: 230px;left: 470px">
        {{ cinemaDetails.address }}
      </span>

      <div class="feature">
        <span style="font: 16px Medium;color: white;position: absolute;bottom: 190px;left: 470px">
          影院服务
        </span>
        <span style="font: 14px Base;color:white;position: absolute;bottom: 160px;left: 470px">
        <span class="tag">退票政策</span>
          未取票用户放映前60分钟可退票
      </span>
        <span style="font: 14px Base ;color:white;position: absolute;bottom: 130px;left: 470px">
        <span class="tag">改签政策</span>
          未取票用户放映前60分钟可改签
      </span>
        <span style="font: 14px Base ;color:white;position: absolute;bottom: 100px;left: 470px">
          <span class="tag">额外服务</span>
          影城免费提供3D眼镜，也可自备3D眼镜或到影城购买，3元起/副
      </span>
      </div>
    </div>

    <div style="width: 1200px;margin-top: 120px;margin-left: 200px">
      <!--导航-->
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">天天影院</el-breadcrumb-item>
        <el-breadcrumb-item :to="{path: ''}">影院</el-breadcrumb-item>
        <el-breadcrumb-item>{{ this.cinemaDetails.cinemaName }}</el-breadcrumb-item>
      </el-breadcrumb>

      <!--此影院所有电影信息-->
      <div style="width: 1200px" v-if="this.allMovies.length!==0">
        <el-carousel :interval="4000" @change="change" :initial-index="index" type="card" trigger="click"
                     height="400px"
                     :autoplay='false'>
          <el-carousel-item v-for="(item,index) in this.allMovies" :key="index" style="width: 300px;margin-left: 100px">
            <el-image :src="item.imgUrl" style="width: 100%;height: 100%"></el-image>
          </el-carousel-item>
        </el-carousel>
        <span>{{ index }}</span>
        <br>
        <div align="left" v-show="this.allMovies.length!==0">
          <span v-text="allMovies[index].movieName" style="font: 28px Extra large"></span>
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <span v-if="allMovies[index].commentsNumber !== 0"
                style="display: inline-block;font-size: 24px;color: #faaf00">评分:{{
              (allMovies[index].score / allMovies[index].commentsNumber).toFixed(1)
            }}</span>
          <span v-if="allMovies[index].commentsNumber === 0">暂无评分!</span>
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <span v-text="allMovies[index].duration + '分钟'" style="font: 14px Base"></span>
        </div>
        <!--对应电影的排片信息-->
        <div>
          <el-table :data="scenesDetails" stripe:true style="width: 100%">
            <el-table-column align="center" prop="dateStart" label="电影开场时间" width="280" :show-overflow-tooltip="true">
              <template slot-scope="scope">
                <div style="font: 18px large">
                  {{ $format(scope.row.dateStart, 'YYYY-MM-DD HH:mm') }}
                </div>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="dateEnd" label="电影散场时间" width="280" :show-overflow-tooltip="true">
              <template slot-scope="scope">
                <div style="font: 18px large">
                  {{ $format(scope.row.dateEnd, 'YYYY-MM-DD HH:mm') }}
                </div>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="hallId" label="放映厅" width="200" :show-overflow-tooltip="true">
              <template slot-scope="scope">
                <div style="font: 18px large">{{ scope.row.hallName }}</div>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="price" label="票价(元)" width="200" :show-overflow-tooltip="true">
              <template slot-scope="scope">
                <div style="font-size: 18px;color: #f03d37;font-weight: 700;">{{ scope.row.price }}</div>
              </template>
            </el-table-column>
            <el-table-column align="center" prop="dateStart" label="选座购票" width="200">
              <template slot-scope="scope">
                <el-button round type="primary" size="small"
                           @click="toCheckSeats(scope.row.scenesId)">选座购票
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </div>

    <div v-show="this.allMovies.length === 0">
      <span style="font: 20px Extra large;color: #303133">暂无影片!</span>
    </div>

  </div>
</template>

<script>
export default {
  name: "cinemaDetails",
  props: ['cinemaId'],

  data() {
    return {
      index: 0,
      cinemaDetails: {
        /**电影院的ID*/
        cinemaId: '',
        /**电影院的名称*/
        cinemaName: '',
        /**电影院地址*/
        address: '',
        /**电影院地址的经度*/
        addressX: '',
        /**电影院地址的纬度*/
        addressY: '',
      },
      allMovies: {},
      scenesDetails: [{}],
    }
  },
  methods: {
    change(cur, last) {
      this.index = cur;
      this.$axios({
        url: '/cinema/getAllScenes',
        params: {cinemaId: this.cinemaId, movieId: this.allMovies[cur].movieId}
      }).then((res) => {
        this.scenesDetails = res.data.data;
        console.log(this.scenesDetails)
      })
    },
    toCheckSeats(scenesId){
      this.$router.push('/checkSeats/'+scenesId)
    }
  },
  mounted() {
    this.$axios({
      url: '/cinema/getCinemaDetails',
      params: {cinemaId: this.cinemaId}
    }).then((res) => this.cinemaDetails = res.data.data)

    this.$axios({
      url: '/cinema/getCinemaMovie',
      params: {cinemaId: this.cinemaId}
    }).then((res) => {
      console.log(res.data.data)
      this.allMovies = res.data.data;
      if (this.allMovies.length > 0) {
        this.$axios({
          url: '/cinema/getAllScenes',
          params: {cinemaId: this.cinemaId, movieId: this.allMovies[0].movieId}
        }).then((res) => {
          this.scenesDetails = res.data.data;
        })
      }
    })


  }
}
</script>

<style scoped>
.cinemaDetailsBackground {
  background: url("/static/渐变.jpg");
  background-size: 100% 100%;
}

.banner .wrapper .cinema-brief-container .features-group {
  position: relative;
}

.banner .wrapper .cinema-brief-container .features-group .group-title {
  font-size: 14px;
  margin-bottom: 5px;
  overflow: hidden;
  width: 410px;
}

.movie:hover {
  cursor: pointer;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
</style>
