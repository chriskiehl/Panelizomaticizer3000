import javax.swing.InputMap;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;


public class Panelizomaticizer3000 {
	// 25px inbetween panels 
	// 60px padding 
	public static void main(String[] args) {
		System.loadLibrary("opencv_java247");
		int PANEL_PADDING = 50; 
		int BODY_PADDING = 60;
		AspectRatio aspectRatio = new AspectRatio(1.5);
		
//		Mat b = Highgui.imread("images\\coolimage.jpg");
		Mat a = Highgui.imread("images\\bioshock.png");
		Mat b = createBaseImage(a.size(), a.type(),60, 50);
//		Rect rect = new Rect(new Point(0,0), a.size());
//		Rect rect = calculateCenter(a, b, 0);
		Mat cropped = crop(a, leftRIO(a));
		System.out.println("1. " + cropped.size());
		Imgproc.copyMakeBorder(cropped, cropped, 3,3,3,3,
													 Imgproc.BORDER_CONSTANT,
													 new Scalar(255,255,255));
		
//		System.out.println("2. " + cropped.size());
//		Imgproc.copyMakeBorder(cropped, cropped, 0,0,0,3,
//													 Imgproc.BORDER_CONSTANT,
//													 new Scalar(255,255,255));
//		
//		System.out.println("3: " + cropped.size());
//		Imgproc.copyMakeBorder(cropped, cropped, 1,1,1,1,
//													 Imgproc.BORDER_CONSTANT,
//													 new Scalar(190,190,190));
//		System.out.println(cropped.size());
//		
//		System.out.println(cropped.size());
		
		Rect rect = new Rect(new Point(60,60),cropped.size());
		Mat bSubmat = b.submat(rect);
		
		cropped.copyTo(bSubmat);
		
		cropped = crop(a, rightRIO(a));
		Highgui.imwrite("images\\coolimage3__.jpg", a);
//		Imgproc.copyMakeBorder(cropped, cropped, 3,3,3,3,
//													 Imgproc.BORDER_CONSTANT,
//													 new Scalar(255,255,255));
//		Imgproc.copyMakeBorder(cropped, cropped, 0,0,0,3,
//													 Imgproc.BORDER_CONSTANT,
//													 new Scalar(255,255,255));
//		
//		Imgproc.copyMakeBorder(cropped, cropped, 1,1,1,1,
//													 Imgproc.BORDER_CONSTANT,
//													 new Scalar(190,190,190));
//		
//		System.out.println(cropped.size());
//		
//		rect = new Rect(new Point(b.size().width - cropped.size().width - 60,
//															60), cropped.size());
//		bSubmat = b.submat(rect);
//		
//		cropped.copyTo(bSubmat);
//		
//		Highgui.imwrite("images\\coolimage3.jpg", b);
//		System.out.println("Done");
		
		//			src1 first input array.
//			alpha weight of the first array elements.
//			src2 second input array of the same size and channel number as src1.
//			beta weight of the second array elements.
//			gamma scalar added to each sum.
//			dst output array that has the same size and number of channels as the input arrays.

			
	}
	
	public static Mat createBaseImage(Size size, int cvtype, double bodyPadding, double panelPadding) {
		Size fullSize = new Size(size.width + (bodyPadding * 2) + panelPadding,
														size.height + (bodyPadding * 2)); 
		return new Mat(fullSize, 
									 cvtype,
									 new Scalar(255, 255, 255, 50));
	}
	
	public static Rect calculateCenter(Mat a, Mat b, double offset) {
		Point middle = new Point((b.size().width / 2) - (a.size().width / 2) + offset, 
														 (b.size().height / 2) - (a.size().height / 2));
		return new Rect(middle, a.size());
	}
	
	public static Mat crop(Mat inputMat, Rect roi) {
		return new Mat(inputMat, roi);
	}
	
	public static Rect leftRIO(Mat inputMat) {
		return new Rect(new Point(0.0, 0.0), new Point(inputMat.size().width / 3, inputMat.size().height));
	}
	
	public static Rect rightRIO(Mat inputMat) {
		double x = inputMat.size().width * (2./3.); // Sets X to the last 1/3 of the image
		System.out.println("Pos: " + inputMat.size().width * (2./3.));
		return new Rect(new Point(x, 0.0), 
										new Point(inputMat.size().width / 3 + x, 
															inputMat.size().height));
	}
	
	public static Rect centerROI(Mat inputMat) {
		double x = inputMat.size().width / 3;
		Rect r = new Rect(new Point(x, 0.0), 
									  	new Point(x + inputMat.size().width / 3, 
									  						inputMat.size().height));
		System.out.println(r);
		return r;
	}
	
}
