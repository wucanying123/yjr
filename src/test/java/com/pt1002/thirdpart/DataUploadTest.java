package com.pt1002.thirdpart;

import com.pt1002.util.HttpUtil;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xubo
 * @Description:
 * @Date: Create in 14:13 2018/6/8
 * @Modified By:
 * @Test By:
 */
public class DataUploadTest {

    @Test
    public void testFileUpload(){

        HashMap<String, String> strParam = new HashMap<>();
        strParam.put("Description", "GUID=402D8D6F-1234-6547-4567-147854S125DED;CardNo=421022199212186711");

        HashMap<String, File> fileParam = new HashMap<>();
        fileParam.put("Card", new File("D:\\test-pictures\\11.jpg"));
        fileParam.put("Face", new File("D:\\test-pictures\\22.jpg"));
        fileParam.put("Snapshot", new File("D:\\test-pictures\\33.jpg"));

        UploadResp response = HttpUtil.postForm("http://10.0.0.139:10088/datadoorinterface/fileupload", strParam, fileParam, null, null, UploadResp.class);

        System.out.println(response);

    }

    @Test
    public void testPersoonUpload(){

        String guid = "38C4F04F-57EA-48C9-A857-953A0CB3F299";
        String pic = "127.0.0.1:10077@2018-06-08_2dd9abb31b6e41b2bfc28937bd02d340";
        String token = "57c07599-3c02-41bd-9afa-bd3c27a2e28a";

        Map map = new HashMap();
        String PersonId = guid;
        String Name = "胡淞";
        String Sex = "男";
        String Nation = "汉";
        String Birthday = "1986-12-04";
        String Address = "四川省乐山市市中区青平镇宝兴村12组472号";
        String CardNo = "511102198612045310";
        String SignGov = "乐山市公安局市中分局";
        String LimitedDate = "2008年11月27日--2018年11月27日";
        String EntryTime = "2017-11-02 16:10:10";
        String PositionID = "111000010003";
        String PositionName = "九鼎市场正大门";
        String Resemblance = "0.8";
        String CheckResult = "0";
        String SBJD = "75.0000";
        String SBWD = "134.9000";
        String Nationality = "CHN";
        String CardType = "111";
        String Direction = "1";
        String StationID = "660000000000";
        String StationName = "九鼎市场正大门";

        map.put("heartbeat","");//心跳包
        map.put("PersonId", PersonId);
        map.put("Name", Name);
        map.put("Sex", Sex);
        map.put("Nation", Nation);
        map.put("Birthday", Birthday);
        map.put("Address", Address);
        map.put("CardNo", CardNo);
        map.put("SignGov", SignGov);
        map.put("LimitedDate", LimitedDate);
        map.put("EntryTime", EntryTime);
        map.put("Dubious", "0");//该数据为重点人员核验返回结果
        map.put("PositionID", PositionID);
        map.put("PositionName", PositionName);
        map.put("Resemblance", Resemblance);
        map.put("CheckResult", CheckResult);
        map.put("SBJD", SBJD);
        map.put("SBWD", SBWD);
        map.put("ZFSPATH", pic);//该数据为图片上传返回结果
        map.put("Note", "");//该数据为重点人员核验返回结果
        map.put("Nationality", Nationality);
        map.put("CardType", CardType);
        map.put("Direction", Direction);
        map.put("StationID", StationID);
        map.put("StationName", StationName);
        map.put("Token", token);

        UploadResp response = HttpUtil.postJson("http://10.0.0.139:10088/datadoorinterface/person", map, UploadResp.class);
        System.out.println(response);
    }

}
