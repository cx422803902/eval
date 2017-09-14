package org.goiot.utils.eval.functor;

public class FuncAcos extends FunctionBase {

	public FuncAcos() {
		super("acos", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.acos(((Number) operands[0]).doubleValue());
	}

}
