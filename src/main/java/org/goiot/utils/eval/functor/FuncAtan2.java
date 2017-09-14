package org.goiot.utils.eval.functor;

public class FuncAtan2 extends FunctionBase {

	public FuncAtan2() {
		super("atan2", 2);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.atan2(((Number) operands[0]).doubleValue(), ((Number) operands[1]).doubleValue());
	}

}
