package util;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



/**
 * @author chenshuai
 * @date 2020/2/10 14:53
 * ImageUtils.class
 */
public class ImageUtils {
    private static final int BLACK = 0;
    private static final int WHITE = 255;


    private Mat mat;

    /**
     * 空参构造函数
     */
    public ImageUtils() {

    }

    /**
     * 通过图像路径创建一个mat矩阵
     *
     * @param imgFilePath 图像路径
     */
    public ImageUtils(String imgFilePath) {
        mat = Imgcodecs.imread(imgFilePath);
    }

    public void ImageUtils(Mat mat) {
        this.mat = mat;
    }

    /**
     * 加载图片
     *
     * @param imgFilePath
     */
    public void loadImg(String imgFilePath) {
        mat = Imgcodecs.imread(imgFilePath);
    }

    /**
     * 获取图片高度的函数
     *
     * @return
     */
    public int getHeight() {
        return mat.rows();
    }

    /**
     * 获取图片宽度的函数
     *
     * @return
     */
    public int getWidth() {
        return mat.cols();
    }

    /**
     * 获取图片像素点的函数
     *
     * @param y
     * @param x
     * @return
     */
    public int getPixel(int y, int x) {
        // 我们处理的是单通道灰度图
        return (int) mat.get(y, x)[0];
    }

    /**
     * 设置图片像素点的函数
     *
     * @param y
     * @param x
     * @param color
     */
    public void setPixel(int y, int x, int color) {
        // 我们处理的是单通道灰度图
        mat.put(y, x, color);
    }

    /**
     * 保存图片的函数
     *
     * @param filename
     * @return
     */
    public boolean saveImg(String filename) {
        return Imgcodecs.imwrite(filename, mat);
    }


    /**
     * @param inputImage 待处理的图像
     * @return 灰度化处理完毕的图像
     */
    public static Mat toGray(Mat inputImage) {
        Mat imGray = new Mat();
        //使用OpenCV原生方法对图片进行灰度化处理
        Imgproc.cvtColor(inputImage, imGray, Imgproc.COLOR_BGR2GRAY);
        return imGray;
    }


    /**
     * 灰度化
     */
    public void toGray() {
        Mat imGray = new Mat();
        Imgproc.cvtColor(mat, imGray, Imgproc.COLOR_BGR2GRAY);
        mat = imGray;
    }


