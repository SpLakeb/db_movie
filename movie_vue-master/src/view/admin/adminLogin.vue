<template>
  <div>
    <el-row>
      <el-col :span="8" :offset="8">
        <div class="grid-content bg-purple-dark"
             style="background-color: #e8eee2 ;border-radius: 30px; box-shadow: 0 5px 20px 0 #99CB1E;
             padding: 50px 50px 50px 10px;margin-top: 35px;">
          <h1 style="text-align: center; color: #99CB1E; margin-left: 70px;">天天影院用户登录</h1>
          <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
                   class="demo-ruleForm">
            <el-form-item label="管理员账户" prop="username">
              <el-input type="text" v-model="ruleForm.username" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
              <el-button @click="register()">立即注册</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
export default {
  name: "loginPage",
  data() {
    const checkUsername = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入管理员账户'))
      } else {
        callback();
      }
    };
    const checkPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        username: '',
        password: '',
      },
      rules: {
        username: [
          {validator: checkUsername, trigger: 'blur'}
        ],
        password: [
          {validator: checkPassword, trigger: 'blur'}
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios({
            method: 'post',
            url: '/cinema/adminLogin',
            params: {username: this.ruleForm.username, password: this.ruleForm.password}
          }).then((res) => {
            if (res.data.flag) {
              //登录成功
              this.$message.success(res.data.msg)
              //登录后的用户信息为： res.data.data
              //后续跳转待写
                this.$router.push('/adminMain');

            } else {
              this.$message.error(res.data.msg);
            }
          })
        }
      });
    },
    register() {

    }
  }
}
</script>

<style scoped>

</style>
