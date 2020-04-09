package test;

import org.opencv.core.Core;
import org.opencv.core.MatOfPoint;
import util.ImageUtils;

import java.util.List;

public class FindContoursTest {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
    public static void main(String[] args) {
        ImageUtils src = new ImageUtils("D:\\photo\\pertect.jpg");
        ImageUtils dst = new ImageUtils("D:\\photo\\pertect.jpg");
        dst.photoResize();
        src.photoResize();
        src.toGray();
        src.gaussianBlur();
        src.canny();
        List<MatOfPoint> contours = src.findContours();
        System.out.println(contours.size());
        //src.drawContours(contours);
        dst.drawContours(contours);
        dst.saveImg("D:\\out\\FindContoursTest.jpg");
    }
}
