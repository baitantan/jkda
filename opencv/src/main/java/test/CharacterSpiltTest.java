package test;

import org.opencv.core.Core;

public class CharacterSpiltTest {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        /*String chi_sim = "chi_sim";
        String eng = "eng";
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
        name.saveImg(ImageUtils.OUTPUT_FILE_PATH + "name" + ".jpg");
        nation.saveImg(ImageUtils.OUTPUT_FILE_PATH + "nation" + ".jpg");
        address.saveImg(ImageUtils.OUTPUT_FILE_PATH + "address" + ".jpg");
        number.saveImg(ImageUtils.OUTPUT_FILE_PATH + "number" + ".jpg");
        System.out.println(ImageUtils.doOCR(ImageUtils.OUTPUT_FILE_PATH + "address" + ".jpg",chi_sim));
        System.out.println(ImageUtils.doOCR(ImageUtils.OUTPUT_FILE_PATH + "name" + ".jpg",chi_sim));
        System.out.println(ImageUtils.doOCR(ImageUtils.OUTPUT_FILE_PATH + "nation" + ".jpg",chi_sim));
        System.out.println(ImageUtils.doOCR(ImageUtils.OUTPUT_FILE_PATH + "number" + ".jpg",eng));
        int i = 0;
        String result = "";
        for (Mat mat:name.nameCharacterSplit()){
            Imgcodecs.imwrite(ImageUtils.OUTPUT_FILE_PATH + "name" + i +".jpg",mat);

             //result += ImageUtils.doOCR(ImageUtils.OUTPUT_FILE_PATH + "name" + i +".jpg");
            i++;
        }
        i = 0;
        for (Mat mat:nation.nationCharacterSpilt()){
            Imgcodecs.imwrite(ImageUtils.OUTPUT_FILE_PATH + "nation" + i +".jpg",mat);

                //    result +=    ImageUtils.doOCR(ImageUtils.OUTPUT_FILE_PATH + "nation" + i +".jpg");
            i++;
        }
        i = 0;
        for (Mat mat:address.addressCharacterSplit()){
            Imgcodecs.imwrite(ImageUtils.OUTPUT_FILE_PATH + "address" + i +".jpg",mat);

                  //  result +=  ImageUtils.doOCR(ImageUtils.OUTPUT_FILE_PATH + "address" + i +".jpg");
            i++;
        }
        i = 0;
        for (Mat mat:number.numberCharacterSpilt()){
            Imgcodecs.imwrite(ImageUtils.OUTPUT_FILE_PATH + "number" + i +".jpg",mat);

                  //  result +=    ImageUtils.doOCR(ImageUtils.OUTPUT_FILE_PATH + "number" + i +".jpg");
            i++;
        }


        System.out.println(result);
    }*/
    }
}
