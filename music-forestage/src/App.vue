<template>
	<div id="app">
		<div class="el-container">
			<!-- 右侧区域 -->
			<div class="right-content">
				<!-- 顶部 -->
				<div class="the-header">
					<the-header />
				</div>
				<!-- 右侧主要内容 -->
				<div class="main-content">
					<router-view class="music-content" />
				</div>
			</div>
			<div class="footer-bar">
				<music-player></music-player>
			</div>
		</div>
	</div>
</template>

<script>
import '@/assets/css/reset.css';
import LeftMenu from '@/pages/layout/left-menu';
import MusicPlayer from './pages/player/music-player.vue';
import MusicLyric from './pages/lyric/music-lyric.vue';
import TheHeader from '@/pages/layout/the-header.vue';
import TrackList from './pages/lyric/track-list.vue';
import { mapGetters } from 'vuex';

export default {
	//注入依赖
	provide() {
		return {
			// 注入重新加载函数
			reload: this.reload,
		};
	},
	name: 'App',
	components: {
		LeftMenu,
		MusicPlayer,
		MusicLyric,
		TheHeader,
		TrackList,
	},
	data() {
		return {
			// isRouter: true,
		};
	},
	computed: {
		// ...mapGetters(['getShowLyric']),
	},
	created() {
		// this.$store.dispatch('loadRecentSongs');
		//在页面加载时读取sessionStorage里的状态信息
		if (sessionStorage.getItem('store')) {
			this.$store.replaceState(
				Object.assign({}, this.$store.state, JSON.parse(sessionStorage.getItem('store')))
			);
		}

		//在页面刷新时将vuex里的信息保存到sessionStorage里
		window.addEventListener('beforeunload', () => {
			sessionStorage.setItem('store', JSON.stringify(this.$store.state));
		});
	},
	methods: {
		/**
		 * 自定义刷新函数
		 * 本质上就是修改 v-if 中所绑定的Boolean值
		 */
		reload() {
			this.isRouter = false;
			this.$nextTick(function () {
				this.isRouter = true;
			});
		},
	},
};
</script>

<style lang="scss" scoped>
#app {
	height: 100vh;
	box-sizing: border-box;
	.el-container {
		display: flex;
		justify-content: flex-start;
		width: 100%;
		height: 100%;
		box-sizing: border-box;
		// background-color: #ecf2ff;

		// 右边
		.right-content {
			width: 100%;
			height: 100%;
			box-sizing: border-box;
			// overflow-y: auto;

			// 右边头部
			.the-header {
				height: 60px;
				width: 100%;
				box-sizing: border-box;
				// border-bottom: 1px solid #dadada;
			}
			// 右边主要内容
			.main-content {
				height: calc(100% - 60px);
				margin-bottom: 110px;
				width: 100%;
				padding: 20px;
				box-sizing: border-box;
				overflow: auto;
			}
			.music-content {
				overflow: overlay;
				padding-bottom: 110px;
			}
		}
		.footer-bar {
			position: absolute;
			bottom: 0;
			width: 100%;
			height: 100px;
		}
	}
	
}
</style>
