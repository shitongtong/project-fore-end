package cn.stt.mail.springmail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import static cn.stt.mail.util.MailConfig.*;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/11.
 */
public class SendSimple {
    public static void main(String[] args) {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", "25000");
        senderImpl.setJavaMailProperties(prop);
        // 设定mail server
        senderImpl.setHost(myEmailSMTPHost);
        senderImpl.setUsername(myEmailAccount); // 根据自己的情况,设置username
        senderImpl.setPassword(myEmailPassword); // 根据自己的情况, 设置password

        // 建立邮件消息
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人 用数组发送多个邮件
        // String[] array = new String[] {"sun111@163.com","sun222@sohu.com"};
        // mailMessage.setTo(array);
        mailMessage.setFrom(myEmailAccount);
        mailMessage.setTo(receiveMailAccount);

        mailMessage.setSubject(" 测试spring简单文本邮件发送！ ");
        mailMessage.setText(" 测试我的spring简单邮件发送机制！！ ");


        // 发送邮件
        senderImpl.send(mailMessage);

        System.out.println(" 邮件发送成功.. ");
    }
}
