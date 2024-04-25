<template>

  <div>
    <!-- 登录框 -->
    <el-row style="margin-top: 80px">
      <el-col :span="5" style="text-align: left">
        <label style="color: rgb(90, 90, 90); font-size: 13px"
        >账号登录</label>
      </el-col>

      <el-col :span="4" style="text-align: right">
        <label
          style="color: rgb(90, 90, 90); font-size: 13px; cursor: pointer"
          @click="change">验证码登录<i
          class="el-icon-message"
          style="font-size: 120%; position: relative; top: 2px"
        ></i
        >&nbsp;&nbsp;&nbsp;</label
        >
      </el-col>
    </el-row>

    <el-row style="margin-top: 10px">
      <el-form
        :model="dynamicValidateForm"
        status-icon
        :rules="rules"
        ref="dynamicValidateForm"
        class="demo-ruleForm"
        hide-required-asterisk
        style="width: 250px"
      >
        <!-- 邮箱框 -->
        <el-form-item prop="email">
          <el-input
            v-model="dynamicValidateForm.email"
            placeholder="邮箱"
            prefix-icon="el-icon-message"
          ></el-input>
        </el-form-item>
        <!-- 密码框 -->
        <el-form-item prop="pass">
          <el-input
            type="password"
            v-model="dynamicValidateForm.pass"
            placeholder="密码"
            prefix-icon="el-icon-lock"
            show-password
          ></el-input>
        </el-form-item>

      </el-form>
    </el-row>
    <!-- 忘记密码链接 -->
    <el-row>
      <el-link
        type="danger"
        style="position: relative; top: -20px; left: 190px; font-size: 10px"
        :underline="false"
        href="/#/forgetPassword"
      >忘记密码？
      </el-link
      >
    </el-row>
    <!-- 登录按钮 -->
    <el-row>
      <el-button type="danger" style="width: 250px" @click="submit">立即登录</el-button>
    </el-row>
    <!-- 注册按钮 -->
    <el-row style="margin-top: 8px">
          <span>还没有账号？</span
          >
      <el-link type="danger" :underline="false" @click="toReg">免费注册</el-link>
    </el-row>

    <!-- 用户协议 -->
    <el-row>
      <el-checkbox
        v-model="checked"
        style="display: inline-block; margin-top: 20px; height: 5px"
        class="myRedCheckBox"
      >
      </el-checkbox>

      <div
        style="
              display: inline-block;
              width: 230px;
              text-align: left;
              margin-left: 10px;
              margin-top: 17px;
            "
      >
            <span>
              我已阅读并同意
              <span style="color: rgb(255, 100, 100); cursor: pointer"
              >《天天用户协议》</span
              >
              <span style="color: rgb(255, 100, 100); cursor: pointer"
              >《隐私政策》</span
              >
              ，并授权天天使用该天天账号信息（如昵称、头像、收货地址）进行统一管理
            </span>
      </div>
    </el-row>
  </div>

</template>

<script>
export default {
  name: "userLoginForm1",
  data() {
    let checkLogin = (rule, value, callback)=>{
      this.$axios({
        url: '/user/login',
        method: "post",
        data: {email: this.dynamicValidateForm.email, password: this.dynamicValidateForm.pass}
      }).then(res => {
        if (res.data.flag === true){
          callback()
        }else {
          callback(new Error('邮箱或密码不正确'))
        }
      })
    }
    return {
      dynamicValidateForm: {
        email: "",
        pass: "",
      },
      rules: {
        email: [
          {required: true, message: "请输入邮箱地址", trigger: "blur"},
          {type: "email", message: "请输入正确的邮箱地址", trigger: "blur"},
        ],
        pass: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {validator: checkLogin, trigger: 'submit'}
        ]
      },
      checked: false,
    };
  },
  methods: {
    change(){
      this.$router.push('/userLogin/form2')
    },
    submit() {

      if (!this.checked) {
        this.$message({message: '请勾选用户协议！', type: "error"})
      } else {
        this.submitForm('dynamicValidateForm')
      }
    },
    submitForm(dynamicValidateForm) {
      this.$refs[dynamicValidateForm].validate((valid) => {
        if (valid) {
          this.$router.push('/')
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    toReg(){
      this.$router.push('/userRegister')
    }
  }
}
</script>

<style scoped>

</style>
