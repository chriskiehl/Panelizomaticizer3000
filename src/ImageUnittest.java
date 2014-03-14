import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.*;



public class ImageUnittest {
	
	@BeforeClass
	public static void linkLibrary() {
		System.loadLibrary("opencv_java247");
		
		System.out.println(new File("unittest_images").isDirectory());
		System.out.println(new File("images").isDirectory());
	}

	@Test
	public void testOpen() {
		Image im = Image.open("images\\bioshock.png");
	}
	
	@Test
	public void testCrop() {
		Image im = Image.open("images\\bioshock.png");
		im.dropShadow(4);
		Image cropped = im.crop(new Rect(new Point(0.0,0.0), 
																		 new Point(640,1000)));
		cropped.addBorder(10, new Scalar(255,0,0));
		cropped.save("images\\croppedWithBorder.png");
		assertTrue(cropped.size().width == 640);
		assertTrue(cropped.size().height == 1000);
	}
	
	@Test 
	public void testPaste() {
		Image source = Image.open("images\\bioshock.png");
		Image dest = new Image(new Size(source.size().width + 60, 
																		source.size().height + 60), 
													 source.getMat().type(), 
													 new Scalar(255,255,255));
		dest.blit(source, new Rect(0, 0, 1920, 1080));
		dest.save("images\\biofuck.png");
	}

	@Test
	public void testGetHeight() { 
		Image im = new Image(new Size(10,10), CvType.CV_8UC3, new Scalar(255,255,255));
		assertEquals(im.width(), 10);
	}
}
