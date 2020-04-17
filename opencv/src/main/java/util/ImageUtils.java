package util;

import com.sun.istack.internal.NotNull;
import exception.PhotoNotTrueException;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Point;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.utils.Converters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.opencv.core.CvType.CV_8UC1;


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
    public static final int NUMBER_START_HEIGHT = 440*  i;
    public static final int NUMBER_END_HEIGHT = 490*  i;
    public static final int NUMBER_START_WIDTH = 290*  i;
    public static final int NUMBER_END_WIDTH = 775*  i;
    //除了姓名 其他字符最大高度等
    public static final int MAX_HEIGHT = 31 * i;
    public static final int MAX_WIDTH = 30 * i;

    public static final int MIN_HEIGHT = 23 * i;
    public static final int MIN_WIDTH = 39;
    //最小间距，间距小于这个的视为一个汉字
    public static final int MIN_SPACE = 7;
    //姓名宽度
    public static final int NAME_MIN_WIDTH = 25 * i;
    public static final int NAME_MAX_WIDTH = 45 * i;

    public static final String INPUT_FILE_PATH = "D:\\photo\\";
    public static final String OUTPUT_FILE_PATH = "D:\\out\\";
    //public Mat mat;
    public static final int OFFSET = 2;
    public static final int UCOFFSET = 20;

    /**
     * 空参构造函数
     */
    public ImageUtils() {

    }


    /**
     * 加载图片
     *
     * @param imgFilePath 等待加载的图像的路径
     */

    public static Mat loadImg(String imgFilePath){
        return Imgcodecs.imread(imgFilePath);
    }





    /**
     * 保存图片的函数
     *
     * @param filename 文件名
     * @return 是否成功保存
     */
    public boolean saveImg(String filename , Mat mat) {
        return Imgcodecs.imwrite(filename, mat);
    }


    /**
     * @param inputImage 待处理的图像
     * @return 灰度化处理完毕的图像
     */
    public static void toGray(Mat inputImage) {

        //使用OpenCV原生方法对图片进行灰度化处理
        Imgproc.cvtColor(inputImage, inputImage, Imgproc.COLOR_BGR2GRAY);

    }


    /**
     * 灰度化
     */
    /*public void toGray() {
        Mat imGray = new Mat();
        Imgproc.cvtColor(mat, imGray, Imgproc.COLOR_BGR2GRAY);
        mat = imGray;
    }*/


    public static Mat binaryzationByOpenCV(Mat mat) {
        Mat mat1 = new Mat();
        Imgproc.threshold(mat, mat1, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
        return mat1;
    }

    /**
     * 使用自适应阈值二值化
     */
    public static int getUCNew(Mat mat) {

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


        return ucThre_new - UCOFFSET;

    }

    public static void  binaryzationByOTSU(Mat mat){
        Imgproc.threshold(mat , mat ,0 ,255 ,Imgproc.THRESH_OTSU);
    }

    /**
     * 使用固定的阈值进行二值化
     */
    public static void binaryzation(int ucTher , Mat mat) {


        if (mat.channels() != 1 || ucTher < 0) {
            System.out.println("不是单通道图像或者阀值异常");
            return;
        }
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



    /**
     * Mat转image
     * @param matrix 待处理的内容
     * @return 返回的image
     */
    public static Image toBufferedImage(Mat matrix) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (matrix.channels()>1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = matrix.channels()*matrix.cols()*matrix.rows();
        byte[] buffer = new byte[bufferSize];
        matrix.get(0, 0, buffer);
        BufferedImage image = new BufferedImage(matrix.cols(), matrix.rows(),type);
        final byte[] targetPxiels = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPxiels, 0, buffer.length);
        return image;
    }

    /**
     * 寻找边缘
     * @return 边缘得集合
     */
/*
    public List<MatOfPoint> findContours(Mat mat){
        List<MatOfPoint> list = new ArrayList<>();
        Imgproc.findContours(mat , list ,new Mat() ,Imgproc.RETR_EXTERNAL , Imgproc.CHAIN_APPROX_SIMPLE);

        return list;
    }*/

    /**
     * 8邻域降噪,又有点像9宫格降噪;即如果9宫格中心被异色包围，则同化
     * @param pNum 默认值为1
     */
    public static void denoise(int pNum , Mat mat) {
        int i, j, m, n, nValue, nCount;
        int nWidth = mat.width(), nHeight = mat.height();

        // 对图像的边缘进行预处理
        for (i = 0; i < nWidth; ++i) {
            mat.put(i, 0, WHITE);
            mat.put(i, nHeight - 1, WHITE);
        }

        for (i = 0; i < nHeight; ++i) {
            mat.put(0, i, WHITE);
            mat.put(nWidth - 1, i, WHITE);
        }

        // 如果一个点的周围都是白色的，而它确是黑色的，删除它
        for (j = 1; j < nHeight - 1; ++j) {
            for (i = 1; i < nWidth - 1; ++i) {
                nValue = (int)mat.get(j, i)[0];
                if (nValue == 0) {
                    nCount = 0;
                    // 比较以(j ,i)为中心的9宫格，如果周围都是白色的，同化
                    for (m = j - 1; m <= j + 1; ++m) {
                        for (n = i - 1; n <= i + 1; ++n) {
                            if ((int) mat.get(m, n)[0] == 0) {
                                nCount++;
                            }
                        }
                    }
                    if (nCount <= pNum) {
                        // 周围黑色点的个数小于阀值pNum,把该点设置白色
                        mat.put(j, i, WHITE);
                    }
                } else {
                    nCount = 0;
                    // 比较以(j ,i)为中心的9宫格，如果周围都是黑色的，同化
                    for (m = j - 1; m <= j + 1; ++m) {
                        for (n = i - 1; n <= i + 1; ++n) {
                            if ((int) mat.get(m, n)[0] == 0) {
                                nCount++;
                            }
                        }
                    }
                    if (nCount >= 7) {
                        // 周围黑色点的个数大于等于7,把该点设置黑色;即周围都是黑色
                        mat.put(j, i, BLACK);
                    }
                }
            }
        }

    }

    /**
     * OpenCV提供的中值滤波降噪算法
     */
    public static void medianBlur(Mat mat){
        Imgproc.medianBlur(mat, mat , 5);
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(str);
        dest = m.replaceAll("");
    }
    return dest;
    }

    /**
     * OPenCV提供的Canny边缘检测
     */
    public static void canny(Mat mat){
        Imgproc.Canny(mat, mat, 50, 150, 3);

    }

    public static void canny(Mat src, int threshold1 , int threshold2, int apertureSize){
        Imgproc.Canny(src, src, threshold1, threshold2, apertureSize);
    }

    /***
     * 将Image变量保存成图片
     * @param im image
     * @param fileName 文件名
      */
    public static void  saveImage(Image im ,String  fileName) {
        int w = im.getWidth(null);
        int h = im.getHeight(null);
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = bi.getGraphics();
        g.drawImage(im, 0, 0, null);
        try {
            ImageIO.write(bi, "jpg", new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将图片大小固定化
     */
    public static Mat photoResize(Mat mat){
        return resize(mat, PHOTO_WIDTH, PHOTN_HEIFHT);

    }


    /**
     * 使用指定的长宽归一化
     * @param src 原图像
     * @param height 目标图像的高度
     * @param width 目标图像的宽度
     * @return 目标图像
     */
    public static Mat resize(Mat src , int width , int height){
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
     * 自适应改变图片大小，降低运算量
     * @param src 原图片
     * @return 结果
     */
    public static Mat resize(Mat src){
        int width = src.width();
        int height = src.height();
        if (width > 1600 && height > 1600){
            int a = Math.max(width , height) / 1600;
            width /= a;
            height /= a;
        }
        return resize(src , width , height);

    }
    /**
     * 图片分割

     * @param start_Height 高度起点
     * @param end_Height  高度终点
     * @param start_Width 宽度起点
     * @param end_Width 宽度终点
     * @return 处理完毕的图像
     */
    public static Mat split(Mat mat, int start_Height , int end_Height , int start_Width , int end_Width){
        Mat result = new Mat();
        Rect rect = new Rect(start_Width, start_Height, end_Width - start_Width, end_Height - start_Height);
        //原理：在生成新的图片时只生成指定的部分
        Mat tmp_Mat = new Mat(mat, rect);
        tmp_Mat.copyTo(result);
        return result;
    }

    /**
     * 对输入的图片进行字符分割，行分割和列分割，适用于住址，民族部分
     * @param src 待处理的图片
     * @return 分割完毕的图片集合
     */
    public static List<Mat> characterSplit(Mat src){
        ArrayList<Mat> result = new ArrayList<>();
        List<Mat> rows = new LinkedList<>();
        int height = src.rows();
        int[] heightNumbers = new int[height];
        int width = src.cols();
        //int[] widthNumbers = new int[width];
        //先进行行分割
        //先统计每一行的黑色像素数目
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                if (src.get(i, j)[0] == 0){
                    heightNumbers[i]++;
                }
            }
        }
        //记录黑色像素跃变的位置
        int[] a = new int[height];
        int j = 0;
        boolean flag = false;
        for (int i = 0; i < height; i++) {
            if (flag != heightNumbers[i] > 0){
                flag = !flag;
                a[j++] = i;
            }
        }
        //最后一行也是黑色像素的情况下
        if (flag){
            a[j++] = height;
        }
        //判断每一行文字的高度是否符合要求
        for (int i = 0; i < j ; i += 2) {
            int start = a[i];
            int end = a[i + 1];
            //高度小于最低要求
            while (end - start <= MIN_HEIGHT && i < j - 2) {
                i += 2;
                end = a[i + 1];
            }
                rows.add(new Mat(src, new Rect(0, start, width, end - start)));
                //为分割点添加横线
                /*for (int s = 0;s < width ; s++){
                    src.put(start , s , BLACK);
                    src.put(end-1 ,s , BLACK);
                }*/

        }

        for (Mat m : rows){
            int mHeight = m.rows();
            int mWidth = m.cols();
            int[] widthNumbers = new  int[mWidth];
            for (int i = 0;i<mWidth;i++){
                for (j=0;j<mHeight;j++){
                    if (m.get(j,i)[0]==BLACK){
                        widthNumbers[i]++;
                    }
                }
            }

            //记录黑色像素跃变的位置
            a = new int[50];
            int k = 0;
            boolean mflag = false;
            for (int i = 0; i < mWidth; i++) {
                if (mflag != (widthNumbers[i] > 0)){
                    mflag = !mflag;
                    a[k++] = i;
                }
            }
            //最后一列也是黑色像素的情况下
            if (mflag){
                a[k++] = mWidth;
            }
            for (int i = 0; i < k ; i += 2){
                int start = i;
                int end = i+1;
                //地址中可能出现数字，二根字，三根字等，
                // 都需要判断是否是数字需要判断它和前后的字符的距离，两个字符之间的距离较大
                while (a[end] - a[start] <= MIN_WIDTH && i < k-2){
                    //第一个有可能是偏旁
                    if (start == 0){
                        i+=2;
                        end+=2;
                        continue;
                    }
                    if (a[start] - a[start - 1] >= MIN_SPACE &&
                    a[end+1] - a[end] >= MIN_SPACE){
                        //这是一个数字
                        result.add(new Mat(m, new Rect(a[start] , 0 ,a[end] - a[start] , mHeight)));
                        /*for (int s = 0;s < mWidth ; s++){
                            src.put(s , a[start] , BLACK);
                            src.put(s , a[end - 1], BLACK);
                        }*/
                        break;
                    }
                    i+=2;
                    end+=2;
                }
                result.add(new Mat(m, new Rect(a[start] , 0 ,a[end] - a[start] , mHeight)));

            }
        }


        return result;
    }

    /**
     * 适用于姓名部分的图片分割（字符更大）
     * @param src 待处理的图片
     * @return 结果
     */
    public static List<Mat> nameCharacterSpilt(Mat src){
        List<Mat> result = new ArrayList<>();
        int height = src.rows();
        int width = src.cols();
        int startHeight = -1, endHeight = -1;
        for (int i=0;i < height;i++){
            for (int j = 0;j< width;j++){
                if (src.get(i ,j)[0] == BLACK){
                    startHeight = i;
                    break;
                }

            }
            if (startHeight > -1){
                break;
            }
        }
        for (int i=height - 1;i >= 0;i--){
            for (int j = 0;j< width;j++){
                if (src.get(i ,j)[0] == BLACK){
                    endHeight = i;
                    break;
                }

            }
            if (endHeight > -1){
                break;
            }
        }
        Mat m = new Mat(src,new Rect(0 , startHeight , width , endHeight - startHeight+1));
        int mHeight = m.rows();
        int mWidth = m.cols();
        int[] widthNumbers = new  int[mWidth];
        for (int i = 0;i<mWidth;i++){
            for (int j=0;j<mHeight;j++){
                if (m.get(j,i)[0]==BLACK){
                    widthNumbers[i]++;
                }
            }
        }

        //记录黑色像素跃变的位置
        int[] a = new int[20];
        int k = 0;
        boolean mflag = false;
        for (int i = 0; i < mWidth; i++) {
            if (mflag != (widthNumbers[i] > 0)){
                mflag = !mflag;
                a[k++] = i;
            }
        }
        //最后一列也是黑色像素的情况下
        if (mflag){
            a[k++] = mWidth;
        }
        for (int i = 0; i < k ; i += 2){
            int start = i;
            int end = i+1;
            //姓名中可能出现二根字，三根字等，
            //姓名中不可能有数字
            while (a[end] - a[start] <= NAME_MIN_WIDTH && i < k-2){
                i+=2;
                end+=2;
            }
            if (a[end] - a[start] > NAME_MAX_WIDTH){
                int i1 = (a[end] - a[start]) / 2;
                result.add(new Mat(m, new Rect(a[start] , 0 , i1, mHeight)));
                result.add(new Mat(m, new Rect(a[start] + i1, 0 , i1, mHeight)));
            }
            result.add(new Mat(m, new Rect(a[start] , 0 ,a[end] - a[start] , mHeight)));
                /*for (int s = 0;s < mWidth ; s++){
                    src.put(s , a[start] , BLACK);
                    src.put(s , a[end - 1], BLACK);
                }*/
        }
        return result;
    }
    public static List<Mat> numberCharacterSpilt(Mat src){
        List<Mat> result = new ArrayList<>();
        int height = src.rows();
        int width = src.cols();
        int startHeight = -1, endHeight = -1;
        //寻找上面的起点
        for (int i=0;i < height;i++){
            for (int j = 0;j< width;j++){
                if (src.get(i ,j)[0] == BLACK){
                    startHeight = i;
                    break;
                }
            }
            if (startHeight > -1){
                break;
            }
        }
        //寻找下面的终点
        for (int i=height - 1;i >= 0;i--){
            for (int j = 0;j< width;j++){
                if (src.get(i ,j)[0] == BLACK){
                    endHeight = i;
                    break;
                }
            }
            if (endHeight > -1){
                break;
            }
        }

        int[] widthNumbers = new  int[width];
        for (int i = 0;i<width;i++){
            for (int j=startHeight;j<endHeight;j++){
                if (src.get(j,i)[0]==BLACK){
                    widthNumbers[i]++;
                }
            }
        }

        //记录黑色像素跃变的位置
        int[] a = new int[40];
        int k = 0;
        boolean mflag = false;
        for (int i = 0; i < width; i++) {
            if (mflag != (widthNumbers[i] > 0)){
                mflag = !mflag;
                a[k++] = i;
            }
        }
        //最后一列也是黑色像素的情况下
        if (mflag){
            a[k++] = width;
        }
        for (int i = 0; i < k ; i += 2) {
        //身份证号中不存在二根字、三根字
            result.add(new Mat(src, new Rect(a[i] , startHeight ,
                    a[i+1] - a[i] , endHeight - startHeight)));
        }
        return result;
    }

    /**
     * doOCR
     */
    public static String doOCR(String fileName , String language){
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("D:\\project\\jkda");
        tesseract.setLanguage(language);

        try {

            return tesseract.doOCR(new File(fileName));

        }catch (TesseractException e){
            System.err.print(e.getMessage());
        }
        return "";
    }

    /**
     * 对自身的mat进行分割
     * @return 结果集
     */
    public static List<Mat> addressCharacterSplit(Mat mat){
        return characterSplit(mat);
    }

    public static List<Mat> nameCharacterSplit(Mat mat){
        return nameCharacterSpilt(mat);
    }

   // public static List<Mat> numberCharacterSpilt(Mat mat){return numberCharacterSpilt(mat);}

    public static List<Mat> nationCharacterSpilt(Mat mat){return characterSplit(mat);}

    /**
     * 寻找轮廓
     * OpenCV3.2.0中提供了查找轮廓的方法：
     * Imgproc.findContours(Mat image, List contours, Mat hierarchy, int mode, int method, Point offset)
     *
     * 参数说明：
     * image：8位单通道图像。
     * contours：存储检测到的轮廓的集合。
     * hierarchy：可选的输出向量，包含了图像轮廓的拓扑信息。
     * mode：轮廓检索模式。有如下几种模式：
     * 1、RETR_EXTERNAL只检测最外围的轮廓
     * 2、RETR_LIST提取所有的轮廓,不建立上下等级关系,只有兄弟等级关系
     * 3、RETR_CCOMP提取所有轮廓,建立为双层结构
     * 4、RETR_TREE提取所有轮廓,建立网状结构
     * method：轮廓的近似方法。取值如下：
     * 1、CHAIN_APPROX_NONE获取轮廓的每一个像素,像素的最大间距不超过1
     * 2、CHAIN_APPROX_SIMPLE压缩水平垂直对角线的元素,只保留该方向的终点坐标(也就是说一条中垂线a-b,中间的点被忽略了)
     * 3、CHAIN_APPROX_TC89_LI使用TEH_CHAIN逼近算法中的LI算法
     * 4、CHAIN_APPROX_TC89_KCOS使用TEH_CHAIN逼近算法中的KCOS算法
     * offset：每个轮廓点的可选偏移量。
     */
    public static List<MatOfPoint> findContours(Mat src){
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(src, contours, hierarchy,  Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE,
                new Point(OFFSET, OFFSET));
        return contours;
    }


    /**
     * 标识出轮廓
     * @param contours 轮廓
     */
    public static void drawContours(Mat mat,List<MatOfPoint> contours){
        for (int i = 0; i < contours.size(); i++)
        {
            Imgproc.drawContours(mat, contours, i, new Scalar(255, 255, 255), 1);
        }
    }


    /**
     * 寻找轮廓，并按照递增排序
     *
     * @param src Canny之后的Mat矩阵
     * @return 递减顺序的轮廓集合
     */
    public static List<MatOfPoint> findContoursAndSort(Mat src)  {
        List<MatOfPoint> contours = findContours(src);
        if (contours.size() < 1 || contours.size() > 100) {
            //throw new FileNotFoundException("未找到身份证轮廓，请检查提交的照片是否符合要求");
        }
                // 对
        // contours进行了排序，按递减顺序
                contours.sort((o1, o2) -> {
                    MatOfPoint2f mat1 = new MatOfPoint2f(o1.toArray());
                    RotatedRect rect1 = Imgproc.minAreaRect(mat1);
                    Rect r1 = rect1.boundingRect();
                    MatOfPoint2f mat2 = new MatOfPoint2f(o2.toArray());
                    RotatedRect rect2 = Imgproc.minAreaRect(mat2);
                    Rect r2 = rect2.boundingRect();
                    return (int) (r2.area() - r1.area());
                });
                return contours;

        }

    /**
     * 作用：返回边缘检测之后的最大轮廓
     *
     * @param src      Canny之后的Mat矩阵
     * @return 最大轮廓
     */
    public static MatOfPoint findMaxContour(Mat src) {
        return findContoursAndSort(src).get(0);
    }


    public static Mat cutIDCard(Mat src){
        src = resize(src);
        Mat dst = src.clone();
        //Imgproc.cvtColor(dst, dst, Imgproc.COLOR_BGRA2GRAY);
        toGray(dst);
        //Imgproc.GaussianBlur(dst , dst , new Size(15,15),0);
        gaussianBlur(dst);
        canny(dst);
        RotatedRect rect = findMaxRect(dst);
        // 旋转矩形
        Mat CorrectImg = rotation(dst , rect);
        Mat NativeCorrectImg = rotation(src , rect);
        Imgcodecs.imwrite("D:\\tmp\\CorrectImg.jpg",CorrectImg);
        Imgcodecs.imwrite("D:\\tmp\\NativeCorrectImg.jpg",NativeCorrectImg);

        //裁剪矩形
        return cutRect(CorrectImg , NativeCorrectImg) ;
    }

    public static Mat wrap(Mat srcImage) throws PhotoNotTrueException {

        List<MatOfPoint> matOfPoints = new ArrayList<>();
        //需要的话缩小原图
        srcImage = ImageUtils.resize(srcImage);
        //dst为edge图片
        Mat dst = ImageUtils.proProcess(srcImage);
        //寻找最大轮廓
        MatOfPoint contour = findMaxContour(dst);
        //最大轮廓的最小外接矩形
        RotatedRect rect = Imgproc.minAreaRect(new MatOfPoint2f(contour.toArray()));
        //寻找凸包
        matOfPoints.add(ImageUtils.convextHull(contour));
        //最小外接矩形的四个顶点，把凸包的四个顶点映射到最小外接矩形的四个顶点
        Point[] points = new Point[4];
        rect.points(points);

        MatOfPoint2f matOfPoint2f = new MatOfPoint2f();
        //创建蒙版，此处只是展示凸包的结果，在识别中不起作用
        Mat mask = new Mat(dst.rows(),dst.cols(),CV_8UC1);
        //逼近凸包，此处应该使用循环逐渐逼近，确保识别出来的是四边形
        //最大最小阈值，固定步长逼近
        //正常来说，必然可以找到四边形，如果不行，也可以使用二分法逼近，根据测试结果判断是否需要二分法
        int max = 100;
        int min = 10;
        do {
            Imgproc.approxPolyDP(new MatOfPoint2f(matOfPoints.get(0).toArray()),
                    matOfPoint2f,min,true);
            min+=5;
            //System.out.println(min);
            //System.out.println(matOfPoint2f.toArray().length);
        }while (matOfPoint2f.toArray().length != 4 && min <= max);
        if (matOfPoint2f.toArray().length != 4){
            throw new PhotoNotTrueException("无法找到四个顶点");
        }
        matOfPoints.clear();
        matOfPoints.add(new MatOfPoint(matOfPoint2f.toArray()));
        //在蒙版中展示逼近的结果
        Imgproc.drawContours(mask,matOfPoints,0,new Scalar(255,255,255));
        Imgcodecs.imwrite("D:\\tmp\\mask.jpg", mask);


        Point[] points1 = matOfPoint2f.toArray();
        //透视变换
        List<Point> listSrcs = Arrays.asList(points1[1],
                points1[2],points1[3],points1[0]);
        //顺序为右上。右下，左下，左上
        Mat srcPoints = Converters.vector_Point_to_Mat(listSrcs, CvType.CV_32F);

        List<Point> listDsts = Arrays.asList(points);


        Mat dstPoints = Converters.vector_Point_to_Mat(listDsts, CvType.CV_32F);

        Mat perspectiveMmat = Imgproc.getPerspectiveTransform(srcPoints, dstPoints);

        Mat dst1 = new Mat();

        Imgproc.warpPerspective(srcImage, dst1, perspectiveMmat, srcImage.size());

        Imgcodecs.imwrite("D:\\tmp\\dst.jpg", dst1);
        Imgcodecs.imwrite("D:\\tmp\\src.jpg",srcImage);

        //Mat dst2 = dst1.clone();
        //Imgproc.cvtColor(dst, dst, Imgproc.COLOR_BGRA2GRAY);
        //toGray(dst2);
        //Imgproc.GaussianBlur(dst , dst , new Size(15,15),0);
        //gaussianBlur(dst2);
        //canny(dst2);
        //Imgcodecs.imwrite("D:\\tmp\\dst2.jpg", dst2);
        //RotatedRect rect = findMaxRect(dst1);
        // 旋转矩形
        //Mat CorrectImg = rotation(dst2 , rect);
        Mat NativeCorrectImg = rotation(dst1 , rect);
        //Imgcodecs.imwrite("D:\\tmp\\CorrectImg.jpg",CorrectImg);
        Imgcodecs.imwrite("D:\\tmp\\NativeCorrectImg.jpg",NativeCorrectImg);
        //裁剪矩形
        int width = Math.max((int) rect.size.width,(int)rect.size.height);
        int height = Math.min((int) rect.size.width,(int)rect.size.height);
        Point center = rect.center;
        int startLeft = (int) center.x - width / 2;
        int startUp = (int)center.y - height / 2;
        //Imgcodecs.imwrite("D:\\out\\nativeCorrectMat2.jpg",nativeCorrectMat);
        Mat temp = new Mat(NativeCorrectImg , new Rect(startLeft , startUp , width , height ));
        Mat t = new Mat();
        temp.copyTo(t);
        return t;
    }

    /**
     * 返回边缘检测之后的最大矩形
     *
     * @param src Canny之后的mat矩阵
     * @return 最大矩形
     */
    public static RotatedRect findMaxRect(@NotNull Mat src) {
        return Imgproc.minAreaRect(new MatOfPoint2f(findMaxContour(src).toArray()));
    }
    /**
     * 旋转矩形
     *
     * @param src mat矩阵
     * @param rect 矩形
     * @return 旋转完毕的结果
     */
    public static Mat rotation(Mat src, RotatedRect rect) {
        // 获取矩形的四个顶点
        Point[] rectPoint = new Point[4];
        rect.points(rectPoint);
        double angle = rect.angle;
        if (rect.size.width < rect.size.height ){
            angle += 90;
        }

        Point center = rect.center;

        Mat CorrectImg = new Mat(src.size(), src.type());

        src.copyTo(CorrectImg);

        // 得到旋转矩阵算子
        Mat matrix = Imgproc.getRotationMatrix2D(center, angle, 1);

        Imgproc.warpAffine(CorrectImg, CorrectImg, matrix, CorrectImg.size());

        return CorrectImg;
    }


    /**
     * 旋转180°
     * @param src 原图像
     * @return 结果
     */
    public static Mat rotation180(Mat src){
        Mat matrix = Imgproc.getRotationMatrix2D(new Point(src.width() / 2 , src.height() / 2)
        ,180 , 1);
        Imgproc.warpAffine(src , src , matrix , src.size() , 1 , 0 , new Scalar(0,0,0));
        return src;
    }

    public static Mat rotation180AndScale(Mat src){
        Mat matrix = Imgproc.getRotationMatrix2D(new Point(src.width() / 2 , src.height() / 2)
                ,180 , 0.9);
        Imgproc.warpAffine(src , src , matrix , src.size() , 1 , 0 , new Scalar(0,0,0));
        return src;
    }

    /**
     * 从原图片中裁切出正确的身份证图片
     * @param correctMat 旋转完毕的边缘检测图片
     * @param nativeCorrectMat 旋转完毕的原图片
     * @return 正确的身份证图片，但可能仍然需要反转180°
     */
    public static Mat cutRect(Mat correctMat , Mat nativeCorrectMat) {
        // 获取最大矩形
        RotatedRect rect = findMaxRect(correctMat);

        Point[] rectPoint = new Point[4];
        rect.points(rectPoint);
        int startLeft = (int)Math.abs(rectPoint[0].x);
        int startUp = (int)Math.min(rectPoint[0].y , rectPoint[1].y);
        int width = (int)Math.abs(rectPoint[2].x - rectPoint[0].x);
        int height = (int)Math.abs(rectPoint[1].y - rectPoint[0].y);
        //Imgcodecs.imwrite("D:\\out\\nativeCorrectMat2.jpg",nativeCorrectMat);
        Mat temp = new Mat(nativeCorrectMat , new Rect(startLeft , startUp , width , height ));
        Mat t = new Mat();
        temp.copyTo(t);
        return t;
    }
    /**
     * 利用函数approxPolyDP来对指定的点集进行逼近 精确度设置好，效果还是比较好的
     * 主要功能是把一个连续光滑曲线折线化：
     * 参数有4个：
     * InputArray curve：输入曲线，数据类型可以为vector<Point>。
     * OutputArray approxCurve：输出折线，数据类型可以为vector<Point>。
     * double epsilon：判断点到相对应的line segment 的距离的阈值。（距离大于此阈值则舍弃，小于此阈值则保留，epsilon越小，折线的形状越“接近”曲线。）
     * bool closed：曲线是否闭合的标志位。
     * @param src 待处理
     */
    public static Point[] useApproxPolyDPFindPoints(Mat src) {
        return useApproxPolyDPFindPoints(src, 0.01);
    }

    /**
     * 利用函数approxPolyDP来对指定的点集进行逼近 精确度设置好，效果还是比较好的
     *
     * @param src 待处理
     * @param threshold 阀值(精确度)
     * @return 结果
     */
    public static Point[] useApproxPolyDPFindPoints(Mat src, double threshold) {

        MatOfPoint maxContour = findMaxContour(src);
        MatOfPoint2f approxCurve = new MatOfPoint2f();
        MatOfPoint2f matOfPoint2f = new MatOfPoint2f(maxContour.toArray());
        // 原始曲线与近似曲线之间的最大距离设置为0.01，true表示是闭合的曲线
        Imgproc.approxPolyDP(matOfPoint2f, approxCurve, threshold, true);
        return approxCurve.toArray();

    }


    /**
     * 高斯滤波
     */
    public static void gaussianBlur(Mat src){
        /*src：输入源图像
        dst：输出目标图像
        ksize：内核模板大小
        sigmaX：高斯内核在X方向的标准偏差
        sigmaY：高斯内核在Y方向的标准偏差。如果sigmaY为0，他将和sigmaX的值相同，如果他们都为0，那么他们由ksize.width和ksize.height计算得出
        borderType： 用于判断图像边界的模式*/
        Imgproc.GaussianBlur(src, src, new Size(15,15), 0, 0, Core.BORDER_DEFAULT);
    }
    /**
     * 透视变换
     * @param src
     * @param points
     * @return 结果
     */
    public static Mat warpPerspective(Mat src , Point[] points) {

        // 点的顺序[左上 ，右上 ，右下 ，左下]
        //传过来的点的顺序事右上 右下 左下 左上
        List<Point> listSrcs = Arrays.asList(points[2], points[3], points[1], points[0]);
        Mat srcPoints = Converters.vector_Point_to_Mat(listSrcs, CvType.CV_32F);

        List<Point> listDsts = Arrays.asList(new Point(0, 0), new Point(src.width(), 0),
                new Point(src.width(), src.height()), new Point(0, src.height()));


        Mat dstPoints = Converters.vector_Point_to_Mat(listDsts, CvType.CV_32F);

        Mat perspectiveMmat = Imgproc.getPerspectiveTransform(srcPoints, dstPoints);

        Mat dst = new Mat();

        Imgproc.warpPerspective(src, dst, perspectiveMmat, src.size());

        return dst;
    }

    /**
     * 前期处理
     * @param src1 完全未处理的src
     * @return edge
     */
    public static Mat proProcess(Mat src1){
        src1 = resize(src1);
        Mat src = src1.clone();
        toGray(src);
        gaussianBlur(src);
        canny(src);
        return src;
    }

    /**
     * 只保留身份证轮廓
     * @param src 未进行任何处理的原图
     * @return 只含有身份证轮廓的二值化图片
     */
    public static Mat onlyContours(Mat src){
        src = proProcess(src);
        MatOfPoint contour = findMaxContour(src);
        int i, j;
        int width = src.width(), height = src.height();
        for (j = 0; j < height; ++j) {
            for (i = 0; i < width; ++i) {
                    src.put(j, i, BLACK);
                }
            }
        List<MatOfPoint> contours = new ArrayList<>();
        contours.add(contour);
        drawContours(src, contours);
        return src;

    }

    /**
     * 寻找凸包
     * @param src 轮廓
     * @return 结果
     */
    public static MatOfPoint convextHull(MatOfPoint src){
        MatOfInt result = new MatOfInt();
        /*
        void convexHull(InputArray points,OutputArray hull,bool clockwise = false,bool returnPoints = true)
        @第一个参数，InputArray类型的Points，输入的二维点集，可以填Mat类型或者std::vector
        @第二个参数，OutputArray类型的Hull，输出参数，函数调用后找到的凸包
        @第三个参数，bool类型的clockwise，操作方向标识符。当此标志符为真时，输出的凸包为顺时针方向，
        否则就为逆时针方向。并且是假定坐标系的x轴指向右，y轴指向上方
        @第四个参数，bool类型的returnPoints，操作符标识，默认为true。
        当标识符为真时，函数返回各个凸包的各个点。否则，它返回凸包各点的指数。当输出数组是std：：vector时，此标志被忽略
         */
        Imgproc.convexHull(src , result);
        return convertIndexesToPoints(src, result);
    }

    public static MatOfPoint convertIndexesToPoints(MatOfPoint contour, MatOfInt indexes) {
        int[] arrIndex = indexes.toArray();
        Point[] arrContour = contour.toArray();
        Point[] arrPoints = new Point[arrIndex.length];

        for (int i=0;i<arrIndex.length;i++) {
            arrPoints[i] = arrContour[arrIndex[i]];
        }

        MatOfPoint hull = new MatOfPoint();
        hull.fromArray(arrPoints);
        return hull;
    }
}
