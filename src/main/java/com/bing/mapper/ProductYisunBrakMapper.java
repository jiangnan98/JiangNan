package com.bing.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bing.model.ProductYisunBrak;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jiang
 * @since 2019-04-28
 */
public interface ProductYisunBrakMapper extends BaseMapper<ProductYisunBrak> {
    ProductYisunBrak findOne(ProductYisunBrak productYisunBrak);

    int editModel(ProductYisunBrak productYisunBrak);

    int editBrak(ProductYisunBrak productYisunBrak);
}
