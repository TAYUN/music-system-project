<template>
	<div class="playlist-container">
		<template> </template>
		<template v-if="userId">
			<div class="sin-container">
				<el-empty v-if="playlist.length === 0" description="暂无歌单">
					<el-button @click="dialogVisible = true" type="primary" isShow
						>新建歌单</el-button
					>
				</el-empty>

				<div class="row">
					<template>
						<div
							class="sin-card"
							v-for="(item, index) in partOfPlayList"
							:key="index"
							@click="goDetails(item.id)">
							<img class="card-img" :src="item.absolutePath" />
							<h3>{{ item.title }}</h3>
						</div>
					</template>
					<el-button class="add-btn sin-card" @click="dialogVisible = true"
						>新建歌单</el-button
					>
				</div>
			</div>

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
			<template>
				<el-dialog title="添加歌单" :visible.sync="dialogVisible" width="400px" center>
					<el-form :model="dataForm" ref="dataForm" label-width="80px">
						<el-form-item prop="title" label="标题" size="mini">
							<el-input v-model="dataForm.rubric" placeholder="标题"></el-input>
						</el-form-item>
						<el-form-item prop="introduction" label="简介" size="mini">
							<el-input
								v-model="dataForm.describe"
								placeholder="简介"
								type="textarea"></el-input>
						</el-form-item>
						<el-form-item prop="style" label="风格" size="mini">
							<el-input v-model="dataForm.class" placeholder="风格"></el-input>
						</el-form-item>
					</el-form>
					<span slot="footer">
						<el-button size="mini" @click="dialogVisible = false">取消</el-button>
						<el-button size="mini" @click="createPlaylist">确定</el-button>
					</span>
				</el-dialog>
			</template>
		</template>

		<div class="" v-if="!userId">请先登录</div>
	</div>
</template>

<script>
import ModPlaylist from '@/components/mod-playlist.vue';
import { createMyCollection, getSongListByUserId } from '@/api/index.js';

export default {
	name: 'playlist-list',
	components: {
		ModPlaylist,
	},
	data() {
		return {
			// 右键相关
			visible: false,
			top: 500,
			left: 500,

			dialogVisible: false, //添加弹窗是否显示
			editVisible: false, //编辑弹窗是否显示
			delVisible: false, //删除弹窗是否显示
			dataForm: {
				//添加框
				rubric: '',
				describe: '',
				class: '',
			},
			playlist: [],
			pageSize: 30, //页面大小，一页有15条数据
			currentPage: 1, //当前页，默认第一页
			userId: this.$route.query.userId,
		};
	},

	mounted() {
		if (this.userId) {
			this.queryAllSongList(this.userId);
		} else {
			this.$notify.info({
				showClose: true,
				message: '请先登录',
				offset:60,
			});
		}
	},
	computed: {
		partOfPlayList() {
			return this.playlist.slice(
				(this.currentPage - 1) * this.pageSize,
				this.currentPage * this.pageSize
			);
		},
	},

	methods: {
		// 查询全部歌单
		queryAllSongList(id) {
			getSongListByUserId(id)
				.then((res) => {
					for (let i = 0; i < res.length; i++) {
						// 解析相对地址添加绝对地址字段方便显示图片
						res[i].absolutePath = this.attachImageUrl(res[i].pic);
					}
					this.playlist = res;
					console.log('res : ', res);
					//通过歌曲id获取歌曲信息
				})
				.catch((err) => {
					console.log(err);
				});
		},
		goDetails(id) {
			this.$router.push({
				path: '/playlist-details',
				query: { playlistId: id, userId: this.userId },
			});
		},
		createPlaylist() {
			let _ = this;//防止闭包
			var query = new URLSearchParams();
			query.append('title', this.dataForm.rubric);
			query.append('pic', '/img/songListPic/default.png');
			query.append('introduction', this.dataForm.describe);
			query.append('style', this.dataForm.class);
			query.append('userId', this.userId);

			createMyCollection(query)
				.then((data) => {
					if (data.code == 0) {
						_.$message({
							showClose: true,
							message: '已存在或未知错误',
							type: 'error',
						});
					} else {

						_.queryAllSongList(_.userId);
						_.$message({
							showClose: true,
							message: '新添成功',
							type: 'success',
						});
						_.dataForm = {
							title: '',
							introduction: '',
							style: '',
						};
						
					}
				})
				.catch((err) => {
					console.log(err);
				});
			this.dialogVisible = false;
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
.playlist-container {
	// background-color: #123456;
	box-sizing: border-box;
	width: 100%;
	height: 100%;
}
.song-list-header {
	width: 100%;
	padding: 0 40px;
	li {
		display: inline-block;
		line-height: 40px;
		margin: 40px 20px 15px 20px;
		font-size: 20px;
		font-weight: 400;
		color: #000;
		border-bottom: none;
		cursor: pointer;
	}
	li.active {
		color: #242424;
		font-weight: 600;
		border-bottom: 4px solid #5289fa;
	}
}

.sin-container {
	box-sizing: border-box;
	// width: 100%;
	// height: 100%;
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
	max-width: 250px;
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

.add-btn {
	margin: 10px 10px;
	border-radius: 5px;
	min-width: 200px;
	min-height: 200px;
	/* 设置过渡 */
	transition: 0.35s;
}
.add-btn:hover {
	// background: #303ef7;
	color: #000;
	transform: scale(1.05);
}
// 分页居中
.pagination {
	display: flex;
	justify-content: center;
}
</style>
