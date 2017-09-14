package org.goiot.utils.eval.functor;

import java.util.ArrayList;

import org.goiot.utils.eval.ITExprParams;
import org.goiot.utils.eval.TreeData;
import org.goiot.utils.eval.TreeNode;

public class FuncIf extends FuncLogic {

	public FuncIf() {
		super("if", 3, false);
	}

	@Override
	public Object operate(TreeData treeData, ArrayList<TreeNode> children, ITExprParams params) {
		checkArgsNum(children);
		if (computeTreeNodeAsLogic(children.get(2), treeData, params)) {
			return children.get(1).computeSubTree(treeData, params);
		} else {
			return children.get(0).computeSubTree(treeData, params);
		}
	}

}
