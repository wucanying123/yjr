package com.pt1002.thirdpart;

/**
 * @Author: xubo
 * @Description: 上传数据到第三方平台返回值
 * @Date: Create in 14:35 2018/6/8
 * @Modified By:
 * @Test By:
 */
public class UploadResp {

    private String Status;

    private String Message;

    private String ZfsPath;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getZfsPath() {
        return ZfsPath;
    }

    public void setZfsPath(String zfsPath) {
        ZfsPath = zfsPath;
    }

    @Override
    public String toString() {
        return "UploadResp{" +
                "Status='" + Status + '\'' +
                ", Message='" + Message + '\'' +
                ", ZfsPath='" + ZfsPath + '\'' +
                '}';
    }
}
