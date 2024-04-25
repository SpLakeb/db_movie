<template>

  <div style="margin-top: 60px">

    <div style="height: 376px;text-align: left;position: relative"
         class="movieDetailsBackground" v-if="movieId !== 'empty'">
      <img :src="movieDetails.imgUrl" style="width: 240px;height: 330px;
                position: absolute;bottom: -40px;left: 200px;border: 5px solid #fdfdd8"/>

      <span style="color: white;font-size: 30px;font-weight: bolder;
            position: absolute;bottom: 270px;left: 470px">
        {{ movieDetails.movieName }}
      </span>

      <span style="font-size: 20px;color: white;
            position: absolute;bottom: 240px;left: 470px">
        {{ movieDetails.englishName }}
      </span>

      <div style="position: absolute;bottom: 200px;left: 470px">

        <span v-if="types.length === 0" style="color: white">
          暂无分类
        </span>

        <span v-if="movieTypes.length !== 0">
          <span v-for="item in movieTypes" :key="item.typeId" style="color: white">
            {{ item.typeName }}
          </span>
        </span>
      </div>

      <div style="position: absolute;bottom: 180px;left: 470px">

        <span style="color: white">
          {{ movieDetails.country }} / {{ movieDetails.duration }} 分钟
        </span>
      </div>

      <div style="position: absolute;bottom: 150px;left: 470px">
        <span style="color: white">
          {{ this.$format(movieDetails.releaseTime, 'YYYY-MM-DD HH:mm') }}
          {{ movieDetails.country }} 上映
        </span>
      </div>

      <!--    按钮 想看 评分 -->
      <div style="width: 260px;height: 36px;position: absolute;bottom: 80px;left: 470px">

        <el-row>
          <!--           爱心图片 想看-->
          <el-col :span="10" style="background: #ffffff4f;border-radius: 5px;
          height: 36px" class="mouseToFinger">
            <div @click="handleExpect">
              <!--想看-->
              <el-row v-show="isExpected===false">
                <el-col :span="12" style="text-align: center">
                  <el-image src="/static/爱心.png"
                            style="width: 25px;height: 25px;margin-top: 5px"/>
                </el-col>
                <el-col :span="12">
                  <span style="font-size: 16px;line-height: 36px;color: white">想看</span>
                </el-col>
              </el-row>
              <!--已经想看-->
              <el-row v-show="isExpected===true">
                <el-col :span="12" style="text-align: center">
                  <el-image src="/static/红色爱心.png"
                            style="width: 25px;height: 25px;margin-top: 5px"/>
                </el-col>
                <el-col :span="12">
                  <span style="font-size: 16px;line-height: 36px;color: white">已想看</span>
                </el-col>
              </el-row>
            </div>

          </el-col>

          <!--          星星图片 评分-->
          <el-col :span="12" :offset="2" style="background: #ffffff4f;border-radius: 5px;
          height: 36px" class="mouseToFinger">
            <div @click="handleEvaluate">
              <el-row v-show="loginStatus === false">
                <el-col :span="12" style="text-align: center">
                  <el-image src="/static/星星.png"
                            style="width: 25px;height: 25px;margin-top: 5px"/>
                </el-col>
                <el-col :span="12">
                  <span style="font-size: 14px;line-height: 36px;color: white">评分</span>
                </el-col>
              </el-row>
              <el-row v-show="loginStatus === true">
                <el-col :span="12" style="text-align: center">
                  <el-image src="/static/黄色星星.png"
                            style="width: 25px;height: 25px;margin-top: 5px"/>
                </el-col>
                <el-col :span="12">
                  <span style="font-size: 14px;line-height: 36px;color: white">修改评价</span>
                </el-col>
              </el-row>
            </div>
          </el-col>

        </el-row>

      </div>

      <!--      特惠购票-->
      <div style="width: 260px;height: 38px;position: absolute;bottom: 20px;left: 470px;
      background: rgb(218,41,41);text-align: center" class="mouseToFinger"
           @click="toMovieDetails(movieId)">
        <span style="color: white;line-height: 38px"> 查看电影详情 </span>
      </div>

      <!--      天天口碑 评分-->
      <div style="height: 150px;width: 200px;position: absolute;bottom: 2px;left: 830px">

        <span style="color: white;font-size: 10px"> 天天口碑 </span>

        <div style="margin-top: 5px;width: 200px;height: 40px">

          <el-row>
            <el-col :span="6">
                <span style="color: #f5b43d;font-size: 30px;line-height: 40px"
                      v-if="movieDetails.commentsNumber !== 0">
                    {{ (movieDetails.score / movieDetails.commentsNumber).toFixed(1) }}
                </span>

              <span style="color: #f5b43d;font-size:12px;line-height: 40px"
                    v-if="movieDetails.commentsNumber === 0">
                    暂无评分
                </span>
            </el-col>

            <el-col :span="16" :offset="2">
              <el-rate v-if="movieDetails.commentsNumber !== 0"
                       :value="Number((movieDetails.score / movieDetails.commentsNumber / 2).toFixed(1))"
                       disabled/>

              <span v-if="movieDetails.commentsNumber < 10000"
                    style="color: white;font-size: 10px">
                {{ movieDetails.commentsNumber }}人评分</span>
              <span v-else style="color: white;font-size: 10px">
                {{ (movieDetails.commentsNumber / 10000).toFixed(2) }}万人评分</span>
            </el-col>

          </el-row>

        </div>

        <!--        累计票房-->
        <div style="margin-top: 10px">
          <span style="color: white;font-size: 10px"> 累计票房 </span>

          <div v-if="movieDetails.boxOffice >= 1000000000000" style="margin-top: 10px">
            <span style="font-size: 30px;color: white">
              {{ (movieDetails.boxOffice / 1000000000000).toFixed(1) }}</span>
            <span style="font-size: 15px;color: white">万亿</span>
          </div>

          <div v-else-if="movieDetails.boxOffice >= 100000000" style="margin-top: 10px">
            <span style="font-size: 30px;color: white">
              {{ (movieDetails.boxOffice / 100000000).toFixed(1) }}</span>
            <span style="font-size: 15px;color: white">亿</span>
          </div>

          <div v-else-if="movieDetails.boxOffice >= 10000" style="margin-top: 10px">
            <span style="font-size: 30px;color: white">
              {{ (movieDetails.boxOffice / 10000).toFixed(1) }}</span>
            <span style="font-size: 15px;color: white">万</span>
          </div>

          <div v-else style="margin-top: 10px">
            <span style="font-size: 30px;color: white">{{ movieDetails.boxOffice }}</span>
          </div>

        </div>


      </div>
    </div>

