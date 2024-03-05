<template>
	<div>
		<!-- 歌单通用组件展示层 -->
		<div class="sin-container">
			<el-empty v-if="playList.length === 0" description="暂无歌单"></el-empty>

			<div class="row">
				<template>
					<div
						class="sin-card"
						v-for="(item, index) in playList"
						:key="index"
						@click="goDetails(item.id)">
						<img class="card-img" :src="item.absolutePath" />
						<h3>{{ item.title }}</h3>
					</div>
				</template>
			</div>
		</div>
	</div>
</template>

<script>
import { clickSongListById } from '@/api/index';

export default {
	name: 'mod-playlist',
	props: ['playList'],
	data() {
		return {};
	},
	methods: {
		goDetails(id) {
			clickSongListById(id)
				.then((res) => {
					console.log('点击量加1 ',res)
			})
			this.$router.push({ path: '/playlist-details', query: { playlistId: id } });
		},
	},
};
</script>

<style lang="scss" scoped>
.sin-container {
	box-sizing: border-box;
	width: 100%;
	height: 100%;
	padding: 0px 2%;
}

.sin-container h1 {
	text-align: center;
	padding-top: 20px;
	margin-bottom: 60px;
	font-weight: 600;
	position: relative;
}

.sin-container h1::after {
	content: '';
	background: #303ef7;
	width: 100px;
	height: 5px;
	position: absolute;
	bottom: -5px;
	left: 50%;
	transform: translateX(-50%);
}

.row {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(210px, 1fr));
	grid-column-gap: 15px;
	grid-row-gap: 30px;
}

.sin-card {
	// 限制卡片大小
	max-width: 200px;
	text-align: center;
	padding: 10px 10px;
	border-radius: 5px;
	font-size: 14px;
	cursor: pointer;
	background: transparent;
	transition: transform 0.5s, background 0.5s;
}
.card-img {
	width: 100%;
	border-radius: 14px;
	/* 设置过渡 */
	transition: 0.35s;
}

.sin-card i {
	font-size: 40px;
	margin-bottom: 10px;
	color: #303ef7;
}

.sin-card h3 {
	font-weight: 600;
	margin-bottom: 8px;
}

.sin-card:hover {
	// background: #303ef7;
	color: #000;
	transform: scale(1.05);
}

.sin-card:hover i {
	color: #000;
}
</style>
