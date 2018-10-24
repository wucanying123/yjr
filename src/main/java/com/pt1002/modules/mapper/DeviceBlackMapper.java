package com.pt1002.modules.mapper;

import com.pt1002.modules.pojo.DeviceBlack;
import com.pt1002.modules.pojo.DeviceBlackDTO;
import com.pt1002.modules.pojo.DeviceBlackExample;
import java.util.List;

import com.pt1002.modules.pojo.PersonDTO;
import org.apache.ibatis.annotations.Param;

public interface DeviceBlackMapper {
    int countByExample(DeviceBlackExample example);

    int deleteByExample(DeviceBlackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeviceBlack record);

    int insertSelective(DeviceBlack record);

    List<DeviceBlack> selectByExample(DeviceBlackExample example);

    DeviceBlack selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeviceBlack record, @Param("example") DeviceBlackExample example);

    int updateByExample(@Param("record") DeviceBlack record, @Param("example") DeviceBlackExample example);

    int updateByPrimaryKeySelective(DeviceBlack record);

    int updateByPrimaryKey(DeviceBlack record);

    List<DeviceBlackDTO> selectDeivceBlackByCondition(@Param("name") String name,@Param("idcard")String idcard,@Param("deviceId")Integer deviceId);

    List<PersonDTO> selectBySn(@Param("sn") String sn);
}