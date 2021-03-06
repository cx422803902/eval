package org.goiot.utils.eval.handler;

import org.goiot.utils.eval.Operator;

public class BitwiseNegationHandler implements IOperatorHandler {

	@Override
	public Object operate(Operator oper, Object[] operands) {
		return ~((Number) operands[0]).longValue();
	}
}
