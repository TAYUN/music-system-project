<template>
	<!--  显示歌曲表格-->
	<el-table
		stripe
		:data="tableData"
		row-key="id"
		tooltip-effect="dark"
		:row-class-name="getRowIndex"
		@row-dblclick="dblclick"
		style="width: 100%">
		<el-table-column prop="gqName" label="歌曲名">
			<template slot-scope="scope">
				<div class="gq-img-name">
					<el-avatar
						shape="square"
						:size="30"
						:src="removeDuplicate(scope.row.gqPic)"></el-avatar>
					<div style="margin-left: 10px">{{ scope.row.gqName }}</div>
					<!-- <div>{{ $store.state.configure.HOST + scope.row.gqPic }}</div> -->
				</div>
			</template>
		</el-table-column>
		<el-table-column prop="gqSingerName" label="歌手"></el-table-column>
		<el-table-column prop="gqIntroduction" label="专辑"></el-table-column>
	</el-table>
</template>

<script>
import { clickSongById } from '@/api/index';
export default {
	name: 'moudlu-song',
	props: ['mPlayList'],
	computed: {
		// 检测搜索结果
		tableData() {
			return this.mPlayList;
		},
	},

	watch: {
		mPlayList() {
			// this.saveSongsToCache();
		},
	},

	methods: {
		// 保存当前歌曲列表到到缓存
		saveSongsToCache() {
			if (this.mPlayList.length === 0) {
				return;
			}
			let listOfSongs = this.mPlayList.map((song, index) => {
				const newSong = { ...song }; // 复制原始对象
				newSong.gqPic = this.$store.state.configure.HOST + song.gqPic; // 修改 gqPic 字段的值
				newSong.gqUrl = this.$store.state.configure.HOST + song.gqUrl; // 修改 gqUrl 字段的值
				newSong.rowIndex = index; // 添加索引
				newSong.isPlay = false; // 是否正在播放默认设置为false
				newSong.favorited = false; // 是否喜欢默认为false
				return newSong;
			});
			this.$store.commit('setListOfSongs', listOfSongs);
			console.log('this.listOfSongs : ', listOfSongs);
		},
		dblclick(row) {
			// 深拷贝防止接下来的防止出现重复，方法一：该方法不能处理函数和其他原始类型数据，而且会忽略 undefined、symbol、function 等。同时，当对象包含循环引用时，该函数会抛出错误。
			// let line = JSON.parse(JSON.stringify(row));

			// 方法二：直接去除重复的HOST地址

			// row.gqUrl = this.$store.state.configure.HOST + row.gqUrl;
			// row.gqPic = this.$store.state.configure.HOST + row.gqPic;

			row.gqUrl = this.removeDuplicate(row.gqUrl);
			row.gqPic = this.removeDuplicate(row.gqPic);
			//播放
			this.$store.commit('setId', row.id);
			// this.$store.commit('setCurrentSong', row.id);
			this.$store.commit('setUrl', row.gqUrl);
			this.$store.commit('setPicUrl', row.gqPic);
			this.$store.commit('setListIndex', row.rowIndex);
			this.$store.commit('setTitle', row.gqName);
			this.$store.commit('setArtist', row.gqSingerName);
			this.$store.commit('setGqLyric', row.gqLyric);
			// 存储数据到 session storage 中//测试
			// this.$session.set('lyric', row.gqLyric);
			this.$store.commit('setIsActive', false);
			// 播放状态设置true
			this.$store.commit('setIsPlay', true);
			this.$store.commit('setFavorite', false);
			// this.$store.dispatch('addRecentSong', row);
			this.$store.commit('addSong', row);
			// 添加当前播放的歌曲对象到缓存
			this.$store.commit('setPlayingSong', row);
			this.$store.commit('setCurrentSong', row);
			// 给该id的歌曲点击数加1
			clickSongById(row.id)
				.then((res) => {
					console.log('点击量加1 ',res)
			})
			console.log('row : ', row);
		},
		// 给表格中的每一行添加index索引
		getRowIndex({ row, rowIndex }) {
			row.rowIndex = rowIndex;
		},

		// 去除因为浅拷贝而重复的HOST字符串
		removeDuplicate(str) {
			const reg = /^(.*)\1+/; // 匹配重复部分的正则表达式
			str = this.$store.state.configure.HOST + str;
			return str.replace(reg, '$1');
		},
	},
};
</script>

<style lang="scss" scoped>
// 去除el-table底部白线
.el-table::before {
	height: 0px;
}
.gq-img-name {
	display: flex;
	align-items: center;
	// justify-content: center;
}

/deep/ .el-table__row {
	cursor: pointer;
}
</style>