<!--    ----------------------------影厅筛选--------------------------------->

    <div style="width: 100%;margin-top: 120px;z-index: 999">
      <div style="width: 1200px;margin: 0 auto;border: 1px solid #e7e4e4;padding: 10px">

        <el-row>
          <!--品牌-->
          <el-col :span="2">
            <span style="color: #a4a3a3;line-height: 50px">品牌:</span>
          </el-col>
          <el-col :span="22" style="text-align: left" class="type">

            <!--        全部-->
            <div style="padding: 10px">
              <el-tag v-if="activeBrands === '全部'" type="danger" effect="dark" color="red">
                <span class="chooseText">全 部</span>
              </el-tag>
              <el-tag v-else type="info" class="chooseText mouseToFinger"
                      @click="changeActiveBrands('全部')">
                全 部
              </el-tag>
            </div>

            <!--品牌-->
            <div v-for="(item,index) in brands" :key="item" style="padding: 10px">
              <el-tag v-if="index === activeBrands" type="danger" effect="dark" color="red">
                {{ item }}
              </el-tag>
              <el-tag v-else type="info" @click="changeActiveBrands(index)">
                {{ item }}
              </el-tag>
            </div>


          </el-col>

          <!--影厅类型-->
          <el-col :span="2">
            <span style="color: #a4a3a3;line-height: 50px">影厅类型:</span>
          </el-col>
          <el-col :span="22" style="text-align: left" class="type">

            <!--        全部-->
            <div style="padding: 10px">
              <el-tag v-if="activeTypes === '全部'" type="danger" effect="dark" color="red">
                <span class="chooseText">全 部</span>
              </el-tag>
              <el-tag v-else type="info" class="chooseText mouseToFinger"
                      @click="changeActiveTypes('全部')">
                全 部
              </el-tag>
            </div>

            <!--品牌-->
            <div v-for="(item,index) in types" :key="item" style="padding: 10px">
              <el-tag v-if="index === activeTypes" type="danger" effect="dark" color="red">
                {{ item }}
              </el-tag>
              <el-tag v-else type="info" @click="changeActiveTypes(index)">
                {{ item }}
              </el-tag>
            </div>
          </el-col>
        </el-row>
      </div>


      <div style="width: 100%;margin-top: 20px;left: 0;right: 0;z-index: 999">

        <div v-show="this.movies.total === 0" class="empty" style="color: #999999;font-size: 26px">
          <h3>没有查询到相关的信息!</h3>
        </div>
        <!--头-->
        <div style="width: 1200px;margin: 0 auto;">
          <div class="module">
            <div class="mod-title" align="left">
              <h2 v-show="this.movies.total!==0">
                影院列表
              </h2>

              <el-checkbox v-model="checked" style="float: right;margin-top: 30px;margin-right: 30px"
                           class="myRedCheckBox" @change="changeOrder">

                <span style="font-size: 20px" v-if="!loading">离我最近</span>
                <span style="font-size: 20px" v-if="loading">正在重新获取影院
                <i class="el-icon-loading"></i>
              </span>
              </el-checkbox>
            </div>
          </div>
        </div>

        <div align="left" style="width: 1200px;z-index: 999;margin: 20px auto 0;"
             v-show="this.movies.total!==0">
          <el-row>
            <el-col v-for="(item,index) in this.movies.records" :key="index">
              <div style="text-align: left">
                <div style="position: relative;height: 100px;margin: 0 auto 0">
                <span class="cinema" style="font: 20px Extra large"
                      @click="cinemaDetails(item.cinemaId)">{{ item.cinemaName }}</span><br>
                  <br>

                  <span style="font: 14px Base;">
                  {{ item.address }}
                  <span style="float: right">
                    <div style="display: inline-block">

                      <div>
                        <span style="color: #898b8d">暂无票价</span>
                      </div>

                      <div>
                         <span style="font-size: 15px;color: #898b8d">{{
                             item.distance.toFixed(1)
                           }}</span>
                        <span style="font-size: 10px;color: #898b8d">km &nbsp;&nbsp;</span>
                      </div>

                    </div>
                      <el-button type="primary" round @click="cinemaDetails(item.cinemaId)">
                        选座购票</el-button>
                  </span>
                </span><br>
                  <br>
                  <el-tag>影院</el-tag>
                </div>
                <el-divider></el-divider>
              </div>
            </el-col>
          </el-row>
        </div>
        <br>
        <!--页数跳转-->
        <el-pagination v-show="movies.total>movies.size"
                       background
                       layout="prev, pager, next"
                       :total="pagination.total"
                       :current-page="pagination.currentPage"
                       :page-size="pagination.pageSize"
                       @current-change="changePage">
        </el-pagination>
        <br>
        <br>
      </div>

    </div>

