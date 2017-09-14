package org.goiot.utils.eval.functor;

public class FuncRound extends FunctionBase{

	public FuncRound() {
		super("round", 1);
	}
	
	@Override
	protected Object operate(Object[] operands) {
		return StrictMath.round(((Number) operands[0]).doubleValue());
	}
	
}
