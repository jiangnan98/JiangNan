package com.bing.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bing.model.ProductType;

/**
 * <p>
 * 商品类型表
自级联
p_id为上级s_id Mapper 接口
 * </p>
 *
 * @author Jiang
 * @since 2019-04-23
 */
public interface ProductTypeMapper extends BaseMapper<ProductType> {

}
