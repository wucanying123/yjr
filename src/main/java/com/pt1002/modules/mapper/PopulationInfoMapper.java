package com.pt1002.modules.mapper;

import com.pt1002.modules.pojo.PopulationInfo;
import com.pt1002.modules.pojo.PopulationInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PopulationInfoMapper {
    int countByExample(PopulationInfoExample example);

    int deleteByExample(PopulationInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PopulationInfo record);

    int insertSelective(PopulationInfo record);

    List<PopulationInfo> selectByExample(PopulationInfoExample example);

    PopulationInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PopulationInfo record, @Param("example") PopulationInfoExample example);

    int updateByExample(@Param("record") PopulationInfo record, @Param("example") PopulationInfoExample example);

    int updateByPrimaryKeySelective(PopulationInfo record);

    int updateByPrimaryKey(PopulationInfo record);

    PopulationInfo selectByIdentity(String identityCard);
}