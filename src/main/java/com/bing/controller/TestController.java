package com.bing.controller;

import com.bing.anno.Auth;
import com.bing.model.BaseUser;
import com.bing.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/18
 * Time: 17:19
 * To change this template use File | Setting | File Template.
 **/
@RestController
@RequestMapping("/test/")
public class TestController {

    @Autowired
    TestService testService;

    @Auth
    @PostMapping( value = "findList")
    public List<BaseUser> findList(){
        return testService.findUser();
    }
}
