//package cn.stt.mail;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//import javax.mail.internet.MimeUtility;
//import java.io.File;
//import java.util.Properties;
//
///**
// * 创建并发送一封包含文本、图片、附件的复杂邮件
// *
// * @Author shitongtong
// * <p>
// * Created by shitongtong on 2017/8/10.
// */
//public class SendComplex2 {
//    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
//    public static String myEmailAccount = "xxxxxxx@qq.com";
//    public static String myEmailPassword = "xxxxxx";
//
//    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般格式为: smtp.xxx.com
//    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com   //163整天报SMTPSendFailedException: 554 DT:SPM 163 恶心死，所以换qq试试
//    public static String myEmailSMTPHost = "smtp.qq.com";
//
//    // 收件人邮箱（替换为自己知道的有效邮箱）
//    public static String receiveMailAccount = "xxxxxxxxx@163.com";
//
//    public static void main(String[] args) throws Exception {
//        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
//        Properties props = new Properties();                    // 参数配置
//        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
//        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
//        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
//
//        // 开启 SSL 连接, 以及更详细的发送步骤请看上一篇: 基于 JavaMail 的 Java 邮件发送：简单邮件发送
//
//        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
//        Session session = Session.getDefaultInstance(props);
//        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
//
//        // 3. 创建一封邮件
//        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);
//
//        // 也可以保持到本地查看
//        // message.writeTo(file_out_put_stream);
//
//        // 4. 根据 Session 获取邮件传输对象
//        Transport transport = session.getTransport();
//
//        // 5. 使用 邮箱账号 和 密码 连接邮件服务器
//        //    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
//        transport.connect(myEmailAccount, myEmailPassword);
//
//        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
//        transport.sendMessage(message, message.getAllRecipients());
//
//        // 7. 关闭连接
//        transport.close();
//
//        System.out.println("发送成功！！！");
//    }
//
//    /**
//     * 创建一封复杂邮件（文本+图片+附件）
//     */
//    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
//        // 1. 创建邮件对象
//        MimeMessage message = new MimeMessage(session);
//
//        // 2. From: 发件人
//        message.setFrom(new InternetAddress(sendMail, "tt", "UTF-8"));
//
//        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
//        message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "rr", "UTF-8"));
//
//        // 4. Subject: 邮件主题
//        message.setSubject("这是使用javaMail测试邮件发送（文本+图片+附件）", "UTF-8");
//
//        /*
//         * 下面是邮件内容的创建:
//         */
//
//        //整封邮件的MINE消息体
//        MimeMultipart msgMultipart = new MimeMultipart("mixed");//混合的组合关系
//        //设置邮件的MINE消息体
//        message.setContent(msgMultipart);
//
//        //附件1
//        MimeBodyPart attch1 = new MimeBodyPart();
//        //正文内容
//        MimeBodyPart content = new MimeBodyPart();
//
//        //把内容，附件1，附件2加入到 MINE消息体中
//        msgMultipart.addBodyPart(attch1);
//        msgMultipart.addBodyPart(content);
//
//        //把文件，添加到附件中
//        //数据源
//        DataSource ds1 = new FileDataSource(new File("D:\\图片\\文档.doc"));
//        //数据处理器
//        DataHandler dh1 = new DataHandler(ds1 );
//        //设置第一个附件的数据
//        attch1.setDataHandler(dh1);
//        //设置第一个附件的文件名
//        attch1.setFileName(MimeUtility.encodeText( "文档.doc"));
//
//
//        //正文（图片和文字部分）
//        MimeMultipart bodyMultipart  = new MimeMultipart("related");
//        //设置内容为正文
//        content.setContent(bodyMultipart);
//
//        //html代码部分
//        MimeBodyPart htmlPart = new MimeBodyPart();
//        //html中嵌套的图片部分
//        MimeBodyPart imgPart = new MimeBodyPart();
//
//        //正文添加图片和html代码
//        bodyMultipart.addBodyPart(htmlPart);
//        bodyMultipart.addBodyPart(imgPart);
//
//        //把文件，添加到图片中
//        DataSource imgds = new FileDataSource(new File("D:\\图片\\小幺鸡3.png"));
//        DataHandler imgdh = new DataHandler(imgds );
//        imgPart.setDataHandler(imgdh);
//        //说明html中的img标签的src，引用的是此图片
////        imgPart.setHeader("Content-Location", "http://sunteam.cc/logo.jsg");
//        imgPart.setContentID("image_fairy_tail");
//
//        //html代码
//        htmlPart.setContent("<span style='color:red'>中文呵呵</span><img src=\"image_fairy_tail\">","text/html;charset=utf-8");
//
//        //生成文件邮件
//        message.saveChanges();
//
//        return message;
//    }
//}
