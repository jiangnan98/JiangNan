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
 * @since 2019-04-29
 */
@Data
@Accessors(chain = true)
@TableName("t_model_tire_dlp")
public class ModelTireDlp extends Model<ModelTireDlp> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String letter;

    private String brand;

    private String model;

    private String category;

    private String year;

    private String tire;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
