package test;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import util.ImageUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FROMSt {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat srcImage = Imgcodecs.imread("D:\\photo\\IMG_20200409_150723.jpg");
        srcImage = ImageUtils.resize(srcImage);
        Mat dstImage = srcImage.clone();
        ImageUtils.toGray(dstImage);
        ImageUtils.gaussianBlur(dstImage);
        ImageUtils.canny(dstImage);
        MatOfPoint contour = ImageUtils.findMaxContour(dstImage);
        MatOfPoint2f matOfPoint2f = new MatOfPoint2f();
        MatOfPoint2f approxCurve = new MatOfPoint2f();
        Rect rect = Imgproc.boundingRect(contour);
        double contourArea = Imgproc.contourArea(contour);
        matOfPoint2f.fromList(contour.toList());
        Imgproc.approxPolyDP(matOfPoint2f, approxCurve, Imgproc.arcLength(matOfPoint2f, true) * 0.02, true);
        long total = approxCurve.total();
        System.out.println(total);
        if (total == 3) { // is triangle
                // do things for triangle

        }
        if (total >= 4 && total <= 6) {

            List<Double> cos = new ArrayList<>();
            Point[] points = approxCurve.toArray();
            for (int j = 2; j < total + 1; j++) {
                cos.add(angle(points[(int) (j % total)], points[j - 2], points[j - 1]));
            }
            Collections.sort(cos);
            Double minCos = cos.get(0);
            Double maxCos = cos.get(cos.size() - 1);
            boolean isRect = total == 4 && minCos >= -0.1 && maxCos <= 0.3;
            boolean isPolygon = (total == 5 && minCos >= -0.34 && maxCos <= -0.27) || (total == 6 && minCos >= -0.55 && maxCos <= -0.45);
            if (isRect) {
                double ratio = Math.abs(1 - (double) rect.width / rect.height);
                drawText(dstImage,rect.tl(), ratio <= 0.02 ? "SQU" : "RECT");
                drawText(srcImage,rect.tl(), ratio <= 0.02 ? "SQU" : "RECT");

            }
            if (isPolygon) {
                drawText(dstImage,rect.tl(), "Polygon");
                drawText(srcImage,rect.tl(), "Polygon");

            }
        }
        Imgcodecs.imwrite("D:\\tmp\\dst.jpg",dstImage);
        Imgcodecs.imwrite("D:\\tmp\\src.jpg",srcImage);
    }
    private static double angle(Point pt1, Point pt2, Point pt0) {
        double dx1 = pt1.x - pt0.x;
        double dy1 = pt1.y - pt0.y;
        double dx2 = pt2.x - pt0.x;
        double dy2 = pt2.y - pt0.y;
        return (dx1*dx2 + dy1*dy2)/Math.sqrt((dx1*dx1 + dy1*dy1)*(dx2*dx2 + dy2*dy2) + 1e-10);
    }

    private static void drawText(Mat colorImage, Point ofs, String text) {
        Imgproc.putText(colorImage, text, ofs, Imgproc.FONT_HERSHEY_SIMPLEX,
                0.5, new Scalar(255,255,25));
    }
}
