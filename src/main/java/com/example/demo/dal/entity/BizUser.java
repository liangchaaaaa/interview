package com.example.demo.dal.entity;

import com.mybatisflex.annotation.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/** 商户员工 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Accessors(chain = true)
@Table(value = "biz_user")
public class BizUser implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @Id(value = "pid", keyType = KeyType.Auto)
  private Long pid;

  @Column("mid")
  private Long mid;

  @Column("sid")
  private Long sid;

  @Column("id")
  private String id;

  @Column("lid")
  private Long lid;

  @Column("name")
  private String name;

  @Column("salt")
  private String salt;

  @Column("pwd")
  private String pwd;

  @Column("email")
  private String email;

  @Column("phone")
  private String phone;

  @Column("openid")
  private String openid;

  @Column("unionid")
  private String unionid;

  @Column("avatarurl")
  private String avatarurl;

  @Column("nickname")
  private String nickname;

  @Column("created_by")
  private String createdBy;

  @Column(value = "created_time", onInsertValue = "now()")
  private LocalDateTime createdTime;

  @Column("updated_by")
  private String updatedBy;

  @Column(value = "updated_time", onUpdateValue = "now()")
  private LocalDateTime updatedTime;

  @Column(value = "deleted", isLogicDelete = true)
  private Short deleted;
}
