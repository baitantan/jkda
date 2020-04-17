package test;

import com.google.gson.Gson;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import util.ImageUtils;

import java.util.HashMap;

public class MainTest {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        long startTime=System.currentTimeMillis();
        Mat src = Imgcodecs.imread("D:\\photo\\IMG_20200409_150723.jpg");
        src = ImageUtils.cutIDCard(src);
        Imgcodecs.imwrite( "D:\\out\\src.jpg" , src);
        //ImageUtils.preProcess(src);
        String name = ImageUtils.doOCR("D:\\out\\name.jpg" , "chi_sim");
        String nation = ImageUtils.doOCR("D:\\out\\nation.jpg" , "chi_sim");
        String address = ImageUtils.doOCR("D:\\out\\address.jpg" , "chi_sim");
        String number = ImageUtils.doOCR("D:\\out\\number.jpg" , "eng");
        HashMap<String , String> map = new HashMap<>();
        map.put("name" , ImageUtils.replaceBlank(name));
        map.put("nation" , ImageUtils.replaceBlank(nation));
        map.put("address", ImageUtils.replaceBlank(address));
        map.put("number" , ImageUtils.replaceBlank(number));
        Gson gson = new Gson();
        System.out.println(gson.toJson(map));
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }
}
