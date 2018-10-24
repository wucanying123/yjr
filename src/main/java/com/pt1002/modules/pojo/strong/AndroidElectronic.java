package com.pt1002.modules.pojo.strong;

import com.pt1002.modules.pojo.AndroidImsi;
import com.pt1002.modules.pojo.AndroidWifi;


import java.util.List;

public class AndroidElectronic {
    List<AndroidImsi> IMSI;
    List<AndroidWifi> WIFI;

    public List<AndroidImsi> getIMSI() {
        return IMSI;
    }

    public void setIMSI(List<AndroidImsi> IMSI) {
        this.IMSI = IMSI;
    }

    public List<AndroidWifi> getWIFI() {
        return WIFI;
    }

    public void setWIFI(List<AndroidWifi> WIFI) {
        this.WIFI = WIFI;
    }
}
