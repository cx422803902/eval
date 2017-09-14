package org.goiot.utils.eval.functor;

import java.util.ArrayList;

import org.goiot.utils.eval.ITExprParams;
import org.goiot.utils.eval.TreeData;
import org.goiot.utils.eval.TreeNode;

public class FuncNot extends FuncLogic {

	public FuncNot() {
		super("not", 1, false);
	}

	@Override
	public Object operate(TreeData treeData, ArrayList<TreeNode> children, ITExprParams params) {
		checkArgsNum(children);
		boolean result = !computeTreeNodeAsLogic(children.get(0), treeData, params);
		return result ? 1 : 0;
	}

}
