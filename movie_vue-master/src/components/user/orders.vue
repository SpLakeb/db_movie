<template>
  <div style="margin-bottom: 100px">
    <el-row v-for="(order,index) in this.ordersInfo" :key="index" style="margin-top: 30px;width: 1027px;">
      <el-col
        style="border: 1px solid #e5e5e5;margin: 0 40px 30px 0;padding: 16px 20px;height: 188px;width: 1000px;color: black;background-color: #f7f7f7">
        <el-col style="height: 50px;width: 950px">
          <div style="text-align: left">
            <el-row>
              <el-col :span="16">
                  <span style="color: #333;font-size: 14px">
              {{ $format(order.orderDate, 'YYYY-MM-DD')}}</span>
                <span style="margin-left: 50px;color: #999;font-size: 14px">
              订单号: {{ order.orderId }}</span>
              </el-col>

              <el-col :span="3" :offset="5">
                <el-popconfirm title="确认要删除此订单吗?" @confirm="handleConfirm(order.orderId)">
                  <el-button slot="reference" style="border: none;
              background-color: #f7f7f7;cursor: pointer"
                             class="el-icon-delete">删除</el-button>
                </el-popconfirm>
              </el-col>

            </el-row>

          </div>
        </el-col>
        <div style="width:100%">
          <el-col :span="8">
            <el-image :src="order.imgUrl" style="width: 66px;height: 91px;float: left;cursor: pointer"
                      @click="movieDetails(order.movieId)"></el-image>
            <span>《{{ order.movieName }}》</span><br>
            <span style="color: #999999">{{ order.cinemaName }}</span><br>
            <span style="color: #999999">{{ order.hallName }}</span><br>
            <span style="color: #999999">{{ $format(order.dateStart, 'YYYY年MM月DD日 HH:mm') }}</span><br>
          </el-col>
        </div>
        <el-col :span="4" :offset="2" style="margin-top: 30px">
          <span>￥ {{ order.price }}</span>
        </el-col>
        <el-col :span="10" style="margin-top: 18px">
          <el-button round @click="handleClick(order.orderId)">查看详情</el-button>
        </el-col>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "orders",

  data() {
    return {
      ordersInfo: {},
    }
  },
  mounted() {
    this.getOrdersInfo();
  }
  , methods: {
    getOrdersInfo() {
      this.$axios({
        url: '/order/getOrders'
      }).then(res => {
        console.log(res.data.data)
        this.ordersInfo = res.data.data
      });
    },
    handleConfirm(value){
      this.$axios({
        url: '/order/deleteOrder',
        params: {orderId: value}
      }).then(res =>{
        if (res.data.flag) {
          const h = this.$createElement;
          this.$notify({
            title: '删除成功',
            message: h('i', {style: 'color: teal'}, '删除成功!  '),
            type: 'success'
          });
          location.reload();
        }else{
          const h = this.$createElement;
          this.$notify({
            title: '错误提示',
            message: h('i', {style: 'color: teal'}, '删除失败!  '),
            type: 'warning'
          });
        }
      })
    },
    handleClick(orderId){
      this.$router.push('/orderDetails/' + orderId);
      location.reload()
    },
    movieDetails(id){
      this.$router.push('/movieDetails/'+id)
    }
  }
}
</script>

<style scoped>

</style>
