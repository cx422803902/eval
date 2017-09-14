package org.goiot.utils.eval.handler;

import org.goiot.utils.eval.Operator;

import java.math.BigDecimal;

public class LessThanHandler implements IOperatorHandler {

	@Override
	public Object operate(Operator oper, Object[] operands) {
		return ((BigDecimal) operands[0]).compareTo((BigDecimal) operands[1]) < 0 ? 1 : 0;
	}

}
