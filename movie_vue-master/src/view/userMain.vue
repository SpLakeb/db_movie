<template>

  <div>

    <div style="position: fixed;top: 0;left: 0;right: 0;
             width: 100%;z-index: 999;box-shadow: 0 0 25px #898b8d">
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal"
               menu-trigger="click" active-text-color="#ff0000"
               :router="true"
               style="margin: 0 auto;width: 1518px" ref="menu">

        <el-menu-item style="margin-left: 150px">
          <img src="/static/log.png" height="50" width="200" @click="$router.push('/')"/>
        </el-menu-item>

        <!--        定位-->
        <el-submenu style="margin-left: 20px" index="#">
          <template slot="title" @click="getAllCities">
            <span style="font-size: 18px">
              <i class="el-icon-location-information" @click="flushCity"/>
              {{ localCity }}
            </span>
          </template>

          <div style="width: 400px;height: 500px;overflow: auto;padding: 20px">

            <span style="font-size: 18px">
              <span v-if="currentCity !== null">当前定位城市：
                <span style="color: #898b8d">{{ currentCity }}</span>
              </span>
              <span v-else>点击右侧按钮获取定位</span>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <el-tooltip class="item" effect="dark" content="点击重新定位" placement="bottom-start">
                <span style="font-size: 30px">
                  <i :class="locationFlag" @click="flushCity"/>
                </span>
              </el-tooltip>
            </span>
            <el-divider/>

            <div v-for="(item,index) in allCities" :key="index">

              <h3 style="color: red">{{ item.name }}</h3>

              <div class="flex">
               <span v-for="(item1,index) in item.children" :key="index+'a'" class="finger"
                     style="color: #898b8d" @click="changeCity(item1.name,item1.log,item1.lat)">
                 {{ item1.name }}&nbsp;&nbsp;&nbsp;
               </span>
              </div>

              <el-divider/>

            </div>
          </div>

        </el-submenu>

        <el-menu-item index="/" style="margin-left: 20px">
          <span class="headText">首页</span>
        </el-menu-item>
        <el-menu-item index="/movies" style="margin-left: 20px">
          <span class="headText">电影</span>
        </el-menu-item>
        <el-menu-item index="/cinema/empty" style="margin-left: 20px">
          <span class="headText">影院</span>
        </el-menu-item>
        <el-menu-item index="/ranking" style="margin-left: 20px">
          <span class="headText">榜单</span>
        </el-menu-item>
        <el-menu-item style="margin-left: 20px">
          <div>
            <el-input
              class="search"
              v-model="searchText"
              @focus="getFocus"
              @blur="loseFocus"
              @input="input"
              placeholder="找影视剧、影人、影院">
              <template slot="append">
                <el-button class="search" icon="el-icon-search" @click="search()"/>
              </template>
            </el-input>
            <el-popover trigger="focus" :value="isFocus" v-show="this.searchText !== ''">
              <div v-if="this.cinema.length !==0">
                <el-row v-for="(cinema,index) in this.cinema" :key="index">
                  <el-col :span="8">
                    <span v-show="index === 0" class="suggest-name suggest-name-small">影院</span>
                  </el-col>
                  <el-col :span="16">
                    <a class="suggest-detail" @click="cinemaDetails(cinema.cinemaId)">
                      <span class="detail-name" style="margin-left: 0">{{cinema.cinemaName}}</span>
                    </a>
                  </el-col>
                </el-row>
              </div>

              <div v-if="this.movie.length !==0">
                <el-row v-for="(movie,index) in this.movie" :key="index">
                  <el-col :span="8">
                    <span v-show="index === 0" class="suggest-name suggest-name-small">影视剧</span>
                  </el-col>
                  <el-col :span="16">
                    <a class="suggest-detail" @click="movieDetails(movie.movieId)">
                      <img class="detail-img" :src="movie.imgUrl"/>
                      <span class="detail-name">{{ movie.movieName }}</span>
                    </a>
                  </el-col>
                </el-row>
              </div>

              <div v-if="this.performer.length !==0">
                <el-row v-for="(performer,index) in this.performer" :key="index">
                  <el-col :span="8">
                    <span v-show="index === 0" class="suggest-name suggest-name-small">影人</span>
                  </el-col>
                  <el-col :span="16">
                    <a class="suggest-detail" @click="performerDetails(performer.performerId)">
                      <img class="detail-img" :src="performer.imgUrl"/>
                      <span class="detail-name">{{ performer.name }}</span>
                    </a>
                  </el-col>
                </el-row>
              </div>
            </el-popover>
          </div>
        </el-menu-item>


        <el-submenu style="margin-left: 20px" index="null">
          <template slot="title">
            <el-avatar :size="40" v-if="user === null"
                       src="https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png"></el-avatar>
            <el-avatar :size="40" v-else
                       :src="user.avatar"></el-avatar>
          </template>

          <el-menu-item v-if="user === null" index="/userLogin">登录</el-menu-item>
          <el-menu-item v-if="user !== null" index="/userHomepage/information">个人中心</el-menu-item>
          <el-menu-item v-if="user !== null" index="/userHomepage/orders">我的订单</el-menu-item>
          <el-menu-item v-if="user !== null" @click="logout">退出登录</el-menu-item>

        </el-submenu>

      </el-menu>
    </div>

    <router-view/>

    <div style="margin-top: 300px;background: #3d3c3c;height: 200px">
      <a href="https://beian.miit.gov.cn/" style="font-size: 20px;color: white;
      line-height: 100px">
        皖ICP备2022000783号-1</a>
    </div>
  </div>

</template>

<script>

