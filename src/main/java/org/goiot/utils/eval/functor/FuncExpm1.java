package org.goiot.utils.eval.functor;

public class FuncExpm1 extends FunctionBase {

	public FuncExpm1() {
		super("expm1", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.expm1(((Number) operands[0]).longValue());
	}

}
