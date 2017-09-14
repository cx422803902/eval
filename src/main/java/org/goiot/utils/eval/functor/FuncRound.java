package org.goiot.utils.eval.functor;

import java.math.BigDecimal;
import java.math.MathContext;

public class FuncRound extends FunctionBase{

	public FuncRound() {
		super("round", 1);
	}
	
	@Override
	protected Object operate(Object[] operands) {
		return ((BigDecimal)operands[0]).round(MathContext.DECIMAL64);
	}
	
}
