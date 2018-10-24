package com.pt1002.modules.controller;

import com.pt1002.common.entity.Response;
import com.pt1002.common.enums.ResponseCode;
import com.pt1002.modules.pojo.CertificationRecord;
import com.pt1002.modules.pojo.query.*;
import com.pt1002.modules.service.CertificationRecordService;
import com.pt1002.modules.service.ImsiService;
import com.pt1002.modules.service.TemplatesService;
import com.pt1002.modules.service.WifiService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/certification/record")
public class CertificationRecordController {

    @Autowired
    CertificationRecordService recordService;
    @Autowired
    ImsiService imsiService;

    @Autowired
    WifiService wifiService;

    @Autowired
    TemplatesService templatesService;

    /**
     * 分页的展示
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/recordPage/query")
    @ResponseBody
    public CertificationRecordQuery pageQuery(Integer page, Integer rows) {
        if (page == null) {
            page = 1;
        }
        if (rows == null) {
            rows = 30;
        }
        CertificationRecordQuery recordQuery = recordService.findPage(page, rows);

        return recordQuery;
    }

    /**
     * 认证记录的增加
     */
    @RequestMapping("/record/add")
    public Response addRecord(CertificationRecord record) {
        int i = recordService.addRecord(record);

        return new Response<Integer>(ResponseCode.SUCCESS,i);
    }

    /**
     * 认证记录的删除
     */
    @RequestMapping("/record/delete")
    public Response deleteRecord(String id) {
        int i = recordService.deleteRecord(id);
        return new Response<Integer>(ResponseCode.SUCCESS,i);
    }

    /**
     * 认证记录的更新
     */
    @RequestMapping("/record/update")
    public Response updateRecord(CertificationRecord record) {
        return new Response<Integer>(ResponseCode.SUCCESS,recordService.updateRecord(record));
    }

    @RequestMapping("/record/history")
    @ResponseBody
    public BaseQuery recordHistory(@RequestParam(value = "id", required = false) String id, Integer page, Integer rows,
                                   @RequestParam(value = "imsi", required = false) String imsi) {
        String ids = id.substring(0, id.lastIndexOf("_"));
        String suffix = id.substring(id.lastIndexOf("_") + 1);
        if (StringUtils.isNotEmpty(suffix) && suffix.equals("imsi")) {
            ImsiQuery imsis = imsiService.findAll(Long.valueOf(ids), imsi, page, rows);
            return imsis;
        }
        if (StringUtils.isNotEmpty(suffix) && suffix.equals("wifi")) {
            WifiQuery wifis = wifiService.findAll(Long.valueOf(ids), page, rows);
            return wifis;
        }
        if (StringUtils.isNotEmpty(suffix) && suffix.equals("template")) {
            TemQuery temQuery = templatesService.findAll(ids, page, rows);
            return temQuery;
        }

        return null;
    }
}
