package test;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class Tess4jTest {
    public static void main(String[] args) {
        Tesseract tesseract = new Tesseract();
        //tesseract.setDatapath("D:\\project\\IDEAProject\\jkda\\opencv\\src\\main\\resources");
        tesseract.setLanguage("chi_sim");

        try {

            System.out.println(tesseract.doOCR(new File("D:\\out\\address.jpg")));

        }catch (TesseractException e){
            System.err.print(e.getMessage());
        }

    }
}
