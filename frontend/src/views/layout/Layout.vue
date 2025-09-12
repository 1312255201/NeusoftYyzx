<template>
	<el-container style="height: 100%;">
    <el-header style="border-bottom: 1px solid #e4e7ed;">
      <Header></Header>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <Aside></Aside>
      </el-aside>
      <!--Tab选项-->
      <el-main style="padding: 5px;">
        <TabNav></TabNav>
        <transition name="fade" mode="out-in">
          <router-view />
        </transition>
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

	/* 页面切换淡入淡出动画 */
	.fade-enter-active,
	.fade-leave-active {
		transition: opacity 0.5s ease-in-out;
	}

	.fade-enter-from,
	.fade-leave-to {
		opacity: 0;
	}

	.fade-enter-to,
	.fade-leave-from {
		opacity: 1;
	}
</style>