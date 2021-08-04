package com.badu.controller;

import com.badu.model.OrderExp;
import com.badu.service.IOrderExpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderExpService iOrderExpService;


    @GetMapping("/findAll")
    public List<OrderExp> findAll(){
        List<OrderExp> all = iOrderExpService.findAll();
        return all;
    }

    @GetMapping("submitOder")
    public String saveOrder(@RequestParam("orderNumber")int orderNumber){

        iOrderExpService.insert(orderNumber);
        return "SUCCESS";
    }
}
