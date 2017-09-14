package org.goiot.utils.eval.functor;

public class FuncUlp extends FunctionBase {

	public FuncUlp() {
		super("ulp", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return Math.ulp(((Number) operands[0]).doubleValue());
	}

}
