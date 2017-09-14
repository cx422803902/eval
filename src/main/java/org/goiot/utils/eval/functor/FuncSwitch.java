package org.goiot.utils.eval.functor;

import java.util.ArrayList;

import org.goiot.utils.eval.ExprException;
import org.goiot.utils.eval.ITExprParams;
import org.goiot.utils.eval.TreeData;
import org.goiot.utils.eval.TreeNode;

public class FuncSwitch extends FuncLogic {

	//switch(a,1,2,3,4,5)
	//a=1->2
	//a=3->2
	//a=其他->5
	public FuncSwitch() {
		super("switch", 3, true);
	}

	@Override
	public Object operate(TreeData treeData, ArrayList<TreeNode> children, ITExprParams params) {
		checkArgsNum(children);
		// 参数必须是偶数数个，因为第一个数用于判断的参数,最后一个是default
		if (children.size() % 2 == 0) {
			ExprException.throwException("传参个数不匹配！函数%s的申明参数个数必须是奇数且大于等于3，实际传参个数为%d,(条件1，值1,条件2,值2,...默认值)", getIdentifier(), children.size());
			return null;
		}
		for (int i = children.size() - 1; i > 1; i -= 2) {
			if(computeTreeNodeAsLogic(children.get(i), treeData, params)) {
				return children.get(i - 1).computeSubTree(treeData, params);
			}
		}
		// default
		return children.get(0).computeSubTree(treeData, params);
	}

}
