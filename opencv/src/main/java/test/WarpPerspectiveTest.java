package test;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.imgcodecs.Imgcodecs;
import util.ImageUtils;

public class WarpPerspectiveTest {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = ImageUtils.loadImg("D:\\photo\\IMG_20200416_124038.jpg");
        Mat dst = src.clone();
        src = ImageUtils.resize(src);
        ImageUtils.toGray(src);
        ImageUtils.gaussianBlur(src);
        ImageUtils.canny(src);
        RotatedRect rotatedRect = ImageUtils.findMaxRect(src);
        Point[] points = new Point[4];
        rotatedRect.points(points);
        Imgcodecs.imwrite("D:\\tmp\\WarpPerspectiveTest.jpg", ImageUtils.rotation180(ImageUtils.warpPerspective(dst,points)));

    }
}
