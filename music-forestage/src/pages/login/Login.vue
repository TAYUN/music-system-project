<template>
	<div class="container" :class="{ navigate: !isSignIn }">
		<div class="form-wrapper">
			<img src="../../assets/images/img1.png" class="img-left" />
			<img src="../../assets/images/img2.png" class="img-right" />
			<div class="form-wrapper-bg"></div>
			<div class="form-wrapper-left">
				<h1>Sign Up</h1>
				<el-form
					:model="ruleForm"
					status-icon
					:rules="rules"
					ref="ruleForm"
					label-width="100px"
					class="demo-ruleForm form-signup">
					<el-form-item label="用户名" prop="username">
						<el-input v-model="ruleForm.username"></el-input>
					</el-form-item>
					<el-form-item
						prop="email"
						label="邮箱"
						:rules="[
							{ required: true, message: '请输入邮箱地址', trigger: 'blur' },
							{
								type: 'email',
								message: '请输入正确的邮箱地址',
								trigger: ['blur', 'change'],
							},
						]">
						<el-input v-model="ruleForm.email"></el-input>
					</el-form-item>
					<el-form-item label="密码" prop="pass">
						<el-input
							type="password"
							v-model="ruleForm.pass"
							autocomplete="off"></el-input>
					</el-form-item>
					<el-form-item label="确认密码" prop="checkPass">
						<el-input
							type="password"
							v-model="ruleForm.checkPass"
							autocomplete="off"></el-input>
					</el-form-item>

					<el-form-item>
						<el-button type="primary" @click="handleSignUp('ruleForm')">注册</el-button>
						<el-button @click="resetForm('ruleForm')">重置</el-button>
					</el-form-item>
				</el-form>
				<a href="#" class="signin-link">登录</a>
			</div>
			<div class="form-wrapper-right">
				<h1>Sign In</h1>
				<el-form
					:model="loginForm"
					status-icon
					:rules="rules"
					ref="loginForm"
					label-width="100px"
					class="demo-ruleForm form-signup">
					<el-form-item label="用户名" prop="username">
						<el-input v-model="loginForm.username"></el-input>
					</el-form-item>

					<el-form-item label="密码" prop="password">
						<el-input
							type="password"
							v-model="loginForm.password"
							autocomplete="off"></el-input>
					</el-form-item>

					<el-form-item>
						<el-button type="primary" @click="handleSignIn('loginForm')">登录</el-button>
						<el-button @click="resetForm('loginForm')">重置</el-button>
					</el-form-item>
				</el-form>
				<a href="#" class="signup-link">注册</a>
			</div>
		</div>
	</div>
</template>

<script>
import { SignUp, loginIn } from '@/api';

export default {
	name: 'login',
	data() {
		var checkUser = (rule, value, callback) => {
			if (!value) {
				return callback(new Error('用户名不能为空'));
			} else {
				callback();
			}
		};

		var validatePass = (rule, value, callback) => {
			if (value === '') {
				callback(new Error('请输入密码'));
			} else {
				if (this.ruleForm.checkPass !== '') {
					this.$refs.ruleForm.validateField('checkPass');
				}
				callback();
			}
		};
		var validatePass2 = (rule, value, callback) => {
			if (value === '') {
				callback(new Error('请再次输入密码'));
			} else if (value !== this.ruleForm.pass) {
				callback(new Error('两次输入密码不一致!'));
			} else {
				callback();
			}
		};
		var checkLoginPass = (rule, value, callback) => {
			if (!value) {
				return callback(new Error('请输入密码'));
			} else {
				callback();
			}
		};
		return {
			signUpHandler: null,
			signInHandler: null,
			isSignIn: true,
			username: '',
			email: '',
			password: '',

			ruleForm: {
				pass: '',
				checkPass: '',
				username: '',
				email: '',
			},
			rules: {
				pass: [{ validator: validatePass, trigger: 'blur' }], //注册密码的规则
				checkPass: [{ validator: validatePass2, trigger: 'blur' }],
				username: [{ validator: checkUser, trigger: 'blur' }],
				password: [{ validator: checkLoginPass, trigger: 'blur' }], //登录密码的规则
			},
			loginForm: {
				username: '',
				password: '',
			},
		};
	},
	mounted() {
		const container = document.querySelector('.container');
		const signUpLink = document.querySelector('.signup-link');
		const signInLink = document.querySelector('.signin-link');

		this.signUpHandler = () => {
			container.classList.add('navigate');
		};

		this.signInHandler = () => {
			container.classList.remove('navigate');
		};

		signUpLink.addEventListener('click', this.signUpHandler);
		signInLink.addEventListener('click', this.signInHandler);
	},

	methods: {
		handleSignUp(formName) {
			// 处理注册逻辑
			let _this = this;

			this.$refs[formName].validate((valid) => {
				let _this_ = _this;
				if (!valid) {
					console.log('error submit!!');
					return false;
				} else {
					let params = new URLSearchParams();
					params.append('username', _this.ruleForm.username);
					params.append('password', _this.ruleForm.pass);
					params.append('email', _this.ruleForm.email);
					params.append('avator', '/img/user.jpeg');
					SignUp(params)
						.then((res) => {
							if (res.code == 1) {
								_this_.$notify({
									title: '注册成功,请登录',
									type: 'success',
								});
								_this_.isSignIn = false;
								// 提交登录状态
								// this.$store.commit('loginIn', true);
							} else {
								_this_.$notify({
									title: '注册失败:' + res.MSG,
									type: 'error',
								});
								console.log(res);
							}
						})
						.catch((err) => {
							_this_.$notify({
								title: '注册失败',
								type: 'error',
							});
							console.log('res : ', res);
						});
				}
			});
		},
		handleSignIn(formName) {
			// 处理登录逻辑
			let _this = this;
			this.$refs[formName].validate((valid) => {
				let _this_ = _this;
				if (!valid) {
					console.log('error submit!!');
					return false;
				} else {
					let params = new URLSearchParams();
					params.append('username', _this.loginForm.username);
					params.append('password', _this.loginForm.password);
					loginIn(params)
						.then((res) => {
							console.log(res);
							if (res.code == 1) {
								_this_.$notify({
									title: '欢迎你: ' + _this.loginForm.username,
									type: 'success',
								});
								// 提交登录状态
								_this.$store.commit('setLoginIn', true);
								_this.$store.commit('setUserId', res.userMsg.id);
								_this.$store.commit('setUsername', res.userMsg.username);
								_this.$store.commit('setAvator', res.userMsg.avator);

								_this.$router.push({ path: '/' });
								window.location.reload();
							} else {
								_this_.$notify({
									title: res.MSG,
									type: 'error',
								});
							}
						})
						.catch((err) => {
							_this_.$notify({
								title: '登录失败',
								type: 'error',
							});
							console.log('res : ', res);
						});
				}
			});
		},
		resetForm(formName) {
			this.$refs[formName].resetFields();
		},
	},
	// // 回收资源
	// beforeDestroy() {
	// 	const signUpLink = document.querySelector('.signup-link');
	// 	const signInLink = document.querySelector('.signin-link');

	// 	signUpLink.removeEventListener('click', this.signUpHandler);
	// 	signInLink.removeEventListener('click', this.signInHandler);
	// },
};
</script>
<style lang="css" scoped>
@import url('@/assets/css/login.css');
</style>
