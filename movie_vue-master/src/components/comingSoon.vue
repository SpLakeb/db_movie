<template>

  <div>
    <div style="width: 1200px;margin: 0 auto;border: 1px solid #e7e4e4;
  padding: 10px">

      <el-row>
        <el-col :span="2">
          <span style="color: #a4a3a3;line-height: 50px">类型：</span>
        </el-col>
        <el-col :span="22" style="text-align: left" class="type">

          <!--        全部-->
          <div style="padding: 5px">
            <el-tag v-if="activeTypes === '全部'" type="danger" effect="dark" color="red">
              <span class="chooseText">全 部</span>
            </el-tag>
            <el-tag v-else type="info" class="chooseText mouseToFinger"
                    @click="changeActiveTypes('全部')">
              全 部
            </el-tag>
          </div>

          <!--        分类-->
          <div v-for="(item,index) in types" :key="item.typeId" style="padding: 5px">
            <el-tag v-if="index === activeTypes" type="danger" effect="dark" color="red">
              <span class="chooseText">{{ item.typeName }}</span>
            </el-tag>
            <el-tag v-else type="info" @click="changeActiveTypes(index)">
              <span class="chooseText mouseToFinger">{{ item.typeName }}</span>
            </el-tag>
          </div>

        </el-col>
      </el-row>


      <el-divider/>


      <el-row>
        <el-col :span="2">
          <span style="color: #a4a3a3;line-height: 50px">区域：</span>
        </el-col>
        <el-col :span="22" style="text-align: left" class="type">

          <!--        全部-->
          <div style="padding: 5px">
            <el-tag v-if="activeArea === '全部'" type="danger" effect="dark" color="red">
              <span class="chooseText">全 部</span>
            </el-tag>
            <el-tag v-else type="info" class="chooseText mouseToFinger"
                    @click="changeActiveArea('全部')">
              全 部
            </el-tag>
          </div>

          <!--        分类-->
          <div v-for="(item,index) in areas" :key="item" style="padding: 5px">
            <el-tag v-if="index === activeArea" type="danger" effect="dark" color="red">
              <span class="chooseText">{{ item }}</span>
            </el-tag>
            <el-tag v-else type="info" @click="changeActiveArea(index)">
              <span class="chooseText mouseToFinger">{{ item }}</span>
            </el-tag>
          </div>

        </el-col>
      </el-row>


      <el-divider/>


      <el-row>
        <el-col :span="2">
          <span style="color: #a4a3a3;line-height: 50px">年代：</span>
        </el-col>
        <el-col :span="22" style="text-align: left" class="type">

          <!--        全部-->
          <div style="padding: 5px">
            <el-tag v-if="activeYear === '全部'" type="danger" effect="dark" color="red">
              <span class="chooseText">全 部</span>
            </el-tag>
            <el-tag v-else type="info" class="chooseText mouseToFinger"
                    @click="changeActiveYear('全部')">
              全 部
            </el-tag>
          </div>

          <!--        分类-->
          <div v-for="(item,index) in years" :key="item" style="padding: 5px">
            <el-tag v-if="index === activeYear" type="danger" effect="dark" color="red">
              <span class="chooseText">{{ item }}</span>
            </el-tag>
            <el-tag v-else type="info" @click="changeActiveYear(index)">
              <span class="chooseText mouseToFinger">{{ item }}</span>
            </el-tag>
          </div>

        </el-col>
      </el-row>

    </div>

    <!--    影片-->

    <div style="width: 1100px;margin: 100px auto 0 auto;text-align: left">

      <p v-if="movies.length === 0" style="font-size: 50px;color: #898b8d;margin-left: 450px">
        暂无数据！
      </p>

      <el-row v-else class="flex">
        <el-col :span="6" v-for="item in movies" :key="item.movieId" style="padding: 30px">
          <el-image :src="item.imgUrl" style="height: 300px" @click="toDetails(item.movieId)"
                    class="mouseToFinger"/>

          <div style="text-align: center" class="movie_name">
            <span style="font-size: 18px" @click="toDetails(item.movieId)"
                  class="mouseToFinger">{{ item.movieName }}</span>

           <span style="color: #318fee;font-size: 20px;display: block">
             {{ $format(item.releaseTime,'MM月DD上映') }}</span>
          </div>
        </el-col>
      </el-row>

    </div>


  </div>

</template>

<script>
export default {
  name: "comingSoon",
  data() {
    return {
      loading: false,
      checked: false,
      activeTypes: '全部',
      activeArea: '全部',
      activeYear: '全部',
      movies: [],
      types: [],
      areas: [],
      years: []
    }
  },
  methods: {
    changeActiveTypes(index) {
      this.activeTypes = index
      this.search()
    },
    changeActiveArea(index) {
      this.activeArea = index
      this.search()
    },
    changeActiveYear(index) {
      this.activeYear = index
      this.search()
    },

    search(){
      let searchTypes = '全部'
      let searchArea = '全部'
      let searchYear = '全部'

      if (this.activeTypes !== '全部'){
        searchTypes = this.types[this.activeTypes].typeId
      }
      if (this.activeArea !== '全部'){
        searchArea = this.areas[this.activeArea]
      }
      if (this.activeYear !== '全部'){
        searchYear = this.years[this.activeYear]
      }

      //发起请求更新数据、
      this.$axios({
        url: '/movie/moviesByChoose',
        params: {type: searchTypes, area: searchArea,
          year: searchYear, showType: 2,order: this.checked}
      }).then(res => {
        this.movies = res.data.data
      })
    },
    toDetails(movieId){
      this.$router.push('/movieDetails/'+movieId)
    }
  },
  mounted() {

    this.$axios({
      url: '/type/typeList'
    }).then(res => this.types = res.data.data)

    this.$axios({
      url: '/movie/countryList'
    }).then(res => this.areas = res.data.data)

    this.$axios({
      url: '/movie/yearList'
    }).then(res => this.years = res.data.data)

    this.$axios({
      url: '/movie/allComingSoon'
    }).then(res => {
      this.movies = res.data.data
    })
  }
}
</script>

<style scoped>

.flex{
  display: flex;
  flex-wrap: wrap;
}

.mouseToFinger {
  cursor: pointer;
}

.type {
  display: flex;
  flex-wrap: wrap;
}

.el-divider {
  margin-top: 8px;
  margin-bottom: 8px;
}

.chooseText {
  font-size: 13px;
}

.movie_name{

  width: 215px;
  overflow: hidden;
  /* 强制文本在一行内显示 */
  white-space: nowrap;
  /* 当对象内文本溢出时显示省略标记  */
  text-overflow: ellipsis;
}

</style>
