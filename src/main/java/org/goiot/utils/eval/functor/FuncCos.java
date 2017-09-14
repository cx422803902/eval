package org.goiot.utils.eval.functor;

public class FuncCos extends FunctionBase {

	public FuncCos() {
		super("cos", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.cos(((Number) operands[0]).doubleValue());
	}

}
