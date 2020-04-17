package test;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import util.ImageUtils;

public class DrawContour {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat srcImage = Imgcodecs.imread("D:\\photo\\IMG_20200409_150723.jpg");
        /*srcImage = ImageUtils.resize(srcImage);
        Mat dstImage = srcImage.clone();
        ImageUtils.toGray(dstImage);
        ImageUtils.gaussianBlur(dstImage);
        ImageUtils.canny(dstImage);
        //Imgproc.Canny(dstImage,dstImage,50,200,3);
        List<MatOfPoint> contours = new ArrayList<>();
        contours.add(ImageUtils.findMaxContour(dstImage));
        ImageUtils.drawContours(srcImage,contours);
        Imgcodecs.imwrite("D:\\tmp\\DrawContour.jpg",srcImage);*/
        Imgcodecs.imwrite("D:\\tmp\\DrawContour1.jpg",
                ImageUtils.onlyContours(srcImage));
    }
}
