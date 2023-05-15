package com.jike;

import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * @author king
 * @date 2023/4/25-22:47
 * @Desc
 */
public class Main {
    public static void main(String[] args) throws IOException {
        InputStream in = Main.class.getClassLoader().getResourceAsStream("com/jike/Hello.class");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] by = new byte[1024];
        int len = -1;
        while ((len = in.read(by)) != -1) {
            outputStream.write(by, 0 , len);
        }
        String s = Base64.getEncoder().encodeToString(outputStream.toByteArray());
        System.out.println(s);
    }
}
