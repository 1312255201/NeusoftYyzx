package cn.gugufish.yyzx.service.impl;

import cn.gugufish.yyzx.mapper.ImageStoreMapper;
import cn.gugufish.yyzx.mapper.UserMapper;
import cn.gugufish.yyzx.pojo.StoreImage;
import cn.gugufish.yyzx.service.ImageService;
import cn.gugufish.yyzx.utils.Const;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.minio.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class ImageServiceImpl extends ServiceImpl<ImageStoreMapper, StoreImage> implements ImageService {
    @Resource
    MinioClient minioClient;
    private final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    @Override
    public String uploadImage(int id , MultipartFile file) throws IOException {
        String key = Const.YYZX_IMAGE_COUNTER + id;

        String imageName = UUID.randomUUID().toString().replace("-", "");
        Date date = new Date();
        imageName = "/cache/" + format.format(date) + "/" + imageName;
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket("yyzx")
                .stream(file.getInputStream(), file.getSize(), -1)
                .object(imageName)
                .build();
        try {
            minioClient.putObject(args);

            if(this.save(new StoreImage(id, imageName, date))) {
                return imageName;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("图片上传出现问题: "+ e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void fetchImageFromMinio(OutputStream stream, String image) throws Exception {
        GetObjectArgs args = GetObjectArgs.builder()
                .bucket("yyzx")
                .object(image)
                .build();
        GetObjectResponse response = minioClient.getObject(args);
        IOUtils.copy(response, stream);
    }

    @Override
    public boolean deleteImage(String imagePath) throws Exception {
        try {
            // 从MinIO删除图片文件
            RemoveObjectArgs args = RemoveObjectArgs.builder()
                    .bucket("yyzx")
                    .object(imagePath)
                    .build();
            minioClient.removeObject(args);
            
            // 从数据库删除图片记录
            this.remove(Wrappers.<StoreImage>lambdaQuery()
                    .eq(StoreImage::getName, imagePath));
            
            log.info("成功删除图片: {}", imagePath);
            return true;
        } catch (Exception e) {
            log.error("删除图片失败: {}, 错误: {}", imagePath, e.getMessage(), e);
            return false;
        }
    }
}
