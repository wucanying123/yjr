package com.pt1002.modules.controller;


import com.pt1002.common.entity.Response;
import com.pt1002.common.enums.ResponseCode;
import com.pt1002.modules.pojo.Templates;
import com.pt1002.modules.pojo.TemplatesWithBLOBs;
import com.pt1002.modules.pojo.query.TemQuery;
import com.pt1002.modules.service.TemplatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/templates")
public class TemplatesController {


    @Autowired
    TemplatesService templatesService;

    /**
     * 分页展示
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/pageQuery")
    @ResponseBody
    public TemQuery findPage(Integer page, Integer rows){
        if (page==null){
            page=1;
        }
        if (rows==null){
            rows=10;
        }
        TemQuery temQuery= templatesService.findPage(page,rows);
        return temQuery;
    }

    /**
     * 删除3D模板
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Response temDelete(String ids){
        templatesService.temDelete(ids);
        return new Response(ResponseCode.SUCCESS) ;
    }

    /**
     * 更新3D模板
     * @param templates
     * @return
     */
    @RequestMapping("/update")
    public Response temUpdate(TemplatesWithBLOBs templates){
        templatesService.temUpdate(templates);
        return new Response(ResponseCode.SUCCESS) ;
    }

}
