<template>
  <div>
    <el-row style="margin: 20px" :gutter="20">
      <el-col :span="6">

        <el-image style="width: 100%" :src="movie.imgUrl"></el-image>
      </el-col>
      <el-col :span="6" style="text-align: left">
        <div><span style="font-size: 30px; font-weight: bolder">{{movie.movieName}}</span></div>
        <div><span>{{movie.englishName}}</span></div>
        <div style="margin-top: 20px">
          <el-breadcrumb separator="/" style="font-size: 20px;">
            <el-breadcrumb-item v-for="type in movie.types" :key="type.typeId"><span style="color: #3fa7f8">{{type.typeName}}</span></el-breadcrumb-item>
          </el-breadcrumb>
        </div>
        <div style="text-align: left; margin-top: 20px">
          <span style="font-size: 20px;color: #ff4d51">{{movie.country}} / {{movie.duration}}分钟</span>
        </div>
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
        <div style="text-align: left">
          <h2 style="color: #234234">累计票房</h2>
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
        </div>
      </el-col>
      <el-col :span="10" :offset="2">
        <el-row>
          <el-col style="text-align: left;">
            <el-tag type="success"><span style="font-size: 20px;">剧情简介</span></el-tag>
            <el-card shadow="" style="margin-top: 10px">
              <el-scrollbar :native="false" style="height: 150px;">
                <p style="margin: 0; color: #8c939d">{{movie.describe}}</p>
              </el-scrollbar>
            </el-card>

          </el-col>
        </el-row>
        <el-row style="margin-top: 10px">
          <el-col style="text-align: left;">
            <el-tag type="success"><span style="font-size: 20px;">预告片</span></el-tag>
            <div style="height: 80px; width: 400px; margin-top: 5px;border: 1px solid #f3f0f0"
                 class="hoverToBlack mouseToFinger" @click="playVideo">
              <el-row>
                <el-col :span="8">
                  <el-image style="height: 100%" :src="movie.previewImgUrl"/>
                </el-col>
                <el-col :span="15" :offset="1">
                    <span style="color: #3d3c3c;font-size: 15px;line-height: 80px">
                      《{{movie.movieName}}预告片》
                    </span>
                </el-col>
              </el-row>
            </div>
            <!-- 预告预览弹窗 -->
            <el-dialog :title="movie.movieName" :visible="videoDialogVisible" @close="closeVideoDialog" width="500" :modal-append-to-body="false">
              <video id="video" style="width: 100%" v-if="movie.previewUrl" controls="controls">
                <source :src="movie.previewUrl">
              </video>
            </el-dialog>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-row style="margin: 20px" :gutter="20">
      <el-col>
<!--    演员列表    -->
        <div style=" text-align: left">

          <el-tag type="success"><span style="font-size: 20px">参演演员</span></el-tag>

          <el-row v-if="movie.roles" style="margin-top: 20px">
            <el-col style="text-align: center" :span="4" v-for="item in movie.roles" :key="item.performerId">
              <el-image :src="item.imgUrl" style="height: 200px;"/>
              <span style="display: block">{{ item.name }}</span>
              <span style="color: #898b8d;display: block">饰：{{ item.roleName }}</span>
            </el-col>
          </el-row>

          <div v-else style="margin-top: 40px">
            <span style="font-size: 25px;color: #DCDFE6">无参演人员</span>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row style="margin: 20px; text-align: left" :gutter="20">

      <el-col>
        <el-row>
          <el-tag type="success"><span style="font-size: 20px">热门评论</span></el-tag>
        </el-row>

        <el-row  style="margin-top: 20px;border: 2px #c0ccda solid; border-radius: 10px">
          <el-col v-for="(item,index) in movie.comments" :key="index">
            <el-card style="margin: 20px" shadow="hover">
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
            </el-card>
          </el-col>
          <!--分页组件-->
          <el-col style="text-align: center">
            <el-pagination
              style="margin-top: 10px; margin-bottom: 20px"
              background
              layout="prev, pager, next"
              @current-change="handleCurrentChange"
              :current-page="pagination.currentPage"
              :page-size="pagination.pageSize"
              :total="pagination.total">
            </el-pagination>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
