import exception.PhotoNotTrueException;
import exception.ServerErrorException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class DoOCRTest {



    @Test
    public void doOCRTest69b21270ac573450() throws ServerErrorException, PhotoNotTrueException {
        /*Map<String, String> map =
                DoOCR.doOCR("D:\\photo\\IMG_20200409_150723.jpg");
        Assert.assertTrue(map.containsKey("name"));
        Assert.assertEquals("陈帅" , map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        Assert.assertEquals("汉" , map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        //Assert.assertEquals("河南省叶县邓李乡吕庄五组" , map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        Assert.assertEquals("410422199812207638" , map.get("number"));*/
        Map<String,String > map =
                DoOCR.doOCR("D:\\photo\\69b21270ac573450.jpg");
        Assert.assertTrue(map.containsKey("name"));
        System.out.println(map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        System.out.println(map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        System.out.println(map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        System.out.println(map.get("number"));
    }
    @Test
    public void doOCRTest20200330153519() throws ServerErrorException, PhotoNotTrueException {
        /*Map<String, String> map =
                DoOCR.doOCR("D:\\photo\\IMG_20200409_150723.jpg");
        Assert.assertTrue(map.containsKey("name"));
        Assert.assertEquals("陈帅" , map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        Assert.assertEquals("汉" , map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        //Assert.assertEquals("河南省叶县邓李乡吕庄五组" , map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        Assert.assertEquals("410422199812207638" , map.get("number"));*/
        Map<String,String > map =
                DoOCR.doOCR("D:\\photo\\20200330153519.jpg");
        Assert.assertTrue(map.containsKey("name"));
        System.out.println(map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        System.out.println(map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        System.out.println(map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        System.out.println(map.get("number"));
    }
    @Test
    public void doOCRTestall() throws ServerErrorException, PhotoNotTrueException {
        /*Map<String, String> map =
                DoOCR.doOCR("D:\\photo\\IMG_20200409_150723.jpg");
        Assert.assertTrue(map.containsKey("name"));
        Assert.assertEquals("陈帅" , map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        Assert.assertEquals("汉" , map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        //Assert.assertEquals("河南省叶县邓李乡吕庄五组" , map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        Assert.assertEquals("410422199812207638" , map.get("number"));*/
        Map<String,String > map =
                DoOCR.doOCR("D:\\photo\\all.jpg");
        Assert.assertTrue(map.containsKey("name"));
        System.out.println(map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        System.out.println(map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        System.out.println(map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        System.out.println(map.get("number"));
    }
    @Test
    public void doOCRTestclose() throws ServerErrorException, PhotoNotTrueException {
        /*Map<String, String> map =
                DoOCR.doOCR("D:\\photo\\IMG_20200409_150723.jpg");
        Assert.assertTrue(map.containsKey("name"));
        Assert.assertEquals("陈帅" , map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        Assert.assertEquals("汉" , map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        //Assert.assertEquals("河南省叶县邓李乡吕庄五组" , map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        Assert.assertEquals("410422199812207638" , map.get("number"));*/
        Map<String,String > map =
                DoOCR.doOCR("D:\\photo\\close.jpg");
        Assert.assertTrue(map.containsKey("name"));
        System.out.println(map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        System.out.println(map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        System.out.println(map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        System.out.println(map.get("number"));
    }
    @Test
    public void doOCRTesthigh() throws ServerErrorException, PhotoNotTrueException {
        /*Map<String, String> map =
                DoOCR.doOCR("D:\\photo\\IMG_20200409_150723.jpg");
        Assert.assertTrue(map.containsKey("name"));
        Assert.assertEquals("陈帅" , map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        Assert.assertEquals("汉" , map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        //Assert.assertEquals("河南省叶县邓李乡吕庄五组" , map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        Assert.assertEquals("410422199812207638" , map.get("number"));*/
        Map<String,String > map =
                DoOCR.doOCR("D:\\photo\\high.jpg");
        Assert.assertTrue(map.containsKey("name"));
        System.out.println(map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        System.out.println(map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        System.out.println(map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        System.out.println(map.get("number"));
    }
    @Test
    public void doOCRTestIMG_20200330_153849() throws ServerErrorException, PhotoNotTrueException {
        /*Map<String, String> map =
                DoOCR.doOCR("D:\\photo\\IMG_20200409_150723.jpg");
        Assert.assertTrue(map.containsKey("name"));
        Assert.assertEquals("陈帅" , map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        Assert.assertEquals("汉" , map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        //Assert.assertEquals("河南省叶县邓李乡吕庄五组" , map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        Assert.assertEquals("410422199812207638" , map.get("number"));*/
        Map<String,String > map =
                DoOCR.doOCR("D:\\photo\\IMG_20200330_153849.jpg");
        Assert.assertTrue(map.containsKey("name"));
        System.out.println(map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        System.out.println(map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        System.out.println(map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        System.out.println(map.get("number"));
    }
    @Test
    public void doOCRTestIMG_20200409_150723() throws ServerErrorException, PhotoNotTrueException {
        /*Map<String, String> map =
                DoOCR.doOCR("D:\\photo\\IMG_20200409_150723.jpg");
        Assert.assertTrue(map.containsKey("name"));
        Assert.assertEquals("陈帅" , map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        Assert.assertEquals("汉" , map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        //Assert.assertEquals("河南省叶县邓李乡吕庄五组" , map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        Assert.assertEquals("410422199812207638" , map.get("number"));*/
        Map<String,String > map =
                DoOCR.doOCR("D:\\photo\\IMG_20200409_150723.jpg");
        Assert.assertTrue(map.containsKey("name"));
        System.out.println(map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        System.out.println(map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        System.out.println(map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        System.out.println(map.get("number"));
    }
    @Test
    public void doOCRTestIMG_20200409_151605() throws ServerErrorException, PhotoNotTrueException {
        /*Map<String, String> map =
                DoOCR.doOCR("D:\\photo\\IMG_20200409_150723.jpg");
        Assert.assertTrue(map.containsKey("name"));
        Assert.assertEquals("陈帅" , map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        Assert.assertEquals("汉" , map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        //Assert.assertEquals("河南省叶县邓李乡吕庄五组" , map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        Assert.assertEquals("410422199812207638" , map.get("number"));*/
        Map<String,String > map =
                DoOCR.doOCR("D:\\photo\\IMG_20200409_151605.jpg");
        Assert.assertTrue(map.containsKey("name"));
        System.out.println(map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        System.out.println(map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        System.out.println(map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        System.out.println(map.get("number"));
    }
    @Test
    public void doOCRTestIMG_20200416_120456() throws ServerErrorException, PhotoNotTrueException {
        /*Map<String, String> map =
                DoOCR.doOCR("D:\\photo\\IMG_20200409_150723.jpg");
        Assert.assertTrue(map.containsKey("name"));
        Assert.assertEquals("陈帅" , map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        Assert.assertEquals("汉" , map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        //Assert.assertEquals("河南省叶县邓李乡吕庄五组" , map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        Assert.assertEquals("410422199812207638" , map.get("number"));*/
        Map<String,String > map =
                DoOCR.doOCR("D:\\photo\\IMG_20200416_120456.jpg");
        Assert.assertTrue(map.containsKey("name"));
        System.out.println(map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        System.out.println(map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        System.out.println(map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        System.out.println(map.get("number"));
    }
    @Test
    public void doOCRTestIMG_20200416_124038() throws ServerErrorException, PhotoNotTrueException {
        /*Map<String, String> map =
                DoOCR.doOCR("D:\\photo\\IMG_20200409_150723.jpg");
        Assert.assertTrue(map.containsKey("name"));
        Assert.assertEquals("陈帅" , map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        Assert.assertEquals("汉" , map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        //Assert.assertEquals("河南省叶县邓李乡吕庄五组" , map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        Assert.assertEquals("410422199812207638" , map.get("number"));*/
        Map<String,String > map =
                DoOCR.doOCR("D:\\photo\\IMG_20200416_124038.jpg");
        Assert.assertTrue(map.containsKey("name"));
        System.out.println(map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        System.out.println(map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        System.out.println(map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        System.out.println(map.get("number"));
    }
    @Test
    public void doOCRTestnormal() throws ServerErrorException, PhotoNotTrueException {
        /*Map<String, String> map =
                DoOCR.doOCR("D:\\photo\\IMG_20200409_150723.jpg");
        Assert.assertTrue(map.containsKey("name"));
        Assert.assertEquals("陈帅" , map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        Assert.assertEquals("汉" , map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        //Assert.assertEquals("河南省叶县邓李乡吕庄五组" , map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        Assert.assertEquals("410422199812207638" , map.get("number"));*/
        Map<String,String > map =
                DoOCR.doOCR("D:\\photo\\normal.jpg");
        Assert.assertTrue(map.containsKey("name"));
        System.out.println(map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        System.out.println(map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        System.out.println(map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        System.out.println(map.get("number"));
    }
    @Test
    public void doOCRTestpertect() throws ServerErrorException, PhotoNotTrueException {
        /*Map<String, String> map =
                DoOCR.doOCR("D:\\photo\\IMG_20200409_150723.jpg");
        Assert.assertTrue(map.containsKey("name"));
        Assert.assertEquals("陈帅" , map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        Assert.assertEquals("汉" , map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        //Assert.assertEquals("河南省叶县邓李乡吕庄五组" , map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        Assert.assertEquals("410422199812207638" , map.get("number"));*/
        Map<String,String > map =
                DoOCR.doOCR("D:\\photo\\pertect.jpg");
        Assert.assertTrue(map.containsKey("name"));
        System.out.println(map.get("name"));
        Assert.assertTrue(map.containsKey("nation"));
        System.out.println(map.get("nation"));
        Assert.assertTrue(map.containsKey("address"));
        System.out.println(map.get("address"));
        Assert.assertTrue(map.containsKey("number"));
        System.out.println(map.get("number"));
    }


}
