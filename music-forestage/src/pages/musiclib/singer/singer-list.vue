<template>
	<div>
		<ul class="singer-header">
			<li
				v-for="(item, index) in singerStyle"
				:key="index"
				@click="handleChangeView(item)"
				:class="{ active: item.name == activeName }">
				{{ item.name }}
			</li>
		</ul>
		<mod-singer :m-singer-list="partOfSingerList" />
		<el-pagination
			class="pagination"
			hide-on-single-page
			background
			layout="prev, pager, next"
			:total="singerList.length"
			:current-page="currentPage"
			:page-size="pageSize"
			@current-change="handleCurrentChange">
		</el-pagination>
	</div>
</template>

<script>
import ModSinger from '@/components/mod-singer';
import { getAllSinger, getSingerOfSex } from '@/api/index.js';
import { singerStyle } from '@/data/singer';
export default {
	name: 'singer-list',
	components: {
		ModSinger,
	},
	data() {
		return {
			singerList: [],
			pageSize: 50, //页面大小，一页有15条数据
			currentPage: 1, //当前页，默认第一页

			singerStyle: [], //风格
			activeName: '全部', //当前风格
		};
	},
	computed: {
		partOfSingerList() {
			return this.singerList.slice(
				(this.currentPage - 1) * this.pageSize,
				this.currentPage * this.pageSize
			);
		},
	},
	created() {
		this.singerStyle = singerStyle;
		this.queryAllSinger();
	},
	methods: {
		queryAllSinger() {
			getAllSinger().then((res) => {
				if (res) {
					this.singerList = res;
				} else {
					console.log('res : ', res);
				}
				console.log('res歌手: ', res);
			});
		},
		//根据类型显示对应的歌手
		handleChangeView(item) {
			this.activeName = item.name;
			this.singerList = [];
			if (item.name == '全部') {
				this.queryAllSinger();
			} else {
				this.getSingerOfSex(item.type);
			}
		},
		//根据性别查询对应的歌手
		getSingerOfSex(sex) {
			getSingerOfSex(sex).then((res) => {
				this.currentPage = 1;
				this.singerList = res;
			});
		},

		//获取当前页
		handleCurrentChange(val) {
			this.currentPage = val;
		},
	},
};
</script>

<style lang="scss" scoped>
.singer-header {
	width: 100%;
	padding: 0 40px;
	li {
		display: inline-block;
		line-height: 40px;
		margin: 0 20px 10px 0;
		font-size: 16px;
		font-weight: 400;
		color: #909399;
		border-bottom: none;
		cursor: pointer;
	}
	li:hover {
		color: #000;
	}
	li.active {
		color: #20a0ff;
		font-weight: 600;
		border-bottom: 2px solid #20a0ff;
	}
}
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
// 分页居中
.pagination {
	display: flex;
	justify-content: center;
}
</style>
