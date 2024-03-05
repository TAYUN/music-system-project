import Vue from 'vue';
import Router from 'vue-router';

import UserCollection from '@/pages/user/user-collection';
import UserInfo from '@/pages/user/user-info';
import Home from '@/pages/index';
import SingerList from '@/pages/musiclib/singer/singer-list'
import SingerDetails from '@/pages/musiclib/singer/singer-details'
import SongList from '@/pages/musiclib/song/song-list' 
import PlayListList from '@/pages/musiclib/playlist/playlist-list'
import PlayListDetails from '@/pages/musiclib/playlist/playlist-details'
import searchResult from '@/pages/search/search-result'
import Login from '@/pages/login/Login'
// import Logon from '@/pages/login/Logon'
import Empty from '@/pages/user/empty'

Vue.use(Router);

export default new Router({
	routes: [
		// 首页
		{
			path: '/',
			name: 'home',
			component: Home,
		},
		// 用户信息
		{
			path: '/user-info',
			name: 'user-info',
			component: UserInfo,
		},
		// 用户收藏
		{
			path: '/user-collection',
			name: 'user-collection',
			component: UserCollection,
		},
		// 歌手列表
		{
            path: '/singer-list',
            name: 'singer-list',
            component: SingerList,
        },
		// 歌手详情
		{
            path: '/singer-details',
            name: 'singer-details',
            component: SingerDetails,
        },
		// 歌曲列表
		{
            path: '/song-list',
            name: 'song-list',
            component: SongList,
        },
		// 歌曲列表
		{
            path: '/playlist-list',
            name: 'playlist-list',
            component: PlayListList,
        },
		// 歌单详情
		{
            path: '/playlist-details',
            name: 'playlist-details',
            component: PlayListDetails,
        },
		
		// 搜索结果页面
        {
            path: '/search',
            name: 'search',
            component: searchResult,
        },
		// 登录注册
        {
            path: '/login',
            name: 'login',
            component: Login,
        },
		// // 登录注册
        // {
        //     path: '/logon',
        //     name: 'logon',
        //     component: Logon,
        // },
        // 空白页
        {
            path: '/empty',
            name: 'empty',
            component: Empty,
        },

	],
});
