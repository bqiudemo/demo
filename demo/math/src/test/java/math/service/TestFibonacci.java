package math.service;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestFibonacci {
	
	@BeforeClass
	public static void setup() throws Exception {
	}
	
	@Test
	public void testFabNegative() {
		assertTrue(Fibonacci.caculateFibonacci(-1) == null);
	}
	
	@Test
	public void testFab0() {
		String result = "[]";
		assertTrue(Fibonacci.caculateFibonacci(0).equals(result));
	}
	
	@Test
	public void testFab1() {
		String result = "[0]";
		assertTrue(Fibonacci.caculateFibonacci(1).equals(result));
	}
	
	@Test
	public void testFab2() {
		String result = "[0, 1]";
		assertTrue(Fibonacci.caculateFibonacci(2).equals(result));
	}
	
	@Test
	public void testFab3() {
		String result = "[0, 1, 1]";
		assertTrue(Fibonacci.caculateFibonacci(3).equals(result));
	}
	
	@Test
	public void testFab4() {
		String result = "[0, 1, 1, 2]";
		assertTrue(Fibonacci.caculateFibonacci(4).equals(result));
	}
	
	@Test
	public void testFab5() {
		String result = "[0, 1, 1, 2, 3]";
		assertTrue(Fibonacci.caculateFibonacci(5).equals(result));
	}
	
	@Test
	public void testFab29() {
		String result = "[0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, "
			+ "610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811]";
		assertTrue(Fibonacci.caculateFibonacci(29).equals(result));
	}
	
	@Test
	public void testFab300() {
		String result = Fibonacci.caculateFibonacci(300);
		String[] resultArray = result.split(",");
		String str = resultArray[299].trim();
		if( str != null && str.length() > 0 && str.endsWith("]") )
            str = str.substring(0, str.length()-1);
		assertTrue(str.equals("137347080577163115432025771710279131845700275212767467264610201"));
	}
	
	@Test
	public void testFab800() {
		String result = Fibonacci.caculateFibonacci(800);
		String[] resultArray = result.split(",");
		String str = resultArray[799].trim();
		if( str != null && str.length() > 0 && str.endsWith("]") )
            str = str.substring(0, str.length()-1);
		assertTrue(str.equals("42819299437432302617632053522679588759544125417235071019025069011950982099745888354964809941468470253059003158607459662907055750510443512830937550167717484757372564701"));
	}
	

}
