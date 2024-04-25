<template>

  <div id="container"></div>
</template>

<script>
import AMapLoader from '@amap/amap-jsapi-loader';
export default {
  name: "Gps",
  data(){
    return{
      map: null,
      geolocation: null
    }
  },
  methods: {

    initMap(){
      AMapLoader.load({
        key:"ae092bc4ecb0e1c2162293e6094165f8", // 申请好的Web端开发者Key，首次调用 load 时必填
        version:"2.0",      // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins:['AMap.Geolocation','AMap.CitySearch'], // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      }).then((AMap)=>{
        this.map = new AMap.Map("container",{  //设置地图容器id
          viewMode:"3D",    //是否为3D地图模式
          zoom:5,           //初始化地图级别
          center:[105.602725,37.076636], //初始化地图中心点位置
        });
        let that = this

        var citysearch = new AMap.CitySearch();

        citysearch.getLocalCity((status,result)=>{
          console.log(111)
        })

          AMap.plugin('AMap.Geolocation', function() {
            that.geolocation = new AMap.Geolocation({
              enableHighAccuracy: true,//是否使用高精度定位，默认:true
              timeout: 10000,          //超过10秒后停止定位，默认：5s
              buttonPosition: 'RB',    //定位按钮的停靠位置
              buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
              zoomToAccuracy: true,   //定位成功后是否自动调整地图视野到定位点

            });
          })
        this.map.addControl(this.geolocation)
        this.geolocation.getCurrentPosition(function(status,result){
          if(status=='complete'){
            that.onComplete(result)
          }else{
            that.onError(result)
          }
        });

      }).catch(e=>{
        console.log(e);
      })
    },

    //解析定位结果
    onComplete(data) {
      // document.getElementById('status').innerHTML='定位成功'
      var str = [];
      str.push('定位结果：' + data.position);
      str.push('定位类别：' + data.location_type);
      if(data.accuracy){
        str.push('精度：' + data.accuracy + ' 米');
      }//如为IP精确定位结果则没有精度信息
      str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
      console.log(str)
      // alert('定位成功'+str)
      // document.getElementById('result').innerHTML = str.join('<br>');
    },
    //解析定位错误信息
    onError(data) {
      console.log('定位失败')
      console.log(data.message)
      alert('定位失败'+data.message)
      // document.getElementById('status').innerHTML='定位失败'
      // document.getElementById('result').innerHTML = '失败原因排查信息:'+data.message;
    },

  },
  mounted() {
    this.initMap();
  }
}
</script>

<style scoped>
#container{
  padding:0px;
  margin: 0 auto;
  width: 80%;
  height: 500px;
}
</style>
