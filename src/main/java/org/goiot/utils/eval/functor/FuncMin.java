package org.goiot.utils.eval.functor;

public class FuncMin extends FunctionBase {

	public FuncMin() {
		super("min", 2);
	}

	@Override
	public Object operate(Object[] operands) {
		return Math.min(((Number) operands[0]).doubleValue(), ((Number) operands[1]).doubleValue());
	}

}
