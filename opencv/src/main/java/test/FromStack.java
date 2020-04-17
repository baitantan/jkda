package test;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;
import util.ImageUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.opencv.core.CvType.CV_8UC1;

public class FromStack {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat srcImage = Imgcodecs.imread("D:\\photo\\IDCard.jpg");
        List<MatOfPoint> matOfPoints = new ArrayList<>();
        srcImage = ImageUtils.resize(srcImage);
        srcImage = ImageUtils.rotation180(srcImage);
        Mat dst = ImageUtils.proProcess(srcImage);
        matOfPoints.add(ImageUtils.convextHull(ImageUtils.findMaxContour(dst)));
        //matOfPoints.add(ImageUtils.findMaxContour(dst));
        RotatedRect rect = ImageUtils.findMaxRect(dst);
        Point[] points = new Point[4];
        rect.points(points);
        MatOfPoint2f matOfPoint2f = new MatOfPoint2f();
        Mat mask = new Mat(dst.rows(),dst.cols(),CV_8UC1);
        Imgproc.approxPolyDP(new MatOfPoint2f(matOfPoints.get(0).toArray()),
                matOfPoint2f,20,true);
        matOfPoints.clear();
        matOfPoints.add(new MatOfPoint(matOfPoint2f.toArray()));
        Imgproc.drawContours(mask,matOfPoints,0,new Scalar(255,255,255));

       /* System.out.println(matOfPoint2f.size());
        System.out.println(matOfPoint2f.toArray().length);
        System.out.println(matOfPoint2f.toArray()[0]);
        System.out.println(matOfPoint2f.toArray()[1]);
        System.out.println(matOfPoint2f.toArray()[2]);
        System.out.println(matOfPoint2f.toArray()[3]);
        int i = 1;
        for (Point point : matOfPoint2f.toArray()){
            i+=2;
            Imgproc.circle(mask,point,i,new Scalar(255,255,255));
            Imgproc.circle(srcImage,point,i,new Scalar(255,255,255));
        }
        for (Point point:points){
            System.out.println(point);
        }*/
        //Imgcodecs.imwrite("D:\\tmp\\mask1.jpg",mask);
        //Imgcodecs.imwrite("D:\\tmp\\src1.jpg",srcImage);
        //Mat result = ImageUtils.warpPerspective(srcImage,matOfPoint2f.toArray());
        //Icodecs.imwrite("D:\\tmp\\result.jpg",result);
        //Imgproc.cvtColor(mask,mask,Imgproc.COLOR_GRAY2BGR);
        //System.out.println(mask.type());
        //Mat lines = new Mat();
        //Imgproc.HoughLinesP(mask, lines, 1, Math.PI / 180, 50 );

        /*System.out.println(lines.rows());
        for (i = 0; i < lines.rows(); i++) {
            double[] val = lines.get(i, 0);
            //Imgproc.line(mask, new Point(val[0], val[1]), new Point(val[2], val[3]), new Scalar(255, 255, 255),1);
            //Imgproc.line(srcImage, new Point(val[0], val[1]), new Point(val[2], val[3]), new Scalar(0, 255, 0),1);
        }*/


        List<Point> listSrcs = matOfPoint2f.toList();
        Mat srcPoints = Converters.vector_Point_to_Mat(listSrcs, CvType.CV_32F);

        List<Point> listDsts = Arrays.asList(new Point(srcImage.width(), 0),new Point(srcImage.width(), srcImage.height()),
                new Point(0, srcImage.height()),
                new Point(0, 0)
                );


        Mat dstPoints = Converters.vector_Point_to_Mat(listDsts, CvType.CV_32F);

        Mat perspectiveMmat = Imgproc.getPerspectiveTransform(srcPoints, dstPoints);

        Mat dst1 = new Mat();

        Imgproc.warpPerspective(srcImage, dst1, perspectiveMmat, srcImage.size());

        Imgcodecs.imwrite("D:\\tmp\\dst.jpg", dst1);
        Imgcodecs.imwrite("D:\\tmp\\mask.jpg", mask);
        Imgcodecs.imwrite("D:\\tmp\\src.jpg",srcImage);

    }

}
