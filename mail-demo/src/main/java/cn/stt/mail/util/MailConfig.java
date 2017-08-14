package cn.stt.mail.util;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/11.
 */
public class MailConfig {

    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    public static String myEmailAccount = "xxxxxxxx@qq.com";
    public static String myEmailPassword = "xxxxx";

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com   //163整天报SMTPSendFailedException: 554 DT:SPM 163 恶心死，所以换qq试试(可以)
    //163邮箱 发送附件会有一个tcmime.47824.47931.80630.bin这样的附件
    public static String myEmailSMTPHost = "smtp.qq.com";

    // 收件人邮箱（替换为自己知道的有效邮箱）
    public static String receiveMailAccount = "xxxxxxxxx@qq.com";
    /**
     * 发件人昵称
     */
    public static String fromNickName = "xx发件人";
    /**
     * 收件人昵称
     */
    public static String toNickName = "xx收件人";
    /**
     * 编码格式
     */
    public static String chartset = "UTF-8";
    /**
     * 附件本地路径
     */
    public static String attachmentLocalPath = "D:\\图片\\文档.doc";
    /**
     * 附件网络路径
     */
    public static String attachmentUrlPath = "http://api.onlyeduhi.cn/uploadPath/courseware/bc8e0cc27c41499da9cc7d3efd07d1c6/original/1502336600028_-1147910762.pdf";
    /**
     * 图片本地路径
     */
    public static String iamgeLocalPath = "D:\\图片\\小幺鸡.png";
    /**
     * 图片网络路径
     */
    public static String iamgeUrlPath = "http://api.onlyeduhi.cn/uploadPath/courseware/bc8e0cc27c41499da9cc7d3efd07d1c6/image/1501841305921_568224304/8.jpg";

}
