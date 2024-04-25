<template>
  <div>
    <el-steps :active="activeStep" finish-status="success" simple>
      <el-step title="信息完善" ></el-step>
      <el-step title="类型绑定" ></el-step>
      <el-step title="演员绑定" ></el-step>
      <el-step title="媒体文件"></el-step>
    </el-steps>
    <el-row v-if="activeStep===0" :gutter="30" style="margin-top: 20px">
      <el-col :span="24">
        <div style="">
          <el-form style="width: 70%;margin: auto" label-position="right" :rules="rules" label-width="150px" :model="movieForm" ref="movieForm">
            <el-form-item prop="movieName" label="中文名">
              <el-input v-model="movieForm.movieName"></el-input>
            </el-form-item>
            <el-form-item prop="englishName" label="英文名">
              <el-input v-model="movieForm.englishName"></el-input>
            </el-form-item>
            <el-form-item prop="country" label="国家和地区">
              <el-input v-model="movieForm.country"></el-input>
            </el-form-item>
            <el-form-item prop="describe" label="电影简介">
              <el-input type="textarea" :autosize="{minRows:5, maxRows:5}" v-model="movieForm.describe"></el-input>
            </el-form-item>
            <el-form-item prop="duration" label="电影时长(分钟)">
              <el-input v-model.number="movieForm.duration"></el-input>
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

          </el-form>
        </div>
      </el-col>
        <div style="margin: auto; margin-top: 30px;">
          <el-button @click="reset">重置</el-button>
          <el-button type="primary" @click="nextStep">下一步</el-button>
        </div>
    </el-row>


<!--    类型绑定-->
    <el-row v-if="activeStep===1">
      <el-row style="width: 100%">
        <el-col :span="11">
          <div><h3 style="margin-top: 5px">电影类别</h3></div>
          <div  style="height: 250px; box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); border-radius: 5px; text-align: left" >
            <el-tag
              style="margin: 5px"
              :key="type.typeId"
              v-for="type in movieForm.types"
              closable
              effect="dark"
              :type="type.type"
              :disable-transitions="false"
              @close="removeType(type)">
              {{type.typeName}}
            </el-tag>
          </div>
        </el-col>
        <el-col :span="2" style="font-size: 40px; margin-top: 150px;">
          <i class="el-icon-back"></i>
        </el-col>
        <el-col :span="11">
          <div><h3 style="margin-top: 5px">所有类别</h3></div>
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
      <div style="margin: auto; margin-top: 30px">
        <el-button  @click="beforeStep">上一步</el-button>
        <el-button @click="nextStep" type="primary">下一步</el-button>
      </div>
    </el-row>

<!--演员绑定-->
    <div v-if="activeStep===2">
      <el-row>
        <el-col :span="24">
          <el-table
            :data="roleList"
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
                  <p>姓名: {{ scope.row.performer.name }}</p>
                  <p>英文名: {{ scope.row.performer.englishName }}</p>
                  <el-image style="width: 150px" :src="scope.row.performer.imgUrl"></el-image>
                  <div slot="reference" class="name-wrapper">
                    <el-tag size="medium">{{ scope.row.performer.name }}</el-tag>
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
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6" v-for="(performer, index) in this.performerList" :key="index"><div>
          <el-popover
            placement="right"
            title="简介"
            width="200"
            trigger="hover">
            <el-scrollbar :native="false"  style="height: 150px;">
              <p>{{performer.description}}</p>
            </el-scrollbar>
            <el-card slot="reference" :body-style="{ padding: '10px' }" shadow="hover" style="margin: 20px">
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
        style="margin-top: 20px"
        background
        layout="prev, pager, next"
        @current-change="handleCurrentChange"
        :current-page="pagination.currentPage"
        :page-size="pagination.pageSize"
        :total="pagination.total">
      </el-pagination>
      <div style="margin: auto; margin-top: 30px">
        <el-button @click="beforeStep">上一步</el-button>
        <el-button @click="nextStep" type="primary">下一步</el-button>
      </div>

      <!--绑定角色信弹窗-->
      <el-dialog title="角色绑定" :visible.sync="roleBindDialogVisible" :modal-append-to-body="false">
        <el-form :model="roleForm">
          <h3>{{roleForm.performer.name}}</h3>
          <el-form-item label="饰演角色" :label-width="formLabelWidth">
            <el-input v-model="roleForm.roleName" placeholder="输入角色信息"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="roleBindDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="bindRole">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <!--媒体文件设置-->
    <div v-if="activeStep===3" style="margin-top: 50px">
      <el-row :gutter="30">
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
            <img v-if="movieForm.imgUrl" :src="movieForm.imgUrl" class="avatar" alt="演员图片">
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
            <el-image fit="contain" v-if="movieForm.previewImgUrl" :src="movieForm.previewImgUrl" class="avatar" alt="演员图片"></el-image>
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

          <div style="margin: auto; margin-top: 30px">
            <el-button  @click="beforeStep">上一步</el-button>
            <el-button @click="nextStep" type="primary">下一步</el-button>
          </div>
        </el-col>
      </el-row>
    </div>

