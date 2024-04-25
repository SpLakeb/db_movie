<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="数据总览"><total></total></el-tab-pane>
<!--      <el-tab-pane label="今日票房"><box-office-today></box-office-today></el-tab-pane>-->
<!--      <el-tab-pane label="影院票房"><cinema-box-office></cinema-box-office></el-tab-pane>-->
<!--      <el-tab-pane label="最受欢迎" style="visibility: hidden"><popular-type></popular-type></el-tab-pane>-->
    </el-tabs>
  </div>
</template>

<script>
import boxOfficeToday from "./children/data/boxOfficeToday";
import cinemaBoxOffice from "./children/data/cinemaBoxOffice";
import popularType from "./children/data/popularType";
import total from "./children/data/total";
export default {
  name: "dataShow",
  components: {
    cinemaBoxOffice,
    boxOfficeToday,
    popularType,
    total,
  data(){
    return {
    }
  },
  methods: {
  },
  mounted() {
  }
}
}
</script>

<style scoped>

</style>
