package org.goiot.utils.eval.handler;

import org.goiot.utils.eval.Operator;

import java.math.BigDecimal;

public class MultiplicationHandler implements IOperatorHandler {

	@Override
	public Object operate(Operator oper, Object[] operands) {
		return ((BigDecimal) operands[0]).multiply((BigDecimal) operands[1]);
	}
}
