package com.common;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

/**
 * 负责将文件上传至FTP
 */
@Component
public class FileUploader {

    private static Logger logger = LoggerFactory.getLogger(FileUploader.class);

    private static final int connectCount = 3;

    private static final String ip = "";
    private static final String port = "";
    private static final String userName = "";
    private static final String password = "";

    private static final String sftpIp = "";
    private static final String sftpPort = "";
    private static final String sftpUserName = "";
    private static final String sftpPassword = "";

    private ThreadLocal<FTPClient> ftp = new ThreadLocal<FTPClient>() {
        protected FTPClient initialValue() {
            return new FTPClient();
        }
    };

    /**
     * 组合成完成的文件路径 example: upop/20130922/merId_20130922.txt
     * @param servPath
     * @param merId
     * @param date
     * @return
     */
    public static String getFullFilePath(String servPath, String merId, String date) {
        return new StringBuilder(FileUtil.ensureDirPath(servPath)).append(date).append(File.separator).append(merId).append("_")
                .append(date).append(".txt").toString();
    }

    /**
     * 组合成完成的文件路径 example: upop/20130922/merId_20130922.txt
     * @param servPath
     * @param merId
     * @param date
     * @return
     */
    public static String getFullFilePath(String servPath, String merId, String date, String postfix) {
        return new StringBuilder(FileUtil.ensureDirPath(servPath)).append(date).append(File.separator).append(merId).append("_")
                .append(date).append("_").append(postfix).append(".txt").toString();
    }


    /**
     * 将本地某个路径下的所有文件上传至SFTP某一个路径下
     *
     * @param localPath  本地路径
     * @param remotePath FTP路径
     * @return
     */
    public int uploadSftpServer(String localPath, String remotePath) {
        return uploadServer(localPath, remotePath, ServerTypeEnum.SFTP);
    }


    /**
     * 将本地某个路径下的所有文件上传至FTP某一个路径下
     *
     * @param localPath  本地路径
     * @param remotePath FTP路径
     * @return
     */
    public int uploadFtpServer(String localPath, String remotePath) {
        return uploadServer(localPath, remotePath, ServerTypeEnum.FTP);
    }

    /**
     * 将本地某个路径下的所有文件上传至FTP某一个路径下
     *
     * @param localPath  本地路径
     * @param remotePath FTP路径
     * @param serverType
     * @return
     */
    public int uploadServer(String localPath, String remotePath, ServerTypeEnum serverType) {
        int result = -1;
        try {
            File localFileFolder = new File(localPath);
            if (!localFileFolder.exists()) {
                logger.error("本地文件夹不存在");
                return result;
            }

            // 连接服务器，如果连接失败则返回-1
            if (!connect(remotePath, serverType)) {
                return result;
            }

            // 上传
            if (recursiveUpload(new File(localPath))) {
                result = 1;
            } else {
                result = 0;
            }
        } catch (IOException e) {
            logger.error("上传文件过程出错", e);
        } finally {
            // 关闭ftp连接
            closeConnect();
        }

        return result;
    }

    private boolean connect(String remotePath, ServerTypeEnum serverType) {
        logger.info("当前链接的ip地址为{}", ip);
        for (int i = 0; i < connectCount; i++) {
            logger.info("第{}次尝试链接ftp服务器开始", i + 1);
            if (connectOnce(remotePath, serverType)) {
                return true;
            } else {
                logger.info("第{}次尝试链接ftp服务器失败", i + 1);
            }
        }
        return false;
    }

    /**
     * 与ftp服务器进行链接
     *
     * @param remotePath 上传到ftp服务器哪个路径下
     * @param serverType
     * @return true连接成功 false连接失败
     */
    private boolean connectOnce(String remotePath, ServerTypeEnum serverType) {
        FTPClient ftp = this.ftp.get();
        try {
            do {
                if(ServerTypeEnum.FTP.equals(serverType)){
                    ftp.connect(ip, Integer.parseInt(port));
                    if (!ftp.login(userName, password)) {
                        logger.error("ftp服务器login失败");
                        break;
                    }
                }else {
                    ftp.connect(sftpIp, Integer.parseInt(sftpPort));
                    if (!ftp.login(sftpUserName, sftpPassword)) {
                        logger.error("ftp服务器login失败");
                        break;
                    }
                }
                if (!ftp.setFileType(FTPClient.BINARY_FILE_TYPE)) {
                    logger.error("ftp服务器setFileType失败");
                    break;
                }
                if (!ftp.changeWorkingDirectory(remotePath)) {
                    logger.error("ftp服务器changeWorkingDirectory失败");
                    break;
                }
                return true;
            } while (false);
            ftp.disconnect();
        } catch (SocketException e) {
            logger.error("和ftp服务器通信失败", e);
        } catch (Exception e) {
            logger.error("服务器链接异常", e);
        }
        return false;
    }

    /**
     * 文件批量上传
     *
     * @param file 上传的文件或文件夹
     */
    private boolean recursiveUpload(File file) throws IOException {
        boolean isUploadOK = true;
        FTPClient ftp = this.ftp.get();

        // 单个文件直接上传
        if (!file.isDirectory()) {
            singleFileUpload(file, ftp);
            return true;
        }
        // 文件夹则递归上传
        // 空文件夹则直接返回
        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            logger.info("上传的过程中出现空文件夹，{}", file.getAbsolutePath());
            return true;
        }

        // ftp目录转到子文件夹
        ftp.makeDirectory(file.getName());
        logger.info("current file {}", file.getName());
        ftp.changeWorkingDirectory(file.getName());
        logger.info("current directory {}", ftp.printWorkingDirectory());

        for (File subFile : files) {
            if (subFile.isDirectory()) {
                // 查看下一级子目录的处理情况，如果子目录出现错误，则当前目录肯定处理错误；
                if (!recursiveUpload(subFile)) {
                    isUploadOK = false;
                }
            } else {
                // 日期文件夹下有部分文件未上传成功
                if (!singleFileUpload(subFile, ftp)) {
                    isUploadOK = false;
                }
            }
        }

        // 当前ftp转到父目录
        ftp.changeToParentDirectory();
        // 删除当前文件或文件夹
        file.delete();

        return isUploadOK;
    }

    /**
     * 单个文件上传
     *
     * @throws IOException
     * @throws Exception
     */
    private boolean singleFileUpload(File file, FTPClient ftp) {
        FileInputStream input = null;
        try {
            input = new FileInputStream(file);
            if (ftp.storeFile(file.getName(), input)) {
                input.close();
                // 删除当前文件
                file.delete();
                return true;
            } else {
                logger.error("上传单个文件失败：" + file.getPath());
                return false;
            }
        } catch (IOException e) {
            logger.error("上传单个文件出现异常,{}", e);
            return false;
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * 关闭连接
     */
    private void closeConnect() {
        FTPClient ftp = this.ftp.get();
        try {
            ftp.logout();
            ftp.disconnect();
            logger.info("关闭与ftp服务器的链接");
        } catch (Exception e) {
            logger.error("链接关闭异常{}", e);
        }
    }

    private enum ServerTypeEnum{
        SFTP, FTP
    }
}
