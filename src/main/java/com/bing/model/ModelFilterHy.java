package com.bing.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jiang
 * @since 2019-04-23
 */
@Data
@Accessors(chain = true)
@TableName("t_model_ignition_ngk")
public class ModelFilterHy extends Model<ModelFilterHy> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 首写字母
     */
    private String letter;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 车型
     */
    private String model;

    /**
     * 车系
     */
    private String category;

    /**
     * 年款
     */
    private String series;

    /**
     * 排量
     */
    private String swept;

    /**
     * 发动机
     */
    private String motor;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
