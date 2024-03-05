<template>
	<!-- 详情页面展示层 -->
	<div class="module-detail">
		<div class="module-header">
			<div class="sin-card">
				<!--                <img-->
				<!--                        class="card-img"-->
				<!--                        src="http://qpic.y.qq.com/music_cover/0j74Yxg836PickDahFsg1M9oAGBVb0IrwbqiaYZxKO4jDNNTx4wGNxBrLIV37Uiacsx/300?n=1"-->
				<!--                        alt=""/>-->
				<img class="card-img" :src="getPicUrl(obj.pic)" alt="" />
			</div>
			<!-- 右边介绍 -->
			<div class="module-info">
				<el-card class="box-card" shadow="never">
					<p class="card-title">{{ obj.name || obj.title }}</p>
					<p v-if="obj.style">{{ obj.style }}</p>
					<p>{{ obj.introduction }}</p>
					<el-button type="primary" @click="playAllMusic">播放全部</el-button>
					<el-button type="primary" style="margin-right: 10px;" v-if="obj.userId === userId" @click="addCollect(songId, playlistId)">添加歌曲</el-button>
					<el-popover
						placement="top"
						width="160"
						v-model="visible"
						v-if="obj.userId === userId">
						<p>确定删除此歌单吗？</p>
						<div style="text-align: right; margin: 0 ">
							<el-button size="mini" type="text" @click="visible = false">取消</el-button>
							<el-button type="primary" size="mini" @click="delePlaylist(userId)"
								>确定</el-button
							>
						</div>
						<el-button type="danger" slot="reference">删除歌单</el-button>
					</el-popover>
					
				</el-card>
			</div>
		</div>

		<template>
			<el-tabs v-model="activeName">
				<el-tab-pane label="音乐" name="first">
					<!-- 歌曲列表 -->
					<div class="module-playlist">
						<song-list ref="songlist" :singerId="singerId" :playlistId="playlistId" />
					</div>
				</el-tab-pane>
				<el-tab-pane v-if="playlistId" label="评论" name="second">
					<Comment :playId="playlistId" :type="1"></Comment>
				</el-tab-pane>
			</el-tabs>
		</template>
	</div>
</template>

<script>
import SongList from '@/pages/musiclib/song/song-list.vue';
import Comment from '@/pages/user/comment.vue';
import { delSongListByUserId, listSongAdd, listSongDetail } from '@/api/index';
import { mapGetters } from 'vuex';
export default {
	name: 'mod-details',
	inject: ['reload'],
	props: {
		obj: Object,
	},
	components: {
		SongList,
		Comment,
	},
	computed:{
		...mapGetters([
			'id',
		])
	},
	watch: {
		id(){
			this.songId = this.id;
		}
	},
	data() {
		return {
			visible: false,
			// 路由得到id，不用路由获取不知道为什么从singer中获取id获取不到,比如传singer.id到song-list组件，子组件接收不到
			singerId: this.$route.query.singerId,
			playlistId: this.$route.query.playlistId,
			userId: +this.$route.query.userId, //当前登录用户从自己歌单跳转过来的用户id,转成数字类型
			activeName: 'first',
			songId: this.$store.state.song.id,
		};
	},

	created() {
		console.log('路由用户 :userId : ', this.userId);
		console.log('歌单用户id', this.obj.userId);
		console.log(this.obj.userId === this.userId);
	},

	methods: {
		// 得到图片真实地址
		getPicUrl(picUrl) {
			return this.$store.state.configure.HOST + picUrl;
		},
		delePlaylist(id) {
			delSongListByUserId(id).then((res) => {
				if (res.code === 1) {
					this.$notify({
						title: '歌单删除成功',
						type: 'success',
					});
					this.$router.go(-1);
					this.visible = false;
				} else {
					this.$notify({
						title: '歌单删除成功',
						type: 'success',
					});
					this.visible = false;
				}
			});
		},

		addCollect(songId, playlistId) {
			listSongDetail(playlistId).then((res) => {
				console.log(res);
				if (res.length > 0) {
					const hasTargetSong = res.some((item) => {
						console.log('item.songId : ',item.songId)

						if (item.hasOwnProperty('songId') && item.songId === songId) {
							this.$notify({
								title: '该歌曲已经存在',
								type: 'error',
							});
							return true;
						}
					});
					if (!hasTargetSong) {
						this.addMusic(2);
					}
				} else {
					this.addMusic(1);
				}
			});
		},
		addMusic(ww) {
			console.log('ww : ', ww);
			let params = new URLSearchParams();
			params.append('songId', this.songId);
			params.append('songListId', this.playlistId);

			listSongAdd(params)
				.then((res) => {
					if (res.code == 1) {
						// this.getData();
						this.$notify({
							title: '添加成功',
							type: 'success',
						});
					} else {
						// this.notify('添加失败', 'error');
						this.$notify({
							title: '添加失败',
							type: 'error',
						});
					}
					// this.$router.go(0);
					this.refresh()
				})
				.catch((err) => {
					console.log(err);
				});
		},

		// 播放歌单全部音乐
		playAllMusic() {
			this.$refs.songlist.playAllMusic();
		},
		refresh() {
			this.reload();
		},
	},
};
</script>

<style lang="scss" scoped>
// 父容器  布局
.module-detail {
	.module-header {
	}

	// 图片卡片
	.sin-card {
	}

	// 信息
	.module-info {
		width: 100%;

		.box-card {
			height: 100%;
			border: none;

			.card-title {
				font-size: 25px;
				font-weight: 600;
			}
		}
	}

	// 歌曲列表
	.module-playlist {
	}
}

//样式
.module-header {
	display: flex;
	justify-content: flex-start;
	max-height: 220px;
	// 信息
	.module-info {
		padding: 10px;

		.box-card {
			height: 100%;
			border: none;
			.card-title {
				font-size: 25px;
				font-weight: 600;
			}
		}
	}
}

.sin-card {
	/* // 限制卡片大小 */
	max-width: 200px;
	padding: 10px 10px;
	border-radius: 5px;
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
</style>
