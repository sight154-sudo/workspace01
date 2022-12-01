package com.huawei.controller;

import com.huawei.po.Student;
import com.huawei.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author king
 * @date 2022/2/16-23:37
 * @Desc
 */
@RestController
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public List<Map<String,Object>> findInfo(@RequestParam(required = false,value = "cid")String cid) {
        return studentService.findInfo(cid);
    }

    @GetMapping("/searchStudent")
    public List<Map<String,Object>> findStudentScore(@RequestParam(required = false,value = "cid") String cid) {
        return studentService.findStudentSearch(cid);
    }

    @PostMapping("student/upload")
    public String uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("stu") Student stu, HttpServletRequest request) {
        System.out.println(stu);
        String originalFilename = file.getOriginalFilename();
        long size = file.getSize();
        logger.info(String.format(Locale.ROOT, "The upload file name is %s, And file size is %d", originalFilename, size));
        String contextPath = request.getContextPath();
        System.out.println(contextPath);
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        String realPath = request.getServletContext().getRealPath("/");
        System.out.println("realPath = " + realPath);
//        file.transferTo(new File());
        return stu.toString();
    }
}
