<template>
	<div class="login-wrap">
		<!-- <div class="video-box">
			<video
				class="video-background"
				preload="auto"
				loop
				playsinline
				autoplay
				src="@/assets/images/background.mp4"
				tabindex="-1"
				muted></video>
			</div> -->
		<!-- muted="muted"css样式静音播放 -->
		<!-- <div class="ms-title">音乐网站管理员登录</div> -->
		<div class="ms-login">
			<el-form :model="ruleForm" :rules="rules" ref="ruleForm">
				<el-form-item prop="username">
					<el-input v-model="ruleForm.username" placeholder="用户名"></el-input>
				</el-form-item>
				<el-form-item prop="password">
					<el-input
						type="password"
						v-model="ruleForm.password"
						placeholder="密码"
						@keyup.enter.native="submitForm"></el-input>
				</el-form-item>
				<div class="login-btn">
					<el-button type="primary" @click="submitForm">登录</el-button>
				</div>
			</el-form>
		</div>
	</div>
</template>

<script>
import { getLoginStatus } from '../api/index';
import { mixin } from '@/mixins';
export default {
	mixins: [mixin],
	data() {
		return {
			ruleForm: {
				username: 'admin',
				password: '1',
			},
			rules: {
				username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
				password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
			},
		};
	},
	methods: {
		submitForm() {
			let params = new URLSearchParams();
			params.append('name', this.ruleForm.username);
			params.append('password', this.ruleForm.password);
			getLoginStatus(params).then((res) => {
				console.log(res);
				if (res.code == 1) {
					// 登录成功将用户名放到缓存里
					localStorage.setItem('username', this.ruleForm.username);
					this.$router.push('/Info');
					this.notify('登录成功', 'success');
				} else {
					this.notify('用户名或密码错误', 'error');
				}
			});
		},
	},
};
</script>

<style scoped>
.login-wrap {
	position: relative;
	background: url('../assets/images/background.jpg');
	background-attachment: fixed;
	background-position: center;
	background-size: cover;
	width: 100%;
	height: 100%;
}
/* 背景 */
.video-box {
	position: relative;
	height: 100vh;
	background-color: #c1cff7;
	/*进行视频裁剪*/
	overflow: hidden;
	z-index: -1;
}

.video-box .video-background {
	position: absolute;
	left: 50%;
	top: 50%;
	/*保证视频内容始终居中*/
	transform: translate(-50%, -50%);
	width: 100%;
	height: 100%;
	/*保证视频充满屏幕*/
	object-fit: cover;
	min-height: 800px;
}
/* 标题 */
.ms-title {
	position: absolute;
	top: 50%;
	width: 300px;
	margin-left: 280px;
	margin-top: -230px;
	text-align: center;
	font-size: 30px;
	font-weight: 600;
	color: #fff;
}

/* 表单 */
.ms-login {
	position: absolute;
	left: 50%;
	top: 50%;

	width: 300px;
	height: 160px;
	margin-left: -150px;
	margin-top: -80px;
	padding: 40px;
	border-radius: 5px;
	background: #fff;
}
.login-btn {
	text-align: center;
}
.login-btn button {
	width: 100%;
	height: 36px;
}
</style>
