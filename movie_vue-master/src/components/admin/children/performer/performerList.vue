<template>
  <div>
    <div style="width: 500px;margin-left: 728px">
      <el-input type="text" v-model="performerKeyword"
                placeholder="请输入想要搜索的演员关键字" class="search">
        <template slot="append">
          <el-button icon="el-icon-search" @click="searchPerformer"/>
        </template>
      </el-input>
    </div>
    <div>
      <el-row
        :gutter="80">
        <el-col :span="6" v-for="(performer, index) in this.performerList" :key="index">
          <div>
            <el-popover
              placement="right"
              title="简介"
              width="200"
              trigger="hover">
              <el-scrollbar :native="false" style="height: 150px;">
                <p>{{ performer.description }}</p>
              </el-scrollbar>
              <el-card slot="reference" :body-style="{ padding: '10px' }" shadow="hover" style="margin: 20px">
                <el-image
                  v-loading="performer.loading"
                  element-loading-text="拼命加载中"
                  element-loading-spinner="el-icon-loading"
                  element-loading-background="rgba(0, 0, 0, 0.8)"
                  style="height: 250px"
                  :src="performer.imgUrl">
                  <div slot="error" class="image-slot">
                    <i class="el-icon-picture-outline"></i>
                  </div>
                </el-image>
                <div>
                  <div style="font-weight: bold" class="perMsg">
                    {{ performer.name }}
                  </div>
                  <div class="perMsg">
                    <el-tag>{{ performer.englishName }}</el-tag>
                  </div>

                  <div class="perMsg">生于:{{ $format(performer.birthday, "YYYY年MM月DD日") }}</div>
                  <div class="bottom clearfix">
                    <el-button type="warning" class="button" size="small" round
                               @click="editPerformer(performer, index)">编辑信息
                    </el-button>
                    <el-button type="warning" class="button" size="small" round @click="openBindDialog(performer)">
                      绑定电影
                    </el-button>
                  </div>
                </div>
              </el-card>
            </el-popover>

          </div>
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

    <!--编辑窗口-->
    <el-dialog title="编辑演员信息" :visible.sync="editDialogVisible" :modal-append-to-body="false">
      <el-row>
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
            <img v-if="editForm.imgUrl" :src="editForm.imgUrl" class="avatar" alt="演员图片">
            <i v-else class="el-icon-plus avatar-uploader-icon">演员图片</i>
            <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过2MB</div>
          </el-upload>
        </el-col>
        <el-col :span="15" :offset="1">
          <el-form :model="editForm" :rules="rules" ref="editForm">
            <el-form-item prop="name" label="中文名" :label-width="formLabelWidth">
              <el-input v-model="editForm.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item prop="englishName" label="英文名" :label-width="formLabelWidth">
              <el-input v-model="editForm.englishName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item prop="birthday" label="出生日期" :label-width="formLabelWidth">
              <el-date-picker type="date" placeholder="选择日期" v-model="editForm.birthday"
                              style="float: left"></el-date-picker>
            </el-form-item>
            <el-form-item prop="description" label="描述" :label-width="formLabelWidth">
              <el-input type="textarea" :autosize="{minRows:5, maxRows:5}" v-model="editForm.description"
                        autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>

      <div slot="footer" class="dialog-footer">
        <el-button @click="editDialogVisible = false">取 消</el-button>
        <el-popconfirm title="确定修改吗？" @confirm="submitEditForm(editForm.index)">
          <el-button type="primary" slot="reference">确 定</el-button>
        </el-popconfirm>
      </div>
    </el-dialog>

    <!--绑定电影-->
    <el-dialog title="绑定电影" :visible.sync="bindDialogVisible" :modal-append-to-body="false" width="70%"
               @close="closeBindDialog">
      <!--绑定表-->
      <div style="width: 500px;margin-left: 523px">
        <el-input type="text" v-model="movieKeyword"
                  placeholder="请输入想要搜索的电影关键字" class="search">
          <template slot="append">
            <el-button icon="el-icon-search" @click="searchMovie"/>
          </template>
        </el-input>
      </div>
      <el-row>
        <el-col :span="24">
          <el-table
            :data="movieBindList"
            style="width: 100%; align-content: center"
            border
            height="200">
            <el-table-column
              label="序号"
              align="center"
              type="index">
            </el-table-column>
            <el-table-column
              label="电影名"
              align="center"
              prop="movieName">
            </el-table-column>
            <el-table-column
              label="演员名"
              align="center"
              prop="name">
            </el-table-column>

            <el-table-column
              label="角色名"
              align="center"
              prop="roleName">
            </el-table-column>
            <el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="danger"
                  @click="deleteMovie(scope.$index, scope.row)">删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
      <el-dialog title="绑定角色信息" :visible.sync="addRoleDialogVisible" :append-to-body="true" @close="closeRoleDialog">
        <el-form
          v-model="roleForm">
          <h3 align="center">电影名:{{ roleForm.movieName }}</h3>
          <h3 align="center">演员名:{{ roleForm.name }}</h3>
          <el-form-item label="请输入角色名:" label-width="100px">
            <el-input v-model="roleForm.roleName" placeholder="请输入角色名:"></el-input>
          </el-form-item>
          <el-form-item align="right">
            <el-button @click="cancelBind">取消</el-button>
            <el-button type="primary" @click="submitRoleDialog">确定</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <!--电影信息-->
      <el-row>
        <el-col :span="6" v-for="(movie, index) in this.movieList" :key="index">
          <div>
            <el-popover
              placement="right"
              title="简介"
              width="200"
              trigger="hover">
              <el-scrollbar :native="false" style="height: 150px;">
                <p>{{ movie.describe }}</p>
              </el-scrollbar>
              <el-card slot="reference" :body-style="{ padding: '10px' }" shadow="hover" style="margin: 20px">
                <el-image
                  style="height: 200px;"
                  :src="movie.imgUrl">
                  <div slot="error" class="image-slot">
                    <i class="el-icon-picture-outline"></i>
                  </div>
                </el-image>
                <div>
                  <div style="font-weight: bold" class="perMsg">
                    {{ movie.movieName }}
                  </div>
                  <div class="perMsg">
                    <el-tag>{{ movie.englishName }}</el-tag>
                  </div>
                  <div class="bottom clearfix">
                    <el-button type="primary" :disabled="disabled(movie)" class="button" @click="openRoleDialog(movie)"
                               size="small"
                               round>绑定电影
                    </el-button>
                  </div>
                </div>
              </el-card>
            </el-popover>
          </div>
        </el-col>
      </el-row>
      <!--分页组件-->
      <el-pagination
        style="margin-top: 20px"
        background
        layout="prev, pager, next"
        @current-change="handleMovieCurrentChange"
        :current-page="moviePagination.currentPage"
        :page-size="moviePagination.pageSize"
        :total="moviePagination.total">
      </el-pagination>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "performerList",
  data() {
    return {
      //演员搜索的关键字
      performerKeyword: '',
      //电影搜索的关键字
      movieKeyword: '',

      //演员列表
      performerList: [],
      //电影绑定列表
      movieBindList: [{
        movieId: '',
        performerId: '',
        movieName: '',
        name: '',
        roleName: ''
      }],
      // 电影列表
      movieList: [],
      //编辑表单
      editForm: {},

      //绑定表单
      roleForm: {
        //电影名
        movieId: '',
        performerId: '',
        movieName: '',
        //角色名
        roleName: '',
        //演员名
        name: '',
      },

      //电影分页组件
      moviePagination: {
        currentPage: 1, //当前页
        pageSize: 8,   //每页条数
        total: 0,       //总条数
      },
      //分页组件
      pagination: {
        currentPage: 1, //当前页
        pageSize: 16,   //每页条数
        total: 0,       //总条数
      },
      globalIndex: 0,
      editDialogVisible: false,  //编辑窗口
      addRoleDialogVisible: false,//添加角色窗口
      bindDialogVisible: false, //绑定窗口
      formLabelWidth: '120px',
      rules: {
        name: [
          {required: true, message: '演员中文名不能为空', trigger: 'blur'},
        ],
        englishName: [
          {required: true, message: '演员英文名不能为空', trigger: 'blur'},
        ],
        birthday: [
          {required: true, message: '出生日期不能为空', trigger: 'blur'},
        ],
        description: [
          {required: true, message: '演员简介不能为空', trigger: 'blur'},
        ],
      },

    }
  },
  mounted() {
    this.getPerformerList();
    this.getMovieList();
  },
  methods: {
    //对演员进行关键字搜索
    searchPerformer() {
      this.getPerformerList();
    },
    //对电影进行关键字搜索
    searchMovie() {
      this.getMovieList();
    },
    //获得此角色所参演的电影
    getBindList(performerId) {
      this.$axios({
        url: '/role/getPerformerRoles',
        params: {performerId: performerId}
      }).then(res => {
        this.movieBindList = res.data.data
      })
    },

    //切换页码
    handleCurrentChange(currentPage) {
      this.pagination.currentPage = currentPage;
      this.getPerformerList();
    },

    handleMovieCurrentChange(currentPage) {
      this.moviePagination.currentPage = currentPage;
      this.getMovieList();
    },
    //获取所有演员信息
    getPerformerList() {
      //分页查询参数
      let url = "/performer/listPerformers/" + this.pagination.currentPage + "/" + this.pagination.pageSize
      this.$axios({
        url: url,
        params: {performerKeyword: this.performerKeyword}
      }).then((res) => {
        if (res.data.flag) {
          this.performerList = res.data.data.records;
          console.log(this.performerList);
          this.pagination.total = res.data.data.total;
        }
      })
    },

    //获取所有电影信息
    getMovieList() {
      this.$axios({
        url: '/movie/getAllMovies',
        params:
          {currentPage: this.moviePagination.currentPage,
          pageSize: this.moviePagination.pageSize,
          movieKeyword: this.movieKeyword}
      }).then(res => {
        this.movieList = res.data.data.records
        this.moviePagination.total = res.data.data.total;
      })
    },

    deleteMovie(index, row) {
      this.$axios({
        url: '/role/deleteRoles',
        method: 'delete',
        params: {movieId: row.movieId, performerId: row.performerId}
      }).then(res => {
        if (res.data.flag === true) {
          const h = this.$createElement;
          this.$notify({
            title: '成功提示',
            message: h('i', {style: 'color: teal'}, '删除角色信息成功!'),
            type: 'success'
          });
          this.getBindList(row.performerId)
        } else {
          const h = this.$createElement;
          this.$notify({
            title: '错误提示',
            message: h('i', {style: 'color: teal'}, '角色删除失败!'),
            type: 'error'
          });
        }
      })
    },

    disabled(movie) {
      for (let i = 0; i < this.movieBindList.length; i++) {
        if (movie.movieId === this.movieBindList[i].movieId) {
          return true;
        }
      }
      return false;
    },

    //编辑修改演员信息
    editPerformer(performer, index) {

      //深拷贝要修改的演员信息
      this.editForm = JSON.parse(JSON.stringify(performer));
      this.editForm.birthday = new Date(this.editForm.birthday);
      //打开窗口
      this.editDialogVisible = true;
      this.currentPer = performer;

    },
    //上传图片
    imgUpload(file) {
      if (this.beforeAvatarUpload(file)) {
        this.editForm.imgUrl = URL.createObjectURL(file.raw);
        this.editForm.img = file.raw;
      }
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
      this.editForm.imgUrl = '';
      this.editForm.img = '';
    },
    //提交修改信息
    submitEditForm(index) {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          let param = new FormData();
          param.append("performerId", this.editForm.performerId);
          param.append("name", this.editForm.name);
          param.append("englishName", this.editForm.englishName);
          param.append("birthday", this.editForm.birthday);
          param.append("description", this.editForm.description);
          param.append("img", this.editForm.img);
          this.$axios.put("/performer/editPerformer", param).then((res) => {
            if (res.data.flag) {
              //修改成功
              this.$message.success(res.data.msg);
              //关闭并重置表单
              this.editDialogVisible = false;
              this.resetEditForm();
              //刷新
              this.getPerformerList();
            } else {
              //修改失败
              this.$message.error(res.data.msg);
            }
          }).finally(() => {
            this.$forceUpdate();
          })
        } else {
          this.$message.warning("信息格式有误！")
        }
      })
    },
    resetEditForm() {
      this.$refs['editForm'].resetFields();
    },
    openBindDialog(performer) {
      this.roleForm.name = performer.name;
      this.roleForm.performerId = performer.performerId;
      this.getBindList(performer.performerId);
      this.bindDialogVisible = true;
    },
    closeBindDialog() {
      this.roleForm.name = '';
    },
    openRoleDialog(movie) {
      this.roleForm.movieName = movie.movieName;
      console.log(this.roleForm);
      this.roleForm.movieId = movie.movieId
      this.addRoleDialogVisible = true;
    },
    closeRoleDialog() {
      this.roleForm.movieName = '';
    },
    cancelBind() {
      this.addRoleDialogVisible = false;
      this.roleForm.movieName = '';
    },
    submitRoleDialog() {
      if (this.roleForm.roleName === '') {
        const h = this.$createElement;
        this.$notify({
          title: '错误提示',
          message: h('i', {style: 'color: teal'}, '请输入角色名 !  '),
          type: 'error'
        });
      } else {
        let obj = {};
        obj = JSON.parse(JSON.stringify(this.roleForm));
        this.$axios({
          url: '/role/saveRoles',
          method: 'post',
          data: new Array(obj),
        }).then(res => {
          if (res.data.flag === true) {
            this.movieBindList.push(obj);
            const h = this.$createElement;
            this.$notify({
              title: '添加成功',
              message: h('i', {style: 'color: teal'}, '绑定成功 !  '),
              type: 'success'
            });
            this.addRoleDialogVisible = false;
          } else {
            const h = this.$createElement;
            this.$notify({
              title: '添加失败!',
              message: h('i', {style: 'color: teal'}, res.data.msg),
              type: 'error'
            });
          }
        })
      }
    }
  }
}
</script>

<style>
.el-scrollbar__wrap {
  overflow-x: hidden;
}

.perMsg {
  margin: 5px;
}

.search .el-input-group__append {
  border: none;
  background: #f30b0b;
}

.search .el-input-group__append .el-icon-search {
  font-size: 20px;
  color: white;
}
</style>
