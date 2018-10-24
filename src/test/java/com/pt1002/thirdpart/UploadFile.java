package com.pt1002.thirdpart;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;




public class UploadFile {

	public static final String newLine = "\r\n";
	public static final String boundaryPrefix = "--";
	public static String BOUNDARY = "wqilauwfas651fw32qer465q4wr2a1f65a";

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//GUID????Ψ?????????????????????36
//		ZfsFileWrite("http://127.0.0.1:8080/ZfsServer/servlet/FileServlet",
//				"402D8D6F-0432-4E98-82B1-18DE9BF012ED","11111",
//				"D:\\1.jpg","D:\\2.jpg","D:\\3.jpg");
		ZfsFileWrite("http://10.184.14.159:8080/ZfsServer/servlet/FileServlet",
				"402D8D6F-0432-4E98-82B1-18DE9BF012ED","65212219870105321X",
				"D:\\xpj\\1.jpg","D:\\xpj\\2.jpg","D:\\xpj\\3.jpg");
	}
	
	private static String ZfsFileWrite(String strUrl, String strGUID, String strCardNo, String strCardFileName,
			String strFaceFileName, String strSnapshotFileName)
	{
		String strRet = "";
		try{
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + BOUNDARY);
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			
			WriteTextDomain(strGUID, strCardNo, out);
			WriteFileDomain(strCardFileName, "Card", out);
			WriteFileDomain(strFaceFileName, "Face", out);
			WriteFileDomain(strSnapshotFileName, "Snapshot", out);
			
			byte[] end_data = (newLine + boundaryPrefix + BOUNDARY + boundaryPrefix + newLine).getBytes();
			out.write(end_data);
			
			out.flush();
			out.close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while((strRet = reader.readLine()) != null)
			{
				System.out.println(strRet);
			}
			System.out.println("send post success");
		}
		catch(Exception e)
		{
			System.out.println("send post error");
			strRet = "";
		}
		return strRet;
	}
	
	private static void WriteFileDomain(String strFileName, String strFileType, OutputStream out)
	{
		File file = new File(strFileName);
		StringBuilder sb = new StringBuilder();
		sb.append(boundaryPrefix);
		sb.append(BOUNDARY);
		sb.append(newLine);
		sb.append("Content-Disposition: form-data; name=\"" + strFileType + "\";filename=\"" + "" + "\"" + newLine);
		sb.append("Content-Type:application/octet-stream");
		sb.append(newLine + newLine);
		try {
			out.write(sb.toString().getBytes());
			
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			byte[] bufferOut = new byte[1024];
			int bytes = 0;
			while((bytes = in.read(bufferOut))!= -1)
			{
				out.write(bufferOut, 0, bytes);
			}
			out.write(newLine.getBytes());
			in.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void WriteTextDomain(String strGUID, String strCardNo, OutputStream out)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(boundaryPrefix);
		sb.append(BOUNDARY);
		sb.append(newLine);
		sb.append("Content-Disposition: form-data; name=\"" + "Description" + "\"" + newLine);
		//sb.append("Content-Type:text/plain");
		sb.append(newLine + newLine);
		try {
			out.write(sb.toString().getBytes());
			
			String desc = "GUID=" + strGUID + ";CardNo=" + strCardNo;
			
			out.write(desc.getBytes());
			out.write(newLine.getBytes());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
