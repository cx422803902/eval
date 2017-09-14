package org.goiot.utils.eval.functor;

public class FuncMax extends FunctionBase {

	public FuncMax() {
		super("max", 2);
	}

	@Override
	public Object operate(Object[] operands) {
		return Math.max(((Number) operands[0]).doubleValue(), ((Number) operands[1]).doubleValue());
	}

}
