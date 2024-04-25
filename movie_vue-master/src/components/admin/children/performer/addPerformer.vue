<template>
  <div>
    <el-row>
      <el-col :span="8">
        <el-upload
          ref="img"
          name="img"
          class="avatar-uploader"
          :auto-upload="false"
          action="#"
          :show-file-list="false"
          :on-change="imgUpload"
          :on-remove="imgRemove">
          <img v-if="performer.imgUrl" :src="performer.imgUrl" class="avatar" alt="演员图片">
          <i v-else class="el-icon-plus avatar-uploader-icon">演员图片</i>
          <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过2MB</div>
        </el-upload>

        <div style="text-align: left">
          <div><h1>中文名：<span style="color: #2b526e">{{ performer.name }}</span></h1></div>
          <div><h1>英文名：<span style="color: #2b526e">{{ performer.englishName }}</span></h1></div>
          <div><h1>生日：<span style="color: #2b526e" v-if="performer.birthday">{{ performer.birthday }}</span></h1></div>
          <div><h1>简介：<span style="color: #2b526e"></span></h1></div>
          <div style="border: #2b526e 2px solid;padding-left: 10px;padding-right: 10px; height: 150px">
            <el-scrollbar style="height: 100%;">
              <p style="text-align: left;">
                {{ performer.description }}
              </p>
            </el-scrollbar>
          </div>
        </div>
      </el-col>
      <el-col :span="15" :offset="1">
        <div>
          <el-form label-position="right" :rules="rules" label-width="80px" :model="performer" ref="performer">
            <el-form-item prop="name" label="中文名">
              <el-input v-model="performer.name"></el-input>
            </el-form-item>
            <el-form-item prop="englishName" label="英文名">
              <el-input v-model="performer.englishName"></el-input>
            </el-form-item>
            <el-form-item prop="birthday" label="生日">
              <el-col :span="11">
                <el-date-picker type="date" placeholder="选择日期" value-format="yyyy年MM月dd日" v-model="performer.birthday" style="width: 100%;"></el-date-picker>
              </el-col>
            </el-form-item>
            <el-form-item prop="description" label="简介">
              <el-input type="textarea" v-model="performer.description" :autosize="{minRows:10, maxRows:10}"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="savePerformer">保存</el-button>
              <el-button @click="reset">重置</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "addPerformer",
  data() {
    return {
      performer: {
        name: '',         //演员姓名
        englishName: '',  //英文名
        imgUrl: '',       //图片路径
        description: '',  //简介
        birthday: '',     //生日

      },
      img: '',
      uploadURL: '',
      //校验规则
      rules: {
        name: [
          { required: true, message: '请输入演员中文名', trigger: 'blur' },
        ],
        englishName: [
          { required: true, message: '请输入演员英文名', trigger: 'blur' },
        ],
        birthday: [
          { required: true, message: '请选择生日', trigger: 'blur' },
        ],
        description: [
          { required: true, message: '请输入演员简介', trigger: 'blur' },
        ],
      }
    };
  },
  methods: {
    //上传图片
    imgUpload(file, fileList) {
      if (this.beforeAvatarUpload(file)) {
        this.performer.imgUrl = URL.createObjectURL(file.raw);
        this.img = file.raw;
      }

    },
    //移除图片
    imgRemove() {
      this.performer.imgUrl = '';
      this.img = '';
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
    //保存演员信息
    savePerformer() {
      this.$refs['performer'].validate((valid) => {
        if (valid) {
          if (this.img) {
            let fd = new FormData();
            fd.append("img", this.img);
            fd.append("name",this.performer.name)
            // fd.append("name", this.performer.name);
            fd.append("englishName", this.performer.englishName);
            fd.append("birthday", this.performer.birthday);
            fd.append("description", this.performer.description);

            this.$axios.post("/performer/save", fd).then((res) => {
              if (res.data.flag) {
                this.$message.success(res.data.msg);
                this.reset();
              } else {
                this.$message.error(res.data.msg);
              }
            })
          } else {
            this.$message.warning("未找到图片路径");
          }
        }
      })
    },
    reset() {
      this.$refs['performer'].resetFields();
      this.img = '';
      this.performer.imgUrl = '';
    },

  },

}

</script>

<style>
.el-scrollbar__wrap    {
  overflow-x: hidden;
}
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
  font-size: 20px;
  color: #8c939d;
  width: 180px;
  height: 250px;
  line-height: 250px;
  text-align: center;
}
.avatar {
  width: 180px;
  height: 250px;
  display: block;
}

</style>
