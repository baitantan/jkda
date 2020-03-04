package core.dao;
import core.pojo.GallergyInfo;

/**
 * @author cs
 */
public interface GallergyInfoMapper {
    int insert(GallergyInfo record);

    int insertSelective(GallergyInfo record);
}