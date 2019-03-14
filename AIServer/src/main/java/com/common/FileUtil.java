package com.common;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 取得文件路径
     *
     * @param path   当前目录之下的目录
     * @param fileNm 文件的名称
     * @return String 整个文件路径
     */
    public static String getPath(String path, String fileNm) {
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        StringBuffer merKeyFile = new StringBuffer();
        merKeyFile.append(basePath);
        merKeyFile.append(path);
        merKeyFile.append(fileNm);
        return merKeyFile.toString();
    }

    /**
     * 返回程序所在路径
     *
     * @return String
     */
    public static String getPath() {
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        return basePath;
    }

    /**
     * 获得原始路径
     *
     * @return String
     */
    public static String getBasePath() {
        String[] path = Thread.currentThread().getContextClassLoader().getResource("").getPath().split("/");
        StringBuilder basePath = new StringBuilder();
        if (path != null) {
            for (int i = 0; i < path.length; i++) {
                if (path[i].equals("webapps")) {
                    basePath.append("cache").append("/");
                    break;
                } else {
                    basePath.append(path[i]).append("/");
                }
            }
        }
        return basePath.toString();
    }

    /**
     * 获取文件名
     *
     * @param path
     * @return String
     */
    public static String getFileName(String path) {
        String[] stemps = path.split("/");
        if (stemps == null || stemps.length <= 0) {
            return "";
        }
        return stemps[stemps.length - 1];
    }

    /**
     * 读取文件内容
     *
     * @param fileName
     * @return String
     */
    public static String readFile(String fileName) {
        String output = "";
        BufferedReader input = null;
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            try {
                input = new BufferedReader(new FileReader(file));
                StringBuffer buffer = new StringBuffer();
                String text;
                while ((text = input.readLine()) != null)
                    buffer.append(text);
                output = buffer.toString();
            } catch (Exception ioException) {
            } finally {
                IOUtils.closeQuietly(input);
            }
        }
        return output;
    }

    /**
     * 删除文件
     *
     * @param deleteFileName
     */
    public static void deleteFileOne(String deleteFileName, String date) {
        StringBuilder txtName = new StringBuilder();
        txtName.append(getBasePath());
        txtName.append(deleteFileName);
        txtName.append("/");
        txtName.append(date);
        txtName.append(".txt");

        File deleteFile = new File(deleteFileName);
        if (deleteFile.exists()) {
            deleteFile.delete();
            logger.info("deleteFileOne |  delete txt file succeed :  file name:" + txtName.toString());
        } else {
            logger.info("deleteFileOne |  delete txt file fail :     file name:" + txtName.toString());
        }
    }

    /**
     * 从项目类路径中读取文件 得到字节数组
     *
     * @param filePath 文件的相对路径
     */
    public static byte[] loadKeyFromClasspath(String filePath) {
        byte[] bytes = null;
        try {
            bytes = FileUtils.readFileToByteArray(new ClassPathResource(filePath).getFile());
        } catch (IOException e) {
            logger.error("loadKeyFromClasspath | load data fail : file path :" + filePath, e);
        }
        return bytes;
    }

    /**
     * 读取某个路径下的文件内容
     * 如果filePath以'/'开头，则认为其为linux下的一个绝对路径
     * 否则认为其为classpath下的相对路径
     *
     * @param filePath 文件的相对路径
     * @return
     */
    public static byte[] loadKey(String filePath) {
        logger.info("load key from path : {}", filePath);
        if (StringUtil.isBlank(filePath)) {
            return null;
        }
        if (filePath.startsWith("/")) {
            try {
                return FileUtils.readFileToByteArray(new File(filePath));
            } catch (IOException e) {
                logger.error("loadKey | load data fail : file path :" + filePath, e);
            }
        }
        return loadKeyFromClasspath(filePath);
    }
    
    /**
     * 如果path 以‘/’开头或者第二个字符为':' 则认为是一个绝对路径，否则认为是一个相对路径，则从class path下读取
     * @param path
     * @return 指定路径下的File对象
     */
	public static File getFile(String path) {
		//logger.info("load file from path : {}", path);
		if (StringUtils.isBlank(path)) {
			return null;
		}
		//在linux环境下 以‘/’开头
		if (path.startsWith("/")  || path.charAt(1) ==':') {
			return new File(path);
		}

		try {
			return new ClassPathResource(path).getFile();
		} catch (IOException e) {
			return null;
		}

	}

    /**
     * 确保将传入的路径以路径分割符结尾
     * @param path
     * @return
     */
    public static String ensureDirPath(String path) {
        if (path.endsWith("/") || path.endsWith("\\")) {
            return path;
        }
        return path + File.separator;
    }
}
