package test;

import exception.PhotoNotTrueException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import util.ImageUtils;

public class WarpTest {
    public static void main(String[] args) throws PhotoNotTrueException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat srcImage = Imgcodecs.imread("D:\\photo\\IMG_20200409_150723.jpg");
        Imgcodecs.imwrite("D:\\tmp\\ddd.jpg", ImageUtils.wrap(srcImage));
    }
}
