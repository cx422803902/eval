package org.goiot.utils.eval.handler;

import org.goiot.utils.eval.Operator;

import java.math.BigDecimal;
import java.math.MathContext;

public class DivisionHandler implements IOperatorHandler {

	@Override
	public Object operate(Operator oper, Object[] operands) {
		return ((BigDecimal) operands[0]).divide((BigDecimal) operands[1], MathContext.DECIMAL64);
	}
}
