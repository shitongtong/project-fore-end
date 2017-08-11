package cn.stt.mail.springmail;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

import static cn.stt.mail.util.MailConfig.attachmentLocalPath;
import static cn.stt.mail.util.MailConfig.myEmailAccount;
import static cn.stt.mail.util.MailConfig.myEmailPassword;
import static cn.stt.mail.util.MailConfig.myEmailSMTPHost;
import static cn.stt.mail.util.MailConfig.receiveMailAccount;

/**
 * 发送包含附件的邮件
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/11.
 */
public class SendAttached {
    public static void main(String[] args) throws MessagingException {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", "25000");
        senderImpl.setJavaMailProperties(prop);
        // 设定mail server
        senderImpl.setHost(myEmailSMTPHost);
        senderImpl.setUsername(myEmailAccount); // 根据自己的情况,设置username
        senderImpl.setPassword(myEmailPassword); // 根据自己的情况, 设置password

        // 建立邮件消息,发送简单邮件和html邮件的区别
        MimeMessage mailMessage = senderImpl.createMimeMessage();
        // 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
        // multipart模式 为true时发送附件 可以设置html格式
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8");

        // 设置收件人，寄件人
        messageHelper.setTo(receiveMailAccount);
        messageHelper.setFrom(myEmailAccount);
        messageHelper.setSubject("测试邮件中上传附件!！");
        // true 表示启动HTML格式的邮件
        messageHelper.setText(
                "<html><head></head><body><h1>你好：附件中有学习资料！</h1></body></html>",
                true);

        FileSystemResource file = new FileSystemResource(
                new File(attachmentLocalPath));
        // 这里的方法调用和插入图片是不同的。
        messageHelper.addAttachment("文档.doc", file);

        // 发送邮件
        senderImpl.send(mailMessage);

        System.out.println("邮件发送成功..");
    }
}
