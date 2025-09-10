package cn.gugufish.yyzx.controller;

import cn.gugufish.yyzx.pojo.Food;
import cn.gugufish.yyzx.service.FoodService;
import cn.gugufish.yyzx.utils.ResultVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.List;

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
}
