package org.goiot.utils.eval.functor;

public class FuncStrLower extends FunctionBase {

	public FuncStrLower() {
		super("lower", 1);
	}

	protected Object operate(Object[] operands) {
		return operands[0].toString().toLowerCase();
	}
}
