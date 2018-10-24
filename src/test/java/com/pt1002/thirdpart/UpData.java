package com.pt1002.thirdpart;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class UpData {
	public static void main(String[] args) {
		String guid = "38C4F04F-57EA-48C9-A857-953A0CB3F299";
		String pic = "202.100.180.51:8080@2017-11-02-05_9842";
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
         
         String j = new Gson().toJson(map);
        // System.out.println(j);
         //http://202.100.180.51:8080/QueryData/QueryData?personid=DEA74F92-3A69-48EB-BC01-6556730DA88E
         j = "{\"LimitedDate\":\"2008年11月27日--2018年11月27日\",\"SBJD\":\"\",\"Direction\":\"1\",\"CardNo\":\"511102198612045310\",\"StationID\":\"660000000000\",\"Birthday\":\"1986-12-04\",\"CheckResult\":\"1\",\"Resemblance\":\"0.88\",\"Nation\":\"汉\",\"CardType\":\"111\",\"EntryTime\":\"2017-11-06 12:54:33\",\"Sex\":\"男\",\"StationName\":\"九鼎市场正大门\",\"ZFSPATH\":\"202.100.180.51:8080@2017-11-06-12_6461\",\"PositionName\":\"九鼎市场正大门\",\"Note\":\"测试\",\"Token\":\"57c07599-3c02-41bd-9afa-bd3c27a2e28a\",\"SignGov\":\"乐山市公安局市中分局\",\"Nationality\":\"CHN\",\"Name\":\"胡淞\",\"PersonId\":\"DEA74F92-3A69-48EB-BC01-6556730DA88E\",\"heartbeat\":\"\",\"Address\":\"四川省乐山市市中区青平镇宝兴村12组472号\",\"SBWD\":\"\",\"PositionID\":\"111000010003\",\"Dubious\":\"0\"}";
//         String ret = TestGays.Read("http://202.100.180.51:8080/SmzPersonUpload/PersonUpload", j);
//         String ret = TestGays.Read("http://127.0.0.1:8080/mweb/test/testReq.do", j);
//         System.out.println(ret);
	}
}
