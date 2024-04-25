import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import router from './router'
import axios from "axios";
import VueAxios from "vue-axios";
import format from './format'
import VideoPlayer from 'vue-video-player'
import 'vue-video-player/src/custom-theme.css'
import 'video.js/dist/video-js.css'

Vue.use(ElementUI);
Vue.use(VueAxios,axios);
Vue.use(VideoPlayer);

Vue.config.productionTip = false

axios.defaults.baseURL = "/api"
//设置全局，每次ajax请求携带cookies
axios.defaults.withCredentials = true
// 将API方法绑定到全局
Vue.prototype.$axios = axios
Vue.prototype.$format = format

new Vue({
  el: '#app',
  router,
  render: h => h(App)
});
