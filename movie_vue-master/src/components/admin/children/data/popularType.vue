<template>
  <div>
    <div id="echart3" :style="{width: '1250px', height: '450px'}"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "userInfo",
  data() {
    return {
      option: {
        title: {
          text: '票房前十电影类型',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{b} : {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          // data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
          data: []
        },
        series: [
          {
            // name: '访问来源',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            // data: [
            //   { value: 335, name: '直接访问' },
            //   { value: 310, name: '邮件营销' },
            //   { value: 234, name: '联盟广告' },
            //   { value: 135, name: '视频广告' },
            //   { value: 1548, name: '搜索引擎' }
            // ],
            data: [{name:'',value:''}],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      },
    }
  },
  methods: {
    getData3() {
      this.$axios({
        url:'/type/getTypeNameCount',
      }).then(res=>{
        for (let i = 0; i < res.data.data.length; i++) {
          this.option.legend.data.push(res.data.data[i].typeName)
          this.option.series[0].data.push({name:res.data.data[i].typeName,value: res.data.data[i].count})
        }

        var myChart = echarts.init(document.getElementById('echart3'));
        // 绘制图表
        myChart.setOption(this.option);

        let currentIndex = -1;
        var that = this;

        setInterval(function() {
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
      })

    },
  },
  mounted() {
    this.getData3();
  }

}
</script>

<style scoped>

</style>
