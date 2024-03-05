<template>
    <div></div>
</template>
<script>
// redirect.vue
export default {
	beforeCreate() {
		const { params, query } = this.$route;
		const { path } = params;
		this.$router.replace({ path: '/' + path, query });
        console.log(3234);
	},
	render: function (h) {
		return h(); // avoid warning message
	},
};
</script>
