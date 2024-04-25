<template>

  <div>
    <span style="font-size: 30px">
      {{ x }}
    </span>
    <p style="font-size: 30px">
      {{ location }}
    </p>
  </div>
</template>

<script>
import AMapLoader from "@amap/amap-jsapi-loader";

export default {
  name: "ipCity",
  data(){
    return{
      city: '',
      x: '',
      location: ''
    }
  },
  methods: {
    init(){
      AMapLoader.load({
        key:"ae092bc4ecb0e1c2162293e6094165f8", // 申请好的Web端开发者Key，首次调用 load 时必填
        version:"2.0",      // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins:['AMap.Geolocation','AMap.CitySearch'], // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      }).then((AMap)=> {

        var geolocation = new AMap.Geolocation({
          enableHighAccuracy: true,//是否使用高精度定位，默认:true
          timeout: 10000,          //超过10秒后停止定位，默认：5s
        });

        geolocation.getCityInfo((status,result)=>{
          console.log(status)
          console.log(result)
        })

        geolocation.getCurrentPosition((status,result)=>{
          console.log('geo:'+status)
          this.x = result.position
        })

      })
    }
  },
  mounted() {
    this.init();
  }
}
</script>

<style scoped>

</style>
