import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;


public class Shitfucker {

	public static void main(String[] args) {
		System.loadLibrary("opencv_java247");
		int numberOfPanels = 3;
//		int dropShadowSize = 
		
		Image source = Image.open("images\\bioshock.png");
		Image canvas = new Image(new Size(source.width() + (25 * (numberOfPanels - 1)), 
																			source.height()), 
														 source.type(), 
														 new Scalar(255,255,255));
		
		List<Image> panels = boundingRects(source, numberOfPanels, 0).stream()
																			.map((r) -> source.crop(r))
																			.collect(Collectors.toList());
		List<Rect> blitPosiitons = boundingRects(source, numberOfPanels, 25);
		
		for (int i=0; i<panels.size(); i++) {
			canvas.blit(panels.get(i), blitPosiitons.get(i));
		}
		
		canvas.addBorder(60, new Scalar(255,255,255));
		canvas.save("images\\canvas.png");
		
	}
	
	public static List<Rect> boundingRects(Image image, int numPanels, int padding) {
		List<Rect> rects = new ArrayList<>();
		
		double xPos = image.width() / numPanels;
		double croppedWidth = image.width() / numPanels;
		for (int x=0; x<image.width(); x += xPos + padding) {
			rects.add(new Rect(new Point(x, 0), 
							 					 new Point(x + croppedWidth, image.height())));	
		}
		return rects;
	}
	
	public static List<Image> cropToBoundaries(Image image, List<Rect> boundaries) {
		return boundaries.stream().map((rect) -> image.crop(rect))
										 					.collect(Collectors.toList());
	}

}
