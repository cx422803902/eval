package org.goiot.utils.eval.functor;

import java.math.BigDecimal;

public class FuncMax extends FunctionBase {

	public FuncMax() {
		super("max", 2);
	}

	@Override
	public Object operate(Object[] operands) {
		return ((BigDecimal)operands[0]).compareTo((BigDecimal)operands[1]) > 0 ? operands[0] : operands[1];
	}

}
