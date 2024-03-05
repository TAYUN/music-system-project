<template>
	<div>
		<div class="info">
			<!-- <div class="title">
				<span>编辑个人资料</span>
			</div> -->
			<div class="module-header">
				<div class="section"></div>
				<div class="sin-card">
					<el-upload
						:action="uploadUrl()"
						:show-file-list="false"
						:on-success="handleAvatorSuccess"
						:before-upload="beforeAvatorUpload">
						<img class="card-img" :src="modObj.pic" alt="" />
					</el-upload>
				</div>
				<!-- 右边介绍 -->
				<div class="module-info">
					<el-card class="box-card" shadow="never">
						<p class="card-title">{{ modObj.name }}</p>
						<p>{{ modObj.introduction }}</p>
					</el-card>
				</div>
			</div>

			<div class="personal">
				<el-form
					:model="userForm"
					ref="userForm"
					label-width="70px"
					class="demo-ruleForm"
					:rules="rules">
					<el-form-item prop="username" label="用户名">
						<el-input
							:disabled="true"
							v-model="userForm.username"
							placeholder="用户名"></el-input>
					</el-form-item>
					<el-form-item prop="password" label="密码">
						<el-input
							type="password"
							v-model="userForm.password"
							placeholder="密码"></el-input>
					</el-form-item>
					<el-form-item prop="sex" label="性别">
						<el-radio-group v-model="userForm.sex">
							<el-radio label="女" border size="small"></el-radio>
							<el-radio label="男" border size="small"></el-radio>
						</el-radio-group>
					</el-form-item>
					<el-form-item prop="phoneNum" label="手机">
						<el-input v-model="userForm.phoneNum" placeholder="手机"></el-input>
					</el-form-item>
					<el-form-item prop="email" label="邮箱">
						<el-input v-model="userForm.email" placeholder="邮箱"></el-input>
					</el-form-item>
					<el-form-item prop="birth" label="生日">
						<el-date-picker
							type="date"
							:editable="false"
							v-model="userForm.birth"
							placeholder="选择日期"
							style="width: 100%"></el-date-picker>
					</el-form-item>
					<el-form-item prop="introduction" label="签名">
						<el-input v-model="userForm.introduction" placeholder="签名"></el-input>
					</el-form-item>
					<el-form-item prop="location" label="地区">
						<el-select
							v-model="userForm.location"
							placeholder="地区"
							style="width: 100%">
							<el-option
								v-for="item in cities"
								:key="item.value"
								:label="item.label"
								:value="item.value"></el-option>
						</el-select>
					</el-form-item>
				</el-form>
				<div class="btn">
					<div @click="updateInfo">保存</div>
					<!-- <div @click="goback(-1)">取消</div> -->
					<div @click="logout">退出登录</div>
				</div>
			</div>
		</div>
	</div>
</template>
<script>
import { mapGetters } from 'vuex';
import { rules, cities } from '@/data/form';
import { getUserOfId, updateUserInfo } from '@/api/index';
import ModDetails from '@/components/mod-details.vue';

