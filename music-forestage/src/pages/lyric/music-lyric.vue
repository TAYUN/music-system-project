<template>
	<div class="song-lyric">
		<!-- <h1 class="lyric-title">歌词</h1> -->
		<!-- 有歌词 -->
		<ul class="has-lyric" v-if="lyr.length" key="index">
			<li v-for="(item, index) in lyr" v-bind:key="index">
				{{ item[1] }}
			</li>
		</ul>
		<!-- 没有歌词 -->
		<div v-else class="no-lyric" key="no-lyric">
			<span>无歌词</span>
		</div>
	</div>
</template>
<script>
import { mapGetters } from 'vuex';

export default {
	name: 'music-lyric',
	data() {
		return {
			lyr: [], //当前歌曲的歌词
		};
	},
	computed: {
		...mapGetters([
			'getCurrentTime', //当前歌曲播放到的位置
			'getShowLyric', //是否显示歌词
			'getGqLyric', //歌词
		]),
	},

	watch: {
		getGqLyric: function () {
			this.lyr = this.parseLyrics(this.getGqLyric);
		},
		// 待优化
		getCurrentTime: function () {
			if (this.lyr.length > 0) {
				for (let i = 0; i < this.lyr.length; i++) {
					if (this.getCurrentTime >= this.lyr[i][0]) {
						for (let j = 0; j < this.lyr.length; j++) {
							document.querySelectorAll('.has-lyric li')[j].style.color = '#000';
							document.querySelectorAll('.has-lyric li')[j].style.fontSize = '15px';
						}
						if (i >= 0) {
							document.querySelectorAll('.has-lyric li')[i].style.color = '#95d2f6';
							document.querySelectorAll('.has-lyric li')[i].style.fontSize = '25px';
						}
					}
				}
			}
		},
	},
	methods: {
		//解析歌词
		parseLyrics(lyrics) {
			const pattern = /\[(\d{2}:\d{2})\.\d{2}\](.*)/g;
			const result = [];
			let match;
			while ((match = pattern.exec(lyrics)) !== null) {
				result.push([match[1], match[2].replace(/\n/g, '')]);
			}
			return result;
		},
	},
};
</script>
<style lang="scss" scoped>
@import '@/assets/css/lyric.scss';
</style>
