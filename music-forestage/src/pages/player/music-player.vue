<template>
	<div id="gradient">
		<div class="wrapper">
			<!-- 设置音频 -->
			<audio ref="player"></audio>
			<!-- 左侧信息：包括图片、歌名、歌手 -->
			<div class="left-bar">
				<div class="player-cover">
					<div
						class="player-cover__item"
						:style="{ backgroundImage: `url(${currentTrack.gqPic})` }"></div>
				</div>
				<div class="album-info" v-if="currentTrack">
					<!-- 歌名 -->
					<div class="album-info__track">{{ currentTrack.gqName }}</div>
					<!-- 歌手名 -->
					<div class="album-info__name">{{ currentTrack.gqSingerName }}</div>
				</div>
			</div>

			<div class="mid-player">
				<div class="player-controls">
					<!-- 上一首 -->
					<div class="player-controls__item" @click="prevTrack">
						<svg class="icon">
							<use xlink:href="#icon-prev"></use>
						</svg>
					</div>
					<!-- 播放暂停 -->
					<div class="player-controls__item -xl js-play" @click="play">
						<svg class="icon">
							<use xlink:href="#icon-pause" v-if="isTimerPlaying"></use>
							<use xlink:href="#icon-play" v-else></use>
						</svg>
					</div>
					<!-- 下一首 -->
					<div class="player-controls__item" @click="nextTrack">
						<svg class="icon">
							<use xlink:href="#icon-next"></use>
						</svg>
					</div>
				</div>
				<!-- 进度条 -->
				<div class="progress" ref="progress">
					<!-- 当前时间 -->
					<div class="progress__time">{{ currentTime }}</div>
					<div class="progress__bar" @click="clickProgress">
						<div class="progress__current" :style="{ width: barWidth }"></div>
					</div>

					<div class="progress__top">
						<!-- duration时长 -->
						<div class="progress__duration">{{ duration }}</div>
					</div>
				</div>
				<!-- 未处理 -->
				<div v-cloak></div>
			</div>

			<!-- 右侧控件 -->
			<div class="right-bar">
				<!-- 音量 -->
				<div class="block">
					<div class="volume-slider">
						<svg v-if="!!volume" class="player-controls__item icon icon-24gf-list5">
							<use xlink:href="#icon-24gf-volumeMiddle"></use>
						</svg>
						<svg v-if="!volume" class="player-controls__item icon icon-24gf-list5">
							<use xlink:href="#icon-24gf-volumeZero"></use>
						</svg>
					</div>
					<el-slider v-model="volume" @change="changeVolume"> </el-slider>
				</div>
				<!-- 歌词 -->
				<div
					class="player-controls__item"
					@click="drawer === true ? (drawer = false) : (drawer = true)">
					<svg class="icon icon-24gf-list5">
						<use xlink:href="#icon-minganci"></use>
					</svg>
				</div>
				<!-- 播放列表 -->
				<div
					class="player-controls__item"
					@click="showList === true ? (showList = false) : (showList = true)">
					<svg class="icon">
						<use xlink:href="#icon-Notesmusic"></use>
					</svg>
				</div>
				<!-- 喜欢 -->
				<div
					class="player-controls__item -favorite"
					:class="{ active: currentTrack.favorited }"
					@click="favorite">
					<svg class="icon">
						<use xlink:href="#icon-heart-o"></use>
					</svg>
				</div>
				<!-- 下载 -->
                <div class="player-controls__item" @click="download">
                    <svg class="icon">
                        <use xlink:href="#icon-ziyuan19"></use>
                    </svg>
                </div>
			</div>
		</div>

		<svg
			class="player-svg"
			xmlns="http://www.w3.org/2000/svg"
			hidden
			xmlns:xlink="http://www.w3.org/1999/xlink">
			<defs>
				<symbol id="icon-heart-o" viewBox="0 0 32 32">
					<title>icon-heart-o</title>
					<path
						d="M22.88 1.952c-2.72 0-5.184 1.28-6.88 3.456-1.696-2.176-4.16-3.456-6.88-3.456-4.48 0-9.024 3.648-9.024 10.592 0 7.232 7.776 12.704 15.072 17.248 0.256 0.16 0.544 0.256 0.832 0.256s0.576-0.096 0.832-0.256c7.296-4.544 15.072-10.016 15.072-17.248 0-6.944-4.544-10.592-9.024-10.592zM16 26.56c-4.864-3.072-12.736-8.288-12.736-14.016 0-5.088 3.040-7.424 5.824-7.424 2.368 0 4.384 1.504 5.408 4.032 0.256 0.608 0.832 0.992 1.472 0.992s1.248-0.384 1.472-0.992c1.024-2.528 3.040-4.032 5.408-4.032 2.816 0 5.824 2.304 5.824 7.424 0.064 5.728-7.808 10.976-12.672 14.016z"></path>
					<path
						d="M16 30.144c-0.32 0-0.64-0.096-0.896-0.256-7.296-4.576-15.104-10.048-15.104-17.344 0-7.008 4.576-10.688 9.12-10.688 2.656 0 5.152 1.216 6.88 3.392 1.728-2.144 4.224-3.392 6.88-3.392 4.544 0 9.12 3.68 9.12 10.688 0 7.296-7.808 12.768-15.104 17.344-0.256 0.16-0.576 0.256-0.896 0.256zM9.12 2.048c-4.448 0-8.928 3.616-8.928 10.496 0 7.168 7.744 12.64 15.008 17.152 0.48 0.288 1.12 0.288 1.568 0 7.264-4.544 15.008-9.984 15.008-17.152 0-6.88-4.48-10.496-8.928-10.496-2.656 0-5.088 1.216-6.816 3.392l-0.032 0.128-0.064-0.096c-1.696-2.176-4.192-3.424-6.816-3.424zM16 26.688l-0.064-0.032c-3.808-2.4-12.768-8.032-12.768-14.112 0-5.152 3.072-7.52 5.952-7.52 2.432 0 4.48 1.536 5.504 4.096 0.224 0.576 0.768 0.928 1.376 0.928s1.152-0.384 1.376-0.928c1.024-2.56 3.072-4.096 5.504-4.096 2.848 0 5.952 2.336 5.952 7.52 0 6.080-8.96 11.712-12.768 14.112l-0.064 0.032zM9.12 5.248c-2.752 0-5.728 2.304-5.728 7.328 0 5.952 8.8 11.488 12.608 13.92 3.808-2.4 12.608-7.968 12.608-13.92 0-5.024-2.976-7.328-5.728-7.328-2.336 0-4.32 1.472-5.312 3.968-0.256 0.64-0.864 1.056-1.568 1.056s-1.312-0.416-1.568-1.056c-0.992-2.496-2.976-3.968-5.312-3.968z"></path>
					<path
						d="M6.816 20.704c0.384 0.288 0.512 0.704 0.48 1.12 0.224 0.256 0.384 0.608 0.384 0.96 0 0.032 0 0.032 0 0.064 0.16 0.128 0.32 0.256 0.48 0.384 0.128 0.064 0.256 0.16 0.384 0.256 0.096 0.064 0.192 0.16 0.256 0.224 0.8 0.576 1.632 1.12 2.496 1.664 0.416 0.128 0.8 0.256 1.056 0.32 1.984 0.576 4.064 0.8 6.112 0.928 2.688-1.92 5.312-3.904 8-5.792 0.896-1.088 1.92-2.080 2.912-3.104v-7.552c-0.096-0.128-0.192-0.288-0.32-0.416-0.768-1.024-1.184-2.176-1.6-3.296-0.768-0.416-1.536-0.8-2.336-1.12-0.128-0.064-0.256-0.096-0.384-0.16h-21.568v12.992c1.312 0.672 2.496 1.6 3.648 2.528z"></path>
				</symbol>
				<symbol id="icon-heart" viewBox="0 0 32 32">
					<title>icon-heart</title>
					<path
						d="M22.88 1.952c-2.72 0-5.184 1.28-6.88 3.456-1.696-2.176-4.16-3.456-6.88-3.456-4.48 0-9.024 3.648-9.024 10.592 0 7.232 7.776 12.704 15.072 17.248 0.256 0.16 0.544 0.256 0.832 0.256s0.576-0.096 0.832-0.256c7.296-4.544 15.072-10.016 15.072-17.248 0-6.944-4.544-10.592-9.024-10.592zM16 26.56c-4.864-3.072-12.736-8.288-12.736-14.016 0-5.088 3.040-7.424 5.824-7.424 2.368 0 4.384 1.504 5.408 4.032 0.256 0.608 0.832 0.992 1.472 0.992s1.248-0.384 1.472-0.992c1.024-2.528 3.040-4.032 5.408-4.032 2.816 0 5.824 2.304 5.824 7.424 0.064 5.728-7.808 10.976-12.672 14.016z"></path>
					<path
						d="M16 30.144c-0.32 0-0.64-0.096-0.896-0.256-7.296-4.576-15.104-10.048-15.104-17.344 0-7.008 4.576-10.688 9.12-10.688 2.656 0 5.152 1.216 6.88 3.392 1.728-2.144 4.224-3.392 6.88-3.392 4.544 0 9.12 3.68 9.12 10.688 0 7.296-7.808 12.768-15.104 17.344-0.256 0.16-0.576 0.256-0.896 0.256zM9.12 2.048c-4.448 0-8.928 3.616-8.928 10.496 0 7.168 7.744 12.64 15.008 17.152 0.48 0.288 1.12 0.288 1.568 0 7.264-4.544 15.008-9.984 15.008-17.152 0-6.88-4.48-10.496-8.928-10.496-2.656 0-5.088 1.216-6.816 3.392l-0.032 0.128-0.064-0.096c-1.696-2.176-4.192-3.424-6.816-3.424zM16 26.688l-0.064-0.032c-3.808-2.4-12.768-8.032-12.768-14.112 0-5.152 3.072-7.52 5.952-7.52 2.432 0 4.48 1.536 5.504 4.096 0.224 0.576 0.768 0.928 1.376 0.928s1.152-0.384 1.376-0.928c1.024-2.56 3.072-4.096 5.504-4.096 2.848 0 5.952 2.336 5.952 7.52 0 6.080-8.96 11.712-12.768 14.112l-0.064 0.032zM9.12 5.248c-2.752 0-5.728 2.304-5.728 7.328 0 5.952 8.8 11.488 12.608 13.92 3.808-2.4 12.608-7.968 12.608-13.92 0-5.024-2.976-7.328-5.728-7.328-2.336 0-4.32 1.472-5.312 3.968-0.256 0.64-0.864 1.056-1.568 1.056s-1.312-0.416-1.568-1.056c-0.992-2.496-2.976-3.968-5.312-3.968z"></path>
				</symbol>
				<symbol id="icon-infinity" viewBox="0 0 32 32">
					<title>icon-infinity</title>
					<path
						d="M29.312 20.832c-1.28 1.28-3.008 1.984-4.832 1.984s-3.52-0.704-4.832-1.984c-0.032-0.032-0.224-0.224-0.256-0.256v0 1.28c0 0.448-0.352 0.8-0.8 0.8s-0.8-0.352-0.8-0.8v-3.168c0-0.448 0.352-0.8 0.8-0.8h3.168c0.448 0 0.8 0.352 0.8 0.8s-0.352 0.8-0.8 0.8h-1.28c0.032 0.032 0.224 0.224 0.256 0.256 0.992 0.992 2.304 1.536 3.68 1.536 1.408 0 2.72-0.544 3.68-1.536 0.992-0.992 1.536-2.304 1.536-3.68s-0.544-2.72-1.536-3.68c-0.992-0.992-2.304-1.536-3.68-1.536-1.408 0-2.72 0.544-3.68 1.536l-8.416 8.448c-1.312 1.312-3.072 1.984-4.832 1.984s-3.488-0.672-4.832-1.984c-2.656-2.656-2.656-6.976 0-9.632s6.976-2.656 9.632 0c0.032 0.032 0.16 0.16 0.192 0.192l0.064 0.064v-1.28c0-0.448 0.352-0.8 0.8-0.8s0.8 0.352 0.8 0.8v3.168c0 0.448-0.352 0.8-0.8 0.8h-3.168c-0.448 0-0.8-0.352-0.8-0.8s0.352-0.8 0.8-0.8h1.28l-0.096-0.064c-0.032-0.032-0.16-0.16-0.192-0.192-0.992-0.992-2.304-1.536-3.68-1.536s-2.72 0.544-3.68 1.536c-2.048 2.048-2.048 5.344 0 7.392 0.992 0.992 2.304 1.536 3.68 1.536s2.72-0.544 3.68-1.536l8.512-8.512c1.28-1.28 3.008-1.984 4.832-1.984s3.52 0.704 4.832 1.984c2.624 2.656 2.624 7.008-0.032 9.664z"></path>
					<path
						d="M24.512 23.488c-1.6 0-3.136-0.512-4.416-1.44-0.128 0.704-0.736 1.248-1.44 1.248-0.8 0-1.472-0.672-1.472-1.472v-3.168c0-0.8 0.672-1.472 1.472-1.472h3.168c0.8 0 1.472 0.672 1.472 1.472 0 0.608-0.384 1.152-0.928 1.376 0.64 0.352 1.376 0.544 2.144 0.544 1.216 0 2.368-0.48 3.2-1.344 0.864-0.864 1.344-1.984 1.344-3.2s-0.48-2.368-1.344-3.2c-0.864-0.864-1.984-1.344-3.2-1.344s-2.368 0.48-3.2 1.344l-8.512 8.48c-1.408 1.408-3.296 2.176-5.312 2.176s-3.872-0.768-5.312-2.176c-2.912-2.912-2.912-7.68 0-10.592 1.408-1.408 3.296-2.176 5.312-2.176 0 0 0 0 0 0 1.6 0 3.136 0.512 4.416 1.44 0.128-0.704 0.736-1.248 1.472-1.248 0.8 0 1.472 0.672 1.472 1.472v3.168c0 0.8-0.672 1.472-1.472 1.472h-3.168c-0.8 0-1.472-0.672-1.472-1.472 0-0.608 0.384-1.152 0.928-1.376-0.64-0.352-1.376-0.544-2.144-0.544-1.216 0-2.368 0.48-3.2 1.344-1.76 1.76-1.76 4.64 0 6.432 0.864 0.864 2.016 1.344 3.2 1.344 1.216 0 2.368-0.48 3.2-1.344l8.48-8.544c1.408-1.408 3.296-2.208 5.312-2.208s3.872 0.768 5.312 2.208c1.408 1.408 2.176 3.296 2.176 5.312s-0.768 3.872-2.208 5.312v0c0 0 0 0 0 0-1.408 1.408-3.296 2.176-5.28 2.176zM18.752 18.912l1.44 1.44c1.152 1.152 2.688 1.792 4.32 1.792s3.168-0.64 4.32-1.792v0c1.152-1.152 1.792-2.688 1.792-4.32s-0.64-3.168-1.792-4.32c-1.152-1.152-2.688-1.792-4.352-1.792-1.632 0-3.168 0.64-4.32 1.792l-8.48 8.448c-1.12 1.12-2.592 1.728-4.16 1.728s-3.072-0.608-4.16-1.728c-2.304-2.304-2.304-6.048 0-8.352 1.12-1.12 2.592-1.728 4.16-1.728s3.072 0.608 4.16 1.728l1.44 1.408h-2.912c-0.064 0-0.128 0.064-0.128 0.128s0.064 0.128 0.128 0.128h3.168c0.064 0 0.128-0.064 0.128-0.128v-3.168c0-0.064-0.064-0.128-0.128-0.128s-0.128 0.064-0.128 0.128v2.912l-1.408-1.408c-1.152-1.152-2.688-1.792-4.352-1.792-1.632 0-3.168 0.64-4.32 1.792-2.4 2.4-2.4 6.272 0 8.672 1.152 1.152 2.688 1.792 4.32 1.792s3.168-0.64 4.32-1.792l8.512-8.512c1.12-1.12 2.592-1.728 4.16-1.728s3.072 0.608 4.16 1.728c1.12 1.12 1.728 2.592 1.728 4.16s-0.608 3.072-1.728 4.16c-1.12 1.12-2.592 1.728-4.16 1.728s-3.072-0.608-4.16-1.728l-1.408-1.408h2.912c0.064 0 0.128-0.064 0.128-0.128s-0.064-0.128-0.128-0.128h-3.168c-0.064 0-0.128 0.064-0.128 0.128v3.168c0 0.064 0.064 0.128 0.128 0.128s0.128-0.064 0.128-0.128v-2.88z"></path>
				</symbol>
				<symbol id="icon-pause" viewBox="0 0 32 32">
					<title>icon-pause</title>
					<path
						d="M16 0.32c-8.64 0-15.68 7.040-15.68 15.68s7.040 15.68 15.68 15.68 15.68-7.040 15.68-15.68-7.040-15.68-15.68-15.68zM16 29.216c-7.296 0-13.216-5.92-13.216-13.216s5.92-13.216 13.216-13.216 13.216 5.92 13.216 13.216-5.92 13.216-13.216 13.216z"></path>
					<path
						d="M16 32c-8.832 0-16-7.168-16-16s7.168-16 16-16 16 7.168 16 16-7.168 16-16 16zM16 0.672c-8.448 0-15.328 6.88-15.328 15.328s6.88 15.328 15.328 15.328c8.448 0 15.328-6.88 15.328-15.328s-6.88-15.328-15.328-15.328zM16 29.568c-7.488 0-13.568-6.080-13.568-13.568s6.080-13.568 13.568-13.568c7.488 0 13.568 6.080 13.568 13.568s-6.080 13.568-13.568 13.568zM16 3.104c-7.104 0-12.896 5.792-12.896 12.896s5.792 12.896 12.896 12.896c7.104 0 12.896-5.792 12.896-12.896s-5.792-12.896-12.896-12.896z"></path>
					<path
						d="M12.16 22.336v0c-0.896 0-1.6-0.704-1.6-1.6v-9.472c0-0.896 0.704-1.6 1.6-1.6v0c0.896 0 1.6 0.704 1.6 1.6v9.504c0 0.864-0.704 1.568-1.6 1.568z"></path>
					<path
						d="M19.84 22.336v0c-0.896 0-1.6-0.704-1.6-1.6v-9.472c0-0.896 0.704-1.6 1.6-1.6v0c0.896 0 1.6 0.704 1.6 1.6v9.504c0 0.864-0.704 1.568-1.6 1.568z"></path>
				</symbol>
				<symbol id="icon-play" viewBox="0 0 32 32">
					<title>icon-play</title>
					<path
						d="M21.216 15.168l-7.616-5.088c-0.672-0.416-1.504 0.032-1.504 0.832v10.176c0 0.8 0.896 1.248 1.504 0.832l7.616-5.088c0.576-0.416 0.576-1.248 0-1.664z"></path>
					<path
						d="M13.056 22.4c-0.224 0-0.416-0.064-0.608-0.16-0.448-0.224-0.704-0.672-0.704-1.152v-10.176c0-0.48 0.256-0.928 0.672-1.152s0.928-0.224 1.344 0.064l7.616 5.088c0.384 0.256 0.608 0.672 0.608 1.088s-0.224 0.864-0.608 1.088l-7.616 5.088c-0.192 0.16-0.448 0.224-0.704 0.224zM13.056 10.272c-0.096 0-0.224 0.032-0.32 0.064-0.224 0.128-0.352 0.32-0.352 0.576v10.176c0 0.256 0.128 0.48 0.352 0.576 0.224 0.128 0.448 0.096 0.64-0.032l7.616-5.088c0.192-0.128 0.288-0.32 0.288-0.544s-0.096-0.416-0.288-0.544l-7.584-5.088c-0.096-0.064-0.224-0.096-0.352-0.096z"></path>
					<path
						d="M16 0.32c-8.64 0-15.68 7.040-15.68 15.68s7.040 15.68 15.68 15.68 15.68-7.040 15.68-15.68-7.040-15.68-15.68-15.68zM16 29.216c-7.296 0-13.216-5.92-13.216-13.216s5.92-13.216 13.216-13.216 13.216 5.92 13.216 13.216-5.92 13.216-13.216 13.216z"></path>
					<path
						d="M16 32c-8.832 0-16-7.168-16-16s7.168-16 16-16 16 7.168 16 16-7.168 16-16 16zM16 0.672c-8.448 0-15.328 6.88-15.328 15.328s6.88 15.328 15.328 15.328c8.448 0 15.328-6.88 15.328-15.328s-6.88-15.328-15.328-15.328zM16 29.568c-7.488 0-13.568-6.080-13.568-13.568s6.080-13.568 13.568-13.568c7.488 0 13.568 6.080 13.568 13.568s-6.080 13.568-13.568 13.568zM16 3.104c-7.104 0-12.896 5.792-12.896 12.896s5.792 12.896 12.896 12.896c7.104 0 12.896-5.792 12.896-12.896s-5.792-12.896-12.896-12.896z"></path>
				</symbol>
				<symbol id="icon-link" viewBox="0 0 32 32">
					<title>link</title>
					<path
						d="M23.584 17.92c0 0.864 0 1.728 0 2.56 0 1.312 0 2.656 0 3.968 0 0.352 0.032 0.736-0.032 1.12 0.032-0.16 0.032-0.288 0.064-0.448-0.032 0.224-0.096 0.448-0.16 0.64 0.064-0.128 0.128-0.256 0.16-0.416-0.096 0.192-0.192 0.384-0.32 0.576 0.096-0.128 0.16-0.224 0.256-0.352-0.128 0.16-0.288 0.32-0.48 0.48 0.128-0.096 0.224-0.16 0.352-0.256-0.192 0.128-0.352 0.256-0.576 0.32 0.128-0.064 0.256-0.128 0.416-0.16-0.224 0.096-0.416 0.16-0.64 0.16 0.16-0.032 0.288-0.032 0.448-0.064-0.256 0.032-0.512 0.032-0.768 0.032-0.448 0-0.896 0-1.312 0-1.472 0-2.976 0-4.448 0-1.824 0-3.616 0-5.44 0-1.568 0-3.104 0-4.672 0-0.736 0-1.44 0-2.176 0-0.128 0-0.224 0-0.352-0.032 0.16 0.032 0.288 0.032 0.448 0.064-0.224-0.032-0.448-0.096-0.64-0.16 0.128 0.064 0.256 0.128 0.416 0.16-0.192-0.096-0.384-0.192-0.576-0.32 0.128 0.096 0.224 0.16 0.352 0.256-0.16-0.128-0.32-0.288-0.48-0.48 0.096 0.128 0.16 0.224 0.256 0.352-0.128-0.192-0.256-0.352-0.32-0.576 0.064 0.128 0.128 0.256 0.16 0.416-0.096-0.224-0.16-0.416-0.16-0.64 0.032 0.16 0.032 0.288 0.064 0.448-0.032-0.256-0.032-0.512-0.032-0.768 0-0.448 0-0.896 0-1.312 0-1.472 0-2.976 0-4.448 0-1.824 0-3.616 0-5.44 0-1.568 0-3.104 0-4.672 0-0.736 0-1.44 0-2.176 0-0.128 0-0.224 0.032-0.352-0.032 0.16-0.032 0.288-0.064 0.448 0.032-0.224 0.096-0.448 0.16-0.64-0.064 0.128-0.128 0.256-0.16 0.416 0.096-0.192 0.192-0.384 0.32-0.576-0.096 0.128-0.16 0.224-0.256 0.352 0.128-0.16 0.288-0.32 0.48-0.48-0.128 0.096-0.224 0.16-0.352 0.256 0.192-0.128 0.352-0.256 0.576-0.32-0.128 0.064-0.256 0.128-0.416 0.16 0.224-0.096 0.416-0.16 0.64-0.16-0.16 0.032-0.288 0.032-0.448 0.064 0.48-0.064 0.96-0.032 1.44-0.032 0.992 0 1.952 0 2.944 0 1.216 0 2.432 0 3.616 0 1.056 0 2.112 0 3.168 0 0.512 0 1.024 0 1.536 0 0 0 0 0 0.032 0 0.448 0 0.896-0.192 1.184-0.48s0.512-0.768 0.48-1.184c-0.032-0.448-0.16-0.896-0.48-1.184s-0.736-0.48-1.184-0.48c-0.64 0-1.28 0-1.92 0-1.408 0-2.816 0-4.224 0-1.44 0-2.848 0-4.256 0-0.672 0-1.344 0-2.016 0-0.736 0-1.472 0.192-2.112 0.576s-1.216 0.96-1.568 1.6c-0.384 0.64-0.544 1.376-0.544 2.144 0 0.672 0 1.376 0 2.048 0 1.28 0 2.56 0 3.84 0 1.504 0 3.040 0 4.544 0 1.408 0 2.848 0 4.256 0 0.992 0 1.952 0 2.944 0 0.224 0 0.448 0 0.64 0 0.864 0.224 1.76 0.768 2.464 0.16 0.192 0.288 0.384 0.48 0.576s0.384 0.352 0.608 0.512c0.32 0.224 0.64 0.384 1.024 0.512 0.448 0.16 0.928 0.224 1.408 0.224 0.16 0 0.32 0 0.48 0 0.896 0 1.792 0 2.72 0 1.376 0 2.784 0 4.16 0 1.536 0 3.040 0 4.576 0 1.312 0 2.656 0 3.968 0 0.768 0 1.536 0 2.336 0 0.416 0 0.832-0.032 1.248-0.128 1.504-0.32 2.784-1.6 3.104-3.104 0.128-0.544 0.128-1.056 0.128-1.568 0-0.608 0-1.184 0-1.792 0-1.408 0-2.816 0-4.224 0-0.256 0-0.512 0-0.768 0-0.448-0.192-0.896-0.48-1.184s-0.768-0.512-1.184-0.48c-0.448 0.032-0.896 0.16-1.184 0.48-0.384 0.384-0.576 0.768-0.576 1.248v0z"></path>
					<path
						d="M32 11.232c0-0.8 0-1.568 0-2.368 0-1.248 0-2.528 0-3.776 0-0.288 0-0.576 0-0.864 0-0.896-0.768-1.696-1.696-1.696-0.8 0-1.568 0-2.368 0-1.248 0-2.528 0-3.776 0-0.288 0-0.576 0-0.864 0-0.448 0-0.896 0.192-1.184 0.48s-0.512 0.768-0.48 1.184c0.032 0.448 0.16 0.896 0.48 1.184s0.736 0.48 1.184 0.48c0.8 0 1.568 0 2.368 0 1.248 0 2.528 0 3.776 0 0.288 0 0.576 0 0.864 0-0.576-0.576-1.12-1.12-1.696-1.696 0 0.8 0 1.568 0 2.368 0 1.248 0 2.528 0 3.776 0 0.288 0 0.576 0 0.864 0 0.448 0.192 0.896 0.48 1.184s0.768 0.512 1.184 0.48c0.448-0.032 0.896-0.16 1.184-0.48 0.352-0.256 0.544-0.64 0.544-1.12v0z"></path>
					<path
						d="M15.040 21.888c0.16-0.16 0.288-0.288 0.448-0.448 0.384-0.384 0.8-0.8 1.184-1.184 0.608-0.608 1.184-1.184 1.792-1.792 0.704-0.704 1.44-1.44 2.176-2.176 0.8-0.8 1.568-1.568 2.368-2.368s1.6-1.6 2.4-2.4c0.736-0.736 1.504-1.504 2.24-2.24 0.64-0.64 1.248-1.248 1.888-1.888 0.448-0.448 0.896-0.896 1.344-1.344 0.224-0.224 0.448-0.416 0.64-0.64 0 0 0.032-0.032 0.032-0.032 0.32-0.32 0.48-0.768 0.48-1.184s-0.192-0.896-0.48-1.184c-0.32-0.288-0.736-0.512-1.184-0.48-0.512 0.032-0.928 0.16-1.248 0.48-0.16 0.16-0.288 0.288-0.448 0.448-0.384 0.384-0.8 0.8-1.184 1.184-0.608 0.608-1.184 1.184-1.792 1.792-0.704 0.704-1.44 1.44-2.176 2.176-0.8 0.8-1.568 1.568-2.368 2.368s-1.6 1.6-2.4 2.4c-0.736 0.736-1.504 1.504-2.24 2.24-0.64 0.64-1.248 1.248-1.888 1.888-0.448 0.448-0.896 0.896-1.344 1.344-0.224 0.224-0.448 0.416-0.64 0.64 0 0-0.032 0.032-0.032 0.032-0.32 0.32-0.48 0.768-0.48 1.184s0.192 0.896 0.48 1.184c0.32 0.288 0.736 0.512 1.184 0.48 0.48 0 0.928-0.16 1.248-0.48v0z"></path>
				</symbol>
				<symbol id="icon-next" viewBox="0 0 32 32">
					<title>next</title>
					<path
						d="M2.304 18.304h14.688l-4.608 4.576c-0.864 0.864-0.864 2.336 0 3.232 0.864 0.864 2.336 0.864 3.232 0l8.448-8.48c0.864-0.864 0.864-2.336 0-3.232l-8.448-8.448c-0.448-0.448-1.056-0.672-1.632-0.672s-1.184 0.224-1.632 0.672c-0.864 0.864-0.864 2.336 0 3.232l4.64 4.576h-14.688c-1.248 0-2.304 0.992-2.304 2.272s1.024 2.272 2.304 2.272z"></path>
					<path
						d="M29.696 26.752c1.248 0 2.304-1.024 2.304-2.304v-16.928c0-1.248-1.024-2.304-2.304-2.304s-2.304 1.024-2.304 2.304v16.928c0.064 1.28 1.056 2.304 2.304 2.304z"></path>
				</symbol>
				<symbol id="icon-prev" viewBox="0 0 32 32">
					<title>prev</title>
					<path
						d="M29.696 13.696h-14.688l4.576-4.576c0.864-0.864 0.864-2.336 0-3.232-0.864-0.864-2.336-0.864-3.232 0l-8.448 8.48c-0.864 0.864-0.864 2.336 0 3.232l8.448 8.448c0.448 0.448 1.056 0.672 1.632 0.672s1.184-0.224 1.632-0.672c0.864-0.864 0.864-2.336 0-3.232l-4.608-4.576h14.688c1.248 0 2.304-1.024 2.304-2.304s-1.024-2.24-2.304-2.24z"></path>
					<path
						d="M2.304 5.248c-1.248 0-2.304 1.024-2.304 2.304v16.928c0 1.248 1.024 2.304 2.304 2.304s2.304-1.024 2.304-2.304v-16.928c-0.064-1.28-1.056-2.304-2.304-2.304z"></path>
				</symbol>
			</defs>
		</svg>
		<!-- partial -->

		<!-- 抽屉歌词 -->
		<el-drawer
			title="歌词"
			:modal="false"
			:visible.sync="drawer"
			direction="btt"
			size="100%">
			<music-lyric></music-lyric>
		</el-drawer>
		<el-drawer
			title="播放列表"
			:modal="false"
			:visible.sync="showList"
			direction="rtl"
			size="25%">
			<track-list></track-list>
		</el-drawer>
	</div>
