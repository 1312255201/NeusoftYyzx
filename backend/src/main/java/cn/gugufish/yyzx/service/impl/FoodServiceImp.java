package cn.gugufish.yyzx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gugufish.yyzx.mapper.FoodMapper;
import cn.gugufish.yyzx.pojo.Food;
import cn.gugufish.yyzx.service.FoodService;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImp extends ServiceImpl<FoodMapper, Food> implements FoodService {
}
