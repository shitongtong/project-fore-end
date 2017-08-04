package cn.stt.fastdfs.demo1;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * FastDFS文件上传下载工具类
 *
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/3.
 */
public class FastDFSClient {
    private static final String CONFIG_FILENAME = "src/main/resources/fdfs_client.conf";

    private static StorageClient1 storageClient1 = null;

    private static StorageClient storageClient = null;

    // 初始化FastDFS Client
    static {
        try {
            ClientGlobal.init(CONFIG_FILENAME);
            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            TrackerServer trackerServer = trackerClient.getConnection();
            if (trackerServer == null) {
                throw new IllegalStateException("getConnection return null");
            }

            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            if (storageServer == null) {
                throw new IllegalStateException("getStoreStorage return null");
            }

            storageClient1 = new StorageClient1(trackerServer, storageServer);
            storageClient = new StorageClient(trackerServer, storageServer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     * @param file     文件对象
     * @param fileName 文件名
     * @return
     */
    public static String uploadFile(File file, String fileName) {
        return uploadFile(file, fileName, null);
    }

    /**
     * 上传文件
     *
     * @param file     文件对象
     * @param fileName 文件名
     * @param metaList 文件元数据
     * @return
     */
    public static String uploadFile(File file, String fileName, Map<String, String> metaList) {
        try {
            byte[] buff1 = IOUtils.toByteArray(new FileInputStream(file));
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(buff)) != -1) {
                output.write(buff, 0, len);
            }
            /*long count;
            int n;
            for(count = 0L; -1 != (n = fileInputStream.read(buff)); count += (long)n) {
                output.write(buff, 0, n);
            }*/
            NameValuePair[] nameValuePairs = null;
            if (metaList != null) {
                nameValuePairs = new NameValuePair[metaList.size()];
                int index = 0;
                for (Iterator<Map.Entry<String, String>> iterator = metaList.entrySet().iterator(); iterator.hasNext(); ) {
                    Map.Entry<String, String> entry = iterator.next();
                    String name = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs[index++] = new NameValuePair(name, value);
                }
            }
            String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
            System.out.println("fileExtName=" + fileExtName);
            String[] strings = storageClient.upload_file(output.toByteArray(), fileExtName, nameValuePairs);
            return storageClient1.upload_file1(output.toByteArray(), fileExtName, nameValuePairs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件元数据
     *
     * @param fileId 文件ID
     * @return
     */
    public static Map<String, String> getFileMetadata(String fileId) {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            NameValuePair[] metaList = storageClient1.get_metadata1(fileId);
            if (metaList != null) {
                for (NameValuePair metaItem : metaList) {
                    map.put(metaItem.getName(), metaItem.getValue());
                }
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 删除文件
     *
     * @param fileId 文件ID
     * @return 删除失败返回-1，否则返回0
     */
    public static int deleteFile(String fileId) {
        try {
            return storageClient1.delete_file1(fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 下载文件
     *
     * @param fileId  文件ID（上传文件成功后返回的ID）
     * @param outFile 文件下载保存位置
     * @return
     */
    public static int downloadFile(String fileId, File outFile) {
        FileOutputStream fos = null;
        try {
            byte[] content = storageClient1.download_file1(fileId);
            fos = new FileOutputStream(outFile);
//            IOUtil.copy(content,fos);
            fos.write(content);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }
}
