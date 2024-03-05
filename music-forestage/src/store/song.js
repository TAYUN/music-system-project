const song = {
	state: {
		id: '', //歌曲id
		listOfSongs: [], //当前歌曲列表
		isPlay: null, //是否播放中
		playingSong: null, //正在播放的歌曲对象
		currentSongId: '', // 当前正在播放的音乐的 ID 和id重复
		gqUrl: '', //歌曲地址
		playButtonUrl: '#icon-bofang', //播放状态的图标
		duration: 0, //音乐时长
		currentTime: 0, //当前音乐的播放位置
		changeTime: 0, //指定播放时刻
		gqName: '', //歌名
		gqSingerName: '', //歌手名
		gqPic: '', //歌曲图片
		autoNext: true, //用于自动触发播放下一首
		gqLyric: [], //歌词数据
		tempList: {}, //单个歌单信息或歌手信息
		rowIndex: null, //当前歌曲在歌单中的位置
		volume: 30, //音量
		recentSongs: [], //最近播放歌单
		// showLyric: false, //是否显示歌词
		favorite: false, //是否收藏喜欢
	},
	getters: {
		// getShowLyric: (state) => {
		// 	let showLyric = state.showLyric;
		// 	if (!showLyric) {
		// 		showLyric = JSON.parse(window.sessionStorage.getItem('showLyric') || null);
		// 	}
		// 	return showLyric;
		// },
		getFavorite: (state) => {
			let favorite = state.favorite;
			if (!favorite) {
				favorite = JSON.parse(window.sessionStorage.getItem('favorite') || null);
			}
			return favorite;
		},
		// 前正在播放的音乐的 ID
		currentSongId: (state) => {
			let currentSongId = state.currentSongId;
			if (!currentSongId) {
				currentSongId = JSON.parse(
					window.sessionStorage.getItem('currentSongId') || null
				);
			}
			return currentSongId;
		},
		getListOfSongs: (state) => {
			let listOfSongs = state.listOfSongs;
			console.log(
				'listOfSongs缓存：',
				JSON.parse(window.sessionStorage.getItem('listOfSongs') || null)
			);
			if (!listOfSongs.length) {
				listOfSongs = JSON.parse(window.sessionStorage.getItem('listOfSongs') || null);
			}
			return listOfSongs;
		},
		getPlayingSong: (state) => {
			let playingSong = state.playingSong;
			if (!playingSong) {
				// 根据当前正在播放的音乐的 ID 从 listOfSongs 中获取对应的音乐对象
				// playingSong = state.listOfSongs.find((item) => item.id === state.currentSongId);
				playingSong = JSON.parse(window.sessionStorage.getItem('playingSong') || null);
			}
			console.log('playingSong : ', playingSong);
			return playingSong;
		},
		isPlay: (state) => {
			let isPlay = state.isPlay;
			if (!isPlay) {
				isPlay = JSON.parse(window.sessionStorage.getItem('isPlay') || null);
			}
			return isPlay;
		},
		gqUrl: (state) => {
			let gqUrl = state.gqUrl;
			if (!gqUrl) {
				gqUrl = JSON.parse(window.sessionStorage.getItem('gqUrl') || null);
			}
			return gqUrl;
		},
		id: (state) => {
			let id = state.id;
			if (!id) {
				id = JSON.parse(window.sessionStorage.getItem('id') || null);
			}
			return id;
		},
		playButtonUrl: (state) => {
			let playButtonUrl = state.playButtonUrl;
			if (!playButtonUrl) {
				playButtonUrl = JSON.parse(
					window.sessionStorage.getItem('playButtonUrl') || null
				);
			}
			return playButtonUrl;
		},
		setDuration: (state) => {
			let duration = state.duration;
			if (!duration) {
				duration = JSON.parse(window.sessionStorage.getItem('duration') || null);
			}
			return duration;
		},
		getCurrentTime: (state) => {
			let currentTime = state.currentTime;
			if (!currentTime) {
				currentTime = JSON.parse(window.sessionStorage.getItem('currentTime') || null);
			}
			return currentTime;
		},
		changeTime: (state) => {
			let changeTime = state.changeTime;
			if (!changeTime) {
				changeTime = JSON.parse(window.sessionStorage.getItem('changeTime') || null);
			}
			return changeTime;
		},
		gqName: (state) => {
			let gqName = state.gqName;
			if (!gqName) {
				gqName = JSON.parse(window.sessionStorage.getItem('gqName') || null);
			}
			return gqName;
		},
		gqSingerName: (state) => {
			let gqSingerName = state.gqSingerName;
			if (!gqSingerName) {
				gqSingerName = JSON.parse(window.sessionStorage.getItem('gqSingerName') || null);
			}
			return gqSingerName;
		},
		gqPic: (state) => {
			let gqPic = state.gqPic;
			if (!gqPic) {
				gqPic = JSON.parse(window.sessionStorage.getItem('gqPic') || null);
			}
			return gqPic;
		},
		autoNext: (state) => {
			let autoNext = state.autoNext;
			if (!autoNext) {
				autoNext = JSON.parse(window.sessionStorage.getItem('autoNext') || null);
			}
			return autoNext;
		},
		getGqLyric: (state) => {
			let gqLyric = state.gqLyric;
			if (!gqLyric) {
				gqLyric = JSON.parse(window.sessionStorage.getItem('gqLyric') || null);
			}
			return gqLyric;
		},
		tempList: (state) => {
			let tempList = state.tempList;
			if (!tempList) {
				tempList = JSON.parse(window.sessionStorage.getItem('tempList') || null);
			}
			return tempList;
		},
		rowIndex: (state) => {
			let rowIndex = state.rowIndex;
			if (!rowIndex) {
				rowIndex = JSON.parse(window.sessionStorage.getItem('rowIndex') || null);
			}
			return rowIndex;
		},
		volume: (state) => {
			let volume = state.volume;
			if (!volume) {
				volume = JSON.parse(window.sessionStorage.getItem('volume') || null);
			}
			return volume;
		},
		// 最近播放
		recentSongs: (state) => {
			let recentSongs = state.recentSongs;
			if (!recentSongs) {
				recentSongs = JSON.parse(window.sessionStorage.getItem('recentSongs') || null);
			}
			return recentSongs;
		},
	},
	mutations: {
		// setShowLyric: (state, showLyric) => {
		// 	state.showLyric = showLyric;
		// 	window.sessionStorage.setItem('showLyric', JSON.stringify(showLyric));
		// },
		setFavorite: (state, favorite) => {
			state.favorite = favorite;
			window.sessionStorage.setItem('favorite', JSON.stringify(favorite));
		},

		// 设置当前播放音乐的id
		setCurrentSongId(state, songId) {
			state.currentSongId = songId;
			window.sessionStorage.setItem('playingSong', JSON.stringify(songId));
		},
		setIsPlay: (state, isPlay) => {
			state.isPlay = isPlay;
			window.sessionStorage.setItem('isPlay', JSON.stringify(isPlay));
		},
		setUrl: (state, gqUrl) => {
			state.gqUrl = gqUrl;
			window.sessionStorage.setItem('gqUrl', JSON.stringify(gqUrl));
		},
		setId: (state, id) => {
			state.id = id;
			window.sessionStorage.setItem('id', JSON.stringify(id));
		},
		setPlayButtonUrl: (state, playButtonUrl) => {
			state.playButtonUrl = playButtonUrl;
			window.sessionStorage.setItem('playButtonUrl', JSON.stringify(playButtonUrl));
		},
		setDuration: (state, duration) => {
			state.duration = duration;
			window.sessionStorage.setItem('duration', JSON.stringify(duration));
		},
		setCurrentTime: (state, currentTime) => {
			state.currentTime = currentTime;
			window.sessionStorage.setItem('currentTime', JSON.stringify(currentTime));
		},
		setChangeTime: (state, changeTime) => {
			state.changeTime = changeTime;
			window.sessionStorage.setItem('changeTime', JSON.stringify(changeTime));
		},
		setTitle: (state, gqName) => {
			state.gqName = gqName;
			window.sessionStorage.setItem('gqName', JSON.stringify(gqName));
		},
		setArtist: (state, gqSingerName) => {
			state.gqSingerName = gqSingerName;
			window.sessionStorage.setItem('gqSingerName', JSON.stringify(gqSingerName));
		},
		setPicUrl: (state, gqPic) => {
			state.gqPic = gqPic;
			window.sessionStorage.setItem('gqPic', JSON.stringify(gqPic));
		},
		setAutoNext: (state, autoNext) => {
			state.autoNext = autoNext;
			window.sessionStorage.setItem('autoNext', JSON.stringify(autoNext));
		},
		setGqLyric: (state, gqLyric) => {
			state.gqLyric = gqLyric;
			window.sessionStorage.setItem('gqLyric', JSON.stringify(gqLyric));
		},
		setTempList: (state, tempList) => {
			state.tempList = tempList;
			window.sessionStorage.setItem('tempList', JSON.stringify(tempList));
		},
		setListIndex: (state, rowIndex) => {
			state.rowIndex = rowIndex;
			window.sessionStorage.setItem('rowIndex', JSON.stringify(rowIndex));
		},
		setVolume: (state, volume) => {
			state.volume = volume;
			window.sessionStorage.setItem('volume', JSON.stringify(volume));
		},
		// 正在播放的歌曲对象
		setPlayingSong: (state, song) => {
			state.playingSong = song;
			window.sessionStorage.setItem('playingSong', JSON.stringify(song));
		},
		// 设置当前播放列表
		setListOfSongs: (state, listOfSongs) => {
			state.listOfSongs = listOfSongs;
			window.sessionStorage.setItem('listOfSongs', JSON.stringify(listOfSongs));
		},
		// 设置点击播放到最前
		addSong: (state, song) => {
			const index = state.listOfSongs.findIndex((s) => s.id === song.id);

			if (index !== -1) {
				// 如果歌曲已经存在于最近播放列表中，则将其移动到列表开头
				state.listOfSongs.splice(index, 1);
			}
			// 将歌曲添加到最近播放列表的开头
			state.listOfSongs.unshift(song);
			// state.commit('setCurrentSong', song);
			// context.dispatch('addCurrentSong', song);
		},
		setCurrentSong: (state, song) => {
			// 最近播放列表为空（长度为 0）直接添加
			console.log('song : ',song)
			console.log('state : ',state)
			let list = JSON.parse(JSON.stringify(state.recentSongs))
			console.log('state.recentSongs : ',state.recentSongs)
			console.log('list : ',list)
			if(list[0] === null || list[0] === undefined){
				state.recentSongs.pop()
				list.pop()
			}
			console.log('state.recentSongs222222222222 : ',state.recentSongs)

			if (state.recentSongs.length === 0) {
				state.recentSongs.unshift(song);
				console.log('state.recentSongs.l : ',state.recentSongs)
				return;
			}
			// 如果最近播放列表有歌曲
			const index = state.recentSongs.findIndex((s) => {
				console.log('s : ',s)
				return s.id === song.id;
			});

			// 如果当前播放的歌曲不在最近佛放列表里

			if (index === -1) {
				state.recentSongs.unshift(song);
				// 如果当前播放的歌曲在最近佛放列表里
			} else {
				// 如果当前播放的歌曲与要设置的歌曲相同并且与最近播放列表中的第一首相同，则不需要做任何操作
				if (state.playingSong.id === song.id && state.recentSongs[0].id === song.id) {
					return;
				}
				// 将当前播放的歌曲添加到最近播放列表的开头
				state.recentSongs.splice(index, 1);
				state.recentSongs.unshift(song);
			}

			// 最近播放列表保持不超过 50 首歌曲
			if (state.recentSongs.length > 50) {
				state.recentSongs.pop();
			}

			// 设置新的当前歌曲为播放状态
			state.playingSong = song;

			// 存储最近播放列表到 sessionStorage 中
			window.sessionStorage.setItem('recentSongs', JSON.stringify(state.recentSongs));
		},
		// 加载最近播放列表中的数据
		loadRecentSongs: (state) => {
			const recentSongs = JSON.parse(window.sessionStorage.getItem('recentSongs'));
			if (recentSongs && recentSongs.length > 0) {
				state.recentSongs = recentSongs;
			}
		},
	},
	// 辅助函数
	// actions: {
	// 	addCurrentSong: ({ commit }, song) => {
	// 		commit('setCurrentSong', song);
	// 	},
	// },
};

export default song;
