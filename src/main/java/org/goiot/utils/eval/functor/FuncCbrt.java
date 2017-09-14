package org.goiot.utils.eval.functor;

public class FuncCbrt extends FunctionBase {

	public FuncCbrt() {
		super("cbrt", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.cbrt(((Number) operands[0]).doubleValue());
	}

}
