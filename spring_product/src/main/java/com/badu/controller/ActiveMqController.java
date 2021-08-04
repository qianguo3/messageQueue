package com.badu.controller;

import com.badu.queue.QueueSender;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/activeMq")
public class ActiveMqController {

    @Resource
    private QueueSender queueSender;

    @GetMapping("/queueSend")
    public  String queueSend(@RequestParam("message")String message){
        String opt = "";
        try {
            queueSender.send("test.queue", message);

            System.out.println("发送的消息为："+message);
            opt = "suc";
        } catch (Exception e) {
            opt = e.getCause().toString();
        }
        return opt;


    }
}
