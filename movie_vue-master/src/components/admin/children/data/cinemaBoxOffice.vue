<template>
  <div>
    <div id="echart2" :style="{width: '1250px', height: '450px'}"></div>
    <div class="block">
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page.sync="currentPage"
        :page-size="pageSize"
        layout="prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "cinemaBoxOffice",
  data(){
    return{
      currentPage: 1,
      pageSize: 5,
      total: 5,

      option : {
        title:{
          text: '影院票房',
        },
        tooltip: {
          trigger: 'item',
          formatter: '{b}: {c} 元'
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [
              // {
              //   value: 20000,
              //   itemStyle:{
              //     color: '#a90000'
              //   }
              // },
            ],
            type: 'bar',
            showBackground: true,
            name: '影院票房',
          }
        ]
      }
    }
  },
  methods: {
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getData();
    },
    getData() {
      this.$axios({
        url: '/scenes/getTotalPrice',
      }).then(res=>{
        var arrx = new Array();
        var arry = new Array();

        this.total = res.data.data.length;  //将数据总条数传给页面显示总条目数

        for (let i = 0; i < res.data.data.length; i++) {
          arrx.push(res.data.data[i].cinemaName);
          arry.push(res.data.data[i].totalPrice);
        }

        while (this.option.xAxis.data.length !== 0) {
          this.option.xAxis.data.pop();
          this.option.series[0].data.pop();
        }

        for(let i = this.pageSize * (this.currentPage - 1);i < (this.pageSize * (this.currentPage - 1) + 5); i++){
          this.option.xAxis.data.push(arrx[i]);
          this.option.series[0].data.push(arry[i]);
        }

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('echart2'));
        // 绘制图表
        myChart.setOption(this.option);

        let currentIndex = -1;
        var that = this;
        var id = setInterval(function() {
          var dataLen = that.option.series[0].data.length;
          // 取消之前高亮的图形
          myChart.dispatchAction({
            type: 'downplay',
            seriesIndex: 0,
            dataIndex: currentIndex
          });
          currentIndex = (currentIndex + 1) % dataLen;
          // 高亮当前图形
          myChart.dispatchAction({
            type: 'highlight',
            seriesIndex: 0,
            dataIndex: currentIndex
          });
          // 显示 tooltip
          myChart.dispatchAction({
            type: 'showTip',
            seriesIndex: 0,
            dataIndex: currentIndex
          });
        }, 1000);
        clearInterval(id);
      })
    }
  },
  mounted() {
    this.getData();
  },
}
</script>

<style scoped>

</style>
