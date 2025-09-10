package cn.gugufish.yyzx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.dto.MealDTO;
import cn.gugufish.yyzx.pojo.Meal;
import cn.gugufish.yyzx.service.MealService;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.MealVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/meal")
@CrossOrigin
@Tag(name = "膳食日历管理")
public class MealController {

    @Resource
    private MealService mealService;

    @PostMapping("/addMeal")
    @Operation(summary = "添加膳食")
    public ResultVo addMeal(@RequestBody Meal meal) throws Exception {
        mealService.save(meal);
        return ResultVo.ok("添加膳食");
    }

    @Operation(summary = "更新膳食")
    @PostMapping("/updateMeal")
    public ResultVo updateMeal(@RequestBody Meal meal) throws Exception {
        mealService.updateById(meal);
        return ResultVo.ok("更新膳食");
    }

    @Operation(summary = "删除膳食")
    @GetMapping("/removeMeal")
    public ResultVo removeMeal(Integer id) throws Exception {
        mealService.removeById(id);
        return ResultVo.ok("删除膳食");
    }

    @PostMapping("/listMealPage")
    @Operation(summary = "膳食查询（分页）/可以根据星期查询，根据膳食类型（早餐/午餐/晚餐）")
    public ResultVo<Page<MealVo>> listMealPage(@RequestBody MealDTO mealDTO) throws Exception {
        return mealService.listMealVoPage(mealDTO);
    }
}
