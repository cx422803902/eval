package org.goiot.utils.eval.functor;

public class FuncStrLen extends FunctionBase {

	public FuncStrLen() {
		super("len", 1);
	}

	protected Object operate(Object[] operands) {
		return operands[0].toString().length();
	}
}
