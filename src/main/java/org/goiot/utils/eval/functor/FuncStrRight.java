package org.goiot.utils.eval.functor;

public class FuncStrRight extends FunctionBase {

	public FuncStrRight() {
		super("right", 2);
	}

	protected Object operate(Object[] operands) {
		String text = operands[0].toString();
		int number = ((Number) operands[1]).intValue();
		return text.substring(text.length() - number);
	}
}
