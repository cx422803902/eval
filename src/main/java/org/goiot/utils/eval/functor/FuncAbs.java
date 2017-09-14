package org.goiot.utils.eval.functor;

import java.math.BigDecimal;

public class FuncAbs extends FunctionBase {

	public FuncAbs() {
		super("abs", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return ((BigDecimal) operands[0]).abs();
	}

}
