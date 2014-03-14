import org.opencv.core.Size;


public class AspectRatio {
	// Assuming 3 panels, each 2x4', giving a total size of 
	// 6x4, and thus and aspect ratio of 1/5 
	private double targetRatio;
	
	public AspectRatio(double targetRatio) {
		this.targetRatio = targetRatio;
	}
	
	/**
	 * Return the targetRatio
	 */
	public double getTargetRatio() {
		return targetRatio;
	}
	
	/**
	 * Calculates what the height would be given the 
	 * width at the current AspectRatio 
	 * @param width
	 * @return height
	 */
	public int calcHeight(double width) {
		if (width <= 0)
			throw new IllegalArgumentException("Width cannot be <= 0");
		return new Double(width / targetRatio).intValue();
	}
	
	
	/**
	 * Calculates what the width would be given the 
	 * height at the current AspectRatio 
	 * @param height
	 * @return width
	 */
	public int calcWidth(double height) {
		if (height <= 0)
			throw new IllegalArgumentException("Width cannot be <= 0");
		return new Double(height * targetRatio).intValue();
	}
	
	/** 
	 * Calculate the aspect ratio from a set of width/height params
	 * @param size
	 * @return aspectRatio
	 */
	public static double calculate(Size size) {
		if (size.width == 0 || size.height == 0)
			throw new IllegalArgumentException("Cannot calculate aspect ratio from zero");
		if (size.width < 0 || size.height < 0)
			throw new IllegalArgumentException("Cannot calculate aspect ratio from negative numbers");
		if (size.width > size.height)
			return size.width / size.height;
		else
			return size.height / size.width;
	}
}
