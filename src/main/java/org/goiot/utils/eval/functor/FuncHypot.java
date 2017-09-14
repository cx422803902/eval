package org.goiot.utils.eval.functor;

public class FuncHypot extends FunctionBase {

	public FuncHypot() {
		super("hypot", 2);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.hypot(((Number) operands[0]).doubleValue(), ((Number) operands[1]).doubleValue());
	}

}
