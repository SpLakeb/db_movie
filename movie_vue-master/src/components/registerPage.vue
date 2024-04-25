<template>
  <div>
    <el-row>
      <el-col :span="8" :offset="8">
        <div class="grid-content bg-purple-dark"
             style="background-color: #e8eee2 ;border-radius: 30px; box-shadow: 0 5px 20px 0 #99CB1E;
             padding: 50px 50px 50px 10px;margin-top: 10px">
          <h1 style="text-align: center; color: #99CB1E; margin-left: 70px;">天天影院用户注册</h1>
          <el-form :model="registerForm" status-icon :rules="rules" ref="registerForm" label-width="100px"
                   class="demo-registerForm"
                   th:src="@{/register}">
            <el-form-item label="邮箱" prop="email">
              <el-input type="text" v-model="registerForm.email"
                        autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="用户名" prop="username">
              <el-input type="text" v-model="registerForm.username"
                        autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="registerForm.password"
                        autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPassword">
              <el-input type="password" v-model="registerForm.checkPassword" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="性别" prop="gender">
              <div align="center" style="margin-right: 20px">
                <template>
                  <el-radio v-model="registerForm.gender" label="男">男</el-radio>
                  <el-radio v-model="registerForm.gender" label="女">女</el-radio>
                </template>
              </div>
            </el-form-item>
            <el-form-item label="出生日期" prop="birth">
              <div id="date">
                <template>
                  <div class="block">
                    <span class="demonstration"></span>
                    <el-date-picker
                      v-model="registerForm.birthday"
                      type="date" :editable="false"
                      placeholder="选择日期"
                      style="width: 346px">
                    </el-date-picker>
                  </div>
                </template>
              </div>
            </el-form-item>
            <el-form-item label="头像" prop="avatar">
              <el-upload
                ref="upload"
                action="http://localhost:8081/api/user/picture"
                list-type="picture-card"
                :on-preview="handlePictureCardPreview"
                :on-remove="handleRemove"
                >
                <i class="el-icon-plus"></i>
              </el-upload>
              <el-dialog :visible.sync="this.registerForm.dialogVisible">
                <img width="100%" :src="this.registerForm.dialogImageUrl" alt="">
              </el-dialog>
            </el-form-item>
            <el-form-item label="个性签名" prop="signature">
              <el-input type="textarea" v-model="registerForm.signature"
                        autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item style="margin-left: 70px">
              <el-button type="primary" @click="submitForm('registerForm')">提交</el-button>
              <el-button @click="resetForm('registerForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "registerPage",
  data() {
    const checkEmail = (rule, value, callback) => {
      const reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
      if (!reg.test(value)) {
        callback(new Error('请输入正确的邮箱格式'))
      } else {
        callback();
      }
    };
    var checkUsername = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('用户名不能为空'));
      } else {
        callback();
      }
    }
    var checkBirthday = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('出生日期不能为空'));
      } else {
        callback();
      }
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.registerForm.checkPassword !== '') {
          this.$refs.registerForm.validateField('checkPassword');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      registerForm: {
        email: '',
        username: '',
        password: '',
        gender: '男',
        birth: '',
        telephone: '',
        avatar: '',
        dialogImageUrl: '',
        dialogVisible: false
      },
      rules: {
        email: [
          {validator: checkEmail, trigger: 'blur'}
        ],
        username: [
          {validator: checkUsername, trigger: 'blur'}
        ],
        password: [
          {validator: validatePass, trigger: 'blur'}
        ],
        checkPassword: [
          {validator: validatePass2, trigger: 'blur'}
        ],
        birthday: [
          {validator: checkBirthday, trigger: 'blur'}
        ],
        gender: []
      }
    };
  },
  methods: {
    submitForm(formName, event) {
      console.log(this.registerForm.dialogImageUrl);
      // this.$refs[formName].validate((valid) => {
      //   if (valid) {
      //     var params = new URLSearchParams();
      //     params.append('username', this.registerForm.username)
      //     params.append('password', this.registerForm.password)
      //     params.append('birthday', this.registerForm.birthday)
      //     params.append('telephone', this.registerForm.telephone)
      //     params.append('gender', this.registerForm.gender)
      //     axios.post("/IGeekShopDemo/Register", params).then((res) => {
      //       if (res.data.flag) {
      //         //注册成功
      //         this.$message.success(res.data.msg);
      //         setTimeout(function () {
      //           location.href = '/IGeekShopDemo/Login';
      //         }, 2000);
      //       } else {
      //         this.$message.error(res.data.msg);
      //       }
      //     })
      //   }
      // });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    }
  }

}

function uuid() {
  var s = [];
  var hexDigits = "0123456789abcdef";
  for (var i = 0; i < 36; i++) {
    s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
  }
  s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
  s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
  s[8] = s[13] = s[18] = s[23] = "-";

  var uuid = s.join("");
  return uuid;
}
</script>


<style>
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
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
