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
 * @since 2019-05-10
 */
@Data
@Accessors(chain = true)
@TableName("t_model_cell_tuhu")
public class ModelCellTuhu extends Model<ModelCellTuhu> {

    private static final long serialVersionUID = 1L;

    private String id;

    private String letter;

    private String brand;

    private String model;

    private String category;

    private String motor;

    @TableField("A")
    private String a;

    @TableField("B")
    private String b;

    private String dis;

    private String year;

    @TableField("F")
    private String f;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
