package com.pt1002.spring.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "template")
public class TemplatesProp {
    public  static String START_EXE ="";

    public static String getStartExe() {
        return START_EXE;
    }

    public static void setStartExe(String startExe) {
        START_EXE = startExe;
    }
}
