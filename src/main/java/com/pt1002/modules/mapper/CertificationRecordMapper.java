package com.pt1002.modules.mapper;

import com.pt1002.modules.pojo.CertificationRecord;
import com.pt1002.modules.pojo.CertificationRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CertificationRecordMapper {
    int countByExample(CertificationRecordExample example);

    int deleteByExample(CertificationRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CertificationRecord record);

    int insertSelective(CertificationRecord record);

    List<CertificationRecord> selectByExample(CertificationRecordExample example);

    CertificationRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CertificationRecord record, @Param("example") CertificationRecordExample example);

    int updateByExample(@Param("record") CertificationRecord record, @Param("example") CertificationRecordExample example);

    int updateByPrimaryKeySelective(CertificationRecord record);

    int updateByPrimaryKey(CertificationRecord record);

    List<CertificationRecord> selectByIdentity(Long id);

    List<CertificationRecord> selectByDevicesId(Integer value);

    void deleteByIdentityId(long id);
}