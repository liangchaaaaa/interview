package com.example.demo.util;

import com.mybatisflex.core.paginate.Page;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

/** service增强类 */
public interface BaseServicePlus {

  default <T extends Serializable> R<T> toResult(T data) {
    return R.data(data);
  }

  default <T extends Serializable> R<List<T>> toResult(Page<T> page) {
    R<List<T>> result = R.dataByZip(page.getRecords());
    fillPage(page, result);
    return result;
  }

  default <DO extends Serializable, VO extends Serializable> R<VO> toResult(
      DO entity, Function<DO, VO> function) {
    return R.data(function.apply(entity));
  }

  default <DO extends Serializable, VO extends Serializable> R<List<VO>> toResult(
      Page<DO> page, Function<List<DO>, List<VO>> function) {
    R<List<VO>> result = R.dataByZip(function.apply(page.getRecords()));
    fillPage(page, result);
    return result;
  }

  default <DO extends Serializable, VO extends Serializable> R<List<VO>> toResult(
      List<DO> data, Function<List<DO>, List<VO>> function) {
    return R.dataByZip(function.apply(data));
  }

  default <V, D extends Serializable> R<List<D>> toResult(List<D> records, Page<V> iPage) {
    R<List<D>> result = R.dataByZip(records);
    fillPage(iPage, result);
    return result;
  }

  /** 填充页面数据 */
  private static void fillPage(Page<?> page, R<?> result) {
    result.setCurrent(page.getPageNumber());
    result.setPages(page.getTotalPage());
    result.setPageSize(result.getPageSize());
    result.setTotal(page.getTotalRow());
  }
}
