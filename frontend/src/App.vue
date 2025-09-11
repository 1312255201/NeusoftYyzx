<template>
  <div id="app" :data-theme="$store.getters.theme">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  components: {},
}

</script>


<style>
/* 全局CSS变量定义 */
:root {
  /* 亮色主题变量 */
  --primary-color: #409eff;
  --success-color: #67c23a;
  --warning-color: #e6a23c;
  --danger-color: #f56c6c;
  --info-color: #909399;
  
  /* 背景色 */
  --bg-color: #ffffff;
  --bg-color-page: #f5f7fa;
  --bg-color-overlay: rgba(255, 255, 255, 0.9);
  
  /* 文字颜色 */
  --text-color-primary: #303133;
  --text-color-regular: #606266;
  --text-color-secondary: #909399;
  --text-color-placeholder: #c0c4cc;
  
  /* 边框颜色 */
  --border-color: #dcdfe6;
  --border-color-light: #e4e7ed;
  --border-color-lighter: #ebeef5;
  --border-color-extra-light: #f2f6fc;
  
  /* 组件背景色 */
  --header-bg-color: #ffffff;
  --aside-bg-color: #3ca2e0;
  --card-bg-color: #ffffff;
  --button-bg-color: #f5f7fa;
  --button-hover-bg-color: #ecf5ff;
  
  /* 阴影 */
  --box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
  --box-shadow-light: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

/* 暗色主题变量 */
[data-theme='dark'] {
  --primary-color: #409eff;
  --success-color: #67c23a;
  --warning-color: #e6a23c;
  --danger-color: #f56c6c;
  --info-color: #909399;
  
  /* 背景色 */
  --bg-color: #1a1a1a;
  --bg-color-page: #0d1117;
  --bg-color-overlay: rgba(26, 26, 26, 0.9);
  
  /* 文字颜色 */
  --text-color-primary: #e6edf3;
  --text-color-regular: #c9d1d9;
  --text-color-secondary: #8b949e;
  --text-color-placeholder: #6e7681;
  
  /* 边框颜色 */
  --border-color: #30363d;
  --border-color-light: #21262d;
  --border-color-lighter: #161b22;
  --border-color-extra-light: #0d1117;
  
  /* 组件背景色 */
  --header-bg-color: #161b22;
  --aside-bg-color: #21262d;
  --card-bg-color: #21262d;
  --button-bg-color: #21262d;
  --button-hover-bg-color: #30363d;
  
  /* 阴影 */
  --box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3), 0 0 6px rgba(0, 0, 0, 0.2);
  --box-shadow-light: 0 2px 12px 0 rgba(0, 0, 0, 0.4);
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: var(--text-color-primary);
  background-color: var(--bg-color-page);
  height: 100%;
  transition: background-color 0.3s ease, color 0.3s ease;
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

html,
body,
#app {
  width: 100%;
  height: 100%;
  font-family: "微软雅黑";
}

ul,
ol {
  list-style: none;
}

a {
  text-decoration: none;
  color: var(--text-color-primary);
}

/* Element Plus 暗色主题覆盖 */
[data-theme='dark'] .el-container {
  background-color: var(--bg-color);
}

[data-theme='dark'] .el-header {
  background-color: var(--header-bg-color) !important;
  border-bottom-color: var(--border-color) !important;
}

[data-theme='dark'] .el-aside {
  background-color: var(--aside-bg-color) !important;
}

[data-theme='dark'] .el-main {
  background-color: var(--bg-color-page) !important;
}

[data-theme='dark'] .el-card {
  background-color: var(--card-bg-color) !important;
  border-color: var(--border-color) !important;
}

[data-theme='dark'] .el-table {
  background-color: var(--card-bg-color) !important;
}

[data-theme='dark'] .el-table th {
  background-color: var(--button-bg-color) !important;
  color: var(--text-color-primary) !important;
}

[data-theme='dark'] .el-table td {
  background-color: var(--card-bg-color) !important;
  color: var(--text-color-regular) !important;
  border-bottom-color: var(--border-color) !important;
}

[data-theme='dark'] .el-table__row:hover > td {
  background-color: var(--button-hover-bg-color) !important;
}

[data-theme='dark'] .el-button {
  background-color: var(--button-bg-color);
  border-color: var(--border-color);
  color: var(--text-color-primary);
}

[data-theme='dark'] .el-button:hover {
  background-color: var(--button-hover-bg-color);
}

[data-theme='dark'] .el-input__wrapper {
  background-color: var(--button-bg-color) !important;
  border-color: var(--border-color) !important;
}

[data-theme='dark'] .el-input__inner {
  color: var(--text-color-primary) !important;
}

[data-theme='dark'] .el-dropdown-menu {
  background-color: var(--card-bg-color) !important;
  border-color: var(--border-color) !important;
}

[data-theme='dark'] .el-dropdown-menu__item {
  color: var(--text-color-regular) !important;
}

[data-theme='dark'] .el-dropdown-menu__item:hover {
  background-color: var(--button-hover-bg-color) !important;
}
</style>