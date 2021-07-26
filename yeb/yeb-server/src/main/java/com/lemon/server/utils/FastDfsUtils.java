package com.lemon.server.utils;

import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * FastDFS 工具类
 *
 * @author: Lemon
 * @Date : 2021/7/25 19:10
 */
public class FastDfsUtils {

    private static Logger logger = LoggerFactory.getLogger(FastDfsUtils.class);

    /**
     * 1.初始化客户端，加载配置文件的公共客户端工具
     *  ClientGlobal.init 读取配置文件，并初始化对应的属性
     *
     */
    static {
        try {
            String path = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(path);
        } catch (Exception e) {
            logger.error("FastDFS Client init Fail！", e.getMessage());
        }
    }

    /**
     * 2.生成 Tracker 服务器端
     *
     * @return
     * @throws IOException
     */
    private static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer connection = trackerClient.getConnection();
        return connection;
    }

    /**
     * 获取文件路径
     *
     * @return
     */
    public static String getTrackerUrl() {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        StorageServer storeStorage = null;

        try {
            trackerServer = trackerClient.getConnection();
            storeStorage = trackerClient.getStoreStorage(trackerServer);
        } catch (IOException e) {
            logger.error("文件路径获取失败", e.getMessage());
        }

        return "http://" + storeStorage.getInetSocketAddress().getHostString() + ":8888/";
    }

    /**
     * 3.生成 Storage 客户端
     *
     * @return
     * @throws IOException
     */
    private static StorageClient getStorageClient() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public static String[] upload(MultipartFile file) {
        String filename = file.getOriginalFilename();
        logger.info("文件名：{}, {}", filename, file.getContentType());
        long startTime = System.currentTimeMillis();
        StorageClient storageClient = null;
        String[] uploadResults = null;

        try {
            // 获取 Storage 客户端
            storageClient = getStorageClient();
            // 上传
            uploadResults = storageClient.upload_file(file.getBytes(), filename.substring(filename.lastIndexOf(".") + 1), null);

        } catch (Exception e) {
            logger.error("上传文件失败, File Name:" + filename, e.getMessage());
        }

        logger.info("上传时间：" + (System.currentTimeMillis() - startTime) + "ms");
        if (null == uploadResults && storageClient != null) {
            logger.error("上传失败", storageClient.getErrorCode());
        }

        if (uploadResults != null) {
            logger.info("上传成功,group_name: " + uploadResults[0] + ",remoteFileName: " + uploadResults[1]);
        }


        return uploadResults;
    }

    /**
     * 获取文件信息
     *
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static FileInfo getFileInfo(String groupName, String remoteFileName) {
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            logger.error("文件信息获取失败", e.getMessage());
        }

        return null;
    }

    /**
     * 下载文件
     *
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static InputStream downFile(String groupName, String remoteFileName) {
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            InputStream inputStream = new ByteArrayInputStream(fileByte);
            return inputStream;
        } catch (Exception e) {
            logger.error("文件下载失败", e.getMessage());
        }

        return null;
    }

    /**
     * 删除文件
     *
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static void deleteFile(String groupName, String remoteFileName) {
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            storageClient.delete_file(groupName, remoteFileName);

        } catch (Exception e) {
            logger.error("文件删除失败", e.getMessage());
        }

    }
}
