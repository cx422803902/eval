package org.goiot.utils.eval;

import java.math.BigDecimal;
import java.util.ArrayList;

public class TreeNode {
	private final TokenType nodeType;
	private final String token;
	private final OperationSymbol operationSymbol;
	private final ArrayList<TreeNode> children;

	public TreeNode(String token, TreeData treeData) {
		this.token = token;
		this.nodeType = getTokenType(treeData);
		if (nodeType == TokenType.FUNCTION || nodeType == TokenType.LOCALFUNCTION || nodeType == TokenType.OPERATOR) {
			this.operationSymbol = getOperationSymbol(treeData);
			if (this.operationSymbol == null) {
				ExprException.throwException("找不到函数（或操作符）%s,%s", getToken(), getNodeType());
			}
			this.children = new ArrayList<TreeNode>();
		} else {
			this.operationSymbol = null;
			this.children = null;
		}
	}

	private TokenType getTokenType(TreeData treeData) {
		if (treeData.containsFunc(token)) {
			return TokenType.LOCALFUNCTION;
		}
		return TableManager.getTokenType(token);
	}

	private OperationSymbol getOperationSymbol(TreeData treeData) {
		if (treeData.containsFunc(token)) {
			return treeData.getFunction(token);
		}
		return TableManager.getOperationSymbol(token);
	}

	public TokenType getNodeType() {
		return nodeType;
	}

	public String getToken() {
		return token;
	}

	public OperationSymbol getOperationSymbol() {
		return operationSymbol;
	}

	public ArrayList<TreeNode> getChildren() {
		return children;
	}

	public int getCount() {
		int count = 1;
		if (children == null || children.size() == 0) {
			return count;
		}
		for (TreeNode child : children) {
			count += child.getCount();
		}
		return count;
	}

	public Object computeSubTree(TreeData treeData, ITExprParams params) {
		switch (getNodeType()) {
		case FUNCTION:
		case LOCALFUNCTION:
		case OPERATOR:
			return computeOperationSymbol(treeData, params);
		case VARIABLE:
			return TableManager.getGlobalVariable(getToken().substring(1));
		case LOCALVARIABLE:
			return params.get(getToken());
		case STRINGCONST:
			return toStringConst(getToken());
		case CONST:
			return toValue(getToken());
		}
		ExprException.throwException("未计算出节点:%s_%s的值!", getToken(), getNodeType());
		return null;
	}

	private Object computeOperationSymbol(TreeData treeData, ITExprParams params) {
		return operationSymbol.operate(treeData, children, params);
	}

	private String toStringConst(String value) {
		return value.substring(1, value.length() - 1);
	}

	private BigDecimal toValue(String value) {
		try {
			return new BigDecimal(value);
		} catch (NumberFormatException e) {
			ExprException.throwException("常量：%s无法转化成double!", value);
			return BigDecimal.ONE;
		}
	}

}
