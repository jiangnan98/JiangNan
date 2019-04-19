package com.bing.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bing.mapper.BaseUserMapper;
import com.bing.model.BaseUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/18
 * Time: 17:19
 * To change this template use File | Setting | File Template.
 **/
@Service
public class TestService {

    static Logger logger = LogManager.getLogger(TestService.class.getName());

    @Autowired
    BaseUserMapper baseUserMapper;

   public List<BaseUser> findUser(){
       EntityWrapper<BaseUser> wrapper = new EntityWrapper<>();
       logger.debug("日志输出测试");
       List<BaseUser> baseUserArr = baseUserMapper.selectList(wrapper);
       return baseUserArr;
   }

}
