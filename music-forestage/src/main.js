import Vue from 'vue';
import App from './App.vue';
import router from '@/router/index';
import ElementUI from 'element-ui';
import './assets/css/icon-font/iconfont.css'; //导入阿里巴巴矢量图标库下载的图标
import './assets/css/icon-font/iconfont.js'; //导入阿里巴巴矢量图标库下载的图标
import 'element-ui/lib/theme-chalk/index.css';
import './assets/css/main.css'; //导入全局样式
import store from './store'; //导入缓存
import 'babel-polyfill';
import VueSession from 'vue-session';

Vue.use(VueSession);

Vue.use(ElementUI);
Vue.config.productionTip = false;

new Vue({
	router,
	store,
	render: (h) => h(App),
}).$mount('#app');
