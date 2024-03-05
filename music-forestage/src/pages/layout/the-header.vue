<template>
	<!-- 头部搜索栏 -->
	<div class="header-search">
		<!-- <div class="header-menu">
			<span>首 页</span>
			<span>歌 手</span>
			<span>歌 单</span>
			<span>收 藏</span>
		</div> -->

		<el-menu
			class="aside-menu"
			:default-openeds="['2']"
			router
			mode="horizontal"
			active-text-color="#20a0ff"
			:default-active="this.$route.path">
			<!-- <el-menu-item class="user-info" :index="items.myInfo" :key="items.myInfo"
				><el-avatar
					class="user-avatar"
					:size="user.size"
					:src="user.circleUrl"></el-avatar>
			</el-menu-item> -->

			<el-menu-item :index="items.homePage" :key="items.homePage"
				><i class="el-icon-s-home"></i>首页</el-menu-item
			>
			<el-menu-item :index="items.singer" :key="items.singer">歌手</el-menu-item>
			<el-menu-item :index="items.playlist" :key="items.playlist">歌单</el-menu-item>
			<el-menu-item
				:index="items.myMusic"
				:key="items.myMusic"
				:route="{ path: items.myMusic, query: { userId: user.userId } }"
				>我的歌单
			</el-menu-item>
			<!-- <el-menu-item :index="items.homePage" :key="items.homePage"><i class="el-icon-message"></i>我的信息</el-menu-item> -->
		</el-menu>

		<search-box class="search-box"></search-box>
		<div class="user-avatar" @click="enterInfo">
			<!-- <el-avatar> {{ user.username }} </el-avatar> -->
			<el-avatar class="user-avatar" :size="user.size" :src="user.circleUrl"></el-avatar>
		</div>
	</div>
</template>

<script>
import SearchBox from '@/pages/search/search-box.vue';
import { mapGetters } from 'vuex';
export default {
	name: 'the-header',
	components: {
		SearchBox,
	},
	computed: {
		...mapGetters(['loginIn','avator',]),
	},
	data() {
		return {
			keywords: '',
			user: {
				userId: this.$store.state.user.userId,
				username: this.$store.state.user.username,
				size: 30,
				circleUrl:
					this.$store.state.configure.HOST + this.$store.state.user.avator ||
					'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
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
	methods: {
		enterInfo() {
			if (this.loginIn) {
				this.$router.push({
					path: '/user-info',
					query: { username: this.$store.state.user.username },
				});
			} else {
				this.$router.push({ path: '/Login' });
			}
		},
	},
};
</script>

<style lang="scss" scoped>
.aside-menu{
	width: 330px;
	height: 60px;
}
.header-search {
	height: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 0 40px;
}
.header-menu {
	span {
		margin-left: 20px;
		cursor: pointer;
	}
}
.user-avatar {
	cursor: pointer;
	margin: 0 10px;
}

.search-box {
	width: 50%;
}
</style>
