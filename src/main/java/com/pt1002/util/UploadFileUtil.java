package com.pt1002.util;

import java.io.*;
import java.util.UUID;


import com.pt1002.common.exceptions.ApplicationException;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;


public class UploadFileUtil {

    private static final Logger logger = LogManager.getLogger(UploadFileUtil.class);


    /**
     * 移动文件至指定目录
     *
     * @param source
     * @param targetDirectory
     * @return
     */
    public static String moveApkToBackup(File source, String targetDirectory) {

        String fileName = System.currentTimeMillis() + source.getName();
        if (source.exists()) {

            File backupDirectory = new File(targetDirectory);

            if (!backupDirectory.exists()) {
                if (!backupDirectory.mkdirs())
                    return null;
            }

            if (!backupDirectory.isDirectory())
                return null;

            if (source.renameTo(new File(targetDirectory + fileName)))
                return fileName;
            else
                return null;
        } else {
            return null;
        }

    }

    /**
     * 上传apk文件
     *
     * @param uploadPath
     * @param file
     * @param apkName
     * @return
     */
    public static String uploadApk(String uploadPath, MultipartFile file, String apkName) {

        //参数判空
        if (file == null || StringUtils.isEmpty(uploadPath))
            return null;

        //文件原始名称
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //判断文件格式的合法性
        if (StringUtils.isEmpty(suffix) || !UploadFileUtil.fileFormatComparison(suffix, "APK"))
            return null;

        //创建抓拍文件存储路径，创建失败返回null
        String directoryPath = uploadPath;
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean mkdirs = directory.mkdirs();
            if (!mkdirs)
                return null;
        }

        //上传文件
        String filename = originalFilename;
        if (StringUtils.isNotEmpty(apkName)) {
            filename = apkName;
        }
        File saveFile = new File(directoryPath + filename);
        try {
            file.transferTo(saveFile);
            return uploadPath + filename;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传图片文件到服务器
     *
     * @param file 图片文件
     * @return 格式错误：“FFE” ；上传成功  ： 文件路径名 ； 上传失败 ：null
     */
    public static String uploadImage(String fileSavePath, MultipartFile file) throws ApplicationException {//, HttpServletRequest request
        try {

            if (file == null) {//|| request == null
                logger.warn("UploadFileUtil==>uploadImage:传入参数【file】错误， file = " + file);
                return null;
            }
            // 获得原始文件名?
            String fileName = file.getOriginalFilename();

//            //获取文件后缀名
//            String suffixName = null;
//
//            //截取文件后缀名，然后与照片常用的几个格式进行比较，截取或者比较失败，那么返回FFE（file format error）
//            suffixName = fileName.substring(fileName.lastIndexOf("."));
//            if (suffixName == null) {
//                logger.error("UploadFileUtil==>uploadImage:图片无格式");
//                return null;
//            } else if (!UploadFileUtil.fileFormatComparison(suffixName, "IMAGE")) {
//                logger.error("UploadFileUtil==>uploadImage:图片格式不正确，suffixName = " + suffixName);
//                return null;
//            }

            // 新文件名  (UUID取名加上后缀名，确保文件没有中文，并且UUID去掉横杠)+suffixName
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "");

            // 上传位
            String path = fileSavePath;
            String filePath = path + newFileName;
            String savePath = fileSavePath + newFileName;
            File f = new File(path);
            if (!f.exists())
                f.mkdirs();

            File file1 = new File(f.getAbsolutePath() + File.separator + newFileName);
            file.transferTo(file1);
            logger.info("UploadFileUtil==>uploadImage : 文件上传成功，上传路径 ： " + filePath);
            // 保存文件地址，用于JSP页面回显
            return savePath;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("UploadFileUtil==>uploadImage : 文件上传发生错误，错误消息为：" + e.getMessage());
            return null;
        }
    }

