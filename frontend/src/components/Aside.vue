<template>
	<el-aside id="asideNav" width="200px">

		<el-menu :default-active="$route.path"
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
	}

	#asideNav .logo-name {
		width: 100%;
		height: 150px;
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
	}

</style>