
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;


public class Image {
	private Mat mat;
	
	private Image(Mat image) {
		this.mat = image;
	}
	
	public Image(Size size, int type, Scalar scalar){
		this.mat = new Mat(size, type, scalar);
	}
	
	public static Image open(String filename) {
		Mat m = Highgui.imread(filename);
		return new Image(m);
	}
	
	public Image crop(Rect roi) {
		return new Image(new Mat(this.mat, roi));
	}
	
	public void addBorder(int size, Scalar scalar) {
		Imgproc.copyMakeBorder(this.mat, this.mat, size,size,size,size,
													 Imgproc.BORDER_CONSTANT,
													 scalar);
	}
	
	public void addBorder(int size, int top, int bottom, int left, int right, Scalar scalar) {
		Imgproc.copyMakeBorder(this.mat, this.mat, top, bottom,left, right,
													 Imgproc.BORDER_ISOLATED,
													 scalar);
	}
	
	public void blit(Image image, Rect roi) {
		image.getMat().copyTo(mat.submat(roi));
	}
	
	public void save(String filename) {
		Highgui.imwrite(filename, this.mat);
	}
	
	public Size size() {
		return this.mat.size();
	}
	
	public Mat getMat() {
		return this.mat;
	}
	
	public int width() {
		return (int) this.mat.size().width;
	}
	
	public int height() {
		return (int) this.mat.size().height;
	}
	
	public int type() {
		return this.mat.type();
	}
	
	public void dropShadow(int size) {
		Highgui.imwrite("images\\layerOne.png", this.mat);
		Mat baseLayer = new Mat((int) (this.mat.rows() + size + 16), 
														(int)(this.mat.cols() + size + 16), 
														this.mat.type(),
														new Scalar(255,255,255,255));
		Mat shadowLayer = baseLayer.clone();
		Mat imageLayer = baseLayer.clone();
		this.mat.copyTo(imageLayer.submat(new Rect(centerTopLeft(imageLayer, this.mat, 0),
																						 	 centerbottomRight(imageLayer, this.mat, 0))));	
		Highgui.imwrite("images\\layertwo.png", this.mat);
		Core.rectangle(baseLayer, 
									 centerTopLeft(baseLayer, this.mat, 4), 
									 centerbottomRight(baseLayer, this.mat, 4), 
									 new Scalar(200,200,200, 200), 
									 -1);
		Highgui.imwrite("images\\afterrectangle.png", baseLayer);
		Imgproc.GaussianBlur(baseLayer, baseLayer, new Size(17,17), 6.);
		
		double opacity = 0.2;
		Core.addWeighted(baseLayer, 1 - opacity, shadowLayer, opacity, 0, baseLayer);
		this.mat.copyTo(baseLayer.submat(new Rect(centerTopLeft(imageLayer, this.mat, 0),
			 																			  centerbottomRight(imageLayer, this.mat, 0))));
		
		
		Highgui.imwrite("images\\rectangle.png", baseLayer);
	}
	
	private Point centerTopLeft(Mat baseLayer, Mat topImage, int offset) {
		return new Point((baseLayer.size().width / 2 - topImage.size().width / 2) - offset, 	
										 (baseLayer.size().height / 2 - topImage.size().height / 2) - offset);
	}
	
	private Point centerbottomRight(Mat baseLayer, Mat topImage, int offset) {
		return new Point((baseLayer.size().width / 2 + topImage.size().width / 2) + offset, 	
										 (baseLayer.size().height / 2 + topImage.size().height / 2) + offset);
	}
	
}

