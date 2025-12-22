package com.example.demo.biz.controller;

import com.example.demo.api.dto.*;
import com.example.demo.api.vo.BizUserVO;
import com.example.demo.biz.service.BizUserServicePlus;
import com.example.demo.dal.convert.BizUserConvert;
import com.example.demo.dal.entity.BizUser;
import com.example.demo.util.BaseServicePlus;
import com.example.demo.util.R;
import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/biz_user")
@Tag(name = "商户员工管理")
public class BizUserController implements BaseServicePlus {

  @Operation(summary = "获取商户员工详情")
  @PostMapping(value = "/get")
  public R<BizUserVO> get(@RequestBody BizUserGetDTO request) {
    BizUser entity = service.get(request);
    return toResult(entity, CONVERT::toVO);
  }

  @Operation(summary = "分页查询商户员工")
  @PostMapping(value = "/list")
  public R<List<BizUserVO>> list(@RequestBody BizUserQueryDTO request) {
    Page<BizUser> page = service.list(request);
    return toResult(page, CONVERT::toVOList);
  }

  @Operation(summary = "新增商户员工")
  @PostMapping(value = "/create")
  public R<BizUserVO> create(@RequestBody BizUserCreateDTO request) {
    BizUser entity = service.create(request);
    return toResult(entity, CONVERT::toVO);
  }

  @Operation(summary = "修改商户员工")
  @PostMapping(value = "/update")
  public R<Boolean> update(@RequestBody BizUserUpdateDTO request) {
    service.update(request);
    return toResult(true);
  }

  @Operation(summary = "删除商户员工")
  @PostMapping(value = "/delete")
  public R<Boolean> delete(@RequestBody BizUserDeleteDTO request) {
    service.delete(request);
    return toResult(true);
  }

  @Autowired private BizUserServicePlus service;

  private static final BizUserConvert CONVERT = BizUserConvert.INSTANCE;
}
