package com.bing.pack;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/30
 * Time: 10:46
 * To change this template use File | Setting | File Template.
 **/
@Data
public class ArgumentInvalidResult{

    private static final long serialVersionUID = -7375068272299605384L;

    private String field;
    private Object rejectedValue;
    private String defaultMessage;
    private String className;
    private String filedName;
    private Integer lineNumber;
    private String methodName;

    @Override
    public String toString() {
        return "ArgumentInvalidResult{" +
                "field='" + field + '\'' +
                ", rejectedValue=" + rejectedValue +
                ", defaultMessage='" + defaultMessage + '\'' +
                ", className='" + className + '\'' +
                ", filedName='" + filedName + '\'' +
                ", lineNumber=" + lineNumber +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
