package com.pt1002.modules.controller;

import com.pt1002.common.entity.Response;
import com.pt1002.common.enums.ResponseCode;
import com.pt1002.common.exceptions.ApplicationException;
import com.pt1002.common.exceptions.ParamException;
import com.pt1002.modules.pojo.PopulationInfo;
import com.pt1002.modules.pojo.query.PopInfoQuery;
import com.pt1002.modules.pojo.strong.AndroidStrong;
import com.pt1002.modules.service.PopulationInfoService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/population")
public class PopulationInfoController {
    private static final Logger logger = LogManager.getLogger(PopulationInfo.class);
    @Autowired
    PopulationInfoService populationInfoService;

    /**
     * @param data         传过来的json
     * @param sceneFile    场景图
     * @param identityFile 身份证图片
     * @param template     byte数组
     * @param hi_res       byte数组
     * @param hi_res_tex   byte数组
     * @return
     * @throws ParamException
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Response register(AndroidStrong data,
                             @RequestParam(required = false, value = "sceneFile") MultipartFile sceneFile,
                             @RequestParam(required = false, value = "identityFile") MultipartFile identityFile,
                             @RequestParam(required = false, value = "template") MultipartFile template,
                             @RequestParam(required = false, value = "hi_res") MultipartFile hi_res,
                             @RequestParam(required = false, value = "hi_res_tex") MultipartFile hi_res_tex,
                             @RequestParam(required = false, value = "photoFile")MultipartFile photoFile) throws ParamException {
        Response<Object> response = new Response<>();

       if (data != null) {
            try {
                populationInfoService.upload(data, sceneFile, identityFile, template, hi_res, hi_res_tex,photoFile);
            } catch (ApplicationException e) {
                logger.error("上传图片失败");
                response.setCode(-1);
                response.setMessage(e.getMessage());
                e.printStackTrace();
                return response;
            }
        } else {
            logger.error("data参数错误");
            throw new ParamException();

        }
        return new Response(ResponseCode.SUCCESS);
    }

    /**
     * 删除对应的人的信息
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Response deletePopInfo(@RequestParam(value = "id") String ids) throws ApplicationException {
        int i = populationInfoService.deletePopInfo(ids);
        return new Response(ResponseCode.SUCCESS,i);
    }

    /**
     * 更新人的身份
     *
     * @param populationInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public Response updatePopInfo(PopulationInfo populationInfo) {
        int i = populationInfoService.updatePopInfo(populationInfo);
        return new Response(ResponseCode.SUCCESS,i);
    }

    /**
     * WEB端增加人的信息
     *
     * @param
     * @return
     */

    @ResponseBody
    @RequestMapping("/add")
    public Response addPopInfo(PopulationInfo pop) throws ParamException {
        int i = populationInfoService.addPopInfo(pop);
        return new Response(ResponseCode.SUCCESS,i);
    }

    /**
     * 分页展示
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/find")
    @ResponseBody
    public PopInfoQuery findPage(Integer page, Integer rows) {
        if (page == null) {
            page = 1;
        }
        if (rows == null) {
            rows = 10;
        }
        PopInfoQuery popInfoQuery = populationInfoService.findPage(page, rows);
        return popInfoQuery;
    }

    /**
     * 下拉框的选择
     *
     * @return
     */
    @RequestMapping("/pop/find/identityId")
    @ResponseBody
    public List<PopulationInfo> findIdentityId() {
        List<PopulationInfo> identityId = populationInfoService.findIdentityId();
        return identityId;
    }

    /**
     * 关联信息
     * @param id
     * @return
     */
    @RequestMapping("/popHistory")
    @ResponseBody
    public PopInfoQuery popHistory(@RequestParam String id,Long page,Long rows){
       if (page==null || page==0){
           page=1L;
       }
       if (rows==null || rows ==0){
           rows=10L;
       }

        PopInfoQuery popInfoQuery = populationInfoService.popHistory(Long.valueOf(id), page, rows);


        return popInfoQuery;
    }
    @RequestMapping("/popRecord")
    @ResponseBody
    public PopulationInfo pop(@RequestParam(value = "identityId") String identityId){
        PopulationInfo info=    populationInfoService.findPop(identityId);
        return info;

    }
}

