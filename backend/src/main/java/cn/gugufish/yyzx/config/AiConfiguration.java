package cn.gugufish.yyzx.config;

import cn.gugufish.yyzx.service.FoodService;
import cn.gugufish.yyzx.service.RoomService;
import cn.gugufish.yyzx.service.MealService;
import cn.gugufish.yyzx.pojo.dto.MealDTO;
import cn.gugufish.yyzx.pojo.vo.MealVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.pojo.vo.CwsyBedVo;
import cn.gugufish.yyzx.pojo.Food;
import cn.gugufish.yyzx.utils.ResultVo;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.Resource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class AiConfiguration {
    
    @Resource
    private RoomService roomService;

    @Resource
    private MealService mealService;
    
    @Bean
    public ChatClient chatClient(OllamaChatModel chatModel) {
        return ChatClient.builder(chatModel).
                defaultSystem(buildSystemPrompt()).
                defaultAdvisors(new SimpleLoggerAdvisor()).
                build();
    }
    
    /**
     * 构建包含实时信息的系统提示词
     */
    private String buildSystemPrompt() {
        StringBuilder prompt = new StringBuilder();
        prompt.append("你是东软颐养中心的智能助手，需要帮助老人回答问题。\n\n");

        // 添加当前日期和星期信息
        String dateInfo = getCurrentDateInfo();
        prompt.append("当前时间信息：\n").append(dateInfo).append("\n\n");

        // 添加床位统计信息
        String bedInfo = getBedStatistics();
        prompt.append("床位统计信息：\n").append(bedInfo).append("\n\n");

        // 添加膳食信息
        String mealInfo = getTodayMealInfo();
        prompt.append("今日膳食信息：\n").append(mealInfo).append("\n\n");

        prompt.append("请根据以上实时信息为老人提供准确、贴心的服务。");

        return prompt.toString();
    }

    /**
     * 获取当前日期和星期信息
     */
    private String getCurrentDateInfo() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String[] weekDays = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
        String weekDay = weekDays[today.getDayOfWeek().getValue() - 1];

        return String.format("今天是%s，%s", today.format(formatter), weekDay);
    }

    /**
     * 获取床位统计信息
     */
    private String getBedStatistics() {
        try {
            ResultVo<CwsyBedVo> result = roomService.findCwsyBedVo("一楼");
            if (result.flag()  && result.data() != null) {
                CwsyBedVo bedVo = result.data();
                return String.format("总床位数：%d张，空闲床位：%d张，有人床位：%d张，外出床位：%d张",
                    bedVo.getZcw(), bedVo.getKx(), bedVo.getYr(), bedVo.getWc());
            }
        } catch (Exception e) {
            // 如果获取失败，返回默认信息
        }
        return "床位信息暂时无法获取";
    }

    /**
     * 获取今日膳食信息
     */
    private String getTodayMealInfo() {
        try {
            // 获取今天是星期几
            LocalDate today = LocalDate.now();
            String[] weekDays = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
            String todayWeekDay = weekDays[today.getDayOfWeek().getValue() - 1];

            StringBuilder mealInfo = new StringBuilder();
            mealInfo.append("今日膳食安排：\n");

            // 查询早餐、午餐、晚餐
            String[] mealTypes = {"早餐", "午餐", "晚餐"};
            for (int mealType = 1; mealType <= 3; mealType++) {
                MealDTO mealDTO = new MealDTO();
                mealDTO.setWeekDay(todayWeekDay);
                mealDTO.setMealType(mealType);
                mealDTO.setPageNum(1);
                mealDTO.setPageSize(10);

                ResultVo<Page<MealVo>> result = mealService.listMealVoPage(mealDTO);
                if (result.flag() && result.data() != null && !result.data().getRecords().isEmpty()) {
                    mealInfo.append(mealTypes[mealType - 1]).append("：");
                    List<MealVo> meals = result.data().getRecords();
                    for (int i = 0; i < meals.size(); i++) {
                        MealVo meal = meals.get(i);
                        mealInfo.append(meal.getFoodName());
                        if (meal.getTaste() != null && !meal.getTaste().isEmpty()) {
                            mealInfo.append("(").append(meal.getTaste()).append(")");
                        }
                        if (i < meals.size() - 1) {
                            mealInfo.append("、");
                        }
                    }
                    mealInfo.append("\n");
                }
            }

            return mealInfo.toString();
        } catch (Exception e) {
            // 如果获取失败，返回默认信息
        }
        return "膳食信息暂时无法获取";
    }
}
