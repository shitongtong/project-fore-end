package cn.stt.mail.apachemail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import static cn.stt.mail.util.MailConfig.*;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/11.
 */
public class SendSimple {
    public static void main(String[] args) throws EmailException {
        Email email = new SimpleEmail();
        email.setHostName(myEmailSMTPHost);
//        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(myEmailAccount, myEmailPassword));
        email.setSSLOnConnect(true);
        email.setFrom(myEmailAccount, fromNickName, chartset);
        email.addTo(receiveMailAccount, toNickName, chartset);
        email.setCharset(chartset);
        email.setSubject("TestMail中文");
        email.setMsg("This is a test中文 mail ... :-)");
        email.send();
    }
}
