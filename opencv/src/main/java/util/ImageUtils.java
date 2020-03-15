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

    public static final int i = 2;
    //第二代身份证宽54.0mm
    public static final int PHOTN_HEIFHT = 540 *  i;
    //第二代身份证长85.6mm
    public static final int PHOTO_WIDTH = 856*  i;
    //
    public static final int WORD_HEIGHT = 30;
    public static final int WORD_WIDTH = 40;
    //姓名所在区域
    public static final int NAME_START_HEIGHT = 60*  i;
    public static final int NAME_END_HEIGHT = 105*  i;
    public static final int NAME_START_WIDTH = 155*  i;
    public static final int NAME_END_WIDTH = 380*  i;
    //民族所在区域
    public static final int NATION_START_HEIGHT = 135*  i;
    public static final int NATION_END_HEIGHT = 170*  i;
    public static final int NATION_START_WIDTH = 325*  i;
    public static final int NATION_END_WIDTH = 495*  i;
    //地址所在区域
    public static final int ADDRESS_START_HEIGHT = 270*  i;
    public static final int ADDRESS_END_HEIGHT = 430*  i;
    public static final int ADDRESS_START_WIDTH = 155*  i;
    public static final int ADDRESS_END_WIDTH = 510*  i;
    //身份证号码所在区域
    public static final int NUMBER_START_HEIGHT = 450*  i;
    public static final int NUMBER_END_HEIGHT = 490*  i;
    public static final int NUMBER_START_WIDTH = 290*  i;
    public static final int NUMBER_END_WIDTH = 775*  i;

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

    public  ImageUtils(Mat mat) {
        this.mat = mat;
    }

    /**
     * 加载图片
     *
     * @param imgFilePath 等待加载的图像的路径
     */
    public void loadImg(String imgFilePath) {
        mat = Imgcodecs.imread(imgFilePath);
    }

    /**
     * 获取图片高度的函数
     *
     * @return 图片高度
     */
    public int getHeight() {
        return mat.rows();
    }

    /**
     * 获取图片宽度的函数
     *
     * @return 图片宽度
     */
    public int getWidth() {
        return mat.cols();
    }

    /**
     * 获取图片像素点的函数
     *
     * @param y 行
     * @param x 列
     * @return 某像素点的值
     */
    public int getPixel(int y, int x) {
        // 我们处理的是单通道灰度图
        return (int) mat.get(y, x)[0];
    }

    /**
     * 设置图片像素点的函数
     *
     * @param y 行
     * @param x 列
     * @param color 需要设置的值
     */
    public void setPixel(int y, int x, int color) {
        // 我们处理的是单通道灰度图
        mat.put(y, x, color);
    }

    /**
     * 保存图片的函数
     *
     * @param filename 文件名
     * @return 是否成功保存
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
        return Imgproc.minAreaRect(new MatOfPoint2f(findContours().get(0)));

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

            return tesseract.doOCR(new File(fileName));

        }catch (TesseractException e){
            System.err.print(e.getMessage());
        }
        return null;
    }



    /**
     * OpenCV提供的中值滤波降噪算法
     */
    public void medianBlur(){
        Mat mat = new Mat();
        Imgproc.medianBlur(this.mat, mat , 5);
        this.mat = mat;
    }

    /**
     * OPenCV提供的Canny降噪方法
     */
    public void canny(){
        Mat mat = new Mat();
        Imgproc.Canny(this.mat, mat, 50, 150, 3);
        this.mat = mat;

    }

    /**
     * 将图片大小固定化
     */
    public void photoResize(){
        this.mat = resize(this.mat, PHOTO_WIDTH, PHOTN_HEIFHT);
    }


    /**
     * 使用指定的长宽归一化
     * @param src 原图像
     * @param height 目标图像的高度
     * @param width 目标图像的宽度
     * @return 目标图像
     */
    public Mat resize(Mat src , int width , int height){
        Mat dst = new Mat();
        //OpenCV提供的默认归一化方法
        Imgproc.resize(src, dst, new Size(width , height));
        //OpenCV提供的可以指定更多参数的归一化方法
        //src: 待改变大小的图像
        //dst: 输出的目标图像
        //dsize: 目标图像的尺寸
        //fx：width方向的缩放比例，如果它是0，那么它就会按照
        //(double)dsize.width/src.cols来计算
        //fy：height方向的缩放比例，如果它是0，那么它就会按照(double)dsize.height/src.rows来计算
        //interpolation：这个是指定插值的方式，图像缩放之后，肯定像素要进行重新计算的，就靠这个参数来指定重新计算像素的方式，有以下几种：
        //INTER_NEAREST - 最邻近插值
        //INTER_LINEAR - 双线性插值，如果最后一个参数你不指定，默认使用这种方法
        //INTER_AREA - 区域插值; 区域插值分为3种情况。图像放大时类似于线性插值，图像缩小时可以避免波纹出现。
        //INTER_CUBIC -基于4x4像素邻域的3次插值法
        //INTER_LANCZOS4 - 8x8像素邻域内的Lanczos插值
        //Imgproc.resize(src, dst, dsize, 0, 0, Imgproc.INTER_AREA);
        return dst;
    }

    /**
     * 图片分割

     * @param start_Height 高度起点
     * @param end_Height  高度终点
     * @param start_Width 宽度起点
     * @param end_Width 宽度终点
     * @return 处理完毕的图像
     */
    public Mat split(int start_Height , int end_Height , int start_Width , int end_Width){
        Mat result = new Mat();
        Rect rect = new Rect(start_Width, start_Height, end_Width - start_Width, end_Height - start_Height);
        //原理：在生成新的图片时只生成指定的部分
        Mat tmp_Mat = new Mat(this.mat, rect);
        tmp_Mat.copyTo(result);
        return result;
    }
}
