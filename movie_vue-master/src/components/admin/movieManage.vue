<template>
  <div>
    <el-tabs v-model="tabName" type="border-card" @tab-remove="removeTab">
      <el-tab-pane name="movieList" label="电影列表"><movie-list @addNewTab="addNewTab"></movie-list></el-tab-pane>
      <el-tab-pane  label="添加电影"><add-movie></add-movie></el-tab-pane>
      <el-tab-pane :name="movie.movieName"  :closable="true" v-for="(movie, index) in movieList" :key="movie.movieId" :label="movie.movieName">
        <movie-detail :movie="movie"></movie-detail>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import AddMovie from "./children/movie/addMovie";
import movieList from "./children/movie/movieList";
import movieDetail from "./children/movie/movieDetail";
export default {
  name: "movieManage",
  components: {
    AddMovie,
    movieList,
    movieDetail
  },
  data() {
    return{
      tabName: 'movieList',
      movieList:[]
    }
  },
  methods: {
    addNewTab(movie) {
      this.movieList.push(movie);
      this.tabName = movie.movieName
    },
    removeTab(tabName) {
      for(let index in this.movieList) {
        if (this.movieList[index].movieName === tabName) {
          this.movieList.splice(index, 1)
          if (this.movieList.length > 0) {
            if (this.tabName === tabName)
              this.tabName = this.movieList[index-1].movieName
          } else {
            this.tabName = 'movieList'
          }
        }
      }

    }
  }
}
</script>

<style scoped>

</style>
