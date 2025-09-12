<template>
	<div class="common-layout">
		<el-container>
			<el-header>
				<div class="header-container">
					<el-form :inline="true" class="search-form">
						<el-row :gutter="15" class="search-row">
							<!-- 食品类型选择下拉框 -->
							<el-col :span="6" class="input-col">
								<el-form-item label="食品类型: ">
									<el-select v-model="queryParams.foodType" placeholder="请选择食品类型" clearable size="large"
										@clear="query" class="full-width" style="width: 200px;">
										<el-option label="主食" value="主食" />
										<el-option label="汤品" value="汤品" />
										<el-option label="素食" value="素食" />
										<el-option label="荤食" value="荤食" />
										<el-option label="甜品" value="甜品" />
									</el-select>
								</el-form-item>
							</el-col>
							<!-- 食品名称输入框 -->
							<el-col :span="6" class="input-col">
								<el-form-item label="食品名称: ">
									<el-input placeholder="请输入食品名称" v-model="queryParams.foodName" @clear="query"
										clearable size="large" class="full-width">
									</el-input>
								</el-form-item>
							</el-col>
							<!-- 查询按钮 -->
							<el-col :span="3" class="btn-col">
								<el-form-item>
									<el-button type="primary" @click="query" color="#337ab7" icon="Search"
										class="operation-btn">
										<span>查询</span>
									</el-button>
								</el-form-item>
							</el-col>
							<!-- 添加按钮 -->
							<el-col :span="3" class="btn-col">
								<el-form-item>
									<el-button type="primary" @click="addItem" color="#337ab7" class="operation-btn">
										<el-icon>
											<Plus />
										</el-icon>
										<span>添加</span>
									</el-button>
								</el-form-item>
							</el-col>
						</el-row>
					</el-form>
				</div>
			</el-header>
			<el-divider style="margin:0"></el-divider>
			<el-main>
				<div>
					<!-- 表格 -->
					<el-table :data="foodList" style="width: 100% ;color:black;" stripe table-layout="fixed">
						<el-table-column align="center" type="index" :index="indexMethod" label="序号"  />
						<el-table-column align="center" prop="id" label="编号" />
						<el-table-column align="center" prop="foodName" label="食品名称"/>
						<el-table-column align="center" prop="foodType" label="食品类型" />
						<el-table-column align="center" prop="price" label="价格">
							<template #default="scope">¥{{ scope.row.price }}</template>
						</el-table-column>
						<el-table-column align="center" prop="isHalal" label="是否清真">
							<template #default="scope">
								<el-tag :type="scope.row.isHalal === 1 ? 'success' : 'info'">
									{{ scope.row.isHalal === 1 ? '是' : '否' }}
								</el-tag>
							</template>
						</el-table-column>
						<el-table-column align="center" prop="foodImg" label="食品图片" >
							<template #default="scope">
								<el-image v-if="scope.row.foodImg" 
									:src="getImageUrl(scope.row.foodImg)" 
									style="width: 50px; height: 50px; border-radius: 4px;"
									fit="cover"
									:preview-src-list="[getImageUrl(scope.row.foodImg)]"
									preview-teleported>
								</el-image>
								<span v-else style="color: #999;">无图片</span>
							</template>
						</el-table-column>
						<el-table-column align="center" label="操作" >
							<template #default="scope">
								<el-button type="primary" size="small" @click="edit(scope.row)">
									<el-icon><EditPen /></el-icon>
									编辑
								</el-button>
								<el-button type="danger" size="small" @click="del(scope.row.id)">
									<el-icon><Delete /></el-icon>
									删除
								</el-button>
							</template>
						</el-table-column>
					</el-table>
					<!-- 分页 -->
					<el-pagination
						v-model:current-page="page.currentPage"
						v-model:page-size="page.pageSize"
						:page-sizes="[10, 20, 50, 100]"
						:total="page.total"
						layout="total, sizes, prev, pager, next, jumper"
						@size-change="handleSizeChange"
						@current-change="handleCurrentChange"
						style="margin-top: 20px; text-align: right;"
					/>
				</div>
			</el-main>
		</el-container>

		<!-- 添加/编辑对话框 -->
		<el-dialog v-model="dialog.dialogVisible" :title="dialog.tops" width="600px" @close="handleClose">
			<el-form ref="itemForm" :model="dialog.item" :rules="rules" label-width="100px" class="demo-form-inline">
				<el-form-item label="食品名称" prop="foodName">
					<el-input v-model="dialog.item.foodName" placeholder="请输入食品名称" clearable />
				</el-form-item>
				<el-form-item label="食品类型" prop="foodType">
					<el-select v-model="dialog.item.foodType" placeholder="请选择食品类型" clearable style="width: 100%;">
						<el-option label="主食" value="主食" />
						<el-option label="汤品" value="汤品" />
						<el-option label="素食" value="素食" />
						<el-option label="荤食" value="荤食" />
						<el-option label="甜品" value="甜品" />
					</el-select>
				</el-form-item>
				<el-form-item label="价格" prop="price">
					<el-input-number v-model="dialog.item.price" :min="0" :precision="2" :step="0.1" 
						placeholder="请输入价格" style="width: 100%;" />
				</el-form-item>
				<el-form-item label="是否清真" prop="isHalal">
					<el-radio-group v-model="dialog.item.isHalal">
						<el-radio :label="0">否</el-radio>
						<el-radio :label="1">是</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="食品图片">
					<el-upload
						ref="uploadRef"
						class="upload-demo"
						:auto-upload="false"
						:on-change="handleFileChange"
						:file-list="fileList"
						accept="image/*"
						:limit="1">
						<el-button type="primary">
							<el-icon><Upload /></el-icon>
							选择图片
						</el-button>
						<template #tip>
							<div class="el-upload__tip">
								支持jpg/png格式，文件大小不超过5MB
							</div>
						</template>
					</el-upload>
					<!-- 显示当前图片 -->
					<div v-if="dialog.item.foodImg && !fileList.length" style="margin-top: 10px;">
						<el-image 
							:src="getImageUrl(dialog.item.foodImg)" 
							style="width: 100px; height: 100px; border-radius: 4px;"
							fit="cover">
						</el-image>
						<p style="margin: 5px 0; color: #666; font-size: 12px;">当前图片</p>
					</div>
				</el-form-item>
			</el-form>
			<template #footer>
				<span class="dialog-footer">
					<el-button type="primary" @click="save('itemForm')" :loading="saveLoading">保存</el-button>
					<el-button @click="cancel">取消</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>

