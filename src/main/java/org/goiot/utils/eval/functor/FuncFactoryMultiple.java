package org.goiot.utils.eval.functor;

public class FuncFactoryMultiple extends FunctionBase {

	public FuncFactoryMultiple(String name) {
		super(name, 2, true);
	}

	@Override
	public Object operate(Object[] operands) {
		double ret = 1d;
		for (Object double1 : operands) {
			ret *= ((Number) double1).doubleValue();
		}
		return ret;
	}

}