    public void binaryzationByOpenCV() {
        Mat mat1 = new Mat();
        Imgproc.threshold(mat, mat1, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        mat = mat1;
    }

    /**
     * 使用自适应阈值二值化
     */
    public int getUCNew() {

        int ucThre = 0, ucThre_new = 127;
        int nBack_count, nData_count;
        int nBack_sum, nData_sum;
        int nValue;
        int i, j;

        int width = mat.width(), height = mat.height();
        //寻找最佳的阙值
        while (ucThre != ucThre_new) {
            nBack_sum = nData_sum = 0;
            nBack_count = nData_count = 0;

            for (j = 0; j < height; ++j) {
                for (i = 0; i < width; i++) {
                    nValue = (int) mat.get(j, i)[0];

                    if (nValue > ucThre_new) {
                        nBack_sum += nValue;
                        nBack_count++;
                    } else {
                        nData_sum += nValue;
                        nData_count++;
                    }
                }
            }

            nBack_sum = nBack_sum / nBack_count;
            nData_sum = nData_sum / nData_count;
            ucThre = ucThre_new;
            ucThre_new = (nBack_sum + nData_sum) / 2;
        }


        return ucThre_new;

    }

    public void binaryzationByOTSU(){
        Mat mat = new Mat();
        Imgproc.threshold(this.mat , mat ,0 ,255 ,Imgproc.THRESH_OTSU);
        this.mat = mat;
    }

    /**
     * 使用固定的阈值进行二值化
     */
    public void binaryzation(int ucTher) {


        int i, j, avg;
        int nValue;
        int width = mat.width(), height = mat.height();
        //二值化处理
        int nBlack = 0;
        int nWhite = 0;
        for (j = 0; j < height; ++j) {
            for (i = 0; i < width; ++i) {
                nValue = (int) mat.get(j, i)[0];
                if (nValue > ucTher) {
                    mat.put(j, i, WHITE);
                    nWhite++;
                } else {
                    /*avg = 0;
                    //对以(j,i)为中心的7*7模板进行考察
                    for (int p = j - 3; p <= j+3 ; p++) {
                        for (int q = i - 3; q <= i + 3; q++) {
                            avg += (int) mat.get(p, q)[0];
                        }
                    }
                    if ((int) (mat.get(j, i)[0]) > avg) {
                        mat.put(j, i, WHITE);
                        nWhite++;
                    }else {
                        mat.put(j, i, BLACK);
                        nBlack++;
                    }*/
                    mat.put(j, i, BLACK);
                    nBlack++;
                }
            }
        }

        // 确保白底黑字
        if (nBlack > nWhite) {
            for (j = 0; j < height; ++j) {
                for (i = 0; i < width; ++i) {
                    nValue = (int) (mat.get(j, i)[0]);
                    if (nValue == BLACK) {
                        mat.put(j, i, WHITE);
                    } else {
                        mat.put(j, i, BLACK);
                    }
                }
            }
        }

    }




    public List<MatOfPoint> findContours(){
        List<MatOfPoint> list = new ArrayList<>();
        Imgproc.findContours(this.mat , list ,new Mat() ,Imgproc.RETR_EXTERNAL , Imgproc.CHAIN_APPROX_SIMPLE);

        return list;
    }

    public void drawContours(){
        List<MatOfPoint> list = findContours();
        Imgproc.drawContours(this.mat, list , -1 ,new Scalar(255 ,0 , 0), 3);
    }


    public RotatedRect minAreaRect(){
        RotatedRect rotatedRect = Imgproc.minAreaRect(new MatOfPoint2f(findContours().get(0)));
        return rotatedRect;
    }


    /**
     * 8邻域降噪,又有点像9宫格降噪;即如果9宫格中心被异色包围，则同化
     * @param pNum 默认值为1
     */
    public void denoise(int pNum) {
        int i, j, m, n, nValue, nCount;
        int nWidth = getWidth(), nHeight = getHeight();

        // 对图像的边缘进行预处理
        for (i = 0; i < nWidth; ++i) {
            setPixel(i, 0, WHITE);
            setPixel(i, nHeight - 1, WHITE);
        }

        for (i = 0; i < nHeight; ++i) {
            setPixel(0, i, WHITE);
            setPixel(nWidth - 1, i, WHITE);
        }

        // 如果一个点的周围都是白色的，而它确是黑色的，删除它
        for (j = 1; j < nHeight - 1; ++j) {
            for (i = 1; i < nWidth - 1; ++i) {
                nValue = getPixel(j, i);
                if (nValue == 0) {
                    nCount = 0;
                    // 比较以(j ,i)为中心的9宫格，如果周围都是白色的，同化
                    for (m = j - 1; m <= j + 1; ++m) {
                        for (n = i - 1; n <= i + 1; ++n) {
                            if (getPixel(m, n) == 0) {
                                nCount++;
                            }
                        }
                    }
                    if (nCount <= pNum) {
                        // 周围黑色点的个数小于阀值pNum,把该点设置白色
                        setPixel(j, i, WHITE);
                    }
                } else {
                    nCount = 0;
                    // 比较以(j ,i)为中心的9宫格，如果周围都是黑色的，同化
                    for (m = j - 1; m <= j + 1; ++m) {
                        for (n = i - 1; n <= i + 1; ++n) {
                            if (getPixel(m, n) == 0) {
                                nCount++;
                            }
                        }
                    }
                    if (nCount >= 7) {
                        // 周围黑色点的个数大于等于7,把该点设置黑色;即周围都是黑色
                        setPixel(j, i, BLACK);
                    }
                }
            }
        }

    }


    /**
     * doOCR
     */
    public static String doOCR(String fileName){
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



    public void medianBlur(){
        Mat mat = new Mat();
        Imgproc.medianBlur(this.mat, mat , 5);
        this.mat = mat;
    }
    public void Canny(){
        Mat mat = new Mat();
        Imgproc.Canny(this.mat, mat, 50, 150, 3);
        this.mat = mat;

    }
}
