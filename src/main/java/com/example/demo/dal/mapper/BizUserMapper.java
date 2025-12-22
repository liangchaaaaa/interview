package com.example.demo.dal.mapper;

import com.example.demo.dal.entity.BizUser;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;

/** BizUser Mapper 接口 */
public interface BizUserMapper extends BaseMapper<BizUser> {

  BizUser selectOne(QueryWrapper wrapper);

  Page<BizUser> selectPage(Page<BizUser> page);

  int updateById(BizUser entity);

  int update(BizUser update, QueryWrapper wrapper);
}
