package org.goiot.utils.eval.functor;

public class FuncStrT extends FunctionBase {

	public FuncStrT() {
		super("t", 1);
	}

	protected Object operate(Object[] operands) {
		return operands[0].toString();
	}
}
