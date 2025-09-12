package cn.gugufish.yyzx.controller;

import cn.gugufish.yyzx.service.ImageService;
import cn.gugufish.yyzx.utils.Const;
import cn.gugufish.yyzx.utils.ResultVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/api/image")
public class ImageController {

    /**
     * 图片服务类
     * 提供图片上传、处理、存储等核心业务逻辑
     */
    @Resource
    ImageService imageService;

    /**
     * 上传临时图片文件
     * 主要用于论坛帖子、评论等内容中的图片上传
     * 上传的图片会存储到MinIO并返回访问URL
     *
     * 限制条件：
     * - 文件大小不超过5MB
     * - 必须是有效的图片格式
     *
     * @param id 当前登录用户的ID（从JWT令牌中获取）
     * @param file 要上传的图片文件
     * @param response HTTP响应对象，用于设置错误状态码
     * @return 上传结果，成功返回图片访问URL，失败返回错误信息
     * @throws Exception 文件处理过程中可能出现的异常
     */
    @PostMapping("cache")
    public ResultVo<String> uploadImage(@RequestAttribute(Const.ATTR_USER_ID) int id,
                                        @RequestParam("file") MultipartFile file,
                                        HttpServletResponse response) throws Exception{
        // 检查文件大小限制（5MB）
        if(file.getSize() > 1024 * 1024 * 5)
            return ResultVo.fail( "图像大小最大不能大于5MB");

        log.info("用户{}正在进行图片上传操作", id);
        String url = imageService.uploadImage(id, file);

        if(url != null){
            log.info("用户{}图片上传成功，大小：{}", id, file.getSize());
            return ResultVo.ok(url);
        } else {
            response.setStatus(400);
            return ResultVo.fail("图片上传失败，请联系管理员！");
        }
    }

}
