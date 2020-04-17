import org.junit.Assert;

public class IDCardNoVerifyTest {
    @org.junit.Test
    public void IDCardNoVerifyTest1(){
        Assert.assertTrue(DoOCR.IDCardNoVerify("410422199812207638"));
        Assert.assertTrue(DoOCR.IDCardNoVerify("142430199811021635"));
        Assert.assertTrue(DoOCR.IDCardNoVerify("41282619970407171X"));
        Assert.assertFalse(DoOCR.IDCardNoVerify("410422199812207633"));
        Assert.assertFalse(DoOCR.IDCardNoVerify("410422199812227638"));
        Assert.assertFalse(DoOCR.IDCardNoVerify("41042219981220763F"));

    }
}
