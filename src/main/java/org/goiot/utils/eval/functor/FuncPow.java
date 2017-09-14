package org.goiot.utils.eval.functor;

import java.math.BigDecimal;

public class FuncPow extends FunctionBase {

	public FuncPow() {
		super("pow", 2);
	}

	@Override
	public Object operate(Object[] operands) {
		return ((BigDecimal)operands[0]).pow(((BigDecimal) operands[1]).intValue());
	}

}
