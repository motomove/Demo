package com.demo.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YinXu
 */
public class DirectoryUtil {

    private final static String formatDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public static void main(String[] args) {
//        String[] dirStrArr = {
//                "com/whaty/platform/entity/web/action/marketPm",
//                "com/whaty/platform/util"
//        /home/whaty/workspace/whaty-xm/xm-whatypm/whatypm/src/main/java.java
//        };
//                                com/whaty/platform/entity/web/action/marketPm/PeResearchMilestoneManagementAction.java

        String classPath = "/home/whaty/workspace/whaty-xm/xm-whatypm/whatypm/target/by/";
        String[] fileArr = {
                "WEB-INF/classes/com/whaty/platform/sso/web/action/SsoConstant.class",
                "WEB-INF/classes/com/whaty/platform/entity/web/action/borrow/PeBorrowsqAction.class"
        };

        String rootDirPath = "/home/whaty/document/tmp/whatypm";
        String date = formatDate();
        File rootDir = new File(rootDirPath);
        if(rootDir.isDirectory() && rootDir.exists()){
//            for(String dirStr : fileArr){
//                String
//                File dirFile = new File(rootDirPath + File.separator + date + File.separator + dirStr);
//                if(!dirFile.exists()){
//                    dirFile.mkdirs();
//                }
//            }

            for(String file : fileArr){
                String srcFile = classPath + file;
                String dirFiel = rootDirPath + File.separator + date + File.separator + file;
                copyFile(srcFile, dirFiel);
            }


        } else {
            System.out.println("不做任何处理");
        }
    }


    /*
     * 文件复制
     */
    private static void copyFile(String oldPath, String newPath) { // 复制文件
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            File newfile = new File(newPath);
            if (!newfile.getParentFile().exists()) {// 目录不存在时，创建目录
                newfile.getParentFile().mkdirs();
            }

            if (oldfile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);

                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; // 字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
                System.out.println("文件：" + oldfile.getName() + " 复制大小：" + (double) bytesum / 1024 + " KB");
            } else {
                System.err.println("文件：" + oldfile.toString() + "不存在！！！");
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }
}
