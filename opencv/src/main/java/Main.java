import org.opencv.core.Core;
import util.ImageUtils;

/**
 * @author chenshuai
 * @date 2020/2/11 17:45
 * Test.class
 */
public class Main {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        String filePath = "D:\\photo\\cg.jpg";
        ImageUtils imageUtils = new ImageUtils(filePath);
        imageUtils.toGray();
        imageUtils.medianBlur();
        System.out.println(imageUtils.getUCNew());
        imageUtils.binaryzation(105);
        imageUtils.saveImg("D:\\out\\cg.jpg");

    }
}
