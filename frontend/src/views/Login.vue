<template>
	<div class="login-bg">
		<div class="login">
			<!-- 左右布局容器 -->
			<div class="login-container">
				<!-- 左侧Logo区域 -->
				<div class="login-left">
					<div class="login-logo">
						<img src="@/assets/logosmall.jpg" alt="东软颐养中心" class="logo-img" />
					</div>
					<div class="message">东软颐养中心</div>
					<div class="subtitle">智慧养老管理系统</div>
				</div>
				
				<!-- 右侧登录表单区域 -->
				<div class="login-right">
					<div class="form-title">用户登录</div>
					<el-form :model="loginForm" :rules="rules" ref="loginFormRef" class="login-form">
						<el-form-item prop="username">
							<el-input 
								v-model="loginForm.username" 
								placeholder="请输入用户名" 
								size="large"
								prefix-icon="User"
								clearable
							/>
						</el-form-item>
						<el-form-item prop="password">
							<el-input 
								v-model="loginForm.password" 
								type="password" 
								placeholder="请输入密码" 
								size="large"
								prefix-icon="Lock"
								show-password
								clearable
								@keyup.enter="login"
							/>
						</el-form-item>
						<el-form-item>
							<div class="button-group">
								<el-button 
									type="primary" 
									@click="login" 
									size="large" 
									class="login-btn"
									:loading="loading"
								>
									{{ loading ? '登录中...' : '登录' }}
								</el-button>
								<el-button 
									type="default" 
									@click="resetForm" 
									size="large" 
									class="reset-btn"
								>
									重置
								</el-button>
							</div>
						</el-form-item>
					</el-form>
				</div>
			</div>
		</div>
	</div>
</template>


<script>
	import {
		login
	} from '../api/userApi.js'
	import {
		setSessionStorage
	} from '@/utils/common.js'
	import { User, Lock } from '@element-plus/icons-vue'

	export default {
		components: {
			User,
			Lock
		},
		data() {
			return {
				loading: false,
				loginForm: {
					username: "",
					password: ""
				},
				// 表单验证规则
				rules: {
					username: [
						{ required: true, message: '请输入用户名', trigger: 'blur' },
						{ min: 2, max: 20, message: '用户名长度在 2 到 20 个字符', trigger: 'blur' }
					],
					password: [
						{ required: true, message: '请输入密码', trigger: 'blur' },
						{ min: 2, max: 20, message: '密码长度在 2 到 20 个字符', trigger: 'blur' }
					]
				}
			}
		},
		methods: {
			login() {
				// 表单验证
				this.$refs.loginFormRef.validate((valid) => {
					if (valid) {
						this.loading = true;
						login(this.loginForm).then(res => {
							console.log(res);
							if (res.flag) {
								// 将token存入storage
								sessionStorage.setItem('token', res.message);
								// 将当前登录用户信息存入storage中
								setSessionStorage('user', res.data);
								// 将菜单列表存入vuex中
								this.$store.commit('addMenus', res.data.menuList);

								// 显示成功消息
								this.$message.success('登录成功！');
								
								// 路由到第一个子菜单
								this.$router.push(res.data.menuList[0].children[0].path);
							} else {
								this.$message.error(res.message || '登录失败，请检查用户名和密码');
							}
						}).catch(error => {
							console.error('登录错误:', error);
							this.$message.error('网络错误，请稍后重试');
						}).finally(() => {
							this.loading = false;
						});
					} else {
						this.$message.error('请填写完整的登录信息');
						return false;
					}
				});
			},
			resetForm() {
				// 重置表单数据
				this.loginForm.username = '';
				this.loginForm.password = '';
				// 清除表单验证状态
				if (this.$refs.loginFormRef) {
					this.$refs.loginFormRef.clearValidate();
				}
			}
		}
	}
</script>

