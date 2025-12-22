/*
 * @Author: liangchaaaaa git
 * @Date: 2025-12-22 21:58:16
 * @LastEditors: liangchaaaaa git
 * @LastEditTime: 2025-12-22 23:15:25
 * @FilePath: \origin\src\main\java\com\example\demo\biz\controller\BizUserController.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
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
import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/biz_user")
@Tag(name = "商户员工管理")
public class BizUserController implements BaseServicePlus {

  @Operation(summary = "获取商户员工详情")
  @PostMapping(value = "/get")
  public R<BizUserVO> get(@Valid @RequestBody BizUserGetDTO request) {
    BizUser entity = service.get(request);
    return toResult(entity, CONVERT::toVO);
  }

  @Operation(summary = "分页查询商户员工")
  @PostMapping(value = "/list")
  public R<List<BizUserVO>> list(@Valid @RequestBody BizUserQueryDTO request) {
    Page<BizUser> page = service.list(request);
    return toResult(page, CONVERT::toVOList);
  }

  @Operation(summary = "新增商户员工")
  @PostMapping(value = "/create")
  public R<BizUserVO> create(@Valid @RequestBody BizUserCreateDTO request) {
    BizUser entity = service.create(request);
    return toResult(entity, CONVERT::toVO);
  }

  @Operation(summary = "修改商户员工")
  @PostMapping(value = "/update")
  public R<Boolean> update(@Valid @RequestBody BizUserUpdateDTO request) {
    service.update(request);
    return toResult(true);
  }

  @Operation(summary = "删除商户员工")
  @PostMapping(value = "/delete")
  public R<Boolean> delete(@Valid @RequestBody BizUserDeleteDTO request) {
    service.delete(request);
    return toResult(true);
  }

  @Autowired private BizUserServicePlus service;

  private static final BizUserConvert CONVERT = BizUserConvert.INSTANCE;
}
