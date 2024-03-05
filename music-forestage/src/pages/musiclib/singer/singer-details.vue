<template>
	<!-- 详情页数据层 -->
	<div>
		<mod-details :obj="singer" />
	</div>
</template>

<script>
import { getSingerOfId } from '@/api';
import ModDetails from '@/components/mod-details';

export default {
	name: 'singer-details',
	components: {
		ModDetails,
	},
	data() {
		return {
			// 路由得到id
			singerId: this.$route.query.singerId,
			singer: {},
		};
	},
	created() {
		this.getDetails(this.$route.query.singerId);
	},
	computed: {},

	methods: {
		getDetails(id) {
			getSingerOfId(id).then((res) => {
				if (res) {
					this.singer = res;
				} else {
					console.log(res);
				}
			});
		},
	},
};
</script>
