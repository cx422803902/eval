package org.goiot.utils.eval.functor;

import java.math.BigDecimal;

public class FuncSqrt extends FunctionBase {

	public FuncSqrt() {
		super("sqrt", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return new BigDecimal(StrictMath.sqrt(((BigDecimal) operands[0]).doubleValue()));
	}

}
