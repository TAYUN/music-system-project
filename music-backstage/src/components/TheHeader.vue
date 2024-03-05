<template>
	<div class="header">
		<!-- 折叠图标 -->
		<div class="collapse-btn">
			<i class="el-icon-menu" @click="collapseChange"></i>
		</div>
		<div class="logo">音乐网站管理端</div>

		<!-- 右边 -->
		<div class="header-right">
			<div class="btn-fullscreen" @click="handleFullScreen">
				<el-tooltip :content="fullscreen ? `取消全屏` : `全屏`" placement="bottom">
					<i class="el-icon-rank"></i>
				</el-tooltip>
			</div>
			<!-- 头像 -->
			<div class="user-avator">
				<img src="../assets/images/user.jpg" />
			</div>
			<el-dropdown class="uer-name" trigger="click" @command="handleCommand">
				<span class="el-dropdown-link">
					{{ username }}
                    <i class="el-icon-caret-bottom"></i>
				</span>
				<el-dropdown-menu slot="dropdown">
					<el-dropdown-item command="logout">退出登录</el-dropdown-item>
				</el-dropdown-menu>
			</el-dropdown>
		</div>
	</div>
</template>

<script>
import bus from '@/assets/js/bus';

export default {
	data() {
		return {
			collapse: false,
			fullscreen: true,
		};
	},
	computed: {
		username() {
			return localStorage.getItem('username');
		},
	},
	methods: {
		// 折叠侧边栏
		collapseChange() {
			this.collapse = !this.collapse;
            // 调用bus提交collpase状态
			bus.$emit('collapse', this.collapse);
		},

		// 全屏，但是有bug，交叉使用esc推退出全屏有bug
		handleFullScreen() {
			if (this.fullscreen) {
				if (document.exitFullscreen) {
					document.exitFullscreen();
				} else if (document.webkitCancelFullScreen) {
					//safari 、Chrome
					document.webkitCancelFullScreen();
				} else if (document.mozCancelFullScreen) {
					//firefox
					document.mozCancelFullScreen();
				} else if (document.msExitFullScreen) {
					//ie
					document.msExitFullScreen();
				}
			} else {
				let element = document.documentElement;
				if (element.requestFullscreen) {
					element.requestFullscreen();
				} else if (element.webkitRequestFullScreen) {
					//safari 、Chrome
					element.webkitRequestFullScreen();
				} else if (element.mozRequestFullScreen) {
					//firefox
					element.mozRequestFullScreen();
				} else if (element.msRequestFullScreen) {
					//ie
					element.msRequestFullScreen();
				}
			}

			// 写法2
			// if (this.fullscreen) {
			// 	const full =
			// 		document.documentElement.requestFullscreen ||
			// 		document.documentElement.mozRequestFullScreen ||
			// 		document.documentElement.msRequestFullscreen ||
			// 		document.documentElement.webkitRequestFullscreen;
			// 	if (full) {
			// 		full.call(document.documentElement);
			// 	}
			// } else {
			// 	const exit =
			// 		document.exitFullscreen ||
			// 		document.msExitFullscreen ||
			// 		document.mozCancelFullScreen ||
			// 		document.webkitExitFullscreen;

			// 	if (exit) {
			// 		exit.call(document);
			// 	}
			// }

			this.fullscreen = !this.fullscreen;
		},
		handleCommand(command) {
			if (command == 'logout') {
				localStorage.removeItem('username');
				this.$router.push('/');
			}
		},
	},
};
</script>

<style scoped>
.header {
	position: relative;
	background-color: #409EFF;
	box-sizing: border-box;
	width: 100%;
	height: 70px;
	font-size: 22px;
	color: #ffffff;
}

.collapse-btn {
	float: left;
	padding: 0 21px;
	line-height: 70px;
}
.el-icon-menu {
	cursor: pointer;
}
/* 标题 */

.header .logo {
	float: left;
	line-height: 70px;
	font-weight: bold;
	cursor: default;
}
/* 右边 */
.header-right {
	float: right;
	padding-right: 50px;
	display: flex;
	height: 70px;
	align-items: center;
}
.btn-fullscreen {
	transform: rotate(45deg);
	margin-right: 5px;
	font-size: 24px;
}

/* 头像 */
.user-avator {
	margin:0  20px 0;
}

.user-avator img {
	display: block;
	width: 40px;
	height: 40px;
	border-radius: 50%;
}

/* 用户名 */
.user-name {
	margin-left: 10px;
}

.el-dropdown-link {
	color: #ffffff;
	cursor: pointer;
}
</style>
