package test;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import util.ImageUtils;

public class CharacterSpiltTest {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        ImageUtils src = new ImageUtils("D:\\photo\\cg.jpg");
        src.photoResize();
        ImageUtils name = new ImageUtils(src.split(
                ImageUtils.NAME_START_HEIGHT,
                ImageUtils.NAME_END_HEIGHT,
                ImageUtils.NAME_START_WIDTH,
                ImageUtils.NAME_END_WIDTH));
        ImageUtils nation = new ImageUtils(src.split(
                ImageUtils.NATION_START_HEIGHT,
                ImageUtils.NATION_END_HEIGHT,
                ImageUtils.NATION_START_WIDTH,
                ImageUtils.NATION_END_WIDTH));
        ImageUtils address = new ImageUtils(src.split(
                ImageUtils.ADDRESS_START_HEIGHT,
                ImageUtils.ADDRESS_END_HEIGHT,
                ImageUtils.ADDRESS_START_WIDTH,
                ImageUtils.ADDRESS_END_WIDTH));
        ImageUtils number = new ImageUtils(src.split(
                ImageUtils.NUMBER_START_HEIGHT,
                ImageUtils.NUMBER_END_HEIGHT,
                ImageUtils.NUMBER_START_WIDTH,
                ImageUtils.NUMBER_END_WIDTH));
        name.toGray();
        name.medianBlur();
        name.binaryzation(name.getUCNew());
        nation.toGray();
        nation.medianBlur();
        nation.binaryzation(nation.getUCNew());
        address.toGray();
        address.medianBlur();
        address.binaryzation(address.getUCNew());
        number.toGray();
        number.medianBlur();
        number.binaryzation(number.getUCNew());
        int i = 0;
        for (Mat mat:name.nameCharacterSplit()){
            Imgcodecs.imwrite(ImageUtils.OUTPUT_FILE_PATH + "name" + i +".jpg",mat);
            i++;
        }
        i = 0;
        for (Mat mat:nation.nationCharacterSpilt()){
            Imgcodecs.imwrite(ImageUtils.OUTPUT_FILE_PATH + "nation" + i +".jpg",mat);
            i++;
        }
        i = 0;
        for (Mat mat:address.addressCharacterSplit()){
            Imgcodecs.imwrite(ImageUtils.OUTPUT_FILE_PATH + "address" + i +".jpg",mat);
            i++;
        }
        i = 0;
        for (Mat mat:number.numberCharacterSpilt()){
            Imgcodecs.imwrite(ImageUtils.OUTPUT_FILE_PATH + "number" + i +".jpg",mat);
            i++;
        }


    }
}
