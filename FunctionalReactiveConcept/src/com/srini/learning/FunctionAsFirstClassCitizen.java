package com.srini.learning;

import java.util.function.BiFunction;
import java.util.function.Function;

import io.reactivex.rxjava3.core.Observable;

/**
 * The Class FunctionAsFirstClassCitizen.
 */
public class FunctionAsFirstClassCitizen {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Function<String, String> funcUpper = (s) -> {
			return s.toUpperCase();
		};

		BiFunction<String, String, String> concatFunction = (s1, s2) -> {
			return s1 + s2;
		};

		BiFunction<String, String, String> revConcatFunc = (s1, s2) -> {
			return s2 + s1;
		};

		FunctionAsFirstClassCitizen fafcc = new FunctionAsFirstClassCitizen();
		Function<String, String> lowFuc = fafcc::funcLower;
		String word = fafcc.funcAsParameter("heLlo", "WoRlD", concatFunction, funcUpper);
		System.out.println(word);
		String word2 = fafcc.funcAsParameter("heLlo", "WoRlD", concatFunction, lowFuc);
		System.out.println(word2);
		System.out.println(fafcc.funcAsParameter("heLlo", "WoRlD", revConcatFunc, lowFuc));
		System.out.println(fafcc.funcAsParameter("heLlo", "WoRlD", revConcatFunc, funcUpper));

	}

	/**
	 * Func lower.
	 *
	 * @param s the s
	 * @return the string
	 */
	public String funcLower(String s) {
		return s.toLowerCase();
	}

	/**
	 * Func as parameter.
	 *
	 * @param s1 the s 1
	 * @param s2 the s 2
	 * @param concatFunc the concat func
	 * @param funcUpperOrLower the func upper or lower
	 * @return the string
	 */
	public String funcAsParameter(String s1, String s2, BiFunction<String, String, String> concatFunc,
			Function<String, String> funcUpperOrLower) {
		return funcUpperOrLower.apply(concatFunc.apply(s1, s2));

	}

}
