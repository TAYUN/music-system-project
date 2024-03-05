<template>
	<div id="search-result">
		<template>
			<el-tabs v-model="activeName" @tab-click="handleClick">
				<el-tab-pane class="result-label" label="搜索结果" :disabled="true" name="first"></el-tab-pane>
				<el-tab-pane class="result-label" label="|" :disabled="true" name="second"></el-tab-pane>
				<el-tab-pane label="歌曲" name="three">
					<!-- <Song :play-list="listOfSongs"/> -->
					<song-list/>
				</el-tab-pane>
				<el-tab-pane label="歌单" name="third"><playlist-list /></el-tab-pane>
			</el-tabs>
		</template>
	</div>
</template>

<script>

import SongList from '@/pages/musiclib/song/song-list.vue'
import PlaylistList from '@/pages/musiclib/playlist/playlist-list.vue'
 
export default {
	name: 'search-result',
	components: {
        SongList,
        PlaylistList,
	},
	data() {
		return {
			activeName: 'three',
		};
	},

	
	methods: {
		handleClick(tab, event) {
			// console.log(tab, event);
		},
	},
};
</script>

<style lang="scss" scoped>
#search-result {
	/deep/ .el-tabs__item {
		padding-right: 0px;
		font-size: 16px;
	}
	& /deep/ #tab-first {
		padding-right: 0px;
		margin-right: 0px;
		font-size: 20px;
		color: black;
		font-weight: 600;
	}
}
</style>