<!--评价弹窗-->
    <el-dialog title="评价" :visible.sync="evaluateDialogVisible" v-if="movieId !== 'empty'">
      <div>
        <el-input type="textarea" :rows="2"
                  style="width: 60%"
                  v-model="content"
                  :show-word-limit="true"
                  :maxLength="100"
                  placeholder='请输入影评'>

        </el-input>
        <el-rate
          v-model="grade"
          show-score
          :allow-half="true"
          :max="10"
          style="margin-top: 50px"
        ></el-rate>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="evaluateDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitEvaluate" v-show="loginStatus===false">确 定</el-button>
        <el-button type="primary" @click="submitEvaluate" v-show="loginStatus===true">修 改</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
export default {
  name: "cinema",
  props: ['movieId'],
  data() {
    return {
      isExpected: false,//表示此用户是否期待
      loginStatus: false,
      /** 评价框是否可见 */
      evaluateDialogVisible: false,
      /** 评价分 */
      grade: 0,
      /** 评价内容 */
      content: '',
      comments: [],
      roles: [],
      movieTypes: [],
      movieDetails: [
        {
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
      ],
      //--------------------------------
      checked: false,
      loading: false,
      order: 1,
      activeBrands: '全部',
      activeTypes: '全部',
      movies: [],
      brands: [],
      types: [],
      pagination: {
        currentPage: 1,
        pageSize: 8,
        total: 0,
      }
    }
  },
  mounted() {

    if (this.movieId !== 'empty'){
      this.$axios({
        url: '/movie/movieDetails',
        params: {movieId: this.movieId}
      }).then(res => this.movieDetails = res.data.data)

      this.$axios({
        url: '/movie/movieType',
        params: {movieId: this.movieId}
      }).then(res => {
        this.types = res.data.data
      })

      this.$axios({
        url: '/evaluate/movieEvaluate',
        params: {movieId: this.movieId}
      }).then(res => this.comments = res.data.data)

      /**
       * 获取用户对此电影是否想看
       */
      this.$axios({
        url: '/expect/getExpect',
        params: {movieId: this.movieId}
      }).then(res => {
        this.isExpected = res.data.flag
      })

      /**
       * 获取用户是否对此电影已经评价
       */
      this.$axios({
        url: 'evaluate/checkUserEvaluate',
        params: {movieId: this.movieId}
      }).then(res =>{
        if (res.data.flag) {
          this.loginStatus = true;
          this.grade = res.data.data.grade;
          this.content = res.data.data.content;
        }
      })
    }

    // 获得所有的品牌
    this.$axios({
      url: '/cinema/getAllCinemaBrand',
    }).then((res) => this.brands = res.data.data)

    // 获得所有的影院类型
    this.$axios({
      url: '/cinema/getAllType',
    }).then((res) => this.types = res.data.data)

    // 获得对应的电影数据
    this.getMovies();
  },
  methods: {
    toMovieDetails(id){
      this.$router.push('/movieDetails/'+id)
    },
    handleExpect() {
      this.$axios({
        url: '/expect/setExpect',
        params: {isExpected: !this.isExpected, movieId: this.movieId}
      }).then(res => {
        if (res.data.flag === false) {
          const h = this.$createElement;
          this.$notify({
            title: '错误提示',
            message: h('i', {style: 'color: teal'}, '请先登录!  '),
            type: 'warning'
          });
        } else {
          this.isExpected = !this.isExpected;
        }
      })
    },
    handleEvaluate() {
      this.$axios({
        url: '/user/myInfo'
      }).then(res => {
        if (res.data.flag) {
          this.evaluateDialogVisible = true;
        } else {
          const h = this.$createElement;
          this.$notify({
            title: '错误提示',
            message: h('i', {style: 'color: teal'}, '请先登录!  '),
            type: 'warning'
          })
        }
      })
    },
    submitEvaluate() {
      this.$axios({
        url: '/evaluate/setEvaluate',
        params: {movieId: this.movieId, content: this.content, grade: this.grade}
      }).then(res => {
        if (res.data.flag === true) {
          this.evaluateDialogVisible = false;
          location.reload();
        }
      })
    },

    //--------------------------------------
    getMovies() {
      // 获得对应的电影数据
      this.$axios({
        url: '/cinema/getAllCinemas',
        params: {
          page: this.pagination.currentPage,
          brand: this.brands[this.activeBrands],
          type: this.types[this.activeTypes],
          order: this.order,
          movieId: this.movieId
        }
      }).then((res) => {
        this.movies = res.data.data;
        this.pagination.total = res.data.data.total;
        this.loading = false
      })
    },
    changeActiveBrands(index) {
      this.activeBrands = index;
      this.getMovies();
    },
    changeActiveTypes(index) {
      this.activeTypes = index;
      this.getMovies();
    },
    cinemaDetails(id) {
      this.$router.push('/cinemaDetails/' + id)
    },
    changePage(index) {
      this.pagination.currentPage = index;
      this.getMovies();
    },
    changeOrder(value){
      this.loading = true
      if (value === true){
        this.order = 2
      }else {
        this.order = 1
      }

      this.getMovies()
    }
  }
}
</script>

<style scoped>
.chooseText {
  font-size: 13px;
}

.type {
  display: flex;
  flex-wrap: wrap;
}

.mouseToFinger {
  cursor: pointer;
}

.flex {
  display: flex;
  flex-wrap: wrap;
}

.module .mod-title h2 {
  display: inline-block;
  font-weight: 400;
  font-size: 18px;
  color: #333;
  line-height: 18px;
}

.empty {
  margin: 0 35px;
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

.cinema {
  cursor: pointer;
}

.cinema:hover {
  color: red;
}

.movieDetailsBackground {
  background: url("/static/渐变.jpg");
  background-size: 100% 100%;
}

/*/deep/ .el-rate__icon{*/
/*  font-size: 35px;*/
/*}*/

.mouseToFinger:hover {
  cursor: pointer;
}
</style>
