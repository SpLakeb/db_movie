<template>
  <div ref="mainPage" id="mainPage">
    <div ref="echarts-dom" id="echarts-dom"></div>
    <el-row>
      <el-col :span="12">
        <div id="echart3" :style="{width: '600px', height: '450px'}"></div>
      </el-col>
      <el-col :span="12">
        <div>
          <div id="echart1" :style="{width: '600px', height: '400px'}"></div>
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import * as echarts from "echarts";
var elementResizeDetectorMaker = require("element-resize-detector");
export default {
  name: "total",

  data() {
    return{
      chartWidth: 0,
      chartHeight: 0,
      movies: [],
      rawData: [],
      myEcharts: {},
      url: 'https://cdn.jsdelivr.net/gh/apache/echarts-website@asf-site/examples/data/asset/data/life-expectancy-table.json',

      option1: {
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
      option: {
        title:{
          text: '影院票房',
          left: 'center',
        },
        xAxis: {
          type: 'category',
          data: [],
          axisLabel: {
            interval:0,
            rotate:40
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
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }
    }
  },
  mounted() {
    this.getDatas()
    this.getData3();
    //后台获取数据
    this.getMovies()
    this.getOffsetSize();
    this.createEcharts();
  },
  methods: {
    getDatas() {
      this.$axios({
        url: '/scenes/getTotalPrice',
      }).then(res => {
        var arrx = new Array();
        var arry = new Array();

        for (let i = 0; i < res.data.data.length; i++) {
          if(res.data.data[i].cinemaName.length > 4){
            res.data.data[i].cinemaName = res.data.data[i].cinemaName.substring(0,4)
            res.data.data[i].cinemaName += '...'
          }
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
    },
    getData3() {
      this.$axios({
        url:'/type/getTypeNameCount',
      }).then(res=>{
        for (let i = 0; i < res.data.data.length; i++) {
          this.option1.legend.data.push(res.data.data[i].typeName)
          this.option1.series[0].data.push({name:res.data.data[i].typeName,value: res.data.data[i].count})
        }

        var myChart = echarts.init(document.getElementById('echart3'));
        // 绘制图表
        myChart.setOption(this.option1);

        let currentIndex = -1;
        var that = this;

        setInterval(function() {
          var dataLen = that.option1.series[0].data.length;
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
    getMovies() {
      this.axios.get("/movie/getDailyBoxOffice").then((res) => {
        this.rawData = res.data.data;
        this.operateData(this.rawData)
      })
    },
    getJSON(url) {
      return new Promise(function(resolve, reject) {
        var xhr = new XMLHttpRequest();
        xhr.open('get', url, true);
        xhr.responseType = 'json';
        xhr.onload = function() {
          var status = xhr.status;
          if (status === 200) {
            resolve(xhr.response);
          } else {
            reject(status);
          }
        };
        xhr.send();
      });
    },

    createEcharts() {
      let chartDom = this.$refs['echarts-dom'];
      this.myEcharts = echarts.init(chartDom, '', {
        width: this.chartWidth,
        height: this.chartHeight
      })
      //配置信息
      let option = {}
      this.myEcharts.setOption(option)
      //创建页面元素大小变化绑定事件
      let erd = elementResizeDetectorMaker()
      erd.listenTo(this.$refs['mainPage'], () => {
        //获取DOM的大小
        this.getOffsetSize()
        this.myEcharts.resize({
          width: this.chartWidth,
          height: this.chartHeight
        })
      })
    },
    //获取变化后的页面大小
    getOffsetSize() {
      this.chartWidth = this.$refs['mainPage'].offsetWidth
      this.chartHeight = 500
    },
    //数据处理
    operateData(_rawData) {
      const movies = []

      this.rawData.forEach(value => {
        let flag = true;
        movies.forEach(v => {
          if (v === value.movieName) {
            flag = false
          }
        })
        if (flag) {
          movies.push(value.movieName);
        }
      })
      console.log(movies)
      //过滤数据
      const datasetWithFilters = []
      //折线图集合
      const seriesList = []
      //根据分类信息遍历生成线条数据
      movies.forEach((movie) => {
        let datasetId = 'dataset_' + movie
        //1.过滤数据操作
        datasetWithFilters.push({
          id: datasetId,
          fromDatasetId: 'dataset_raw',
          //数据变化
          transform: {
            //过滤
            type: 'filter',
            //配置
            config: {
              and: [
                //dimension指定维度，设置成这样的值 gte 大于等于

                {dimension: 'movieName', '=': movie}
              ]
            }
          }
        })
        //2.生成线条
        seriesList.push({
          type: 'line',
          datasetId: datasetId,
          showSymbol: false,
          //折线端点的标签
          endLabel: {
            show: true,
            formatter: function (params) {
              return params.data.movieName + ": " + params.data.totalPrice
            }
          },
          //标签统一布局配置
          labelLayout: {
            moveOverlap: 'shiftY'
          },
          //折线图的高亮状态
          emphasis: {
            focus: 'series'
          },
          //可以定义data的哪个维度被编码成什么
          encode: {
            x: 'orderDate',
            y: 'totalPrice'
          },
        })
      })
      //图表配置项设定
      let option = {
        title: {
          text: '最近十五天票房',
          left: 'center',
        },
        //初始动画的时长
        animationDuration: 10000,
        //数据源
        dataset: [
          //原始数据
          {
            id: 'dataset_raw',
            source: _rawData
          },
          //数据转换操作
          ...datasetWithFilters
        ],
        tooltip: {
          order: 'valueDesc',
          trigger: 'axis'
        },
        xAxis: {
          type: 'time',
          nameLocation: 'middle',
          interval: 3600 * 24 * 30
        },
        yAxis: {
          name: 'totalPrice'
        },
        series: seriesList
      }
      this.myEcharts.clear()
      this.myEcharts.setOption(option)
    },
  }
}
</script>

<style scoped>

</style>
