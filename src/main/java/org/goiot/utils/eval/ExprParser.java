package org.goiot.utils.eval;

import java.util.Stack;

import org.goiot.utils.eval.functor.*;
import org.goiot.utils.eval.handler.AdditionHandler;
import org.goiot.utils.eval.handler.BitwiseAndHanlder;
import org.goiot.utils.eval.handler.BitwiseExclusiveOrHandler;
import org.goiot.utils.eval.handler.BitwiseInclusiveOrHandler;
import org.goiot.utils.eval.handler.BitwiseNegationHandler;
import org.goiot.utils.eval.handler.DivisionHandler;
import org.goiot.utils.eval.handler.EqualToHandler;
import org.goiot.utils.eval.handler.GreaterThanHandler;
import org.goiot.utils.eval.handler.GreaterThanOrEqualToHandler;
import org.goiot.utils.eval.handler.LessThanHandler;
import org.goiot.utils.eval.handler.LessThanOrEqualToHandler;
import org.goiot.utils.eval.handler.LogicalAndHandler;
import org.goiot.utils.eval.handler.LogicalNegationHandler;
import org.goiot.utils.eval.handler.LogicalOrHandler;
import org.goiot.utils.eval.handler.ModulusHandler;
import org.goiot.utils.eval.handler.MultiplicationHandler;
import org.goiot.utils.eval.handler.NotEqualToHandler;
import org.goiot.utils.eval.handler.SubtractionHandler;
import org.goiot.utils.eval.handler.UnaryMinusHandler;

public class ExprParser {

	private static final String ENDFLAG = "END_FLAG";

	private final TreeData treeData = new TreeData();

	static {
		InitDefaultOperator();
		InitGlobalFunctor();
	}

	public ExprParser() {
	}

	protected Stack<String> reverseStack(Stack<String> postExprStack) {
		Stack<String> retStack = new Stack<String>();
		while (!postExprStack.empty()) {
			retStack.push(postExprStack.pop());
		}
		return retStack;
	}

	/** 注册操作符 */
	private static void InitDefaultOperator() {
		Logger.instance.appendLine("Init Default Operator!");
		TableManager.regOperator(new Operator("!加", "+", 5, 2, new AdditionHandler()));
		TableManager.regOperator(new Operator("!减", "-", 5, 2, new SubtractionHandler()));
		TableManager.regOperator(new Operator("!负", "-", 2, 1, new UnaryMinusHandler()));
		TableManager.regOperator(new Operator("!乘", "*", 4, 2, new MultiplicationHandler()));
		TableManager.regOperator(new Operator("!除", "/", 4, 2, new DivisionHandler()));
		TableManager.regOperator(new Operator("!取模", "%", 4, 2, new ModulusHandler()));

		TableManager.regOperator(new Operator("!大于", ">", 7, 2, new GreaterThanHandler()));
		TableManager.regOperator(new Operator("!大于等于", ">=", 7, 2, new GreaterThanOrEqualToHandler()));
		TableManager.regOperator(new Operator("!小于", "<", 7, 2, new LessThanHandler()));
		TableManager.regOperator(new Operator("!小于等于", "<=", 7, 2, new LessThanOrEqualToHandler()));
		TableManager.regOperator(new Operator("!等于", "==", 8, 2, new EqualToHandler()));
		TableManager.regOperator(new Operator("!不等于", "!=", 8, 2, new NotEqualToHandler()));

		TableManager.regOperator(new ClassMemberOperator());
		TableManager.enumOperator();
	}

	/** 注册全局函数 */
	private static void InitGlobalFunctor() {
		Logger.instance.appendLine("Init Global Functor!");
		TableManager.regGlobalFunction(new FuncAbs());
		TableManager.regGlobalFunction(new FuncMax());
		TableManager.regGlobalFunction(new FuncMin());
		TableManager.regGlobalFunction(new FuncPow());
		TableManager.regGlobalFunction(new FuncRandom());
		TableManager.regGlobalFunction(new FuncGaussion());
		TableManager.regGlobalFunction(new FuncSqrt());
		TableManager.regGlobalFunction(new FuncSwitch());
		TableManager.regGlobalFunction(new FuncRound());
		TableManager.regGlobalFunction(new FuncBetween());
		TableManager.enumGlobalFunction();
	}

	protected boolean regFunction(FunctionBase functor) {
		if (functor == null) {
			return false;
		}
		treeData.regFunction(functor);
		return true;
	}

