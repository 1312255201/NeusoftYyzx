<template>
	<el-container style="height: 100%;">

		<!--左边菜单-->
		<Aside></Aside>

		<!--右边内容-->
		<el-container direction="vertical">
			<Header></Header>

			<!--Tab选项-->
			<TabNav></TabNav>

			<el-main>
				<router-view />
			</el-main>
		</el-container>
	</el-container>
</template>

<script>
	import Aside from '@/components/Aside.vue'
	import Header from '@/components/Header.vue'
	import TabNav from '@/components/TabNav.vue'

	export default {
		name: 'Layout',
		components: {
			Aside,
			Header,
			TabNav
		},
		created() {
			//发送请求获取所有表的扩展信息？？
		}
	}
</script>

<style>
	* {
		margin: 0px;
		padding: 0px;
	}

	body {
		font-size: 14px;
		color: #333333;
	}

	li {
		list-style: none;
	}

	a {
		text-decoration: none;
	}

	html,
	body,
	#layout,
	.el-container,
	#asideNav,
	ul.el-menu {
		height: 100%;
	}
</style>

<style scoped>
.layout {
  height: 100%;
  background-color: var(--bg-color-page);
  transition: background-color 0.3s ease;
}

.el-container {
  background-color: var(--bg-color);
  transition: background-color 0.3s ease;
}

.el-header {
  background-color: var(--header-bg-color);
  border-bottom: 1px solid var(--border-color);
  transition: background-color 0.3s ease, border-color 0.3s ease;
}

.el-aside {
  background-color: var(--aside-bg-color);
  transition: background-color 0.3s ease;
}

.el-main {
  background-color: var(--bg-color-page);
  transition: background-color 0.3s ease;
}
</style>