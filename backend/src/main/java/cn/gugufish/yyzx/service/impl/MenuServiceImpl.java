package cn.gugufish.yyzx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gugufish.yyzx.mapper.MenuMapper;
import cn.gugufish.yyzx.pojo.Menu;
import cn.gugufish.yyzx.service.MenuService;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}