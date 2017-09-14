package org.goiot.utils.eval.functor;

public class FuncTanh extends FunctionBase {

	public FuncTanh() {
		super("tanh", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.tanh(((Number) operands[0]).doubleValue());
	}

}
