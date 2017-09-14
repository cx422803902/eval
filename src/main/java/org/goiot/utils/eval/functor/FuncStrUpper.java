package org.goiot.utils.eval.functor;

public class FuncStrUpper extends FunctionBase {

	public FuncStrUpper() {
		super("upper", 1);
	}

	protected Object operate(Object[] operands) {
		return operands[0].toString().toUpperCase();
	}
}
