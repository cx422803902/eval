package org.goiot.utils.eval.functor;

public class FuncAbs extends FunctionBase {

	public FuncAbs() {
		super("abs", 1);
	}

	@Override
	public Object operate(Object[] operands) {
		return (((Number) operands[0]).doubleValue() <= 0.0D) ? 0.0D - ((Number) operands[0]).doubleValue()
				: operands[0];
	}

}
