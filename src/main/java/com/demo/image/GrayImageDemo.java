package com.demo.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 将图设置为灰色的
 */
public class GrayImageDemo {

    public static void main(String[] args) throws IOException {
        //图片源文件
//        int width = 150, height = 150;
        String imgPath = "/data1/home/whaty/workspace/idea-local/Demo/out/yinxu.png";
        BufferedImage bufferedImage = ImageIO.read(new FileInputStream(imgPath));
//        Image img = ImageIO.read(new File(imgPath));
//        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        tag.getGraphics().drawImage(img, 0, 0, width, height, null);

        BufferedImage grayImage = getGrayImage(bufferedImage);

        //输出目录及文件
        File out = new File("/data1/home/whaty/workspace/idea-local/Demo/out/yinxu4.jpg");
        ImageIO.write(grayImage, "jpg", out);

    }


    /**
     * 将图片置灰 ---  使用现成的类
     *
     * @param originalImage
     * @return
     */
    public static BufferedImage getGrayImage(BufferedImage originalImage) {
        int imageWidth = originalImage.getWidth();
        int imageHeight = originalImage.getHeight();
        //TYPE_3BYTE_BGR -->  表示一个具有 8 位 RGB 颜色分量的图像，对应于 Windows 风格的 BGR 颜色模型，具有用 3 字节存储的 Blue、Green 和 Red 三种颜色。
        BufferedImage grayImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_3BYTE_BGR);
        ColorConvertOp cco = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        cco.filter(originalImage, grayImage);
        return grayImage;
    }

}
