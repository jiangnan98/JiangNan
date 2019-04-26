package com.bing.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 商品类型表
自级联
p_id为上级s_id
 * </p>
 *
 * @author Jiang
 * @since 2019-04-23
 */
@Data
@Accessors(chain = true)
@TableName("t_product_type")
public class ProductType extends Model<ProductType> {

    private static final long serialVersionUID = 1L;


    private String id;
    /**
     * 商品类别名称
     */
    private String name;

    /**
     * 上级商品的s_id
     */
    @TableField("p_id")
    private Integer pId;

    /**
     * 车型显示等级(0-6)
     */
    private String level;

    /**
     * 商品的编号，关联下级商品的p_id
     */
    @TableField("s_id")
    private Integer sId;

    /**
     * 是否有特殊属性 1：雨刮片 2：轮胎
     */
    @TableField("is_attr")
    private String isAttr;

    /**
     * 是否显示  1显示 0不显示
     */
    @TableField("is_show")
    private String isShow;

    @TableField("pic_logo")
    private String picLogo;

    /**
     * 表名
     */
    @TableField("table_name")
    private String tableName;

    @TableField("attr_type")
    private String attrType;

    private Integer sort;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
