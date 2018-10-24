package com.pt1002.spring.props;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: xubo
 * @Description: 第三方平台配置
 * @Date: Create in 17:23 2018/6/8
 * @Modified By:
 * @Test By:
 */
@Component
@ConfigurationProperties(prefix = "thirdparty")
public class ThirdPartyProp {

//    file-upload-url: /datadoorinterface/fileupload
//    certification-upload-url: /datadoorinterface/person
//    mac-upload-url: /datadoorinterface/mac

    private String fileUploadUrl;

    private String certificationUploadUrl;

    private String macUploadUrl;

    public String getMacUploadUrl() {
        return macUploadUrl;
    }

    public void setMacUploadUrl(String macUploadUrl) {
        this.macUploadUrl = macUploadUrl;
    }

    public String getFileUploadUrl() {
        return fileUploadUrl;
    }

    public void setFileUploadUrl(String fileUploadUrl) {
        this.fileUploadUrl = fileUploadUrl;
    }

    public String getCertificationUploadUrl() {
        return certificationUploadUrl;
    }

    public void setCertificationUploadUrl(String certificationUploadUrl) {
        this.certificationUploadUrl = certificationUploadUrl;
    }
}
