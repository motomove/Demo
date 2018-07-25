package com.demo.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HadoopDemo {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        String HDFS_PATH = "hdfs://192.168.44.36:9000"; //要连接的hadoop
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", HDFS_PATH);
        //连接文件系统,FileSystem用来查看文件信息和创建文件
//        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.44.36:9000"), configuration, "hadoop");

        String dst1 = "hdfs://192.168.44.36:9000/user/hadoop/yinxu_test";
        boolean status = creatDir( dst1 ,  configuration) ;
        System.out.println("status = " + status);



    }

    public static boolean creatDir(String dst, Configuration conf) {
        Path dstPath = new Path(dst);
        try {
            FileSystem dhfs = FileSystem.get(new URI("hdfs://192.168.44.36:9000"), conf, "hadoop");
            dhfs.mkdirs(dstPath);
        } catch (IOException ie) {
            ie.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
