package com.bing.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bing.model.ProductYisunChassis;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jiang
 * @since 2019-04-26
 */
@Repository
public interface ProductYisunChassisMapper extends BaseMapper<ProductYisunChassis> {
    int editPrice(ProductYisunChassis productYisunChassis);

    int editFactory(ProductYisunChassis productYisunChassis);
}
