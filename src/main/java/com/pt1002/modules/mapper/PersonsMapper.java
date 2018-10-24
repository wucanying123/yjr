package com.pt1002.modules.mapper;

import com.pt1002.modules.pojo.Persons;
import com.pt1002.modules.pojo.PersonsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersonsMapper {
    int countByExample(PersonsExample example);

    int deleteByExample(PersonsExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Persons record);

    int insertSelective(Persons record);

    List<Persons> selectByExampleWithBLOBs(PersonsExample example);

    List<Persons> selectByExample(PersonsExample example);

    Persons selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Persons record, @Param("example") PersonsExample example);

    int updateByExampleWithBLOBs(@Param("record") Persons record, @Param("example") PersonsExample example);

    int updateByExample(@Param("record") Persons record, @Param("example") PersonsExample example);

    int updateByPrimaryKeySelective(Persons record);

    int updateByPrimaryKeyWithBLOBs(Persons record);

    int updateByPrimaryKey(Persons record);

    void deleteByCardId(long id);

    List<Persons> selectByCardId(Long id);
}