</template>

<script>
import $ from 'jquery';
import { mapState, mapMutations, mapGetters } from 'vuex';
import MusicLyric from '@/pages/lyric/music-lyric.vue';
import TrackList from '@/pages/lyric/track-list.vue';
import 'element-ui/lib/theme-chalk/base.css';
import '@/assets/css/icon-font/iconfont.js';
import { download } from '@/api';
// console.log($);

// //渐变背景颜色
// var colors = new Array(
// 	[62, 35, 255],
// 	[60, 255, 60],
// 	[255, 35, 98],
// 	[45, 175, 230],
// 	[255, 0, 255],
// 	[255, 128, 0]
// );

// var step = 0;
// //color table indices for:
// // current color left
// // next color left
// // current color right
// // next color right
// // 颜色表索引：
// // 当前颜色左侧
// // 下一个颜色左
// // 当前颜色权利
// // 下一个颜色右
// var colorIndices = [0, 1, 2, 3];

// //transition speed
// // 转换速度...
// var gradientSpeed = 0.005;

// function updateGradient() {
// 	if ($ === undefined) return;

// 	var c0_0 = colors[colorIndices[0]];
// 	var c0_1 = colors[colorIndices[1]];
// 	var c1_0 = colors[colorIndices[2]];
// 	var c1_1 = colors[colorIndices[3]];

