package com.example.demo.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/** 分页查询商户员工 */
@Data
public class BizUserQueryDTO implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @Schema(title = "第几页")
  @NotNull(message = "current 不能为空")
  @Min(value = 1, message = "current 不能小于 1")
  private Integer current;

  @Schema(title = "页大小")
  @NotNull(message = "pageSize 不能为空")
  @Min(value = 1, message = "pageSize 最小为 1")
  @Max(value = 100, message = "pageSize 最大为 100")
  private Integer pageSize;
}
