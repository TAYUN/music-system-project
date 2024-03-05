<template>
  <div id="app">
    <!-- 播放控件 -->
    <SongAudio/>
    <router-view />
  </div>
</template>

<script>
import '@/assets/css/reset.css';
import SongAudio from './components/SongAudio';
export default {
  name: 'App',
  components: {
    SongAudio
  }
}
</script>

<style lang="less">
#app {
  // font-family: Avenir, Helvetica, Arial, sans-serif;
  // -webkit-font-smoothing: antialiased;
  // -moz-osx-font-smoothing: grayscale;
  // text-align: center;
  // color: #2c3e50;
  margin: 0;
  padding: 0;

}
</style>
