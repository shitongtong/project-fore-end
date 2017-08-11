package cn.stt.mail.apachemail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import java.net.MalformedURLException;
import java.net.URL;

import static cn.stt.mail.util.MailConfig.*;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/11.
 */
public class SendAttachments {
    public static void main(String[] args) throws EmailException, MalformedURLException {
        // Create the attachment
        EmailAttachment attachment = new EmailAttachment();
//        attachment.setPath(attachmentLocalPath);
        attachment.setURL(new URL(attachmentUrlPath));
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Picture of John");
        attachment.setName("文档.pdf");

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setAuthenticator(new DefaultAuthenticator(myEmailAccount, myEmailPassword));
        email.setSSLOnConnect(true);
        email.setHostName(myEmailSMTPHost);
        email.setFrom(myEmailAccount, fromNickName,chartset);
        email.addTo(receiveMailAccount, toNickName,chartset);
        email.setSubject("The picture");
        email.setMsg("Here is the picture you wanted");

        // add the attachment
        email.attach(attachment);

        // send the email
        email.send();
    }
}
