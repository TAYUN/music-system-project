<template>
	<!-- 详情页数据层 -->
	<div>
		<mod-details :obj="playlist" />
	</div>
</template>

<script>
import { getSongListOfId } from '@/api';
import ModDetails from '@/components/mod-details';

export default {
	name: 'playlist-details',
	components: {
		ModDetails,
	},
	data() {
		return {
			// 路由得到id
			playlistId: this.$route.query.playlistId,
			playlist: {},
		};
	},
	created() {
		this.getDetails(this.$route.query.playlistId);
	},
	computed: {},

	methods: {
		getDetails(id) {
			getSongListOfId(id).then((res) => {
				if (res) {
					this.playlist = res;
				} else {
					console.log(res);
				}
			});
		},
	},
};
</script>
