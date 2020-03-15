package test;

import org.opencv.core.Core;
import util.ImageUtils;

public class TestPreProcess {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        ImageUtils imageUtils = new ImageUtils("D:\\project\\tesseract\\OpenCVTest\\origin.jpg");
        imageUtils.photoResize();
        ImageUtils name = new ImageUtils(imageUtils.split(ImageUtils.NAME_START_HEIGHT, ImageUtils.NAME_END_HEIGHT,
                ImageUtils.NAME_START_WIDTH, ImageUtils.NAME_END_WIDTH));
        name.toGray();
        name.saveImg("D:\\project\\tesseract\\OpenCVTest\\TestPreProcess_name_gray.jpg");
        name.medianBlur();
        name.binaryzation(65);
        name.saveImg("D:\\project\\tesseract\\OpenCVTest\\TestPreProcess_name.jpg");

        ImageUtils nation = new ImageUtils(imageUtils.split(ImageUtils.NATION_START_HEIGHT, ImageUtils.NATION_END_HEIGHT,
                ImageUtils.NATION_START_WIDTH, ImageUtils.NATION_END_WIDTH));
        nation.toGray();
        nation.saveImg("D:\\project\\tesseract\\OpenCVTest\\TestPreProcess_nation_gray.jpg");
        nation.medianBlur();
        nation.binaryzation(65);
        nation.saveImg("D:\\project\\tesseract\\OpenCVTest\\TestPreProcess_nation.jpg");

        ImageUtils address = new ImageUtils(imageUtils.split(ImageUtils.ADDRESS_START_HEIGHT, ImageUtils.ADDRESS_END_HEIGHT,
                ImageUtils.ADDRESS_START_WIDTH, ImageUtils.ADDRESS_END_WIDTH));
        address.toGray();
        address.saveImg("D:\\project\\tesseract\\OpenCVTest\\TestPreProcess_address_gray.jpg");
        address.medianBlur();
        address.binaryzation(70);
        address.saveImg("D:\\project\\tesseract\\OpenCVTest\\TestPreProcess_address.jpg");

        ImageUtils number = new ImageUtils(imageUtils.split(ImageUtils.NUMBER_START_HEIGHT, ImageUtils.NUMBER_END_HEIGHT,
                ImageUtils.NUMBER_START_WIDTH, ImageUtils.NUMBER_END_WIDTH));
        number.toGray();
        number.saveImg("D:\\project\\tesseract\\OpenCVTest\\TestPreProcess_number_gray.jpg");
        number.medianBlur();
        number.binaryzation(65);
        number.saveImg("D:\\project\\tesseract\\OpenCVTest\\TestPreProcess_number.jpg");
    }
}
