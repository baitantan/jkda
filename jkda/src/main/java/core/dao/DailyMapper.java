package core.dao;


import core.pojo.Daily;

public interface DailyMapper {
    int insert(Daily record);

    int insertSelective(Daily record);
}