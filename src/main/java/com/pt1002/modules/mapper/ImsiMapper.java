package com.pt1002.modules.mapper;

import com.pt1002.modules.pojo.Imsi;
import com.pt1002.modules.pojo.ImsiExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImsiMapper {
    int countByExample(ImsiExample example);

    int deleteByExample(ImsiExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Imsi record);

    int insertSelective(Imsi record);

    List<Imsi> selectByExample(ImsiExample example);

    Imsi selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Imsi record, @Param("example") ImsiExample example);

    int updateByExample(@Param("record") Imsi record, @Param("example") ImsiExample example);

    int updateByPrimaryKeySelective(Imsi record);

    int updateByPrimaryKey(Imsi record);

    void deleteByRecordId(Long id);

    List<Imsi> selectByCondition(@Param("id") Long id, @Param("imsi")String imsi);
}