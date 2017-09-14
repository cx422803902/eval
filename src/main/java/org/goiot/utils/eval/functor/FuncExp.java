package org.goiot.utils.eval.functor;

public class FuncExp extends FunctionBase {

	public FuncExp() {
		super("exp", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.exp(((Number) operands[0]).doubleValue());
	}

}
