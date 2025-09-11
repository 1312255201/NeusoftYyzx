package cn.gugufish.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.pojo.Meal;
import cn.gugufish.yyzx.pojo.vo.MealVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MealMapper extends BaseMapper<Meal> {
    
    @Select("<script>" +
            "SELECT " +
            "m.id, " +
            "m.week_day, " +
            "m.meal_type, " +
            "m.taste, " +
            "m.food_id, " +
            "f.food_name, " +
            "f.food_type, " +
            "f.price, " +
            "f.is_halal, " +
            "f.food_img " +
            "FROM " +
            "meal m " +
            "JOIN " +
            "food f ON (m.food_id = f.id) " +
            "<where>" +
            "m.is_deleted = 0 " +
            "<if test='week_day != null and week_day != \"\"'>" +
            "AND m.week_day = #{week_day}" +
            "</if>" +
            "<if test='meal_type != null and meal_type != \"\"'>" +
            "AND m.meal_type = #{meal_type}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<MealVo> selectMealVo(@Param("page") Page<MealVo> page, @Param("week_day") String weekDay, @Param("meal_type") Integer mealType);
}