export default {
  name: "userMain",
  data() {
    return {
      user: null,
      isFocus: false,
      performer: {},
      cinema: {},
      movie: {},
      locationFlag: 'el-icon-location-information',
      localCity: '',
      currentCity: null,
      log: '',
      lat: '',
      activeIndex: '/',
      searchText: '',
      allCities: []
    }
  },
  methods: {
    loseFocus(event) {
      this.isFocus = false;
    },
    getFocus(event) {
      this.input(this.searchText);
    },
    input(value) {
      if (value !== ''){
        this.$axios({
          url: '/search/getSuggestions',
          params: {keyWord: value}
        }).then(res => {
          this.movie = res.data.data.movie[0];
          this.cinema = res.data.data.cinema[0];
          this.performer = res.data.data.performer[0];
          this.isFocus = true;
        })
      }else {
        this.movie = {};
        this.cinema = {};
        this.performer = {};
      }
    },
    search() {
      if (this.searchText === '') {
        const h = this.$createElement;
        this.$notify({
          title: '错误提示',
          message: h('i', {style: 'color: teal'}, '请输入想要搜索的内容'),
          type: 'warning'
        });
      } else {
        this.$router.push('/query/' + this.searchText)
        window.location.reload();
      }
    },
    performerDetails(id) {
      this.searchText = '';
      this.$router.push('/performerDetails/' + id)
    },
    cinemaDetails(id) {
      this.searchText = '';
      this.$router.push('/cinemaDetails/' + id)
    },
    movieDetails(id) {
      this.searchText = '';
      this.$router.push('/movieDetails/' + id)
    },
    flushCity() {
      this.locationFlag = 'el-icon-loading'
      this.$axios({
        url: 'https://restapi.amap.com/v3/ip',
        withCredentials: false,
        params: {key: '6ddd3e885e11b4dd82acec5863e64a5f'}
      }).then(res => {
        this.localCity = res.data.city.substring(0, res.data.city.length - 1)
        this.currentCity = this.localCity
        let arr = res.data.rectangle.split(';')
        let arr1 = arr[0].split(',')
        let arr2 = arr[1].split(',')

        this.log = (parseFloat(arr1[0]) + parseFloat(arr2[0])) / 2
        this.lat = (parseFloat(arr1[1]) + parseFloat(arr2[1])) / 2
        this.locationFlag = 'el-icon-location-information'

        this.updateWhere()
      })
      this.reset()
    },
    getAllCities() {
      this.$axios({
        url: 'http://127.0.0.1:8080/#/static/city.json'
      }).then(res => this.allCities = res.data)
    },
    changeCity(name, log, lat) {
      this.$refs.menu.close('#')
      this.localCity = name
      this.log = log
      this.lat = lat
      this.currentCity = null
      this.updateWhere()
      this.reset()
    },
    logout() {

      this.$axios({
        url: '/user/logout'
      })

      this.$router.push('/userLogin')
    },
    updateWhere() {
      this.$axios({
        url: '/user/updateWhere',
        params: {x: this.log, y: this.lat}
      })
    },
    init() {
      this.$axios({
        url: '/user/myInfo'
      }).then(res => this.user = res.data.data)

      this.getAllCities()

      this.$axios({
        url: 'https://restapi.amap.com/v3/ip',
        withCredentials: false,
        params: {key: '6ddd3e885e11b4dd82acec5863e64a5f'}
      }).then(res => {
        this.localCity = res.data.city.substring(0, res.data.city.length - 1)
        this.currentCity = this.localCity
        let arr = res.data.rectangle.split(';')
        let arr1 = arr[0].split(',')
        let arr2 = arr[1].split(',')

        this.log = (parseFloat(arr1[0]) + parseFloat(arr2[0])) / 2
        this.lat = (parseFloat(arr1[1]) + parseFloat(arr2[1])) / 2

        this.updateWhere()

      })
    },
    reset() {
      this.$router.push('/')
    }
  },
  mounted() {

    this.init()
  }
}
</script>

<style>
.search ~ .suggest-container .suggest-detail-list .detail-name {
  width: 134px;
}

.detail-name {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  display: block;
  height: 68px;
  line-height: 68px;
  margin-left: 50px;
}

.detail-img {
  width: 36px;
  height: 50px;
  margin: 9px 10px 9px 0px;
  float: left;
}

img {
  border-style: none;
}

.suggest-container {
  border: 1px solid #ccc;
  margin-top: 1px;
  border-bottom: none;
  font-size: 14px;
  position: absolute;
  background-color: #fff;
  z-index: 999;
}


.search ~ .suggest-container {
  width: 300px;
  margin-left: 10px;
}

.suggest-detail {
  border-bottom: 1px solid #e5e5e5;
  width: 100%;
  color: #333;
  display: block;
  text-decoration: none;
}

.search ~ .suggest-container .suggest-detail-list {
  width: 300px;
}

.suggest-detail-list {
  float: left;
}

.suggest-name {
  display: inline-block;
  color: #999;
  width: 50px;
  margin-left: 10px;
  height: 68px;
  line-height: 68px;
  float: left;
}

.suggest-name-small {
  height: 34px;
  line-height: 34px;
}

.finger {
  cursor: pointer;
}

.finger:hover {
  color: red !important;
}

.headText {
  font-size: 20px;
}

.search .el-input-group__append {
  border: none;
  background: #f30b0b;
}

.search .el-input-group__append .el-icon-search {
  font-size: 20px;
  color: white;
}

.flex {
  display: flex;
  flex-wrap: wrap;
}

.suggest-body {
  border-bottom: 1px solid #ccc;
}
</style>
