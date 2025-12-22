package com.example.demo.api.dto;

import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 查询指定的商户员工 */
@Data
public class BizUserGetDTO implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  // TODO 补齐代码
  @NotNull(message = "lid 不能为空")
  public Long lid;
}
