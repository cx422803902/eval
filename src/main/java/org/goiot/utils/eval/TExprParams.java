package org.goiot.utils.eval;

import java.util.HashMap;
import java.util.Map;

public class TExprParams implements ITExprParams {
	
	private final HashMap<String, Object> map;
	
	public TExprParams(int initialCapacity) {
		map = new HashMap<String, Object>(initialCapacity);
	}
	
	public TExprParams() {
		map = new HashMap<String, Object>();
	}

	@Override
	public Object get(String key) {
		Object value = map.get(key);
		return value == null ? 0 : value;
	}

	public boolean containsKey(String key) {
		return map.containsKey(key);
	}

	public Object put(String key, Object value) {
		return map.put(key.toLowerCase(), value);
	}

	public TExprParams appendParam(String key, Object value) {
		put(key, value);
		return this;
	}
	
	public boolean isEmpty() {
		return map.isEmpty();
	}
	
	public Map<String, Object> getAllParams() {
		return map;
	}
}
