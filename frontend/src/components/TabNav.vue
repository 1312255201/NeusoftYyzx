<template>
	<div class="container_tab">
		<ul class="tab_nav_box">
			<li v-for="(item, index) in $store.getters.tabs" :key="item.title"
				:class="{ active: $route.path === item.path }">
				<router-link :to="item.path">{{item.title}}</router-link>
				<el-icon v-if="index !== 0">
					<CloseBold @click="onCloseTabIndex(index)" />
				</el-icon>
			</li>
		</ul>

	</div>
</template>

<script>
	export default {
		name: "TabNav",
		methods: {
			/**
			 * 点击了tab选项卡
			 */
			onCloseTabIndex(index) {
				this.$store.commit("deleteTabByIndex", index);
				//获取上一个tab
				let path = this.$store.getters.tabs[this.$store.getters.tabs.length - 1].path;
				this.$router.push(path);
			}
		},
	}
</script>

<style scoped>
	.container_tab {
		padding-left: 20px;
		border-bottom: 1px solid var(--border-color);
		background-color: var(--bg-color);
		transition: background-color 0.3s ease, border-color 0.3s ease;
	}

	.tab_nav_box {
		display: flex;
		align-items: center;
		padding: 2px 0px;
	}

	.tab_nav_box li {
		height: 28px;
		line-height: 28px;
		padding: 1px;
		display: flex;
		align-items: center;
		margin-right: 8px;
		border: 1px solid var(--border-color);
		border-radius: 5%;
		opacity: 0.9;
		background-color: var(--card-bg-color);
		transition: all 0.3s ease;
	}

	.tab_nav_box li:hover {
		opacity: 1;
		background-color: var(--button-hover-bg-color);
	}

	.tab_nav_box li a {
		padding-left: 10px;
		padding-right: 10px;
		display: inline-block;
		color: var(--text-color-primary);
		transition: color 0.3s ease;
	}

	.tab_nav_box li:nth-child(n+2) a {
		padding-right: 15px;
	}

	.tab_nav_box li i:hover {
		color: var(--text-color-secondary);
	}

	.tab_nav_box li.active {
		font-size: 15px;
		background-color: var(--primary-color);
		color: white;
		border-color: var(--primary-color);
	}

	.tab_nav_box li.active a {
		color: #ffffff;
	}

	/* 暗色主题下的特殊处理 */
	[data-theme='dark'] .tab_nav_box li {
		border-color: var(--border-color);
	}

	[data-theme='dark'] .tab_nav_box li:hover {
		background-color: var(--button-hover-bg-color);
	}

	[data-theme='dark'] .tab_nav_box li.active {
		background-color: var(--primary-color);
		border-color: var(--primary-color);
	}
</style>