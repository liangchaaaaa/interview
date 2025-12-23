package com.example.demo.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/** 删除商户员工 */
@Data
public class BizUserDeleteDTO implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @NotEmpty(message = "lids不能为空")
  private List<Long> lids;
}
