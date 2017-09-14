package org.goiot.utils.eval.functor;

import java.math.BigDecimal;
import java.util.Random;

public class FuncGaussion extends FunctionBase {
	Random random = new Random();

	public FuncGaussion() {
		super("gaussian", 0);
	}

	@Override
	protected Object operate(Object[] operands) {
		return new BigDecimal(random.nextGaussian());
	}
}
