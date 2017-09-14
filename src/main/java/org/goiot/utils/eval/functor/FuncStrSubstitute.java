package org.goiot.utils.eval.functor;

public class FuncStrSubstitute extends FunctionBase {

	public FuncStrSubstitute() {
		super("substitute", 3);
	}

	protected Object operate(Object[] operands) {
		String text = operands[0].toString();
		String oldText = operands[1].toString();
		String newText = operands[2].toString();
		return text.replaceAll(oldText, newText);
	}
}
