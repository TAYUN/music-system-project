import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
/**
 * 缓存数据。Vuex.Stroe里面可以json结构
 */
const store = new Vuex.Store({
    state:{
        HOST: 'http://localhost:8888', //后台的地址
        isPlay: false,          //是否播放中
        url: '',                //歌曲地址
        id: ''                  //歌曲id
    },
    getters: {
        isPlay: state => state.isPlay,
        url: state => state.url,
        id: state => state.id
    },
    mutations: {
        setIsPlay: (state,isPlay) => {state.isPlay = isPlay},
        setUrl: (state,url) => {state.url = url},
        setId: (state,id) => {state.id = id}
    }
})

export default store