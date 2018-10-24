package com.pt1002.modules.controller;

import com.pt1002.common.entity.Response;
import com.pt1002.common.enums.ResponseCode;
import com.pt1002.common.exceptions.ApplicationException;
import com.pt1002.modules.pojo.Devices;
import com.pt1002.modules.pojo.query.DevicesQuery;
import com.pt1002.modules.service.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/devices")
public class DevicesController {



    @Autowired
    DevicesService devicesService;

    /**
     * 分页展示
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/devicesPage/query")
    @ResponseBody
    public DevicesQuery pageQuery(Integer page,Integer rows ){

        if (page==null){
            page=1;
        }

        if (rows==null){
            rows=30;
        }
        DevicesQuery devicesQuery=devicesService.findPage(page,rows);
        return devicesQuery;
    }

    /**
     * 增加
     * @param devices
     */
    @ResponseBody
    @RequestMapping("/add")
    public Response devicesAdd(Devices devices){

        int i = devicesService.devicesAdd(devices);
        return new Response(ResponseCode.SUCCESS,i);
    }

    /**
     * 更新
     * @param devices
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Response  devicesUpdate(Devices devices){
        int i = devicesService.devicesUpdate(devices);
        return new Response(ResponseCode.SUCCESS,i);
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Response  devicesUpdate(@RequestParam(value = "id") String ids){
        int i = devicesService.devicesDelete(ids);

        return new Response(ResponseCode.SUCCESS,i);
    }

    @RequestMapping("/register")
    @ResponseBody
    public Response register(String type,String sn) throws ApplicationException {
        Devices devices= null;
        devices = devicesService.devicesRegister(sn,type);
        return new Response(ResponseCode.SUCCESS);
    }
    @RequestMapping("/allDevice")
    @ResponseBody
    public Response allDevice(){
        List<Map<String, Object>> maps = devicesService.allDevices();
        return new Response(ResponseCode.SUCCESS,maps);
    }

}
