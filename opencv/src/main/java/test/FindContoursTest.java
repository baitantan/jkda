package test;

import org.opencv.core.Core;
import org.opencv.core.MatOfPoint;
import util.ImageUtils;

import java.util.List;

public class FindContoursTest {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
    public static void main(String[] args) {
        ImageUtils src = new ImageUtils("D:\\photo\\pertect.jpg");
        src.photoResize();
        src.toGray();
        src.gaussianBlur();
        src.canny();
        List<MatOfPoint> contours = src.findContours();
        System.out.println(contours.size());
        //src.drawContours(contours);
        ImageUtils imageUtils = new ImageUtils(contours.get(0));
        imageUtils.saveImg("D:\\out\\Contour.jpg");
        src.saveImg("D:\\out\\FindContoursTest.jpg");
    }
}