<script>
import { listFood, addFood, updateFood, deleteFood, findFoodPage } from "@/api/foodApi.js";
import {
	Plus,
	Search,
	EditPen,
	Delete,
	Upload
} from '@element-plus/icons-vue';
import { ElMessage, ElMessageBox } from 'element-plus';

export default {
	components: {
		Plus,
		Search,
		EditPen,
		Delete,
		Upload
	},
	computed: {
		indexMethod() {
			return (this.page.currentPage - 1) * this.page.pageSize + 1;
		}
	},
	data() {
		return {
			// 模态框数据
			dialog: {
				dialogVisible: false,
				tops: "",
				item: {
					id: "",
					foodName: "",
					foodType: "",
					price: 0,
					isHalal: 0,
					foodImg: ""
				}
			},
			// 校验规则
			rules: {
				foodName: [{
					required: true,
					message: "请输入食品名称",
					trigger: "blur"
				}],
				foodType: [{
					required: true,
					message: "请选择食品类型",
					trigger: "change"
				}],
				price: [{
					required: true,
					message: "请输入价格",
					trigger: "blur"
				}],
				isHalal: [{
					required: true,
					message: "请选择是否清真",
					trigger: "change"
				}]
			},
			// 分页属性
			page: {
				total: 0,
				pageSize: 10,
				currentPage: 1,
				pageCount: 0
			},
			// 查询参数
			queryParams: {
				foodName: "",
				foodType: "",
				pageNum: 1,
				pageSize: 10
			},
			foodList: [],
			fileList: [],
			saveLoading: false
		};
	},
	mounted() {
		// 页面加载时查询所有食物
		this.getAllFoodList();
	},
	methods: {
		// 获取图片URL
		getImageUrl(imagePath) {
      console.log(imagePath)
			if (!imagePath) return '';
			// 如果已经是完整URL，直接返回
			if (imagePath.startsWith('http')) {
				return imagePath;
			}
			// 否则拼接MinIO服务器地址
			return `http://localhost:9999/yyzx/images/cache/${imagePath}`;
		},
		// 点击查询
		query() {
			this.page.currentPage = 1;
			this.queryParams.pageNum = 1;
			this.getAllFoodList();
		},
		// 页码改变事件
		handleCurrentChange(curPage) {
			this.page.currentPage = curPage;
			this.queryParams.pageNum = curPage;
			this.getAllFoodList();
		},
		// 每页条数改变事件
		handleSizeChange(size) {
			this.page.pageSize = size;
			this.queryParams.pageSize = size;
			this.page.currentPage = 1;
			this.queryParams.pageNum = 1;
			this.getAllFoodList();
		},
		// 点击修改
		edit(row) {
			this.dialog.tops = "修改食物";
			this.dialog.dialogVisible = true;
			this.$nextTick(() => {
				this.$refs.itemForm?.resetFields();
				this.dialog.item = {
					...row
				};
				this.fileList = [];
			});
		},
		// 点击添加
		addItem() {
			this.dialog.tops = "添加食物";
			this.dialog.dialogVisible = true;
			this.$nextTick(() => {
				if (this.$refs.itemForm) {
					this.$refs.itemForm.resetFields();
				}
				this.dialog.item = {
					id: "",
					foodName: "",
					foodType: "",
					price: 0,
					isHalal: 0,
					foodImg: ""
				};
				this.fileList = [];
			});
		},
		// 文件选择变化
		handleFileChange(file, fileList) {
			this.fileList = fileList;
		},
		handleClose(done) {
			this.dialog.dialogVisible = false;
			this.resetForm("itemForm");
			this.fileList = [];
			if (done) done();
		},
		cancel() {
			this.handleClose(() => {});
		},
		resetForm(formName) {
			if (this.$refs[formName]) {
				this.$refs[formName].resetFields();
			}
		},
		// 保存(新增/编辑)
		save(formName) {
			this.$refs[formName].validate(valid => {
				if (valid) {
					this.saveLoading = true;
					
					// 创建FormData对象
					const formData = new FormData();
					formData.append('foodName', this.dialog.item.foodName);
					formData.append('foodType', this.dialog.item.foodType);
					formData.append('price', this.dialog.item.price);
					formData.append('isHalal', this.dialog.item.isHalal);
					
					// 如果有选择新图片，添加到FormData
					if (this.fileList.length > 0) {
						formData.append('foodImage', this.fileList[0].raw);
					}
					
					if (!this.dialog.item.id) {
						// 新增
						addFood(formData).then(res => {
							if (res.flag) {
								ElMessage.success(res.message || '添加成功');
								this.query();
								this.handleClose();
							} else {
								ElMessage.error(res.message || '添加失败');
							}
						}).catch(error => {
							console.error('添加食物失败:', error);
							ElMessage.error('添加失败，请重试');
						}).finally(() => {
							this.saveLoading = false;
						});
					} else {
						// 编辑
						updateFood(this.dialog.item.id, formData).then(res => {
							if (res.flag) {
								ElMessage.success(res.message || '修改成功');
								this.query();
								this.handleClose();
							} else {
								ElMessage.error(res.message || '修改失败');
							}
						}).catch(error => {
							console.error('修改食物失败:', error);
							ElMessage.error('修改失败，请重试');
						}).finally(() => {
							this.saveLoading = false;
						});
					}
				} else {
					return false;
				}
			});
		},
		// 删除食物
		del(id) {
			ElMessageBox.confirm("此操作将删除该食物记录, 是否继续?", "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
				type: "warning"
			})
			.then(() => {
				deleteFood(id).then(res => {
					if (res.flag) {
						ElMessage.success(res.message || '删除成功');
						this.query();
					} else {
						ElMessage.error(res.message || '删除失败');
					}
				}).catch(error => {
					console.error('删除食物失败:', error);
					ElMessage.error('删除失败，请重试');
				});
			})
			.catch(() => {});
		},
		// 查询所有食物
		getAllFoodList() {
			this.queryParams.pageNum = this.page.currentPage;
			this.queryParams.pageSize = this.page.pageSize;

			findFoodPage(this.queryParams).then(res => {
				if (res.flag) {
					// 处理分页数据
					if (res.data && res.data.records) {
						this.foodList = res.data.records;
						this.page.total = res.data.total;
						this.page.pageSize = res.data.size;
						this.page.currentPage = res.data.current;
						this.page.pageCount = res.data.pages;
					} else {
						this.foodList = [];
						this.page.total = 0;
					}
				} else {
					ElMessage.error(res.message || '查询失败');
					this.foodList = [];
					this.page.total = 0;
				}
			}).catch(error => {
				console.error("查询食物列表失败:", error);
				ElMessage.error("查询数据失败，请稍后重试");
				this.foodList = [];
				this.page.total = 0;
			});
		}
	}
};
</script>

