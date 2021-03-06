package com.bing.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bing.model.ProductYisunIgnition;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jiang
 * @since 2019-04-25
 */
public interface ProductYisunIgnitionMapper extends BaseMapper<ProductYisunIgnition> {
    int editPrice(ProductYisunIgnition productYisunIgnition);
    int editRemaker(ProductYisunIgnition productYisunIgnition);
}
