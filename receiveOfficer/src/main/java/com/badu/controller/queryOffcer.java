package com.badu.controller;

import com.badu.service.IQueryOffceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/zrs")
@Controller
public class queryOffcer {

    @Resource
    private IQueryOffceService queryOffceService;

    @RequestMapping(value = "/find")
    public ModelAndView find(){
        String s = queryOffceService.queryOffcer();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("show");
       modelAndView.addObject("message",s);
        return modelAndView;

    }
}
