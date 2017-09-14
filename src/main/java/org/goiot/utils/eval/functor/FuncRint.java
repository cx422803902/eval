package org.goiot.utils.eval.functor;

public class FuncRint extends FunctionBase {

	public FuncRint() {
		super("rint", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.rint(((Number) operands[0]).doubleValue());
	}

}
