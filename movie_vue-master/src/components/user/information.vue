<template>
  <div>
    <el-row>
      <el-col :span="10" style="margin-top: 30px">
        <el-upload
          ref="img"
          name="img"
          class="avatar-uploader"
          :auto-upload="false"
          action="#"
          :show-file-list="false"
          :on-change="imgUpload"
          :on-remove="imgRemove">
          <img v-if="userInfo.avatar" :src="userInfo.avatar" class="avatar" alt="演员图片">
          <el-button style="margin-top: 20px">上传图片</el-button>
          <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过2MB</div>
        </el-upload>
      </el-col>
      <el-col :span="8" :offset="1" style="margin-right: 200px;margin-top: 50px">
        <el-form :model="userInfo" label-position="left" ref="editForm">
          <el-form-item prop="username" label="用户名:" :label-width="formLabelWidth">
            <el-input v-model="userInfo.username" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item prop="gender" label="性别:" :label-width="formLabelWidth">
            <el-radio-group v-model="userInfo.gender">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item prop="birth" label="出生日期" :label-width="formLabelWidth">
            <el-date-picker type="date" placeholder="选择日期" v-model="userInfo.birth"
                            style="float: left;width: 210px"></el-date-picker>
          </el-form-item>
          <el-form-item prop="signature" label="描述" :label-width="formLabelWidth">
            <el-input type="textarea" :autosize="{minRows:1, maxRows:5}" v-model="userInfo.signature"
                      placeholder="请输入个性签名" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item style="margin-left: 110px">
            <el-popconfirm title="确认保存修改?" @confirm="handleSave">
              <el-button type="primary" slot="reference" round>保 存</el-button>
            </el-popconfirm>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "information",
  data() {
    return {
      userInfo: {},
      img: {},
      formLabelWidth: '120px',
    }
  },
  methods: {
    //上传图片
    imgUpload(file) {
      if (this.beforeAvatarUpload(file)) {
        this.userInfo.avatar = URL.createObjectURL(file.raw);
        this.img = file.raw;
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
    handleReset() {
      location.reload();
    },
    handleSave() {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          let param = new FormData();
          param.append("username", this.userInfo.username);
          param.append("gender", this.userInfo.gender);
          param.append("birth", new Date(this.userInfo.birth));
          param.append("signature", this.userInfo.signature);

          param.append("img", this.img);
          this.$axios({
            url: '/user/editUserInfo',
            data: param,
            method: 'post'
          }).then(res => {
            if (res.data.flag) {
              this.$message.success(res.data.msg);
              this.getUserInfo();
            } else {
              this.$message.error(res.data.msg);
            }
          })
        } else {
          this.$message.warning("信息格式有误！")
        }
      })
    },
    getUserInfo() {
      this.$axios({
        url: '/user/myInfo'
      }).then(res => {
        console.log(res.data.data)
        this.userInfo = res.data.data
      });
    }
  },
  mounted() {
    this.getUserInfo();
  }
}
</script>

<style scoped>
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
  width: 280px;
  height: 280px;
  line-height: 280px;
  text-align: center;
}

.avatar {
  width: 300px;
  height: 300px;
  display: block;
}
</style>
