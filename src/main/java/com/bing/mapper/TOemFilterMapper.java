package com.bing.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bing.model.TOemFilter;
import com.bing.req.vo.OemFilter;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jiang
 * @since 2019-06-03
 */
public interface TOemFilterMapper extends BaseMapper<TOemFilter> {
    int edit(OemFilter oemFilter);
    int insertOne(OemFilter oemFilter);
}
