package com.pt1002.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationConstant {

	private ApplicationConstant() {
	}

	/**
	 * Token存储的Map对象
	 */
	//public static Map<String, String> TOKEN_SAVE = new HashMap<String, String>();
	public static Map<String, String> TOKEN_SAVE = new ConcurrentHashMap<String,String>();
	
	public final static List<String> IMAGE_SUFFIXNAME_FORMAT = Arrays.asList(".bmp",".jpg",".jpeg",".png",".gif");
	
	public final static List<String> ZIP_SUFFIXNAME_FORMAT = Arrays.asList(".zip",".jar",".rar");

	public final static List<String> APK_SUFFIXNAME_FORMAT = Arrays.asList(".apk");

	public final static String RETURN_SOCKET_ERROR = "SOCKETSENDERROR";
	
	public final static String RETURN_FAILED = "FAILED";
	
	public final static String FAIL = "FAIL";
	
	public final static String SUCCESS = "SUCCESS";
	
	public final static String FILE_FORMAT_ERROR = "FILRFORMATERROR";
	
	//populationFace not's idCardPic
	public final static String NOT_IDCARDPIC = "0";
	
	//populationFace is idCardPic
	public final static String IS_IDCARDPIC = "1";
	
	//device online
	public final static String DEV_STATE_ONLINE = "1";
	
	//device is not online
	public final static String DEV_STATE_NOT_ONLINE = "0";
	
	//certification record state of SystemPass
	public final static String CER_RECORD_STATE_SYS_PASS = "0";
	
	//certification record state of SystemNotPass
	public final static String CER_RECORD_STATE_SYS_NOT_PASS = "1";
	
	//certification record state of ExaPass
	public final static String CER_RECORD_STATE_EXA_PASS = "2";
	
	//certification record state of ExaNotPass
	public final static String CER_RECORD_STATE_EXA_NOT_PASS = "3";
	
	//population face is idCardPic
	public final static String POP_FACE_IS_IDCARDPIC = "1";
	
	//population face not's idCardPic
	public final static String POP_FACE_NOT_IDCARDPIC = "0";
	
}
