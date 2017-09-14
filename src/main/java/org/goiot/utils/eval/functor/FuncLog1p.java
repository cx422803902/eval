package org.goiot.utils.eval.functor;

public class FuncLog1p extends FunctionBase {

	public FuncLog1p() {
		super("log1p", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.log1p(((Number) operands[0]).doubleValue());
	}

}
