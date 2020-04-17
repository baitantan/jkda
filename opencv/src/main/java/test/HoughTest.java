package test;



import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import util.ImageUtils;

public class HoughTest {
    public static void main(String[] args)
    {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat srcImage = Imgcodecs.imread("D:\\photo\\IMG_20200409_150723.jpg");
        srcImage = ImageUtils.resize(srcImage);
        Mat dstImage = srcImage.clone();
        ImageUtils.toGray(dstImage);
        ImageUtils.gaussianBlur(dstImage);
        ImageUtils.canny(dstImage);


        //Imgproc.Canny(dstImage,dstImage,50,200,3);
        Mat lines = new Mat();
        Imgcodecs.imwrite("D:\\fff.jpg",dstImage);
        Imgproc.HoughLinesP(dstImage, lines, 1, Math.PI / 180, 50 );

        System.out.println(lines.rows());
        for (int i = 0; i < lines.rows(); i++) {
            double[] val = lines.get(i, 0);
            Imgproc.line(srcImage, new Point(val[0], val[1]), new Point(val[2], val[3]), new Scalar(255, 0, 0),1);
        }
        Imgcodecs.imwrite("D:\\dst2.jpg", srcImage);
    }

    public Mat getHoughPTransform(Mat image, double rho, double theta, int threshold) {
        Mat result = image;
        Mat lines = new Mat();
        Imgproc.HoughLinesP(image, lines, rho, theta, threshold);

        for (int i = 0; i < lines.rows(); i++) {
            double[] val = lines.get(i, 0);

            Imgproc.line(result, new Point(val[0], val[1]), new Point(val[2], val[3]), new Scalar(0, 0, 255), 2);
        }
        return result;
    }
}
