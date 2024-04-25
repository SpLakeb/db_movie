<template>
  <div id="app">
    <div style="width: 100%;height: 100%">
      <router-view/>
    </div>
<!--    <div style="margin-top: 300px;background: #3d3c3c;height: 200px">-->
<!--      <a href="https://beian.miit.gov.cn/" style="font-size: 20px;color: white;-->
<!--      line-height: 100px">-->
<!--        皖ICP备2022000783号-1</a>-->
<!--    </div>-->
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
.el-row {
  display: flex;
  flex-wrap: wrap;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  width: 1518px;
  margin: 0 auto;
}
body{
  margin: 0;
  user-select: none;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
}

.el-input__inner:focus {
  border-color: gray;
}
.el-button--danger,
.el-button--danger:focus,
.el-button--danger.is-active,
.el-button--danger:active {
  background: #e20000;
}
.el-button--danger:hover {
  background: #ff3a3a;
  border-color: #e20000;
  color: #fff;
}

/* 设置带边框的checkbox，选中后边框的颜色 */
.myRedCheckBox.is-bordered.is-checked {
  border-color: #e20000;
}

/* 设置选中后的文字颜色 */
.myRedCheckBox .el-checkbox__input.is-checked + .el-checkbox__label {
  color: #e20000;
}

/* 设置选中后对勾框的边框和背景颜色 */
.myRedCheckBox .el-checkbox__input.is-checked .el-checkbox__inner,
.myRedCheckBox .el-checkbox__input.is-indeterminate .el-checkbox__inner {
  border-color: #e20000;
  background-color: #e20000;
}

/* 设置checkbox获得焦点后，对勾框的边框颜色 */
.myRedCheckBox .el-checkbox__input.is-focus .el-checkbox__inner {
  border-color: #e20000;
}

/* 设置鼠标经过对勾框，对勾框边框的颜色 */
.myRedCheckBox .el-checkbox__inner:hover {
  border-color: #e20000;
}

</style>
