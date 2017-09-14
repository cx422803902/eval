package org.goiot.utils.eval.functor;

public class FuncCeil extends FunctionBase {

	public FuncCeil() {
		super("ceil", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.ceil(((Number) operands[0]).doubleValue());
	}

}
