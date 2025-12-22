package com.example.demo.biz.service;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.anno.CacheType;
import com.example.demo.api.dto.*;
import com.example.demo.dal.convert.BizUserConvert;
import com.example.demo.dal.entity.BizUser;
import com.example.demo.dal.mapper.BizUserMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class BizUserServicePlus {

  @Cached(name = KEY_IN_REDIS, key = "#request.lid", expire = 3600, cacheType = CacheType.BOTH)
  public BizUser get(BizUserGetDTO request) {
    QueryWrapper wrapper = new QueryWrapper();
    wrapper.where("lid = ?", request.getLid());
    return mapper.selectOne(wrapper);
  }

  @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
  public Page<BizUser> list(BizUserQueryDTO request) {
    Page<BizUser> page = new Page<>(request.current, request.pageSize);
    return mapper.selectPage(page);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public BizUser create(BizUserCreateDTO request) {
    BizUser entity = CONVERT.toDO(request);
    mapper.insert(entity);
    try {
      Cache<Object, Object> cache = cacheManager.getCache(KEY_IN_REDIS);
      if (entity.getLid() != null) {
        cache.remove(entity.getLid());
      }
    } catch (Exception ignore) {
    }
    return entity;
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void update(BizUserUpdateDTO request) {
    BizUser entity = CONVERT.toDO(request);
    mapper.updateById(entity);
    try {
      Cache<Object, Object> cache = cacheManager.getCache(KEY_IN_REDIS);
      if (entity.getLid() != null) {
        cache.remove(entity.getLid());
      }
    } catch (Exception ignore) {
    }
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void delete(BizUserDeleteDTO request) {
    if (request.lids == null || request.lids.isEmpty()) {
      return;
    }
    Cache<Object, Object> cache = null;
    try {
      cache = cacheManager.getCache(KEY_IN_REDIS);
    } catch (Exception ignore) {
    }
    for (Long lid : request.lids) {
      QueryWrapper wrapper = new QueryWrapper();
      wrapper.where("lid = ?", lid);
      BizUser update = new BizUser();
      update.setDeleted((short) 1);
      mapper.update(update, wrapper);
      if (cache != null) {
        try {
          cache.remove(lid);
        } catch (Exception ignore) {
        }
      }
    }
  }

  @Autowired private CacheManager cacheManager;

  public static final String KEY_IN_REDIS = "pos:biz_user:";

  @Autowired private BizUserMapper mapper;

  private static final BizUserConvert CONVERT = BizUserConvert.INSTANCE;
}
