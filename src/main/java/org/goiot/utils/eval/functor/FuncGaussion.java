package org.goiot.utils.eval.functor;

import java.util.Random;

public class FuncGaussion extends FunctionBase {
	Random random = new Random();

	public FuncGaussion() {
		super("gaussian", 0);
	}

	@Override
	protected Object operate(Object[] operands) {
		return random.nextGaussian();
	}
}
