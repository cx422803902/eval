package org.goiot.utils.eval.functor;

import java.math.BigDecimal;

/**
 * Created by chenxing on 2017/9/15.
 */
public class FuncBetween extends FunctionBase {
    //between(a,b,c)
    //if a is in [b,c)
    public FuncBetween() {
        super("between", 3);
    }

    @Override
    protected Object operate(Object[] operands) {
        return ((((BigDecimal)operands[0]).compareTo((BigDecimal)operands[1]) >= 0)
                && (((BigDecimal)operands[0]).compareTo((BigDecimal)operands[2]) < 0)) ? 1 : 0;
    }
}
