package com.pt1002.modules.mapper;

import com.pt1002.modules.pojo.Wifi;
import com.pt1002.modules.pojo.WifiExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WifiMapper {
    int countByExample(WifiExample example);

    int deleteByExample(WifiExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Wifi record);

    int insertSelective(Wifi record);

    List<Wifi> selectByExample(WifiExample example);

    Wifi selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Wifi record, @Param("example") WifiExample example);

    int updateByExample(@Param("record") Wifi record, @Param("example") WifiExample example);

    int updateByPrimaryKeySelective(Wifi record);

    int updateByPrimaryKey(Wifi record);

    List<Wifi> selectByAll();

    void deleteByRecordId(Long id);

    List<Wifi> selectByRecordId(Long id);
}