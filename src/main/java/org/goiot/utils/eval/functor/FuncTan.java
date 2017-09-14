package org.goiot.utils.eval.functor;

public class FuncTan extends FunctionBase {

	public FuncTan() {
		super("tan", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.tan(((Number) operands[0]).doubleValue());
	}

}
