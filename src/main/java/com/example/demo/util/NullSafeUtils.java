package com.example.demo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/** 数字工具了类 */
public class NullSafeUtils {
  public static byte nullSafe(Byte v) {
    return Optional.ofNullable(v).orElse((byte) 0);
  }

  public static short nullSafe(Short v) {
    return Optional.ofNullable(v).orElse((short) 0);
  }

  public static int nullSafe(Integer v) {
    return Optional.ofNullable(v).orElse(0);
  }

  public static long nullSafe(Long v) {
    return Optional.ofNullable(v).orElse((long) 0);
  }

  public static float nullSafe(Float v) {
    return Optional.ofNullable(v).orElse(0.0f);
  }

  public static double nullSafe(Double v) {
    return Optional.ofNullable(v).orElse(0.0);
  }

  public static boolean nullSafe(Boolean v) {
    return Optional.ofNullable(v).orElse(false);
  }

  public static String nullSafe(String v) {
    return Optional.ofNullable(v).orElse("");
  }

  public static BigDecimal nullSafe(BigDecimal v) {
    return Optional.ofNullable(v).orElse(BigDecimal.ZERO);
  }

  public static Integer add(Integer v1, Integer v2) {
    return nullSafe(v1) + (nullSafe(v2));
  }

  public static Integer subtract(Integer v1, Integer v2) {
    return nullSafe(v1) - (nullSafe(v2));
  }

  public static Integer multiply(Integer v1, Integer v2) {
    return nullSafe(v1) * (nullSafe(v2));
  }

  public static Integer divide(Integer v1, Integer v2) {
    return nullSafe(v1) / nullSafe(v2);
  }

  public static Long add(Long v1, Long v2) {
    return nullSafe(v1) + nullSafe(v2);
  }

  public static Long subtract(Long v1, Long v2) {
    return nullSafe(v1) - nullSafe(v2);
  }

  public static Long multiply(Long v1, Long v2) {
    return nullSafe(v1) * nullSafe(v2);
  }

  public static Long divide(Long v1, Long v2) {
    return nullSafe(v1) / nullSafe(v2);
  }

  public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
    return nullSafe(v1).add(nullSafe(v2));
  }

  public static BigDecimal subtract(BigDecimal v1, BigDecimal v2) {
    return nullSafe(v1).subtract(nullSafe(v2));
  }

  public static BigDecimal multiply(BigDecimal v1, BigDecimal v2) {
    return nullSafe(v1).multiply(nullSafe(v2));
  }

  public static BigDecimal divide(BigDecimal v1, BigDecimal v2) {
    v2 = nullSafe(v2);
    if (v2.compareTo(BigDecimal.ZERO) == 0) {
      return BigDecimal.ZERO;
    }
    return nullSafe(v1).divide(v2, 10, RoundingMode.HALF_EVEN);
  }

  public static <T> List<T> nullSafe(List<T> v) {
    return v == null ? Collections.emptyList() : v;
  }

  public static <T> Set<T> nullSafe(Set<T> v) {
    return v == null ? Collections.emptySet() : v;
  }

  public static <K, V> Map<K, V> nullSafe(Map<K, V> v) {
    return v == null ? Collections.emptyMap() : v;
  }

  public static <T> Collection<T> nullSafe(Collection<T> v) {
    return v == null ? Collections.emptySet() : v;
  }

  public static int compare(BigDecimal a, BigDecimal b) {
    return NullSafeUtils.nullSafe(a).compareTo(NullSafeUtils.nullSafe(b));
  }

  public static int compare(Integer a, Integer b) {
    return NullSafeUtils.nullSafe(a) - NullSafeUtils.nullSafe(b);
  }

  public static String formatZero(BigDecimal v) {
    return formatZero(v, RoundingMode.HALF_EVEN, 2);
  }

  public static String formatZero(BigDecimal v, RoundingMode roundMode, int round) {
    return nullSafe(v).setScale(round, roundMode).stripTrailingZeros().toPlainString();
  }

  public static Object add(Object v1, Object v2) {
    BigDecimal t1 = BigDecimal.ZERO, t2 = BigDecimal.ZERO;
    if (v1 instanceof BigDecimal) {
      t1 = (BigDecimal) v1;
    } else {
      if (v1 instanceof Integer) {
        t1 = BigDecimal.valueOf((Integer) v1);
      }
      if (v1 instanceof Long) {
        t1 = BigDecimal.valueOf((Long) v1);
      }
    }
    if (v2 instanceof BigDecimal) {
      t2 = (BigDecimal) v2;
    } else {
      if (v2 instanceof Integer) {
        t2 = BigDecimal.valueOf((Integer) v2);
      }
      if (v2 instanceof Long) {
        t2 = BigDecimal.valueOf((Long) v2);
      }
    }
    return NullSafeUtils.add(t1, t2);
  }

  public static BigDecimal toScale2(BigDecimal val) {
    return NullSafeUtils.nullSafe(val).setScale(2, RoundingMode.HALF_EVEN);
  }
}
