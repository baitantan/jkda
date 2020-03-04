package core.dao;
import core.pojo.Medication_info;

public interface MedicationInfoMapper {
    int insert(Medication_info record);

    int insertSelective(Medication_info record);
}