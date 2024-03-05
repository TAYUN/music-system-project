<template>
	<!-- 搜索框 -->
	<form @submit="onSearch">
		<div class="search-bar">
			<el-input
				type="text"
				clearable
				prefix-icon="el-icon-search"
				placeholder="音乐 | 歌单"
				@keyup.enter.native
				v-model="keywords">
				<el-button native-type="submit" slot="append" icon="el-icon-search"></el-button
			></el-input>
		</div>
	</form>
</template>

<script>

export default {
	name: 'search-box',
	data() {
		return {
			keywords: '',
		};
	},
	methods: {
		onSearch(e) {
			if (this.keywords.length == 0 || this.keywords.split(' ').join('').length == 0) {
				this.$notify({
					showClose: true,
					center: true,
					title: '输入关键字',
					type: 'warning',
				});
				return;
			} else {
				this.$router.push({ path: '/search', query: { keywords: this.keywords } });
			}
		},
	},
};
</script>

<style>
.search-bar {
	width: 100%;
}
</style>
