<template>
	<el-header id="header">
		<div>
			<img style="width:200px;height:40px;margin:20px 0 0 -12px" src="@/assets/welcome.png" />
		</div>

		<div class="header-right">
			<!-- 主题切换按钮 -->
			<el-tooltip :content="isDarkMode ? '切换到亮色模式' : '切换到暗色模式'" placement="bottom">
				<el-button 
					@click="toggleTheme" 
					type="text" 
					class="theme-toggle-btn"
					:icon="isDarkMode ? 'Sunny' : 'Moon'"
					size="large"
				>
				</el-button>
			</el-tooltip>
			
			<!-- 用户下拉菜单 -->
			<el-dropdown split-button type="default">
				{{user.nickname}}
				<template #dropdown>
					<el-dropdown-menu>
						<el-dropdown-item @click="logout">注销</el-dropdown-item>
					</el-dropdown-menu>
				</template>
			</el-dropdown>
		</div>

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
		computed: {
			isDarkMode() {
				return this.$store.getters.isDarkMode
			}
		},
		methods: {
			toggleTheme() {
				this.$store.dispatch('toggleTheme')
			},
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
		padding: 0 20px;
		background-color: var(--header-bg-color, #ffffff);
		border-bottom: 1px solid var(--border-color, #e4e7ed);
		transition: all 0.3s ease;
	}

	#header>h2 {
		color: #0b67b8;
	}

	.header-right {
		display: flex;
		align-items: center;
		gap: 15px;
	}

	.theme-toggle-btn {
		width: 40px;
		height: 40px;
		border-radius: 50%;
		display: flex;
		align-items: center;
		justify-content: center;
		transition: all 0.3s ease;
		color: var(--text-color, #606266);
		background-color: var(--button-bg-color, #f5f7fa);
		border: 1px solid var(--border-color, #dcdfe6);
	}

	.theme-toggle-btn:hover {
		color: var(--primary-color, #409eff);
		background-color: var(--button-hover-bg-color, #ecf5ff);
		border-color: var(--primary-color, #409eff);
		transform: scale(1.1);
	}

	.theme-toggle-btn:active {
		transform: scale(0.95);
	}
</style>