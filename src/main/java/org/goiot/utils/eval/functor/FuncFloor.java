package org.goiot.utils.eval.functor;

public class FuncFloor extends FunctionBase {

	public FuncFloor() {
		super("floor", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.floor(((Number) operands[0]).doubleValue());
	}

}
