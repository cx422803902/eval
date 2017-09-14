package org.goiot.utils.eval.functor;

public class FuncAtan extends FunctionBase {

	public FuncAtan() {
		super("atan", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.atan(((Number) operands[0]).doubleValue());
	}
}
