<template>
	<div class="table">
		<div class="crumbs"><i class="el-icon-headset"></i>&nbsp;歌曲信息</div>
		<div class="container">
			<div class="handle-box">
				<el-input
				v-model="select_word"
				size="mini"
				placeholder="请输入歌曲名"
				class="handle-input"></el-input>
				<el-button
				type="primary"
				v-if="singerId"
				size="mini"
				@click="centerDialogVisible = true"
				>添加歌曲</el-button
				>
				<el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
			</div>
		</div>
		<el-table
			size="mini"
			ref="multipleTable"
			border
			style="width: 100%"
			max-height="100%"
			:data="data"
			@selection-change="handleSelectionChange">
			<el-table-column type="selection" width="40"></el-table-column>
			<el-table-column label="歌曲图片" width="110" align="center">
				<template slot-scope="scope">
					<div class="song-img">
						<img :src="getUrl(scope.row.gqPic)" style="width: 100%" />
					</div>
					<div class="play" @click="setSongUrl(scope.row.gqUrl, scope.row.id)">
						<div v-if="toggle == scope.row.id">
							<svg class="icon">
								<use xlink:href="#icon-zanting"></use>
							</svg>
						</div>
						<div v-if="toggle != scope.row.id">
							<svg class="icon">
								<use xlink:href="#icon-bofanganniu"></use>
							</svg>
						</div>
					</div>
				</template>
			</el-table-column>
			<el-table-column
				prop="gqName"
				label="歌手-歌名"
				width="120"
				align="center"></el-table-column>
			<el-table-column
				prop="gqIntroduction"
				label="专辑"
				width="150"
				align="center"></el-table-column>
			<el-table-column  label="歌词" align="center">
				<template slot-scope="scope">
					<ul class="music-scrollbar" >
						<li v-for="(item, index) in parseLyric(scope.row.gqLyric)" :key="index">
							{{ item }}
						</li>
					</ul>
				</template>
			</el-table-column>
			<el-table-column label="资源更新" align="center" width="100">
				<template slot-scope="scope">
					<el-upload
						:action="uploadUrl(scope.row.id)"
						:before-upload="beforeAvatorUpload"
						:on-success="handleAvatorSuccess">
						<el-button size="mini">更新图片</el-button>
					</el-upload>
					<br />
					<el-upload
						:action="uploadSongUrl(scope.row.id)"
						:before-upload="beforeSongUpload"
						:on-success="handleSongSuccess">
						<el-button size="mini">更新歌曲</el-button>
					</el-upload>
				</template>
			</el-table-column>

			<el-table-column label="操作" width="150" align="center">
				<template slot-scope="scope">
					<el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
					<el-button size="mini" type="danger" @click="handleDelete(scope.row.id)"
						>删除</el-button
					>
				</template>
			</el-table-column>
		</el-table>
		<div class="pagination">
			<el-pagination
				background
				layout="total,prev,pager,next"
				:current-page="currentPage"
				:page-size="pageSize"
				:total="tableData.length"
				@current-change="handleCurrentChange">
			</el-pagination>
		</div>

		<el-dialog title="添加歌曲" :visible.sync="centerDialogVisible" width="400px" center>
			<el-form
				:model="registerForm"
				ref="registerForm"
				label-width="80px"
				enctype="multipart/form-data"
				id="tf">
				<el-form-item label="歌名" :prop="registerForm.gqName">
					<el-input v-model="registerForm.gqName" type="text" name="gqName"></el-input>
				</el-form-item>
				<el-form-item label="专辑" :prop="registerForm.gqIntroduction">
					<el-input
						v-model="registerForm.gqIntroduction"
						type="text"
						name="gqIntroduction"></el-input>
				</el-form-item>

				<el-form-item label="歌词" :prop="registerForm.gqLyric">
					<el-input
						v-model="registerForm.gqLyric"
						type="textarea"
						name="gqLyric"></el-input>
				</el-form-item>
				<el-form-item label="歌曲上传" :prop="registerForm.file">
					<el-input
						ref="file"
						v-model="registerForm.file"
						type="file"
						name="file"></el-input>
				</el-form-item>
			</el-form>
			<span slot="footer">
				<el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
				<el-button size="mini" @click="addSong">确定</el-button>
			</span>
		</el-dialog>

		<el-dialog title="修改歌曲" :visible.sync="editVisible" width="400px" center>
			<el-form :model="form" ref="form" label-width="80px">
				<el-form-item prop="name" label="歌手-歌名" size="mini">
					<el-input v-model="form.gqName" placeholder="歌手-歌名"></el-input>
				</el-form-item>
				<el-form-item prop="introduction" label="专辑" size="mini">
					<el-input v-model="form.gqIntroduction" placeholder="专辑"></el-input>
				</el-form-item>
				<el-form-item prop="lyric" label="歌词" size="mini">
					<el-input v-model="form.gqLyric" placeholder="歌词" type="textarea"></el-input>
				</el-form-item>
			</el-form>
			<span slot="footer">
				<el-button size="mini" @click="editVisible = false">取消</el-button>
				<el-button size="mini" @click="editSave">确定</el-button>
			</span>
		</el-dialog>

		<el-dialog title="删除歌曲" :visible.sync="delVisible" width="300px" center>
			<div align="center">删除不可恢复，是否确定删除？</div>
			<span slot="footer">
				<el-button size="mini" @click="delVisible = false">取消</el-button>
				<el-button size="mini" @click="deleteRow">确定</el-button>
			</span>
		</el-dialog>
	</div>