<!--  信息修改  -->
    <el-row style="margin: 20px; text-align: left" :gutter="20">
      <el-col>
        <el-row>
          <el-tag type="success"><span style="font-size: 20px">信息修改</span></el-tag>
        </el-row>
        <el-col style="margin-top: 20px;border: 2px #c0ccda solid; border-radius: 10px">
          <el-collapse accordion>
            <el-collapse-item name="1">
              <template slot="title">
                <h2>信息</h2>
              </template>
              <el-form style="width: 70%;margin: auto" label-position="right" :rules="rules" label-width="150px" :model="cloneMovie" ref="cloneMovie">
                <el-form-item prop="movieName" label="中文名">
                  <el-input v-model="cloneMovie.movieName"></el-input>
                </el-form-item>
                <el-form-item prop="englishName" label="英文名">
                  <el-input v-model="cloneMovie.englishName"></el-input>
                </el-form-item>
                <el-form-item prop="country" label="国家和地区">
                  <el-input v-model="cloneMovie.country"></el-input>
                </el-form-item>
                <el-form-item prop="describe" label="电影简介">
                  <el-input type="textarea" :autosize="{minRows:5, maxRows:5}" v-model="cloneMovie.describe"></el-input>
                </el-form-item>
                <el-form-item prop="duration" label="电影时长(分钟)">
                  <el-input v-model.number="cloneMovie.duration"></el-input>
                </el-form-item>
                <el-form-item prop="date" label="上映与下架时间" >
                  <el-date-picker
                    v-model="date"
                    style="width: 100%"
                    type="monthrange"
                    range-separator="至"
                    start-placeholder="上映时间"
                    end-placeholder="下架时间">
                  </el-date-picker>
                </el-form-item>
                <el-form-item style="text-align: center">
                  <el-button type="primary" @click="modifyMovieMessage">修改</el-button>
                </el-form-item>

              </el-form>
            </el-collapse-item>
            <el-collapse-item name="2">
              <template slot="title">
                <h2>类型</h2>
              </template>
              <el-row style="width: 100%">
                <el-col :span="11">
                  <div><h4 style="margin-top: 5px;color: #ff9900">电影类别</h4></div>
                  <div  style="height: 250px; box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); border-radius: 5px; text-align: left" >
                    <el-tag
                      style="margin: 5px"
                      :key="type.typeId"
                      v-for="type in cloneMovie.types"
                      closable
                      effect="dark"
                      :type="type.type"
                      :disable-transitions="false"
                      @close="removeType(type)">
                      {{type.typeName}}
                    </el-tag>
                  </div>
                </el-col>
                <el-col :span="2" style="font-size: 40px; margin-top: 150px; ">
                  <i class="el-icon-back"></i>
                  <i class="el-icon-right"></i>
                </el-col>
                <el-col :span="11">
                  <div><h4 style="margin-top: 5px;color: #ff9900">所有类别</h4></div>
                  <div>
                    <div style="height: 250px; box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); border-radius: 5px; text-align: left" >
                      <el-scrollbar style="height: 100%">
                        <el-tag

                          style="margin: 5px"
                          :key="type.typeId"

                          v-for="type in allTypes"
                          :type="type.type"
                          :disable-transitions="false"
                          @close="addType(type)"
                          @click="addType(type)">
                          {{type.typeName}}
                        </el-tag>
                      </el-scrollbar>
                    </div>
                  </div>
                </el-col>
              </el-row>
              <el-row>
                <el-col style="text-align: center; margin-top: 20px">
                  <el-button type="primary" @click="modifyMovieType">修改</el-button>
                </el-col>
              </el-row>
            </el-collapse-item>
            <el-collapse-item name="3">
              <template slot="title">
                <h2>角色</h2>
              </template>
              <el-scrollbar>
                <el-table
                  :data="this.cloneMovie.roles"
                  style="width: 100%; align-content: center"
                  border
                  height="200">
                  <el-table-column
                    label="序号"
                    align="center"
                    type="index">
                  </el-table-column>
                  <el-table-column label="姓名" align="center">
                    <template slot-scope="scope">
                      <el-popover trigger="hover" placement="top">
                        <p>姓名: {{ scope.row.name }}</p>
                        <el-image style="width: 150px" :src="scope.row.imgUrl"></el-image>
                        <div slot="reference" class="name-wrapper">
                          <el-tag size="medium">{{ scope.row.name }}</el-tag>
                        </div>
                      </el-popover>
                    </template>
                  </el-table-column>
                  <el-table-column
                    label="饰演角色"
                    align="center"
                    property="roleName">
                  </el-table-column>
                  <el-table-column label="操作" align="center">
                    <template slot-scope="scope">
                      <el-button
                        size="mini"
                        type="danger"
                        @click="deleteRole(scope.$index, scope.row)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </el-scrollbar>
              <el-divider></el-divider>
              <div>
                <el-row>
                  <el-input style="width: 300px" v-model="keyword" placeholder="请输入关键字">
                    <el-button slot="append" type="primary" @click="searchPerformer" icon="el-icon-search"></el-button>
                  </el-input>
                </el-row>
                <el-row>
                  <el-col :span="6" v-for="(performer, index) in performerList" :key="index"><div>
                    <el-popover
                      placement="right"
                      title="简介"
                      width="200"
                      trigger="hover">
                      <el-scrollbar :native="false"  style="height: 150px;">
                        <p>{{performer.description}}</p>
                      </el-scrollbar>
                      <el-card slot="reference" :body-style="{ padding: '10px' }" shadow="hover" style="margin: 20px; text-align: center">
                        <el-image
                          style="height: 250px"
                          :src="performer.imgUrl">
                          <div slot="error" class="image-slot">
                            <i class="el-icon-picture-outline"></i>
                          </div>
                        </el-image>
                        <div>
                          <div style="font-weight: bold" class="perMsg">
                            {{performer.name}}
                          </div>
                          <div class="perMsg">
                            <el-tag>{{performer.englishName}}</el-tag>
                          </div>
                          <div class="bottom clearfix">
                            <el-button type="primary" :disabled="disabled(performer)" class="button" size="small" round @click="openBindDialog(performer)">角色绑定</el-button>
                          </div>
                        </div>
                      </el-card>
                    </el-popover>

                  </div></el-col>
                </el-row>
                <!--分页组件-->
                <el-pagination
                  style="margin-top: 20px; text-align: center"
                  background
                  layout="prev, pager, next"
                  @current-change="handlePerformerPage"
                  :current-page="perPagination.currentPage"
                  :page-size="perPagination.pageSize"
                  :total="perPagination.total">
                </el-pagination>
                <!--绑定角色信弹窗-->
                <el-dialog style="text-align: center" title="角色绑定" :visible.sync="roleBindDialogVisible" :modal-append-to-body="false">
                  <el-form :model="roleForm">
                    <h3>{{roleForm.performer.name}}</h3>
                    <el-form-item label="饰演角色" label-width="120px">
                      <el-input v-model="roleForm.roleName" placeholder="输入角色信息"></el-input>
                    </el-form-item>
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button @click="roleBindDialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="bindRole">确 定</el-button>
                  </div>
                </el-dialog>
              </div>
            </el-collapse-item>
            <el-collapse-item name="4">
              <template slot="title">
                <h2>媒体</h2>
              </template>
                <el-row style="margin: 30px" :gutter="30">
                  <el-col :span="8">
                    <el-upload
                      ref="img"
                      name="img"
                      class="avatar-uploader"
                      :auto-upload="false"
                      action="#"
                      :show-file-list="false"
                      :on-change="imgUpload"
                      :on-remove="imgRemove">
                      <img v-if="cloneMovie.imgUrl" :src="cloneMovie.imgUrl" class="avatar" alt="演员图片">
                      <i v-else class="el-icon-plus avatar-uploader-icon">电影图片</i>
                      <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过2MB</div>
                    </el-upload>
                  </el-col>
                  <el-col :span="8">
                    <el-upload
                      ref="img"
                      name="img"
                      class="avatar-uploader"
                      :auto-upload="false"
                      action="#"
                      :show-file-list="false"
                      :on-change="previewImgUpload"
                      :on-remove="previewImgRemove">
                      <el-image fit="contain" v-if="cloneMovie.previewImgUrl" :src="cloneMovie.previewImgUrl" class="avatar" alt="演员图片"></el-image>
                      <i v-else class="el-icon-plus avatar-uploader-icon">预告封面</i>
                      <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过2MB</div>
                    </el-upload>
                  </el-col>
                  <el-col :span="8">
                    <el-upload
                      class="upload-demo"
                      drag
                      name="video"
                      ref="video"
                      :auto-upload="false"
                      accept=".mp4,.flv"
                      :on-change="videoUpload"
                      :on-remove="videoRemove"
                      action="#"
                      multiple>
                      <i class="el-icon-upload"></i>
                      <div class="el-upload__text">
                        将视频文件拖到此处，或
                        <em>点击上传</em>
                      </div>
                      <div class="el-upload__tip" slot="tip">只能上传mp4/flv文件，且不超过1GB</div>
                    </el-upload>
                  </el-col>
                </el-row>
              <el-row style="text-align: center">
                <el-col>
                  <span v-if="loading" style="font-size: 20px"><i class="el-icon-loading"></i>正在上传</span>
                  <el-button type="warning" @click="modifyMedia">修改</el-button>
                </el-col>
              </el-row>
            </el-collapse-item>
          </el-collapse>
        </el-col>
      </el-col>

    </el-row>
  </div>

