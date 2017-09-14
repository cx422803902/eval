package org.goiot.utils.eval.functor;

public class FuncStrReplace extends FunctionBase {

	public FuncStrReplace() {
		super("replace", 4);
	}

	protected Object operate(Object[] operands) {
		String text = operands[0].toString();
		int startIndex = ((Number) operands[1]).intValue() - 1;
		int num = ((Number) operands[2]).intValue();
		String newText = operands[3].toString();
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(text.substring(0, startIndex));
		sBuilder.append(newText);
		sBuilder.append(text.substring(startIndex + num));
		return sBuilder.toString();
	}
}
