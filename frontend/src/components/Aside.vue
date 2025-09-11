<template>
	<el-aside id="asideNav" width="200px">
		<div class="logo-name">
			<p>东软颐养中心</p>
			<img src="../assets/logo.png" class="logo-png" />
		</div>
		<el-menu :active-text-color="$store.getters.isDarkMode ? '#409eff' : '#ffd04b'" 
			:background-color="$store.getters.isDarkMode ? 'var(--aside-bg-color)' : '#3CA2E0'" 
			:default-active="$route.path" 
			:text-color="$store.getters.isDarkMode ? 'var(--text-color-primary)' : '#fff'"
			:router="true" unique-opened>
			<template v-for="menu in $store.getters.menus">
				<el-sub-menu :index="menu.menusIndex">
					<template #title>
						<el-icon>
							<!-- 动态获取图标 -->
							<component :is="menu.icon"></component>
						</el-icon>
						<span>{{menu.title}}</span>
					</template>
					<template v-for="menu in menu.children">
						<el-menu-item :index="menu.path"><el-icon>
								<component :is="menu.icon"></component>
							</el-icon>{{menu.title}}</el-menu-item>
					</template>
				</el-sub-menu>
			</template>
		</el-menu>
	</el-aside>
</template>

<script>
	export default {
		name: 'Aside',
		methods: {

		},
		watch: {
			//监听浏览器直接输入路由,将此路由添加到tabs中
			"$route.path": {
				handler: function(newVal, oldVal) {
					if (true) {
						return;
					}
					//判断当前tab是否存在了.
					let index = this.$store.getters.tabs.findIndex(function(value, key) {
						return value.path === newVal;
					});
					if (index != -1 || newVal == "/login") {
						return;
					}
					let menuName = this.$store.getters.getMenuNameByUrl(newVal);
					//手动构建tab
					this.$store.commit("addTab", {
						title: menuName,
						path: newVal
					});
				},
				immediate: true
			}
		}
	}
</script>

<style scoped>
	#asideNav {
		display: flex;
		flex-direction: column;
		background-color: var(--aside-bg-color);
		transition: background-color 0.3s ease;
	}

	#asideNav .logo-name {
		width: 100%;
		height: 150px;
		background-color: var(--aside-bg-color);
		transition: background-color 0.3s ease;
	}

	#asideNav .logo-name .logo-png {
		width: 165px;
		height: 165px;
		margin: -30px 0 0 10px;
	}

	#asideNav .logo-name p {
		font-weight: 800;
		height: 50px;
		line-height: 50px;
		text-align: center;
		font-size: 25px;
		margin: 0px;
		color: #fff;
	}

	#asideNav .el-menu {
		/* font-weight: 550; */
		flex: 1;
		border-right: none;
		background-color: var(--aside-bg-color) !important;
		transition: background-color 0.3s ease;
	}

	/* 菜单项样式优化 */
	:deep(.el-menu-item) {
		transition: color 0.3s ease, background-color 0.3s ease;
	}

	:deep(.el-menu-item:hover) {
		background-color: rgba(255, 255, 255, 0.1) !important;
	}

	:deep(.el-menu-item.is-active) {
		background-color: rgba(255, 255, 255, 0.2) !important;
	}

	:deep(.el-sub-menu__title) {
		transition: color 0.3s ease, background-color 0.3s ease;
	}

	:deep(.el-sub-menu__title:hover) {
		background-color: rgba(255, 255, 255, 0.1) !important;
	}

	:deep(.el-sub-menu.is-active .el-sub-menu__title) {
		background-color: rgba(255, 255, 255, 0.2) !important;
	}

	/* 暗色主题下的特殊处理 */
	[data-theme='dark'] :deep(.el-menu-item:hover) {
		background-color: var(--button-hover-bg-color) !important;
	}

	[data-theme='dark'] :deep(.el-menu-item.is-active) {
		background-color: var(--primary-color) !important;
		color: #ffffff !important;
	}

	[data-theme='dark'] :deep(.el-sub-menu__title:hover) {
		background-color: var(--button-hover-bg-color) !important;
	}

	[data-theme='dark'] :deep(.el-sub-menu.is-active .el-sub-menu__title) {
		background-color: var(--primary-color) !important;
		color: #ffffff !important;
	}
</style>