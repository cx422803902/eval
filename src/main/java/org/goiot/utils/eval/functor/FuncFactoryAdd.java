package org.goiot.utils.eval.functor;

public class FuncFactoryAdd extends FunctionBase {

	public FuncFactoryAdd(String name) {
		super(name, 2, true);
	}

	@Override
	public Object operate(Object[] operands) {
		double ret = 0d;
		for (Object double1 : operands) {
			ret += ((Number) double1).doubleValue();
		}
		return ret;
	}

}
