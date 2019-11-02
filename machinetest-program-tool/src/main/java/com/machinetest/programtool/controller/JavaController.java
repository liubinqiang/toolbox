package com.machinetest.programtool.controller;

import com.machinetest.programtool.bean.ServerInfoBean;
import com.machinetest.programtool.service.IJavaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @author liubinqiang
 */
@RequestMapping("/java")
@Controller
public class JavaController {

    @Resource
    private IJavaService javaService;

    @RequestMapping("/index")
    public String index(Model model) {
        ServerInfoBean info = javaService.getServerInfo();
        model.addAttribute("serverInfo", info);
        return "java/index";
    }
}