<style scoped>
	canvas {
		display: block;
		margin: 0;
		width: 100%;
		height: 100%;
		position: fixed;
		left: 0;
		right: 0;
		top: 0;
		bottom: 0;
		z-index: -1;
	}

	.login-bg {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: url(@/assets/loginBJ.jpg) no-repeat center;
	background-size: cover;
	display: flex;
	align-items: center;
	justify-content: center;
	z-index: 1000;
	overflow: hidden;
}

	.login {
		width: 900px;
		height: 500px;
		background: rgba(255, 255, 255, 0.95);
		border-radius: 15px;
		box-shadow: 0 15px 35px rgba(0, 0, 0, 0.1);
		backdrop-filter: blur(10px);
		border: 1px solid rgba(255, 255, 255, 0.2);
		position: relative;
		overflow: hidden;
	}

	.login::before {
		content: '';
		position: absolute;
		top: -50%;
		left: -50%;
		width: 200%;
		height: 200%;
		background: linear-gradient(45deg, transparent, rgba(255, 255, 255, 0.1), transparent);
		transform: rotate(45deg);
		animation: shimmer 3s infinite;
	}

	@keyframes shimmer {
		0% { transform: translateX(-100%) translateY(-100%) rotate(45deg); }
		100% { transform: translateX(100%) translateY(100%) rotate(45deg); }
	}

	.login-container {
		display: flex;
		height: 100%;
		position: relative;
		z-index: 1;
	}

	/* 左侧Logo区域 */
	.login-left {
		flex: 1;
		background: linear-gradient(135deg, rgba(102, 126, 234, 0.1) 0%, rgba(118, 75, 162, 0.1) 100%);
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
		padding: 40px;
		border-radius: 15px 0 0 15px;
	}

	.login-logo {
		margin-bottom: 30px;
	}

	.logo-img {
		width: 120px;
		height: 120px;
		object-fit: contain;
		border-radius: 50%;
		box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
		transition: transform 0.3s ease;
	}

	.logo-img:hover {
		transform: scale(1.05);
	}

	.message {
		font-size: 32px;
		font-weight: bold;
		color: #333;
		margin-bottom: 15px;
		text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
		letter-spacing: 2px;
	}

	.subtitle {
		font-size: 16px;
		color: #666;
		font-weight: 300;
		letter-spacing: 1px;
	}

	/* 右侧登录表单区域 */
	.login-right {
		flex: 1;
		padding: 60px 50px;
		display: flex;
		flex-direction: column;
		justify-content: center;
	}

	.form-title {
		font-size: 24px;
		font-weight: 600;
		color: #333;
		margin-bottom: 40px;
		text-align: center;
	}

	.login-form {
		width: 100%;
	}

	.button-group {
		display: flex;
		gap: 15px;
	}

	.login-btn, .reset-btn {
		flex: 1;
		height: 45px;
		font-size: 16px;
		font-weight: 600;
		border-radius: 8px;
		transition: all 0.3s ease;
	}

	.login-btn {
		background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
		border: none;
		box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
	}

	.login-btn:hover {
		transform: translateY(-2px);
		box-shadow: 0 6px 20px rgba(102, 126, 234, 0.6);
	}

	.reset-btn {
		background: #f8f9fa;
		border: 1px solid #dee2e6;
		color: #6c757d;
	}

	.reset-btn:hover {
		background: #e9ecef;
		border-color: #adb5bd;
		transform: translateY(-2px);
		box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	}

	.login-btn:active, .reset-btn:active {
		transform: translateY(0);
	}

	#darkbannerwrap {
		height: 20px;
	}

	/* Element Plus 表单项样式覆盖 */
	:deep(.el-form-item) {
		margin-bottom: 25px;
	}

	:deep(.el-input__wrapper) {
		border-radius: 8px;
		box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
		transition: all 0.3s ease;
		border: 1px solid #e0e6ed;
	}

	:deep(.el-input__wrapper:hover) {
		box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
		border-color: #667eea;
	}

	:deep(.el-input__wrapper.is-focus) {
		box-shadow: 0 4px 12px rgba(102, 126, 234, 0.3);
		border-color: #667eea;
	}

	:deep(.el-input__inner) {
		font-size: 14px;
		padding: 12px 15px;
	}

	/* 错误提示样式 */
	:deep(.el-form-item__error) {
		color: #f56565;
		font-size: 12px;
		margin-top: 5px;
		animation: shake 0.5s ease-in-out;
	}

	/* 错误提示动画 */
	@keyframes shake {
		0%, 100% { transform: translateX(0); }
		25% { transform: translateX(-5px); }
		75% { transform: translateX(5px); }
	}

	/* 响应式设计 */
	@media (max-width: 1024px) {
		.login {
			width: 800px;
			height: 450px;
		}
		
		.login-right {
			padding: 40px 35px;
		}
		
		.logo-img {
			width: 100px;
			height: 100px;
		}
		
		.message {
			font-size: 28px;
		}
	}

	@media (max-width: 768px) {
		.login {
			width: 90%;
			height: auto;
			max-width: 500px;
		}
		
		.login-container {
			flex-direction: column;
		}
		
		.login-left {
			border-radius: 15px 15px 0 0;
			padding: 30px 20px;
		}
		
		.login-right {
			padding: 30px 25px;
		}
		
		.logo-img {
			width: 80px;
			height: 80px;
		}
		
		.message {
			font-size: 24px;
			margin-bottom: 10px;
		}
		
		.subtitle {
			font-size: 14px;
		}
		
		.form-title {
			font-size: 20px;
			margin-bottom: 25px;
		}
		
		.button-group {
			flex-direction: column;
			gap: 10px;
		}
	}
</style>