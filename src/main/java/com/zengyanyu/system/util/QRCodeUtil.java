package com.zengyanyu.system.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * 二维码工具类
 */
public class QRCodeUtil {

    private QRCodeUtil(){
    }

    /**
     * 生成二维码图片
     *
     * @param text     文本
     * @param width    宽度
     * @param height   高度
     * @param filePath 文件路径
     */
    public static void generateQRCodeImage(String text, int width, int height, String filePath) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 确保字符编码正确处理中文等特殊字符
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成二维码图片
     *
     * @param fileName 可以是任何文本，包括中文字符
     * @return 返回二维码的文件地址和文件名称
     */
    public static String generateQRCodeImage(String codeType, String fileName, String filePath) {
        String temp = "https://iot.cloud.tencent.com/iotexplorer/device?page=";
        String str = "";
        if ("adddevice".equals(codeType) || "softap".equals(codeType) || "preview".equals(codeType)) {
            str = temp + codeType + "&productId=" + fileName;
        }

        // 配置扫一扫产品介绍
        if ("adddevice_preview".equals(codeType)) {
            str = temp + "adddevice&productId=" + fileName + "&preview=1";
        }

        // 二维码的宽度和高度
        int width = 300;
        int height = 300;
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            //判断目录是否存在，不存在则直接创建
            fileDir.mkdirs();
        }
        // 输出文件的路径
        String targetDir = filePath + "/" + codeType + "-" + fileName + ".png";
        try {
            generateQRCodeImage(str, width, height, targetDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetDir;
    }

    /**
     * 解析二维码
     *
     * @param filePath 文件路径
     * @return
     */
    public static String decodeQRCode(String filePath) {
        Path path = FileSystems.getDefault().getPath(filePath);
        Result result = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(path.toFile());
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            result = new MultiFormatReader().decode(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.getText();
    }

}
