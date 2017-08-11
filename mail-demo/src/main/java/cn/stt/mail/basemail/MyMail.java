package cn.stt.mail.basemail;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/11.
 */
public class MyMail {
    String from;
    String to;
    String subject;
    String content;
    String userName;
    String pwd;

    public MyMail(String from, String to, String subject, String content, String userName, String pwd) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.userName = this.toBASE64(userName);
        this.pwd = this.toBASE64(pwd);
    }

    /**
     * 在 MyMail 类中进行用户名、密码的转码工作
     */
    private String toBASE64(String str) {
        return (new sun.misc.BASE64Encoder().encode(str.getBytes()));
    }
}
