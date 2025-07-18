package com.zengyanyu.system.controller;

import com.zengyanyu.system.commons.ResponseData;
import com.zengyanyu.system.config.LogRecord;
import com.zengyanyu.system.dto.FileDto;
import com.zengyanyu.system.util.FileUtils;
import com.zengyanyu.system.util.IOUtils;
import com.zengyanyu.system.util.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Api(tags = "文件控制器")
@RestController
public class FileController extends BaseController {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.uploadH5Folder}")
    private String uploadH5Folder;

    private static final long ZIP_MAX_FILE_SIZE = 2 * 1024 * 1024; // 2MB
    private static final String baseURL = "https://holaapi.openhola.com";

    /**
     * 文件上传
     *
     * @param files 文件集合
     * @return 文件上传的响应信息
     */
    @LogRecord("文件上传")
    @ApiOperation("文件上传")
    @PostMapping("/uploadImage")
    public ResponseData<List<FileDto>> fileUpload(@RequestParam("file") List<MultipartFile> files) {
        // 创建对象，设置属性
        List<FileDto> fileDtoList = new ArrayList<>();
        for (MultipartFile file : files) {
            // 单文件上传
            ResponseData<FileDto> upload = FileUtils.upload(file, uploadFolder);
            FileDto fileDto = upload.getData();
            // 将数据添加到集合中
            fileDtoList.add(fileDto);
        }
        return new ResponseData("上传文件成功", fileDtoList);
    }

    /**
     * wangEditor文件上传返回格式，需要JSONObject格式
     *
     * @param file
     * @return
     */
    @LogRecord("文件上传wangEditor")
    @ApiOperation("文件上传wangEditor")
    @PostMapping("/uploadImageWangEditor")
    public JSONObject uploadImageWangEditor(@RequestParam("file") MultipartFile file) {
        // 单文件上传
        ResponseData<FileDto> upload = FileUtils.upload(file, uploadFolder);
        FileDto fileDto = upload.getData();
        if ("/usr/local/upload/".equals(uploadFolder)) {
            fileDto.setFileName(baseURL + "/preview/" + fileDto.getFileName());
        } else {
            fileDto.setFileName("http://localhost:" + request.getServerPort() + "/preview/" + fileDto.getFileName());
        }
        /*
         * {
         *     errno:0,
         *     data:{
         *         url:fileName
         *     }
         * }
         */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("errno", 0);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("url", fileDto.getFileName());
        jsonObject.put("data", jsonObject1);
        return jsonObject;
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名称
     * @return
     * @throws MalformedURLException
     */
    @LogRecord("文件下载")
    @ApiOperation("文件下载")
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws MalformedURLException {
        Path filePath = Paths.get(uploadFolder).resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
                .body(resource);
    }

    /**
     * 文件预览
     *
     * @param fileName 文件名称
     * @return
     * @throws IOException
     */
    @ApiOperation("文件预览")
    @GetMapping("/preview/{fileName}")
    public ResponseEntity<ByteArrayResource> previewFile(@PathVariable String fileName) throws IOException {
        File file = new File(uploadFolder + fileName);

        String contentType = FilenameUtils.getExtension(fileName);
        // 预览
        HttpHeaders httpHeaders = new HttpHeaders();
        if ("pdf".equalsIgnoreCase(contentType)) {
            httpHeaders.add("Content-Type", MediaType.APPLICATION_PDF_VALUE);
        } else if ("jpg".equalsIgnoreCase(contentType) || "jpeg".equalsIgnoreCase(contentType)
                || "png".equalsIgnoreCase(contentType)) {
            httpHeaders.add("Content-Type", MediaType.IMAGE_JPEG_VALUE);
        }
        // 获取文件流
        InputStream inputStream = new FileInputStream(uploadFolder + fileName);
        return new ResponseEntity<>(IOUtils.inputStreamToByteArrayResource(inputStream), httpHeaders, HttpStatus.OK);
    }

    /**
     * @param file 上传的zip文件文件
     * @throws IOException
     */
    @LogRecord("上传ZIP文件并解压到磁盘")
    @ApiOperation("上传ZIP文件并解压到磁盘")
    @PostMapping("/uploadZipAndUnzip/{productId}")
    public ResponseData<List<FileDto>> uploadZipAndUnzip(@RequestParam("file") MultipartFile file,
                                                         @PathVariable String productId) {
        File fileDir = new File(uploadH5Folder + productId + "/");
        // 文件校验
        fileValidate(fileDir);

        // 检查文件类型是否为ZIP
        if (!isZipFile(file)) {
            return new ResponseData("400", "上传的文件类型不是zip文件。请重新上传");
        }

        // 检查文件大小是否超过5MB
        if (file.getSize() > ZIP_MAX_FILE_SIZE) {
            return new ResponseData("400", "上传的ZIP文件大小，不能超过2M。请重新上传");
        }

        // 目标路径
        Path destPath = Paths.get(fileDir.getPath());
        try (InputStream is = new BufferedInputStream(file.getInputStream());
             ZipInputStream zis = new ZipInputStream(is)) {

            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                Path outputPath = destPath.resolve(entry.getName());

                // 安全检查，防止路径遍历攻击
                if (!outputPath.normalize().startsWith(destPath)) {
                    throw new IOException("Bad zip entry");
                }

                if (entry.isDirectory()) {
                    Files.createDirectories(outputPath);
                } else {
                    Files.createDirectories(outputPath.getParent());
                    Files.copy(zis, outputPath, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        } catch (IOException e) {
            return new ResponseData(ResponseData.ERROR_CODE, "上传zip文件并解压到磁盘-异常" + e.getMessage());
        }

        // 迭代文件目录和移动文件目录
        iterationDirectory(fileDir);

        List<FileDto> fileDtoList = handlerFileInfo(file);
        return new ResponseData("上传zip文件并解压到磁盘成功", fileDtoList);
    }

    /**
     * 文件校验
     *
     * @param fileDir
     */
    private void fileValidate(File fileDir) {
        if (!fileDir.exists()) {
            //判断目录是否存在，不存在则直接创建
            fileDir.mkdirs();
        } else {
            // 删除目录和目录下的文件
            deleteDir(fileDir);
        }
    }

    /**
     * 迭代文件目录和移动文件目录
     *
     * @param fileDir
     */
    private void iterationDirectory(File fileDir) {
        // 遍历文件，判断是否有苹果系统相关的配置_MAXOSX
        File[] files = fileDir.listFiles();
        String path = "";
        // 判断是否移动文件目录
        Boolean isTransFileDir = false;
        for (File file1 : files) {
            if (file1.getPath().endsWith("_MACOSX")) {
                isTransFileDir = true;
                // _MACOSX目录下的文件，是不需要的，删除掉当前目录的所有文件
                deleteDir(new File(file1.getPath()));
            } else {
                path = file1.getPath();
            }
        }
        // 移动文件目录
        if (isTransFileDir) {
            try {
                moveDirectory(Paths.get(path), Paths.get(path).getParent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理文件信息
     *
     * @param file 文件对象
     * @return
     */
    private List<FileDto> handlerFileInfo(MultipartFile file) {
        // 创建对象，设置属性
        List<FileDto> fileDtoList = new ArrayList<>();
        // 使用UUID生成唯一标识文件名
        String randomNum = UUIDUtils.generateUUID();
        // 获取文件的原始名
        String originalFilename = file.getOriginalFilename();
        // 获取文件后缀
        String extension = FilenameUtils.getExtension(originalFilename);
        // 生成新的文件名
        String fileName = randomNum + "." + extension;
        // 创建对象，设置属性
        FileDto fileDto = new FileDto(fileName, originalFilename);
        // 将数据添加到集合中
        fileDtoList.add(fileDto);
        return fileDtoList;
    }

    /**
     * 移动目录
     *
     * @param source 资源路径
     * @param target 目标路径
     * @throws IOException
     */
    private static void moveDirectory(Path source, Path target) throws IOException {
        // 使用Files.walkFileTree遍历源目录
        Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                // 在目标目录创建对应的子目录
                Path targetSubDir = target.resolve(source.relativize(dir));
                Files.createDirectories(targetSubDir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                // 移动文件到目标目录
                Path targetFile = target.resolve(source.relativize(file));
                // 标准拷贝方式:替换存在的
                Files.move(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });

        // 删除源目录（如果需要）
//        Files.deleteIfExists(source);
    }

    /**
     * 删除目录和目录下的文件
     *
     * @param dirFile
     */
    private void deleteDir(File dirFile) {
        File[] allContents = dirFile.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDir(file);
            }
        }
        dirFile.delete();
    }

    /**
     * 判断是否为zip文件
     *
     * @param file
     * @return
     */
    private boolean isZipFile(MultipartFile file) {
        // 获取文件的MIME类型
        String contentType = file.getContentType();
        // 检查MIME类型是否为application/zip
        if ("application/zip".equals(contentType) || "application/x-zip-compressed".equals(contentType)) {
            return true;
        }

        // 如果MIME类型检查失败，可以进一步检查文件扩展名
        String fileName = file.getOriginalFilename();
        if (fileName != null && fileName.endsWith(".zip")) {
            return true;
        }
        return false;
    }

}
