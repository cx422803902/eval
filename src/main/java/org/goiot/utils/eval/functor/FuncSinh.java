package org.goiot.utils.eval.functor;

public class FuncSinh extends FunctionBase {

	public FuncSinh() {
		super("sinh", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.sinh(((Number) operands[0]).doubleValue());
	}

}
