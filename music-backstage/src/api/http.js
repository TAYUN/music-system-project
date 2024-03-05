import router from "@/router";
import axios  from "axios";
// import { reject, resolve } from "core-js/fn/promise";

axios.defaults.timeout = 5000; //超时时间为5秒
axios.defaults.withCredentials = true; //允许跨域

//ConTent-Type响应头
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
//基础url // 前端基础url跨域处理方法见:http://t.csdn.cn/C2l2O
// axios.defaults.baseURL = "api";

axios.defaults.baseURL = "http://127.0.0.1:8888";
//响应拦截器
axios.interceptors.response.use(
    response => {
        // console.log(response);
        //如果reponse里面的status是200，说明访问到接口了，否则错误
        if(response.status == 200){
            return Promise.resolve(response);
        }else{
            return Promise.reject(response)
        }
    },

    error => {
        if(error.response.status){
            switch(error.response.status){
                case 401:
                    router.replace({
                        path: '/',
                        query:{
                            redirect: router.currentRoute.fullPath
                        }
                    });
                    break;
                case 404:
                    break;
            }
            return Promise.reject(error.response);
        }
    }
)
// 存地址
// query:{
//     redirect: router.currentRoute.fullPath
// }

// 封装get方法
export function get(url, params={}){
    return new Promise((resolve, reject) => {
        axios.get(url, {params: params})
        .then(response => {
            resolve(response.data)
        })
        .catch(err => {
            reject(err)
        })
    })
}

// 封装post方法

export function post(url, data={}){
    return new Promise((resolve, reject) => {
        axios.post(url, data)
        .then(response => {
            resolve(response.data)
        })
        .catch(err => {
            reject(err)
        })
    })
}