package com.pt1002.spring.startup;

import com.pt1002.common.constant.ThirdPartyConst;
import com.pt1002.modules.pojo.UploadConfig;
import com.pt1002.modules.service.UploadRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: xubo
 * @Description:
 * @Date: Create in 17:30 2018/6/8
 * @Modified By:
 * @Test By:
 */
@Component
public class LoadData implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(LoadData.class);

    @Autowired
    UploadRecordService uploadRecordService;

    @Override
    public void run(String... strings) throws Exception {
        logger.info("加载第三方平台信息");
        UploadConfig uploadConfig = uploadRecordService.queryUploadConfig();
        ThirdPartyConst.IP = uploadConfig.getIp();
        ThirdPartyConst.PORT = uploadConfig.getPort();
    }
}
