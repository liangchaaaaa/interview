package com.example.demo.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import lombok.*;

/**
 * 对Ajax请求返回Json格式数据的简易封装 <br>
 * 所有预留字段：<br>
 * code=状态码 <br>
 * msg=描述信息 <br>
 * data=携带对象 <br>
 *
 * @author kong
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class R<T> implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  @Schema(title = "业务上的请求是否成功")
  @Builder.Default
  private boolean success = true;

  @Schema(title = "是否进行数据同步至线下")
  @Builder.Default
  private boolean sync = false;

  @Schema(title = "业务约定的错误码")
  private String errorCode;

  @Schema(title = "业务上的错误信息")
  private String errorMessage;

  @Schema(title = "业务上的分组信息")
  private String errorTitle;

  @Schema(title = "业务上的分组错误信息")
  private Map<String, List<String>> errorMap;

  @Schema(title = "业务上的成功信息")
  private String successMessage;

  @Schema(title = "数据")
  private T data;

  @Schema(title = "页大小")
  private Long pageSize;

  @Schema(title = "总记录数")
  private Long total;

  @Schema(title = "总页数")
  private Long pages;

  @Schema(title = "当前页")
  private Long current;

  @Schema(title = "数据是否被压缩")
  private Boolean compressed;

  @Schema(title = "返回码，用于兼容老版本")
  @Builder.Default
  private Integer code = CODE_OK;

  @Schema(title = "返回码，用于兼容老版本")
  @Builder.Default
  private String status = STATUS_OK;

  public static final int CODE_OK = 0;
  public static final int CODE_ERROR = -1;

  public static final String STATUS_OK = "ok";
  public static final String STATUS_ERROR = "error";

  // ============================  构建  ==================================

  public static <T> R<T> ok() {
    return R.<T>builder().build();
  }

  public static <T> R<T> ok(String msg) {
    return R.<T>builder().successMessage(msg).build();
  }

  public static <T extends Serializable> R<List<T>> list(List<T> data) {
    return R.<List<T>>builder().data(data).build();
  }

  public static <K extends Serializable, V extends Serializable> R<Map<K, V>> map(Map<K, V> data) {
    return R.<Map<K, V>>builder().data(data).build();
  }

  public static <T extends Serializable> R<List<T>> dataByZip(List<T> data) {
    return R.<List<T>>builder().data(data).compressed(true).build();
  }

  public static <T extends Serializable> R<List<T>> empty() {
    return R.<List<T>>builder()
        .data(Collections.emptyList())
        .compressed(true)
        .current(1L)
        .pageSize(50L)
        .total(0L)
        .pages(0L)
        .build();
  }

  /** 单节点数据，作为list返回，并压缩 */
  public static <T extends Serializable> R<List<T>> dataToListByZip(T data) {
    final List<T> list = Collections.singletonList(data);
    return R.<List<T>>builder().data(list).compressed(true).build();
  }

  /** 单节点数据，作为list返回，不压缩 */
  public static <T extends Serializable> R<List<T>> dataToList(T data) {
    final List<T> list = Collections.singletonList(data);
    return R.<List<T>>builder().data(list).build();
  }

  public static <T extends Serializable> R<T> data(T data) {
    return R.<T>builder().data(data).build();
  }

  public static <T> R<T> data(T data) {
    return R.<T>builder().data(data).build();
  }

  public static <T> R<T> error() {
    return R.<T>builder().success(false).code(CODE_ERROR).status(STATUS_ERROR).build();
  }

  public static <T> R<T> error(String msg) {
    return R.<T>builder()
        .success(false)
        .code(CODE_ERROR)
        .status(STATUS_ERROR)
        .errorMessage(msg)
        .build();
  }

  public static <T> R<T> error(String errorTitle, Map<String, List<String>> errorMap) {
    return R.<T>builder()
        .success(false)
        .code(CODE_ERROR)
        .status(STATUS_ERROR)
        .errorTitle(errorTitle)
        .errorMap(errorMap)
        .build();
  }

  public JSONObject toJsonObject() {
    return JSON.parseObject(JSON.toJSONString(this));
  }

  public static void unzip(JSONObject json) {
    //    处理有压缩的情况
    if (json.containsKey("columns")) {
      JSONArray columns = json.getJSONArray("columns");
      Map<Integer, String> columnsMap = new HashMap<>(columns.size());
      for (int i = 0; i < columns.size(); i++) {
        columnsMap.put(i, columns.getString(i));
      }

      JSONArray data = json.getJSONArray("data");
      JSONArray newData = new JSONArray(data.size());
      json.put("data", newData);
      for (int rowIdx = 0; rowIdx < data.size(); rowIdx++) {
        JSONObject rowObj = new JSONObject();
        newData.add(rowObj);
        JSONArray rowArray = data.getJSONArray(rowIdx);
        for (int colIdx = 0, len = rowArray.size(); colIdx < len; colIdx++) {
          rowObj.put(columnsMap.get(colIdx), rowArray.getString(colIdx));
        }
      }
      json.remove("columns");
    }
  }

  public static <T> R<T> fromJson(JSONObject json, TypeReference<R<T>> typeReference) {
    unzip(json);
    return json.to(typeReference);
  }

  public static boolean isSuccess(JSONObject json) {
    Integer _code = json.getInteger("code");
    String _status = json.getString("status");
    Boolean success = json.getBoolean("success");
    if (_code != null) {
      return _code.equals(CODE_OK);
    }
    if (_status != null) {
      return _status.equals(STATUS_OK);
    }
    return Objects.equals(success, Boolean.TRUE);
  }
}
