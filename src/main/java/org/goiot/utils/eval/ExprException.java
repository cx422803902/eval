package org.goiot.utils.eval;

public class ExprException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public ExprException(String msg) {
		super(msg);
	}

	public static void throwException(boolean isThrow) {
		if (isThrow) {
			String exString = "错误的表达式!";
			throw new ExprException(exString);
		}
	}

	public static void throwException(String exString) {
		throw new ExprException(exString);
	}

	public static void throwException(boolean isThrow, String exString) {
		if (isThrow) {
			throw new ExprException(exString);
		}
	}

	public static void throwException(String format, Object... args) {
		throw new ExprException(String.format(format, args));
	}

	public static void throwException(boolean isThrow, String format, Object... args) {
		if (isThrow) {
			throw new ExprException(String.format(format, args));
		}
	}
}
