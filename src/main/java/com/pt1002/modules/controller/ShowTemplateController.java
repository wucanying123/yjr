package com.pt1002.modules.controller;

import com.pt1002.modules.mapper.TemplatesMapper;
import com.pt1002.modules.pojo.TemplatesWithBLOBs;
import com.pt1002.spring.props.TemplatesProp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.*;

@Controller
@RequestMapping(value = "/test")
public class ShowTemplateController {

    @Resource
    TemplatesMapper templatesMapper;


    @ResponseBody
    @RequestMapping(value = "/showByExe/{id}")
    public String showTemplate(@PathVariable(value = "id")Integer id){

        //先杀死当前显示3d模板的进程
        this.killProcess("SurfaceImageShow.exe");

        //读取java程序运行的目录，3d人脸模板文件需要写到这个目录下
        System.out.println(System.getProperty("user.dir"));

        String dir = System.getProperty("user.dir");
        TemplatesWithBLOBs templates = templatesMapper.selectByPrimaryKey(id);

        byte[] template = templates.getHiRes();//写文件名为Surface.blob
        String surfacePath = dir + File.separator + "Surface.blob";
        boolean b1 = byteToFile(template, surfacePath);

        byte[] hiResTex = templates.getHiResTex();//写文件名为Image.blob
        String imagePath = dir + File.separator + "Image.blob";
        boolean b2 = byteToFile(hiResTex, imagePath);

        //如果任一文件写入失败则返回失败
        if (!b1 || !b2)
            return "fail";

        Runtime runtime = Runtime.getRuntime();
        try {
            //调起3d模板显示的exe程序
            runtime.exec(TemplatesProp.START_EXE);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error exec!");
            return "fail";
        }

        return "success";
    }

    private void killProcess(String processName){

        try {
            String[] cmd = { "tasklist"};

            Process proc = Runtime.getRuntime().exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc
                    .getInputStream()));
            String string_Temp = in.readLine();
            while (string_Temp != null) {
//                System.out.println(string_Temp);
                if(string_Temp.contains(processName))
                    Runtime.getRuntime().exec("taskkill /f /t /im "+processName);
                string_Temp = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private boolean byteToFile(byte[] blob, String filePath){

        OutputStream out = null;
        InputStream is = null;
        try {
            File outFile = new File(filePath);
            if (!outFile.exists()){
                boolean newFile = outFile.createNewFile();
                if (!newFile){
                    return false;
                }
            }
            out = new FileOutputStream(filePath);
            is = new ByteArrayInputStream(blob);
            byte[] buff = new byte[1024];
            int len = 0;
            while((len=is.read(buff))!=-1){
                out.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

}
