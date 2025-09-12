package cn.gugufish.yyzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.gugufish.yyzx.pojo.Food;
import cn.gugufish.yyzx.pojo.dto.AddFoodRequest;
import cn.gugufish.yyzx.utils.ResultVo;
import org.springframework.web.multipart.MultipartFile;

public interface FoodService extends IService<Food> {
    /**
     * 添加食物
     * @param addFoodRequest 食物信息
     * @param foodImage 食物图片文件
     * @param userId 当前用户ID
     * @return 添加结果
     * @throws Exception 处理异常
     */
    ResultVo<Food> addFood(AddFoodRequest addFoodRequest, MultipartFile foodImage, Integer userId) throws Exception;
    
    /**
     * 更新食物信息
     * @param id 食物ID
     * @param addFoodRequest 食物信息
     * @param foodImage 食物图片文件（可选）
     * @param userId 当前用户ID
     * @return 更新结果
     * @throws Exception 处理异常
     */
    ResultVo<Food> updateFood(Integer id, AddFoodRequest addFoodRequest, MultipartFile foodImage, Integer userId) throws Exception;
    
    /**
     * 删除食物
     * @param id 食物ID
     * @param userId 当前用户ID
     * @return 删除结果
     * @throws Exception 处理异常
     */
    ResultVo<String> deleteFood(Integer id, Integer userId) throws Exception;
}
