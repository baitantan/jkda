package test;

import org.opencv.core.Core;

public class PhotoSpiltTest {

    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        /*ImageUtils imageUtils = new ImageUtils(
                ImageUtils.INPUT_FILE_PATH+"origin.jpg");
        imageUtils.photoResize();
        *//*Imgcodecs.imwrite("D:\\project\\tesseract\\OpenCVTest\\origin_name.jpg",
                imageUtils.split(60, 105, 155, 380));
        Imgcodecs.imwrite("D:\\project\\tesseract\\OpenCVTest\\origin_nation.jpg",
                imageUtils.split(135, 170, 325, 495));
        Imgcodecs.imwrite("D:\\project\\tesseract\\OpenCVTest\\origin_address.jpg",
                imageUtils.split(270, 430, 155, 510));
        Imgcodecs.imwrite("D:\\project\\tesseract\\OpenCVTest\\origin_number.jpg",
                imageUtils.split(450, 490, 290, 775));*//*
        Imgcodecs.imwrite(ImageUtils.OUTPUT_FILE_PATH+"origin_name.jpg",
                imageUtils.split(ImageUtils.NAME_START_HEIGHT, ImageUtils.NAME_END_HEIGHT, ImageUtils.NAME_START_WIDTH, ImageUtils.NAME_END_WIDTH));
        Imgcodecs.imwrite(ImageUtils.OUTPUT_FILE_PATH+"origin_nation.jpg",
                imageUtils.split(ImageUtils.NATION_START_HEIGHT, ImageUtils.NATION_END_HEIGHT, ImageUtils.NATION_START_WIDTH, ImageUtils.NATION_END_WIDTH));
        Imgcodecs.imwrite(ImageUtils.OUTPUT_FILE_PATH+"origin_address.jpg",
                imageUtils.split(ImageUtils.ADDRESS_START_HEIGHT, ImageUtils.ADDRESS_END_HEIGHT, ImageUtils.ADDRESS_START_WIDTH, ImageUtils.ADDRESS_END_WIDTH));
        Imgcodecs.imwrite(ImageUtils.OUTPUT_FILE_PATH+"origin_number.jpg",
                imageUtils.split(ImageUtils.NUMBER_START_HEIGHT, ImageUtils.NUMBER_END_HEIGHT, ImageUtils.NUMBER_START_WIDTH, ImageUtils.NUMBER_END_WIDTH));*/
    }
}
