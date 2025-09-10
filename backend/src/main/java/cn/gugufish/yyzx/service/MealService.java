package cn.gugufish.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.gugufish.yyzx.dto.MealDTO;
import cn.gugufish.yyzx.pojo.Meal;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.MealVo;

public interface MealService extends IService<Meal> {
    ResultVo<Page<MealVo>> listMealVoPage(MealDTO mealDTO) throws Exception;
}