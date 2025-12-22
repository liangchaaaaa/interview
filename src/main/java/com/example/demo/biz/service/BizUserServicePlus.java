package com.example.demo.biz.service;

import com.alicp.jetcache.CacheManager;
import com.example.demo.api.dto.*;
import com.example.demo.dal.convert.BizUserConvert;
import com.example.demo.dal.entity.BizUser;
import com.example.demo.dal.mapper.BizUserMapper;
import com.mybatisflex.core.paginate.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class BizUserServicePlus {

  public BizUser get(BizUserGetDTO request) {
    // TODO 补齐代码
    return null;
  }

  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  public Page<BizUser> list(BizUserQueryDTO request) {
    // TODO 补齐代码
    return null;
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public BizUser create(BizUserCreateDTO request) {
    // TODO 补齐代码
    return null;
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void update(BizUserUpdateDTO request) {
    // TODO 补齐代码
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void delete(BizUserDeleteDTO request) {
    // TODO 补齐代码
  }

  @Autowired private CacheManager cacheManager;

  public static final String KEY_IN_REDIS = "pos:biz_user:";

  @Autowired private BizUserMapper mapper;

  private static final BizUserConvert CONVERT = BizUserConvert.INSTANCE;
}
