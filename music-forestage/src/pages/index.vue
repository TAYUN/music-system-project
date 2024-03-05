<template>
	<div>
		<!-- 轮播图 -->
		<div class="index-swiper">
			<el-carousel indicator-position="outside" type="card" height="200px">
				<el-carousel-item v-for="(item, index) in swiperList" :key="index">
					<img :src="item.picImg" alt="" @click="goDetails(item)" />
				</el-carousel-item>
			</el-carousel>
		</div>
		<!-- 推荐歌单歌曲 -->
		<div id="search-result">
			<template>
				<el-tabs v-model="activeName" >
					
					<el-tab-pane label="推荐歌曲" name="first">
						
						<song-list />
					</el-tab-pane>
					<el-tab-pane label="推荐歌单" name="second"><playlist-list /></el-tab-pane>
				</el-tabs>
			</template>
		</div>
	</div>
</template>

<script>
// import { swiperList } from '@/data/swiper.js';
import SongList from '@/pages/musiclib/song/song-list.vue'
import PlaylistList from '@/pages/musiclib/playlist/playlist-list.vue'
export default {
	name: 'musicIndex',
    components: {
        SongList,
        PlaylistList,
	},
	data() {
		return {
			activeName: 'first',
			swiperList: [
				{ id: 11, picImg: require('@/assets/images/swiper/1.png') },
				{ id: 11, picImg: require('@/assets/images/swiper/2.png') },
				{ id: 11, picImg: require('@/assets/images/swiper/3.png') },
				{ id: 11, picImg: require('@/assets/images/swiper/4.png') },
				{ id: 11, picImg: require('@/assets/images/swiper/5.png') },
			],

		};
	},
	methods: {
		goDetails(data) {
			console.log('data : ', data);
			this.$router.push({ path: '/playlist-details', query: { playlistId: data.id } });
		},
	},
};
</script>

<style lang="scss" scoped>
.index-swiper {
    box-sizing: border-box;
    overflow: auto;
}
</style>
