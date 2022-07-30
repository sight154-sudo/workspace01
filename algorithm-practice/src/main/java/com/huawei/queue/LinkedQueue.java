package com.huawei.queue;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author king
 * @date 2022/3/29-23:54
 * @Desc 链式队列
 */

public class LinkedQueue {

    private static final Logger log = LoggerFactory.getLogger(LinkedQueue.class);

    public static void main1(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine().substring(2);
        System.out.println(Integer.parseInt(s,16));
    }

    public static void main(String[] args) {
        String s = pathToBase64("C:\\Users\\King\\Pictures\\Saved Pictures\\303045.jpg");
        log.info(s);
    }

    public static String pathToBase64(String filePath){
        try {
            File file = new File(filePath);
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            String encode = new BASE64Encoder().encode(buffer);
            return "data:image/png;base64,"+encode;
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
