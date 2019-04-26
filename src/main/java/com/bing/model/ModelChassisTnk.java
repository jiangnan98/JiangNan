package com.bing.model;

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
 * @since 2019-04-25
 */
@Data
@Accessors(chain = true)
@TableName("t_model_chassis_tnk")
public class ModelChassisTnk extends Model<ModelChassisTnk> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String brand;

    private String model;

    private String category;

    private String year1;

    private String year2;

    private String year3;

    private String swept;

    private String motor1;

    private String motor;

    private String product;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
