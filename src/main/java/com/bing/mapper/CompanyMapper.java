package com.bing.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bing.model.Company;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 公司表。
通过（t_com_user)和用户进行关联表 Mapper 接口
 * </p>
 *
 * @author Jiang
 * @since 2019-05-30
 */
@Repository
public interface CompanyMapper extends BaseMapper<Company> {

}
