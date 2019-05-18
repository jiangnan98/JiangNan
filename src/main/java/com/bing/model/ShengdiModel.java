package com.bing.model;

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
 * @since 2019-05-18
 */
@Data
@Accessors(chain = true)
@TableName("shengdi_model")
public class ShengdiModel extends Model<ShengdiModel> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String product;

    private String model;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
