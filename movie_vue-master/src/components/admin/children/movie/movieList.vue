<template>
  <div>
<!--  条件查询  -->
    <el-row :gutter="30">
      <el-col :span="4" :offset="12">
        <el-select v-model="condition" placeholder="请选择">
          <el-option style="height: 50px"
            v-for="item in movieConditions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-col>
      <el-col :span="4">
        <el-input
          placeholder="请输入内容"
          prefix-icon="el-icon-search"
          v-model="keyword">
        </el-input>
      </el-col>
      <el-col :span="4">
        <el-button type="primary" @click="searchMovie">
          搜索 <i class="el-icon-search"></i>
        </el-button>
      </el-col>


    </el-row>
    <el-row :gutter="50">
      <el-col style="margin-top: 50px"  :span="12" v-for="(movie, index) in movieList" :key="movie.movieId">
        <el-card   class="card" shadow="hover" style="border: 1px #99a9bf solid; border-radius: 10px " :body-style="{ padding: '0px' }">
          <el-row :gutter="20">
            <el-col :span="8">
              <el-image  :src="movie.imgUrl" style="width: 100%; height: 100%"></el-image>
            </el-col>
            <el-col :span="9">
              <div style="text-align: left">
                <div>
                  <el-link :underline="false" @click="openMovieDetail(movie)"><h2>{{movie.movieName}}</h2></el-link>
                  <p>{{movie.englishName}}</p>
                </div>
                <div>
                  <el-tag style="margin: 0" v-show="movie.types" type="primary" v-for="type in movieList[index].types" :key="type.typeId">{{ type.typeName }}</el-tag>
                </div>
                <div>
                  <p style="color: #2c3e50">{{movie.country}} / {{movie.duration}}分钟</p>
                  <p>{{$format(movie.releaseTime, "YYYY年MM月DD日")}}上映</p>
                </div>

              </div>
            </el-col>
            <el-col :span="7">
              <h2 style="color: #234234">总票房</h2>
              <div v-if="movie.boxOffice >= 1000000000000" style="margin-top: 10px">
                <span style="font-size: 30px;color: #ff4d51">
                  {{ (movie.boxOffice / 1000000000000).toFixed(1) }}</span>
                <span style="font-size: 15px;color: #ff4d51">万亿</span>
              </div>

              <div v-else-if="movie.boxOffice >= 100000000" style="margin-top: 10px">
                <span style="font-size: 30px;color: #ff4d51">
                  {{ (movie.boxOffice / 100000000).toFixed(1) }}</span>
                <span style="font-size: 15px;color: #ff4d51">亿</span>
              </div>

              <div v-else-if="movie.boxOffice >= 10000" style="margin-top: 10px">
                <span style="font-size: 30px;color: #ff4d51">
                  {{ (movie.boxOffice / 10000).toFixed(1) }}</span>
                <span style="font-size: 15px;color: #ff4d51">万</span>
              </div>
              <div v-else style="margin-top: 10px">
                <span style="font-size: 30px;color: #ff4d51">{{ movie.boxOffice }}</span>
              </div>
              <el-row>
                <div style="text-align: left; margin-top: 30px">
                  <el-rate v-if="movie.commentsNumber !== 0"
                           show-score
                           text-color="#ff9900"
                           score-template="{value}"
                           :value="Number((movie.score / movie.commentsNumber / 2).toFixed(1))"
                           disabled/>

                  <span v-if="movie.commentsNumber < 10000"
                        style="color: gray;font-size: 10px">
                {{ movie.commentsNumber }}人评分</span>
                  <span v-else style="color: gray;font-size: 10px">
                {{ (movie.commentsNumber / 10000).toFixed(2) }}万人评分</span>
                </div>
              </el-row>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!--分页组件-->
      <el-pagination
        style="margin-top: 20px"
        background
        layout="prev, pager, next"
        @current-change="handleCurrentChange"
        :current-page="pagination.currentPage"
        :page-size="pagination.pageSize"
        :total="pagination.total">
      </el-pagination>
  </div>
</template>

<script>
export default {
  name: "movieList",
  data() {
    return {
      list: [
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
          commentsNumber: 0,
          types: []
        }
      ],
      movieList: [],
      //分页组件
      pagination: {
        currentPage: 1, //当前页
        pageSize: 16,   //每页条数
        total: 0,       //总条数
      },
      movieConditions: [
        {
          value: 'box_office',
            label: '按票房排名'
        },
        {
          value: 'score',
          label: '按评分排名',
        },
        {
          value: 'release_time',
          label: '按时间排序',
        }
      ],
      condition: '',
      keyword: ''

    }
  },
  created() {
    this.getMovieList();
  },

  methods: {
    searchMovie() {
      this.pagination.currentPage = 1;
      this.getMovieList()
    },
    //切换页码
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage;
      this.getMovieList();
    },
    //获取所有电影
    getMovieList() {
      let rest = "/"+this.pagination.currentPage+"/"+this.pagination.pageSize
      let params = "?keyword=" + this.keyword
      params += "&condition=" + this.condition
      this.movieList = []
      this.axios.get("/movie/listAllMovie" + rest + params).then((res) => {
        if (res.data.flag) {
          this.list = res.data.data.records
          this.getTypes(0)
          this.pagination.total = res.data.data.total;
        }
      })
    },
    //获取电影的类型
    async getTypes(i) {
      await this.$axios({
        url: '/movie/movieType',
        params: {movieId: this.list[i].movieId}
      }).then(res => {
        this.list[i].types = res.data.data
        if (i + 1 < this.list.length)
          this.getTypes(i+1)
        else {
          this.list.forEach(value => {
            this.movieList.push(value)
          })
        }
      })
    },
    openMovieDetail(movie) {
      this.$emit("addNewTab", movie);
    }


  }
}
</script>

<style scoped>

</style>
