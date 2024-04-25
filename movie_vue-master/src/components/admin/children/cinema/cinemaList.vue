<template>
  <div>
    <div>
      <el-row
        :gutter="0" class="row-con">
        <el-col :span="8" v-for="(cinema, index) in this.cinemaList" :key="index"><div>
            <el-card slot="reference" :body-style="{ padding: '10px' }" shadow="hover" style="margin: 20px; height: 200px" class="card">
              <div>
                <div style="font-weight: bold" class="perMsg">
                  <i class="el-icon-office-building"></i> {{cinema.cinemaName}}
                </div>
                <div class="perMsg">
                  <el-tag>{{cinema.brand}}</el-tag>
                </div>

                <div style="height: 50px" class="perMsg"><i class="el-icon-location"></i>地址：{{cinema.address}}</div>
                <div class="bottom clearfix">
                  <el-button type="warning" class="button" size="small" round @click="editCinema(cinema, index)">编辑信息</el-button>
                  <el-button type="primary" class="button" size="small" round @click="editHall(cinema)">影厅管理</el-button>
                </div>
              </div>
            </el-card>


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
      <!--影厅-->
      <el-dialog title="影厅" :visible.sync="dialogTableVisible" width="45%" :modal-append-to-body="false">
        <el-table :data="this.hallList">
          <el-table-column property="hallName" label="影厅名" width="150"></el-table-column>
          <el-table-column property="type" label="类型" width="150" align="center"></el-table-column>
          <el-table-column property="seats" label="座位数" width="150" align="center"></el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="primary" size="mini" width="100" round @click="getScenesList(scope.row.hallId)">场次管理</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-dialog>
      <!--场次-->
      <el-dialog title="场次" :visible.sync="dialogHallVisible" width="80%" :modal-append-to-body="false">
        <div class="filter-container">
          <el-button type="primary" class="butT"  @click="openScenesDialog">新增</el-button>
        </div>
        <el-table :data="this.scenesList">
          <el-table-column property="movieName" label="电影名称" width="150">
            <template slot-scope="scope">
              《{{getMovieName(scope.row.movieId)}}》
            </template>
          </el-table-column>
          <el-table-column property="dateStart" label="开始时间" width="250" align="center">
            <template slot-scope="scope">
              {{$format(scope.row.dateStart, "YYYY年MM月DD日-HH:mm:ss")}}
            </template>
          </el-table-column>
          <el-table-column property="dateEnd" label="结束时间" width="250" align="center">
            <template slot-scope="scope">
              {{$format(scope.row.dateEnd, "YYYY年MM月DD日-HH:mm:ss")}}
            </template>
          </el-table-column>
          <el-table-column property="price" label="票价" width="200" align="center"></el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button type="warning" size="mini" width="100" round @click="editScenes(scope.row)">修改</el-button>
              <el-button type="danger" size="mini" width="100" round @click="handleScenesDelete(scope.row)">删除</el-button>

            </template>
          </el-table-column>
        </el-table>
      </el-dialog>
      <!-- 场次编辑窗口-->
      <el-dialog title="编辑场次信息" :visible.sync="scenesEditDialogVisible" :modal-append-to-body="false">
        <el-row>
          <el-col :span="15" :offset="1">
            <el-form :model="editScenesForm" :rules="rules"  ref="editScenesForm">
              <el-form-item label="电影名" :label-width="formLabelWidth">
                <el-input v-model="editScenesForm.movieName" autocomplete="off"></el-input>
                <el-button type="primary" style="float: left" @click="openMovieDrawer">选择电影</el-button>
              </el-form-item>
              <el-form-item style="text-align: left" prop="dateStart" label="开始时间" :label-width="formLabelWidth">
                <el-date-picker
                  v-model="editScenesForm.dateStart"
                  type="datetime"
                  placeholder="选择日期时间"
                  :picker-options="pickerOptions">
                </el-date-picker>
<!--                <el-date-picker type="date" placeholder="选择时间" v-model="editScenesForm.dateStart" style="float: left"></el-date-picker>-->
              </el-form-item>
              <el-form-item style="text-align: left" prop="dateEnd" label="结束时间" :label-width="formLabelWidth">
                <el-date-picker
                  v-model="editScenesForm.dateEnd"
                  type="datetime"
                  placeholder="选择日期时间"
                  :picker-options="pickerOptions">
                </el-date-picker>
