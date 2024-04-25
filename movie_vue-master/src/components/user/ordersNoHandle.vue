<template>
  <div style="margin-bottom: 100px">
    <el-row style="margin-top: 30px;width: 1027px;" v-if="flag">
      <el-col
        style="border: 1px solid #e5e5e5;margin: 0 40px 30px 0;padding: 16px 20px;height: 188px;width: 1000px;color: black;background-color: #f1eac8">
        <el-col style="height: 50px;width: 950px;text-align: left">

          <span style="color: red;font-size: 20px">{{ parseInt(seconds/60) }}</span>
          <span>分</span>
          <span style="color: red;font-size: 20px">{{ seconds%60 }}</span>
          <span>秒后订单将被自动取消</span>

            <el-popconfirm title="确认要取消此订单吗?" @confirm="cancelOrder">
              <el-button slot="reference" style="border: none;margin-left: 600px;
              background-color:#f1eac8;cursor: pointer;display: inline-block" class="el-icon-delete">取消订单</el-button>
            </el-popconfirm>

        </el-col>
        <div style="width:100%">
          <el-col :span="8">
            <el-image :src="map.movie.imgUrl"
                      style="width: 66px;height: 91px;float: left"></el-image>
            <span>《{{ map.movie.movieName }}》</span><br>
            <span style="color: #999999">{{ map.cinema.cinemaName }}</span><br>
            <span style="color: #999999">{{ map.hall.hallName }}</span><br>
            <span style="color: #999999">{{ $format(map.scenes.dateStart, 'YYYY年MM月DD日 HH:mm')
              }}</span><br>
          </el-col>
        </div>
        <el-col :span="4" :offset="2" style="margin-top: 30px">
          <span>￥ {{ map.scenes.price }}</span>
        </el-col>
        <el-col :span="10" style="margin-top: 18px">
          <el-button round @click="$router.push('/checkOut')">去支付</el-button>
        </el-col>
      </el-col>
    </el-row>

    <div v-if="!flag">
      <el-empty/>
    </div>
  </div>
</template>

<script>
export default {
  name: "ordersNoHandle",

  data() {
    return {
      flag: false,
      map: null,
      seconds: 0,
      countDown: null
    }
  },
  mounted() {

    this.getOrderNoHandle()

    this.$axios({
      url: '/order/ttl'
    }).then(res => {
      if (res.data.flag){
        this.seconds = res.data.data

        this.countDown = setInterval(()=>{
          this.seconds -- ;
          if (this.seconds === 0){
            clearInterval(this.countDown)
            this.flag = false
          }
        },1000)
      }
    })
  },
  methods: {

    getOrderNoHandle(){
      this.$axios({
        url: '/order/orderNoHandle',
      }).then(res => {
        this.flag = res.data.flag
        if (res.data.flag){
          this.map = res.data.data;
          console.log(this.map)
        }
      })
    },
    cancelOrder(){

      this.$axios({
        url: '/order/cancel'
      }).then(() => this.flag = false)
    }
  }
}
</script>

<style scoped>

</style>
