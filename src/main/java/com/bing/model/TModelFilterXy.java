package com.bing.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jiang
 * @since 2019-05-24
 */
@Data
@Accessors(chain = true)
@TableName("t_model_filter_xy")
public class TModelFilterXy extends Model<TModelFilterXy> {

    private static final long serialVersionUID = 1L;

    private String id;

    @TableField("cm_brand_country")
    private String cmBrandCountry;

    private String letter;

    private String brand;

    private String aaaaa;

    private String factory;

    @TableField("cm_factory_id")
    private String cmFactoryId;

    private String model;

    @TableField("cm_model_id")
    private String cmModelId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
