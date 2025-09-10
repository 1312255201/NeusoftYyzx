package cn.gugufish.yyzx.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gugufish.yyzx.dto.MealDTO;
import cn.gugufish.yyzx.mapper.MealMapper;
import cn.gugufish.yyzx.pojo.Meal;
import cn.gugufish.yyzx.service.MealService;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.MealVo;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;


@Service
public class MealServiceImpl extends ServiceImpl<MealMapper, Meal> implements MealService {
    @Resource
    private MealMapper mealMapper;

    @Override
    public ResultVo<Page<MealVo>> listMealVoPage(MealDTO mealDTO) throws Exception {
        Page<MealVo> page = new Page<>(mealDTO.getPageNum(), mealDTO.getPageSize());
        mealMapper.selectMealVo(page, mealDTO.getWeekDay(), mealDTO.getMealType());
        return ResultVo.ok(page);
    }
}