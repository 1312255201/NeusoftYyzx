import {
	createApp
} from 'vue'

// 导入elementUI的依赖
import ElementPlus, {ElCollapseTransition} from 'element-plus'
import 'element-plus/dist/index.css'
// 导入Element Plus中文语言包
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import App from './App.vue'
import router from './router'
import store from './store'

const app = createApp(App)
app.component(ElCollapseTransition.name, ElCollapseTransition)
// 注册ElementPlus图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
	app.component(key, component)
}

// 全局注册ElementPlus并配置中文语言
app.use(ElementPlus, {
	locale: zhCn,
}).use(store).use(router)

// 特别注册通知组件
import {
	ElNotification
} from 'element-plus'
app.config.globalProperties.$notify = ElNotification

app.mount('#app')