<!--  信息预览  -->
    <div v-if="activeStep===4" style="margin-top: 20px">
      <el-row :gutter="30">

        <el-col :span="12">
          <h3 style="text-align: left; margin-left: 20px">电影信息</h3>
          <el-row>
            <el-col :span="15">
              <el-image :src="movieForm.imgUrl" style="width: 350px; height: 500px"></el-image>
            </el-col>
            <el-col :span="9">
              <div style="text-align: left">
                <p>电影名：{{movieForm.movieName}}</p>
                <p>EnglishName：{{movieForm.englishName}}</p>
                <p>国家与地区：{{movieForm.country}}</p>
                <el-row>
                  <span>类型：</span>
                  <el-breadcrumb separator="/" style="margin: auto auto auto 0;">
                    <el-breadcrumb-item v-for="type in movieForm.types" :key="type.typeId">{{type.typeName}}</el-breadcrumb-item>
                  </el-breadcrumb>
                </el-row>
                <p>时长：{{movieForm.duration}}</p>
                <p>上架日期：{{$format(movieForm.releaseTime,"YYYY年MM月DD日")}}</p>
                <p>下架日期：{{$format(movieForm.endTime,"YYYY年MM月DD日")}}</p>
                <p>简介：</p>
                <el-scrollbar style="height: 200px">
                  <p>{{movieForm.describe}}</p>
                </el-scrollbar>
              </div>
            </el-col>
          </el-row>
        </el-col>
        <el-col :span="12">
          <el-row style="width: 100%">
            <h3>角色列表</h3>
          <el-scrollbar style="height: 300px; width: 100%">
            <el-row style="width: 100%;">
              <el-col :span="6" v-for="role in roleList" :key="role.performer.performerId">
                <el-card  :body-style="{ padding: '5px' }" style="margin: 5px;">
                  <el-image :src="role.performer.imgUrl" class="image" style="height: 150px"></el-image>
                  <div style="padding: 0px;">
                    <div>{{role.performer.name}}</div>
                    <div>饰：{{role.roleName}}</div>
                  </div>
                </el-card>
              </el-col>
            </el-row>


          </el-scrollbar>
          </el-row>
          <el-row>
            <h3>预告片预览</h3>
          </el-row>
          <el-row>
            <div style="height: 80px; width: 400px; margin-top: 5px;border: 1px solid #f3f0f0"
                 class="hoverToBlack mouseToFinger" @click="playVideo">
              <el-row>
                <el-col :span="8">
                  <el-image style="height: 100%" :src="movieForm.previewImgUrl"/>
                </el-col>
                <el-col :span="15" :offset="1">
                    <span style="color: #3d3c3c;font-size: 15px;line-height: 80px">
                      《{{movieForm.movieName}}预告片》
                    </span>
                </el-col>
              </el-row>
            </div>
          </el-row>
          <!-- 预告预览弹窗 -->
          <el-dialog :title="movieForm.movieName" :visible="videoDialogVisible" @close="closeVideoDialog" width="500" :modal-append-to-body="false">
            <video id="video" style="width: 100%" v-if="movieForm.previewUrl" controls="controls">
              <source :src="movieForm.previewUrl">
            </video>
          </el-dialog>
        </el-col>
      </el-row>
      <el-row>
        <div style="margin: auto; margin-top: 30px">
          <el-progress style="width: 700px; margin-bottom: 20px" v-if="isSaving" :text-inside="true" :stroke-width="26" :percentage="savePercent" :color="colors"></el-progress>
          <el-button  @click="beforeStep">上一步</el-button>
          <el-button @click="submitMovie" type="primary">保存</el-button>
        </div>
      </el-row>
    </div>

  </div>
