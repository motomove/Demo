package com.demo.string;

/**
 * @Author: YinXu
 * @Date: 19-1-3 下午4:24
 * @Version 1.0
 */
public class ByteOptUtil {

    public static void main(String[] args) {
        boolean flag = true;

        flag = flag & false;

        flag = flag & true;
        System.out.println("flag = " + flag);
    }
}
