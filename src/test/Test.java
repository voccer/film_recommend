package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String path = System.getProperty("user.dir");
		File file = new File(path + "/Communication/request.txt");
		// System.out.println(file.getAbsolutePath());
		// System.out.println(file.exists());
//		if (file.exists()) {
//			file.delete();
//		}
//		try {
//			file.createNewFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// Tạo một OutputStream (luồng đầu ra) để ghi dữ liệu vào file.
		OutputStream out = null;
		try {
			out = new FileOutputStream(file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Tạo một Character Stream (luồng ghi ký tự) bao lấy OutputStream ở trên.
		// Mã hóa (encoding) là UTF-8.
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
		String trongduc = new String("Trongduc");
		
		bw.write(trongduc);
		bw.newLine();
		bw.close();
	

	}

}
