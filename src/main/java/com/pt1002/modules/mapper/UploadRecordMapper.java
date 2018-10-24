package com.pt1002.modules.mapper;

import com.pt1002.modules.pojo.UploadRecord;
import com.pt1002.modules.pojo.UploadRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UploadRecordMapper {
    int countByExample(UploadRecordExample example);

    int deleteByExample(UploadRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UploadRecord record);

    int insertSelective(UploadRecord record);

    List<UploadRecord> selectByExample(UploadRecordExample example);

    UploadRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UploadRecord record, @Param("example") UploadRecordExample example);

    int updateByExample(@Param("record") UploadRecord record, @Param("example") UploadRecordExample example);

    int updateByPrimaryKeySelective(UploadRecord record);

    int updateByPrimaryKey(UploadRecord record);

    /*custom*/

    List<Long> findAllNotUploadRecord();

}