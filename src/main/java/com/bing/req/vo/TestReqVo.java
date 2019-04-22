package com.bing.req.vo;

import com.bing.anno.Verify;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/22
 * Time: 10:05
 * To change this template use File | Setting | File Template.
 **/
@Data
public class TestReqVo implements Serializable {

    private static final long serialVersionUID = 4411523244138623948L;

    @Verify
    private String testId;

    @Verify(require = false)
    private String testName;
}
