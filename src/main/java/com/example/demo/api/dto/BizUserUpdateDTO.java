
package com.example.demo.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 更新商户员工 */
@Data
public class BizUserUpdateDTO implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @Schema(title = "主键")
  @NotNull(message = "lid 不能为空")
  private Long lid;

  @Schema(title = "姓名")
  private String name;
}
