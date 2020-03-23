import org.opencv.core.Core;
import org.opencv.core.MatOfPoint;

import util.ImageUtils;

import java.util.List;

/**
 * @author chenshuai
 * @date 2020/2/11 17:45
 * Test.class
 */
public class Main {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        String fileName = "ddd.jpg";
        String filePath = "D:\\tesseract\\OpenCVTest\\";
        /*LOGGER.info("开始加载"+filePath+fileName);
        ImageUtils imageUtils = new ImageUtils(filePath+fileName);
        //灰度化
        LOGGER.info("开始对{}进行灰度化处理" , filePath+fileName);
        imageUtils.toGray();
        LOGGER.info("开始输出灰度化处理完毕的结果");
        imageUtils.saveImg(filePath + "Gray.jpg");
        //二值化
        imageUtils.medianBlur();
        LOGGER.info("开始对{}进行二值化处理" , filePath+fileName);
        //int uCNew = imageUtils.getUCNew();
        //System.out.println(uCNew);
        imageUtils.binaryzation(70);
        MatOfPoint2f matOfPoint2f = new MatOfPoint2f(new MatOfPoint());
        imageUtils.saveImg(filePath + "65.jpg");
        //System.out.println(ImageUtils.doOCR(filePath+"61.jpg"));*/
        ImageUtils imageUtils = new ImageUtils(filePath + fileName);
        imageUtils.medianBlur();
        imageUtils.canny();
        List< MatOfPoint> list= imageUtils.findContours();
        System.out.println(list.size());
    }
}
