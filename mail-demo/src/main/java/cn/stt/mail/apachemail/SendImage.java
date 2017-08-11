package cn.stt.mail.apachemail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.net.MalformedURLException;
import java.net.URL;

import static cn.stt.mail.util.MailConfig.attachmentUrlPath;
import static cn.stt.mail.util.MailConfig.chartset;
import static cn.stt.mail.util.MailConfig.fromNickName;
import static cn.stt.mail.util.MailConfig.myEmailAccount;
import static cn.stt.mail.util.MailConfig.myEmailPassword;
import static cn.stt.mail.util.MailConfig.myEmailSMTPHost;
import static cn.stt.mail.util.MailConfig.receiveMailAccount;
import static cn.stt.mail.util.MailConfig.toNickName;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/11.
 */
public class SendImage {
    public static void main(String[] args) throws EmailException, MalformedURLException {

        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
//        attachment.setPath(attachmentLocalPath);
        attachment.setURL(new URL(attachmentUrlPath));
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Picture of John");
        attachment.setName("文档.pdf");

        // Create the email message
        HtmlEmail email = new HtmlEmail();
        email.setAuthenticator(new DefaultAuthenticator(myEmailAccount, myEmailPassword));
        email.setSSLOnConnect(true);
        email.setHostName(myEmailSMTPHost);
        email.setFrom(myEmailAccount, fromNickName,chartset);
        email.addTo(receiveMailAccount, toNickName,chartset);
        email.setSubject("Test email with inline image");

        // embed the image and get the content id
        URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
        String cid = email.embed(url, "Apache logo");

        // set the html message
        email.setHtmlMsg("<html>The apache logo - <img src=\"cid:"+cid+"\"></html>");

        // set the alternative message
        email.setTextMsg("Your email client does not support HTML messages");

        email.attach(attachment);

        // send the email
        email.send();
    }
}
