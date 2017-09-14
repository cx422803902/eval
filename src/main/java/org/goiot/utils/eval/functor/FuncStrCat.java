/**
 * 
 */
package org.goiot.utils.eval.functor;

/**
 * @author chenxing
 *
 */
public class FuncStrCat extends FunctionBase {

	public FuncStrCat() {
		super("concatenate", 2, true);
	}

	@Override
	protected Object operate(Object[] operands) {
		String ret = "";
		for (Object object : operands) {
			ret = ret.concat(object.toString());
		}
		return ret;
	}
}
