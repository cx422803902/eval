package org.goiot.utils.eval.handler;

import org.goiot.utils.eval.Operator;

public class BitwiseExclusiveOrHandler implements IOperatorHandler {

	@Override
	public Object operate(Operator oper, Object[] operands) {
		return ((Number) operands[0]).longValue() ^ ((Number) operands[1]).longValue();
	}

}
