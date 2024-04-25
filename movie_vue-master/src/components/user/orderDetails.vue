<template>
  <div style="padding-top: 100px;width: 1500px">
    <div style="width: 1200px;margin: 0 auto 0">
      <div class="status-box finished">
        <p class="title" style="margin: 0 auto 0;float: left">已完成</p>
      </div>
      <div>
        <div>
          <span style="float: left;font: 18px large">天天订单号: {{ this.orderId }}</span>
        </div>
      </div>
      <div style="margin-top: 80px">
        <el-table
          :data="orderDetails"
          stripe
          :cell-style="{ textAlign: 'center' }"
          :header-cell-style="{textAlign: 'center'}"
          style="width: 1200px">
          <el-table-column
          type="index"
          label="序号"
          width="240">
          </el-table-column>
          <el-table-column
            prop="movieName"
            label="影片名"
            width="240">
          </el-table-column>
          <el-table-column
            prop="dateStart"
            label="时间"
            width="240">
          </el-table-column>
          <el-table-column
            prop="cinemaName"
            label="影院"
            width="240">
          </el-table-column>
          <el-table-column
            label="座位号"
            width="240">
            <template slot-scope="scope">
              <span> {{ parseInt( scope.row.seatNo/12+1 ) }}排{{ scope.row.seatNo%12+1 }}座</span>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="margin-top: 100px;margin-left: 800px">
        <span style="font-size: 14px;color: #333;">
          总价:
        </span>
        <span style="font-size: 36px;color: #f03d37;font-weight: 700;">
          {{this.orderDetails.length * this.orderDetails[0].price}}
        </span>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "orderDetails",
  props: ['orderId'],
  data() {
    return {
      orderDetails: [
        {price: 0,}
      ],
    }
  },
  mounted() {
    this.$axios({
      url: '/order/getOrderDetails',
      params: {orderId: this.orderId}
    }).then(res => {
      console.log(res.data.data);
      this.orderDetails = res.data.data;
    })
  }
}
</script>

