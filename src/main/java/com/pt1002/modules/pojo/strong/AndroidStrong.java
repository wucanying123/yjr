package com.pt1002.modules.pojo.strong;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pt1002.modules.pojo.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AndroidStrong {
    String sn;
    PopulationInfo populationInfo;
    Templates ATCparameters;
    Electronic electronic;


    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }


    public Templates getATCparameters() {

        return ATCparameters;
    }

    public void setATCparameters(String ATCparameters) {
        if (ATCparameters.equals("") || ATCparameters==null){
            return;
        }
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        AndroidTemplates templates = gson.fromJson(ATCparameters, AndroidTemplates.class);
        Templates templates1 = new Templates();
        if (templates.getDevice_sn() != null ) {
            templates1.setDeviceSn(templates.getDevice_sn());
        }
        if (templates.getMark() != null) {
            templates1.setMark(Integer.valueOf(templates.getMark()));
        }
        if (templates.getQuality() != null) {
            templates1.setQuality(Short.valueOf(templates.getQuality()));
        }
        if (templates.getEnr_dist() != null) {
            templates1.setEnrDist(Float.valueOf(templates.getEnr_dist()));
        }
        this.ATCparameters = templates1;
    }

/*    public Electronic getElectronic() {
        return electronic;
    }

    public void setElectronic(String electronic) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        AndroidElectronic androidElectronic = gson.fromJson(electronic, AndroidElectronic.class);
        Electronic electronic1 = new Electronic();



        this.electronic = electronic1;
    }*/

    public PopulationInfo getPopulationInfo() {

        return populationInfo;
    }

    public void setPopulationInfo(String populationInfo) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        AndroidPop pop = gson.fromJson(populationInfo, AndroidPop.class);

        PopulationInfo info = new PopulationInfo();
        try {
            info.setAddress(pop.getAddress());
            info.setIdentityCard(pop.getIdentityCard());
            info.setIssue(pop.getIssue());
            if (pop.getExpire_date_start() != null) {
                info.setLastUpdateTime(new SimpleDateFormat("yyyy-MM-dd").parse(pop.getExpire_date_start()));
            }
            info.setName(pop.getName());
            info.setNation(pop.getNation());
            if (pop.getRemark() != null) {
                info.setRemark(pop.getRemark());
            }
            if (pop.getScore() != null) {
                info.setScore(pop.getScore());
            }
            info.setSex(pop.getSex());
            if (pop.getBirthday()!=null){
                info.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(pop.getBirthday()));
            }
            if (pop.getExpire_date_end()!=null){
                info.setExpireDateEnd(new SimpleDateFormat("yyyy-MM-dd").parse(pop.getExpire_date_end()));
            }
            if (pop.getExpire_date_start()!=null){
                info.setExpireDateStart(new SimpleDateFormat("yyyy-MM-dd").parse(pop.getExpire_date_start()));
            }


//            //封装record
                if (pop.getProveSuccessful()!=null && pop.getProveSuccessful().equals("true")){
                    info.setProveSuccessful((byte) 1);
                }else {
                    info.setProveSuccessful((byte) 0);
                }
                if (pop.getCollectSuccessful()!=null && pop.getCollectSuccessful().equals("true")){
                    info.setCollectSuccessful((byte) 1);
                }else {
                    info.setCollectSuccessful((byte) 0);
                }
              if (pop.getCreateTime()!=null && !"".equals(pop.getCreateTime())){
                  Date devicesTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(pop.getCreateTime());
                  Date serverTime= new Date();
                  int devicesHours = devicesTime.getHours();
                  int serverHours = serverTime.getHours();
                    if (devicesHours==serverHours){
                        info.setCreateTime(devicesTime);
                    }else {
                        info.setCreateTime(serverTime);
                    }
            }else {
                info.setCreateTime(new Date());
              }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.populationInfo = info;
    }

    public  Electronic getElectronic() {
        return electronic;
    }

    public void setElectronic(String electronic) {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Electronic electronic1 = gson.fromJson(electronic, Electronic.class);

        this.electronic = electronic1;
    }
}
