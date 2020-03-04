import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ImageUtils;

import java.io.File;

/**
 * @author chenshuai
 * @date 2020/2/19 11:15
 * Test.class
 */
public class Test {
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    public static final Logger LOGGER = LoggerFactory.getLogger("Console");

    public static void main(String[] args) {
        String filePath = "D:\\tesseract\\";
        System.out.println("姓名:"+doOCR(filePath + "姓名.jpg"));
        System.out.println("性别:" + doOCR(filePath + "性别.jpg"));
        System.out.println("民族:" + doOCR(filePath + "民族.jpg"));
        System.out.println("地址：" + doOCR(filePath + "住址.jpg"));

    }
    static String doOCR(String fileName){
        Tesseract tesseract = new Tesseract();

        tesseract.setDatapath("D:\\project\\IDEAProject\\jkda\\opencv\\src\\main\\resources\\tessdata");
        tesseract.setLanguage("chi_sim");
        try {
            String result = tesseract.doOCR(new File(fileName));
            return result;
        }catch (TesseractException e){
            System.err.print(e.getMessage());
        }
        return null;
    }
}
