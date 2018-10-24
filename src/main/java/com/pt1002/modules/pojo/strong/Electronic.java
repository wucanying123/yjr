package com.pt1002.modules.pojo.strong;

import com.pt1002.modules.pojo.Imsi;
import com.pt1002.modules.pojo.Wifi;

import java.util.List;

public class Electronic {
    List<Imsi> IMSI;
    List<Wifi> WIFI;

    public List<Imsi> getIMSI() {
        return IMSI;
    }

    public void setIMSI(List<Imsi> IMSI) {
        this.IMSI = IMSI;
    }

    public List<Wifi> getWIFI() {
        return WIFI;
    }

    public void setWIFI(List<Wifi> WIFI) {
        this.WIFI = WIFI;
    }
}
