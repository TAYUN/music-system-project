<template>
	<div class="table">
		<div class="crumbs"><i class="el-icon-s-custom"></i>&nbsp;歌手管理</div>
		<div class="container">
			<div class="handle-box">
				<!-- 搜索歌手框 -->
				<el-input
					v-model="select_word"
					size="mini"
					placeholder="请输入歌手名"
					class="handle-input">
				</el-input>
				<el-button type="primary" size="mini" @click="delAll">批量删除</el-button>
				<!-- 添加歌手按钮 -->
				<el-button type="primary" size="mini" @click="centerDialogVisible = true"
					>添加歌手</el-button
				>
			</div>
		</div>
		<!-- 歌手信息展示 -->
		<el-table
			size="mini"
			ref="multipleTable"
			border
			style="width: 100%; max-height: 100%"
			:data="data"
			@selection-change="handleSelectionChange">
			<!-- 多选 -->
			<el-table-column type="selection" width="40"></el-table-column>
			<el-table-column label="歌手图片" width="110" align="center">
				<template slot-scope="scope">
					<div class="singer-img">
						<img :src="getUrl(scope.row.pic)" style="width: 100%" />
					</div>
				</template>
			</el-table-column>
			<el-table-column
				prop="name"
				label="歌手"
				width="120"
				align="center"></el-table-column>
			<el-table-column label="性别" width="50" align="center">
				<!-- 这里需要做性别转码 -->
				<template slot-scope="scope">
					{{ changeSex(scope.row.sex) }}
				</template>
			</el-table-column>
			<!-- 这里需要做生日转码 -->
			<el-table-column label="生日" width="120" align="center">
				<template slot-scope="scope">
					{{ attachBirth(scope.row.birth) }}
				</template>
			</el-table-column>
			<el-table-column
				prop="location"
				label="地区"
				width="100"
				align="center"></el-table-column>
			<!-- 地区是多行锁业也用插槽 -->
			<el-table-column label="简介">
				<template slot-scope="scope">
					<p class="music-scrollbar">{{ scope.row.introduction }}</p>
				</template>
			</el-table-column>
			<el-table-column label="歌曲管理" width="110" align="center">
				<template slot-scope="scope">
					<!-- 上传歌手头像图片 -->
					<el-upload
						:action="uploadUrl(scope.row.id)"
						:before-upload="beforeAvatorUpload"
						:on-success="handleAvatorSuccess">
						<el-button size="mini">更新图片</el-button>
					</el-upload>
					<br />
					<el-button size="mini" @click="songEdit(scope.row.id, scope.row.name)"
						>歌曲管理</el-button
					>
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
		<!-- 分页 -->
		<div class="pagination">
			<el-pagination
				background
				layout="total, prev, pager, next"
				:current-page="currentPage"
				:page-size="pageSize"
				:total="tableData.length"
				@current-change="handleCurrentChange">
			</el-pagination>
		</div>

		<!-- 添加歌手弹窗 -->
		<el-dialog title="添加歌手" :visible.sync="centerDialogVisible" width="400px" center>
			<el-form :model="registerForm" ref="registerForm" label-width="80px">
				<el-form-item prop="name" label="歌手名" size="mini">
					<el-input v-model="registerForm.name" placeholder="歌手名"></el-input>
				</el-form-item>
				<el-form-item label="性别" size="mini">
					<el-radio-group v-model="registerForm.sex">
						<el-radio :label="0">女</el-radio>
						<el-radio :label="1">男</el-radio>
						<el-radio :label="2">组合</el-radio>
						<el-radio :label="3">其他</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item prop="birth" label="生日" size="mini">
					<el-date-picker
						type="date"
						placeholder="选择日期"
						v-model="registerForm.birth"
						style="width: 100%"></el-date-picker>
				</el-form-item>
				<el-form-item prop="location" label="地区" size="mini">
					<el-input v-model="registerForm.location" placeholder="地区"></el-input>
				</el-form-item>
				<el-form-item prop="introduction" label="简介" size="mini">
					<el-input
						v-model="registerForm.introduction"
						placeholder="简介"
						type="textarea"></el-input>
				</el-form-item>
			</el-form>
			<span slot="footer">
				<el-button size="mini" @click="centerDialogVisible = false">取消</el-button>
				<el-button size="mini" @click="addSinger">确定</el-button>
			</span>
		</el-dialog>

		<!-- 修改歌手弹窗 -->
		<el-dialog title="修改歌手" :visible.sync="editVisible" width="400px" center>
			<el-form :model="form" ref="form" label-width="80px">
				<el-form-item prop="name" label="歌手名" size="mini">
					<el-input v-model="form.name" placeholder="歌手名"></el-input>
				</el-form-item>
				<el-form-item label="性别" size="mini">
					<el-radio-group v-model="form.sex">
						<el-radio :label="0">女</el-radio>
						<el-radio :label="1">男</el-radio>
						<el-radio :label="2">组合</el-radio>
						<el-radio :label="3">不明</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="生日" size="mini">
					<el-date-picker
						type="date"
						placeholder="选择日期"
						v-model="form.birth"
						style="width: 100%"></el-date-picker>
				</el-form-item>
				<el-form-item prop="location" label="地区" size="mini">
					<el-input v-model="form.location" placeholder="地区"></el-input>
				</el-form-item>
				<el-form-item prop="introduction" label="简介" size="mini">
					<el-input
						v-model="form.introduction"
						placeholder="简介"
						type="textarea"></el-input>
				</el-form-item>
			</el-form>
			<span slot="footer">
				<el-button size="mini" @click="editVisible = false">取消</el-button>
				<el-button size="mini" @click="editSave">确定</el-button>
			</span>
		</el-dialog>

		<!-- 删除歌手弹窗 -->
		<el-dialog title="删除歌手" :visible.sync="delVisible" width="300px" center>
			<div align="center">删除不可恢复，是否确定删除？</div>
			<span slot="footer">
				<el-button size="mini" @click="delVisible = false">取消</el-button>
				<el-button size="mini" @click="delSingerRow">确定</el-button>
			</span>
		</el-dialog>
	</div>
