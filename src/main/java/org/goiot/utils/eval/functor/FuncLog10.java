package org.goiot.utils.eval.functor;

public class FuncLog10 extends FunctionBase {

	public FuncLog10() {
		super("log10", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return StrictMath.log10(((Number) operands[0]).doubleValue());
	}

}
