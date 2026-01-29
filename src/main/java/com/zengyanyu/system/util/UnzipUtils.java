package com.zengyanyu.system.util;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;

/**
 * 解压ZIP文件
 *
 * @author zengyanyu
 */
public class UnzipUtils {

    /**
     * 辅助方法：完整解压ZIP所有条目（文件正常时使用）
     *
     * @param zipFilePath ZIP文件路径
     * @param targetDir   解压的目标目录
     * @return
     */
    public static boolean unzipAll(String zipFilePath, String targetDir) {
        try (ZipFile zipFile = new ZipFile(new File(zipFilePath))) {
            Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
            Path targetPath = Paths.get(targetDir);
            while (entries.hasMoreElements()) {
                unzipSingleEntry(zipFile, entries.nextElement(), targetPath);
            }
            System.out.println("✅ 完整解压成功，目标目录：" + targetDir);
            return true;
        } catch (Exception e) {
            System.err.println("完整解压失败：" + e);
            return false;
        }
    }

    /**
     * 工具方法：解压单个ZIP条目（处理文件/目录，避免路径遍历漏洞）
     *
     * @param zipFile
     * @param entry
     * @param targetDir
     * @throws IOException
     */
    private static void unzipSingleEntry(ZipFile zipFile, ZipArchiveEntry entry, Path targetDir) throws IOException {
        // 防止路径遍历攻击（关键：确保解压后文件在目标目录内）
        Path entryPath = targetDir.resolve(entry.getName()).normalize();
        // 若为目录，创建多级目录
        if (entry.isDirectory()) {
            Files.createDirectories(entryPath);
            return;
        }
        // 若为文件，创建父目录并解压
        Files.createDirectories(entryPath.getParent());
        try (InputStream in = zipFile.getInputStream(entry)) {
            Files.copy(in, entryPath, StandardCopyOption.REPLACE_EXISTING);
        }
        System.out.println("✅ 解压成功：" + entry.getName());
    }

    public static void main(String[] args) {
        String zipFilePath = "E:\\ResultZIP\\S3A_OL_1_EFR____20260126T021301_20260126T021601_20260127T030317_0179_135_217_2520_PS1_O_NT_004.SEN3.zip";
        String targetDir = "E:\\ResultZIP";
        unzipAll(zipFilePath, targetDir);
    }
}
