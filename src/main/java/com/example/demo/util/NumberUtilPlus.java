package com.example.demo.util;

import cn.hutool.core.util.NumberUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;

/**
 * @author 柠檬树微服务平台
 */
public class NumberUtilPlus {
  /**
   * 取绝对值
   *
   * @param number .
   * @return .
   */
  public static BigDecimal abs(BigDecimal number) {
    return Optional.ofNullable(number).map(BigDecimal::abs).orElse(BigDecimal.ZERO);
  }

  /**
   * 取绝对值
   *
   * @param number .
   * @return .
   */
  public static BigDecimal negate(BigDecimal number) {
    return Optional.ofNullable(number).map(BigDecimal::negate).orElse(BigDecimal.ZERO);
  }

  /**
   * 取负数
   *
   * @param number .
   * @return .
   */
  public static BigDecimal absNegate(BigDecimal number) {
    return abs(number).negate();
  }

  /**
   * 10进制转62进制
   *
   * @param num
   * @return
   */
  public static String encode10To62(long num) {
    // StringBuffer线程安全，StringBuilder线程不安全
    StringBuilder sb = new StringBuilder();
    do {
      int i = (int) (num % 62);
      sb.append(CHARS.charAt(i));
      num = num / 62;
    } while (num > 0);
    return sb.reverse().toString();
  }

  /**
   * 62进制转10进制
   *
   * @param val
   * @return
   */
  public static long decode62To10(String val) {
    if (val == null) {
      throw new NumberFormatException("null");
    }
    if (!val.matches(REGEX)) {
      throw new IllegalArgumentException("this is an Invalid parameter:" + val);
    }
    String tmp = val.replace("^0*", "");

    long result = 0;
    int index = 0;
    int length = tmp.length();
    for (int i = 0; i < length; i++) {
      index = CHARS.indexOf(tmp.charAt(i));
      result += (long) (index * Math.pow(SCALE, length - i - 1));
    }
    return result;
  }

  public static String incr(String maxId, int pos) {
    return incr(maxId, pos, false);
  }

  public static String incr(String maxId, int pos, boolean onlyNum) {
    String prefix = maxId.substring(0, pos);
    String tmp = maxId.substring(pos);
    StringBuilder suffix = new StringBuilder(tmp);
    int i;
    for (i = tmp.length() - 1; i >= 0; i--) {
      char c = tmp.charAt(i);
      boolean isNum = (c >= '0' && c < '9');
      boolean isChar = (c >= 'A' && c < 'Z');
      if (isNum || isChar) {
        suffix.replace(i, i + 1, String.valueOf((char) (c + 1)));
        break;
      } else if (c == '9') {
        if (onlyNum) {
          suffix.replace(i, i + 1, "0");
        } else {
          suffix.replace(i, i + 1, "A");
          break;
        }
        //        suffix.append('A');
      } else if (c == 'Z') {
        suffix.replace(i, i + 1, "0");
        //        suffix.append('0');
      } else {
        // 进位
        //        suffix.append(c);
      }
    }
    //    suffix.append(tmp, 0, i);
    return String.format("%s%s", prefix, suffix);
  }

  public static void checkId(String id) {
    for (int i = 0; i < id.length(); i++) {
      char c = id.charAt(i);
      if (c >= '0' && c <= '9') {
        continue;
      }
      if (c >= 'A' && c <= 'Z') {
        continue;
      }
      throw new RuntimeException("编码字符必须是【0-9】或者【A-Z】");
    }
  }

  /**
   * 放大一百倍
   *
   * @param str
   * @return
   */
  public static BigDecimal mul100Times(String str) {
    return mul100Times(new BigDecimal(str));
  }

  /**
   * 放大一百倍
   *
   * @param val
   * @return
   */
  public static BigDecimal mul100Times(BigDecimal val) {
    return val.multiply(ONE_HUNDRED);
  }

  /**
   * 缩小100倍
   *
   * @param str
   * @return
   */
  public static BigDecimal div100Times(String str) {
    return div100Times(new BigDecimal(str));
  }

  public static BigDecimal mulPercent(BigDecimal v1, BigDecimal v2) {
    return mul100Times(NumberUtilPlus.divZero(v1, v2)).setScale(2, RoundingMode.HALF_UP);
  }

  public static BigDecimal divPercent(BigDecimal v1, BigDecimal v2) {
    return div100Times(NumberUtil.mul(v1, v2)).setScale(2, RoundingMode.HALF_UP);
  }

  /**
   * 缩小一百倍
   *
   * @param val
   * @return
   */
  public static BigDecimal div100Times(BigDecimal val) {
    return val.multiply(HUNDREDTH);
  }

  public static BigDecimal divZero(BigDecimal v1, BigDecimal v2) {
    if (zero(v1) || zero(v2)) {
      return BigDecimal.ZERO;
    }
    return NumberUtil.div(v1, v2);
  }

  public static boolean ne(BigDecimal v1, BigDecimal v2) {
    v1 = NullSafeUtils.nullSafe(v1);
    v2 = NullSafeUtils.nullSafe(v2);
    return v1.compareTo(v2) != 0;
  }

  public static boolean neZero(BigDecimal val) {
    if (Objects.isNull(val)) {
      return false;
    }
    return val.compareTo(BigDecimal.ZERO) != 0;
  }

  public static boolean zero(BigDecimal val) {
    if (Objects.isNull(val)) {
      return true;
    }
    return val.compareTo(BigDecimal.ZERO) == 0;
  }

  public static boolean isInteger(BigDecimal val) {
    if (zero(val)) {
      return true;
    }
    return val.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0;
  }

  public static boolean isLtTwoScale(BigDecimal val) {
    if (zero(val)) {
      return false;
    }
    String valStr = NullSafeUtils.formatZero(val);
    val = new BigDecimal(valStr);
    return val.scale() < 2;
  }

  public static boolean gtZero(BigDecimal val) {
    if (Objects.isNull(val)) {
      return false;
    }
    return val.compareTo(BigDecimal.ZERO) > 0;
  }

  public static boolean geZero(BigDecimal val) {
    if (Objects.isNull(val)) {
      return true;
    }
    return val.compareTo(BigDecimal.ZERO) >= 0;
  }

  public static boolean ltZero(BigDecimal val) {
    if (Objects.isNull(val)) {
      return false;
    }
    return val.compareTo(BigDecimal.ZERO) < 0;
  }

  public static boolean leZero(BigDecimal val) {
    if (Objects.isNull(val)) {
      return true;
    }
    return val.compareTo(BigDecimal.ZERO) <= 0;
  }

  /** 62个字符 */
  private static final String CHARS =
      "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

  private static final String REGEX = "^[0-9a-zA-Z]+$";

  /** 进制转换比率 */
  private static final int SCALE = 62;

  /** 100 */
  public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

  /** 0.01 */
  public static final BigDecimal HUNDREDTH = new BigDecimal("0.01");
}
