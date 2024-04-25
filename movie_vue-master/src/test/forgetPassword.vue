<template>
  <div>
    <!-- logo -->
    <div style="text-align: left; margin-left: 17%; margin-top: 40px">
      <el-image
        style="width: 200px; height: 66px"
        src="/static/log.png"
        fit="fit"
      >
      </el-image>
    </div>
    <el-row>

      <el-col :span="6"></el-col>
      <el-col :span="12">
        <!-- 步骤条 -->
        <div>
          <el-steps
            :active="active"
            finish-status="success"
            style="text-align: left; margin-top: 40px"
          >
            <el-step title="确认账号"></el-step>
            <el-step title="安全校验"></el-step>
            <el-step title="设置密码"></el-step>
            <el-step title="完成"></el-step>
          </el-steps>

          <el-form :model="forgetForm" ref="forgetForm" label-width="100px" class="demo-dynamic" :rules="rules">
<!--            邮箱-->
            <el-form-item
              prop="email"
              label="邮箱"
              v-show="active===0"
              style="width: 400px;margin-left: 120px;margin-top: 70px"
            >
              <el-input v-model="forgetForm.email"></el-input>
            </el-form-item>
<!--            验证码-->
            <el-form-item
              prop="vcode"
              label="验证码"
              v-show="active === 1"
              style="width: 400px;margin-left: 120px;margin-top: 70px"
            >
              <el-input
                type="text"
                v-model="forgetForm.vcode"
                autocomplete="off"
                prefix-icon="el-icon-edit"
                placeholder="验证码"
                style="width: 156px"
                size="large"
              ></el-input>
              <el-button
                type="danger"
                style="width: 139px"
                :disabled="disabled"
                @click="getCode"
              >{{ valiBtn }}
              </el-button
              >
            </el-form-item>
<!--            设置新密码-->
            <el-form-item
              label="密码"
              prop="pass"
              required="true"
              v-show="active===2"
              style="width: 400px;margin-left: 120px;margin-top: 70px"
            >
              <el-input
                type="password"
                v-model="forgetForm.pass"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              label="确认密码"
              prop="checkPass"
              required="true"
              v-show="active===2"
              style="width: 400px;margin-left: 120px"
            >
              <el-input
                type="password"
                v-model="forgetForm.checkPass"
                autocomplete="off"
              ></el-input>
            </el-form-item>

            <el-result
              icon="success"
              title="修改成功"
              subTitle=""
              v-show="active===3"
            >
              <template slot="subTitle">
                <div>
                  <span style="color: red">{{ seconds }}</span>
                  <span>秒后自动跳转，或点击下方立即跳转...</span>
                </div>
              </template>
              <template slot="extra">
                <el-button type="primary" size="medium" @click="toMain">去首页</el-button>
              </template>
            </el-result>
          </el-form>

          <div style="position: fixed;bottom: 200px;left: 700px">
            <el-button style="margin-top: 12px;" @click="checkEmail" v-show="active===0">下一步</el-button>
            <el-button style="margin-top: 12px;" @click="checkVcode" v-show="active===1">下一步</el-button>
            <el-button style="margin-top: 12px;" @click="submitForm" v-show="active===2">确认修改</el-button>
          </div>
        </div>
      </el-col>
      <el-col :span="6"></el-col>


    </el-row>
  </div>
</template>

<script>
export default {
  name: "forgetPassword",
  data() {
    var validateEmail = (rule, value, callback) => {
      this.$axios({
        url: '/user/checkEmail',
        params: {email: this.forgetForm.email}
      }).then(res => {
        if (!res.data.flag) {
          callback()
        } else {
          callback(new Error("不存在此账户"))
        }
      })
    };
    var validateVcode = (rule, value, callback) => {
      this.$axios({
        url: "/user/checkSixCode",
        params: { email: this.forgetForm.email, sixCode: value }
      }).then(res => {
        if (res.data.flag) {
          callback();
        } else {
          callback(new Error("验证码错误"));
        }
      });
    };
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.forgetForm.checkPass !== "") {
          this.$refs.forgetForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.forgetForm.pass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      counts:null,
      seconds: 10,
      forgetForm: {
        email: '',
        vcode:'',
        pass:'',
        checkPass:''
      },
      active: 0,
      rules: {
        email: [
          {required: true, message: '请输入邮箱地址', trigger: 'blur'},
          {type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']},
          {validator: validateEmail, trigger: 'submit'}
        ],
        vcode: [
          { required: true, message: "验证码不能为空" },
          { pattern: '^\\d{6}$', message: "验证码必须为六位数字" },
          {
            validator: validateVcode,
            trigger: "submit"
          }
        ],
        pass: [{ validator: validatePass }],
        checkPass: [{ validator: validatePass2 }],
      },
      valiBtn: "获取验证码"
    }
  },
  methods: {
    next() {
      if (this.active++ > 2) this.active = 0;
    },
    checkEmail() {
      this.$refs['forgetForm'].validateField("email", err => {
        if (!err) {
          this.next();
        }
      })
    },
    checkVcode(){
      this.$refs['forgetForm'].validateField("vcode", err => {
        if (!err) {
          this.next();
        }
      })
    },
    checkPass(){
      this.$refs["forgetForm"].validateField("pass", err => {
        if (!err) {
          this.$refs["forgetForm"].validateField("checkPass", err => {
            if (!err) {
              this.next();
            }
          });
        } else {
          this.$refs["forgetForm"].validateField("checkPass");
        }
      });
    },
    submitForm(){
      this.$axios({
        url:'user/updatePassword',
        params:{
          email:this.forgetForm.email,
          password:this.forgetForm.pass,
        }
      }),
      this.next(),
      this.counts = setInterval(()=>{
        this.seconds--;
        if(this.seconds === 0){

          this.toMain()
        }
      },1000)
    },
    toMain(){

      clearInterval(this.counts)
      this.$axios({
        method: 'post',
        url: '/user/login',
        data: {
          email: this.forgetForm.email,
          password: this.forgetForm.pass
        }
      }).then(()=>{
        this.$router.push('/')
      })

    },
    getCode() {
      this.$axios({
        url: "/user/sendEmailToCheck",
        params: {email: this.forgetForm.email}
      });
      console.log("已通过");
      this.tackBtn(); //验证码倒数60秒

    },
    tackBtn() {
      //验证码倒数60秒
      let time = 60;
      this.valiBtn = time + "秒后重试";
      this.disabled = true;
      let timer = setInterval(() => {
        if (time == 0) {
          clearInterval(timer);
          this.valiBtn = "获取验证码";
          this.disabled = false;
        } else {
          this.disabled = true;
          time--;
          this.valiBtn = time + "秒后重试";
        }
      }, 1000);
    },
  }
}
</script>

<style scoped>

</style>
