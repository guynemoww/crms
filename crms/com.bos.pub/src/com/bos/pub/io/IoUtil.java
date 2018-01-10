package com.bos.pub.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class IoUtil {

	public static void download(OutputStream os, File file, boolean delete) {
		if (file == null || !file.exists()) {
			return;
		}
		byte[] buffer = new byte[1024 * 8];
		int size = 0;
		FileInputStream in = null;
		try {
			// System.out.println("下载文件大小:" + file.length());
			in = new FileInputStream(file);
			while ((size = in.read(buffer)) != -1) {
				os.write(buffer, 0, size);
			}
			os.flush();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (os != null)
					os.close();
				if (delete && file.isFile() && file.exists()) {
					// System.out.println("下载完成，删除文件");
					file.delete();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
