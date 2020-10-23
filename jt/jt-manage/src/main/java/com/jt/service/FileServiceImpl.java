package com.jt.service;

import com.jt.vo.ImageVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/9/29 16:16
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Service
@PropertySource("classpath:/properties/images.properties")
public class FileServiceImpl implements FileService {
    private static Set<String> imageType;
    @Value("${image.dirPath}")
    private String dirPath;
    @Value("${image.urlPath}")
    private String urlPath;

    static{
        imageType = new HashSet<>();
        imageType.add(".jpg");
        imageType.add(".png");
        imageType.add(".gif");
    }

    @Override
    public ImageVo upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        fileType = fileType.toLowerCase();
        if(!imageType.contains(fileType)){
            return ImageVo.fail();
        }
        int width;
        int height;
        try {
            BufferedImage  bufferedImage = ImageIO.read(file.getInputStream());
            width = bufferedImage.getWidth();
            height = bufferedImage.getHeight();
            if(width <= 0 || height <= 0){
                return ImageVo.fail();
            }


            String dateDir = new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
            String fileDirPath = dirPath+dateDir;
            File dirFile = new File(fileDirPath);
            if(!dirFile.exists()){
                dirFile.mkdirs();
            }
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String realFileName = uuid + fileType;
            String realFilePath = fileDirPath+realFileName;
            file.transferTo(new File(realFilePath));
            String virtualPath = urlPath+"\\"+dateDir+realFileName;
            return ImageVo.success(virtualPath,width ,height);

        } catch (IOException e) {
            e.printStackTrace();
            return ImageVo.fail();
        }


    }
}
