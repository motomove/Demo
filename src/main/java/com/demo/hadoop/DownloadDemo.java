package com.demo.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

public class DownloadDemo {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String HDFS_PATH = "hdfs://192.168.44.36:9000"; //要连接的hadoop
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", HDFS_PATH);
        //连接文件系统,FileSystem用来查看文件信息和创建文件
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.44.36:9000"), configuration, "hadoop");
        InputStream is = fileSystem.open(new Path("hdfs://192.168.44.36:9000/user/hadoop/yinxu/yinxu.txt"));
        OutputStream out = new FileOutputStream("/home/whaty/workspace/idea-local/Demo/out/yinxu.txt");
        IOUtils.copyBytes(is,out,4096,true);
        System.out.println("下载完成");

    }
}
