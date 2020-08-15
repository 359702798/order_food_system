package com.group.order_food_system.utill;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;


public class UpUtils {

	// 把这个工具类变成动态 多设置一个参数    文件名称
	public static String upfile(MultipartFile file, HttpServletRequest request) {

		//获取服务器的地址
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/img/");

		//获取本地电脑中的地址
		String basePath="E:\\work\\order_food_system\\src\\main\\webapp\\WEB-INF\\img\\";
		//判定目标文件夹是否存在   如果不存在则进行创建
		File file1 = new File(realPath);
		if (!file1.exists()) {
			file1.mkdirs();
		}

		File file2 = new File(basePath);
		if(!file2.exists()) {
             file2.mkdirs();
		}

		// 获取文件的名称   qy.mp4    web-inf/video/qy.mp4
		String orgName = file.getOriginalFilename();
		try {
			FileOutputStream fos = new FileOutputStream(realPath+orgName,true);
			FileOutputStream fos1 = new FileOutputStream(basePath+orgName,true);
			fos.write(file.getBytes());
			fos1.write(file.getBytes());
			fos1.flush();
			fos.flush();
			fos1.close();
			fos.close();
			return orgName;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

}
