<template>
  <div class="tcy_404 container">
    <img src="@/assets/404.png">
    <h2>请稍后～ 正在为您加载信息</h2>
    <p>稍等片刻～ 通常不会超过5s</p>
  </div>
</template>

<script>

	export default {
		mounted() {
			// 5秒后自动刷新页面
			setTimeout(() => {
				location.reload();
			}, 3000);
		}
	}
</script>

<style scoped>
.btn:hover {
  transform: scale(1.05);
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

body{font-size:12px;background:#fff;font-family:"Lantinghei SC","Helvetica Neue","Microsoft YaHei","WenQuanYi Micro Hei","Heiti SC","Segoe UI",Arial,sans-serif}
body,h1,h2,h3,h4,h5,p,ul,li{padding:0;margin:0;list-style:none}
.tcy_404{padding-top:180px;padding-bottom:112px}
.container{width:1170px}
.container{padding-right:15px;padding-left:15px;margin-right:auto;margin-left:auto}
.tcy_404 img{display:block;margin:auto}
.tcy_404 h2{font-size:32px;color:#333333;text-align:center;letter-spacing:5px;padding-top:33px;padding-bottom:25px}
.tcy_404 p{font-size:14px;color:#666666;letter-spacing:1px;text-align:center;padding-bottom:35px}
.tcy_404 a{display:block;margin:auto;width:220px;height:56px}
</style>