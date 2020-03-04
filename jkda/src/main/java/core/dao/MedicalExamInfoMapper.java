package core.dao;
import core.pojo.MedicalExamInfo;

public interface MedicalExamInfoMapper {
    int insert(MedicalExamInfo record);

    int insertSelective(MedicalExamInfo record);
}