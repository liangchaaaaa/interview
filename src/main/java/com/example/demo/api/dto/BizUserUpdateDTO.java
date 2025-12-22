/*
 * @Author: liangchaaaaa git
 * @Date: 2025-12-22 21:58:16
 * @LastEditors: liangchaaaaa git
 * @LastEditTime: 2025-12-22 22:53:49
 * @FilePath: \origin\src\main\java\com\example\demo\api\dto\BizUserUpdateDTO.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.example.demo.api.dto;

import java.io.Serial;
import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/** 更新商户员工 */
@Data
public class BizUserUpdateDTO implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  // TODO 补齐代码

  @NotNull(message = "lid 不能为空")
  public Long lid;

  public String name;
}
