package org.goiot.utils.eval.functor;

import org.goiot.utils.eval.ITExprParams;
import org.goiot.utils.eval.TreeData;
import org.goiot.utils.eval.TreeNode;

abstract class FuncLogic extends FunctionBase {

	protected FuncLogic(String name, int dimension, boolean enableVariableArgs) {
		super(name, dimension, enableVariableArgs);
	}

	protected boolean computeTreeNodeAsLogic(TreeNode treeNode, TreeData treeData, ITExprParams params) {
		Object result = treeNode.computeSubTree(treeData, params);
		// 强转整型，非0为true
		// 浮点类型可能会被坑哦
		return ((Number) result).intValue() != 0;
	}

}
