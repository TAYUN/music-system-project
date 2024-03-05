<template>
	<div>
		<!-- 歌单获取数据层 -->
		<ul class="song-list-header">
			<li
				v-for="(item, index) in playlistStyle"
				:key="index"
				@click="handleChangeView(item.name)"
				:class="{ active: item.name == activeName }">
				{{ item.name }}
			</li>
		</ul>

		<mod-playlist :playList="partOfPlayList"></mod-playlist>
		<el-pagination
			class="pagination"
			hide-on-single-page
			background
			layout="prev, pager, next"
			:total="playlist.length"
			:current-page="currentPage"
			:page-size="pageSize"
			@current-change="handleCurrentChange">
		</el-pagination>
	</div>
</template>

<script>
import ModPlaylist from '@/components/mod-playlist.vue';
import {
	getAllSongList,
	getSongListOfLikeTitle,
	getSongListOfLikeStyle,
} from '@/api/index.js';
import { playlistStyle } from '@/data/playlist';

export default {
	name: 'playlist-list',
	components: {
		ModPlaylist,
	},
	data() {
		return {
			playlist: [],
			pageSize: 50, //页面大小，一页有15条数据
			currentPage: 1, //当前页，默认第一页
			playlistStyle: [], //风格
			activeName: '全部', //当前风格

			// isSearch: false,//是否为搜索歌单
		};
	},
	watch: {
		'$route.query.keywords': {
			immediate: true,
			handler(val) {
				this.keywords = val;
				this.getSearchList(this.$route.query.keywords);
			},
		},
	},
	created() {
		if (this.$route.query.keywords !== undefined) {
			this.getSearchList(this.$route.query.keywords);
		}else if(this.$route.fullPath === '/'){
			// 首页推荐歌单  目前是固定的
			console.log('推荐歌单');
			this.getSongListOfTop();
		}
		 else {
			this.playlistStyle = playlistStyle;
			this.queryAllSongList();
			
		}

	},
	computed: {
		// 当前是否为搜索的结果
		isSearch() {
			return this.$route.query.keywords === undefined;
		},
		partOfPlayList() {
			return this.playlist.slice(
				(this.currentPage - 1) * this.pageSize,
				this.currentPage * this.pageSize
			);
		},
	},
	methods: {
		// 查询全部歌单
		queryAllSongList() {
			getAllSongList().then((res) => {
				if (res) {
					for (let i = 0; i < res.length; i++) {
						// 解析相对地址添加绝对地址字段方便显示图片
						res[i].absolutePath = this.attachImageUrl(res[i].pic);
					}
					this.playlist = res;
				} else {
					console.log('歌单数据 : ', res);
				}
				console.log('歌单数据 : ', res);
			});
		},

		// 根据搜索关键字查询歌单
		getSearchList(searchKeywords) {
			if (!searchKeywords) {
				console.log('您输入的内容为空-歌单', 'warning');
			} else {
				getSongListOfLikeTitle(searchKeywords).then((data) => {
					if (data) {
						for (let i = 0; i < data.length; i++) {
							data[i].absolutePath = this.attachImageUrl(data[i].pic);
						}
						this.playlist = data;
					} else {
						console.log('data : ', data);
					}
				});
			}
		},
		//根据style显示对应的歌单
		handleChangeView(name) {
			this.activeName = name;
			this.playlist = [];
			if (name == '全部') {
				this.queryAllSongList();
			} else {
				this.goSongListOfStyle(name);
			}
		},
		//根据style查询对应的歌单
		goSongListOfStyle(style) {
			getSongListOfLikeStyle(style).then((res) => {
				if (res) {
					for (let i = 0; i < res.length; i++) {
						res[i].absolutePath = this.attachImageUrl(res[i].pic);
					}
					this.currentPage = 1;
					this.playlist = res;
					console.log('根据风格 : ',this.playlist)
				} else {
					console.log('res : ', res);
				}
			});
		},
		// 查询歌单点击榜
		getSongListOfTop() {
			getAllSongList().then((res) => {
				// 截取排序后的前15个歌单
				console.log('热门歌单 : ', res);

				if (!res.length) {
					this.playlist = [];
				} else {
					for (let i = 0; i < res.length; i++) {
						res[i].absolutePath = this.attachImageUrl(res[i].pic);
					}
					res.sort(function (A, B) {
						return B.clickCount - A.clickCount;
					});
				}
				var topPlaylist = res.slice(0, 12);
				this.playlist = topPlaylist;
			});
		},

		// 获取图片地址
		attachImageUrl(picUrl) {
			// 用数据库相对地址拼接后端绝对地址
			return picUrl
				? this.$store.state.configure.HOST + picUrl
				: this.$store.state.configure.HOST + '/img/default.png';
		},
		//获取当前页
		handleCurrentChange(val) {
			this.currentPage = val;
		},
	},
};
</script>

<style lang="scss" scoped>
.song-list-header {
	width: 100%;
	padding: 0 40px;
	li {
		display: inline-block;
		box-sizing: border-box;
		line-height: 40px;
		margin:0 20px 10px 0;
		font-size: 16px;
		font-weight: 400;
		color: #909399;
		border-bottom: none;
		cursor: pointer;
	}
	li:hover{
		color: #000;
	}
	li.active {
		color: #20a0ff;
		font-weight: 600;
		border-bottom: 2px solid #20a0ff;
	}
}

.sin-container {
	box-sizing: border-box;
	width: 100%;
	height: 100%;
	padding: 0px 2%;
}

.sin-container h1 {
	text-align: center;
	padding-top: 20px;
	margin-bottom: 60px;
	font-weight: 600;
	position: relative;
}

.sin-container h1::after {
	content: '';
	background: #303ef7;
	width: 100px;
	height: 5px;
	position: absolute;
	bottom: -5px;
	left: 50%;
	transform: translateX(-50%);
}

.row {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(210px, 1fr));
	grid-column-gap: 15px;
	grid-row-gap: 30px;
}

.sin-card {
	text-align: center;
	padding: 10px 10px;
	border-radius: 5px;
	font-size: 14px;
	cursor: pointer;
	background: transparent;
	transition: transform 0.5s, background 0.5s;
}
.card-img {
	width: 100%;
	border-radius: 14px;
	/* 设置过渡 */
	transition: 0.35s;
}

.sin-card i {
	font-size: 40px;
	margin-bottom: 10px;
	color: #303ef7;
}

.sin-card h3 {
	font-weight: 600;
	margin-bottom: 8px;
}

.sin-card:hover {
	// background: #303ef7;
	color: #000;
	transform: scale(1.05);
}

.sin-card:hover i {
	color: #000;
}
// 分页居中
.pagination {
	display: flex;
	justify-content: center;
}
</style>
