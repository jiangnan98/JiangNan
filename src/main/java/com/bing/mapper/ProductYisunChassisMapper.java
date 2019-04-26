package com.bing.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bing.model.ProductYisunChassis;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jiang
 * @since 2019-04-26
 */
public interface ProductYisunChassisMapper extends BaseMapper<ProductYisunChassis> {
    int editPrice(ProductYisunChassis productYisunChassis);
}
