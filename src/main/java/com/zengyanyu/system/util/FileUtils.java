package com.zengyanyu.system.util;

import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.dto.FileDto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * 文件操作工具类
 */
public class FileUtils {

    private FileUtils() {
    }

    /**
     * 文件上传
     *
     * @param file         上传的文件
     * @param uploadFolder 文件保存的路径
     * @return
     */
    public static ResponseData upload(MultipartFile file, String uploadFolder) {
        // 获取文件的原始名
        String oldFilename = file.getOriginalFilename();
        // 获取文件后缀 .pdf
        String extension = "." + FilenameUtils.getExtension(oldFilename);
        // 生成新的文件名
        String fileName = UUIDUtils.generateUUID() + extension;

        // 创建对象，设置属性
        FileDto fileDto = new FileDto(fileName, oldFilename);

        File destinationDir = new File(uploadFolder);
        if (!destinationDir.exists()) {
            destinationDir.mkdirs();
        }
        try {
            file.transferTo(new File(destinationDir, fileName));
        } catch (IOException e) {
            return new ResponseData(ResponseData.ERROR_CODE, "文件上传失败", e.getMessage());
        }
        return new ResponseData("文件上传成功", fileDto);
    }

    /**
     * 下载在线文件到本地
     *
     * @param imageUrl        在线文件
     * @param destinationFile 本地目录+文件名称
     * @throws IOException
     */
    public static void downloadImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        // 自动关闭资源
        try (BufferedInputStream bis = new BufferedInputStream(url.openStream());
             FileOutputStream fos = new FileOutputStream(destinationFile)) {
            byte[] buffer = new byte[8192];
            int count = 0;
            while ((count = bis.read(buffer, 0, 8192)) != -1) {
                fos.write(buffer, 0, count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