// 	var istep = 1 - step;
// 	var r1 = Math.round(istep * c0_0[0] + step * c0_1[0]);
// 	var g1 = Math.round(istep * c0_0[1] + step * c0_1[1]);
// 	var b1 = Math.round(istep * c0_0[2] + step * c0_1[2]);
// 	var color1 = 'rgb(' + r1 + ',' + g1 + ',' + b1 + ')';

// 	var r2 = Math.round(istep * c1_0[0] + step * c1_1[0]);
// 	var g2 = Math.round(istep * c1_0[1] + step * c1_1[1]);
// 	var b2 = Math.round(istep * c1_0[2] + step * c1_1[2]);
// 	var color2 = 'rgb(' + r2 + ',' + g2 + ',' + b2 + ')';

// 	$('#gradient')
// 		.css({
// 			background:
// 				'-webkit-gradient(linear, left top, right top, from(' +
// 				color1 +
// 				'), to(' +
// 				color2 +
// 				'))',
// 		})
// 		.css({
// 			background: '-moz-linear-gradient(left, ' + color1 + ' 0%, ' + color2 + ' 100%)',
// 		});

// 	step += gradientSpeed;
// 	if (step >= 1) {
// 		step %= 1;
// 		colorIndices[0] = colorIndices[1];
// 		colorIndices[2] = colorIndices[3];

