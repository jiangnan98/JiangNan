package com.bing.jiang.entity;

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
 * @since 2019-04-25
 */
@Data
@Accessors(chain = true)
@TableName("t_model_chassis_tnk")
public class TModelChassisTnk extends Model<TModelChassisTnk> {

    private static final long serialVersionUID = 1L;

    private String brand;

    private String model;

    private String category;

    private String year1;

    private String year2;

    private String year3;

    private String swept;

    private String motor1;

    private String motor;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
