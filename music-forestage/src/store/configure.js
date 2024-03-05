const configure = {
	state: {
		HOST: 'http://127.0.0.1:8888', //后台访问地址根目录
		activeName: '', //当前选中的菜单名
		isActive: false, //当前歌曲是否已收藏
		showAside: false, //是否显示播放中的歌曲列表
		loginIn: false, //用户是否已登录
	},

	getters: {
		// 返回当前点击的东西
		activeName: (state) => {
			let activeName = state.activeName;
			if (!activeName) {
				activeName = JSON.parse(window.sessionStorage.getItem('activeName'));
			}
			return activeName;
		},
		isActive: (state) => {
			let isActive = state.isActive;
			if (!isActive) {
				isActive = JSON.parse(window.sessionStorage.getItem('isActive'));
			}
			return isActive;
		},
		showAside: (state) => {
			let showAside = state.showAside;
			if (!showAside) {
				showAside = JSON.parse(window.sessionStorage.getItem('showAside'));
			}
			return showAside;
		},
		loginIn: (state) => {
			let loginIn = state.loginIn;
			if (!loginIn) {
				loginIn = JSON.parse(window.sessionStorage.getItem('loginIn'));
			}
			return loginIn;
		},
	},
	mutations: {
		// 存储高亮
		setActiveName: (state, activeName) => {
			state.activeName = activeName;
			window.sessionStorage = setItem('activeName', JSON.stringify(activeName));
		},
		//当前歌曲是否已收藏
		setIsActive: (state, isActive) => {
			state.isActive = isActive;
			window.sessionStorage.setItem('isActive', JSON.stringify(isActive));
		},
		setShowAside: (state, showAside) => {
			state.showAside = showAside;
			window.sessionStorage.setItem('showAside', JSON.stringify(showAside));
		},
		setLoginIn: (state, loginIn) => {
			state.loginIn = loginIn;
			window.sessionStorage.setItem('loginIn', JSON.stringify(loginIn));
		},
	},
};

export default configure;
