package org.goiot.utils.eval;

import java.util.ArrayList;

public abstract class OperationSymbol extends Symbol {

	// 操作数个数
	private final int operandNumber;
	private final boolean enableVariableArgs;
	
	protected OperationSymbol(int operandNumber, boolean enableVariableArgs) {
		this.operandNumber = operandNumber;
		this.enableVariableArgs = enableVariableArgs;
	}

	public int getDimension() {
		return operandNumber;
	}

	public boolean enableVariableArgs() {
		return enableVariableArgs;
	}

	public abstract Object operate(TreeData treeData, ArrayList<TreeNode> children, ITExprParams params);
	
}
