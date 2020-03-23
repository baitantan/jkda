package test;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import util.ImageUtils;

import java.util.List;

public class CharacterSpiltTest {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        ImageUtils imageUtils = new ImageUtils("D:\\photo\\origin.jpg");
        imageUtils.photoResize();
        ImageUtils address = new ImageUtils(imageUtils.split(ImageUtils.ADDRESS_START_HEIGHT, ImageUtils.ADDRESS_END_HEIGHT,
                ImageUtils.ADDRESS_START_WIDTH, ImageUtils.ADDRESS_END_WIDTH));
        address.toGray();
        address.medianBlur();
        int uc = address.getUCNew();
        address.binaryzation(70);
        List<Mat> list= address.addressCharacterSplit();
        int j = 0;
        for (Mat mat1:list){
            Imgcodecs.imwrite(ImageUtils.OUTPUT_FILE_PATH+ "character"+j +".jpg" , mat1);
            j++;
        }
        address.saveImg(ImageUtils.OUTPUT_FILE_PATH+"CharacterSpiltTest.jpg");
    }
}
