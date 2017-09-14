package org.goiot.utils.eval.functor;

public class FuncSin extends FunctionBase {

	public FuncSin() {
		super("sin", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.sin(((Number) operands[0]).doubleValue());
	}

}
