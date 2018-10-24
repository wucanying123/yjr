package com.pt1002.modules.controller;

import com.pt1002.common.entity.Response;
import com.pt1002.common.enums.ResponseCode;
import com.pt1002.modules.pojo.Imsi;
import com.pt1002.modules.pojo.query.ImsiQuery;
import com.pt1002.modules.service.ImsiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/imsi")
public class ImsiController {

    @Autowired
    ImsiService imsiService;

    /**
     * 分页
     *
     * @return
     */
    @RequestMapping("/find")
    @ResponseBody
    public ImsiQuery findImsi(Integer page, Integer rows) {

        if (page == null) {
            page = 1;
        }
        if (rows == null) {
            rows = 10;
        }
        ImsiQuery imsiQuery = imsiService.findImsi(page, rows);
        return imsiQuery;
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Response deleteImsi(String ids) {
        return new Response<Integer>(ResponseCode.SUCCESS,imsiService.deleteImsi(ids)) ;
    }

    /**
     * 更新
     * @param imsi
     * @return
     */
    @RequestMapping("/update")
    public Response updateImsi(Imsi imsi) {

        return new Response<Integer>(ResponseCode.SUCCESS,imsiService.updateImsi(imsi)) ;
    }
}
