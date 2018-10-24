package com.pt1002;


import com.pt1002.modules.mapper.TemplatesMapper;
import com.pt1002.modules.pojo.TemplatesWithBLOBs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Pt1002ApplicationTests {

	@Autowired
	TemplatesMapper templatesMapper;
	@Test
	public void contextLoads() {
		TemplatesWithBLOBs bloBs = new TemplatesWithBLOBs();
		byte[]  imageBlob = file2Byte("C:\\Users\\Administrator\\Desktop\\3D\\1.blob");
		byte[] surface = file2Byte("C:\\Users\\Administrator\\Desktop\\3D\\2.blob");
		bloBs.setHiRes(surface);
		bloBs.setHiResTex(imageBlob);
		templatesMapper.insertSelective(bloBs);
	}
	public byte[] file2Byte(String filePath){
		byte[] buffer = null;
		try
		{
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1)
			{
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return buffer;
	}
}
