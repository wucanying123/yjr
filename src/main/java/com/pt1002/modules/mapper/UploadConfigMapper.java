package com.pt1002.modules.mapper;

import com.pt1002.modules.pojo.UploadConfig;
import com.pt1002.modules.pojo.UploadConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UploadConfigMapper {
    int countByExample(UploadConfigExample example);

    int deleteByExample(UploadConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UploadConfig record);

    int insertSelective(UploadConfig record);

    List<UploadConfig> selectByExample(UploadConfigExample example);

    UploadConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UploadConfig record, @Param("example") UploadConfigExample example);

    int updateByExample(@Param("record") UploadConfig record, @Param("example") UploadConfigExample example);

    int updateByPrimaryKeySelective(UploadConfig record);

    int updateByPrimaryKey(UploadConfig record);
}