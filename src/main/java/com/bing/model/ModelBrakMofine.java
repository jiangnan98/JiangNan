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
 * @since 2019-04-28
 */
@Data
@Accessors(chain = true)
@TableName("t_model_brak_mofine")
public class ModelBrakMofine extends Model<ModelBrakMofine> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String letter;

    private String brand;

    @TableField("brand_name")
    private String brandName;

    private String model;

    @TableField("model_name")
    private String modelName;

    private String category;

    @TableField("category_name")
    private String categoryName;

    private String dis;

    @TableField("dis_name")
    private String disName;

    private String year;

    @TableField("year_name")
    private String yearName;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
