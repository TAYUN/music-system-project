<template>
	<div>
		<the-header></the-header>
		<the-aside></the-aside>
		<div class="content-box scroll-container" :class="{'content-collapse': collapse}">
			<router-view></router-view>
		</div>
	</div>
</template>

<script>
import bus from '@/assets/js/bus';
import TheHeader from './TheHeader';
import TheAside from './TheAside';

export default {
	components: {
		TheHeader,
		TheAside,
	},
	data() {
		return {
			collapse: false,
		};
	},
	created(){
        bus.$on('collapse', msg => {
            this.collapse = msg
        })
    }
};
</script>

<style scoped lang="less">
.content-box{
	// background-color: #eaeaea;
}
.scroll-container{
	// 滚动条覆盖内容
	// overflow: overlay;
	// 滚动条不覆盖内容
	overflow: auto;
    // background-color: #eee;
    white-space: nowrap;
}
/* 后台首页滚动条整体 */
.scroll-container::-webkit-scrollbar {
    height: 20px;
    width: 20;
}
/* 两个滚动条交接处 -- x轴和y轴 */
.scroll-container::-webkit-scrollbar-corner {
    background-color: transparent;
}

/* 滚动条滑块 */
.scroll-container::-webkit-scrollbar-thumb {
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    background: #bababa;
}

/* 滚动条轨道 */
.scroll-container::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    border-radius: 10px;
    background: #eaeaea;
}

/* 滚动条两端按钮 */
.scroll-container::-webkit-scrollbar-button {
    background: #eaeaea;
}


</style>
