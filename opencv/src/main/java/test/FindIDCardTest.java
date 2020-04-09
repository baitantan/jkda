package test;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import util.ImageUtils;

public class FindIDCardTest {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("D:\\photo\\pertect.jpg");
        src = ImageUtils.resize(src , 600 , 800);
        Mat dst = src.clone();
        Imgproc.cvtColor(dst, dst, Imgproc.COLOR_BGRA2GRAY);
        Imgproc.GaussianBlur(dst , dst , new Size(15,15),0);
        Imgproc.Canny(dst, dst, 0, 100, 3);
        Rect rect = ImageUtils.findMaxRect(dst).boundingRect();
        System.out.println(rect);
        Imgcodecs.imwrite("D:\\out\\FindIDCardTest4.jpg", new Mat(src ,
                rect));

    }
}
