<template>
  <div style="position:relative;height: 700px;width: 100%;">
    <el-row>
      <el-col :span="8"></el-col>
      <!-- 标题 -->
      <el-col :span="8">
        <div style="margin-top:60px">
          <h1 style="color: rgb(90, 90, 90)">账号注册</h1>
        </div>
        <!-- 步骤条 -->
        <div>
          <el-steps
            :active="active"
            finish-status="success"
            style="text-align: left; margin-top: 40px"
          >
            <el-step title="验证邮箱"></el-step>
            <el-step title="设置密码"></el-step>
            <el-step title="注册完成"></el-step>
          </el-steps>
        </div>

        <div style="display:flex;justify-content:center;margin-top: 50px;">
          <el-form
            :model="registerForm"
            label-position="top"
            status-icon
            :rules="rules"
            ref="registerForm"
            style="width: 300px;text-align:left"
          >
            <!-- 邮箱框 -->
            <transition name="el-fade-in-linear" @after-leave="afterLeave">
              <el-form-item
                prop="email"
                label="邮箱"
                v-show="active === 0"
                :rules="[
                  {
                    required: true,
                    message: '请输入邮箱地址',
                    trigger: 'blur'
                  },
                  {
                    type: 'email',
                    message: '请输入正确的邮箱地址',
                    trigger: ['blur', 'change']
                  }
                ]"
              >
                <el-input
                  v-model="registerForm.email"
                  placeholder="邮箱"
                  prefix-icon="el-icon-message"
                  size="large"
                ></el-input>
              </el-form-item>
            </transition>

            <!-- 验证码框 -->
            <transition name="el-fade-in-linear">
              <el-form-item prop="vcode" label="验证码" v-show="active === 0">
                <el-input
                  type="text"
                  v-model="registerForm.vcode"
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
                  >{{ valiBtn }}</el-button
                >
              </el-form-item>
            </transition>

            <!-- 设置密码 -->
            <transition name="el-fade-in-linear" @after-leave="afterLeave">
              <el-form-item
                label="密码"
                prop="pass"
                v-show="active === 1"
                required="true"
              >
                <el-input
                  type="password"
                  v-model="registerForm.pass"
                  autocomplete="off"
                ></el-input>
              </el-form-item>
            </transition>

            <transition name="el-fade-in-linear">
              <el-form-item
                label="确认密码"
                prop="checkPass"
                v-show="active === 1"
                required="true"
              >
                <el-input
                  type="password"
                  v-model="registerForm.checkPass"
                  autocomplete="off"
                ></el-input>
              </el-form-item>
            </transition>

            <!-- 设置个人信息 -->
            <transition name="el-fade-in-linear" @after-leave="afterLeave">
              <el-form-item
                label="昵称"
                v-show="active === 2"
                prop="nickname"
                :rules="[{ required: true, message: '昵称不能为空' }]"
              >
                <el-input
                  v-model="registerForm.nickname"
                  type="text"
                ></el-input>
              </el-form-item>
            </transition>
            <transition name="el-fade-in-linear">
              <el-form-item
                label="生日"
                v-show="active === 2"
                prop="birth"
                :rules="[{ required: true, message: '请填写生日' }]"
              >
                <el-date-picker
                  type="date"
                  placeholder="选择日期"
                  v-model="registerForm.birth"
                  style="width: 100%;"
                ></el-date-picker>
              </el-form-item>
            </transition>
            <transition name="el-fade-in-linear">
              <el-form-item label="性别" v-show="active === 2" required="true">
                <el-radio-group v-model="registerForm.gender">
                  <el-radio label="男"></el-radio>
                  <el-radio label="女"></el-radio>
                  <el-radio label="保密"></el-radio>
                </el-radio-group>
              </el-form-item>
            </transition>

            <transition name="el-fade-in-linear">
              <el-result
                icon="success"
                title="注册成功"
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
            </transition>
          </el-form>
        </div>
      </el-col>

      <el-col :span="8"></el-col>
    </el-row>

    <div
      style="height:100px;width: 500px;position: absolute;bottom: 10px;left: 500px;"
    >
      <el-button
        type="danger"
        style="margin-top: 12px;"
        @click="nextI"
        v-show="active === 0"
        >下一步</el-button
      >
      <el-button
        type="danger"
        style="margin-top: 12px;"
        @click="nextII"
        v-show="active === 1"
        >下一步</el-button
      >
      <el-button
        type="danger"
        style="margin-top: 12px;"
        @click="nextIII"
        v-show="active === 2"
        >注 册</el-button
      >
    </div>
  </div>
