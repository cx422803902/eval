package org.goiot.utils.eval.handler;

import org.goiot.utils.eval.Operator;

public class LessThanOrEqualToHandler implements IOperatorHandler {

	@Override
	public Object operate(Operator oper, Object[] operands) {
		return ((Number) operands[0]).doubleValue() <= ((Number) operands[1]).doubleValue() ? 1 : 0;
	}

}
