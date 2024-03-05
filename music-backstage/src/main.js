import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import  store  from "./store"; //导入缓存
import './assets/css/main.css' //导入全局样式
import 'babel-polyfill'
import VeChart  from 'v-charts'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(VeChart)

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