</template>

<script>
export default {
  name: "movieDetail",
  props: {
    movie: {}
  },
  mounted() {
    this.init()
  },
  data() {
    //验证电影时长
    let validateDuration = (rule, value, callback) => {
      if (value <= 0) {
        callback("请填写合理的电影时长")
      }
      callback()
    }
    //验证电影上映与下架
    let validateDate = (rule, value, callback) => {
      if (this.date === '') {
        callback("请选择上映与下架时间")
      }
      callback()
    }
    return {
      videoDialogVisible: false,
      //评论分页组件
      pagination: {
        currentPage: 1, //当前页
        pageSize: 5,   //每页条数
        total: 0,       //总条数
      },
      //演员分页组件
      perPagination: {
        currentPage: 1, //当前页
        pageSize: 8,   //每页条数
        total: 0,       //总条数
      },
      activeName: '1',
      cloneMovie: {},
      date: [this.movie.releaseTime, this.movie.endTime],
      //表单输入规则
      rules: {
        movieName: [
          { required: true, message: '请输入电影中文名', trigger: 'blur' },
        ],
        englishName: [
          { required: true, message: '请输入电影英文名', trigger: 'blur' },
        ],
        country: [
          { required: true, message: '请输入电影国家地区', trigger: 'blur' },
        ],
        describe: [
          { required: true, message: '请输入电影简介', trigger: 'blur' },
        ],
        duration: [
          { required: true, message: '请填写电影时长（单位/分钟）', trigger: 'blur'},
          { type: 'number', message: '时长必须为数字值', trigger: 'blur'},
          {validator: validateDuration, trigger: 'blur'}
        ],
        date: [
          {validator: validateDate, trigger: 'blur'}
        ],

      },
      allTypes: [],
      //不可选的演员id数组
      disabledList: [],
      //添加角色表单
      roleList: [],
      roleForm: {
        roleName: '',
        performer: {
          name: ''
        }
      },
      roleBindDialogVisible: false,
      performerList: [],
      //查询演员的关键字
      keyword: '',
      img: '',
      previewImg: '',
      preview: '',
      loading: false
    }
  },
  methods: {
    //获取电影的类型
    getTypes(movie) {
      this.$axios({
        url: '/movie/movieType',
        params: {movieId: movie.movieId}
      }).then(res => {
          res.data.data.forEach(value => {
          value.type = 'success'
        })
        movie.types = res.data.data
        this.cloneMovie.types = JSON.parse(JSON.stringify(movie.types))

      })
    },
    //获取演员列表
    getRoles(movie) {
      this.$axios({
        url: '/role/movieRoles',
        params: {movieId: movie.movieId}
      }).then(res => {
        movie.roles = res.data.data
        this.cloneMovie.roles = JSON.parse(JSON.stringify(movie.roles))
        //角色所对应的演员不可选
        this.getPerformerList()
        this.cloneMovie.roles.forEach(value => {
          for (let i in this.performerList) {
            if (this.performerList[i].imgUrl === value.imgUrl) {
              value.performerId = this.performerList[i].performerId
              this.disabledList.push(this.performerList[i].performerId)
              break
            }
          }
        })
      })
    },
    //获取评论列表
    getComments(movie) {
      this.$axios({
        url: '/evaluate/movieEvaluate/'+this.pagination.currentPage+"/"+this.pagination.pageSize+"/"+movie.movieId,
      }).then(res => {
        movie.comments = res.data.data.records;
        this.pagination.total = res.data.data.total
      })
    },
    //切换页码
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage;
      this.getRoles(this.movie);
    },
    //每次加载的评论数
    playVideo() {
      this.videoDialogVisible = true;
      let video = document.getElementById("video");
      if (video)
        video.play()
    },
    closeVideoDialog() {
      let video = document.getElementById("video");
      if (video)
        video.pause()
      this.videoDialogVisible = false;
    },
    getAllTypes() {
      this.$axios.get("/type").then((res) => {
        let allTypes = res.data.data;
        for (let type in allTypes) {
          this.allTypes.push({typeName:allTypes[type].typeName, type:'', typeId: allTypes[type].typeId, movieId: this.cloneMovie.movieId})
        }
        for (let i = 0; i < this.allTypes.length; i++) {
          for (let j = 0; j < this.cloneMovie.types.length; j++) {
            if (this.allTypes[i].typeId === this.cloneMovie.types[j].typeId) {
              this.allTypes.splice(i, 1);
            }
          }
        }
      })
    },
    //修改电影的基本信息
    modifyMovieMessage() {
      this.cloneMovie.releaseTime = this.date[0];
      this.cloneMovie.endTime = this.date[1];
      this.axios.put("/movie/modifyMovie", this.cloneMovie).then((res) => {
        if (res.data.flag) {
          //重新赋值
          let movie = res.data.data;
          this.movie.movieName = movie.movieName
          this.movie.englishName = movie.englishName
          this.movie.duration = movie.duration
          this.movie.describe = movie.describe
          this.movie.country = movie.country
          this.movie.releaseTime = movie.releaseTime
          this.movie.endTime = movie.endTime
          this.cloneMovie = JSON.parse(JSON.stringify(this.movie))
          this.$message.success(res.data.msg)
        } else {
          this.$message.error(res.data.msg)
        }
      })
    },
    removeType(type) {
      this.cloneMovie.types.splice(this.cloneMovie.types.indexOf(type), 1);
      this.allTypes.push(type);
    },
    addType(type) {
      this.allTypes.splice(this.allTypes.indexOf(type), 1);
      this.cloneMovie.types.push(type);
    },
    //修改电影类型
    modifyMovieType() {
      let movieId = this.cloneMovie.movieId;
      let movieTypeList = [];
      for (let type in this.cloneMovie.types) {
        movieTypeList.push({typeId: this.cloneMovie.types[type].typeId, movieId: movieId});
      }
      this.axios.put("/movietype/modifyMovieTypes/"+movieId, movieTypeList).then((res) => {
        if (res.data.flag) {
          this.cloneMovie.types.forEach((value) => {
            value.type = 'success'
          })
          this.allTypes.forEach(value => {
            value.type = ''
          })
          this.movie.types = this.cloneMovie.types;

          this.cloneMovie = JSON.parse(JSON.stringify(this.movie));

          this.$message.success(res.data.msg)
        } else {
          this.$message.error(res.data.msg)
        }
      })
    },
    //删除演员
    deleteRole(index, row) {
      this.$confirm("确定要删除吗？").then(() =>{
        this.axios.delete("/role/deleteRole/"+row.performerId+"/"+row.movieId).then((res) => {
          if (res.data.flag) {
            this.getRoles(this.movie)
            this.$message.success(res.data.msg)
          } else {
            this.$message.error(res.data.msg)
          }
        })
      })

    },
    //绑定新演员
    bindRole() {
      let role = [{movieId: this.movie.movieId, performerId: this.roleForm.performer.performerId, roleName: this.roleForm.roleName}]
      this.axios.post("/role/saveRoles", role).then((res) => {
        if (res.data.flag) {
          this.getRoles(this.movie)
          this.roleBindDialogVisible = false
          this.roleForm = []
          this.$message.success("添加成功！")
        } else {
          this.$message.error("添加失败！")
        }
      })
    },
    disabled(performer) {
      for (let id in this.movie.roles) {
        if (this.movie.roles[id].imgUrl === performer.imgUrl) {
          return true;
        }
      }
      return false;
    },
    //获取所有演员信息
    getPerformerList() {
      //分页查询参数
      let params = '?keyword='+this.keyword
      let url = "/performer/listPerformers/" + this.perPagination.currentPage + "/" + this.perPagination.pageSize
      this.$axios.get(url + params).then((res) => {
        if (res.data.flag) {
          this.performerList = res.data.data.records;
          this.perPagination.total = res.data.data.total;

        }
      })
    },
    //切换页码
    handlePerformerPage(currentPage) {
      this.perPagination.currentPage = currentPage;
      this.getPerformerList();
    },
    openBindDialog(performer) {
      this.roleForm.performer = performer;
      this.roleBindDialogVisible = true
    },
    //搜索演员
    searchPerformer() {
      this.perPagination.currentPage = 1;
      this.getPerformerList()
    },
    imgUpload(file, fileList) {
      if (this.beforeAvatarUpload(file)) {
        this.cloneMovie.imgUrl = URL.createObjectURL(file.raw);
        this.img = file.raw;
      }
    },
    previewImgUpload(file) {
      if (this.beforeAvatarUpload(file)) {
        this.cloneMovie.previewImgUrl = URL.createObjectURL(file.raw);
        this.previewImg = file.raw;
        return true
      }
      return false
    },
    videoUpload(file) {
      let types = ['mp4', 'flv']
      let name = file.name
      let temp = name.lastIndexOf('.') + 1
      const index = types.indexOf(name.slice(temp))
      const isLt1G = file.raw.size / 1024 / 1024 < 1024
      if (index === -1) {
        this.$message.error('格式只能是mp4/flv格式!')
        this.$refs.video.uploadFiles.length = 0
        return false
      }
      if (!isLt1G) {
        this.$message.error('视频大小不能超过1GB!')
        this.$refs.video.uploadFiles.length = 0
        return false
      }
      this.preview = file.raw
      this.cloneMovie.previewUrl = URL.createObjectURL(file.raw);
      return true
    },
    //检查图片格式
    beforeAvatarUpload(file) {
      const types = ['jpg', 'png', 'jpeg']
      const filename = file.name;
      const isJPGOrPNG = types.indexOf(filename.slice(filename.lastIndexOf('.') + 1)) !== -1;
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPGOrPNG) {
        this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPGOrPNG && isLt2M;
    },
    //移除图片
    imgRemove() {
      this.cloneMovie.imgUrl = '';
      this.img = '';
    },
    previewImgRemove() {
      this.cloneMovie.previewImgUrl = ''
      this.previewImg = ''
    },
    videoRemove() {
      this.cloneMovie.previewUrl = ''
      this.preview = ''
    },
    modifyMedia() {
      //4.保存媒体文件
      let params = new FormData();
      params.append("img", this.img);
      params.append("previewImg", this.previewImg);
      params.append("preview", this.preview);
      params.append("movieId", this.cloneMovie.movieId);
      this.loading = true
      this.axios.post("/movie/saveMedia", params).then((res) => {
        if (res.data.flag) {
          this.movie.imgUrl = res.data.data.imgUrl
          this.movie.previewImgUrl = res.data.data.previewImgUrl
          this.movie.previewUrl = res.data.data.previewUrl
          this.loading = false
          this.$message.success(res.data.msg);
        } else {
          this.$message.error(res.data.msg);
        }
      })
    },
    init() {
      this.getRoles(this.movie)
      this.getAllTypes()
      this.getTypes(this.movie)
      this.getComments(this.movie)
      this.cloneMovie = JSON.parse(JSON.stringify(this.movie))
    }
  }
}
</script>

<style>
.el-scrollbar__wrap {
  overflow-x: hidden;
}

</style>
