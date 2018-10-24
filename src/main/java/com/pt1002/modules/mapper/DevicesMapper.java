package com.pt1002.modules.mapper;

import com.pt1002.modules.pojo.Devices;
import com.pt1002.modules.pojo.DevicesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DevicesMapper {
    int countByExample(DevicesExample example);

    int deleteByExample(DevicesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Devices record);

    int insertSelective(Devices record);

    List<Devices> selectByExample(DevicesExample example);

    Devices selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Devices record, @Param("example") DevicesExample example);

    int updateByExample(@Param("record") Devices record, @Param("example") DevicesExample example);

    int updateByPrimaryKeySelective(Devices record);

    int updateByPrimaryKey(Devices record);

    Devices selectBySn(String sn);

    Devices selectByTypeId(String data);
}