    /**
     * 图片后缀名校验
     *
     * @param suffixName
     * @return
     */
    public static boolean fileFormatComparison(String suffixName, String fileFormat) {
        if (StringUtils.isBlank(suffixName) || StringUtils.isBlank(fileFormat)) {
            logger.warn("UploadFileUtil==>fileFormatComparison : 传入参数【suffixName】、【fileFormat】错误，suffixName = " + suffixName + " ,fileFormat = " + fileFormat);
            return false;
        }

        //将suffixName转换成小写
        suffixName = suffixName.toLowerCase();

        if ("IMAGE".equals(fileFormat)) {
            if (!ApplicationConstant.IMAGE_SUFFIXNAME_FORMAT.contains(suffixName)) {
                return false;
            }
            return true;
        } else if ("ZIP".equals(fileFormat)) {
            if (!ApplicationConstant.ZIP_SUFFIXNAME_FORMAT.contains(suffixName)) {
                return false;
            }
            return true;
        } else if ("APK".equals(fileFormat)) {
            if (!ApplicationConstant.APK_SUFFIXNAME_FORMAT.contains(suffixName))
                return false;
            return true;
        } else {
            logger.warn("UploadFileUtil==>fileFormatComparison : 无匹配的文件格式，请检查代码逻辑！");
            return false;
        }
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            logger.info("UploadFileUtil==>delete : 删除失败，文件不存在，path = " + fileName);
            return false;
        } else {
            if (file.isFile()) {
                return deleteFile(fileName);
            } else {
                return deleteDirectory(fileName);
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                logger.info("UploadFileUtil==>deleteFile : 文件删除成功，path = " + fileName);
                return true;
            } else {
                logger.info("UploadFileUtil==>deleteFile : 文件删除失败，path = " + fileName);
                return false;
            }
        } else {
            logger.info("UploadFileUtil==>deleteFile : 文件删除失败，文件不存在 ，path = " + fileName);
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            logger.info("UploadFileUtil==>deleteDirectory : 目录删除失败，文件不存在 ，path = " + dir);
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = UploadFileUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = UploadFileUtil.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            logger.info("UploadFileUtil==>deleteDirectory : 目录删除失败 ，path = " + dir);
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            logger.info("UploadFileUtil==>deleteDirectory : 目录删除成功 ，path = " + dir);
            return true;
        } else {
            return false;
        }
    }


    /**
     * 已经改动
     *
     * @param fromPathAndFileName
     * @param toPath
     * @return
     */
    public static String copyFileByPath(String fromPathAndFileName, String toPath) {
        if (StringUtils.isBlank(fromPathAndFileName) || StringUtils.isBlank(toPath) || fromPathAndFileName.lastIndexOf(".") == -1) {
            logger.warn("UploadFileUtil ==> copyFileByPath : 传入参数【fromPath】、【toPath】格式错误，请检查代码逻辑！fromPathAndFileName = " + fromPathAndFileName + " ;toPath = " + toPath);
            return null;
        }

        try {
            String suffixName = fromPathAndFileName.substring(fromPathAndFileName.lastIndexOf("."));
            if (suffixName == null) {
                logger.error("UploadFileUtil ==> copyFileByPath : 文件无格式");
                return null;
            }

            // 新文件名  (UUID取名加上后缀名，确保文件没有中文，并且UUID去掉横杠)
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffixName;
            // 上传位置
            String path = toPath;
            String filePath = path + newFileName;
            String savePath = toPath + newFileName;

            File fromFile = new File(fromPathAndFileName);
            File toFile = new File(filePath);

            FileInputStream ins = new FileInputStream(fromFile);
            FileOutputStream out = new FileOutputStream(toFile);
            byte[] b = new byte[1024];
            int n = 0;
            while ((n = ins.read(b)) != -1) {
                out.write(b, 0, n);
            }

            ins.close();
            out.close();

            return savePath;
        } catch (Exception e) {
            logger.error("UploadFileUtil ==> copyFileByPath : 文件复制发生错误：" + e.getMessage());
            return null;
        }
    }
}
