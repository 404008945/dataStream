package utils;

import java.math.BigDecimal;

public  class NumberUtil {

    public static BigDecimal getNumber(Object o) {
        if (o == null) {
            return BigDecimal.ZERO;
        } else if (!(o instanceof BigDecimal)) {
            return new BigDecimal(o.toString());
        } else {
            return (BigDecimal) o;
        }
    }

    public static BigDecimal addNumbers(BigDecimal... numbers) {
        BigDecimal base = BigDecimal.ZERO;
        if (numbers == null) {
            return base;
        }
        for (BigDecimal number : numbers) {
            base = base.add(NumberUtil.getNumber(number));
        }
        return base;
    }
}
