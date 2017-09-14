package org.goiot.utils.eval.functor;

public class FuncAsin extends FunctionBase {

	public FuncAsin() {
		super("asin", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.asin(((Number) operands[0]).doubleValue());
	}

}
