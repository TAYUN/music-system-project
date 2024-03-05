<template>
	<div>
		<!--  显示歌曲歌曲数据层-->
		<mod-song ref="modSong" :m-play-list="partOfPlayList"></mod-song>
		<el-pagination
			class="pagination"
			hide-on-single-page
			background
			layout="prev, pager, next"
			:total="playList.length"
			:current-page="currentPage"
			:page-size="pageSize"
			@current-change="handleCurrentChange">
		</el-pagination>
	</div>
</template>

<script>
import ModSong from '@/components/mod-song.vue';
import {
	songOfSingerId,
	likeSongOfName,
	listSongDetail,
	songOfSongId,
	getAllSong,
} from '@/api/index';
export default {
	name: 'song',
	components: {
		ModSong,
	},
	props: ['singerId', 'playlistId'],
	data() {
		return {
			// 传给展示层的数据
			playList: [],
			pageSize: 30, //页面大小，一页有15条数据
			currentPage: 1, //当前页，默认第一页
		};
	},
	watch: {
		'$route.query.keywords': {
			immediate: true,
			handler(val) {
				this.keywords = val;
				if (this.keywords !== undefined) {
					this.getSongBySearch(this.$route.query.keywords);
				}
			},
		},
	},
	created() {
		if (this.singerId) {
			this.getSongOfSingerId(this.singerId);
		}
		if (this.$route.query.keywords !== undefined) {
			this.getSongBySearch(this.$route.query.keywords);
		}
		if (this.playlistId) {
			this.getListSongDetail(this.playlistId);
		}
		if (this.$route.fullPath === '/') {
			// 如果打开的是首页 查询歌曲点击榜
			console.log('推荐歌曲', this.$route);
			this.getSongOfTop();
		}
	},
	computed: {
		partOfPlayList() {
			return this.playList.slice(
				(this.currentPage - 1) * this.pageSize,
				this.currentPage * this.pageSize
			);
		},
	},

	methods: {
		// 根据歌单id 查询歌单歌曲
		getListSongDetail(id) {
			// let _this = this
			listSongDetail(id).then((res) => {
				if (res.length > 0) {
					for (let item of res) {
						this.getSongOfSongId(item.songId);
					}
				} else {
					// 没有数据怎么处理
				}
			});
		},

		// 根据歌单id查询歌曲
		getSongOfSongId(id) {
			songOfSongId(id).then((res) => {
				console.log(res);
				res.gqSingerName = this.getSingerName(res.gqName);
				res.gqName = this.getSongName(res.gqName);
				this.playList.push(res);
				// 打印出来的是子组件的数据
				// console.log('this.playList : ',this.playList)
			});
		},
		// 根据歌手id查询歌曲
		getSongOfSingerId(id) {
			songOfSingerId(id).then((res) => {
				if (res.length > 0) {
					res = this.analyseSong(res);
				} else {
					// 没有数据怎么处理
				}
				this.playList = res;
				// 打印出来的是子组件的数据
				// console.log('this.playList : ',this.playList)
			});
		},
		// 搜索查到的歌曲
		getSongBySearch(keywords) {
			likeSongOfName(keywords).then((res) => {
				if (!res.length) {
					this.playList = [];
				} else {
					this.playList = this.analyseSong(res);
				}
			});
		},
		// 查询歌曲点击榜
		getSongOfTop() {
			getAllSong().then((res) => {
				// 截取排序后的前15首歌曲
				console.log('res : ', res);

				if (!res.length) {
					this.playList = [];
				} else {
					// 分析歌手名
					res = this.analyseSong(res);
					res.sort(function (songA, songB) {
						return songB.clickCount - songA.clickCount;
					});
				}
				console.log('分析歌曲后的res : ', res);

				var topSongs = res.slice(0, 15);
				this.playList = topSongs;
			});
		},
		// 修改歌曲的歌名歌手属性
		analyseSong(arr) {
			for (let i = 0; i < arr.length; i++) {
				// 解析歌曲名 "洛天依-科幻镇"  拆分成歌手名{洛天依}和歌曲名{科幻镇}
				arr[i].gqSingerName = this.getSingerName(arr[i].gqName);
				arr[i].gqName = this.getSongName(arr[i].gqName);
			}
			return arr;
		},
		//获取名字前半部分--歌手名
		getSingerName(str) {
			let arr = str.split('-');
			return arr[0];
		},
		//获取名字后半部分--歌名
		getSongName(str) {
			let arr = str.split('-');
			return arr[1];
		},
		//获取当前页
		handleCurrentChange(val) {
			this.currentPage = val;
		},
		// 在调用子组件方法
		playAllMusic() {
			// 如果当前歌单列表为空，不能播放全部
			if (this.playList.length === 0) {
				return;
			}
			this.$refs.modSong.saveSongsToCache();
			let playingSong = this.$store.state.song.listOfSongs[0];
			console.log(
				'this.$store.state.song.listOfSongs : ',
				this.$store.state.song.listOfSongs
			);
			this.$store.commit('setPlayingSong', playingSong);
			this.$store.commit('setCurrentSong', playingSong);
		},
	},
};
</script>

<style lang="scss" scoped>
/* 分页居中 */
.pagination {
	display: flex;
	justify-content: center;
}
</style>
