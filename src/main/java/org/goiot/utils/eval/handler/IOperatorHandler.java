package org.goiot.utils.eval.handler;

import org.goiot.utils.eval.Operator;

public interface IOperatorHandler {
	public abstract Object operate(Operator oper, Object[] operands);
}
