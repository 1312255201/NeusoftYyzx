import {
	createStore
} from 'vuex'
import {
	getSessionStorage,
	setSessionStorage
} from '@/utils/common.js'

export default createStore({
	state: {
		tabs: [],
		menus: getSessionStorage('menuList') || [],
		theme: getSessionStorage('theme') || 'light' // 主题状态：light 或 dark
	},
	getters: {
		tabs(state) {
			return state.tabs;
		},
		menus(state) {
			return state.menus;
		},
		theme(state) {
			return state.theme;
		},
		isDarkMode(state) {
			return state.theme === 'dark';
		}
	},
	mutations: {
		addMenus(state, param) {
			state.menus = param;
			setSessionStorage('menuList', param);
		},
		deleteTabByIndex(state, index) {
			state.tabs.splice(index, 1);
		},
		clearTab(state, param) {
			state.tabs = param;
		},
		addTab(state, payload) {
			let path = payload.path;
			if (path) {
				let result = state.tabs.filter((item) => {
					return item.path == path;
				});
				if (result.length == 0) {
					if (state.tabs.length == 10) {
						state.tabs.splice(1, 1);
					}
					state.tabs.push(payload);
				}
			}
		},
		// 主题相关mutations
		setTheme(state, theme) {
			state.theme = theme;
			setSessionStorage('theme', theme);
			// 更新HTML根元素的data-theme属性
			document.documentElement.setAttribute('data-theme', theme);
		},
		toggleTheme(state) {
			const newTheme = state.theme === 'light' ? 'dark' : 'light';
			state.theme = newTheme;
			setSessionStorage('theme', newTheme);
			document.documentElement.setAttribute('data-theme', newTheme);
		}
	},
	actions: {
		// 添加setMenu action
		setMenu({
			commit
		}, menu) {
			commit('addMenus', menu);
		},
		// 主题相关actions
		initTheme({
			commit,
			state
		}) {
			// 初始化主题，设置HTML根元素的data-theme属性
			document.documentElement.setAttribute('data-theme', state.theme);
		},
		changeTheme({
			commit
		}, theme) {
			commit('setTheme', theme);
		},
		toggleTheme({
			commit
		}) {
			commit('toggleTheme');
		}
	},
	modules: {}
})