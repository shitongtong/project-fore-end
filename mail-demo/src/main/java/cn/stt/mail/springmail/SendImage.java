package cn.stt.mail.springmail;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

import static cn.stt.mail.util.MailConfig.iamgeLocalPath;
import static cn.stt.mail.util.MailConfig.myEmailAccount;
import static cn.stt.mail.util.MailConfig.myEmailPassword;
import static cn.stt.mail.util.MailConfig.myEmailSMTPHost;
import static cn.stt.mail.util.MailConfig.receiveMailAccount;

/**
 * 发送嵌套图片的邮件
 * Email允许添加附件，也允许在multipart信件中内嵌资源。内嵌资源可能是你在信件中希望使用的图像，或者样式表，
 * 但是又不想把它们作为附件。
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/11.
 */
public class SendImage {
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
        // multipart模式
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);

        // 设置收件人，寄件人
        messageHelper.setTo(receiveMailAccount);
        messageHelper.setFrom(myEmailAccount);
        messageHelper.setSubject("测试邮件中嵌套图片!！");
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head><body><h1>hello!!spring image html mail</h1>");
        sb.append("<img src='cid:aaa'/>");
        sb.append("</body></html>");
        // true 表示启动HTML格式的邮件
        /*messageHelper.setText(
                "<html><head></head><body><h1>hello!!spring image html mail</h1>"
                        + "<img src=\"cid:aaa\"/></body></html>", true);*/
        messageHelper.setText(sb.toString(), true);

        FileSystemResource img = new FileSystemResource(new File(iamgeLocalPath));

        messageHelper.addInline("aaa", img);

        // 发送邮件
        senderImpl.send(mailMessage);

        System.out.println("邮件发送成功..");
    }
}
