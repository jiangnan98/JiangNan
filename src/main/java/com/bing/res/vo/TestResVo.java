package com.bing.res.vo;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/22
 * Time: 10:10
 * To change this template use File | Setting | File Template.
 **/
@Data
public class TestResVo implements Serializable {
    private static final long serialVersionUID = -3922816859689622096L;
    
    private String testName;

    private Integer aa;
}
