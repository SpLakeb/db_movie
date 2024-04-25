<template>
  <div>
    <div>
      <!--表头 -->
      <el-table size="small" current-row-key="id" :data="this.userList" stripe highlight-current-row>

        <el-table-column type="index" align="center" label="序号"></el-table-column>

        <el-table-column label="头像" align="center" >
          <template slot-scope="scope">
            <el-avatar shape="circle" :size="35" :src="scope.row.avatar"></el-avatar>
          </template>
        </el-table-column>
        <!--
              <img :src="scope.row.avatar" width="25" height="25" class="head_pic" />
         -->
        <el-table-column prop="username" label="用户名" align="center"></el-table-column>

        <el-table-column label="评论" align="center">

          <template slot-scope="scope">

            <el-button type="primary" size="mini" round @click="editcomments(scope.row.userId)">查看</el-button>

          </template>

          comments
        </el-table-column>

        <el-table-column prop="email" label="个人邮箱" align="center"></el-table-column>

        <el-table-column prop="signature" label="个性签名" align="center"></el-table-column>

      </el-table>


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
    <el-dialog title="评论详情" :visible.sync="dialogTableVisible" width="75%" :modal-append-to-body="false">
      <el-table :data="this.evaluateList">
        <el-table-column property="movieName" label="电影名称" width="300">
          <template slot-scope="scope">
            《{{getMovieName(scope.row.movieId)}}》
          </template>
        </el-table-column>
        <el-table-column property="grade" label="评分" width="100" align="center"></el-table-column>
        <el-table-column property="content" label="评论内容" width="600" align="center"></el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" width="100" round @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>

      </el-table>

      <el-pagination
        style="margin-top: 20px"
        background
        layout="prev, pager, next"
        @current-change="pageHandleCurrentChange"
        :current-page="evaluatePagination.currentPage"
        :page-size="evaluatePagination.pageSize"
        :total="evaluatePagination.total">
      </el-pagination>
    </el-dialog>

  </div>
</template>

<script>

  export default {
    name: "userList",
    data() {
      return {
        //列表
        movieList: [],
        userList: [],
        evaluateList:[],
        //编辑表单
        //分页组件
        pagination: {
          currentPage: 1, //当前页
          pageSize: 10,   //每页条数
          total: 0,       //总条数

        },
        evaluatePagination: {
          currentPage: 1, //当前页
          pageSize: 5,   //每页条数
          total: 0,       //总条数

          userId: ""

        },
        dialogTableVisible: false,

      }
    },
    mounted() {
      this.getUserList();
      this.getMovieList()
    },
    methods: {
      getMovieName(movieId) {
        for (let movie in this.movieList) {
          if (this.movieList[movie].movieId === movieId) {
            return this.movieList[movie].movieName
          }
        }
        return ''
      },
      //查找所有的电影
      getMovieList() {
        this.axios.get("/movie/movieList").then((res) => {
          if (res.data.flag) {
            this.movieList = res.data.data
          }
        })
      },
      //切换页码
      handleCurrentChange(currentPage) {
        this.pagination.currentPage = currentPage;
        this.getUserList();
      },
      pageHandleCurrentChange(currentPage) {
        this.evaluatePagination.currentPage = currentPage;
        this.editcomments(this.evaluatePagination.userId);
      },
      //获取所有信息
      getUserList() {
        //分页查询参数
        let url = "/user/listUsers/" + this.pagination.currentPage + "/" + this.pagination.pageSize
        this.$axios.get(url).then((res) => {
          if (res.data.flag) {
            this.userList = res.data.data.records;
            this.pagination.total = res.data.data.total;
          }
        })
      },


      editcomments(userId){
        this.dialogTableVisible = true;
        this.evaluatePagination.userId=userId;
        let url = "/evaluate/userIdEvaluate/" + this.evaluatePagination.currentPage + "/" + this.evaluatePagination.pageSize+ "/" + userId
        this.$axios.get(url).then((res) => {
          if (res.data.flag) {
            this.evaluateList = res.data.data.records;
            this.evaluatePagination.total = res.data.data.total;
          }
        })

      },
      handleDelete(row) {
        this.$confirm("此操作永久删除当前信息，是否继续？").then(()=>{
          console.log(this.evaluatePagination.userId)
          this.axios.get("/evaluate/deleteEvaluate/" + this.evaluatePagination.userId + "/" + row.movieId).then((res)=>{
            if(res.data.flag){
              this.$message.success("删除成功");
            }else{
              this.$message.error("数据同步失败，自动刷新");
            }
          }).finally(()=>{
            //2.重新加载数据
            this.editcomments(this.evaluatePagination.userId);
          });
        })
      },

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

</style>