<!--                <el-date-picker type="date" placeholder="选择时间" v-model="editScenesForm.dateEnd" style="float: left"></el-date-picker>-->
              </el-form-item>
              <el-form-item prop="price" label="票价" :label-width="formLabelWidth">
                <el-input v-model="editScenesForm.price" autocomplete="off"></el-input>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>

        <div slot="footer" class="dialog-footer">
          <el-button @click="scenesEditDialogVisible = false">取 消</el-button>
          <el-popconfirm title="确定修改吗？" @confirm="submitEditForm2(editScenesForm)">
            <el-button type="primary" slot="reference"  >确 定</el-button>
          </el-popconfirm>
        </div>
      </el-dialog>

    <!--排片窗口-->
      <el-dialog width="600" :visible.sync="scenesDialogVisible" :modal-append-to-body="false">
        <el-form :model="scenesForm" :rules="rules"  ref="scenesForm">
          <el-form-item label="电影名称" :label-width="formLabelWidth">
            <el-input v-model="scenesForm.movieName" autocomplete="off"></el-input>
            <el-button type="primary" style="float: left" @click="openMovieDrawer">选择电影</el-button>
          </el-form-item>
          <el-form-item prop="date" label="日期" :label-width="formLabelWidth">
            <el-date-picker
              v-model="scenesForm.date"
              type="date"
              style="float: left"
              placeholder="选择日期"
              @change="selectDayScenes(scenesForm.date)"
              :picker-options="pickerOptions">
            </el-date-picker>
          </el-form-item>
          <el-form-item style="text-align: left" prop="dateStart" label="开始时间" :label-width="formLabelWidth">
            <el-time-picker
              v-model="scenesForm.dateStart"
              :picker-options="{selectableRange}"
              placeholder="开始时间">
            </el-time-picker>
          </el-form-item>
          <el-form-item style="text-align: left" prop="dateEnd" label="结束时间" :label-width="formLabelWidth">
            <el-time-picker
              v-model="scenesForm.dateEnd"
              :picker-options="{selectableRange}"
              placeholder="结束时间">
            </el-time-picker>
          </el-form-item>
          <el-form-item prop="price" label="票价" :label-width="formLabelWidth">
            <el-input v-model="scenesForm.price" autocomplete="off"></el-input>
          </el-form-item>
          <el-popconfirm title="确定提交吗？" @confirm="submitScenesForm()">
            <el-button type="primary" slot="reference">确 定</el-button>
          </el-popconfirm>
        </el-form>
      </el-dialog>
      <el-drawer
        title="我是标题"
        :visible.sync="movieDrawer"
        :modal-append-to-body="false"
        :with-header="false">
        <el-row style="margin-top: 30px">
          <el-col :span="12" :offset="3">
            <el-input
              placeholder="请输入内容"
              prefix-icon="el-icon-search"
              v-model="keyword">
            </el-input>
          </el-col>
          <el-col :span="9">
            <el-button type="primary" @click="searchMovie">
              搜索 <i class="el-icon-search"></i>
            </el-button>
          </el-col>


        </el-row>
        <el-row>
          <el-col style="margin-top: 50px" :offset="1"  :span="11" v-for="(movie, index) in movieList" :key="movie.movieId">
            <el-card   class="card" shadow="hover" style="border: 1px #99a9bf solid; border-radius: 10px " :body-style="{ padding: '0px' }">
                <div>
                  <el-image style="width: 100%" :src="movie.imgUrl"></el-image>
                </div>
                <div style="height: 30px">
                  <span style="font-weight: bold">{{movie.movieName}}</span>
                </div>
                <div style="margin-bottom: 10px">
                  <el-button @click="selectMovie(movie)">选择</el-button>
                </div>
            </el-card>
          </el-col>
        </el-row>
        <!--分页组件-->
        <el-pagination
          style="margin-top: 20px"
          background
          layout="prev, pager, next"
          @current-change="changeMoviePage"
          :current-page="moviePagination.currentPage"
          :page-size="moviePagination.pageSize"
          :total="moviePagination.total">
        </el-pagination>
      </el-drawer>
    </div>
    <!--影院编辑窗口-->
    <el-dialog title="编辑影院信息" :visible.sync="editDialogVisible" :modal-append-to-body="false">
      <el-row>
        <el-col :span="15" :offset="1">
          <el-form :model="editForm" :rules="rules" ref="editForm">
            <el-form-item prop="cinemaName" label="影院名称" :label-width="formLabelWidth">
              <el-input v-model="editForm.cinemaName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item prop="brand" label="影院品牌" :label-width="formLabelWidth">
              <el-input v-model="editForm.brand" autocomplete="off"></el-input>
            </el-form-item>
            <!--
            <el-form-item prop="birthday" label="出生日期" :label-width="formLabelWidth">
              <el-date-picker type="date" placeholder="选择日期" v-model="editForm.birthday" style="float: left"></el-date-picker>
            </el-form-item>
            -->
            <el-form-item prop="address" label="地址" :label-width="formLabelWidth">
              <el-input type="textarea" :autosize="{minRows:5, maxRows:5}" v-model="editForm.address" autocomplete="off"></el-input>
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

  </div>
</template>

