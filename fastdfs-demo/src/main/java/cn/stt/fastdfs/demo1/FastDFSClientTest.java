package cn.stt.fastdfs.demo1;

import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/3.
 */
public class FastDFSClientTest {
    /**
     * 文件上传测试
     */
    @Test
    public void testUpload() {
        File file = new File("D:\\图片\\Tulips.jpg");
        Map<String,String> metaList = new HashMap<String, String>();
        metaList.put("width","1024");
        metaList.put("height","768");
        metaList.put("author","杨信");
        metaList.put("date","20161018");
        String fid = FastDFSClient.uploadFile(file,file.getName(),metaList);
        System.out.println("upload local file " + file.getPath() + " ok, fileid=" + fid);
        //上传成功返回的文件ID：
        //group1/M00/00/00/wKgB_FmC49yAP95vAAl5WLU-YRY474.jpg
        //group1/M00/00/00/wKgB_FmC5LCAeIn1AAl5WLU-YRY264.jpg
        //group1/M00/00/00/wKgB_FmC6EWARSF8AAl5WLU-YRY606.jpg
    }

    /**
     * 文件下载测试
     */
    @Test
    public void testDownload() {
        int r = FastDFSClient.downloadFile("group1/M00/00/00/wKgB_FmC49yAP95vAAl5WLU-YRY474.jpg", new File("DownloadFile_fid.jpg"));
        System.out.println(r == 0 ? "下载成功" : "下载失败");
    }

    /**
     * 获取文件元数据测试
     */
    @Test
    public void testGetFileMetadata() {
        Map<String,String> metaList = FastDFSClient.getFileMetadata("group1/M00/00/00/wKgB_FmC5LCAeIn1AAl5WLU-YRY264.jpg");
        for (Iterator<Map.Entry<String,String>> iterator = metaList.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String,String> entry = iterator.next();
            String name = entry.getKey();
            String value = entry.getValue();
            System.out.println(name + " = " + value );
        }
    }

    /**
     * 文件删除测试
     */
    @Test
    public void testDelete() {
        int r = FastDFSClient.deleteFile("group1/M00/00/00/wKgB_FmC5LCAeIn1AAl5WLU-YRY264.jpg");
        System.out.println(r == 0 ? "删除成功" : "删除失败");
    }
}
