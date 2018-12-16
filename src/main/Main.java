package main;

import java.io.IOException;
import java.util.ArrayList;

import test.ReaderCSV;
import ui.ResultUI;
import ui.SearchUI;

public class Main {
	private static String SAMPLE_CSV_FILE_PATH = System.getProperty("user.dir") + "/Data/file.csv";
	public static void main(String[] args) {
		
		//SearchUI ui = new SearchUI("Searching...");
		//ui.showWindow();
		ReaderCSV read = new ReaderCSV();
		ArrayList<ArrayList<String>> ListInfo = new ArrayList<>();
		try {
			ListInfo = read.ReadCsv(SAMPLE_CSV_FILE_PATH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultUI abc = new ResultUI("Result");
		abc.showWindow();
	}

}
	