package com.pt1002.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "properties")
public class PropertiesConstant {

    public static  String SERVER_PATH;
    public static  String SCENE_PATH;
    public static  String IDENTITY_PATH;
    public static  String TEMPLATES_PATH;
    public static  String UPLOAD_TEMP_PATH;

    public static String getTemplatesPath() {
        return TEMPLATES_PATH;
    }

    public static void setTemplatesPath(String templatesPath) {
        TEMPLATES_PATH = templatesPath;
    }

    public static String getServerPath() {
        return SERVER_PATH;
    }

    public static void setServerPath(String serverPath) {
        SERVER_PATH = serverPath;
    }

    public static String getScenePath() {
        return SCENE_PATH;
    }

    public static void setScenePath(String scenePath) {
        SCENE_PATH = scenePath;
    }

    public static String getIdentityPath() {
        return IDENTITY_PATH;
    }

    public static void setIdentityPath(String identityPath) {
        IDENTITY_PATH = identityPath;
    }

    public static String getUploadTempPath() {
        return UPLOAD_TEMP_PATH;
    }

    public static void setUploadTempPath(String uploadTempPath) {
        UPLOAD_TEMP_PATH = uploadTempPath;
    }
}
