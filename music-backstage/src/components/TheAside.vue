<template>
	<div class="sidebar">
		<el-menu
			class="sidebar-el-menu"
			:default-active="onRoutes"
			:collapse="collapse"
			background-color="#eee"
			text-color="#000"
			active-text-color="#20a0ff"
			router>
            <!-- 需要用到下面的变量的时候属性前面用“:”，如:default-active="onRoutes"，onRoutes是计算变量 -->
			<template v-for="item in items">
				<template>
					<el-menu-item :index="item.index" :key="item.index">
						<i :class="item.icon"></i>
						<span slot="title">{{item.title}}</span>
					</el-menu-item>
				</template>
			</template>
		</el-menu>
	</div>
</template>

<script>
import  bus  from "@/assets/js/bus.js";
export default {
	data() {
		return {
			collapse: false,
			items: [
				{
					icon: 'el-icon-s-home',
					index: 'Info',
					title: '系统首页',
				},
				{
					icon: 'el-icon-user',
					index: 'Consumer',
					title: '用户管理',
				},
				{
					icon: 'el-icon-s-custom',
					index: 'Singer',
					title: '歌手管理',
				},
				{
					icon: 'el-icon-s-fold',
					index: 'SongList',
					title: '歌单管理',
				},
				
				{
					icon: 'el-icon-headset',
					index: 'Song',
					title: '歌曲管理',
				},
				
			],
		};
	},
    computed:{
        // 计算属性  计算active选中的菜单
        onRoutes(){
            //$router指整个路由，$route指但钱活跃路由
            return this.$route.path.replace('/','');
        }
    },
	created(){
		//通过bus进行组件间通信，折叠侧边菜单
		bus.$on('collapse', msg => {
			this.collapse = msg
		})
	}

};
</script>

<style scoped>
.sidebar {
	display: block;
	position: absolute;
	left: 0;
	top: 70px;
	bottom: 0;

	overflow-y: scroll;
	
}
/* 去掉滚动条 */
.sidebar::-webkit-scrollbar{
    width: 0;
}
/* 当collapse为false时起作用 */
.sidebar-el-menu:not(.el-menu--collapse){
    width: 150px;
}
/* 调整菜单字体样式 */
.sidebar-el-menu{
  font-weight: bold;
  line-height: 1px;
  letter-spacing:1px;
  
}
/* 让菜单撑满父容器 */
.sidebar >ul {
    height: 100%;

}

</style>
