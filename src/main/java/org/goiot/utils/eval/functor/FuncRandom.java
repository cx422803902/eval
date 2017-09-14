package org.goiot.utils.eval.functor;

public class FuncRandom extends FunctionBase {

	public FuncRandom() {
		super("random", 0);
	}

	@Override
	public Object operate(Object[] operands) {
		return Math.random();
	}

}
