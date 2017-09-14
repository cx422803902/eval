package org.goiot.utils.eval.functor;

public class FuncCosh extends FunctionBase {

	public FuncCosh() {
		super("cosh", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.cosh(((Number) operands[0]).doubleValue());
	}

}
