package com.example.demo.api.dto;

import java.io.Serial;
import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/** 新增商户员工 */
@Data
public class BizUserCreateDTO implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  // TODO 补齐代码
  public Long lid;

  @NotBlank(message = "name不能为空")
  public String name;

}
