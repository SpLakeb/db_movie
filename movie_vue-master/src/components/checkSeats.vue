<template>

  <div style="margin-top: 150px">

    <div style="margin: 0 auto;width: 100%;">

      <el-steps :active="1" align-center finish-status="success">
        <el-step title="选择电影场次"></el-step>
        <el-step title="选择座位"></el-step>
        <el-step title="支付订单"></el-step>
        <el-step title="支付成功，等待观影"></el-step>
      </el-steps>
    </div>

    <div style="width: 80%;height: 600px;margin: 0 auto;margin-top: 50px;border: 1px solid #e7e5e5">

      <el-row>

        <el-col :span="15" :offset="1">

          <div style="margin-top: 30px">

            <el-row>
              <el-col :span="4" :offset="2">
                <img src="/static/座位红.png" class="seat"/>
                &nbsp;&nbsp;&nbsp;
                <span style="font-size: 18px;color: #67696b">已售座位</span>
              </el-col>
              <el-col :span="4" :offset="1">
                <img src="/static/座位绿.png" class="seat"/>
                &nbsp;&nbsp;&nbsp;
                <span style="font-size: 18px;color: #67696b">已选座位</span>
              </el-col>
              <el-col :span="4" :offset="1">
                <img src="/static/座位白.png" class="seat"/>
                &nbsp;&nbsp;&nbsp;
                <span style="font-size: 18px;color: #67696b">可选座位</span>
              </el-col>
            </el-row>

          </div>

          <div style="width: 80%;margin-top: 50px">

<!--            画荧幕中央-->
            <div style="margin-left: 50px">
              <div style="height: 10px;background: #eaecee;width: 80%;margin: 0 auto">
              </div>
              <div style="height: 10px;background: #eaecee;margin-top: 5px">
              </div>
              <div>
                <span style="font-size: 20px;color: #99a9bf">荧幕中央</span>
              </div>
            </div>

            <div style="margin-top: 30px;
            height: 350px;overflow: auto">

              <el-row>
                <el-col :span="2">

                  <div style="height: 25px;width: 25px;margin-bottom: 4px"
                       v-for="(item,index) in parseInt((seats.length-1)/12)+1" :key="index+'a'">
                    <span style="line-height: 25px">{{ item }}</span>
                  </div>
                </el-col>

                <el-col :span="21" :offset="1">
                  <el-row class="el-row" >
                    <el-col :span="2" v-for="(item,index) in seats" :key="index">
                      <img src="/static/座位红.png" v-if="item.sale" class="seat"/>
                      <img :src="color(item.checked)" @click="choose(index)" v-if="!item.sale" class="seat"/>
                    </el-col>
                  </el-row>
                </el-col>
              </el-row>

            </div>

          </div>

        </el-col>

        <el-col :span="7" :offset="1" style="background: #efefef;padding: 20px">
          <div>

            <el-row>

              <el-col :span="10">
                <el-image
                  :src="map.movie.imgUrl" lazy
                style="width: 120px;border: 5px solid white">
                </el-image>
              </el-col>

              <el-col :span="13" :offset="1">

                <div style="margin-top: 30px">
                  <div>
                    <span style="font-size: 20px; font-weight: bolder">
                      {{ map.movie.movieName }}</span>
                  </div>
                  <div style="text-align: left;margin-left: 20px">
                    <span style="color: #898b8d;font-size: 10px">类型：</span>
                    <span style="font-size: 10px" v-for="(item,index) in map.types"
                          :key="index+'b'">{{ item.typeName }}&nbsp;</span>
                  </div>
                  <div style="text-align: left;margin-left: 20px">
                    <span style="color: #898b8d;font-size: 10px">时长：</span>
                    <span style="font-size: 10px">{{ map.movie.duration }}分钟</span>
                  </div>
                </div>

              </el-col>

            </el-row>

            <div style="margin-top: 20px;text-align: left">
              <div style="margin-bottom: 3px">
                <span style="font-size: 15px;color: #898b8d">影院 : </span>
                <span style="font-size: 15px">{{ map.cinema.cinemaName }}</span>
              </div>

              <div style="margin-bottom: 3px">
                <span style="font-size: 15px;color: #898b8d">影厅 : </span>
                <span style="font-size: 15px">{{ map.hall.hallName }}</span>
              </div>

              <div style="margin-bottom: 3px">
                <span style="font-size: 15px;color: #898b8d">版本 : </span>
                <span style="font-size: 15px">{{ map.hall.type }}</span>
              </div>

              <div style="margin-bottom: 3px">
                <span style="font-size: 15px;color: #898b8d">场次 : </span>
                <span style="font-size: 15px;color: red">
                  {{ $format(map.scenes.dateStart,'MM月DD日 HH:mm') }}</span>
              </div>

              <div style="margin-bottom: 3px">
                <span style="font-size: 15px;color: #898b8d">票价 : </span>
                <span style="font-size: 15px;color: red">￥{{ map.scenes.price }}/张</span>
              </div>

              <el-divider/>

              <div v-if="seatsChecked.length === 0">
                <span>请</span>
                <span style="color: red">点击左侧</span>
                <span>座位图选择座位</span>
              </div>
            </div>

            <div v-if="seatsChecked.length !== 0" style="margin-top: 20px" class="el-row">

              <div style="width: 100%;text-align: left;margin-left: 10px">
                <span>座位 : </span>
              </div>
              <div style="width: 66px;height: 33px;margin: 10px 10px 10px 0"
                   class="ticket" v-for="(item,index) in seatsChecked" :key="index">
                <span style="color: red;line-height: 33px;font-size: 10px">
                  {{ parseInt( item/12+1 ) }}排{{ item%12+1 }}座
                </span>
              </div>

            </div>

            <div style="margin-top: 30px;text-align: left">
              <span>总价 : </span>
              <span style="color: red"> ￥ </span>
              <span style="font-size: 30px;color: red">
                {{ seatsChecked.length * map.scenes.price }}</span>
            </div>

            <div style="width: 200px;margin: 20px auto 0 auto">

              <el-popconfirm title="确定选择这些座位？" @confirm="submit">
                <el-button type="danger" style="width: 200px;
                border-radius: 20px"
                slot="reference" :disabled="seatsChecked.length === 0">确认选座</el-button>
              </el-popconfirm>

            </div>


          </div>
        </el-col>

      </el-row>

    </div>
  </div>