<style scoped>
.status-box.finished {
  padding-top: 40px;
  background-color: #f4fff8;
  background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAAAXNSR0IArs4c6QAACR9JREFUWAmtWV1sHFcVPnd2/Rs3IQldR9umLbELokqpRF544YFSAjyFVlg8gBQBIjT9eWjsCAKlcVBB/YmrBKUhUBDqI0pFxBNShSD0PUKofQHcqqTpetdu0iR1mrV3d4bzfeee2dm1pZSIa82de8/Pd75z7p3ZmXGQm2xfbTw50e509gRJd2WpVDPJqhJCVbJM9FyTLNVDaiEk58oDA3/80/jTb95MqPC/OO2uz1ZC2nxMKTyYpelO99U5G8A4VpLGU+cYaEtCeEOJnwnl9MSr244uUvgRuo9EcGpxduxKpzmt0WeyNBuT6OXBSSwSAZ1+ULczPtlyCOHo5qGxudOV2eUbcezHWmP/ldqhh1LJTmqQ8UJZcjuvHgUFkrBVInkFoQdRl2ngRkjkkT9vn/tDDrbOIFlHRpGChd0LPzzckfQVkLNMuvmAS06OE5uRhCI4kSJ+15tkx9NUXrn/PzOHEatoVxyvq5jKXhi5urD4cpplUzBgaJCIDaMeeayUgxlJrZ6noA4+pp/O1aWLG5LTW5Pq3tPbD1z3GH5eU0Fk00POiQGx0EiXOkTSpVMdiOFAc0LQQYmK5g0uNDJbveKnLqYXXkbs3CYO1hD8cv3QU4o5Rf+CdR4YAfVwpJxIwdZ1BVGXePR3Hac60VvV1BfPTz/lcj/3YOGCwJ7T9HI5iPkk5mu+QO5v4I7aQBWdPDFMix6O6zLTAzT5+l/v6l44HltwK7ncbs4HXK0AIwHbRzk4ZNw8Dmt2610QRQL0p4v5u64Yx+mrbWPrhk2TfgvKl9juc9k4cIjFPRP3FpHiPipUzpKAsq+RB5KzQhIPs3WSo2deJpE0k/Gla5enHZEq/EJI2nxTfx30JqyiAgkYkkiUe1AHyPXRrr+aCGCJcNRL2pjDAjC0i/7Lg6PlCfziWAXTlccVxMjRtNv1Vin41opBPTjQUS3my95GBX0XUiqlzXJs/FF5qTotm0tj1PTGkbFWM30MirjE2ddyf60e8uER9xxvEZDHyuKcV8qSt7VUVtDBjGLaqzBuF+huLW2SF8b3y2eGd8jEYFXuG5pgaOB1MRUjTR+EIvCppN2aJypNezvGsHC5wolSACaRGOaonHPGHLjRRLYkG+XYtkfltoGPU3WhtST73p2TpqwyMQoLXVIemCzjkcnIFa5YY1Uwtcp4hn6mX9ybPcSivxOF7mPJmMxt25+Tq7UuyhMLL0ozU3K9KTEpBM9arT0JnuewBDDyICDAWvAM09g0IvaZPtGYINfbFQshNVEOWyBtSjbInC7rHQMV+tVbl2S6flIuplctTO4YAXLcbFc5S4M+bKYEgh1JegU1mi1nYX+4Ec7rVIoEVY4zaN8SRuTo+MNy1+A2eMhS+7IcaJyUevt9ndGa8iKBfAvpA3A5C1k1gAjMnJi5sPflhMor7GqHdzmAIcMcx1gyIs8ruR16MaC9174iT2jl6u1LvG56wqmjL7Xj6bmqSyz0hrDYPDiCfn7kXvnWxgdkg1YDjRmq3BYwJhedHXxUhuW5yvfl7sHbqbnU+UAO1F+UWvs9+gOjJ6ZOMHd/FEtttII2yuGRRW6oRtg/T279ppRCiURnlk7JB50PreKxB4Q3DEeTIXl2fJ98amg7xZc7y9xzF5RcsbkXE1YFyQFA96CtnGSJxliwiwKu8UIpBFxOr8tS5wpxcd967tZ9skGXDmBsEdCDjSSD8kzle3LP0J1UX+lck5n6L+Xt1ToqgvJHx3jSuceHhmOVYazbb0Fv1KHmGVConbHHTESfbuRHS7+V93WJ0LBkIDkahi0YrjgEUd2gDMjPK9+VncOfoC0qPVM/JW+1FnISik4dOsYt+APHuBhmkoWaVlBfD73FQD718/n2okwvnhIsFRqW7lmt0mgYIiBSKevfzyrfkfuGJ2mDyh9snJL51Xc5z4ugD34sj0pBFXIWhlbdjrIgNX0bTM65mJVTcweDnGMFAckZJYklQ/v00J3yjO4zkByUkjxd+bZ8duST1H2YNuUHjV/Lv1Yv6Nzw/ILSUpKZxehWkyPozIPkEwnn+FPXWl2Zd1JmoncwJQUrnCFDRjrR38/b5GjlYdlYGoVEXm++JdeU0OdG7+H8erpCcpAXtwqVxDRsi2P4JK06xCjKy0PDk5x/6Z2Dr+sLUv4iTjB0AFQXczVnSO4eqOrNdz/vczDz1kxX5dDiS/KPpn1E6EnOjRQAeEya+KqI+xAmwEfDi/7ZieP32tMM3vhpZEoA8+AUiObm8n/rvjrY+JVgn3lbTVvy48ZvSM7tXIc5G3AjBUq43Eq1gM+KwS7LzsCHBEN59ISKlr2+2C8gbFkaNnsAxvbPlfNcyovtq7LcuS4/Wfqd/H1l3tX0xwTkcq/o7yRo7OQw6eqXdWefoIhG2j3wDl6gZTbPFgo4OykdI2sjrUFVDtuS5ljWm/hK1oIHZRzYZI0/dY5bxFSFY+v6zr428YsjsI0PrCL4VqLoDWYXjbnssAJgTwM5E+A+CXIg25Mc1DG5otzxcwDHjf4as5HcsmXOxTlBvEXhW4mCMjQ6ApNJlxBkFtcqCiCYeDIkQGfzp71j+TknDu8YB/VDmJA8crbwUSknCEN8yNF3+yMYs0UghdApDm0q84p4NWDmMlpBoYeTpp93RX/YQM446hnCkdcmjvV8TKLefXHWQEHf8H+vfOzrAiJGICfh9piDhMsBRoIqj26mIwGCd+UAQelJERdSOH128vg3FI8QUKP1VBACGGxNbt+rHqdtrj0CEmtNPiTgUhA1si4hnhHJyQDVaNnIyFWGS3v7yfXamXXeayBUUr/TZIcRxhhGSA2GyqEZIZPH8tkpiugHW5BXGbzyJPAsJXLkbzuO/3Q9coDoporZOu0Lb08/FLL0pFLSTyLA8wBdYwQkEPMwGxIx465hHNFer1ZcEP17rt94zRL3G+BDDr6V6HvLrALnn2ztNq7WygfVJC0nGqvFShUqzbnIMu5zYeOWyRuRA5cbVrBIeHd9ptJuZo+naQcv+ju7y2dWtojRQ0mioUcQTeINJXgGvxCvTv6fP6IjUH+7//zBiSzt7Ek7nV0aXN8MpRrSFP+K0CfhUAtpVtNblj5rhnMl/TfEX+54/qb+DfFfYuhJKyMlqbgAAAAASUVORK5CYII=);
}

.status-box {
  height: 110px;
  padding-left: 85px;
  padding-top: 28px;
  margin-bottom: 40px;
  background-repeat: no-repeat;
  background-position: 30px;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

.status-box.finished .title {
  color: #26ce61;
}

.status-box .title {
  font-size: 24px;
  font-weight: 700;
}

</style>
