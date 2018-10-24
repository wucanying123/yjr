package com.pt1002.modules.service;

import com.pt1002.common.exceptions.ApplicationException;
import com.pt1002.common.exceptions.ParamException;
import com.pt1002.modules.pojo.PopulationInfo;
import com.pt1002.modules.pojo.query.PopInfoQuery;
import com.pt1002.modules.pojo.strong.AndroidStrong;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PopulationInfoService {
     int upload(AndroidStrong data, MultipartFile file, MultipartFile identityFile, MultipartFile template, MultipartFile sceneFile, MultipartFile pictrueFile,MultipartFile photoFile) throws ApplicationException, ParamException;

    int deletePopInfo(String ids) throws ApplicationException;

    int updatePopInfo(PopulationInfo populationInfo);

    int addPopInfo(PopulationInfo populationInfo) throws ParamException;

    PopInfoQuery findPage(Integer page, Integer rows);

    List<PopulationInfo> findIdentityId();

    PopInfoQuery popHistory(Long aLong,Long page,Long rows);

    PopulationInfo findPop(String identityId);
}
