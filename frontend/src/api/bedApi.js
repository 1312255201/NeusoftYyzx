// 引入封装好的axios实例
import http from "@/request/request.js";

/**
 * 获取床位示意图视图数据
 * @param {Object} data - 请求参数
 * @returns {Promise} - 包含响应数据的Promise对象
 */
export function findCwsyBedVo(data) {
	return http.get('/room/findCwsyBedVo', {
		params: data
	});
};
export function findBedDetailsList(data) {
    return http.get('/beddetails/listBedDetailsVoPage', {
        params: data
    });
};
/**
 * 根据房间查询床位信息
 * @param {Object} data - 请求参数
 * @returns {Promise} - 包含响应数据的Promise对象
 */
export function findBedByRoom(data) {
	return http.get('/bed/findBed', {
		params: data
	});
};

/**
 * 根据床位ID获取床位人员信息
 * @param {Number} bedId - 床位ID
 * @returns {Promise} - 包含响应数据的Promise对象
 */
export function getBedPersonInfo(bedId) {
	return http.get(`/beddetails/getBedPersonInfo/${bedId}`);
};

/**
 * 更新床位详情信息-床位开始使用时间
 * @param {Object} data - 请求体数据
 * @returns {Promise} - 包含响应数据的Promise对象
 */
export function updateDetailsStartDate(data) {
	return http.post('/beddetails/updateBedDetails', data);
};

/**
 * 床位调换
 * @param {Object} data - 请求体数据
 * @returns {Promise} - 包含响应数据的Promise对象
 */
export function exchangeBed(data) {
	return http.post('/beddetails/exchangeBed', data);
};

/**
 * 删除床位详情
 * @param {Number} id - 床位详情ID
 * @returns {Promise} - 包含响应数据的Promise对象
 */
export function delBedDetails(id) {
	return http.post('/beddetails/delBedDetails', id, {
		headers: {
			'Content-Type': 'application/json'
		}
	});
};