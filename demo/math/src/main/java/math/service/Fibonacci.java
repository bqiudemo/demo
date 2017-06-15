package math.service;

import java.math.BigInteger;

public class Fibonacci {

	/*
	 * Calculate number of Fibonacci sequence
	 * @param number : total number 
	 * @return returns fibnonacci sequence as string, null if input number is negative
	 * 
	 */
	public static String caculateFibonacci(long number) {
		
		if (number < 0)
			return null;
		
		if (number == 0) {
			return "[]";
		} else if (number == 1) {
			return "[0]";
		} else if (number == 2) {
			return "[0, 1]";
		} else {	
			BigInteger fibo1 = BigInteger.ZERO;
			BigInteger fibo2 = BigInteger.ONE;
			BigInteger fibonacci = BigInteger.ZERO;
			StringBuilder builder = new StringBuilder("[0, 1");
			for (long i = 2; i < number; i++) {
				fibonacci = fibo1.add(fibo2);
				builder.append(", ").append(fibonacci);
				fibo1 = fibo2;
				fibo2 = fibonacci;
			}
			builder.append("]");
			return builder.toString();
		}
	}

}