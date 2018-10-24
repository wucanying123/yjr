package com.pt1002.modules.controller;

import com.pt1002.common.entity.Response;
import com.pt1002.common.enums.ResponseCode;
import com.pt1002.modules.pojo.Wifi;
import com.pt1002.modules.pojo.query.WifiQuery;
import com.pt1002.modules.service.WifiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/wifi")
public class WifiController {

    @Autowired
    WifiService wifiService;

    @RequestMapping("/find")
    @ResponseBody
    public WifiQuery findWifi(Integer page, Integer rows){

        if (page == null) {
            page = 1;
        }
        if (rows == null) {
            rows = 10;
        }
        WifiQuery  wifiQuery=wifiService.findWifi(page,rows);
        return wifiQuery;
    }
    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Response deleteWifi(String ids) {
        wifiService.deleteWifi(ids);
        return new Response(ResponseCode.SUCCESS) ;
    }

    /**
     * 更新
     * @param wifi
     * @return
     */
    @RequestMapping("/update")
    public Response updateWifi(Wifi wifi) {
        wifiService.updateWifi(wifi);
        return new Response(ResponseCode.SUCCESS) ;
    }
}
