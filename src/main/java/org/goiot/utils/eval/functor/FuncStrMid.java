package org.goiot.utils.eval.functor;

public class FuncStrMid extends FunctionBase {

	public FuncStrMid() {
		super("mid", 3);
	}

	protected Object operate(Object[] operands) {
		String text = operands[0].toString();
		int startIndex = ((Number) operands[1]).intValue() - 1;
		int number = ((Number) operands[2]).intValue();
		return text.substring(startIndex, startIndex + number);
	}
}