</template>

<script>

export default {
  name: "checkSeat",
  props: ['scenesId'],
  data() {
    return {
      map: null,
      seatsChecked: [],
      seats: []
    }
  },
  methods: {
    color(checked) {
      if (checked === true) {
        return '/static/座位绿.png'
      } else {
        return '/static/座位白.png'
      }
    },
    choose(id) {
      this.seats[id].checked = !this.seats[id].checked
      if (this.seats[id].checked === true){
        this.seatsChecked.push(id)
      }else {
        //移除id
        let index;
        for (let i = 0; i < this.seatsChecked.length; i++) {
          if (this.seatsChecked[i] === id){
            index = i;
            this.seatsChecked.splice(index,1)
            break;
          }
        }
      }
    },
    submit() {

      //保存订单10分钟
      this.$axios({
        url: '/order/pushOrderToRedis',
        method: "post",
        data: {
          seatsNo: this.seatsChecked,
          scenesId: this.scenesId
        }
      }).then(()=>{
        this.$router.push('/checkOut')
      })
    }
  },
  mounted() {

    this.$axios({
      url: '/user/myInfo'
    }).then(res => {
      if (res.data.flag === false){
        this.$router.push('/userLogin')
      }else {

        this.$axios({
          url: '/order/hasOrderInRedis',
          params: {scenesId: this.scenesId}
        }).then(res => {
          if (res.data.flag){
            this.$router.push('/userHomepage/ordersNoHandle')
            this.$message({
              type: "error",
              message: '您有未支付的订单，请先处理'
            })
          }else {
            //已经登录请求数据
            this.$axios({
              url: '/scenes/getScenesInfo',
              params: {scenesId: this.scenesId}
            }).then(res => {
              this.map = res.data.data

              for (let i = 0; i < this.map.hall.seats; i++) {
                this.seats.push({checked: false, sale: false})
              }

              this.$axios({
                url: '/ticket/seatsHasSale',
                params: {scenesId: this.scenesId}
              }).then(res => {
                res.data.forEach(value => {
                  this.seats[value].sale = true
                })
              })
            })
          }
        })
      }
    })

  }
}
</script>

<style scoped>

.el-row {
  display: flex;
  flex-wrap: wrap;
}

.seat {
  width: 25px;
  height: 25px;
}

.el-divider{
  color: #e3e4e5;
}

.ticket{
  background: url("/static/电影票.png");
  background-size: 100% 100%;
}
</style>
