package org.goiot.utils.eval.functor;

public class FuncStrCmp extends FunctionBase {

	public FuncStrCmp() {
		super("strcmp", 2);
	}

	@Override
	protected Object operate(Object[] operands) {
		return operands[0].toString().compareTo(operands[1].toString());
	}
}
