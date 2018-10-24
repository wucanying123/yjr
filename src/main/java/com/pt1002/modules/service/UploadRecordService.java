package com.pt1002.modules.service;

import com.pt1002.common.exceptions.DataNotFoundException;
import com.pt1002.common.exceptions.ParamException;
import com.pt1002.modules.pojo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: xubo
 * @Description: 第三方平台记录上传serveice
 * @Date: Create in 15:49 2018/6/8
 * @Modified By:
 * @Test By:
 */
public interface UploadRecordService {

    /**
     * @Author: xubo
     * @Description: 获取服务器配置信息
     * @Date: 16:10 2018/6/8
     * @param   
     * @return com.pt1002.modules.pojo.UploadConfig 
     */
    UploadConfig queryUploadConfig() throws ParamException;

    /**
     * @Author: xubo
     * @Description: 更新服务器配置信息
     * @Date: 16:10 2018/6/8
     * @param ip
     * @param port  
     * @param macPort
     * @return int
     */
    int updateUploadConfig(String ip, int port, int macPort) throws ParamException;

    /**
     * @Author: xubo
     * @Description: 通过认证记录id查询上传记录
     * @Date: 20:28 2018/6/8
     * @param certificationId  
     * @return com.pt1002.modules.pojo.UploadRecord 
     */
    UploadRecord findUploadRecordByCertId(long certificationId) ;

    /**
     * @Author: xubo
     * @Description: 保存认证记录上传的日志
     * @Date: 17:06 2018/6/8
     * @param populationInfo 身份证信息
     * @param deviceSn 设备信息sn号
     * @param certificationRecord 认证记录
     * @param wifi 手机mac信息
     * @param sceneFile 场景图
     * @param identityFile  证件照
     * @return int
     */
    int insertUploadRecord(PopulationInfo populationInfo,
                            String deviceSn,
                            CertificationRecord certificationRecord,
                            List<Wifi> wifi,
                            String sceneFile,
                            String identityFile);

    /**
     * @Author: xubo
     * @Description: 重新上传单个认证记录
     * @Date: 20:29 2018/6/8
     * @param certificationId  
     * @return int 
     */
    int reUploadRecord(long certificationId) throws ParamException, DataNotFoundException;

    /**
     * @Author: xubo
     * @Description: 查询所有未上传的认证记录
     * @Date: 10:35 2018/6/11
     * @param   
     * @return java.util.List<java.lang.Long> 
     */
    List<Long> findAllNotUploadRecord();
}
