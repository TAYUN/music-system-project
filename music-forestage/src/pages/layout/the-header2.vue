<template>
	<!-- 头部 -->
	<div class="header-search">
		<el-menu
			class="aside-menu"
			:default-openeds="['2']"
			router
			mode="horizontal"
			active-text-color="#20a0ff"
			:default-active="this.$route.path">
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
			<el-menu-item
			
			></el-menu-item>
		</el-menu>

		<search-box class="search-box"></search-box>
		<div class="user-avatar" @click="enterInfo">
			<!-- <el-avatar> {{ user.username }} </el-avatar> -->
			<el-avatar class="user-avatar" :size="user.size" :src="user.circleUrl"></el-avatar>
		</div>

		<el-menu text-color="#2d2d2d" id="navid" class="nav" mode="horizontal">
			<el-menu-item class="logo" index="0" route="/home">
				<span>+++</span>
			</el-menu-item>
			<el-menu-item
				:key="key"
				v-for="(item, key) in leftNavItems"
				:index="item.index"
				:route="item.activeIndex"
				>{{ item.name }}</el-menu-item
			>
			<el-submenu
				style="float: right"
				class="right-item"
				v-if="Object.keys(rightNavItems).length === 0 ? false : true"
				index="10">
				<template slot="title">
					<i class="el-icon-s-fold" style="font-size: 28px; color: #2d2d2d"></i>
				</template>
				<el-menu-item
					:key="key"
					v-for="(item, key) in rightNavItems"
					:index="item.index"
					:route="item.activeIndex"
					>{{ item.name }}</el-menu-item
				>
			</el-submenu>
		</el-menu>
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
		...mapGetters(['loginIn', 'avator']),
	},
	data() {
		return {
			keywords: '',
			user: {
				userId: this.$store.state.user.userId,
				username: this.$store.state.user.username,
				size: 50,
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

			navItems: [
				{ name: 'Home', indexPath: '/home', index: '1' },
				{ name: 'Subscribe', indexPath: '/subscribe', index: '2' },
				{ name: 'About', indexPath: '/about', index: '3' },
				{ name: 'More', indexPath: '/more', index: '4' },
			],
			screenWidth: document.body.clientWidth,
			timer: null,
		};
	},
	mounted() {
		window.onresize = () => {
			this.screenWidth = document.body.clientWidth;
		};
	},
	computed: {
		leftNavItems: function () {
			return this.screenWidth >= 600 ? this.navItems : {};
		},
		rightNavItems: function () {
			return this.screenWidth < 600 ? this.navItems : {};
		},
	},

	watch: {
		screenWidth(newValue) {
			// 为了避免频繁触发resize函数导致页面卡顿，使用定时器

			if (!this.timer) {
				// 一旦监听到的screenWidth值改变，就将其重新赋给data里的screenWidth
				this.screenWidth = newValue;
				this.timer = true;
				setTimeout(() => {
					//console.log(this.screenWidth);
					this.timer = false;
				}, 400);
			}
		},
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
.aside-menu {
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
