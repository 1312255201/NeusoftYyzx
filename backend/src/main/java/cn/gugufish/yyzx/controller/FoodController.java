package cn.gugufish.yyzx.controller;

import cn.gugufish.yyzx.pojo.Food;
import cn.gugufish.yyzx.pojo.dto.AddFoodRequest;
import cn.gugufish.yyzx.service.FoodService;
import cn.gugufish.yyzx.utils.Const;
import cn.gugufish.yyzx.utils.ResultVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@Slf4j
@RestController
@RequestMapping("/food")
@CrossOrigin
@Tag(name = "食品管理")
public class FoodController {
    @Resource
    private FoodService foodService;

    @GetMapping("/listFood")
    @Operation(summary = "查询所有食品列表")
    public ResultVo<List<Food>> listFood() throws Exception {
        return ResultVo.ok(foodService.list());
    }
    
    /**
     * 分页查询食物列表
     * 支持按食物名称和食物类型进行搜索
     */
    @GetMapping("/findFoodPage")
    @Operation(summary = "分页查询食物列表")
    public ResultVo<Page<Food>> findFoodPage(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "foodName", required = false) String foodName,
            @RequestParam(value = "foodType", required = false) String foodType) {
        try {
            // 创建分页对象
            Page<Food> page = new Page<>(pageNum, pageSize);
            
            // 创建查询条件
            QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
            
            // 添加搜索条件
            if (foodName != null && !foodName.trim().isEmpty()) {
                queryWrapper.like("food_name", foodName.trim());
            }
            if (foodType != null && !foodType.trim().isEmpty()) {
                queryWrapper.eq("food_type", foodType.trim());
            }
            
            // 执行分页查询
            Page<Food> result = foodService.page(page, queryWrapper);
            
            log.info("分页查询食物列表，页码: {}, 每页大小: {}, 食物名称: {}, 食物类型: {}, 总记录数: {}", 
                    pageNum, pageSize, foodName, foodType, result.getTotal());
            
            return ResultVo.ok(result);
        } catch (Exception e) {
            log.error("分页查询食物列表失败: {}", e.getMessage());
            return ResultVo.fail("查询失败: " + e.getMessage());
        }
    }
    
    /**
     * 添加食物
     * 支持上传食物图片到MinIO并保存食物信息到数据库
     * 
     * @param userId 当前登录用户ID（从JWT令牌中获取）
     * @param foodName 食品名称
     * @param foodType 食品类型
     * @param price 价格
     * @param isHalal 是否清真（0：否，1：是）
     * @param foodImage 食物图片文件（可选）
     * @param response HTTP响应对象
     * @return 添加结果
     * @throws Exception 处理异常
     */
    @PostMapping("/addFood")
    @Operation(summary = "添加食物")
    public ResultVo<Food> addFood(@RequestAttribute(Const.ATTR_USER_ID) Integer userId,
                                  @RequestParam("foodName") String foodName,
                                  @RequestParam("foodType") String foodType,
                                  @RequestParam("price") Double price,
                                  @RequestParam("isHalal") Integer isHalal,
                                  @RequestParam(value = "foodImage", required = false) MultipartFile foodImage,
                                  HttpServletResponse response) throws Exception {
        
        // 创建请求对象
        AddFoodRequest addFoodRequest = new AddFoodRequest();
        addFoodRequest.setFoodName(foodName);
        addFoodRequest.setFoodType(foodType);
        addFoodRequest.setPrice(price);
        addFoodRequest.setIsHalal(isHalal);
        
        // 基本参数验证
        if (foodName == null || foodName.trim().isEmpty()) {
            response.setStatus(400);
            return ResultVo.fail("食品名称不能为空");
        }
        if (foodType == null || foodType.trim().isEmpty()) {
            response.setStatus(400);
            return ResultVo.fail("食品类型不能为空");
        }
        if (price == null || price <= 0) {
            response.setStatus(400);
            return ResultVo.fail("价格必须大于0");
        }
        if (isHalal == null || (isHalal != 0 && isHalal != 1)) {
            response.setStatus(400);
            return ResultVo.fail("是否清真参数错误（0：否，1：是）");
        }
        
        log.info("用户{}正在添加食物：{}", userId, foodName);
        
        // 调用服务层添加食物
        ResultVo<Food> result = foodService.addFood(addFoodRequest, foodImage, userId);
        
        if (!result.flag()) {
            response.setStatus(400);
        }
        
        return result;
    }
    
    /**
     * 更新食物信息
     */
    @PostMapping("/updateFood/{id}")
    @Operation(summary = "更新食物信息")
    public ResultVo<Food> updateFood(
            @PathVariable Integer id,
            @Valid @ModelAttribute AddFoodRequest addFoodRequest,
            @RequestParam(value = "foodImage", required = false) MultipartFile foodImage,
            HttpServletRequest request) {
        try {
            // 从请求中获取用户ID
            Integer userId = (Integer) request.getAttribute("userId");
            if (userId == null) {
                return ResultVo.fail("用户未登录");
            }
            
            log.info("用户{}请求更新食物信息，食物ID: {}, 食物名称: {}", userId, id, addFoodRequest.getFoodName());
            
            return foodService.updateFood(id, addFoodRequest, foodImage, userId);
        } catch (Exception e) {
            log.error("更新食物失败: {}", e.getMessage());
            return ResultVo.fail("更新食物失败: " + e.getMessage());
        }
    }
    
    /**
     * 删除食物
     */
    @PostMapping("/deleteFood/{id}")
    @Operation(summary = "删除食物")
    public ResultVo<String> deleteFood(
            @PathVariable Integer id,
            HttpServletRequest request) {
        try {
            // 从请求中获取用户ID
            Integer userId = (Integer) request.getAttribute("userId");
            if (userId == null) {
                return ResultVo.fail("用户未登录");
            }
            
            log.info("用户{}请求删除食物，食物ID: {}", userId, id);
            
            return foodService.deleteFood(id, userId);
        } catch (Exception e) {
            log.error("删除食物失败: {}", e.getMessage());
            return ResultVo.fail("删除食物失败: " + e.getMessage());
        }
    }
}
