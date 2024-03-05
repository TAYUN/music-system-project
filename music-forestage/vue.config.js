const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // 设置前端端口号
  devServer:{
    port: 8072
  },
  
})
