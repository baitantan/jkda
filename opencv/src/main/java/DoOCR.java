import exception.PhotoNotTrueException;
import exception.ServerErrorException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import util.ImageUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.ImageUtils.*;

public class DoOCR {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static final String eng = "eng";
    public static final String chi_sim = "chi_sim";
    public static Map<String, String> doOCR(String imgFilePath) throws PhotoNotTrueException,
            ServerErrorException {
        //加载源图片
        Mat src = loadImg(imgFilePath);
        //灰度化
       //src = toGray(src);
        //高斯滤波
        //gaussianBlur(src);
        //剪切出身份证部分
        Mat IDCard = wrap(src);
        Imgcodecs.imwrite("D:\\tmp\\IDCard1.jpg", IDCard);
        IDCard = photoResize(IDCard);
        Imgcodecs.imwrite("D:\\tmp\\IDCard.jpg", IDCard);
        Mat number = split(IDCard,
                NUMBER_START_HEIGHT, NUMBER_END_HEIGHT, NUMBER_START_WIDTH, NUMBER_END_WIDTH);
        Imgcodecs.imwrite("D:\\tmp\\number.jpg",number);
        String numberString =  doOCR(number, eng);
        if (numberString.length() != 18){
            System.out.println(numberString);
            rotation180(IDCard);
            number = split(IDCard,
                    NUMBER_START_HEIGHT, NUMBER_END_HEIGHT, NUMBER_START_WIDTH, NUMBER_END_WIDTH);
            numberString = doOCR(number, eng);
        }
        //翻转之后仍不正确说明不可正确识别
        if (numberString.length() != 18){
            System.out.println(numberString);
            throw new PhotoNotTrueException("反转之后仍无法识别出18位数字");
        }
        if (!IDCardNoVerify(numberString)){
            System.out.println(numberString);
            throw new ServerErrorException("身份证号码识别不正确");
        }
        Mat name = split(IDCard,
                NAME_START_HEIGHT, NAME_END_HEIGHT, NAME_START_WIDTH, NAME_END_WIDTH);
        Imgcodecs.imwrite("D:\\tmp\\name.jpg",name);
        String nameString = doOCR(name , chi_sim);
        Mat nation = split(IDCard,
                NATION_START_HEIGHT , NATION_END_HEIGHT , NATION_START_WIDTH, NATION_END_WIDTH);
        Imgcodecs.imwrite("D:\\tmp\\nation.jpg",nation);
        String nationString = doOCR(nation, chi_sim);
        Mat address = split(IDCard,
                ADDRESS_START_HEIGHT, ADDRESS_END_HEIGHT, ADDRESS_START_WIDTH, ADDRESS_END_WIDTH);
        Imgcodecs.imwrite("D:\\tmp\\address.jpg",address);
        String addressString = doOCR(address, chi_sim);
        HashMap<String , String> map = new HashMap<>();
        map.put("name" , nameString);
        map.put("nation", nationString);
        map.put("address", addressString);
        map.put("number", numberString);
        return map;
    }


    public static String doOCR(Mat src , String language){
        String filePath = "D:\\tmp\\tmp.jpg";
        toGray(src);
        medianBlur(src);
        //使用自适应阈值进行二值化
        binaryzation(getUCNew(src), src);
        Imgcodecs.imwrite(filePath , src);
        return replaceBlank(ImageUtils.doOCR(filePath , language));
    }

    public static boolean IDCardNoVerify(String IDCardNo) {
        if (!IDCardNoFormatVerify(IDCardNo)) {
            return false;
        }
        int[] coef = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += Integer.parseInt(IDCardNo.substring(i, i + 1)) * coef[i];
        }
        int remainder = sum % 11;
        Integer lastNum = ((11 - remainder) + 1) % 11;
        String last = null;
        if (lastNum == 10) {
            last = "X";// 10是罗马数字X表示
        }
        else {
            last = lastNum.toString();
        }
        if (IDCardNo.endsWith(last)) {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean IDCardNoFormatVerify(String IDCardNo) {
        String regexp = "^[0-9]{17}[0-9xX]$";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(IDCardNo);
        return matcher.matches();
    }

}