	private String removeBlank(String expr) {
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < expr.length(); i++) {
			char ch = expr.charAt(i);
			if (ch == '\"') {
				if (i >= expr.length() - 1) {
					ExprException.throwException("字符串意外终止!错误位置:\'%s\'", ch);
				}
				sBuilder.append(ch);
				i++;
				ch = expr.charAt(i++);
				while (ch != '\"' && i < expr.length()) {
					sBuilder.append(ch);
					ch = expr.charAt(i++);
				}
				i--;
				if (ch != '\"') {
					ExprException.throwException("未找到字符串结束标记‘.’!错误位置:\'%s\'", ch);
				}
			}
			if (CharacterUtils.isBlank(ch)) {
				continue;
			}
			sBuilder.append(ch);
		}
		return sBuilder.toString();
	}

	private void checkOperatorRight(boolean isEnd, char pos) {
		ExprException.throwException(isEnd, "检索符号‘%s’时，意外的遇到表达式结尾!错误位置:\'%s\'", pos, pos);
	}

	private String getToken(String expr, int iPointer) {
		if (iPointer >= expr.length()) {
			return null;
		}
		StringBuilder sBuilder = new StringBuilder();
		boolean isThrow;
		char ch = expr.charAt(iPointer++);
		if (ch == '$') {
			isThrow = iPointer >= expr.length();
			ExprException.throwException(isThrow, "全局变量意外终止!错误位置:\'%s\'", ch);
			sBuilder.append(ch);
			ch = expr.charAt(iPointer++);
			isThrow = !CharacterUtils.canBeTitleOfSymbol(ch);
			ExprException.throwException(isThrow, "全局变量命名错误!错误位置:\'%s\'", ch);
		}
		if (CharacterUtils.canBeTitleOfSymbol(ch)) {
			while (CharacterUtils.canBePartOfSymbol(ch)) {
				sBuilder.append(ch);
				if (iPointer < expr.length()) {
					ch = expr.charAt(iPointer++);
				} else {
					break;
				}
			}
		} else if (CharacterUtils.isDigit(ch)) {
			while (CharacterUtils.isDigit(ch) || ch == '.') {
				sBuilder.append(ch);
				if (iPointer < expr.length()) {
					ch = expr.charAt(iPointer++);
				} else {
					break;
				}
			}
			isThrow = sBuilder.indexOf(".") != sBuilder.lastIndexOf(".");
			ExprException.throwException(isThrow, "浮点数包含多个‘.’号!错误位置:\'%s\'", ch);
		} else if (ch == '\"') {
			sBuilder.append(ch);
			ch = expr.charAt(iPointer++);
			// 这里一定有字符串结束
			while (ch != '\"') {
				sBuilder.append(ch);
				ch = expr.charAt(iPointer++);
			}
			sBuilder.append(ch);
		} else if (ch == '+') {
			sBuilder.append("!加");
		} else if (ch == '-') {
			if (iPointer - 2 < 0) {
				sBuilder.append("!负");
			} else {
				char temp = expr.charAt(iPointer - 2);
				if (temp == ',' || temp == '(') {
					sBuilder.append("!负");
				} else {
					sBuilder.append("!减");
				}
			}
		} else if (ch == '*') {
			sBuilder.append("!乘");
		} else if (ch == '/') {
			sBuilder.append("!除");
		} else if (ch == '%') {
			sBuilder.append("!取模");
		} else if (ch == '^') {
			sBuilder.append("!按位异或");
		} else if (ch == '&') {
			checkOperatorRight(iPointer >= expr.length(), ch);
			ch = expr.charAt(iPointer++);
			if (ch == '&') {
				sBuilder.append("!逻辑与");
			} else {
				sBuilder.append("!按位与");
			}
		} else if (ch == '|') {
			checkOperatorRight(iPointer >= expr.length(), ch);
			ch = expr.charAt(iPointer++);
			if (ch == '|') {
				sBuilder.append("!逻辑或");
			} else {
				sBuilder.append("!按位或");
			}
		} else if (ch == '~') {
			sBuilder.append("!按位非");
		} else if (ch == '!') {
			checkOperatorRight(iPointer >= expr.length(), ch);
			ch = expr.charAt(iPointer++);
			if (ch == '=') {
				sBuilder.append("!不等于");
			} else {
				sBuilder.append("!逻辑非");
			}
		} else if (ch == '<') {
			checkOperatorRight(iPointer >= expr.length(), ch);
			ch = expr.charAt(iPointer++);
			if (ch == '=') {
				sBuilder.append("!小于等于");
			} else if (ch == '>') {
				sBuilder.append("!不等于");
			} else {
				sBuilder.append("!小于");
			}
		} else if (ch == '>') {
			checkOperatorRight(iPointer >= expr.length(), ch);
			ch = expr.charAt(iPointer++);
			if (ch == '=') {
				sBuilder.append("!大于等于");
			} else {
				sBuilder.append("!大于");
			}
		} else if (ch == '.') {
			sBuilder.append("!类成员");
		} else if (ch == '(' || ch == ')' || ch == ',') {
			sBuilder.append(ch);
		} else {
			switch (ch) {
			case '=':
				checkOperatorRight(iPointer >= expr.length(), ch);
				ch = expr.charAt(iPointer++);
				if (ch == '=') {
					sBuilder.append("!等于");
					break;
				}
			default:
				ExprException.throwException("未定义标识符!错误位置:\'%s\'", ch);
				break;
			}
		}
		return sBuilder.toString();
	}

	// 构造后缀表达式栈。
	// 通过词法分析，获得各种类别的单词，从而依据语法规则构造后缀表达式栈
	// param expr 输入表达式
	// return 由各个单词构成的表达式栈
	private Stack<String> buildPostExpressionStack(String expr) {
		expr = removeBlank(expr).toLowerCase();
		ExprException.throwException(expr.isEmpty(), "表达式为空!");
		Stack<String> retStack = new Stack<String>();
		Stack<String> stack = new Stack<String>();
		stack.push(ENDFLAG);
		int iPointer = 0;
		while (iPointer < expr.length()) {
			// 词法分析，得到单词
			String token = getToken(expr, iPointer);
			int step;
			if (TableManager.existOperator(token)) {
				step = TableManager.getOperator(token).getEscape().length();
			} else {
				step = token.length();
			}
			if (token.equals("(")) {
				stack.push(token);
			} else if (TableManager.existOperator(token)) {
				// 分析操作符
				String lastToken = stack.lastElement();
				while (treeData.containsFunc(token) || TableManager.existGlobalFunction(lastToken)
						|| TableManager.getOperatorPriority(lastToken) <= TableManager.getOperatorPriority(token)) {
					retStack.push(stack.pop());
					lastToken = stack.lastElement();
				}
				stack.push(token);
			} else if (treeData.containsFunc(token) || TableManager.existGlobalFunction(token)) {
				// 分析函数
				stack.push(token);
				if ((treeData.containsFunc(token) && treeData.getFunction(token).enableVariableArgs())
						|| (TableManager.existGlobalFunction(token) && TableManager.getGlobalFunction(token)
								.enableVariableArgs())) {
					retStack.push("(");
				}
			} else if (token.equals(",")) {
				while (!stack.empty() && !stack.lastElement().equals("(")) {
					retStack.push(stack.pop());
				}
			} else if (token.equals(")")) {
				while (!stack.empty() && !stack.lastElement().equals("(")) {
					retStack.push(stack.pop());
				}
				ExprException.throwException(stack.empty());
				stack.pop();
				String lastToken = stack.lastElement();
				if (TableManager.existGlobalFunction(lastToken) || treeData.containsFunc(lastToken)) {
					retStack.push(stack.pop());
				}
			} else {
				retStack.push(token);
			}
			iPointer += step;
		}
		ExprException.throwException(stack.empty());
		while (!stack.lastElement().equals(ENDFLAG)) {
			retStack.push(stack.pop());
			ExprException.throwException(stack.empty());
		}
		ExprException.throwException(retStack.empty());
		return retStack;
	}

	protected TreeNode buildTree(String expr) {
		// treeData.enumFunction();
		Stack<String> postExprStack = buildPostExpressionStack(expr);
		Stack<String> tmpStack = reverseStack(postExprStack);
		Stack<TreeNode> nodeStack = new Stack<TreeNode>();
		while (!tmpStack.isEmpty()) {
			String token = tmpStack.pop();
			TreeNode node = new TreeNode(token, treeData);
			if (node.getNodeType() == TokenType.FUNCTION || node.getNodeType() == TokenType.LOCALFUNCTION
					|| node.getNodeType() == TokenType.OPERATOR) {
				OperationSymbol operationSymbol = node.getOperationSymbol();
				if (operationSymbol == null) {
					ExprException.throwException(nodeStack.empty(), String.format("找不到函数（或操作符）%s!", token));
				}
				int dimension = operationSymbol.getDimension();
				for (int i = 0; i < dimension; i++) {
					ExprException.throwException(nodeStack.empty(), String.format("函数（或操作符）%s缺少参数（操作数）!", token));
					TreeNode element = nodeStack.pop();
					node.getChildren().add(element);
				}
				if (operationSymbol.enableVariableArgs()) {
					ExprException.throwException(nodeStack.empty(), String.format("函数（或操作符）%s缺少参数（操作数）!", token));
					TreeNode lastElement = nodeStack.lastElement();
					while (!lastElement.getToken().equals("(")) {
						node.getChildren().add(nodeStack.pop());
						ExprException.throwException(nodeStack.empty(), String.format("函数（或操作符）%s缺少参数（操作数）!", token));
						lastElement = nodeStack.lastElement();
					}
					nodeStack.pop();
				}
			}
			nodeStack.push(node);
		}
		TreeNode root = nodeStack.pop();
		ExprException.throwException(!nodeStack.isEmpty(), "错误的表达式:发现多余的操作数!");
		return root;
	}

	protected TreeData getTreeData() {
		return treeData;
	}
}
