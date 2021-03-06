package org.goiot.utils.eval.functor;

public class FuncToRadians extends FunctionBase {

	public FuncToRadians() {
		super("toRadians", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return ((Number) operands[0]).doubleValue() / 180.0 * Math.PI;
	}

}
