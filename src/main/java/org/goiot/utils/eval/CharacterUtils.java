package org.goiot.utils.eval;

public class CharacterUtils {

	public static boolean isChineseCharacter(char ch) {
		if (isChineseLetter(ch)) {
			return true;
		}
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
		if (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}

	public static boolean isChineseLetter(char ch) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
		if (ub == null) {
			return false;
		}
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS) {
			return true;
		}
		return false;
	}

	public static boolean isLetter(char ch) {
		if (ch >= 'a' && ch <= 'z') {
			return true;
		}
		if (ch >= 'A' && ch <= 'Z') {
			return true;
		}
		return false;
	}

	public static boolean isDigit(char ch) {
		if (ch >= '0' && ch <= '9') {
			return true;
		}
		return false;
	}

	public static boolean isBlank(char ch) {
		if (ch == ' ' || ch == '\n' || ch == '\t' || ch == '\r') {
			return true;
		}
		return false;
	}

	protected static boolean canBePartOfSymbol(char ch) {
		if (canBeTitleOfSymbol(ch)) {
			return true;
		}
		if (isDigit(ch)) {
			return true;
		}
		return false;
	}

	protected static boolean canBeTitleOfSymbol(char ch) {
		if (ch == '_') {
			return true;
		}
		if (isLetter(ch)) {
			return true;
		}
		if (isChineseLetter(ch)) {
			return true;
		}
		return false;
	}

	protected static boolean isLegalVariableName(String str) {
		if (!canBeTitleOfSymbol(str.charAt(0))) {
			return false;
		}
		for (int index = 1; index < str.length(); index++) {
			if (!canBePartOfSymbol(str.charAt(index))) {
				return false;
			}
		}
		return true;
	}

	protected static boolean isUsedInSymbol(char ch) {
		switch (ch) {
		case '$':
		case '+':
		case '-':
		case '*':
		case '/':
		case '%':
		case '^':
		case '&':
		case '|':
		case '~':
		case '!':
		case '<':
		case '>':
		case '.':
		case '=':
		case '(':
		case ')':
		case ',':
			return true;
		default:
			return false;
		}
	}
}
