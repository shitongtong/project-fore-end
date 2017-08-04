package cn.stt.fastdfs.springbootdemo;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/3.
 */
@Import(FdfsClientConfig.class)
@SpringBootApplication
public class FdfsApplication {
    public static void main(String[] args) {
        SpringApplication.run(FdfsApplication.class, args);
    }
}
