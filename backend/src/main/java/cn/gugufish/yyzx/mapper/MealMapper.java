package cn.gugufish.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.pojo.Meal;
import cn.gugufish.yyzx.vo.MealVo;
import org.apache.ibatis.annotations.Param;

public interface MealMapper extends BaseMapper<Meal> {
    Page<MealVo> selectMealVo(@Param("page") Page<MealVo> page, @Param("week_day") String weekDay, @Param("meal_type") Integer mealType);
}