export default {
	name: 'user-info',
	inject: ['reload'],

	components: {
		ModDetails,
	},
	data() {
		return {
			userForm: {
				username: '', //用户名
				password: '', //密码
				sex: '', //性别
				phoneNum: '', //手机
				email: '', //邮箱
				birth: '', //生日
				introduction: '', //签名
				location: '', //地区
			},
			cities: [], //所有的地区--省
			rules: {}, //表单提交的规则
			modObj: {},
		};
	},
	computed: {
		...mapGetters(['userId']),
	},
	created() {
		this.rules = rules;
		this.cities = cities;
	},
	mounted() {
		this.getMsg(this.userId);
	},
	methods: {
		getMsg(userId) {
			getUserOfId(userId)
				.then((res) => {
					this.userForm.username = res.username;
					this.userForm.password = res.password;
					this.userForm.sex = res.sex ? '男' : '女';
					this.userForm.phoneNum = res.phoneNum;
					this.userForm.email = res.email;
					this.userForm.birth = res.birth;
					this.userForm.introduction = res.introduction;
					this.userForm.location = res.location;

					this.modObj.pic = this.getPicUrl(res.avator);
					this.modObj.name = res.username;
					this.modObj.introduction = res.introduction;
				})
				.catch((err) => {
					console.log(err);
				});
		},
		updateInfo() {
			var _ = this;//防闭包
			// this.userForm.sex === '男'
			// 	? (this.userForm.sex = true)
			// 	: (this.userForm.sex = false);
			var datetime = new Date(this.userForm.birth);
			// let d = new Date(this.userForm.birth);
			// let datetime = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate();
			var query = new URLSearchParams();
			query.append('id', this.userId);
			query.append('username', this.userForm.username);
			query.append('password', this.userForm.password);
			query.append('sex', this.userForm.sex === '男' ? true : false);
			query.append('phoneNum', this.userForm.phoneNum);
			query.append('email', this.userForm.email);
			query.append('birth', datetime);
			query.append('introduction', this.userForm.introduction);
			query.append('location', this.userForm.location);
			updateUserInfo(query)
				.then((data) => {
					if (data.code == 1) {
						_.$store.commit('setUsername', this.userForm.username);
						_.$message({
							message: '修改成功',
						});
						// setTimeout(function () {
						// 	_.getMsg(_.userId);
						// }, 2000);
					} else {
						_.$message({
							message: '修改出现错误',
							type: 'error',
						});
						
					}
				})
				.catch((err) => {
					_.$notify({
						title: '修改失败',
						type: 'error',
					});
				});
				this.refresh()
		},
		//上传地址
		uploadUrl() {
			return `${this.$store.state.configure.HOST}/consumer/updateConsumerPic?id=${this.userId}`;
		},
		//上传成功
		handleAvatorSuccess(res, file) {
			if (res.code == 1) {
				this.$store.commit('setAvator', res.avator);
				this.$notify({
					title: '修改成功',
					type: 'success',
				});
				// this.$router.go(0)
				// 刷新页面
				// console.log(this.$route.fullPath);
				// const { fullPath } = this.$route;
				// this.$router.replace({
				// 	path: '/empty' + fullPath,
				// });

				this.refresh()
				// window.location.reload();
			} else {
				this.$notify({
					title: '修改失败',
					type: 'error',
				});
			}
		},
		//上传之前的校验
		beforeAvatorUpload(file) {
			const isJPG =
				file.type == 'image/jpeg' ||
				file.type == 'image/jpg' ||
				file.type == 'image/png' ||
				file.type == 'image/gif';
			const isLt10M = file.size / 1024 / 1024 < 10;
			if (!isJPG) {
				this.$notify({
					title: '上传头像图片只能是 JPG/PNG/GIF/JPEG 格式',
					type: 'error',
				});
				return false;
			}
			if (!isLt10M) {
				this.$notify({
					title: '上传头像图片不能大于10MB',
					type: 'error',
				});
				return false;
			}
			return true;
		},
		goback(index) {
			this.$router.go(index);
		},
		// 退出登录
		logout() {
			this.$store.commit('setLoginIn', false);
			this.$store.commit('setIsActive', false);
			this.$store.commit('setUserId', '');
			this.$store.commit('setUsername', '');
			this.$store.commit('setAvator', '');
			this.$router.push({ path: '/login' });
			window.location.reload();
		},
		// 得到图片真实地址
		getPicUrl(picUrl) {
			if (!picUrl) {
				return this.$store.state.configure.HOST + '/img/userPic/user.jpeg';
			}
			return this.$store.state.configure.HOST + picUrl;
		},
		// 刷新路由组件函数
		refresh() {
			this.reload();
		},
	},
};
</script>

<style lang="scss" scoped>
@import '@/assets/css/info.scss';

// 父容器  布局

//样式
.module-header {
	display: flex;
	justify-content: flex-start;
	max-height: 220px;
	// 信息
	.module-info {
		padding: 10px 50px 10px 10px;

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
}

.sin-card {
	/* // 限制卡片大小 */
	max-width: 200px;
	padding: 10px 10px;
	border-radius: 5px;
	cursor: pointer;
	background: transparent;
	transition: transform 0.5s, background 0.5s;
	.card-img {
		min-width: 30px;
		width: 100%;
		border-radius: 14px;
		/* 设置过渡 */
		transition: 0.35s;
	}
}
</style>
