package org.goiot.utils.eval;

import org.goiot.utils.eval.functor.FunctionBase;

/***
 * 
 * @author chenxing
 * @category 这个类是解析表达式模块的入口
 */
public class TExprParser extends ExprParser {
	private TreeNode root;
	private final String expr;

	public TExprParser(String expr) {
		this.expr = expr;
	}
	
	public String getExpression(){
		return expr;
	}

	public synchronized void init() {
		if (root == null) {
			root = buildTree(expr);
		}
	}

	public void addFunction(FunctionBase functor) {
		regFunction(functor);
	}

	public static void regGlobalVariable(String name, double value) {
		TableManager.regGlobalVariable(name, value);
	}

	public Object calculate(ITExprParams params) {
		if (root == null) {
			init();
		}
		return root.computeSubTree(getTreeData(), params);
	}
}
