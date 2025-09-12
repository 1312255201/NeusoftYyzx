// 引入封装好的axios实例
import http from "@/request/request.js";

/**
 * 查询所有食物列表
 * @param {Object} data - 查询参数（可选）
 * @returns {Promise} - 包含食物列表的Promise对象
 */
export function listFood(data) {
	return http.get("/food/listFood", {
		params: data,
		headers: {
			'Content-Type': 'application/json'
		}
	});
}

/**
 * 添加食物
 * @param {FormData} formData - 包含食物信息和图片的FormData对象
 * @returns {Promise} - 包含操作结果的Promise对象
 */
export function addFood(formData) {
	return http.post('/food/addFood', formData, {
		headers: {
			'Content-Type': 'multipart/form-data'
		}
	});
}

/**
 * 更新食物信息
 * @param {Number} id - 食物ID
 * @param {FormData} formData - 包含更新的食物信息和图片的FormData对象
 * @returns {Promise} - 包含操作结果的Promise对象
 */
export function updateFood(id, formData) {
	return http.post(`/food/updateFood/${id}`, formData, {
		headers: {
			'Content-Type': 'multipart/form-data'
		}
	});
}

/**
 * 删除食物
 * @param {Number} id - 食物ID
 * @returns {Promise} - 包含操作结果的Promise对象
 */
export function deleteFood(id) {
	return http.post(`/food/deleteFood/${id}`, {}, {
		headers: {
			'Content-Type': 'application/json'
		}
	});
}

/**
 * 分页查询食物列表
 * @param {Object} data - 查询条件和分页参数
 * @returns {Promise} - 包含分页食物列表的Promise对象
 */
export function findFoodPage(data) {
	return http.get('/food/findFoodPage', {
		params: data,
		headers: {
			'Content-Type': 'application/json'
		}
	});
}