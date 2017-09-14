package org.goiot.utils.eval.functor;

import java.util.ArrayList;

import org.goiot.utils.eval.ExprException;
import org.goiot.utils.eval.ITExprParams;
import org.goiot.utils.eval.OperationSymbol;
import org.goiot.utils.eval.TreeData;
import org.goiot.utils.eval.TreeNode;

public abstract class FunctionBase extends OperationSymbol {
	
	protected FunctionBase(String name, int operandNumber) {
		this(name, operandNumber, false);
	}
	
	protected FunctionBase(String name, int operandNumber, boolean enableVariableArgs) {
		super(operandNumber, enableVariableArgs);
		setIdentifier(name);
		ExprException.throwException(operandNumber < 0, String.format("函数%s参数个数不能为负数！", getIdentifier()));
	}
	
	@Override
	public Object operate(TreeData treeData, ArrayList<TreeNode> children, ITExprParams params) {
		checkArgsNum(children);
		int length = children.size();
		Object[] operands = new Object[length];
		for (int i = children.size() - 1; i >= 0; i--) {
			TreeNode node = children.get(i);
			operands[length - i - 1] = node.computeSubTree(treeData, params);
		}
		return operate(operands);
	}

	protected void checkArgsNum(ArrayList<TreeNode> children) {
		int argsNum = children == null ? 0 : children.size();
		if (argsNum < getDimension() || (argsNum > getDimension() && !enableVariableArgs())) {
			ExprException.throwException("传参个数不匹配！函数%s的申明参数个数为%d，实际传参个数为%d", getIdentifier(), getDimension(), argsNum);
		}
	}
	
	protected Object operate(Object[] operands) {
		ExprException.throwException("子类未重写本方法");
		return null;
	}

	// private String clazz;
	// private boolean isStatic;
	//
	// private Method method;
	// private Class<?> thisType;
	// private Class<?>[] paramTypes;

	// public FunctionBase(String identifier, String clazz, Class<?>[]
	// paramTypes,
	// boolean isStatic) {
	// setIdentifier(identifier);
	// this.clazz = clazz;
	// this.isStatic = isStatic;
	// this.paramTypes = paramTypes;
	// Init();
	// }
	//
	// private void Init() {
	// thisType = null;
	// try {
	// thisType = Class.forName(clazz);
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// try {
	// method = thisType.getDeclaredMethod(getIdentifier(), paramTypes);
	// } catch (SecurityException e) {
	// e.printStackTrace();
	// } catch (NoSuchMethodException e) {
	// e.printStackTrace();
	// } catch (IllegalArgumentException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// private Object[] getParamObjects() throws ExpressionException {
	// Object[] ret = null;
	// if (getOperands() != null && getOperands().size() > 0) {
	// ret = new Object[getOperands().size()];
	// for (int i = 0; i < getOperands().size(); i++) {
	// if ((paramTypes.length == getOperands().size() && paramTypes[i] !=
	// getOperands()
	// .get(i).getValue().getClass())
	// || paramTypes[0] != Array.newInstance(
	// getOperands().get(i).getValue().getClass(), 0)
	// .getClass()) {
	// String exString = String.format("计算时函数%s参数%s的类型错误!",
	// getIdentifier(), i);
	// throw new ExpressionException(exString);
	// }
	// ret[i] = getOperands().get(i).getValue();
	// }
	// }
	// return ret;
	// }
	//
	// @Override
	// public Variable operate() throws ExpressionException {
	// Object retObject = null;
	// Variable retVariable = null;
	// try {
	// Object[] oParams = getParamObjects();
	// if (isStatic) {
	// retObject = method.invoke(null, oParams);
	// } else {
	// retObject = method.invoke(thisType.newInstance(), oParams);
	// }
	// } catch (IllegalArgumentException e) {
	// e.printStackTrace();
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// } catch (InvocationTargetException e) {
	// e.printStackTrace();
	// } catch (InstantiationException e) {
	// e.printStackTrace();
	// }
	// if (retObject != null) {
	// retVariable = new Variable(retObject);
	// }
	// return retVariable;
	// }
}
