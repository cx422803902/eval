package org.goiot.utils.eval.handler;

import org.goiot.utils.eval.Operator;

import java.math.BigDecimal;

public class ModulusHandler implements IOperatorHandler {

	@Override
	public Object operate(Operator oper, Object[] operands) {
		return ((BigDecimal) operands[0]).divideAndRemainder((BigDecimal) operands[1])[1];
	}
}
