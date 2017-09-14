package org.goiot.utils.eval;

import java.util.ArrayList;

final class ClassMemberOperator extends Operator {

	public ClassMemberOperator() {
		super("!类成员", ".", 1, 2);
	}

	private ITExprParams calcLeftOperand(TreeData treeData, TreeNode leftNode, ITExprParams params) {
		return (ITExprParams) leftNode.computeSubTree(treeData, params);
	}

	@Override
	public Object operate(TreeData treeData, ArrayList<TreeNode> children, ITExprParams params) {
		if (children.size() != getDimension()) {
			ExprException.throwException("运算符‘%s’(%s)的操作数个数不匹配!", getEscape(), getIdentifier());
		}
			
		TreeNode leftNode = children.get(1);
		String right = children.get(0).getToken();
		Object ret = calcLeftOperand(treeData, leftNode, params).get(right);
		return ret;
	}
	
}
