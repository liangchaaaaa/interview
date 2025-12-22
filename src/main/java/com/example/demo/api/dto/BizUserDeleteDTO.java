package com.example.demo.api.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/** 删除商户员工 */
@Data
public class BizUserDeleteDTO implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  // TODO 补齐代码
  @NotEmpty(message = "lids不能为空")
  public List<Long> lids;
}
