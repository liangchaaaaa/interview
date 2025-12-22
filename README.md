# 笔试内容

完善代码，使其可以运行，并实现对商户员工表biz_user进行增删改查操作。

# 题目要求

## 1、对controller层的入参做必要的参数校验

### create，要求name不能为空

### delete接口，lids不能为空

### list接口，页大小不能为空，且不能大于100，当前页不能为空，且不能小于0

## 2、功能要求

用mybatis-flex实现下面的功能
create接口，create_timer 字段，默认为当前时间
update接口，update_timer 字段，默认为当前时间
delete接口，要求逻辑删除，不能用物理删除

mybatis-flex官网：https://mybatis-flex.com

## 3、实现redis缓存功能

get接口，要求用jetcache，本地缓存和分布式缓存都实现。

```sql
DROP TABLE IF EXISTS biz_user;
CREATE TABLE biz_user
(
    pid          BIGINT NOT NULL AUTO_INCREMENT  COMMENT '物理编号;',
    mid          BIGINT NOT NULL COMMENT '商户号;',
    sid          BIGINT COMMENT '门店号;',
    id           VARCHAR(255) COMMENT '逻辑编号;',
    lid          BIGINT NOT NULL COMMENT 'lmn内部编号',
    name         VARCHAR(255) COMMENT '系统用户名称',
    salt         VARCHAR(255) COMMENT '盐',
    pwd          VARCHAR(255) COMMENT '密码',
    email        VARCHAR(255) COMMENT '电子邮箱',
    phone        VARCHAR(32) COMMENT '手机号',
    openid       VARCHAR(255) COMMENT 'openid',
    unionid      VARCHAR(255) COMMENT 'unionid',
    avatarurl    VARCHAR(255) COMMENT '微信头像',
    nickname     VARCHAR(255) COMMENT '微信昵称',
    created_by   VARCHAR(90) COMMENT '创建者',
    created_time DATETIME COMMENT '创建时间',
    updated_by   VARCHAR(90) COMMENT '更新者',
    updated_time DATETIME COMMENT '更新时间',
    deleted      INT8 COMMENT '是否删除',
    PRIMARY KEY (pid)
) COMMENT = '商户员工';
```