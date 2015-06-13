package com.ndroid.calcplusplus;

public enum KeypadButton {

	MR("MRC", KeypadButtonCategory.MEMORYBUFFER), M_ADD("M+",
			KeypadButtonCategory.MEMORYBUFFER), M_REMOVE("M-",
			KeypadButtonCategory.MEMORYBUFFER)

	, BACKSPACE("DEL", KeypadButtonCategory.CLEAR), ANS("ANS",
			KeypadButtonCategory.CLEAR), C("C", KeypadButtonCategory.CLEAR)

	, DECIMAL_SEP(".", KeypadButtonCategory.NUMBER), ZERO("0",
			KeypadButtonCategory.NUMBER), ONE("1", KeypadButtonCategory.NUMBER), TWO(
			"2", KeypadButtonCategory.NUMBER), THREE("3",
			KeypadButtonCategory.NUMBER), FOUR("4", KeypadButtonCategory.NUMBER), FIVE(
			"5", KeypadButtonCategory.NUMBER), SIX("6",
			KeypadButtonCategory.NUMBER), SEVEN("7",
			KeypadButtonCategory.NUMBER), EIGHT("8",
			KeypadButtonCategory.NUMBER), NINE("9", KeypadButtonCategory.NUMBER)

	, PLUS(" + ", KeypadButtonCategory.OPERATOR), MINUS(" - ",
			KeypadButtonCategory.OPERATOR), MULTIPLY(" * ",
			KeypadButtonCategory.OPERATOR), DIV(" / ",
			KeypadButtonCategory.OPERATOR), OPEN_BRACKET("(",
			KeypadButtonCategory.OPERATOR), CLOSE_BRACKET(")",
			KeypadButtonCategory.OPERATOR)

	, SIGN("±", KeypadButtonCategory.RESULT), CALCULATE("=",
			KeypadButtonCategory.RESULT), DUMMY("", KeypadButtonCategory.DUMMY);

	CharSequence mText; // Display Text
	KeypadButtonCategory mCategory;

	KeypadButton(CharSequence text, KeypadButtonCategory category) {
		mText = text;
		mCategory = category;
	}

	public CharSequence getText() {
		return mText;
	}
}
