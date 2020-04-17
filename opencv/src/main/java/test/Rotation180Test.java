package test;

import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;
import util.ImageUtils;

public class Rotation180Test {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Imgcodecs.imwrite("D:\\out\\Rotation180Test.jpg" ,
                ImageUtils.rotation180(Imgcodecs.imread("D:\\out\\CutIDCardTest.jpg")));
    }
}
