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
 * @since 2019-04-30
 */
@Data
@Accessors(chain = true)
@TableName("t_model_brak_shengdi")
public class ModelBrakShengdi extends Model<ModelBrakShengdi> {

    private static final long serialVersionUID = 1L;

    private String id;

    @TableField("brand_id")
    private String brandId;

    @TableField("model_id")
    private String modelId;

    @TableField("category_id")
    private String categoryId;

    @TableField("year_id")
    private String yearId;

    @TableField("motor_id")
    private String motorId;

    private String letter;

    private String brand;

    private String model;

    private String category;

    private String dis;

    private String motor;

    private String product;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
