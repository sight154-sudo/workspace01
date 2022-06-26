package com.huawei.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.po.Student;
import com.huawei.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author king
 * @date 2022/2/16-23:37
 * @Desc
 */
@RestController
public class StudentController {

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
    public String uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("stu") Student stu) {
        System.out.println(stu);
        return stu.toString();
    }
}
