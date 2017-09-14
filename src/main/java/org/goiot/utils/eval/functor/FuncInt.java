package org.goiot.utils.eval.functor;

public class FuncInt extends FunctionBase {

	public FuncInt() {
		super("int", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return ((Number) operands[0]).intValue();
	}

}
