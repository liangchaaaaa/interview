package com.example.demo.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 查询指定的商户员工 */
@Data
public class BizUserGetDTO implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @NotNull(message = "lid 不能为空")
  private Long lid;
}
