<template>

  <div style="margin-top: 150px">

    <div style="margin: 0 auto;width: 100%;">

      <el-steps :active="2" align-center finish-status="success">
        <el-step title="选择电影场次"></el-step>
        <el-step title="选择座位"></el-step>
        <el-step title="支付订单"></el-step>
        <el-step title="支付成功，等待观影"></el-step>
      </el-steps>
    </div>

    <div
      style="background: rgb(255 7 102 / 20%);height: 100px;text-align: left;width: 80%;
      margin: 50px auto 0 auto">
      <span style="font-size: 20px;line-height: 70px;margin-left: 50px">
        <span>请在</span>
        <span style="color: red;font-size: 30px">{{ parseInt(seconds/60) }}</span>
        <span>分</span>
        <span style="color: red;font-size: 30px">{{ seconds%60 }}</span>
        <span>秒内付款</span>
      </span>

      <div style="margin-left: 50px">
        <span>如遇支付问题，请联系天天客服</span>
        <span style="color: red">2488689182@qq.com</span>
      </div>

    </div>

    <div style="width: 80%;margin: 50px auto 0 auto">

      <el-descriptions direction="vertical" :column="4" border>
        <el-descriptions-item label="影片">{{ map.movie.movieName }}</el-descriptions-item>
        <el-descriptions-item label="时间">
          <span style="color: red">{{ $format(map.scenes.dateStart,'MM月DD HH:mm') }}</span></el-descriptions-item>
        <el-descriptions-item label="影院" :span="2">
          {{ map.cinema.cinemaName }}</el-descriptions-item>
        <el-descriptions-item label="影厅">
          {{ map.hall.hallName }}
        </el-descriptions-item>
        <el-descriptions-item label="座位" style="display: flex;flex-wrap: wrap">
          <el-tag size="small" type="danger" v-for="item in seatsNo" :key="item"
                  style="margin: 5px">
            {{ parseInt(item/12+1) }}排{{ item%12+1 }}座
          </el-tag>
        </el-descriptions-item>
      </el-descriptions>
    </div>

    <div style="float: right;margin-top: 50px">
      <div style="margin-right: 50px">
        <span style="font-size: 20px">实际支付 : </span>
        <span style="font-size: 30px;color: red">￥{{ seatsNo.length * map.scenes.price }}</span>
      </div>

      <el-button type="danger" @click="checkOut" style="width: 200px">确认支付
      </el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "checkOut",
  data(){
    return{
      seconds: 0,
      map: null,
      seatsNo: []
    }
  },
  mounted() {
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

    //请求场次信息
    this.$axios({
      url: '/order/orderNoHandle',
    }).then(res => this.map = res.data.data)

    //请求座位
    this.$axios({
      url: '/order/seatsNo',
    }).then(res => this.seatsNo = res.data.data)
  },

  methods: {
    uuid() {
      var  s = [];
      var  hexDigits =  "0123456789abcdef" ;
      for  ( var  i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
      }
      s[14] =  "4" ;   // bits 12-15 of the time_hi_and_version field to 0010
      s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);   // bits 6-7 of the clock_seq_hi_and_reserved to 01
      s[8] = s[13] = s[18] = s[23] =  "-" ;

      var  uuid = s.join( "" );
      return  uuid.substring(0,30);
    },

    checkOut(){

      let orderId = this.uuid().replaceAll("-","");

      this.$axios({
        url: '/order/addOrder',
        method: "post",
        data: {scenesId: this.map.scenes.scenesId, seatsNo: this.seatsNo,orderId : orderId}
      }).then(res => {
        if (!res.data){
          this.$message.error('订单发生冲突，请重新购买！')
          this.$router.push('/checkSeats/'+this.map.scenes.scenesId)
        }else {

          //支付
          this.$axios.post('/alipay/pay',{
            out_trade_no: orderId,
            subject: '支付',
            total_amount: (this.seatsNo.length * this.map.scenes.price).toFixed(2),
            body: '天天电影票支付'
          }).then(res => {
            // 添加之前先删除一下，如果单页面，页面不刷新，添加进去的内容会一直保留在页面中，二次调用form表单会出错
            const divForm = document.getElementsByTagName('div')
            if (divForm.length) {
              document.body.removeChild(divForm[0])
            }
            const div = document.createElement('div')
            div.innerHTML = res.data // data就是接口返回的form 表单字符串
            document.body.appendChild(div)
            document.forms[0].setAttribute('target', '_blank') // 新开窗口跳转
            document.forms[0].submit()
          })

          let formData = new FormData();
          formData.append("up",this.seatsNo.length * this.map.scenes.price+'')
          formData.append("movieId",this.map.movie.movieId)
          this.$axios.put('/movie/upBoxOffice',formData)

        }
      })

      this.$axios({
        url: '/order/cancel'
      })

    }
  }

}
</script>

<style scoped>

</style>
