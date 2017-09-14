package org.goiot.utils.eval;

import java.util.ArrayList;

import org.goiot.utils.eval.handler.IOperatorHandler;

public class Operator extends OperationSymbol {
	// 优先级越高，数值越小
	private int priority;

	private IOperatorHandler handler;
	// 操作符是否可被重写处理器
	private boolean isOverridable = false;

	public Operator(String identifier, String escape, int priority, int operandNumber) {
		this(identifier, escape, priority, operandNumber, false, null);
	}

	public Operator(String identifier, String escape, int priority, int operandNumber, IOperatorHandler handler) {
		this(identifier, escape, priority, operandNumber, false, handler);
	}

	public Operator(String identifier, String escape, int priority, int operandNumber, boolean enableVariableOperands,
			IOperatorHandler handler) {
		super(operandNumber, enableVariableOperands);
		setIdentifier(identifier);
		setEscape(escape);
		this.priority = priority;
		this.handler = handler;
	}

	public int getPriority() {
		return priority;
	}

	public IOperatorHandler getHandler() {
		return handler;
	}

	public void setHandler(IOperatorHandler handler) {
		if (isOverridable) {
			this.handler = handler;
		} else {
			ExprException.throwException("操作符‘%s’不允许重写处理器!", getIdentifier());
		}
	}

	public boolean isOverridable() {
		return isOverridable;
	}

	public void setOverridable(boolean isOverridable) {
		this.isOverridable = isOverridable;
	}

	@Override
	public Object operate(TreeData treeData, ArrayList<TreeNode> children, ITExprParams params) {
		int length = children.size();
		Object[] operands = new Object[length];
		for (int i = children.size() - 1; i >= 0; i--) {
			TreeNode node = children.get(i);
			if (node == null) {
				ExprException.throwException("空节点!");
			}
			operands[length - i - 1] = node.computeSubTree(treeData, params);
		}
		return operate(operands);
	}

	protected Object operate(Object[] operands) {
		if (!TableManager.existOperator(getIdentifier())) {
			ExprException.throwException("运算符‘%s’(%s)未定义!", getIdentifier(), getEscape());
		}
		if (operands == null || operands.length != getDimension()) {
			ExprException.throwException("运算符‘%s’(%s)的操作数个数不匹配!", getIdentifier(), getEscape());
		}
		return handler.operate(this, operands);
	}
}
