package core.dao;
import core.pojo.MedicaiInfo;

public interface MedicaiInfoMapper {
    int insert(MedicaiInfo record);

    int insertSelective(MedicaiInfo record);
}