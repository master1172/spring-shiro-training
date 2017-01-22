package com.wangzhixuan.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by sterm on 2016/11/15.
 */

public class UploadUtil {
	private static Logger logger = LoggerFactory.getLogger(UploadUtil.class);
	
    private static String UPLOAD_PATH = "/static/upload/head/";

    // 附件上传--批量导入
	public static String fileUpload(String filePath, CommonsMultipartFile file) {
		OutputStream os = null;
		InputStream in = null;
		StringBuffer upLoadFilePath = new StringBuffer();
		try {

			String oldFileName = file.getOriginalFilename();// 原始文件名称
			if (!file.isEmpty()) {
				String fileFix = oldFileName.substring(oldFileName
						.lastIndexOf(".") + 1);// 文件后缀
				if (fileFix.equals("xlsx") || fileFix.equals("xls")) {
					StringBuffer newFileName = new StringBuffer(UUID
							.randomUUID().toString().replaceAll("-", "")
							+ "." + fileFix);// 新文件名称
					upLoadFilePath = new StringBuffer(filePath + "/temporary");// 上传附件路径
					File f = new File(upLoadFilePath.toString());
					if (!f.exists()) {
						f.mkdirs();
					}
					upLoadFilePath.append("/").append(newFileName);
					os = new FileOutputStream(upLoadFilePath.toString());
					// 拿到上传文件的输入流
					in = file.getInputStream();
					// 写入文件
					int b = 0;
					while ((b = in.read()) != -1) {
						os.write(b);
					}
					os.flush();
					os.close();
					in.close();
				} else {
					logger.error("file foramt is not supported:" + oldFileName);
				}
			} else {
				logger.error("file is empty:" + oldFileName);
			}
		} catch (IOException e) {

		} finally {

		}
		return upLoadFilePath.toString();
	}

	/**
	 * 附件上传-头像
	 * 
	 * @param people
	 * @param file
	 * @return
	 */
	public static String pictureUpLoad(String filePath,
			CommonsMultipartFile file) {
		try {
			String oldFileName = file.getOriginalFilename();// 原始文件名称

			if (!file.isEmpty()) {

				String fileExt = oldFileName.substring(oldFileName
						.lastIndexOf(".") + 1);// 文件后缀

				StringBuffer newFileName = new StringBuffer(UUID.randomUUID()
						.toString().replaceAll("-", "")
						+ "." + fileExt);// 新文件名称
				StringBuffer upLoadFilePath = new StringBuffer(filePath
						+ UPLOAD_PATH);// 上传附件路径
				StringBuffer downLoadFilePath = new StringBuffer(UPLOAD_PATH);// 下载附件路径
				File f = new File(upLoadFilePath.toString());
				if (!f.exists()) {
					f.mkdirs();
				}
				upLoadFilePath.append(newFileName);
				downLoadFilePath.append(newFileName);
				OutputStream os = new FileOutputStream(
						upLoadFilePath.toString());
				// 拿到上传文件的输入流
				InputStream in = file.getInputStream();
				// 写入文件
				int b = 0;
				while ((b = in.read()) != -1) {
					os.write(b);
				}
				os.flush();
				os.close();
				in.close();
				return downLoadFilePath.toString();

			}
		} catch (IOException e) {
			return "";
		} finally {

		}
		return "";
	}

	// 存储从Excel来的图片，返回存储图片路径
	public static String pictureUpLoad(String filePath,
			XSSFPictureData pictureData) {
		String fileExt = pictureData.suggestFileExtension();
		byte[] data = pictureData.getData();

		StringBuffer newFileName = new StringBuffer(UUID.randomUUID()
				.toString().replaceAll("-", "")
				+ "." + fileExt);// 新文件名称
		StringBuffer upLoadFilePath = new StringBuffer(filePath + UPLOAD_PATH);// 上传附件路径
		StringBuffer downLoadFilePath = new StringBuffer(UPLOAD_PATH);// 下载附件路径
		File f = new File(upLoadFilePath.toString());
		if (!f.exists()) {
			f.mkdirs();
		}
		upLoadFilePath.append(newFileName);
		downLoadFilePath.append(newFileName);

		try {
			OutputStream os = new FileOutputStream(upLoadFilePath.toString());
			os.write(data);
			os.flush();
			os.close();
		} catch (Exception exp) {
			return "";
		}

		return downLoadFilePath.toString();
	}
}
