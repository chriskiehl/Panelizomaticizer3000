import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.opencv.core.Size;


public class AspectRatioUnittest {

	private AspectRatio aspectRatio;
	
	@Before 
	public void setUp() {
		aspectRatio = new AspectRatio(16/9.);
	}
	
	@Test
	public void testCalculate__16x8__returns1_777() {
		assertCalculate(new Size(16,9), 1.777777, 0.000001);
	}
	
	@Test
	public void testCalculate__4x3__returns_1_333() {
		assertCalculate(new Size(4,3), 1.333333, 0.000001);
	}
	
	@Test
	public void testCalculate__numeratorGreaterThanDenominator__returnsCorrectAspectRatio() {
		// even if a longer vertical, you still want to know units x per unit y
		assertCalculate(new Size(3,4), 1.333333, 0.000001);
	}
	
	@Test
	public void testCalculate__sameValues__returnsOne() {
		assertCalculate(new Size(5,5), 1, 0.0);
	}
	
	private void assertCalculate(Size size, double expected, double delta) {
		double result = AspectRatio.calculate(size);
		assertEquals(expected, result, delta);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCalculate__zeroInDivisor__throwsException() {
		double result = AspectRatio.calculate(new Size(5,0));
		assertTrue(true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCalculate__negativeInput__throwsException() {
		double result = AspectRatio.calculate(new Size(-5,5));
		assertTrue(true);
	}
	
	@Test
	public void testCalcHeight__inputWidth1920__returnExpected16_9_Width() {
		assertCalcHeight(1920, 1080, "");
	}
	
	@Test
	public void testCalcHeight__inputWidth640__returnExpected4_3_Width() {
		assertCalcHeight(640, 360, "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCalcHeight__negativeInput__throwsIlligalArgumentException() {
		int actual = aspectRatio.calcHeight(-1);
		assertTrue(true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCalcHeight__zeroInput__throwsIlligalArgumentException() {
		int actual = aspectRatio.calcHeight(0);
		assertTrue(true);
	}
	
	@Test
	public void testCalcWidth__inputHeight1080__returnExpected1920Width() {
		assertCalcWidth(1080, 1920, "");
	}
	
	@Test
	public void testCalcWidth__inputWidth360__returnExpected4_3_Height() {
		assertCalcWidth(360, 640, "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCalcWidth__zeroInput__throwsIlligalArgumentException() {
		int actual = aspectRatio.calcWidth(0);
		assertTrue(true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCalcWidth__NegativeInput__throwsIlligalArgumentException() {
		int actual = aspectRatio.calcWidth(-1);
		assertTrue(true);
	}
	
	private void assertCalcHeight(int inputNumber, int expected, String msg) {
		int actual = aspectRatio.calcHeight(inputNumber);
		assertEquals(msg, expected, actual);
	}
	
	private void assertCalcWidth(int inputNumber, int expected, String msg) {
		int actual = aspectRatio.calcWidth(inputNumber);
		assertEquals(msg, expected, actual);
	}
	
	
	
	
	
	
	
	
	
	
	

}
