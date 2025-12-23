总体实现
1. Controller 层参数校验
       create：name 不能为空。使用 @Valid + @NotBlank 注解。
       delete：lids 不能为空且非空列表。用 @NotNull 校验。
       list：pageSize 不能为空且 <= 100；current（当前页）不能为空且 >= 0。校验并在不合规时返回错误响应。

2. mybatis-flex操作biz_user表
     create：在转换 DTO > DO 时，设置 created_time 为当前时间（如 LocalDateTime.now()），然后 mapper.insert(entity)。

     update：在转换 DTO > DO 时，设置 updated_time 为当前时间，使用 mapper.update(entity)。确保 lid 非空且用于定位记录。

     delete：实现为更新操作，设置 deleted=1，逻辑删除。可使用 mapper.updateByQuery 或 updateById。

     list：使用 MyBatisFlex 的 QueryWrapper 构造分页查询 .where(BizUser::getDeleted).eq(0) 并 .orderBy(BizUser::getCreatedTime, false)；确保对 request.getCurrent()、request.getPageSize() 做边界校验与默认值。

3. get 接口缓存实现
     在 get(BizUserGetDTO) 方法上使用 @Cached(name = KEY_IN_REDIS, key = "#request.lid", cacheType = CacheType.BOTH, localExpire=60, expire=3600, cacheNullValue=true)。

     key 命名：确保 KEY_IN_REDIS 与 Redis key 前缀一致（示例 pos:biz_user:），#request.lid 能解析为数值。

     缓存更新：在 create/update 方法使用 @CacheUpdate(name = KEY_IN_REDIS, key = "#result.lid", value = "#result") 更新缓存； 在 delete 方法移除缓存（cache.remove(lid) 或使用 @CacheInvalidate）。

