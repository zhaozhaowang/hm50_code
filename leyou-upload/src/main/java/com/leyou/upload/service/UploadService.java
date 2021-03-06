package com.leyou.upload.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {

    //初始化常量的数组,作为检验文件类型的白名单集合
    private static final List<String> content_Types = Arrays.asList("image/gif", "image/jpeg");


    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    public String uploadImage(MultipartFile file) {
        //文件类型
        String originalFilename = file.getOriginalFilename();


        /**
         * 例如： Content-Type: text/html;charset:utf-8;

         常见的媒体格式类型如下：

         text/html ： HTML格式
         text/plain ：纯文本格式
         text/xml ：  XML格式
         image/gif ：gif图片格式
         image/jpeg ：jpg图片格式
         image/png：png图片格式
         以application开头的媒体格式类型：

         application/xhtml+xml ：XHTML格式
         application/xml     ： XML数据格式
         application/atom+xml  ：Atom XML聚合格式
         application/json    ： JSON数据格式
         application/pdf       ：pdf格式
         application/msword  ： Word文档格式
         application/octet-stream ： 二进制流数据（如常见的文件下载）
         */

        String contentType = file.getContentType();
        if (!content_Types.contains(contentType)) {
            LOGGER.info("文件类型不合法: {}", originalFilename);
            return null;
        }

        try {
            //校验文件内容
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null) {
                LOGGER.info("文件的内容不合法: {}", originalFilename);
                return null;
            }

            //保存到服务器
            file.transferTo(new File("/Users/mobiletestingdevice/git/hm50/allkindsofproblems/image/" + originalFilename));

            //返回URL地址进行回显
            return "http://image.leyou.com//" + originalFilename;
        } catch (IOException e) {
            LOGGER.info("服务器内部错误..." + originalFilename + "   " + e);
            e.printStackTrace();
        }
        return null;
    }
}
