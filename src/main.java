import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class main {
   public static void main( String[] args )
   {
      try{
         System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
         Mat isource = Highgui.imread("images\\original.jpg",
        		 												Highgui.CV_LOAD_IMAGE_COLOR);
         
//         Mat source = new Mat(isource.rows(), isource.cols() /2, isource.type());
         Mat source = new Mat(isource, new Rect(0, 0, 480, 540));
         Mat destination = new Mat(source.rows(),source.cols(),source.type());
         int top, bottom, left, right;
         int borderType;

         /// Initialize arguments for the filter
         top = (int) (0.01*source.rows()); 
         bottom = (int) (0.01*source.rows());
         left = (int) (0.01*source.cols()); 
         right = (int) (0.01*source.cols());
         System.out.println(top);
         System.out.println(bottom);
         System.out.println(left);
         System.out.println(right);

//         destination = source;
         
         System.out.println(source.size());
         Imgproc.copyMakeBorder(source, source, 10, 10, 
        		 										10, 10, Imgproc.BORDER_CONSTANT, 
        		 										new Scalar(255,0,0));
         System.out.println(source.size());
         
         Imgproc.copyMakeBorder(source, source, 0, 0, 
																0, 10, Imgproc.BORDER_CONSTANT, 
																new Scalar(255,0,0));
         System.out.println(source.size());
         
//         top = (int) (0.01*source.rows()); 
//         bottom = (int) (0.01*source.rows());
//         left = (int) (0.01*source.cols()); 
//         right = (int) (0.01*source.cols());
//         Imgproc.copyMakeBorder(source, source, 10, 10, 
//																10, 10, Imgproc.BORDER_CONSTANT, new Scalar(0,255,0));
         Highgui.imwrite("images\\borderWrap.jpg", source);
         }catch (Exception e) {
            System.out.println("error: " + e.getMessage());
         }
   }
}