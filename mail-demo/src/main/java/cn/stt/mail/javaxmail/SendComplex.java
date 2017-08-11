package cn.stt.mail.javaxmail;

import cn.stt.mail.util.MailConfig;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.Date;
import java.util.Properties;

/**
 * 创建并发送一封包含文本、图片、附件的复杂邮件
 * 注：附件为doc文件时会额外有个tcmime.xxxxx.xxxxx.xxxxx.bin附件
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/10.
 */
public class SendComplex {
    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    public static String myEmailAccount = MailConfig.myEmailAccount;//767035687@qq.com
    public static String myEmailPassword = MailConfig.myEmailPassword;

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com   //163整天报SMTPSendFailedException: 554 DT:SPM 163 恶心死，所以换qq试试(可以)
    //163邮箱 发送附件会有一个tcmime.47824.47931.80630.bin这样的附件
    public static String myEmailSMTPHost = MailConfig.myEmailSMTPHost;

    // 收件人邮箱（替换为自己知道的有效邮箱）
    public static String receiveMailAccount = MailConfig.receiveMailAccount;//tong_tong_0909@163.com

    public static void main(String[] args) throws Exception {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        // 开启 SSL 连接, 以及更详细的发送步骤请看上一篇: 基于 JavaMail 的 Java 邮件发送：简单邮件发送

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
/*

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmailAccount, myEmailPassword);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session session = Session.getInstance(props, authenticator);
*/

        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);

        // 也可以保持到本地查看
        // message.writeTo(file_out_put_stream);

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器
        //    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
        transport.connect(myEmailAccount, myEmailPassword);

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();

//        Transport.send(message);

        System.out.println("发送成功！！！");
    }

    /**
     * 创建一封复杂邮件（文本+图片+附件）
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. 创建邮件对象
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "xx发件人", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "xx用户", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("这是使用javaMail测试邮件发送（文本+图片+附件）", "UTF-8");

        /*
         * 下面是邮件内容的创建:
         */

        // 5. 创建图片“节点”
        MimeBodyPart image = new MimeBodyPart();

        File imgFile = new File(MailConfig.iamgeLocalPath);
        DataHandler dh = new DataHandler(new FileDataSource(imgFile)); // 读取本地文件

        /*URL url = new URL("http://api.onlyeduhi.cn/uploadPath/courseware/bc8e0cc27c41499da9cc7d3efd07d1c6/image/1501841305921_568224304/8.jpg");
        dh = new DataHandler(new URLDataSource(url));*/

        image.setDataHandler(dh);                   // 将图片数据添加到“节点”
        String cid = "image_fairy_tail";    //图片id
        image.setContentID("image_fairy_tail");     // 为“节点”设置一个唯一编号（在文本“节点”将引用该ID）

        // 6. 创建文本“节点”
        MimeBodyPart text = new MimeBodyPart();
        //    这里添加图片的方式是将整个图片包含到邮件内容中, 实际上也可以以 http 链接的形式添加网络图片
        text.setContent("这是一张测试邮件包含<br/>图片：<br/><img src='cid:image_fairy_tail'/>", "text/html;charset=UTF-8");

        // 7. （文本+图片）设置 文本 和 图片 “节点”的关系（将 文本 和 图片 “节点”合成一个混合“节点”）
        MimeMultipart mm_text_image = new MimeMultipart();
        mm_text_image.addBodyPart(text);
        mm_text_image.addBodyPart(image);
        mm_text_image.setSubType("related");    // 关联关系

        // 8. 将 文本+图片 的混合“节点”封装成一个普通“节点”
        //    最终添加到邮件的 Content 是由多个 BodyPart 组成的 Multipart, 所以我们需要的是 BodyPart,
        //    上面的 mm_text_image 并非 BodyPart, 所有要把 mm_text_image 封装成一个 BodyPart
        MimeBodyPart text_image = new MimeBodyPart();
        text_image.setContent(mm_text_image);

        // 9. 创建附件“节点”
        MimeBodyPart attachment = new MimeBodyPart();
        File attachmentFile = new File(MailConfig.attachmentLocalPath);
        DataHandler dh2 = new DataHandler(new FileDataSource(attachmentFile));  // 读取本地文件
        String fileName = dh2.getName();

        /*URL url1 = new URL("http://api.onlyeduhi.cn/uploadPath/courseware/bc8e0cc27c41499da9cc7d3efd07d1c6/original/1502336600028_-1147910762.pdf");
        DataHandler dh2 = new DataHandler(new URLDataSource(url1)); //网络文件 不成功？！！！是因为设置的文件名不对
        String name = dh2.getName();
        String fileName = name.substring(name.lastIndexOf("/"));*/

        attachment.setDataHandler(dh2); // 将附件数据添加到“节点”
        attachment.setFileName(MimeUtility.encodeText(fileName));              // 设置附件的文件名（需要编码）

//        attachment.attachFile(attachmentFile);
//        attachment.setFileName(MimeUtility.encodeText(attachmentFile.getName()));

        // 10. 设置（文本+图片）和 附件 的关系（合成一个大的混合“节点” / Multipart ）
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text_image);
        mm.addBodyPart(attachment);     // 如果有多个附件，可以创建多个多次添加
        mm.setSubType("mixed");         // 混合关系

        // 11. 设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
        message.setContent(mm);

        // 12. 设置发件时间
        message.setSentDate(new Date());

        // 13. 保存上面的所有设置
        message.saveChanges();

        return message;
    }
}
