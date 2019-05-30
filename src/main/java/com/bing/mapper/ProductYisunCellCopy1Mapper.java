package com.bing.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bing.model.ProductYisunCellCopy1;
import com.bing.po.CellModel;
import com.bing.res.vo.FactoryNum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jiang
 * @since 2019-05-10
 */
@Repository
public interface ProductYisunCellCopy1Mapper extends BaseMapper<ProductYisunCellCopy1> {
    int editModel(ProductYisunCellCopy1 productYisunCellCopy1);
    List<String> findStr();

    List<CellModel> findCellModel();

    List<FactoryNum> findFactory(@Param("tab")String tab);

    int editFactory(FactoryNum factoryNum);
}
