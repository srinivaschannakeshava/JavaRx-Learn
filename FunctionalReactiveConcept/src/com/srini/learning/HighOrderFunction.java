package com.srini.learning;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

// TODO: Auto-generated Javadoc
/**
 * The Class HighOrderFunction.
 */
public class HighOrderFunction {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Function<String, String> funcUpper = (s) -> {
			return s.toUpperCase();
		};

		BiFunction<String, String, String> concatFunction = (s1, s2) -> {
			return s1 + s2;
		};

		HighOrderFunction hof= new HighOrderFunction();
		Supplier<String> highOrderFunc = hof.highOrderFunc("heLlo", "WoRlD", concatFunction, funcUpper);
		System.out.println(highOrderFunc.get());
	}

	/**
	 * High order func.
	 *
	 * @param s1 the s 1
	 * @param s2 the s 2
	 * @param concatFunc the concat func
	 * @param funcUpperOrLower the func upper or lower
	 * @return the supplier
	 */
	public Supplier<String> highOrderFunc(String s1, String s2, BiFunction<String, String, String> concatFunc,
			Function<String, String> funcUpperOrLower) {
		return () -> {
			return funcUpperOrLower.apply(concatFunc.apply(s1, s2));
		};

	}

}
