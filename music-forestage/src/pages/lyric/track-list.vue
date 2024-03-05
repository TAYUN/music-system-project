<template>
	<div>
		<el-tabs class="el-track-list" v-model="activeName" @tab-click="tableclick">
			<el-tab-pane label="当前播放" name="first">
				<template>
					<el-table :data="getListOfSongs" @row-dblclick="dblclickA" style="width: 100%">
						<!-- 设置宽度可以防止闪烁？width="250" -->
						<el-table-column prop="gqName" label="歌名" width="250"> </el-table-column>
						<el-table-column prop="gqSingerName" label="歌手"> </el-table-column>
					</el-table>
				</template>
			</el-tab-pane>
			<el-tab-pane label="最近播放" name="second">
				<template>
					<el-table :data="recentSongs" @row-dblclick="dblclickB" style="width: 100%">
						<el-table-column prop="gqName" label="歌名" width="250"> </el-table-column>
						<el-table-column prop="gqSingerName" label="歌手"> </el-table-column>
					</el-table>
				</template>
			</el-tab-pane>
		</el-tabs>
	</div>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
	name: 'track-list',
	data() {
		return {
			activeName: 'first',
		};
	},
	computed: {
		...mapGetters(['getListOfSongs', 'recentSongs']),
	},
	watch: {
		recentSongs(val, oldVal) {
			return val;
		},
		getListOfSongs(val) {
			return val;
		},
	},
	methods: {
		tableclick(e) {
			// console.log(e);
			// console.log(123132);
		},
		// 逻辑待优化
		dblclickA(row) {
			// this.$store.commit('setListOfSongs', this.getListOfSongs);
			this.setStore(row);
		},
		dblclickB(row) {
			// this.$store.commit('setCurrentSong', this.recentSongs);
			this.setStore(row);
		},
		setStore(row) {
			console.log('row : ', row);
			//播放
			this.$store.commit('setId', row.id);
			this.$store.commit('setCurrentSongId', row.id);
			this.$store.commit('setUrl', row.gqUrl);
			this.$store.commit('setPicUrl', row.gqPic);
			this.$store.commit('setListIndex', row.rowIndex);
			this.$store.commit('setTitle', row.gqName);
			this.$store.commit('setArtist', row.gqSingerName);
			this.$store.commit('setGqLyric', row.gqLyric);
			this.$store.commit('setIsActive', false);
			// 播放状态设置true
			this.$store.commit('setIsPlay', true);
			this.$store.commit('setFavorite', false);
			// 添加当前播放的歌曲对象到缓存
			// this.$store.commit('addSong', row);
			this.$store.commit('setPlayingSong', row);
			this.$store.commit('setCurrentSong', row);
		},
	},
};
</script>

<style lang="scss" scoped>
.el-track-list {
	padding: 0 20px;
	// overflow-y: auto;
	// height: 100%;
}
</style>
