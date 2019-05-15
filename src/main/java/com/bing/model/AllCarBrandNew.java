package com.bing.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @since 2019-05-15
 */
@Data
@Accessors(chain = true)
@TableName("t_all_car_brand_new")
public class AllCarBrandNew extends Model<AllCarBrandNew> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "brand_id", type = IdType.AUTO)
    private Long brandId;

    @TableField("brand_name")
    private String brandName;

    private String letter;

    @TableField("href_id")
    private String hrefId;

    @TableField("is_show")
    private Integer isShow;

    @TableField("is_fresh")
    private Integer isFresh;

    @TableField("is_hot")
    private Integer isHot;

    @TableField("create_day")
    private String createDay;

    @TableField("href_oss")
    private String hrefOss;


    @Override
    protected Serializable pkVal() {
        return this.brandId;
    }

}
