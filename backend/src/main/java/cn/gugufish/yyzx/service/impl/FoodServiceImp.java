package cn.gugufish.yyzx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gugufish.yyzx.mapper.FoodMapper;
import cn.gugufish.yyzx.pojo.Food;
import cn.gugufish.yyzx.pojo.dto.AddFoodRequest;
import cn.gugufish.yyzx.service.FoodService;
import cn.gugufish.yyzx.service.ImageService;
import cn.gugufish.yyzx.utils.ResultVo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class FoodServiceImp extends ServiceImpl<FoodMapper, Food> implements FoodService {
    
    @Resource
    private ImageService imageService;
    
    @Override
    public ResultVo<Food> addFood(AddFoodRequest addFoodRequest, MultipartFile foodImage, Integer userId) throws Exception {
        try {
            // 创建Food对象
            Food food = new Food();
            BeanUtils.copyProperties(addFoodRequest, food);
            
            // 处理图片上传
            if (foodImage != null && !foodImage.isEmpty()) {
                // 检查文件大小限制（5MB）
                if (foodImage.getSize() > 1024 * 1024 * 5) {
                    return ResultVo.fail("图片大小不能超过5MB");
                }
                
                log.info("用户{}正在上传食物图片，文件大小：{}", userId, foodImage.getSize());
                
                // 上传图片到MinIO
                String imageUrl = imageService.uploadImage(userId, foodImage);
                if (imageUrl != null) {
                    // 从完整URL中提取文件名作为图片路径
                    int lastSlashIndex = imageUrl.lastIndexOf("/");
                    int secondLastSlashIndex = imageUrl.lastIndexOf("/", lastSlashIndex - 1);
                    if (secondLastSlashIndex != -1) {
                        String result = imageUrl.substring(secondLastSlashIndex + 1);
                        food.setFoodImg(result);
                        log.info("食物图片上传成功，路径：{}", result);
                    }
                    else {
                        return ResultVo.fail("图片上传失败");
                    }
                } else {
                    log.error("食物图片上传失败");
                    return ResultVo.fail("图片上传失败，请重试");
                }
            }
            
            // 保存食物信息到数据库
            boolean saveResult = save(food);
            if (saveResult) {
                log.info("食物添加成功，ID：{}, 名称：{}", food.getId(), food.getFoodName());
                return ResultVo.ok(food);
            } else {
                log.error("食物保存到数据库失败");
                return ResultVo.fail("食物添加失败，请重试");
            }
            
        } catch (Exception e) {
            log.error("添加食物时发生异常：{}", e.getMessage(), e);
            return ResultVo.fail("添加食物失败：" + e.getMessage());
        }
    }
    
    @Override
    public ResultVo<Food> updateFood(Integer id, AddFoodRequest addFoodRequest, MultipartFile foodImage, Integer userId) throws Exception {
        // 检查食物是否存在
        Food existingFood = this.getById(id);
        if (existingFood == null) {
            return ResultVo.fail("食物不存在");
        }
        
        // 更新食物信息
        existingFood.setFoodName(addFoodRequest.getFoodName());
        existingFood.setFoodType(addFoodRequest.getFoodType());
        existingFood.setPrice(addFoodRequest.getPrice());
        existingFood.setIsHalal(addFoodRequest.getIsHalal());
        
        // 如果有新图片，先删除旧图片再上传新图片
        if (foodImage != null && !foodImage.isEmpty()) {
            // 如果原来有图片，先删除旧图片
            if (existingFood.getFoodImg() != null && !existingFood.getFoodImg().isEmpty()) {
                try {
                    String oldImagePath = "/cache/" + existingFood.getFoodImg();
                    boolean oldImageDeleted = imageService.deleteImage(oldImagePath);
                    if (oldImageDeleted) {
                        log.info("成功删除旧的食物图片: {}", oldImagePath);
                    } else {
                        log.warn("删除旧的食物图片失败: {}", oldImagePath);
                    }
                } catch (Exception e) {
                    log.error("删除旧图片时发生异常: {}", e.getMessage(), e);
                    // 继续上传新图片，即使旧图片删除失败
                }
            }
            
            // 上传新图片
            try {
                String imageUrl = imageService.uploadImage(userId, foodImage);
                if (imageUrl != null) {
                    int lastSlashIndex = imageUrl.lastIndexOf("/");
                    int secondLastSlashIndex = imageUrl.lastIndexOf("/", lastSlashIndex - 1);
                    if (secondLastSlashIndex != -1) {
                        String result = imageUrl.substring(secondLastSlashIndex + 1);
                        existingFood.setFoodImg(result);
                    }
                    else {
                        return ResultVo.fail("图片上传失败");
                    }
                } else {
                    return ResultVo.fail("图片上传失败");
                }
            } catch (Exception e) {
                log.error("图片上传失败: {}", e.getMessage());
                return ResultVo.fail("图片上传失败: " + e.getMessage());
            }
        }
        
        // 保存更新后的食物信息
        boolean updated = this.updateById(existingFood);
        if (!updated) {
            return ResultVo.fail("更新食物信息失败");
        }
        
        log.info("用户{}成功更新食物信息，食物ID: {}", userId, id);
        return ResultVo.ok(existingFood);
    }
    
    @Override
    public ResultVo<String> deleteFood(Integer id, Integer userId) throws Exception {
        // 检查食物是否存在
        Food existingFood = this.getById(id);
        if (existingFood == null) {
            return ResultVo.fail("食物不存在");
        }
        
        // 如果食物有图片，先删除图片
        if (existingFood.getFoodImg() != null && !existingFood.getFoodImg().isEmpty()) {
            try {
                // 构建完整的图片路径
                String fullImagePath = "/cache/" + existingFood.getFoodImg();
                boolean imageDeleted = imageService.deleteImage(fullImagePath);
                if (imageDeleted) {
                    log.info("成功删除食物图片: {}", fullImagePath);
                } else {
                    log.warn("删除食物图片失败，但继续删除食物记录: {}", fullImagePath);
                }
            } catch (Exception e) {
                log.error("删除食物图片时发生异常: {}", e.getMessage(), e);
                // 即使图片删除失败，也继续删除食物记录
            }
        }
        
        // 删除食物记录
        boolean deleted = this.removeById(id);
        if (!deleted) {
            return ResultVo.fail("删除食物失败");
        }
        
        log.info("用户{}成功删除食物，食物ID: {}", userId, id);
        return ResultVo.ok("删除成功");
    }
}
