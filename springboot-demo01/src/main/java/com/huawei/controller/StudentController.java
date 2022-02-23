package com.huawei.controller;

import com.huawei.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
