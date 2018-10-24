package com.pt1002.modules.service;

import com.pt1002.common.exceptions.ApplicationException;
import com.pt1002.modules.pojo.BlackList;
import com.pt1002.modules.pojo.DeviceBlackDTO;
import com.pt1002.modules.pojo.PersonDTO;

import java.util.List;
import java.util.Map;

public interface BlackListService {
    /**
     * 查询黑名单
     * @param name
     * @param idcard
     * @param page
     * @param rows
     * @return
     */
    Map<String ,Object> query(String name,String idcard,Integer deviceId,Integer page,Integer rows);

    int addBlackList(BlackList blackList) throws ApplicationException;

    int deleteById(List<Integer> id);

    int updateById(BlackList blackList) throws ApplicationException;

    Map<String, Object> queryDeviceBlackList(String name, String idcard, Integer deviceId, Integer page, Integer rows);

    int putBlackList2Deveice(Integer deviceId,List<Integer> blackId) throws ApplicationException;

    int deleteDeviceBlack(List<Integer> ids);

    int addBlackList(String name,String idcard) throws ApplicationException;

    boolean checkCanPushBySn(String sn) throws ApplicationException;

    List<PersonDTO> queryBlackListBySn(String sn) throws ApplicationException;

    int setCanPush(Integer deviceId) throws ApplicationException;

    public int batchAdd(List<BlackList> blackLists);
}
