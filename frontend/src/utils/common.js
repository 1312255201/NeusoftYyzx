//获取当前时间（XXXX-XX-XX）
export function getCurDate() {
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth() + 1;
	var day = now.getDate();
	month = month < 10 ? "0" + month : month;
	day = day < 10 ? "0" + day : day;
	return year + "-" + month + "-" + day;
}

//向sessionStorage中存储一个JSON对象
export function setSessionStorage(keyStr, value) {
	sessionStorage.setItem(keyStr, JSON.stringify(value));
}

//从sessionStorage中获取一个JSON对象（取不到时返回null）
export function getSessionStorage(keyStr) {
	var str = sessionStorage.getItem(keyStr);
	if (str == '' || str == null || str == 'null' || str == undefined) {
		return null;
	} else {
		return JSON.parse(str);
	}
}

//从sessionStorage中移除一个JSON对象
export function removeSessionStorage(keyStr) {
	sessionStorage.removeItem(keyStr);
}

//向localStorage中存储一个JSON对象
export function setLocalStorage(keyStr, value) {
	localStorage.setItem(keyStr, JSON.stringify(value));
}

//从localStorage中获取一个JSON对象（取不到时返回null）
export function getLocalStorage(keyStr) {
	var str = localStorage.getItem(keyStr);
	if (str == '' || str == null || str == 'null' || str == undefined) {
		return null;
	} else {
		return JSON.parse(str);
	}
}

//从localStorage中移除一个JSON对象
export function removeLocalStorage(keyStr) {
	localStorage.removeItem(keyStr);
}

// 人性化时间格式化函数
export function formatDate(date, format = 'YYYY年MM月DD日') {
	if (!date) return '-';
	
	let dateObj;
	if (typeof date === 'string') {
		dateObj = new Date(date);
	} else if (date instanceof Date) {
		dateObj = date;
	} else {
		return '-';
	}
	
	// 检查日期是否有效
	if (isNaN(dateObj.getTime())) {
		return '-';
	}
	
	const year = dateObj.getFullYear();
	const month = String(dateObj.getMonth() + 1).padStart(2, '0');
	const day = String(dateObj.getDate()).padStart(2, '0');
	const hours = String(dateObj.getHours()).padStart(2, '0');
	const minutes = String(dateObj.getMinutes()).padStart(2, '0');
	const seconds = String(dateObj.getSeconds()).padStart(2, '0');
	
	return format
		.replace('YYYY', year)
		.replace('MM', month)
		.replace('DD', day)
		.replace('HH', hours)
		.replace('mm', minutes)
		.replace('ss', seconds);
}

// 相对时间格式化（多久之前/之后）
export function formatRelativeTime(date) {
	if (!date) return '-';
	
	let dateObj;
	if (typeof date === 'string') {
		dateObj = new Date(date);
	} else if (date instanceof Date) {
		dateObj = date;
	} else {
		return '-';
	}
	
	if (isNaN(dateObj.getTime())) {
		return '-';
	}
	
	const now = new Date();
	const diff = now.getTime() - dateObj.getTime();
	const diffDays = Math.floor(diff / (1000 * 60 * 60 * 24));
	
	if (diffDays === 0) {
		return '今天';
	} else if (diffDays === 1) {
		return '昨天';
	} else if (diffDays === -1) {
		return '明天';
	} else if (diffDays > 0 && diffDays <= 7) {
		return `${diffDays}天前`;
	} else if (diffDays < 0 && diffDays >= -7) {
		return `${Math.abs(diffDays)}天后`;
	} else {
		return formatDate(dateObj, 'YYYY年MM月DD日');
	}
}

// 智能时间格式化（根据时间远近自动选择格式）
export function formatSmartDate(date) {
	if (!date) return '-';
	
	let dateObj;
	if (typeof date === 'string') {
		dateObj = new Date(date);
	} else if (date instanceof Date) {
		dateObj = date;
	} else {
		return '-';
	}
	
	if (isNaN(dateObj.getTime())) {
		return '-';
	}
	
	const now = new Date();
	const diff = Math.abs(now.getTime() - dateObj.getTime());
	const diffDays = Math.floor(diff / (1000 * 60 * 60 * 24));
	
	// 7天内显示相对时间
	if (diffDays <= 7) {
		return formatRelativeTime(dateObj);
	}
	// 超过7天显示具体日期
	else {
		return formatDate(dateObj, 'YYYY年MM月DD日');
	}
}