package com.bing.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bing.model.ModelChassisTnk;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jiang
 * @since 2019-04-25
 */
public interface ModelChassisTnkMapper extends BaseMapper<ModelChassisTnk> {
    int editProduct(ModelChassisTnk modelChassisTnk);

    int editLetter(ModelChassisTnk modelChassisTnk);
}
