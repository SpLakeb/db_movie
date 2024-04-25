<template>

  <div style="padding-top: 60px">

    <div style="height: 376px;text-align: left;position: relative"
         class="movieDetailsBackground">
      <img :src="movieDetails.imgUrl" alt="图片加载失败"
           width="240px" height="330px"
           style="position: absolute;bottom: -40px;left: 200px;border: 5px solid #fdfdd8"/>

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

        <span v-if="types.length !== 0">
          <span v-for="item in types" :key="item.typeId" style="color: white">
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
           @click="toChooseCinema(movieDetails.movieId)">
        <span style="color: white;line-height: 38px"> 特惠购票 </span>
      </div>

      <!--      天天口碑 评分-->
      <div style="height: 150px;width: 200px;position: absolute;bottom: 2px;left: 830px">

        <span style="color: white;font-size: 10px"> 天天口碑 </span>

        <div style="margin-top: 5px;width: 200px;height: 40px">

          <el-row>
            <el-col :span="6">
                <span style="color: #f5b43d;font-size: 30px;line-height: 40px"
                      v-if="movieDetails.commentsNumber !== 0">
                    {{ (movieDetails.score).toFixed(1) }}
                </span>

              <span style="color: #f5b43d;font-size:12px;line-height: 40px"
                    v-if="movieDetails.commentsNumber === 0">
                    暂无评分
                </span>
            </el-col>

            <el-col :span="16" :offset="2">
              <el-rate v-if="movieDetails.commentsNumber !== 0"
                       :value="Number((movieDetails.score  / 2).toFixed(1))"
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
              {{ (movieDetails.boxOffice / 1000000000000).toFixed(2) }}</span>
            <span style="font-size: 15px;color: white">万亿</span>
          </div>

          <div v-else-if="movieDetails.boxOffice >= 100000000" style="margin-top: 10px">
            <span style="font-size: 30px;color: white">
              {{ (movieDetails.boxOffice / 100000000).toFixed(2) }}</span>
            <span style="font-size: 15px;color: white">亿</span>
          </div>

          <div v-else-if="movieDetails.boxOffice >= 10000" style="margin-top: 10px">
            <span style="font-size: 30px;color: white">
              {{ (movieDetails.boxOffice / 10000).toFixed(2) }}</span>
            <span style="font-size: 15px;color: white">万</span>
          </div>

          <div v-else style="margin-top: 10px">
            <span style="font-size: 30px;color: white">{{ movieDetails.boxOffice }}</span>
          </div>

        </div>


      </div>
    </div>

    <!--    面包屑-->
    <div style="margin-top: 100px;text-align: left">

      <el-row>

        <el-col :span="12" :offset="2">

          <el-breadcrumb separator=">">
            <el-breadcrumb-item :to="{ path: '/' }">天天影院</el-breadcrumb-item>
            <el-breadcrumb-item :to="{ path: '/movies' }">电影</el-breadcrumb-item>
            <el-breadcrumb-item>{{ movieDetails.movieName }}</el-breadcrumb-item>
          </el-breadcrumb>


          <!--          剧情简介-->
          <div style="margin-top: 60px">

            <el-tag type="danger"><span style="font-size: 20px">剧情简介</span></el-tag>

            <p style="color: #898b8d;margin-top: 30px;padding:20px;background: #eeeff3">
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ movieDetails.describe }}
            </p>
          </div>


          <!--          演员-->
          <div style="margin-top: 60px">

            <el-tag type="danger"><span style="font-size: 20px">参演演员</span></el-tag>

            <el-row v-if="roles.length !== 0" style="margin-top: 20px">
              <el-col :span="6" v-for="item in roles" :key="item.performerId"
                      style="padding: 10px;text-align:center">
                <el-image :src="item.imgUrl" style="height: 200px;cursor: pointer"
                          @click="performerDetails(item.performerId)"/>
                <span style="display: block">{{ item.name }}</span>
                <span style="color: #898b8d;display: block">饰：{{ item.roleName }}</span>
              </el-col>
            </el-row>

            <div v-else style="margin-top: 40px">
              <span style="font-size: 25px;color: #DCDFE6">无参演人员</span>
            </div>

          </div>


          <!--          剧情简介-->
          <div style="margin-top: 60px">

            <el-tag type="danger"><span style="font-size: 20px">热门评论</span></el-tag>

            <div style="margin-top: 50px">
              <div v-for="(item,index) in comments" :key="index">

                <el-divider v-if="index !== 0"/>

                <el-avatar :size="45" :src="item.avatar"/>

                <div style="display: inline-block">
                  <span style="font-size: 20px">{{ item.username }}</span>
                  <el-rate style="height: 20px" disabled :value="item.grade/2"/>
                </div>

                <div style="display: inline-block;margin-left: 425px">
                  <span style="color: #898b8d">
                    {{ $format(item.evaluateTime, 'YYYY-MM-DD HH:mm') }}
                  </span>
                </div>
                <p style="color: #666667;padding:20px">
                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ item.content }}
                </p>
              </div>
            </div>

          </div>

        </el-col>

        <el-col :span="6" :offset="2">
          <div style="margin-top: 70px">

            <el-tag type="danger"><span style="font-size: 20px">预告片</span></el-tag>

            <div style="margin-top: 30px">

              <div style="height: 80px;margin-top: 5px;border: 1px solid #f3f0f0"
                   class="hoverToBlack mouseToFinger" @click="toVideo"
                   v-if="movieDetails.previewImgUrl !== null">

                <el-row>
                  <el-col :span="8">
                    <img height="80px"
                              :src="movieDetails.previewImgUrl"/>
                  </el-col>

                  <el-col :span="15" :offset="1">
                    <span style="color: #3d3c3c;font-size: 15px;line-height: 80px">
                      《{{ movieDetails.movieName }}预告片》
                    </span>
                  </el-col>
                </el-row>
              </div>

              <el-empty v-else/>

            </div>
          </div>
        </el-col>

      </el-row>

    </div>

    <el-dialog title="评价" :visible.sync="evaluateDialogVisible">
      <div>

        <el-input type="textarea" :rows="2"
                  style="width: 60%"
                  v-model="content"
                  :show-word-limit="true"
                  :maxLength="100"
                  placeholder='请输入影评'>

        </el-input>
        <el-popover
          placement="bottom"
          title="标题"
          width="500"
          height="700"
          trigger="click"
          v-model="emojiShow"
        >
          <el-button slot="reference">😀</el-button>
          <div class="browBox">
            <ul>
              <li
                v-for="(item, index) in faceList"
                :key="index"
                @click="getBrow(item)"
              >
                {{ item }}
              </li>
            </ul>
          </div>
        </el-popover>
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
const emoji = require("@/assets/emoji.json")
export default {
  name: "movieDetails",
  props: ['movieId'],
  data() {
    return {
      //聊天输入内容
      //表情框是否展示
      emojiShow: false,
      //表情列表
      faceList: [],
      //表情文本
      getBrowString: "",
      isExpected: false,//表示此用户是否期待
      loginStatus: false,
      /** 评价框是否可见 */
      evaluateDialogVisible: false,
      /** 评价分 */
      grade: 0,
      /** 评价内容 */
      content: "",
      comments: [],
      roles: [],
      types: [],
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
      ]
    }
  },
  created() {
    this.loadEmojis();
  },
  methods: {
    //加载表情，存放到表情列表中
    loadEmojis() {
      for (let i in emoji) {
        this.faceList.push(emoji[i].char);
      }
    },
    // 获取用户点击之后的标签 ，存放到输入框内
    getBrow(item) {
      this.content += item;
      this.emojiShow = false;
    },
    toChooseCinema(id){
      this.$router.push('/cinema/'+id)
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
          this.$router.push('/userLogin')
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
          this.$router.push('/userLogin')
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
    toVideo() {
      // this.$router.push('/video/'+this.movieDetails.movieId)

      let routeData = this.$router.resolve({
        name: 'video',
        query: { id: this.movieDetails.movieId }
      })
      window.open(routeData.href, '_blank')

    },
    performerDetails(id) {
      this.$router.push('/performerDetails/' + id)
    },
  },
  mounted() {
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
      url: '/role/movieRoles',
      params: {movieId: this.movieId}
    }).then(res => this.roles = res.data.data)

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
}
</script>

<style scoped>
.browBox {
  width: 100%;
  height: 200px;
  background: #e6e6e6;
  position: absolute;
  z-index: 100;
  bottom: 0px;
  overflow: scroll;
}
ul {
  display: flex;
  flex-wrap: wrap;
  padding: 10px;
}
li {
  cursor: pointer;
  width: 10%;
  font-size: 26px;
  list-style: none;
  text-align: center;
}
.submit-btn {
  margin: 0 15px 10px 0;
  float: right;
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

.hoverToBlack:hover {

  background: #e6e6e7;
}
</style>