</template>

<script>
import { mixin } from '../mixins/index';
import { mapGetters } from 'vuex';
import '@/assets/js/iconfont.js';
import { songOfSingerId, updateSong, delSong, getAllSong } from '../api/index';

export default {
	mixins: [mixin],
	data() {
		return {
			singerId: '', //歌手id
			singerName: '', //歌手名
			centerDialogVisible: false, //添加弹窗是否显示
			editVisible: false, //编辑弹窗是否显示
			delVisible: false, //删除弹窗是否显示
			registerForm: {
				//添加框
				gqName: '',
				gqSingerName: '',
				gqIntroduction: '',
				gqLyric: '',
			},
			form: {
				//编辑框
				id: '',
				gqName: '',
				gqIntroduction: '',
				gqLyric: '',
			},
			tableData: [],
			tempData: [],
			select_word: '',
			pageSize: 5, //分页每页大小
			currentPage: 1, //当前页
			idx: -1, //当前选择项
			multipleSelection: [], //哪些项已经打勾
			toggle: false, //播放器的图标状态,根据播放音乐的唯一id标记
		};
	},
	computed: {
		...mapGetters(['isPlay']),
		//计算当前搜索结果表里的数据
		data() {
			return this.tableData.slice(
				(this.currentPage - 1) * this.pageSize,
				this.currentPage * this.pageSize
			);
		},
	},
	watch: {
		//搜索框里面的内容发生变化的时候，搜索结果table列表的内容跟着它的内容发生变化
		select_word: function () {
			if (this.select_word == '') {
				this.tableData = this.tempData;
			} else {
				this.tableData = [];
				for (let item of this.tempData) {
					if (item.gqName.includes(this.select_word)) {
						this.tableData.push(item);
					}
				}
			}
		},
	},
	created() {
		this.singerId = this.$route.query.id;
		this.singerName = this.$route.query.name;
		this.getData();
	},
	destroyed() {
		this.$store.commit('setIsPlay', false);
	},
	methods: {
		//获取当前页
		handleCurrentChange(val) {
			this.currentPage = val;
		},

		//根据条件查询歌曲
		getData() {
			this.tempData = [];
			this.tableData = [];
			if (this.singerId) {
				songOfSingerId(this.singerId).then((res) => {
					this.tempData = res;
					this.tableData = res;
					this.currentPage = 1;
				});
			} else {
				getAllSong().then((res) => {
					this.tempData = res;
					this.tableData = res;
					this.currentPage = 1;
				});
			}
		},
		//添加歌曲
		addSong() {
			console.log('this.singerId : ', this.singerId);
			let _this = this;
			var form = new FormData(document.getElementById('tf'));
			form.append('gqSingerId', this.singerId);
			form.set('gqName', this.singerName + '-' + form.get('gqName'));
			console.log(form);
			if (!form.get('gqLyric')) {
				form.set('gqLyric', '[00:00:00]暂无歌词');
			}
			var req = new XMLHttpRequest();
			req.onreadystatechange = function () {
				//req.readyState == 4 获取到返回的完整数据
				//req.status == 200 和后台正常交互完成
				if (req.readyState == 4 && req.status == 200) {
					let res = JSON.parse(req.response);
					console.log(res);
					if (res.code) {
						_this.getData();
						_this.registerForm = {};
						_this.notify(res.MSG, 'success');
					} else {
						_this.notify('保存失败', 'error');
					}
				}
			};
			req.open('post', `${_this.$store.state.HOST}/song/add`, false);
			req.send(form);
			_this.centerDialogVisible = false;
		},
		//弹出编辑页面
		handleEdit(row) {
			console.log('row : ', row);
			this.editVisible = true;
			this.form = {
				id: row.id,
				gqName: row.gqName,
				gqIntroduction: row.gqIntroduction,
				gqLyric: row.gqLyric,
			};
		},
		//保存编辑页面修改的数据
		editSave() {
			let params = new URLSearchParams();
			params.append('id', this.form.id);
			params.append('gqName', this.form.gqName);
			params.append('gqIntroduction', this.form.gqIntroduction);
			params.append('gqLyric', this.form.gqLyric);

			updateSong(params)
				.then((res) => {
					console.log('res : ', res);
					if (res.code == 1) {
						this.getData();
						this.notify('修改成功', 'success');
					} else {
						this.notify('修改失败', 'error');
					}
				})
				.catch((err) => {
					console.log(err);
				});
			this.editVisible = false;
		},
		//更新图片
		uploadUrl(id) {
			return `${this.$store.state.HOST}/song/updateSongPic?id=${id}`;
		},
		//删除
		deleteRow() {
			delSong(this.idx)
				.then((res) => {
					if (res) {
						this.getData();
						this.notify('删除成功', 'success');
						this.$store.commit('setIsPlay', false);
					} else {
						this.notify('删除失败', 'error');
					}
				})
				.catch((err) => {
					console.log(err);
				});
			this.delVisible = false;
		},
		//解析歌词
		parseLyric(text) {
			if (text) {
				let lines = text.split('\n');
				let pattern = /\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g;
				let result = [];
				for (let item of lines) {
					let value = item.replace(pattern, '');
					result.push(value);
				}
				return result;
			}
		},
		//上传歌曲之前的校验
		beforeSongUpload(file) {
			// 常见音乐格式
			var arr = ['mp3', 'wmv', 'wav', 'ape', 'flac', 'aac', 'ogg'];
			// 获取上传文件格式
			var type = file.name.match(/^(.*)(\.)(.{1,8})$/)[3].toLowerCase();
			// array.indexOf此方法判断数组中是否存在某个值，如果存在返回数组元素的下标，否则返回-1。
			var flag = arr.indexOf(type);

			// var testMsg = file.name.substring(file.name.lastIndexOf('.') + 1);
			if (flag == -1) {
				this.$message({
					message: '不支持这种音乐格式上传,请检查',
					type: 'error',
				});
				return false;
			}
			return true;
		},
		//上传歌曲成功之后要做的工作
		handleSongSuccess(res) {
			let _this = this;
			if (res.code == 1) {
				_this.getData();
				_this.$notify({
					title: '上传成功',
					type: 'success',
				});
			} else {
				_this.$notify({
					title: '上传失败',
					type: 'error',
				});
			}
		},
		//更新歌曲url
		uploadSongUrl(id) {
			return `${this.$store.state.HOST}/song/updateSongUrl?id=${id}`;
		},
		//切换播放歌曲
		setSongUrl(url, id) {
			this.toggle == id ? (this.toggle = '') : (this.toggle = id);
			this.$store.commit('setUrl', this.$store.state.HOST + url); //访问音乐地址
			if (this.isPlay) {
				//默认不播放
				this.$store.commit('setIsPlay', false);
			} else {
				this.$store.commit('setIsPlay', true);
			}
		},
	},
};
</script>

<style scoped lang="less">
.handle-box {
	margin-bottom: 0px;
}
.song-img {
	width: 100%;
	height: 80px;
	border-radius: 5px;
	margin-bottom: 5px;
	overflow: hidden;
}
.handle-input {
	width: 300px;
	display: inline-block;
	margin-right: 10px;
}
.pagination {
	margin-top: 10px;
	display: flex;
	justify-content: center;
}
.play {
	position: absolute;
	z-index: 100;
	width: 80px;
	height: 80px;
	display: flex;
	align-items: center;
	justify-content: center;
	cursor: pointer;
	top: 18px;
	left: 15px;
}

.icon {
	width: 2.5em;
	height: 2.5em;
	color: #94e2ff;
	fill: currentColor;
	overflow: hidden;
	opacity: 0.7;
}
.crumbs {
	margin: 20px;
}


</style>
