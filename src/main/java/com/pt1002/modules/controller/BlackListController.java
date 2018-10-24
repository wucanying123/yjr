package com.pt1002.modules.controller;

import com.pt1002.common.PropertiesConstant;
import com.pt1002.common.entity.Response;
import com.pt1002.common.enums.ResponseCode;
import com.pt1002.common.exceptions.ApplicationException;
import com.pt1002.modules.pojo.BlackList;
import com.pt1002.modules.pojo.PersonDTO;
import com.pt1002.modules.service.BlackListService;
import org.apache.ibatis.annotations.Delete;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blackList")
public class BlackListController {

    @Autowired
    private BlackListService blackListService;

    @ResponseBody
    @GetMapping("/list")
    public Map queryList(@RequestParam(value = "name",required = false) String name,
                         @RequestParam(value = "idcard",required = false)String idcard,
                         @RequestParam(value = "page",defaultValue = "1")Integer page,
                         @RequestParam(value = "deviceId",required = false) Integer deviceId,
                         @RequestParam(value = "rows",defaultValue = "10")Integer pageSize){
        return blackListService.query(name,idcard,deviceId,page,pageSize);
    }

    @ResponseBody
    @PostMapping("/add")
    public Response add(@RequestParam("name") String name,
                      @RequestParam("idcard") String idcard,
                      @RequestParam("remark") String remark) throws ApplicationException {

        BlackList blackList = new BlackList(null,name,idcard,remark);
        int i = blackListService.addBlackList(blackList);
        return new Response(ResponseCode.SUCCESS,i);
    }
    @ResponseBody
    @PostMapping("/delete")
    public Response delete(@RequestParam("id") List<Integer> id) throws ApplicationException {
        int i = blackListService.deleteById(id);
        return new Response(ResponseCode.SUCCESS,i);
    }
    @ResponseBody
    @PostMapping("/update")
    public Response add(@ModelAttribute BlackList blackList) throws ApplicationException {
        int i = blackListService.updateById(blackList);
        return new Response(ResponseCode.SUCCESS,i);
    }
    @ResponseBody
    @GetMapping("/device/blacklist")
    public Map<String, Object> queryDeviceBlackList(@RequestParam(value = "name",required = false) String name,
                                       @RequestParam(value = "idcard",required = false)String idcard,
                                       @RequestParam(value = "deviceId",required = false)Integer deviceId,
                                       @RequestParam(value = "page",defaultValue = "1")Integer page,
                                       @RequestParam(value = "rows",defaultValue = "10")Integer pageSize) throws ApplicationException {
        Map<String, Object> stringObjectMap = blackListService.queryDeviceBlackList(name, idcard, deviceId, page, pageSize);
        return stringObjectMap;
    }
    @ResponseBody
    @PostMapping("/device/blackList/put")
    public Response putBlackListToDeveice(@RequestParam("deviceId") Integer deviceId, @RequestParam("blackId") List<Integer> blackId) throws ApplicationException {
        int count = blackListService.putBlackList2Deveice(deviceId,blackId);
        return new Response(ResponseCode.SUCCESS,count);
    }
    @ResponseBody
    @PostMapping("/device/blackList/delete")
    public Response deleteDeviceBlackList(@RequestParam("id") List<Integer> id) throws ApplicationException {
        int i = blackListService.deleteDeviceBlack(id);
        return new Response(ResponseCode.SUCCESS,i);
    }

    @ResponseBody
    @PostMapping("/blackList/add")
    public Response addBlackList(@RequestParam("name") String name,@RequestParam("idcard")String idcard) throws ApplicationException {
        int i = blackListService.addBlackList(name, idcard);
        return new Response(ResponseCode.SUCCESS,i);
    }

    /**
     * create by: 不背锅
     * description:通过sn码查询设备的黑名单是否更新了
     * create time: 2018/10/22 15:19
     * @Param: sn
     * @return com.pt1002.common.entity.Response
     */
    @ResponseBody
    @PostMapping("/device/update/checkBySN")
    public Response checkThisDeviceUpdate(@RequestParam("sn") String sn) throws ApplicationException {
        boolean b = blackListService.checkCanPushBySn(sn);
        return new Response(ResponseCode.SUCCESS,b);
    }

    @ResponseBody
    @PostMapping("/blackList/updateBySn")
    public Response updateBlackListBySn(@RequestParam("sn") String sn) throws ApplicationException {
        List<PersonDTO> personDTOS = blackListService.queryBlackListBySn(sn);
        return new Response(ResponseCode.SUCCESS,personDTOS);
    }

    @ResponseBody
    @GetMapping("/setCanPush")
    public Response setCanPush(@RequestParam("deviceId") Integer deviceId) throws ApplicationException {
        int t = blackListService.setCanPush(deviceId);
        return new Response(ResponseCode.SUCCESS,t);
    }

    @ResponseBody
    @PostMapping("/excel/Import")
    public Response excelImport(@RequestParam("excelFile") MultipartFile excelFile) throws ApplicationException, IOException {

        if(!excelFile.getContentType().equalsIgnoreCase("application/vnd.ms-excel")){
            throw new ApplicationException("请上传excel文件");
        }

        File destory = new File(PropertiesConstant.UPLOAD_TEMP_PATH);
        if(!destory.exists())
            destory.mkdirs();
        String fileName = excelFile.getOriginalFilename();
        File file = new File(destory,fileName);
        excelFile.transferTo(file);

        InputStream is = new FileInputStream(file);
        Workbook hssfWorkbook = null;
        if (fileName.endsWith("xlsx")){
            hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
        }else if (fileName.endsWith("xls")){
            hssfWorkbook = new HSSFWorkbook(is);//Excel 2003

        }
        List<BlackList> list = new ArrayList<BlackList>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet <hssfWorkbook.getNumberOfSheets(); numSheet++) {
            //HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                //HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                Row hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    BlackList blackList = new BlackList();
                    //HSSFCell name = hssfRow.getCell(0);
                    //HSSFCell pwd = hssfRow.getCell(1);
                    Cell name = hssfRow.getCell(0);
                    Cell idcard = hssfRow.getCell(1);
                    blackList.setName(name.toString());
                    blackList.setIdcard(idcard.toString());

                    list.add(blackList);
                }
            }
        }
        int i = blackListService.batchAdd(list);

        if(file.exists()){
            file.delete();
        }

        return new Response(ResponseCode.SUCCESS,i);
    }

}
