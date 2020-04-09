package test;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.RotatedRect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import util.ImageUtils;

import static util.ImageUtils.cutRect;
import static util.ImageUtils.rotation;

public class CutIDCardTest {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("D:\\photo\\IMG_20200409_150723.jpg");
        src = ImageUtils.resize(src );
        Mat dst = src.clone();
        Imgproc.cvtColor(dst, dst, Imgproc.COLOR_BGRA2GRAY);
        //Imgproc.equalizeHist(dst,dst);
        //Imgproc.adaptiveThreshold(dst, dst, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C,
        // Imgproc.THRESH_BINARY_INV, 3, 3);
        Imgproc.GaussianBlur(dst , dst , new Size(15,15),0);
        Imgproc.Canny(dst, dst, 0, 100, 3);
        Imgcodecs.imwrite("D:\\out\\edge.jpg",
                dst  );
        RotatedRect rect = ImageUtils.findMaxRect(dst);
        // 旋转矩形
        Mat CorrectImg = rotation(dst , rect);
        Mat NativeCorrectImg = rotation(src , rect);
        //裁剪矩形
        Imgcodecs.imwrite("D:\\out\\CutIDCardTest.jpg",
                cutRect(CorrectImg , NativeCorrectImg)  );

    }
}