<style scoped>
/* 样式与用户管理界面保持一致 */
.common-layout {
	height: 100%;
	display: flex;
	flex-direction: column;
}

.el-container {
	height: 100%;
	display: flex;
	flex-direction: column;
}

.el-header {
	padding: 15px 20px;
	height: auto !important;
	background-color: #f8f9fa;
	box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
}

.header-container {
	width: 100%;
}

.search-form {
	width: 100%;
}

.search-row {
	width: 100%;
	display: flex;
	align-items: center;
}

.input-col {
	display: flex;
	align-items: center;
}

.btn-col {
	display: flex;
	align-items: center;
	padding: 0 5px !important;
}

.full-width {
	width: 100%;
}

.operation-btn {
	padding: 8px 16px;
	font-size: 13px;
	height: 40px;
	min-width: 90px;
	margin: 0 !important;
}

.el-main {
	padding: 15px;
	flex: 1;
	overflow: auto;
	background-color: #f5f7fa;
}

.el-button {
	border-radius: 4px;
	transition: all 0.3s ease;
	font-weight: 500;
}

.el-button:hover {
	transform: translateY(-2px);
	box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
}

.el-table {
	width: 100%;
	margin-top: 15px;
	border-radius: 4px;
	overflow: hidden;
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
}

.el-table th {
	background-color: #f5f7fa;
	color: #606266;
	font-weight: 500;
}

.el-table__row:hover>td {
	background-color: #ecf5ff !important;
}

.el-pagination {
	margin-top: 15px;
	text-align: right;
}

.el-dialog {
	border-radius: 8px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.demo-form-inline {
	padding: 10px 0;
}

.el-form-item {
	margin-bottom: 15px;
}

.el-form-item__label {
	font-weight: 500;
}

.upload-demo {
	width: 100%;
}

.el-upload__tip {
	color: #999;
	font-size: 12px;
	margin-top: 5px;
}

@media (max-width: 768px) {
	.search-row {
		flex-wrap: wrap;
	}

	.input-col,
	.btn-col {
		flex: 1 0 100%;
		margin-bottom: 10px;
		padding: 0 !important;
	}

	.el-table__header-wrapper,
	.el-table__body-wrapper {
		overflow-x: auto;
	}

	.el-dialog {
		width: 90% !important;
	}

	.operation-btn {
		padding: 6px 12px;
		font-size: 12px;
		height: 36px;
	}
}
</style>