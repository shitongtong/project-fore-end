package cn.stt.file.handle.pdf.watermark;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 增加文字水印功能
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/4.
 */
public class TestWaterPrint2 {
    public static void main(String[] args) throws DocumentException, IOException {
        //原pdf文件
        String srcPath = "D:\\testoffice2pdf\\水印\\Java基础PPT.pdf";
        // 要输出的pdf文件
        String outPath = "D:\\testoffice2pdf\\水印\\water_out2.pdf";

        // 待加水印的文件
        PdfReader reader = new PdfReader(srcPath);
        // 加完水印的文件
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outPath));

        int total = reader.getNumberOfPages() + 1;

        PdfContentByte content;
        // 设置字体
        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        // 水印文字
        String waterText = "水印文字！";
        int j = waterText.length(); // 文字长度
        char c = 0;
        int high = 0;// 高度
        // 循环对每页插入水印
        for (int i = 1; i < total; i++) {
            // 水印的起始
            high = 500;
            content = stamper.getUnderContent(i);
            // 开始
            content.beginText();
            // 设置颜色
            content.setColorFill(Color.GRAY);
            // 设置字体及字号
            content.setFontAndSize(base, 50);
            // 设置起始位置
            content.setTextMatrix(70, 200);
            //后三位参数含义：距离左侧宽度、底部高度、旋转角度
            content.showTextAligned(Element.ALIGN_CENTER, "公司内部文件，请注意保密！", 300, 350, 0);
            content.showTextAligned(Element.ALIGN_CENTER, "公司内部文件，请注意保密！", 400, 250, 0);
            // 开始写入水印
            /*for (int k = 0; k < j; k++) {
                content.setTextRise(14);
                c = waterText.charAt(k);
                // 将char转成字符串
                content.showText(c + "");
                high -= 5;
            }*/
            content.endText();

        }
        stamper.close();
    }
}
