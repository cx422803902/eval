package org.goiot.utils.eval.handler;

import org.goiot.utils.eval.Operator;

public class LogicalNegationHandler implements IOperatorHandler {

	@Override
	public Object operate(Operator oper, Object[] operands) {
		boolean ret = ((Number) operands[0]).longValue() != 0;
		return !ret ? 1 : 0;
	}

}
