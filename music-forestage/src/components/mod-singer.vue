<template>
	<!-- 歌手展示层 -->
	<div>
		<div class="sin-container">
			<!-- <h1>歌手</h1> -->
			<el-empty v-if="mSingerList.length === 0" description="暂无数据"></el-empty>
			<div class="row">
				<template>
					<div
						class="sin-card"
						v-for="(item, index) in mSingerList"
						:key="index"
						@click="goDetails(item.id)">
						<img class="card-img" :src="attachImageUrl(item.pic)" />
						<h3>{{ item.name }}</h3>
					</div>
				</template>
			</div>
		</div>
	</div>
</template>

<script>
import mixin from '@/mixins/index';
export default {
	name: 'mod-singer',
	mixins: [mixin],
	props: ['mSingerList'],

	methods: {
		goDetails(id) {
			this.$router.push({ path: '/singer-details', query: { singerId: id } });
		},
	},
};
</script>

<style lang="scss" scoped>
.sin-container {
	box-sizing: border-box;
	width: 100%;
	height: 100%;
	padding: 0px 2% 200px;
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
	grid-template-columns: repeat(auto-fit, minmax(190px, 1fr));
	grid-column-gap: 15px;
	grid-row-gap: 30px;
}

.sin-card {
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
	border-radius: 50%;
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
