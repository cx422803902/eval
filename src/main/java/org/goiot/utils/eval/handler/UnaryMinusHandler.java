package org.goiot.utils.eval.handler;

import org.goiot.utils.eval.Operator;

import java.math.BigDecimal;

public class UnaryMinusHandler implements IOperatorHandler {
	private static final BigDecimal Unary = BigDecimal.valueOf(-1L);
	@Override
	public Object operate(Operator oper, Object[] operands) {
		return ((BigDecimal) operands[0]).multiply(Unary);
	}
}
