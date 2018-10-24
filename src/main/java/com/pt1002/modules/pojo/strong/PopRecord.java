package com.pt1002.modules.pojo.strong;

import com.pt1002.modules.pojo.Persons;
import com.pt1002.modules.pojo.TemplatesWithBLOBs;

import java.util.List;

public class PopRecord {

    Persons persons;
    TemplatesWithBLOBs tBloBs;

    public TemplatesWithBLOBs gettBloBs() {
        return tBloBs;
    }

    public void settBloBs(TemplatesWithBLOBs tBloBs) {
        this.tBloBs = tBloBs;
    }

    public Persons getPersons() {
        return persons;
    }

    public void setPersons(Persons persons) {
        this.persons = persons;
    }
}
