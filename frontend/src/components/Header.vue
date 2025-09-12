<template>
	<el-header id="header">
		<div>
			<img style="width:200px;height:40px;margin:20px 0 0 -12px" src="@/assets/welcome.png" />
		</div>

		<el-dropdown split-button type="default">
			{{user.nickname}}
			<template #dropdown>
				<el-dropdown-menu>
					<el-dropdown-item @click="logout">注销</el-dropdown-item>
				</el-dropdown-menu>
			</template>
		</el-dropdown>

	</el-header>
</template>

<script>
	import {
		getSessionStorage,
		setSessionStorage
	} from '@/utils/common.js'
	import {
		logout as logoutApi
	} from '@/api/userApi.js'
	export default {
		name: "Header",
		data() {
			return {
				user: getSessionStorage('user')
			}
		},
		methods: {
			logout() {
				const token = sessionStorage.getItem('token')
				
				// 如果没有token，直接清空本地存储并跳转
				if (!token || token === 'null') {
					this.clearLocalStorage()
					return
				}
				
				// 调用后端logout接口
				logoutApi(token).then(res => {
					if (res.flag) {
						this.$message.success(res.message)
					} else {
						this.$message.warning(res.message)
					}
					// 无论后端接口成功与否，都清空本地存储并跳转
					this.clearLocalStorage()
				}).catch(error => {
					console.error('退出登录接口调用失败:', error)
					this.$message.error('退出登录失败，但将清空本地登录信息')
					// 即使接口调用失败，也要清空本地存储
					this.clearLocalStorage()
				})
			},
			
			clearLocalStorage() {
				sessionStorage.setItem('token', null)
				setSessionStorage('user', null)
				this.$store.commit('addMenus', [])
				this.$store.commit('clearTab', [])
				this.$router.push("/")
			}
		},

	}
</script>

<style scoped>
	#header {
		max-height: 50px;
		line-height: 50px;

		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	#header>h2 {
		color: #0b67b8;
	}
</style>