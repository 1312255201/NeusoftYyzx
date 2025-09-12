<template>
  <transition name="page" mode="out-in">
    <router-view/>
  </transition>
</template>

<script>
export default {
  name: 'App',
  components: {},
}

</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #000000;
  height: 100%;
}

/* 页面级切换动画 */
.page-enter-active,
.page-leave-active {
  transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
}

.page-enter-from {
  opacity: 0;
  transform: translateX(30px);
}

.page-leave-to {
  opacity: 0;
  transform: translateX(-30px);
}

.page-enter-to,
.page-leave-from {
  opacity: 1;
  transform: translateX(0);
}
html,
	body,
	div,
	span,
	h1,
	h2,
	h3,
	h4,
	h5,
	h6,
	ul,
	ol,
	li,
	p {
		margin: 0;
		padding: 0;
	}

ul,
	ol {
		list-style: none;
	}

	a {
		text-decoration: none;
	}
</style>