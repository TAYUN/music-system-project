import Axios from 'axios';
import { get, post } from './http';

//============歌手相关================
//查询歌手
export const getAllSinger = () => get(`singer/allSinger`);
export const getSingerOfId = (id) => get(`singer/selectByPrimaryKey?id=${id}`);

//根据性别查询歌手
export const getSingerOfSex = (sex) => get(`singer/singerOfSex?sex=${sex}`);

//============歌曲相关================
//查询所有歌曲
export const getAllSong = () => get(`song/allSong`);
//根据歌手id查询歌曲
export const songOfSingerId = (id) => get(`song/singer/detail?singerId=${id}`);
//根据歌曲id查询歌曲对象
export const songOfSongId = (id) => get(`song/detail?songId=${id}`);
//根据歌曲名称模糊查询歌曲
export const likeSongOfName = (keywords) => get(`song/likeSongOfName?gqName=${keywords}`); 
//根据id记录歌曲被点击数
export const clickSongById = (id) => get(`song/clickSong?songId=${id}`); 

//下载歌曲  get文件流转成blob下载
export const download = (url)  => Axios({
    method: 'get',
    url: url,
    responseType: 'blob'
});

//============歌单相关================
//查询歌单
export const getAllSongList = () => get(`songList/allSongList`);
//根据歌单id查询歌单对象
export const getSongListOfId = (id) => get(`songList/detail?playlistId=${id}`);
// 根据用户id查询歌单
export const getSongListByUserId = (id) => get(`songList/userSongList?userId=${id}`);
// 根据用户id删除歌单
export const delSongListByUserId = (id) =>
	get(`songList/delSongListByUserId?userId=${id}`);
//返回标题包含文字的歌单列表
export const getSongListOfLikeTitle = (keywords) =>
	get(`songList/likeTitle?title=${keywords}`); //0
//根据风格模糊查询歌单列表
export const getSongListOfLikeStyle = (style) => get(`songList/likeStyle?style=${style}`); //0
//根据id记录歌单被点击数
export const clickSongListById = (id) => get(`songList/clickSongList?songListId=${id}`); 

//============歌单的歌曲相关============
//根据歌单id查询歌曲列表
export const listSongDetail = (songListId) =>
	get(`songListMusic/detail?songListId=${songListId}`);
export const listSongAdd = (params) => post(`songListMusic/add`, params);

//============用户相关================
//查询用户
export const getAllConsumer = () => get(`consumer/allConsumer`);
//注册
export const SignUp = (params) => post(`/consumer/add`, params); //0
//登录
export const loginIn = (params) => post(`/consumer/login`, params);
//根据用户id查询该用户的详细信息
export const getUserOfId = (id) => get(`/consumer/selectByPrimaryKey?id=${id}`);
//更新用户信息
export const updateUserInfo = (params) => post(`/consumer/update`, params);


//===========评价======================
//提交评分
export const setRank = (params) => post(`/rank/add`, params);
//获取指定歌单的平均分
export const getRankOfSongListId = (songListId) => get(`/rank?songListId=${songListId}`);

//===========评论======================
//提交评论
export const setComment = (params) => post(`/comment/add`, params);
//点赞
export const setLike = (params) => post(`/comment/like`, params);
//返回当前歌单或歌曲的评论列表
export const getAllComment = (type, id) => {
	if (type == 0) {
		//歌曲
		return get(`/comment/commentOfSongId?songId=${id}`);
	} else {
		//歌单
		return get(`/comment/commentOfSongListId?songListId=${id}`);
	}
};


//===============收藏歌单===================
//新增收藏
export const setCollect = (params) => post(`/collecttion/add`, params);
//指定用户的收藏列表
export const getCollectOfUserId = (userId) =>
	get(`/collection/collectOfUserId?userId=${userId}`);
// 新建收藏歌单
export const createMyCollection = (params) => post(`songList/add`, params);

