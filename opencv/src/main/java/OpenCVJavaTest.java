
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import  org.opencv.imgcodecs.Imgcodecs;
import  org.opencv.imgproc.Imgproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author chenshuai
 * @date 2020/1/1 17:04
 * OpenCVJavaTest.class
 */
public class OpenCVJavaTest {

    //加载核心库
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    public static final Logger LOGGER = LoggerFactory.getLogger("Console");
    public static void main(String[] args) {
        Mat image;
        image = Imgcodecs.imread("D:\\tesseract\\OpenCVTest\\111.jpg");
        if (image.empty()){
            System.out.println("File can not be found!");
        }

        Mat imGray = new Mat();
        Imgproc.cvtColor(image , imGray , Imgproc.COLOR_BGR2GRAY);
        /*Imgproc.blur(imGray , image ,new  Size(3 ,3));
        Imgcodecs.imwrite("D:\\tesseract\\OpenCVTest\\blur.jpg" , image);
        Imgproc.Canny(image , imGray , 50 ,150 ,3);
        Imgcodecs.imwrite("D:\\tesseract\\OpenCVTest\\Canny.jpg" , imGray);*/
        /*ImageViewer imageViewer = new ImageViewer(imGray , "Canny算子处理后的图片");
        imageViewer.imshow();*/
        /*Imgproc.Sobel(imGray , image  ,1 , 0);*/
    }

}
