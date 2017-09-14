package org.goiot.utils.eval.functor;

public class FuncTest1 extends FunctionBase {

	public FuncTest1(String name) {
		// super(name, 3);
		super(name, 1);
	}

	@Override
	public Object operate(Object[] operands) {
		// if (operands.length != getDimension()) {
		// return 0;
		// }
		// double a = 1.0;
		// for (Object object : operands) {
		// a *= ((Number) object).doubleValue();
		// }
		// return a;
		Object obj = operands[0];
		obj.toString();
		return 1;
	}

}
