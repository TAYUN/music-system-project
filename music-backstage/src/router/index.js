import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
	routes: [
		{
			path: '/',
			component: (resolve) => require(['@/views/Login.vue'], resolve),
		},
		{
			path: '/Home',
			component: (resolve) => require(['@/components/Home.vue'], resolve),
			children: [
				{
					path: '/Info',
					component: (resolve) => require(['@/views/InfoPage.vue'], resolve),
				},
				// 普通用户管理页面
				{
					path: '/Consumer',
					component: (resolve) => require(['@/views/ConsumerPage.vue'], resolve),
				},
				// 用户歌曲歌单收藏管理页面
				{
					path: '/Collection',
					component: (resolve) => require(['@/views/CollectPage.vue'], resolve),
				},
				// 歌手管理页面
				{
					path: '/Singer',
					component: (resolve) => require(['@/views/SingerPage.vue'], resolve),
				},
				// 歌单管理页面
				{
					path: '/SongList',
					component: (resolve) => require(['@/views/SongListPage.vue'], resolve),
				},
				// 歌曲管理页面
				{
					path: '/Song',
					component: (resolve) => require(['@/views/SongPage.vue'], resolve),
				},
				// 歌单中的歌曲管理页面
				{
					path: '/SongListMusic',
					component: (resolve) => require(['@/views/SongListMusicPage.vue'], resolve),
				},
				//   评论
				{
					path: '/Comment',
					component: (resolve) => require(['@/views/CommentPage.vue'], resolve),
				},
			],
		},
	],
});