// 		//pick two new target color indices
// 		//do not pick the same as the current one
// 		// 选择两个新的目标颜色索引
// 		// 不要选择与当前相同的
// 		colorIndices[1] =
// 			(colorIndices[1] + Math.floor(1 + Math.random() * (colors.length - 1))) %
// 			colors.length;
// 		colorIndices[3] =
// 			(colorIndices[3] + Math.floor(1 + Math.random() * (colors.length - 1))) %
// 			colors.length;
// 	}
// }

// setInterval(updateGradient, 10);
export default {
	name: 'music-player',
	components: {
		MusicLyric,
		TrackList,
	},

	data() {
		return {
			drawer: false, // 抽屉歌词
			showList: false, //抽屉播放列表
			// showLyric: false, //歌词
			audio: null, //音频对象
			circleLeft: null, // 圆圈左边距离
			barWidth: null, // 播放进度条长度
			volume: 20, //音量
			duration: '00:00', // 歌曲时长
			currentTime: null, // 当前播放时长
			isTimerPlaying: false, //是否开始计时
			currentTrackIndex: 0, //当前歌曲索引
			transitionName: null, // 过渡动画名
			// currentTrack: this.$store.state.song.playingSong, //从缓存拿到当前的播放的歌曲对象
			// tracks: this.$store.state.song.listOfSongs, //从缓存拿到当前的播放歌曲列表对象

			// currentTrack: null, //当前歌曲对象
			// // // 歌曲对象
			defTracks: [
				{
					gqName: '此生不换',
					gqSingerName: '青鸟飞鱼',
					gqPic:
						'https://img0.baidu.com/it/u=4040902079,2954932421&fm=253&fmt=auto&app=138&f=PNG?w=889&h=500',
					gqUrl:
						'https://tyst.migu.cn/public/product5th/product28/2019/02/26/2018%E5%B9%B408%E6%9C%8802%E6%97%A508%E7%82%B953%E5%88%86%E7%B4%A7%E6%80%A5%E5%86%85%E5%AE%B9%E5%87%86%E5%85%A5%E5%8D%8E%E7%BA%B3444%E9%A6%96/%E6%A0%87%E6%B8%85%E9%AB%98%E6%B8%85/MP3_320_16_Stero/6005751EFTP.mp3',
					url: 'https://www.youtube.com/watch?v=z3wAjJXbYzA',
					favorited: false,
				},
			],
		};
	},
	computed: {
		// 先让缓存有个正确的数据
		//从缓存拿到当前的播放的歌曲对象
		//从缓存拿到当前的播放歌曲列表对象
		...mapState({
			tracks(state) {
				console.log('state.song.listOfSongs : ', state.song.listOfSongs);
				let newList = state.song.listOfSongs;

				if (!newList) {
					console.log('初始化track');
					return this.defTracks;
				}
				return newList;
			},
			currentTrack(state) {
				// console.log('this.audio : ', this.audio);
				let newTrack = state.song.playingSong;
				// 如果没有值给个默认值
				if (!newTrack) {
					console.log('currentTrack');
					return this.defTracks[0];
				}
				// this.audio.src = newTrack.gqUrl;
				// this.audio.load();
				// this.audio.play();

				return state.song.playingSong;
			},
		}),
	},
	watch: {
		// ...mapState({
		// 	currentTrack: (state) => state.song.playingSong, //从缓存拿到当前的播放的歌曲对象
		// 	tracks: (state) => state.song.listOfSongs, //从缓存拿到当前的播放歌曲列表对象
		// }),
		currentTrack(newTrack, oldTrack) {
			this.$store.commit('setId', newTrack.id);
			this.$store.commit('setGqLyric', newTrack.gqLyric);
			this.audio.src = newTrack.gqUrl;
			// this.resetPlayer();
			// this.audio.load();
			// this.audio.play();
			this.play();
		},
		currentTime() {
			this.$store.commit('setCurrentTime', this.currentTime);
		},
		duration() {
			this.$store.commit('setDuration', this.duration);
		},
		tracks(val) {
			console.log('this.tracks : ', val);
		},
		// showLyric() {
		// 	this.$store.commit('setShowLyric', this.showLyric);
		// },
	},

	mounted() {
		// this.tracks = this.$store.getters.getListOfSongs;
		// this.currentTrack = this.$store.getters.getPlayingSong;

		// this.currentTrack = this.tracks[0];

		this.audio = this.$refs.player;
		let vm = this;

		// this.audio = new Audio();
		this.audio.src = this.currentTrack.gqUrl;
		this.audio.ontimeupdate = function () {
			vm.generateTime();
		};

		this.audio.onloadedmetadata = function () {
			vm.generateTime();
		};
		this.audio.onended = function () {
			vm.nextTrack();
			this.isTimerPlaying = true;
		};

		// this is optional (for preload covers)
		for (let index = 0; index < this.tracks.length; index++) {
			const element = this.tracks[index];
			let link = document.createElement('link');
			link.rel = 'prefetch';
			link.href = element.gqPic;
			link.as = 'image';
			document.head.appendChild(link);
		}
	},

	methods: {
		// 播放
		play() {
			if (this.audio.paused) {
				this.audio.play();
				this.isTimerPlaying = true;
			} else {
				this.audio.pause();
				this.isTimerPlaying = false;
			}
		},
		// 生成时间
		generateTime() {
			let width = (100 / this.audio.duration) * this.audio.currentTime;
			this.barWidth = width + '%';
			this.circleLeft = width + '%';
			let durmin = Math.floor(this.audio.duration / 60);
			let dursec = Math.floor(this.audio.duration - durmin * 60);
			let curmin = Math.floor(this.audio.currentTime / 60);
			let cursec = Math.floor(this.audio.currentTime - curmin * 60);
			if (durmin < 10) {
				durmin = '0' + durmin;
			}
			if (dursec < 10) {
				dursec = '0' + dursec;
			}
			if (curmin < 10) {
				curmin = '0' + curmin;
			}
			if (cursec < 10) {
				cursec = '0' + cursec;
			}
			this.duration = durmin + ':' + dursec;
			this.currentTime = curmin + ':' + cursec;
		},
		// 更新进度条
		updateBar(x) {
			let progress = this.$refs.progress;
			let maxduration = this.audio.duration;
			let position = x - progress.offsetLeft;
			let percentage = (100 * position) / progress.offsetWidth;
			if (percentage > 100) {
				percentage = 100;
			}
			if (percentage < 0) {
				percentage = 0;
			}
			this.barWidth = percentage + '%';
			this.circleLeft = percentage + '%';
			this.audio.currentTime = (maxduration * percentage) / 100;
			this.audio.play();
		},
		// 点击进度
		clickProgress(e) {
			this.isTimerPlaying = true;
			this.audio.pause();
			this.updateBar(e.pageX);
		},
		// 上一首
		prevTrack() {
			this.transitionName = 'scale-in';
			this.isShowCover = false;
			if (this.currentTrackIndex > 0) {
				this.currentTrackIndex--;
			} else {
				this.currentTrackIndex = this.tracks.length - 1;
				if (this.currentTrackIndex < 0) {
					this.currentTrackIndex = 0;
				}
			}
			// 因为vue是单项数据流，currentTrack设置了通过计算state中的状态得到，这里不能通过第二种方式赋值
			// this.currentTrack = this.tracks[this.currentTrackIndex];
			// 先改变store中state的状态，再取出来
			console.log('this.tracks : ', this.tracks);
			console.log('this.currentTrackIndex : ', this.currentTrackIndex);
			let newCurrentTrack = this.tracks[this.currentTrackIndex];
			console.log('newCurrentTrack : ', newCurrentTrack);

			if (!!newCurrentTrack) {
				this.$store.commit('setPlayingSong', newCurrentTrack);
				this.$store.commit('setCurrentSong', newCurrentTrack);
			}

			this.resetPlayer();
		},
		// 下一首
		nextTrack() {
			this.transitionName = 'scale-out';
			this.isShowCover = false;
			let nextIndex = this.tracks.length - 1;
			if (nextIndex < 0) {
				nextIndex = 0;
			}
			if (this.currentTrackIndex < nextIndex) {
				this.currentTrackIndex++;
			} else {
				this.currentTrackIndex = 0;
			}
			// 因为vue是单项数据流，currentTrack设置了通过计算state中的状态得到，这里不能通过第二种方式赋值
			// this.currentTrack = this.tracks[this.currentTrackIndex];
			// 先改变store中state的状态，再取出来
			let newCurrentTrack = this.tracks[this.currentTrackIndex];
			if (!!newCurrentTrack) {
				this.$store.commit('setPlayingSong', newCurrentTrack);
				this.$store.commit('setCurrentSong', newCurrentTrack);
			}

			this.resetPlayer();
		},
		// 重置播放器
		resetPlayer() {
			this.barWidth = 0;
			this.circleLeft = 0;
			this.audio.currentTime = 0;
			this.audio.src = this.currentTrack.gqUrl;
			setTimeout(() => {
				if (this.isTimerPlaying) {
					this.audio.play();
				} else {
					this.audio.pause();
				}
			}, 300);
		},
		// 音量调节
		changeVolume(value) {
			let newVolume = value / 100;
			if (newVolume >= 0 && newVolume <= 1) {
				this.audio.volume = newVolume;
				this.$store.commit('setVolume', value);
				this.volume = Math.floor(newVolume * 100);
			} else {
				console.error('音量必须在 0 到 1 之间');
			}
		},
		// 标记为喜欢
		favorite() {
			this.tracks[this.currentTrackIndex].favorited =
				!this.tracks[this.currentTrackIndex].favorited;
		},
		//下载音乐******
        download() {
			console.log('歌曲地址 : ',this.currentTrack.gqUrl)
            download(this.currentTrack.gqUrl)
            .then(res=>{
                //触发下载
                let content = res.data;
                let eleLink = document.createElement('a');
                eleLink.download = `${this.currentTrack.gqSingerName}-${this.currentTrack.gqName}.mp3`;
                eleLink.style.display = 'none';
                //把字符内容转换成blob地址
                let blob = new Blob([content]);
                eleLink.href = URL.createObjectURL(blob);
                //把链接地址加到document里
                document.body.appendChild(eleLink);
                //触发点击
                eleLink.click();
                //然后移除掉这个新加的控件
                document.body.removeChild(eleLink);
                console.log(" download success")
            })
            .catch(err =>{
                console.log(err);
            })
        },
	},
};
</script>
<style lang="scss" scoped>
@import '@/assets/css/music-player.scss';
</style>
