package org.goiot.utils.eval.handler;

import org.goiot.utils.eval.Operator;

public class LogicalOrHandler implements IOperatorHandler {

	@Override
	public Object operate(Operator oper, Object[] operands) {
		boolean left = ((Number) operands[0]).longValue() != 0;
		boolean right = ((Number) operands[1]).longValue() != 0;
		return left || right ? 1 : 0;
	}

}
