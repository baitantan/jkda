package test;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import util.ImageUtils;

public class dd {
    public static void main(String[] args)
    {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("D:\\photo\\IMG_20200330_153849.jpg");
        src = ImageUtils.resize(src , 800 , 600);
        Mat dst = src.clone();
        Imgproc.cvtColor(dst, dst, Imgproc.COLOR_BGRA2GRAY);
        //Imgproc.equalizeHist(dst,dst);
        //Imgproc.adaptiveThreshold(dst, dst, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C,
               // Imgproc.THRESH_BINARY_INV, 3, 3);
        Imgproc.GaussianBlur(dst , dst , new Size(15,15),0);
        Imgproc.Canny(dst, dst, 0, 100, 3);
        Imgcodecs.imwrite("D:\\out\\edge.jpg",dst);
        java.util.List<MatOfPoint> contours = new java.util.ArrayList<>();
        Mat hierarchy = new Mat();
        /*Imgproc.findContours(dst, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE,
                new Point(3, 3));*/
        contours.add(ImageUtils.findMaxContour(dst));
        //contours = ImageUtils.findContoursAndSort(dst);
        System.out.println(contours.size());
        for (int i = 0; i < contours.size(); i++)
        {
            MatOfPoint2f matOfPoint2f = new MatOfPoint2f(contours.get(i).toArray());
            RotatedRect rotatedRect = Imgproc.minAreaRect(matOfPoint2f);
            System.out.println(rotatedRect.boundingRect().area());
            Imgproc.drawContours(src, contours, i, new Scalar(255, 0, 0, 0), 1);
        }

        Imgcodecs.imwrite("D:\\out\\test.jpg", src);
    }
}
