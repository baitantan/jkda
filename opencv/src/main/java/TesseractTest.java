import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * @author chenshuai
 * @date 2020/2/11 12:44
 * TesseractTest.class
 */
public class TesseractTest {
    public static void main(String[] args) {
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("chi_sim+eng");
        tesseract.setDatapath("D:\\project\\IDEAProject\\jkda\\opencv\\src\\main\\resources\\tessdata");
        try{
            String result = tesseract.doOCR(new File("D:\\tesseract\\test.png"));
            System.out.println(result);
        }catch (TesseractException e){
            System.err.print(e.getMessage());
            e.printStackTrace();
        }

    }
}
