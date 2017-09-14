package org.goiot.utils.eval.functor;

public class FuncSqrt extends FunctionBase {

	public FuncSqrt() {
		super("sqrt", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.sqrt(((Number) operands[0]).doubleValue());
	}

}