<script>
  export default {
    name: "cinemaList",
    data() {
      return {
        movies:[],
        movie: {},
        keyword: '',
        movieList: [],
        movieDrawer: false,
        dayScenesList: [],
        //可选时间范围
        selectableRange: [],
        //影院列表
        cinemaList: [],
        //影厅列表
        hallList: [],
        cinema: {},
        hall: {},
        //场次列表
        scenesList: [],
        //编辑表单
        editForm: {},
        editScenesForm: {},
        scenesForm: {},
        //分页组件
        pagination: {
          currentPage: 1, //当前页
          pageSize: 6,   //每页条数
          total: 0,       //总条数
        },
        //电影分页组件
        moviePagination: {
          currentPage: 1, //当前页
          pageSize: 10,   //每页条数
          total: 0,       //总条数
        },
        globalIndex: 0,
        editDialogVisible: false,  //编辑窗口
        formLabelWidth: '120px',
        rules: {
          cinemaName: [
            { required: true, message: '不能为空', trigger: 'blur' },
          ],
          brand: [
            { required: true, message: '不能为空', trigger: 'blur' },
          ],
          address: [
            { required: true, message: '不能为空', trigger: 'blur' },
          ],

          movieName: [
            { required: true, message: '请输入电影中文名', trigger: 'blur' },
          ],
          date: [
            { required: true, message: '请选择日期', trigger: 'blur' },
          ],
          dateStart: [
            { required: true, message: '请选择开始时间', trigger: 'blur' },
          ],
          dateEnd: [
            { required: true, message: '请选择结束时间', trigger: 'blur' },
          ],
          price: [
            { required: true, message: '请输入价格', trigger: 'blur' },
          ],
        },
        dialogTableVisible: false,
        dialogHallVisible: false,
        scenesEditDialogVisible: false,
        //排片窗口
        scenesDialogVisible:false,
        cinemaId: "",
        hallId: "",
        pickerOptions: {
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '明天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '后天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24 * 2);
              picker.$emit('pick', date);
            }
          }]
        },

      }
    },
    mounted() {
      this.getAllMovieList()
      this.getCinemaList();
      this.getMovieList()
    },
    methods: {
      getMovieName(movieId) {
        for (let movie in this.movies) {
          if (this.movies[movie].movieId === movieId) {
            return this.movies[movie].movieName
          }
        }
        return ''
      },
      getMovieDetail(scenes) {
        this.movies.forEach(value => {
          if (value.movieId === scenes.movieId) {
            scenes.movieName = value.movieName;
          }
        })
      },
      searchMovie() {
        this.moviePagination.currentPage = 1;
        this.getMovieList()
      },
      //切换页码
      handleCurrentChange(currentPage) {
        this.pagination.currentPage = currentPage;
        this.getCinemaList();
      },
      changeMoviePage(currentPage) {
        this.moviePagination.currentPage = currentPage;
        this.getMovieList();
      },
      //获取所有影院信息
      getCinemaList() {
        //分页查询参数
        let url = "/cinema/listCinemas/" + this.pagination.currentPage + "/" + this.pagination.pageSize
        this.$axios.get(url).then((res) => {
          if (res.data.flag) {
            this.cinemaList = res.data.data.records;
            this.pagination.total = res.data.data.total;
          }
        })
      },

      //获取影院所有影厅
      getHallList(cinemaId) {
        //查询参数
        this.cinemaId=cinemaId;
        let url = "/hall/getAllByCinemaId/"  + cinemaId
        this.$axios.get(url).then((res) => {
          if (res.data.flag) {
            this.hallList = res.data.data;
          }
        })
      },
      //编辑影院修改信息
      editCinema(cinema, index) {
        //先拷贝要修改的信息
        this.editForm = JSON.parse(JSON.stringify(cinema));
        //this.editForm.birthday = new Date(this.editForm.birthday);
        //打开窗口
        this.editDialogVisible = true;
        this.currentPer = cinema;
      },
      //提交影院修改信息
      submitEditForm(index) {
        this.$refs['editForm'].validate((valid) => {
          if (valid) {
            let param = new FormData();
            param.append("cinemaId",this.editForm.cinemaId)
            param.append("cinemaName", this.editForm.cinemaName);
            param.append("brand", this.editForm.brand);
            param.append("address", this.editForm.address);
            this.$axios.put("/cinema/editCinema", param).then((res) => {
              if (res.data.flag) {
                //修改成功
                this.$message.success(res.data.msg);
                //关闭并重置表单
                this.editDialogVisible = false;
                this.resetEditForm();
                //刷新
                this.getCinemaList();

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

      //获取所有场次
      getScenesList(hallId) {
        //查询参数
        this.scenesList = []
        this.dialogHallVisible = true;
        this.hallId=hallId;
        let url = "/scenes/getAllByHallId/"+ hallId
        this.$axios.get(url).then((res) => {
          if (res.data.flag) {
            res.data.data.forEach(value => {
              this.getMovieDetail(value)
            })
            res.data.data.forEach(value => {
              this.scenesList.push(value)
            })
          }
        })
      },
      //编辑场次
      editScenes(scenes){
        this.editScenesForm = JSON.parse(JSON.stringify(scenes));
        this.editScenesForm.dateStart = new Date(this.editScenesForm.dateStart);
        this.editScenesForm.dateEnd = new Date(this.editScenesForm.dateEnd);
        this.scenesEditDialogVisible = true;
      },
      //提交场次信息
      submitEditForm2(editScenesForm) {
        this.$refs['editScenesForm'].validate((valid) => {
          if (valid) {
            let param = new FormData();
            param.append("scenesId",editScenesForm.scenesId)
            param.append("movieId",editScenesForm.movieId)
            param.append("dateStart",editScenesForm.dateStart);
            param.append("dateEnd", editScenesForm.dateEnd);
            param.append("price", editScenesForm.price);
            this.$axios.put("/scenes/editScenes", param).then((res) => {
              if (res.data.flag) {
                //修改成功
                this.$message.success(res.data.msg);
                //关闭并重置表单
                this.getScenesList(this.hallId)
                this.scenesEditDialogVisible = false;
                this.resetEditForm();
                //刷新
                this.getCinemaList();

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

      //删除场次
      handleScenesDelete(row) {
        this.$confirm("此操作永久删除当前信息，是否继续？").then(()=>{
          this.axios.delete("/scenes/deleteByScenesId/"+row.scenesId).then((res)=>{
            if(res.data.flag){
              this.$message.success("删除成功");
              //2.重新加载数据
              this.getScenesList(this.hallId);
            }else{
              this.$message.error("数据同步失败，自动刷新");
            }
          })
        })
      },
      //
      editHall(cinema){

        this.dialogTableVisible = true;
        this.cinema = cinema;
        this.getHallList(cinema.cinemaId);
      },
      //选择当前日期对应的场次
      selectDayScenes(date) {
        this.axios.get("/scenes/getScenesByDate/"+this.hallId+"/"+this.scenesForm.date.getDate()).then((res) => {
          if (res.data.flag) {
            this.dayScenesList = res.data.data;
            console.log(this.dayScenesList)
            //设置可选时间段
            let ts = '00:00:00'
            let te = '00:00:00'
            this.dayScenesList.forEach(value => {
              let dateStart = value.dateStart;
              let dateEnd = value.dateEnd;
              ts = this.$format(dateStart, "HH:mm:ss")
              this.selectableRange.push(""+te + " - " + ts);
              te = this.$format(dateEnd, "HH:mm:ss")
            })
            this.selectableRange.push(""+te + " - " + "23:59:59")
          }
        })
      },
      openScenesDialog() {
        this.scenesDialogVisible = true
      },
      submitScenesForm() {
        this.scenesForm.hallId = this.hallId
        this.scenesForm.dateStart.setDate(this.scenesForm.date.getDate());
        this.scenesForm.dateEnd.setDate(this.scenesForm.date.getDate());
        this.axios.post("/scenes/addScenes", this.scenesForm).then((res) => {
          if (res.data.flag){
            this.$refs['scenesForm'].resetFields()
            this.scenesDialogVisible = false;
            this.$message.success(res.data.msg);
            this.getScenesList(this.hallId)
          } else {
            this.$message.error(res.data.msg);
          }
        })
      },
      //查找所有的电影
      getMovieList() {
        let rest = "/"+this.moviePagination.currentPage+"/"+this.moviePagination.pageSize
        let params = "?keyword=" + this.keyword
        this.axios.get("/movie/listAllMovie" + rest + params).then((res) => {
          if (res.data.flag) {
            this.movieList = res.data.data.records
            this.moviePagination.total = res.data.data.total;
          }
        })
      },
      getAllMovieList() {
        this.axios.get("/movie/movieList" ).then((res) => {
          if (res.data.flag) {
            this.movies = res.data.data
          }
        })
      },
      openMovieDrawer() {
        this.movieDrawer = true;
        this.getMovieList();
      },
      selectMovie(movie) {
        this.scenesForm.movieName = movie.movieName
        this.scenesForm.movieId = movie.movieId
        this.editScenesForm.movieName = movie.movieName
        this.editScenesForm.movieId = movie.movieId
        this.movieDrawer = false
      },
      resetEditForm() {
        this.$refs['editForm'].resetFields();
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
  .row-con {
    margin-bottom: 20px;
    display: flex;
    flex-flow: row wrap;
  }
  .row-con .card {

    height: 100%;
    margin-right: 20px;
    transition: all .5s;

  }
</style>
