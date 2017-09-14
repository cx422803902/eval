package org.goiot.utils.eval.functor;

import java.util.ArrayList;

import org.goiot.utils.eval.ITExprParams;
import org.goiot.utils.eval.TreeData;
import org.goiot.utils.eval.TreeNode;

public class FuncFalse extends FuncLogic {

	public FuncFalse() {
		super("false", 0, false);
	}

	@Override
	public Object operate(TreeData treeData, ArrayList<TreeNode> children, ITExprParams params) {
		checkArgsNum(children);
		return 0;
	}

}
