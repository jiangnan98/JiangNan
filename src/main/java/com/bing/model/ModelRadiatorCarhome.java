package com.bing.model;

import com.baomidou.mybatisplus.annotations.TableField;
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
 * @since 2019-05-07
 */
@Data
@Accessors(chain = true)
@TableName("t_model_radiator_carhome")
public class ModelRadiatorCarhome extends Model<ModelRadiatorCarhome> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String letter;

    private String brand;

    private String factory;

    private String model;

    private String engineInfo;

    @TableField("model_detail")
    private String modelDetail;

    private String trans;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
