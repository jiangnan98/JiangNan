package com.bing.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bing.model.ModelTireDlp;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Jiang
 * @since 2019-04-29
 */
public interface ModelTireDlpMapper extends BaseMapper<ModelTireDlp> {
  List<ModelTireDlp> findModel(ModelTireDlp modelTireDlp);
}
