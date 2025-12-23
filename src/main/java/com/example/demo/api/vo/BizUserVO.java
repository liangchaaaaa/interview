/*
 * @Author: liangchaaaaa git
 * @Date: 2025-12-22 21:58:16
 * @LastEditors: liangchaaaaa git
 * @LastEditTime: 2025-12-23 13:19:32
 * @FilePath: \origin\src\main\java\com\example\demo\api\vo\BizUserVO.java
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
package com.example.demo.api.vo;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/** 商户员工 */
@Data
public class BizUserVO implements Serializable {
    private static final long serialVersionUID = 1L;
    

    private Long pid;
    private String name;
    private String email;
    private String phone;
    private String avatarurl;
    private String nickname;
    private Long mid;
    private Long sid;
    private String id;
}