</template>



<script>
export default {
  name: "addMovie",
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
      movieForm: {
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
        /**电影预告片图片*/
        previewImgUrl: '',
        // 电影预告片路径
        previewUrl: '',
        //电影类别
        types:[]
      },
      date: '',
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
        ]

      },
      allTypes: [],
      activeStep: 0,
      performerList: [],
      roleList: [],
      roleForm: {
        roleName: '',
        performer: {
          name: ''
        }
      },
      roleBindDialogVisible: false,
      //演员分页组件
      pagination: {
        currentPage: 1, //当前页
        pageSize: 16,   //每页条数
        total: 0,       //总条数
      },
      formLabelWidth: "120px",
      videoDialogVisible: false,
      disabledList: [],
      isSaving: false,
      savePercent: 0,
      colors: [
        {color: '#b7a7a7', percentage: 20},
        {color: '#397fe8', percentage: 40},
        {color: '#e73162', percentage: 60},
        {color: '#d7cb48', percentage: 80},
        {color: '#5cb87a', percentage: 100}
      ]
    }
  },
  mounted() {
    this.getAllTypes();
    this.getPerformerList();
  },
  methods: {
    getAllTypes() {
      this.$axios.get("/type").then((res) => {
        let allTypes = res.data.data;
        for (let type in allTypes) {
          this.allTypes.push({typeName:allTypes[type].typeName, type:'success', typeId: allTypes[type].typeId, movieId: ''})
        }
      })
    },
    //获取所有演员信息
    getPerformerList() {
      //分页查询参数
      let url = "/performer/listPerformers/" + this.pagination.currentPage + "/" + this.pagination.pageSize
      this.$axios.get(url).then((res) => {
        if (res.data.flag) {
          this.performerList = res.data.data.records;
          this.pagination.total = res.data.data.total;
        }
      })
    },
    //切换页码
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage;
      this.getPerformerList();
    },
    openBindDialog(performer) {
      this.roleForm.performer = performer;
      this.roleBindDialogVisible = true
    },
    //绑定演员
    bindRole() {
      this.roleList.push(JSON.parse(JSON.stringify(this.roleForm)));
      this.roleBindDialogVisible = false;
      this.roleForm.performer.disabled = true;
      this.disabledList.push(this.roleForm.performer.performerId);
      this.roleForm.roleName = '';
      this.$message.success("添加成功！")
    },
    //删除绑定演员
    deleteRole(index, row) {
      this.roleList.splice(index, 1);
      this.disabledList.splice(index, 1)
    },
    disabled(performer) {
      for (let id in this.disabledList) {
        if (this.disabledList[id] === performer.performerId) {
          return true;
        }
      }
      return false;
    },
    //电影上架
    submitMovie() {
      this.$confirm("您确认要保存吗？")
      .then(()=>{
        this.isSaving = true;
        //1.保存电影信息
        this.axios.post("/movie/saveMovie", this.movieForm).then((res) => {
          if (res.data.flag) {
            this.savePercent = 25
            let movieId = res.data.msg;
            //2.保存电影类型
            let movieTypeList = [];
            for (let type in this.movieForm.types) {
              movieTypeList.push({typeId: this.movieForm.types[type].typeId, movieId: movieId});
            }
            this.axios.post("/movietype/saveMovieTypes", movieTypeList).then((res) => {
              if (res.data.flag) {
                this.savePercent = 50
                //3.保存角色信息
                let roleList = [];
                for (let role in this.roleList) {
                  roleList.push({movieId: movieId,
                    performerId:this.roleList[role].performer.performerId,
                    roleName: this.roleList[role].roleName})
                }
                this.axios.post("/role/saveRoles", roleList).then((res) => {
                  if (res.data.flag) {
                    this.savePercent = 75
                    //4.保存媒体文件
                    let params = new FormData();
                    params.append("img", this.img);
                    params.append("previewImg", this.previewImg);
                    params.append("preview", this.preview);
                    params.append("movieId", movieId);
                    this.axios.post("/movie/saveMedia", params).then((res) => {
                      if (res.data.flag) {
                        this.savePercent = 100
                        this.$message.success(res.data.msg);
                      } else {
                        this.$message.error(res.data.msg);
                      }
                    })
                  }
                })
              }
            })
          }
        })
      })
    },
    //下一步
    nextStep() {
      if (this.activeStep === 0) {
        this.$refs['movieForm'].validate((valid) => {
          if (valid) {
            this.movieForm.releaseTime = this.date[0]
            this.movieForm.endTime = this.date[1]
            this.activeStep ++;
          } else {
            this.$message.warning("请完善电影信息！")
          }
        })
      } else if (this.activeStep === 1) {
        if (this.movieForm.types.length !== 0) {
          this.activeStep ++
        } else {
          this.$message.warning("请添加电影类别（至少一种）！")
        }
      } else if (this.activeStep === 2) {
        if (this.roleList.length !== 0) {
          this.activeStep ++
        } else {
          this.$message.warning("请添加演员信息！")
        }
      } else if (this.activeStep === 3) {
        if (this.img !== null && this.previewImg!== null && this.preview!== null) {
          this.activeStep ++
        } else {
          this.$message.warning("请添加媒体文件")
        }
      }
    },
    //上一步
    beforeStep() {
      this.activeStep --;
    },
    reset() {
      this.$refs['movieForm'].resetFields()
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
    //上传图片
    imgUpload(file, fileList) {
      if (this.beforeAvatarUpload(file)) {
        this.movieForm.imgUrl = URL.createObjectURL(file.raw);
        this.img = file.raw;
      }
    },
    previewImgUpload(file) {
      if (this.beforeAvatarUpload(file)) {
        this.movieForm.previewImgUrl = URL.createObjectURL(file.raw);
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
      this.movieForm.previewUrl = URL.createObjectURL(file.raw);
      return true
    },
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
    //移除图片
    imgRemove() {
      this.movieForm.imgUrl = '';
      this.img = '';
    },
    previewImgRemove() {
      this.movieForm.previewImgUrl = ''
      this.previewImg = ''
    },
    videoRemove() {
      this.movieForm.previewUrl = ''
      this.preview = ''
    },

    removeType(type) {
      this.movieForm.types.splice(this.movieForm.types.indexOf(type), 1);
      this.allTypes.push(type);
    },
    addType(type) {
      this.allTypes.splice(this.allTypes.indexOf(type), 1);
      this.movieForm.types.push(type);
    }
  }
}
</script>

<style>
  .el-scrollbar__wrap    {
    overflow-x: hidden;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 180px;
    height: 250px;
    line-height: 250px;
    text-align: center;
  }
  .avatar {
    width: 180px;
    height: 250px;
    display: block;
  }
  .perMsg {
    margin: 5px;
  }
  .movieDetailsBackground {
    background: url("/static/渐变.jpg");
    background-size: 100% 100%;
  }

  .mouseToFinger:hover {
    cursor: pointer;
  }

  .hoverToBlack:hover{

    background: #e6e6e7;
  }
</style>
