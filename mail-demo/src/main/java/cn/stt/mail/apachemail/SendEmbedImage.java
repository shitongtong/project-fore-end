package cn.stt.mail.apachemail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;

import java.net.MalformedURLException;
import java.net.URL;

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
public class SendEmbedImage {
    public static void main(String[] args) throws EmailException, MalformedURLException {
        // load your HTML email template
        String htmlEmailTemplate = ".... <img src=\"http://www.apache.org/images/feather.gif\"> ....";

        // define you base URL to resolve relative resource locations
        URL url = new URL("http://www.apache.org");

        // create the email message
        ImageHtmlEmail email = new ImageHtmlEmail();
        email.setDataSourceResolver(new DataSourceUrlResolver(url));
        email.setAuthenticator(new DefaultAuthenticator(myEmailAccount, myEmailPassword));
        email.setSSLOnConnect(true);
        email.setHostName(myEmailSMTPHost);
        email.setFrom(myEmailAccount, fromNickName,chartset);
        email.addTo(receiveMailAccount, toNickName,chartset);
        email.setSubject("Test email with inline image");

        // set the html message
        email.setHtmlMsg(htmlEmailTemplate);

        // set the alternative message
        email.setTextMsg("Your email client does not support HTML messages");

        // send the email
        email.send();
    }
}
