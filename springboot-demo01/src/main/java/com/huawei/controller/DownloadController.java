package com.huawei.controller;

import com.google.common.io.Files;
import com.google.gson.JsonObject;
import com.huawei.utils.BaseResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthScrollBarUI;
import javax.xml.ws.Response;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author king
 * @date 2022/7/28-1:11
 * @Desc
 */
@RestController
public class DownloadController {


    @PostMapping("download")
    public void download(@RequestBody JsonObject jsonObject, HttpServletResponse response) throws UnsupportedEncodingException {
        String fileName = "test.zip";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
        ZipOutputStream out = null;
        long s = System.currentTimeMillis();
        byte[] buffer = new byte[1024];
        try {
            File file1 = new File("C:\\Users\\King\\Downloads\\aaa.pdf");
            File file2 = new File("C:\\Users\\King\\Downloads\\bbb.pdf");
            out = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
            List<File> lists = Arrays.asList(file1, file2);

            for (File list : lists) {
                FileInputStream in = new FileInputStream(list);
                out.putNextEntry(new ZipEntry(list.getName()));
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis() - s);
        /*try {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
            ServletOutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(output);
            FileInputStream ipt = new FileInputStream(new File("C:\\Users\\King\\Downloads", fileName));
            int len = 0;
            while ((len = ipt.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer , 0 ,len);
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            ipt.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }*/
        return;
    }

}
