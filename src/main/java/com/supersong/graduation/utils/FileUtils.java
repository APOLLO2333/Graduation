package com.supersong.graduation.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {

    @Value("${uploadpic.path}")
    private static  String uploadPicPath;

    public static void uploadFile(byte[] file, String fileName) throws Exception {
        if (uploadPicPath==null){
            uploadPicPath = "C:/graduation/img/";
        }
        File targetFile = new File(uploadPicPath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(uploadPicPath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static String storePic(MultipartFile file) throws Exception {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadPicPath + filename), // 这里指定了下载的位置

                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new Exception("失败！" + filename, e);
        }
        return filename;
    }

}