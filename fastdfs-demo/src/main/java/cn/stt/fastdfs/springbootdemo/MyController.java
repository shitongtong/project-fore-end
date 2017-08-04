package cn.stt.fastdfs.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/3.
 */
@Controller
public class MyController {
    @Autowired
    private FastDFSClientWrapper dfsClient;

    // 上传图片
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 省略业务逻辑代码。。。
        String imgUrl = dfsClient.uploadFile(file);
        System.out.println("imgUrl=" + imgUrl);
        // 。。。。
        return imgUrl;

    }
}
