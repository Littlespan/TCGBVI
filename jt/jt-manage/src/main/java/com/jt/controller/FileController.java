package com.jt.controller;

import com.jt.service.FileService;
import com.jt.vo.ImageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/9/29 15:19
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@RestController
@RequestMapping("/pic/")
public class FileController {
    @Autowired
    private FileService fileService;

    @RequestMapping("upload")
    public ImageVo upload(MultipartFile uploadFile) {
        return fileService.upload(uploadFile);
    }

}
