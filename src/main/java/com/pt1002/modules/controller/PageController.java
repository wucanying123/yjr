package com.pt1002.modules.controller;

import com.pt1002.modules.mapper.PopulationInfoMapper;
import com.pt1002.modules.mapper.TemplatesMapper;
import com.pt1002.modules.pojo.PopulationInfo;
import com.pt1002.modules.pojo.TemplatesExample;
import com.pt1002.modules.pojo.TemplatesWithBLOBs;
import com.pt1002.modules.pojo.User;
import com.pt1002.modules.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/system")
public class PageController {
    @Autowired
    UserService userService;

    @Autowired
    TemplatesMapper templatesMapper;

    @Autowired
    PopulationInfoMapper infoMapper;
    @RequestMapping("/login")
    public String login( User user) {
        if (user != null && StringUtils.isNotEmpty(user.getAccount()) && StringUtils.isNotEmpty(user.getPassword()
        ) && user.getAccount().equals("admin") && user.getPassword().equals("admin")) {
            return "newIndex";
        }
        if (user != null && StringUtils.isNotEmpty(user.getAccount()) && StringUtils.isNotEmpty(user.getPassword())){
            User user1 = userService.login(user);
            if (user1!=null){
                return " newIndex";
            }
        }
        return "error";
    }

    @RequestMapping("/")
    public String index1() {
        return "login";
    }

    @RequestMapping(value = "/showTemplate")
    public String showTemplate(){
        return "3dface";
    }

    @RequestMapping("/showImage")
    public String showImage(){
        return "image";
    }

    @RequestMapping("/image")
    @ResponseBody
    public void getImage(HttpServletRequest request,
                           HttpServletResponse response, String id){
        response.setHeader("Content-Type","image/*");//设置响应的媒体类型，这样浏览器会识别出响应的是图片
        TemplatesExample example = new TemplatesExample();
        TemplatesExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(114);
        List<TemplatesWithBLOBs> bs = templatesMapper.selectByExampleWithBLOBs(example);
        ServletOutputStream outputStream=null;
        try {
             outputStream = response.getOutputStream();
            outputStream.write(bs.get(0).getTemplate());
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream!=null){
                try {
                    outputStream .close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @RequestMapping("/testLong")
    @ResponseBody
    public Long getLong(HttpServletRequest request,
                         HttpServletResponse response, String id){
        PopulationInfo info = infoMapper.selectByIdentity("430621198906058864");
        return info.getId();
    }
}
