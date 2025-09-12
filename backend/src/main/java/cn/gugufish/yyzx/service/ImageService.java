package cn.gugufish.yyzx.service;

import cn.gugufish.yyzx.pojo.StoreImage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

public interface ImageService extends IService<StoreImage> {
    String uploadImage(int id,MultipartFile file) throws Exception;
    void fetchImageFromMinio(OutputStream stream, String image) throws Exception;
    boolean deleteImage(String imagePath) throws Exception;
}
