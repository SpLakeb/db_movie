import Vue from 'vue';
import Router from 'vue-router';
import cinemaManage from "../components/admin/cinemaManage";
import dataShow from "../components/admin/dataShow";
import movieManage from "../components/admin/movieManage";
import performerManage from "../components/admin/performerManage";
import userManage from "../components/admin/userManage";
import checkSeats from "../components/checkSeats";
import comingSoon from "../components/comingSoon";
import homePage from "../components/homePage";
import hotShowing from "../components/hotShowing";
import movieDetails from "../components/movieDetails";
import movies from "../components/movies";
import oldMovies from "../components/oldMovies";
import ranking from "../components/ranking";
import cinema from "../components/cinema";
import userLoginForm1 from "../view/userLoginForm1";
import userLoginForm2 from "../view/userLoginForm2";
import hotRank from "../components/hotRank";
import expectedRank from "../components/expectedRank";
import boxRank from "../components/boxRank";
import userHomepage from "../view/userHomepage";
import information from "../components/user/information";
import orders from "../components/user/orders";
import performerDetails from "../components/performerDetails";
import query from "../components/query";
import video from "../components/video";
import adminMain from "../view/admin/adminMain";
import userLogin from "../view/userLogin";
import userMain from "../view/userMain";
import orderDetails from "../components/user/orderDetails";
import userLoginBeta from '../view/userLogin'
import userRegister from '../test/userRegister'
import cinemaDetails from "../components/cinemaDetails";
import checkOut from "../components/checkOut";
import forgetPassword from "../test/forgetPassword";
import addMovie from "../components/admin/children/movie/addMovie";
import movieList from "../components/admin/children/movie/movieList";
import ordersNoHandle from "../components/user/ordersNoHandle";
import success from "../components/success";


Vue.use(Router)

export default new Router({
  scrollBehavior(to, from, savedPosition) {
// return 期望滚动到哪个的位置 0,0 表示在顶部
    return {x: 0, y: 0}
  },
  routes: [
    {
      path: '/userLogin',
      name: 'userLogin',
      component: userLogin,
      redirect: '/userLogin/form1',
      children: [
        {
          path: 'form1',
          component: userLoginForm1,
        },
        {
          path: 'form2',
          component: userLoginForm2,
        },
      ]
    },
    {
      path:'/userRegister',
      name:'userRegister',
      component:userRegister
    },
    {
      path: '/video/:movieId',
      props: true,
      name: 'video',
      component: video
    },
    {
      path: '/',
      name: 'userMain',
      redirect: 'homePage',
      component: userMain,
      children: [
        {
          path: 'homePage',
          name: 'homePage',
          component: homePage
        },
        {
          path: '/orderDetails/:orderId',
          name: 'orderDetails',
          props: true,
          component: orderDetails,
        },
        {
          path: 'checkSeats/:scenesId',
          name: 'checkSeats',
          props: true,
          component: checkSeats
        },
        {
          path: 'checkOut',
          name: 'checkOut',
          component: checkOut
        },
        {
          path: '/success',
          component: success
        },
        // 榜单
        {
          path: '/ranking',
          name: 'ranking',
          redirect: 'ranking/hotRank',
          component: ranking,
          children: [
            // 热映榜单
            {
              path: 'hotRank',
              component: hotRank
            },
            // 热映榜单
            {
              path: 'expectedRank',
              component: expectedRank
            },
            // 热映榜单
            {
              path: 'boxRank',
              component: boxRank
            }
          ]
        },
        // 电影
        {
          path: 'movies',
          name: 'movies',
          redirect: 'movies/hotShowing',
          component: movies,
          children: [
            {
              path: 'hotShowing',
              component: hotShowing
            },
            {
              path: 'comingSoon',
              component: comingSoon
            },
            {
              path: 'oldMovies',
              component: oldMovies
            }
          ]
        },
        // 影院
        {
          path: '/cinema/:movieId',
          name: 'cinema',
          component: cinema,
          props: true,
        },
        {
          path: '/cinemaDetails/:cinemaId',
          props: true,
          component: cinemaDetails
        },
        // 电影详情
        {
          path: 'movieDetails/:movieId',
          name: 'movieDetails',
          props: true,
          component: movieDetails
        },
        {
          path: 'query/:keyWord',
          name: 'query',
          props: true,
          component: query
        },
        {
          path: 'performerDetails/:performerId',
          name: 'performerDetails',
          props: true,
          component: performerDetails
        },
        // 影院详情
        {
          path: 'cinemaDetails/:cinemaId',
          name: 'cinemaDetails',
          props: true,
          component: cinemaDetails,
        },
        //用户个人主页
        {
          path: '/userHomepage',
          name: 'userHomepage',
          redirect: 'userHomepage/information',
          component: userHomepage,
          children: [
            {
              path: 'information',
              component: information
            },
            {
              path: 'orders',
              component: orders,
            },
            {
              path: 'ordersNoHandle',
              component: ordersNoHandle
            }
          ]
        },
      ]
    },

    /**
     * admin
     */
    {
      path: '/adminMain',
      name: 'adminMain',
      component: adminMain,
      redirect: 'dataShow',
      children: [
        {
          path: '/movieManage',
          name: 'movieManage',
          component: movieManage,
          children: [
            {
              path: '/addMovie',
              name: 'addMovie',
              component: addMovie,
            },
            {
              path: '/movieList',
              name: 'movieList',
              component: movieList
            }
          ]
        },
        {
          path: '/performerManage',
          name: 'performerManage',
          component: performerManage
        },
        {
          path: '/cinemaManage',
          name: 'cinemaManage',
          component: cinemaManage
        },
        {
          path: '/userManage',
          name: 'userManage',
          component: userManage
        },
        {
          path: '/dataShow',
          name: 'dataShow',
          component: dataShow,
        },
      ]
    },
    //测试
    {
      path: '/userLoginBeta',
      name: 'userLoginBeta',
      component: userLoginBeta
    },
    {
      path: '/userRegister',
      name: 'userRegister',
      component: userRegister
    },
    {
      path:'/forgetPassword',
      name:'forgetPassword',
      component: forgetPassword
    },
  ]
})
