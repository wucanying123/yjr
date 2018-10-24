package com.pt1002.modules.mapper;

import com.pt1002.modules.pojo.Templates;
import com.pt1002.modules.pojo.TemplatesExample;
import com.pt1002.modules.pojo.TemplatesWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TemplatesMapper {
    int countByExample(TemplatesExample example);

    int deleteByExample(TemplatesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TemplatesWithBLOBs record);

    int insertSelective(TemplatesWithBLOBs record);

    List<TemplatesWithBLOBs> selectByExampleWithBLOBs(TemplatesExample example);

    List<Templates> selectByExample(TemplatesExample example);

    TemplatesWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TemplatesWithBLOBs record, @Param("example") TemplatesExample example);

    int updateByExampleWithBLOBs(@Param("record") TemplatesWithBLOBs record, @Param("example") TemplatesExample example);

    int updateByExample(@Param("record") Templates record, @Param("example") TemplatesExample example);

    int updateByPrimaryKeySelective(TemplatesWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TemplatesWithBLOBs record);

    int updateByPrimaryKey(Templates record);

    TemplatesWithBLOBs selectByPid(Integer id);

    void deleteByDevicesSn(String sn);

    TemplatesWithBLOBs selectByDevicesSn(String sn);

    void deleteByPid(Integer pid);
}