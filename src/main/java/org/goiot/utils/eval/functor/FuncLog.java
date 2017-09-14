package org.goiot.utils.eval.functor;

public class FuncLog extends FunctionBase {

	public FuncLog() {
		super("log", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.log(((Number) operands[0]).doubleValue());
	}

}
