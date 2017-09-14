package org.goiot.utils.eval.functor;

public class FuncStrLeft extends FunctionBase {

	public FuncStrLeft() {
		super("left", 2);
	}

	protected Object operate(Object[] operands) {
		String text = operands[0].toString();
		int number = ((Number) operands[1]).intValue();
		return text.substring(0, number);
	}
}
