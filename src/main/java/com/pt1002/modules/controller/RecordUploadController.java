package com.pt1002.modules.controller;

import com.pt1002.common.entity.Response;
import com.pt1002.common.enums.ResponseCode;
import com.pt1002.common.exceptions.DataNotFoundException;
import com.pt1002.common.exceptions.ParamException;
import com.pt1002.modules.pojo.UploadConfig;
import com.pt1002.modules.service.UploadRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: xubo
 * @Description: 认证记录上传配置
 * @Date: Create in 15:43 2018/6/8
 * @Modified By:
 * @Test By:
 */
@RestController
@RequestMapping(value = "/thirdparty")
public class RecordUploadController {

    private ExecutorService executors = Executors.newFixedThreadPool(2);

    @Autowired
    UploadRecordService uploadRecordService;

    /**
     * @Author: xubo
     * @Description: 第三方服务器的ip 端口配置
     * @Date: 10:28 2018/6/11
     * @param   
     * @return com.pt1002.common.entity.Response<com.pt1002.modules.pojo.UploadConfig> 
     */
    @RequestMapping(value = "/query/config", method = RequestMethod.GET)
    public Response<UploadConfig> getServerConfig() throws ParamException {
        UploadConfig uploadConfig = uploadRecordService.queryUploadConfig();
        return new Response<>(ResponseCode.SUCCESS, uploadConfig);
    }

    /**
     * @Author: xubo
     * @Description: 更新第三方服务器的ip 端口配置
     * @Date: 10:28 2018/6/11
     * @param ip
     * @param port
     * @return com.pt1002.common.entity.Response
     */
    @RequestMapping(value = "/update/config", method = RequestMethod.POST)
    public Response updateServerConfig(@RequestParam(value = "ip")String ip,
                                       @RequestParam(value = "port")Integer port,
                                       @RequestParam(value = "macPort")Integer macPort) throws ParamException {
        uploadRecordService.updateUploadConfig(ip, port, macPort);
        return new Response<>(ResponseCode.SUCCESS);
    }

    /**
     * @Author: xubo
     * @Description: 重新上传单条认证记录
     * @Date: 10:29 2018/6/11
     * @param id  
     * @return com.pt1002.common.entity.Response 
     */
    @RequestMapping(value = "/reUpload", method = RequestMethod.POST)
    public Response reUpload(@RequestParam(value = "certificationId")Long id) throws DataNotFoundException, ParamException {

        uploadRecordService.reUploadRecord(id);
        return new Response(ResponseCode.SUCCESS);
    }

    /**
     * @Author: xubo
     * @Description: 重新上传所有认证记录
     * @Date: 10:29 2018/6/11
     * @param
     * @return com.pt1002.common.entity.Response
     */
    @RequestMapping(value = "/reUploadAll", method = RequestMethod.POST)
    public Response uploadAll(){

        List<Long> allNotUploadRecord = uploadRecordService.findAllNotUploadRecord();
        allNotUploadRecord.forEach( id -> CompletableFuture.supplyAsync( () -> {
            try {
                return uploadRecordService.reUploadRecord(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 1;
        },executors));

        return new Response(ResponseCode.SUCCESS);
    }

}
