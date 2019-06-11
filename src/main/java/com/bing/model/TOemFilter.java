package com.bing.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;



import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jiang
 * @since 2019-06-03
 */
@Data
@Accessors(chain = true)
@TableName("t_oem_filter")
public class TOemFilter extends Model<TOemFilter> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String oem;

    private String manpai;

    private String male;

    private String hgst;

    @TableField("hgst_size")
    private String hgstSize;

    @TableField("hgst_img")
    private String hgstImg;

    private String haiye;

    @TableField("haiye_size")
    private String haiyeSize;

    @TableField("haiye_img")
    private String haiyeImg;

    private String xinyi;

    @TableField("xinyi_size")
    private String xinyiSize;

    @TableField("xinyi_img")
    private String xinyiImg;

    private String factory;

    @TableField("hgst_new")
    private String hgstNew;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
