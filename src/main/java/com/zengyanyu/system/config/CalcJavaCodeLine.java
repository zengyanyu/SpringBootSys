package com.zengyanyu.system.config;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 计算指定磁盘目录下所有.java文件的代码行数，并将文件内容输出到指定文件
 * 优化点：资源自动关闭、无静态变量、高效遍历、规范异常、消除硬编码等
 *
 * @author zengyanyu
 */
public class CalcJavaCodeLine {

    // 配置常量：消除硬编码，修改此处即可适配不同场景
    private static final String SOURCE_ROOT_DIR = "D:/Projects/sz-data-processing/src/main/java/cn/piesat";
    private static final String OUTPUT_DIR = "D:/sourceCode";
    private static final String OUTPUT_FILE_NAME = "java_output.txt";
    private static final String FILE_SUFFIX = ".java";

    // 总行数：局部变量，线程安全
    private static int totalLineNum = 0;

    public static void main(String[] args) {
        System.out.println("===== 开始统计Java文件代码行数 =====");
        // 1. 初始化输出目录
        File outputDir = new File(OUTPUT_DIR);
        // mkdirs()创建多级目录，mkdir()仅单级
        if (!outputDir.exists() && !outputDir.mkdirs()) {
            throw new RuntimeException("输出目录创建失败：" + OUTPUT_DIR);
        }
        File outputFile = new File(outputDir, OUTPUT_FILE_NAME);

        // 2. 遍历所有Java文件
        File sourceRoot = new File(SOURCE_ROOT_DIR);
        List<File> javaFileList = listAllJavaFiles(sourceRoot);
        if (javaFileList.isEmpty()) {
            System.out.println("未找到任何" + FILE_SUFFIX + "文件");
            return;
        }
        System.out.println("共找到" + javaFileList.size() + "个" + FILE_SUFFIX + "文件，开始逐文件统计...");

        // 3. 重定向System.out到输出文件
        try (FileOutputStream fos = new FileOutputStream(outputFile, true);
             PrintStream filePrintStream = new PrintStream(fos, true, StandardCharsets.UTF_8.name());
             PrintStream originalOut = System.out) {

            // 重定向标准输出
            System.setOut(filePrintStream);
            // 4. 逐文件统计行数并输出内容
            for (File file : javaFileList) {
                calcSingleFileLine(file);
            }
            // 输出总行数到文件
//                System.out.println("\n===== 统计结果 =====");
//                System.out.println("Java文件总行数：" + totalLineNum);

            // 恢复标准输出到控制台
            System.setOut(originalOut);
            System.out.println("===== 统计完成 =====");
            System.out.println("共统计" + javaFileList.size() + "个Java文件，总行数：" + totalLineNum);
            System.out.println("文件内容已输出至：" + outputFile.getAbsolutePath());
        } catch (Exception e) {
            System.err.println("程序执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 递归遍历指定目录下所有的Java文件（单次遍历，高效IO）
     *
     * @param dir 待遍历目录
     * @return 所有Java文件的List集合
     */
    private static List<File> listAllJavaFiles(File dir) {
        List<File> javaFileList = new ArrayList<>();
        // 入参校验：非空、是目录
        if (dir == null || !dir.exists() || !dir.isDirectory()) {
            System.err.println("无效的目录：" + (dir == null ? "null" : dir.getAbsolutePath()));
            return javaFileList;
        }

        // 单次调用listFiles()，获取所有文件/子目录，避免重复IO
        File[] fileArray = dir.listFiles();
        // 无权限/非目录时返回null，直接返回空集合
        if (fileArray == null) {
            System.err.println("无法读取目录内容（无权限/不是目录）：" + dir.getAbsolutePath());
            return javaFileList;
        }

        for (File file : fileArray) {
            if (file.isFile()) {
                if (file.getName().endsWith(FILE_SUFFIX)) {
                    javaFileList.add(file);
                }
            } else if (file.isDirectory()) {
                // 是目录：递归遍历子目录
                javaFileList.addAll(listAllJavaFiles(file));
            }
        }
        return javaFileList;
    }

    /**
     * 统计单个Java文件的行数，并将文件内容输出到重定向后的流（控制台/文件）
     * 使用try-with-resources自动关闭流，局部捕获异常，不影响整体程序
     *
     * @param file 待统计的Java文件
     */
    private static void calcSingleFileLine(File file) {
        // 入参校验
        if (file == null || !file.exists() || !file.isFile()) {
            System.err.println("无效的文件，跳过统计：" + (file == null ? "null" : file.getAbsolutePath()));
            return;
        }

        int singleFileLine = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                singleFileLine++;
                totalLineNum++;
            }
            // 打印文件分隔符，便于查看
//            System.out.println("=====================================");
//            System.out.println("文件：" + file.getName() + "，行数：" + singleFileLine);
//            System.out.println("=====================================\n");
        } catch (IOException e) {
            // 局部捕获异常，记录日志并继续执行，不终止整体统计
            System.err.println("读取文件失败，跳过该文件：" + file.getAbsolutePath() + "，异常：" + e.getMessage());
        }
    }

}