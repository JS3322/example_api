package com.cleancode.example_api.CustomUtility.CustomConvert;

import java.math.BigDecimal;

public class ConversionUtil {

    /**
     * convertToDouble : double 타입으로 형변환
     * @param value
     * @return
     */
    public static double convertToDouble(Object value) {
        if (value == null) return 0.0;
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        try {
            return Double.parseDouble(value.toString());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /**
     * toBigDecimal로 타입으로 형변환
     * @param value
     * @return
     */
    public static BigDecimal toBigDecimal(Object value) {
        // If the value is already a BigDecimal, return it
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }

        // If the value is null, return BigDecimal representation of 0.0
        if (value == null) {
            return BigDecimal.ZERO;
        }

        try {
            // Try to convert the value's string representation to BigDecimal
            return new BigDecimal(value.toString());
        } catch (NumberFormatException e) {
            // If the conversion fails, return BigDecimal representation of 0.0
            return BigDecimal.ZERO;
        }
    }
}