</template>

<script>
import { setSinger, getAllSinger, updateSinger, removeSinger } from '@/api/index';
import { mixin } from '@/mixins/index';
export default {
	mixins: [mixin],
	data() {
		return {
			// 弹窗
			centerDialogVisible: false, //添加弹窗是否显示
			editVisible: false, //编辑弹窗是否展示
			delVisible: false, //删除弹窗是否显示

			// 添加表单
			registerForm: {
				name: '',
				sex: '',
				birth: '',
				location: '',
				introduction: '',
			},
			//编辑表单
			form: {
				id: '',
				name: '',
				sex: '',
				birth: '',
				location: '',
				introduction: '',
			},
			// 歌手数据
			tableData: [], //展示的数据
			tempData: [],
			select_word: '', //搜索关键词

			// 分页
			pageSize: 4, //每页大小
			currentPage: 1, //当前页码
			singerRowId: -1, //删除当前选择项
			multipleSelection: [], //标记勾选的项
		};
	},
	computed: {
		// 计算当前搜索结果表里的数据
		data() {
			return this.tableData.slice(
				(this.currentPage - 1) * this.pageSize,
				this.currentPage * this.pageSize
			);
		},
	},
	watch: {
		// 前端实现搜索功能
		//搜索框里面的内容发生变化的时候，搜索结果table列表的内容跟着它的内容发生变化
		select_word: function () {
			if (this.select_word == '') {
				this.tableData = this.tempData;
			} else {
				// 搜索关键词不为空，模糊筛选包含关键词的歌手
				this.tableData = [];
				for (let item of this.tempData) {
					if (item.name.includes(this.select_word)) {
						this.tableData.push(item);
					}
				}
			}
		},
	},
	created() {
		// 加载页面时，加载歌手数据
		this.getData();
	},
	methods: {
		//获取当前页
		handleCurrentChange(val) {
			this.currentPage = val;
		},
		// 查询歌手

		getData() {
			this.tempData = [];
			this.tableData = [];
			getAllSinger().then((res) => {
				this.tempData = res;
				console.log('res : ', res);
				this.tableData = res;
				// this.currentPage = 1;
			});
		},

		// 新增歌手
		addSinger() {
			// 转换日期
			// 或者在上面日期选择标签那加上：value-format = 'yyyy-MM-dd'
			let d = this.registerForm.birth;
			let datetime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
			// URLSearchParams API 用于处理 URL 之中的查询字符串，即？之后的部分。
			let params = new URLSearchParams();
			params.append('name', this.registerForm.name);
			params.append('sex', this.registerForm.sex);
			params.append('pic', '/img/singerPic/default.jpeg');
			params.append('birth', datetime);
			params.append('location', this.registerForm.location);
			params.append('introduction', this.registerForm.introduction);
			setSinger(params)
				.then((res) => {
					if (res.code == 1) {
						// this.getData();//添加成功查询一次
						this.$notify({
							title: '添加成功',
							type: 'success',
						});
						this.registerForm = {
							name: '',
							sex: '',
							birth: '',
							location: '',
							introduction: '',
						};
					} else {
						this.$notify({
							title: '添加失败',
							type: 'false',
						});
					}
				})
				.catch((err) => {
					console.log(err);
				});

			this.centerDialogVisible = false;
			this.getData();
		},

		// 弹出修改歌手
		handleEdit(row) {
			this.editVisible = true;
			this.form = { ...row };
		},
		//保存编辑页面修改的数据
		editSave() {
			let d = new Date(this.form.birth);
			let datetime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
			let params = new URLSearchParams();
			params.append('id', this.form.id);
			params.append('name', this.form.name);
			params.append('sex', this.form.sex);
			params.append('birth', datetime);
			params.append('location', this.form.location);
			params.append('introduction', this.form.introduction);

			updateSinger(params)
				.then((res) => {
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

		//更新歌手图片
		uploadUrl(id) {
			return `${this.$store.state.HOST}/singer/updateSingerPic?id=${id}`;
		},
		// 弹出删除歌手弹窗
		handleDelete(id) {
			this.singerRowId = id;
			this.delVisible = true;
		},
		//删除一名歌手
		delSingerRow() {
			var query = new URLSearchParams();
			query.append('id', this.singerRowId);
			removeSinger(query)
				.then((data) => {
					if (data) {
						this.getData();
						this.$message({
							message: '删除成功',
							type: 'success',
						});
					} else {
						this.$message({
							message: '删除失败',
							type: 'error',
						});
					}
				})
				// .catch((err) => {
				// 	console.log(err);
				// });
			this.delVisible = false;
		},
		// 跳转到歌曲管理页面
		songEdit(id, name) {
			this.$router.push({ path: `/Song`, query: { id, name } });
		},
		//批量删除已经选择的项
        delAll(){
            //遍历被选中的元素，循环删除
            for(let item of this.multipleSelection){
                //弹出删除窗口
                this.handleDelete(item.id);
                this.delSingerRow();
            }
            this.multipleSelection = [];
        }
	},
};
</script>

<style scoped lang="less">
.handle-box {
	margin-bottom: 0px;
	display: inline-block;
}
/* 歌手图片 */
.singer-img {
	width: 100%;
	height: 80px;
	border-radius: 5px;
	margin-bottom: 5px;
	overflow: hidden;
}

/* 搜索框 */
.handle-input {
	width: 300px;
	display: inline-block;
	margin-right: 10px;
}
/* 分页 */
.pagination {
	display: flex;
	justify-content: center;
}
.crumbs {
	margin: 20px;
}
</style>
