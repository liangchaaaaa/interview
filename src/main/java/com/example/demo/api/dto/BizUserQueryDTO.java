package com.example.demo.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/** 分页查询商户员工 */
@Data
public class BizUserQueryDTO implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  // TODO 补齐代码

  @Schema(title = "第几页")
  public Long current;

  @Schema(title = "页大小")
  public Long pageSize;
}
