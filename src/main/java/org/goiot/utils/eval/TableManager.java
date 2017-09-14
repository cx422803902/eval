package org.goiot.utils.eval;

import java.util.HashMap;
import java.util.Map;

import org.goiot.utils.eval.functor.FunctionBase;

public class TableManager {
	// 管理、存储表达式语言环境涉及的语法关键字
	private static Map<String, Symbol> tblSyntaxKey = new HashMap<String, Symbol>();
	// 管理、存储表达式语言环境涉及的操作符，包括解析引擎保留操作符以及用户注册的自定义操作符
	private static Map<String, Operator> tblOperator = new HashMap<String, Operator>();
	// 管理、存储表达式语言环境定义的函数，包括解析引擎内部函数以及用户注册的自定义函数
	private static Map<String, FunctionBase> tblGlobalFunction = new HashMap<String, FunctionBase>();
	// 管理、存储表达式语言环境定义的变量，包括解析引擎内部全局变量以及用户注册的自定义变量
	private static Map<String, Double> tblGlobalVar = new HashMap<String, Double>();

	// 标识符最大长度，方便表达式解析过程中的回溯处理
	private static int MAX_IDENTIFIER_LEN = 1;

	// 根据标识符号字符串取得标识符
	public static Symbol getIdentifier(String identifier) {
		return tblSyntaxKey.get(identifier);
	}

	// 根据标识符号字符串取得操作符
	public static Operator getOperator(String identifier) {
		return tblOperator.get(identifier);
	}

	// 根据标识符号字符串取得函数
	public static FunctionBase getGlobalFunction(String identifier) {
		return tblGlobalFunction.get(identifier);
	}

	// //判断是否存在某标识符
	public static boolean existIdentifier(String identifier) {
		return tblSyntaxKey.containsKey(identifier);
	}

	// 判断是否存在某操作符
	public static boolean existOperator(String identifier) {
		return tblOperator.containsKey(identifier);
	}

	// 判断是否存在某函数
	public static boolean existGlobalFunction(String identifier) {
		return tblGlobalFunction.containsKey(identifier);
	}

	// 注册语法关键字
	public static boolean regSyntaxKeys(Symbol identifier) {
		if (identifier == null) {
			return false;
		}
		identifier.setIdentifier(identifier.getIdentifier().toLowerCase());
		if (tblSyntaxKey.containsKey(identifier.getIdentifier())) {
			Logger.instance.appendFormat("语法关键字%s被重新注册！\n", identifier.getIdentifier());
		}
		tblSyntaxKey.put(identifier.getIdentifier(), identifier);
		return true;
	}

	// 注册运算符
	public static boolean regOperator(Operator oper) {
		if (oper == null) {
			return false;
		}
		oper.setIdentifier(oper.getIdentifier().toLowerCase());
		if (tblOperator.containsKey(oper.getIdentifier())) {
			Logger.instance.appendFormat("运算符%s被重新注册！\n", oper.getIdentifier());
		}
		tblOperator.put(oper.getIdentifier(), oper);
		return true;
	}

	// 注册全局函数
	public static boolean regGlobalFunction(FunctionBase func) {
		if (func == null) {
			return false;
		}
		func.setIdentifier(func.getIdentifier().toLowerCase());
		if (tblGlobalFunction.containsKey(func.getIdentifier())) {
			Logger.instance.appendFormat("全局函数%s被重新注册！\n", func.getIdentifier());
		}
		tblGlobalFunction.put(func.getIdentifier(), func);
		return true;
	}

	public static void enumOperator() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("已注册运算符: ");
		for (Operator oper : tblOperator.values()) {
			sBuilder.append(String.format("‘%s’(%s), ", oper.getIdentifier(), oper.getEscape()));
		}
		Logger.instance.appendLine(sBuilder.toString());
	}

	public static void enumGlobalFunction() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("已注册全局函数: ");
		for (FunctionBase functor : tblGlobalFunction.values()) {
			sBuilder.append(String.format("‘%s’, ", functor.getIdentifier()));
		}
		Logger.instance.appendLine(sBuilder.toString());
	}

	// 取得操作符优先级
	public static int getOperatorPriority(String identifier) {
		if (tblOperator.containsKey(identifier)) {
			return tblOperator.get(identifier).getPriority();
		}
		return 99;
	}

	// 取得最大标识符长度
	public static int getMaxIdentifierLength() {
		return MAX_IDENTIFIER_LEN;
	}

	// 取得运算符/全局函数
	public static OperationSymbol getOperationSymbol(String token) {
		if (tblOperator.containsKey(token)) {
			return tblOperator.get(token);
		}
		if (tblGlobalFunction.containsKey(token)) {
			return tblGlobalFunction.get(token);
		}
		return null;
	}

	// 取得单词类型
	public static TokenType getTokenType(String token) {
		if (token.startsWith("$")) {
			return TokenType.VARIABLE;
		} else if (token.startsWith("\"") && token.endsWith("\"")) {
			return TokenType.STRINGCONST;
		} else if (existGlobalFunction(token)) {
			return TokenType.FUNCTION;
		} else if (existOperator(token)) {
			return TokenType.OPERATOR;
		} else if (CharacterUtils.isLegalVariableName(token)) {
			return TokenType.LOCALVARIABLE;
		}
		return TokenType.CONST;
	}

	// 根据变量名取得已注册的变量
	public static double getGlobalVariable(String varName) {
		varName = varName.toLowerCase();
		if (existGlobalVariable(varName)) {
			return tblGlobalVar.get(varName);
		}
		ExprException.throwException("未定义全局变量%s!", varName);
		return 0;
	}

	// 判断是否存在某变量
	public static boolean existGlobalVariable(String varName) {
		varName = varName.toLowerCase();
		return tblGlobalVar.containsKey(varName);
	}

	// 注册全局变量
	public static boolean regGlobalVariable(String name, double value) {
		if (name == null) {
			return false;
		}
		name = name.toLowerCase();
		if (tblGlobalVar.containsKey(name)) {
			Logger.instance.appendFormat("全局变量%s被重新注册！\n", name);
		}
		tblGlobalVar.put(name, value);
		return true;
	}
}
