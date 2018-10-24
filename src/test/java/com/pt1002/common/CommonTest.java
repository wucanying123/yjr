package com.pt1002.common;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @Author: xubo
 * @Description:
 * @Date: Create in 19:39 2018/6/8
 * @Modified By:
 * @Test By:
 */
public class CommonTest {

    @Test
    public void testList(){
        ArrayList<String> ids = new ArrayList<>();
        ids.add("1");
        ids.add("2");

        System.out.println(ids.get(0));

    }

}
