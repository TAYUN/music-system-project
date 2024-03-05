<template>
	<!-- 左侧菜单栏 -->
	<el-menu
		class="aside-menu"
		:default-openeds="['1', '2']"
		router
		:default-active="this.$route.path">
		<el-menu-item class="user-info" :index="items.myInfo" :key="items.myInfo"
			><el-avatar class="user-avatar" :size="user.size" :src="user.circleUrl"></el-avatar>
			<span class="user-name">{{ user.username }} </span>
			<i class="el-icon-view"></i>
		</el-menu-item>

		<template>
			<el-menu-item :index="items.homePage" :key="items.homePage"
				><i class="el-icon-s-home"></i>首页</el-menu-item
			>
			<el-submenu index="2">
				<template slot="title"><i class="el-icon-headset"></i>发现</template>
				<el-menu-item :index="items.singer" :key="items.singer">歌手</el-menu-item>
				<el-menu-item :index="items.playlist" :key="items.playlist">歌单</el-menu-item>
			</el-submenu>
			<el-menu-item :index="items.myMusic" :key="items.myMusic"
				><i class="el-icon-star-on"></i>我的收藏
			</el-menu-item>
			<!-- <el-menu-item :index="items.homePage" :key="items.homePage"><i class="el-icon-message"></i>我的信息</el-menu-item> -->
		</template>
	</el-menu>
</template>
<script>
export default {
	name: 'left-menu',
	data() {
		return {
			user: {
				username: '缘缘堂',
				size: 60,
				circleUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
			},
			items: {
				homePage: '/',
				singer: '/singer-list',
				playlist: '/playlist-list',
				myMusic: '/user-collection',
				myInfo: '/user-info',
			},
		};
	},
	methods: {},
};
</script>

<style lang="scss" scoped>
.aside-menu {
	height: 100%;
	padding-top: 40px;
	box-sizing: border-box;
	background-color: #f2f3f5;
}

.user-info {
	padding: 10px 30px 10px 10px;
	margin-bottom: 10px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	font-size: 18px;
	box-sizing: border-box;
	cursor: pointer;
}
</style>
