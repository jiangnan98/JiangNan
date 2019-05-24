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
 * @since 2019-05-23
 */
@Data
@Accessors(chain = true)
@TableName("t_model_filter_xy")
public class ModelFilterXy extends Model<ModelFilterXy> {

    private static final long serialVersionUID = 1L;

    private String id;

    @TableField("cm_brand_country")
    private String cmBrandCountry;

    @TableField("cm_brand_country_id")
    private String cmBrandCountryId;

    @TableField("cm_brand")
    private String cmBrand;

    @TableField("cm_brand_id")
    private String cmBrandId;

    @TableField("cm_factory")
    private String cmFactory;

    @TableField("cm_factory_id")
    private String cmFactoryId;

    @TableField("cm_model")
    private String cmModel;

    @TableField("cm_model_id")
    private String cmModelId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
