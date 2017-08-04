package cn.stt.file.handle.image.watermark;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/4.
 */
public class ImageMarkLogoByIcon {

    public static void main(String[] args) {
        String srcImgPath = "D:\\图片\\阿里面试题.png";
        String iconPath = "D:\\图片\\爱符号_84.png";
        String targerPath = "D:\\图片\\img_mark_icon.png";
        String targerPath2 = "D:\\图片\\img_mark_icon_rotate.png";
        // 给图片添加水印
        ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath, targerPath);
        // 给图片添加水印,水印旋转-45
        ImageMarkLogoByIcon.markImageByIcon(iconPath, srcImgPath, targerPath2, -45);
    }

    /**
     * 给图片添加水印
     *
     * @param iconPath   水印图片路径
     * @param srcImgPath 源图片路径
     * @param targerPath 目标图片路径
     */
    public static void markImageByIcon(String iconPath, String srcImgPath, String targerPath) {
        markImageByIcon(iconPath, srcImgPath, targerPath, null);
    }

    /**
     * 给图片添加水印、可设置水印图片旋转角度
     *
     * @param iconPath   水印图片路径
     * @param srcImgPath 源图片路径
     * @param targerPath 目标图片路径
     * @param degree     水印图片旋转角度
     */
    public static void markImageByIcon(String iconPath, String srcImgPath,
                                       String targerPath, Integer degree) {
        OutputStream os = null;
        try {
            Image srcImg = ImageIO.read(new File(srcImgPath));
            int width = srcImg.getWidth(null);
            int height = srcImg.getHeight(null);
            System.out.println("原图宽高：" + width + "," + height);
            BufferedImage buffImg = new BufferedImage(width,
                    height, BufferedImage.TYPE_INT_RGB);
            // 得到画笔对象
            // Graphics g= buffImg.getGraphics();
            Graphics2D g = buffImg.createGraphics();
            // 设置对线段的锯齿状边缘处理
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g.drawImage(srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            if (null != degree) {
                // 设置水印旋转
                g.rotate(Math.toRadians(degree),
                        (double) buffImg.getWidth() / 2, (double) buffImg
                                .getHeight() / 2);
            }
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
            ImageIcon imgIcon = new ImageIcon(iconPath);
            // 得到Image对象。
            Image img = imgIcon.getImage();
            float alpha = 0.5f; // 透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                    alpha));
            // 表示水印图片的位置
            g.drawImage(img, 150, 300, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
            g.dispose();
            os = new FileOutputStream(targerPath);
            // 生成图片
            ImageIO.write(buffImg, "png", os);
            System.out.println("图片完成添加Icon印章。。。。。。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os)
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
