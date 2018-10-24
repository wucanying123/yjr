package com.pt1002.modules.controller;

import com.pt1002.common.entity.Response;
import com.pt1002.common.enums.ResponseCode;
import com.pt1002.modules.pojo.Persons;
import com.pt1002.modules.pojo.PopulationInfo;
import com.pt1002.modules.pojo.query.PersonsQuery;
import com.pt1002.modules.service.PersonsService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/person")
public class PersonsController {
    private static final Logger logger = LogManager.getLogger(Persons.class);
    @Autowired
    PersonsService personsService;

    /**
     * 档案的分页展示
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/PageQuery")
    @ResponseBody
    public PersonsQuery findPage(Integer page,Integer rows){
        if (page==null){
            page=1;
        }
        if (rows==null){
            rows=10;
        }
        PersonsQuery personsQuery=personsService.findPage(page,rows);
        if (personsQuery==null){
            logger.warn("分页失败");
        }
        return personsQuery;
    }

    /**
     * 删除档案记录
     * 会删除这个人所有记录!!!!
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Response   personsDelete(String ids){
        personsService.personsDelete(ids);
        return new Response<Integer>(ResponseCode.SUCCESS) ;
    }

    /**
     * 更新记录
     * @param persons
     * @return
     */
    @RequestMapping("/update")
    public Response personsUpdate(Persons persons){

        return new Response<Integer>(ResponseCode.SUCCESS,personsService.personsUpdate(persons)) ;
    }


}
