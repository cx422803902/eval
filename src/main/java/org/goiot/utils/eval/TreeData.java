package org.goiot.utils.eval;

import java.util.HashMap;
import java.util.Map;

import org.goiot.utils.eval.functor.FunctionBase;

public class TreeData {

	private final Map<String, FunctionBase> tblFunctionMap = new HashMap<String, FunctionBase>();

	public void regFunction(FunctionBase functor) {
		functor.setIdentifier(functor.getIdentifier().toLowerCase());
		if (tblFunctionMap.containsKey(functor.getIdentifier())) {
			Logger.instance.appendFormat("全局函数%s被局部函数覆盖！\n", functor.getIdentifier());
		}
		tblFunctionMap.put(functor.getIdentifier(), functor);
	}

	public void enumFunction() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("已注册局部函数: ");
		for (FunctionBase functor : tblFunctionMap.values()) {
			sBuilder.append(String.format("‘%s’, ", functor.getIdentifier()));
		}
		Logger.instance.appendLine(sBuilder.toString());
	}

	@Deprecated
	public Map<String, FunctionBase> getTblFunctionMap() {
		return tblFunctionMap;
	}

	public boolean containsFunc(String token) {
		return tblFunctionMap.containsKey(token);
	}

	public FunctionBase getFunction(String token) {
		return tblFunctionMap.get(token);
	}

}
