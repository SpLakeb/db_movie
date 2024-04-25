<template>
  <div>

    <el-dialog :visible.sync="loading"
               :close-on-click-modal="false"
               :close-on-press-escape="false"
               :show-close="false"
               top="30vh"
               width="20%" style="text-align: center">

      <i class="el-icon-loading" style="font-size: 200%"/>
      <p style="font-size: 20px">
        登录中
      </p>

    </el-dialog>
    <!-- 登录框 -->
    <el-row style="margin-top: 80px">
      <el-col :span="5" style="text-align: left">
        <label style="color: rgb(90, 90, 90); font-size: 13px"
        >账号登录</label>
      </el-col>

      <el-col :span="4" style="text-align: right">

        <label
          style="color: rgb(90, 90, 90); font-size: 13px; cursor: pointer"
          @click="change"
        >邮箱登录<i
          class="el-icon-s-custom"
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

        <!-- 验证码框 -->
        <el-form-item
          prop="vcode">
          <el-input
            type="text"
            v-model="dynamicValidateForm.vcode"
            prefix-icon="el-icon-edit"
            placeholder="验证码"
            style="width:130px"
          ></el-input>
          <el-button
            type="danger"
            style="width:116px"
            :disabled="disabled"
            @click="getCode"
          >{{ valiBtn }}
          </el-button>
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

    <el-row style="margin-top: 8px">
      <span style="font-size:13px">未注册的邮箱验证后自动创建天天账户</span>
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
  name: "userLoginForm2",
  data() {
    let checkCode = (rule, value, callback) => {

      this.$axios({
        url: '/user/checkSixCode',
        params: {email: this.dynamicValidateForm.email, sixCode: this.dynamicValidateForm.vcode}
      }).then(res => {
        if (res.data.flag === false) {
          callback(new Error('验证码不正确'))
        } else {
          callback()
        }
      })
    }

    return {
      loading: false,
      dynamicValidateForm: {
        email: "",
        vcode: ""
      },
      rules: {
        email: [
          {required: true, message: "请输入邮箱地址", trigger: "blur"},
          {type: "email", message: "请输入正确的邮箱地址", trigger: "blur"},
        ],
        vcode: [
          {pattern: '^\\d{6}$', message: '验证码必须为六位数字', trigger: 'blur'},
          {validator: checkCode, trigger: 'submit'}
        ]
      },
      checked: false,
      valiBtn: '获取验证码',
      disabled: false,
    };
  },
  methods: {
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

          this.loading = true

          this.$axios({
            method: "post",
            url: '/user/autoAddOrLogin',
            data: {email: this.dynamicValidateForm.email}
          }).then(res => {
            if (res.data.flag === true) {
              this.loading = false
              //跳转主页
              this.$router.push('/')
            } else {
              alert('服务器异常，请稍后重试！')
            }
          })


        } else {
          return false;
        }
      });
    },
    change() {
      this.$router.push('/userLogin/form1')
    },

    //获取验证码
    getCode() {
      this.disabled = true
      this.$refs['dynamicValidateForm'].validateField('email', (err) => {
        if (err) {
          console.log('未通过')
          this.disabled = false
        } else {
          this.$axios({
            url: '/user/hasOverTime',
            params: {email: this.dynamicValidateForm.email}
          }).then(res => {
            if (res.data.flag === false) {
              this.$message({
                message: '操作频繁，请于' + res.data.data + 's后重试。',
                type: 'error'
              })
              this.disabled = false
            } else {
              this.$axios({
                url: '/user/sendEmailToCheck',
                params: {email: this.dynamicValidateForm.email}
              })
              this.tackBtn();   //验证码倒数60秒
            }
          })
        }
      })
    },
    tackBtn() {       //验证码倒数60秒
      let time = 60;
      this.valiBtn = time + '秒后重试';
      this.disabled = true;
      let timer = setInterval(() => {
        if (time === 0) {
          clearInterval(timer);
          this.valiBtn = '获取验证码';
          this.disabled = false;
        } else {
          this.disabled = true;
          time--;
          this.valiBtn = time + '秒后重试';
        }
      }, 1000);
    },
  },
};
</script>

<style scoped>

</style>

