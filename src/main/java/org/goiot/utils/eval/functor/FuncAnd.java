package org.goiot.utils.eval.functor;

import java.util.ArrayList;

import org.goiot.utils.eval.ITExprParams;
import org.goiot.utils.eval.TreeData;
import org.goiot.utils.eval.TreeNode;

public class FuncAnd extends FuncLogic {

	public FuncAnd() {
		super("and", 2, true);
	}

	@Override
	public Object operate(TreeData treeData, ArrayList<TreeNode> children, ITExprParams params) {
		checkArgsNum(children);
		boolean result = true;
		for (int i = children.size() - 1; i >= 0; i--) {
			if (!computeTreeNodeAsLogic(children.get(i), treeData, params)) {
				result = false;
				break;
			}
		}
		return result ? 1 : 0;
	}

}
