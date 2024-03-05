<template>
	<!-- 头部搜索栏 -->
	<div class="header-search">
		<el-menu
			class="aside-menu"
			:default-openeds="['2']"
			router
			mode="horizontal"
			active-text-color="#20a0ff"
			:default-active="this.$route.path">
			<template v-if="!isFild">
				<el-menu-item
					:key="key"
					v-for="(item, key) in navItems"
					:index="item.indexPath"
					:route="
						3 === key ? { path: item.indexPath, query: { userId: user.userId } } : ''
					"
					>{{ item.name }}</el-menu-item
				>
			</template>
			<el-submenu index="10" v-if="isFild">
				<template slot="title"><i class="el-icon-menu"></i></template>
				<el-menu-item
					:key="key"
					v-for="(item, key) in navItems"
					:index="item.indexPath"
					:route="
						3 === key ? { path: item.indexPath, query: { userId: user.userId } } : ''
					"
					>{{ item.name }}</el-menu-item
				>
			</el-submenu>
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
			navItems: [
				{ name: '首页', indexPath: '/', index: 0 },
				{ name: '歌手', indexPath: '/singer-list', index: 1 },
				{ name: '歌单', indexPath: '/playlist-list', index: 2 },
				{ name: '我的歌单', indexPath: '/user-collection', index: 3 },
			],
			screenWidth: 1,
			timer: null,
			show: false,
		};
	},

	mounted() {
		this.screenWidth = 0;

		window.onresize = () => {
			this.screenWidth = document.body.clientWidth;
			console.log('document.body.clientWidth : ', document.body.clientWidth);
			console.log('this.screenWidth : ', this.screenWidth);
		};
		// this.show = false;
	},
	computed: {
		isFild: function () {
			if (this.screenWidth <= 750) {
				if (this.navItems.length === 4) {
					let obj = { name: '我的', indexPath: '/user-info', index: 5 };
					this.navItems.push(obj);
				}
				this.show = true;
				return true;
			} else {
				this.navItems.length = 4;
				this.show = false;
				return false;
			}
		},
	},

	watch: {
		screenWidth(newValue) {
			// 为了避免频繁触发resize函数导致页面卡顿，使用定时器
			console.log('newValue : ', newValue);

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
			console.log('this.loginIn : ',this.loginIn)
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

// 导航栏 element ui内部样式
.el-dropdown-menu__item,
.el-menu-item {
	padding: 0 0 0 0;
	margin-right: 20px !important;
}
:deep(.el-submenu__title) {
	padding: 0 0 0 0;
	margin: 0 10px 0;
}
</style>
