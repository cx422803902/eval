package org.goiot.utils.eval.functor;

public class FuncStrValue extends FunctionBase {

	public FuncStrValue() {
		super("value", 1);
	}

	protected Object operate(Object[] operands) {
		return Double.valueOf(operands[0].toString());
	}
}
