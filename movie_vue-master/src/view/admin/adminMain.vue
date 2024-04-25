<template>
  <div>
    <el-container style="position:fixed;left:0;height:100%;width: 100%;margin: 0 auto">
      <el-header style="background-color: #2b526e; box-shadow: 0 0 20px #2b526e">
        <el-row>
          <el-col :span="4">
            <el-icon class="el-icon-video-camera-solid"
                     style="color: #FFFFFF; font-size: 50px; margin-top: 5px"></el-icon>
          </el-col>
          <el-col :span="8" :offset="4"><h1 style="color: #fff; margin-top: 15px">天 天 影 院 后 台 管 理 系 统</h1></el-col>
          <el-col :span="4" style="color: #FFF; margin-top: 20px">
            <div v-if="this.admin.status">欢迎您！管理员{{ admin.username }}</div>
          </el-col>
          <el-col :span="4">
            <div>
              <el-popover
                placement="bottom"
                trigger="hover">
                <el-row v-if="this.admin.status">
                  <el-col style="margin-left: 50px">
                    <el-link :underline="false" @click="logout"><span style="font-size: 20px">退出</span></el-link>
                  </el-col>
                </el-row>
                <el-row v-if="!this.admin.status">
                  <el-col style="margin-left: 50px">
                    <el-link :underline="false" @click="loginDialogVisible=true"><span style="font-size: 20px">登录</span>
                    </el-link>
                  </el-col>
                </el-row>
                <el-button slot="reference" icon="el-icon-user-solid" circle style="margin-top: 10px"></el-button>
              </el-popover>
            </div>
          </el-col>
        </el-row>
      </el-header>
      <el-container style="height: 90%">
        <el-aside width="200px">
          <el-menu
            :router="true"
            :default-active="this.$route.path.split('/').pop()"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
            background-color="#2c3e50"
            text-color="#fff"
            style="height: 100%;box-shadow: 0 0 20px #2b526e"
            active-text-color="#3fa7f8">
            <el-menu-item index="dataShow">
              <i class="el-icon-s-data"></i>
              <span slot="title">数据总览</span>
            </el-menu-item>
            <el-menu-item index="movieManage">
              <i class="el-icon-video-camera"></i>
              <span slot="title">电影管理</span>
            </el-menu-item>
            <el-menu-item index="performerManage">
              <i class="el-icon-view"></i>
              <span slot="title">演员管理</span>
            </el-menu-item>
            <el-menu-item index="cinemaManage">
              <i class="el-icon-office-building"></i>
              <span slot="title">影院管理</span>
            </el-menu-item>
            <el-menu-item index="userManage">
              <i class="el-icon-s-custom"></i>
              <span slot="title">用户管理</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main style="padding-top: 0;">
          <el-row style="border-bottom: #2c3e50 2px solid">
            <el-col :span="2">
              <h2>{{ title }}</h2>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <router-view></router-view>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </el-container>

    <!-- 管理员登录 -->
    <el-dialog title="管理员登录" :visible.sync="loginDialogVisible" width="400px">
      <el-form :model="loginForm" ref="loginForm" :rules="rules" :label-width="formLabelWidth"
               style="margin-right: 50px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="loginForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="loginForm.password" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-row>
          <el-button type="primary" @click="login" style="width: 100%;">登 录</el-button>
        </el-row>
      </div>
    </el-dialog>
  </div>

</template>

<script>
export default {
  name: "adminMain",
  computed: {


    title() {
      let path = this.$route.path.split('/').pop();
      switch (path) {
        case 'dataShow':
          return '数据总览';
        case 'movieManage':
          return '电影管理';
        case 'performerManage':
          return '演员管理';
        case 'cinemaManage':
          return '影院管理';
        case 'userManage':
          return '用户管理';
        default:
          return '';
      }
    }
  },
  data() {
    return {
      loginDialogVisible: false,  //登录弹窗
      //登录表单
      loginForm: {
        username: '',
        password: '',
      },
      //管理员
      admin: {
        username: '',
        status: false
      },
      formLabelWidth: '120px',
      //输入规则
      rules: {
        username: [
          {required: true, message: "请输入用户名", trigger: 'blur'}
        ],
        password: [
          {required: true, message: "请输入密码", trigger: 'blur'},
          {min: 8, max: 16, message: "长度在8-16个字符", trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },

    //管理员登录
    login() {
      this.$refs["loginForm"].validate((valid) => {
        if (valid) {
          //验证成功，发送登录请求
          this.$axios({
            url: '/admin/adminLogin',
            data: this.loginForm,
            method: 'post',
          }).then((res) => {
            if (res.data.flag) {
              this.$message.success(res.data.msg);
              //关闭登录窗口
              this.loginDialogVisible = false;
              this.admin.username = this.loginForm.username;
              this.admin.status = true;
            } else {
              //登陆失败
              this.$message.error(res.data.msg);
            }
          })
        }
      })
    },
    //退出登录
    logout() {
      this.admin.status = false;
      this.$message.info("管理员退出登录")
    }
  }
}
</script>

<style scoped>
.el-dialog {
  border-radius: 30px;
}
</style>
