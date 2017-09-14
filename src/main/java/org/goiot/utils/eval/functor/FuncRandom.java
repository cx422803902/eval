package org.goiot.utils.eval.functor;

import java.math.BigDecimal;

public class FuncRandom extends FunctionBase {

	public FuncRandom() {
		super("random", 0);
	}

	@Override
	public Object operate(Object[] operands) {
		return new BigDecimal(Math.random());
	}

}
