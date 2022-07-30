package com.huawei.controller;

import com.huawei.service.TimeoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @author king
 * @date 2022/7/12-1:41
 * @Desc
 */
@RestController
public class TimeoutController {

    @Autowired
    private TimeoutService timeoutService;


    @GetMapping("/timeout")
    public String timeout() {
        String msg = "FAILED";
        try {
            msg = timeoutService.timeout();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg;
    }
}
