package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class Test {

	public static void main(String[] args) throws IOException {
		executePython();
	}
	// Hàm xem tiến trình chạy của process

	private static void watch(final Process process) {
		new Thread() {
			public void run() {
				BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line = null;
				try {
					while ((line = input.readLine()) != null) {
						System.out.println(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public static void executePython() {
		String dirPath = System.getProperty("user.dir");
		File filePythonExecute = new File(dirPath + "/Python_Module/main.py");
		if (!filePythonExecute.exists()) {
			System.out.println("not found file");
		}

		Process process = null;
		try {
			process = Runtime.getRuntime().exec("python " + filePythonExecute.getAbsolutePath());
			// System.out.println(filePythonExecute.getAbsolutePath());
			// System.exit(1);
			watch(process);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Watch the process

	}

}
