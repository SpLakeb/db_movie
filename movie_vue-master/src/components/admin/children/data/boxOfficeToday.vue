<template>
  <div>
    <div id="echart1" :style="{width: '1250px', height: '400px'}"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: 'boxOfficeToday',
  data() {
    return {
      option: {
        xAxis: {
          type: 'category',
          data: [],
          axisLabel: {
            interval: 0,
            formatter:function(value)
            {
              return value.split("").join("\n");
            }
          }
        },
        yAxis: {
          type: 'value'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{b}: {c} 元'
        },
        series: [
          {
            data: [],
            type: 'bar',
            itemStyle: {
              normal:{
                //每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                color: function (params){
                  var colorList = [
                    'rgb(82,202,252)', 'rgb(82,252,132)','rgb(238,102,102)','rgb(102,238,170)',
                    'rgb(88,138,250)','rgb(250,200,88)', 'rgb(200,88,250)','rgb(84,112,198)',
                    'rgb(219,250,88)','rgb(250,88,138)'
                  ];
                  return colorList[params.dataIndex];
                }
              },
              //鼠标悬停时：
              emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 5,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
    }
  },
  methods: {
    getDatas() {
      this.$axios({
        url: '/scenes/getTotalPrice',
      }).then(res => {
        var arrx = new Array();
        var arry = new Array();

        for (let i = 0; i < res.data.data.length; i++) {
          arrx.push(res.data.data[i].cinemaName);
          arry.push(res.data.data[i].totalPrice);
        }

        this.option.xAxis.data = arrx;
        this.option.series[0].data = arry;


        // while (this.option.xAxis.data.length !== 0) {
        //   this.option.xAxis.data.pop();
        //   this.option.series[0].data.pop();
        // }

        // 基于准备好的dom，初始化echarts实例
        var myCharts = echarts.init(document.getElementById('echart1'));

        // 绘制图表
        myCharts.setOption(this.option);

      })
    }
  },
  mounted() {
    this.getDatas();
  },
}

</script>

<style scoped>

</style>
