package com.bing.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bing.model.ProductYisunFilterHy;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jiang
 * @since 2019-04-20
 */
public interface ProductYisunFilterHyMapper extends BaseMapper<ProductYisunFilterHy> {
    int editPrice(ProductYisunFilterHy productYisunFilterHy);
    int editOurcPriceMa(ProductYisunFilterHy productYisunFilterHy);
    int editPriceMa(ProductYisunFilterHy productYisunFilterHy);
    int editPriceMan(ProductYisunFilterHy productYisunFilterHy);
    int editOurcPriceMan(ProductYisunFilterHy productYisunFilterHy);
}
