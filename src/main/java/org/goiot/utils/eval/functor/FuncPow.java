package org.goiot.utils.eval.functor;

public class FuncPow extends FunctionBase {

	public FuncPow() {
		super("pow", 2);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.pow(((Number) operands[0]).doubleValue(), ((Number) operands[1]).doubleValue());
	}

}
