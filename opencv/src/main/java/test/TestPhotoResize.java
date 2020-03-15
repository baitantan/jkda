package test;

import org.opencv.core.Core;
import util.ImageUtils;

public class TestPhotoResize {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        ImageUtils imageUtils = new ImageUtils();
        imageUtils.loadImg("D:\\project\\tesseract\\OpenCVTest\\111.jpg");
        imageUtils.photoResize();
        imageUtils.saveImg("D:\\project\\tesseract\\OpenCVTest\\TestPhotoResize.jpg");
    }
}
