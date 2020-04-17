package test;

import org.opencv.core.Core;

public class NameSplitTest {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        /*ImageUtils imageUtils = new ImageUtils(
                ImageUtils.INPUT_FILE_PATH+"origin.jpg");
        imageUtils.photoResize();
        ImageUtils name = new ImageUtils(imageUtils.split(ImageUtils.NAME_START_HEIGHT, ImageUtils.NAME_END_HEIGHT,
                ImageUtils.NAME_START_WIDTH, ImageUtils.NAME_END_WIDTH));
        name.toGray();
        name.medianBlur();
        name.binaryzation(70);
        int j =0;
        for (Mat mat:name.nameCharacterSplit()){
            Imgcodecs.imwrite(ImageUtils.OUTPUT_FILE_PATH + "NameSplitTest" + j +".jpg",mat);
            j++;
        }*/
    }
}