</template>

<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.registerForm.checkPass !== "") {
          this.$refs.registerForm.validateField("checkPass");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.registerForm.pass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };

    var validateVcode = (rule, value, callback) => {
      this.$axios({
        url: "/user/checkSixCode",
        params: { email: this.registerForm.email, sixCode: value }
      }).then(res => {
        if (res.data.flag) {
          callback();
        } else {
          callback(new Error("验证码错误"));
        }
      });
    };
    return {
      counts:null,
      active: 0,
      seconds: 10,
      registerForm: {
        email: "",
        vcode: "",
        pass: "",
        checkPass: "",
        age: "",
        nickname: "",
        birth: "",
        gender: "保密"
      },
      rules: {
        pass: [{ validator: validatePass }],
        checkPass: [{ validator: validatePass2 }],
        vcode: [
          { required: true, message: "验证码不能为空" },
          { pattern: '^\\d{6}$', message: "验证码必须六位数" },
          {
            validator: validateVcode,
            trigger: "submit"
          }
        ]
      },
      valiBtn: "获取验证码"
    };
  },
  methods: {
    afterLeave(el) {
      this.active += 0.5;
    },
    next() {
      if (this.active++ > 2) this.active = 0;
      if (this.active !== 0) {
        this.active -= 0.5;
      }
    },
    getCode() {
      this.$axios({
        url: "/user/checkEmailUsed",
        params: { email: this.registerForm.email }
      }).then(res => {
        if (!res.data.flag) {
          this.$refs["registerForm"].validateField("email", err => {
            if (err) {
              console.log("未通过");
              return;
            } else {
              this.$axios({
                url: "/user/sendEmailToCheck",
                params: { email: this.registerForm.email }
              });
              console.log("已通过");
              this.tackBtn(); //验证码倒数60秒
            }
          });
        } else {
          this.$message.error("该邮箱已被注册");
          return;
        }
      });
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
    nextI() {
      this.$refs["registerForm"].validateField("email", err => {
        if (!err) {
          this.$refs["registerForm"].validateField("vcode", err => {
            if (!err) {
              this.next();
            }
          });
        } else {
          this.$refs["registerForm"].validateField("vcode");
        }
      });
    },
    nextII() {
      this.$refs["registerForm"].validateField("pass", err => {
        if (!err) {
          this.$refs["registerForm"].validateField("checkPass", err => {
            if (!err) {
              this.next();
            }
          });
        } else {
          this.$refs["registerForm"].validateField("checkPass");
        }
      });
    },
    nextIII() {
      this.$refs["registerForm"].validateField("nickname", err => {
        if (!err) {
          this.$refs["registerForm"].validateField("birth", err => {
            if (!err) {

              this.$axios({
                method: 'post',
                url: '/user/add',
                data: {
                  username: this.registerForm.nickname,
                  gender: this.registerForm.gender,
                  birth: this.registerForm.birth,
                  email: this.registerForm.email,
                  password: this.registerForm.pass
              }
            })
              this.next();

              this.counts = setInterval(()=>{
                this.seconds--;
                if(this.seconds === 0){

                  this.toMain()
                }
              },1000)
            }
          });
        } else {
          this.$refs["registerForm"].validateField("birth");
        }
      });
    },
    toMain(){

      clearInterval(this.counts)
      this.$axios({
        method: 'post',
        url: '/user/login',
        data: {
          email: this.registerForm.email,
          password: this.registerForm.pass
        }
      }).then(()=>{
        this.$router.push('/')
      })

    }
  }
};
</script>

<style>
/* 选中后的字体颜色 */
.el-radio__input.is-checked + .el-radio__label {
  color: #000000 !important;
}
/* 选中后小圆点的颜色 */
.el-radio__input.is-checked .el-radio__inner {
  background: #e20000 !important;
  border-color: #e20000 !important;
}
</style>
