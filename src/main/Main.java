package main;

import java.io.IOException;
import java.util.ArrayList;

import model.ConversetListFilm;
import model.FilmDetail;
import model.TOPSIS;
import test.ReaderCSV;
import ui.ResultUI;
import ui.SearchUI;


public class Main {
	private static String SAMPLE_CSV_FILE_PATH = System.getProperty("user.dir") + "/Data/file.csv";
	public static void main(String[] args) {
		
		SearchUI ui = new SearchUI("Searching...");
		ui.showWindow();
		ResultUI abc = new ResultUI("Result");
		abc.showWindow();
	}
}

/*
'''
các trường liên quan tới việc đánh giá..
date
totalComment
PositiveComment
AVG star -- sao trung bình của các bình luận
star -- sao của chuyên gia đánh giá cho phim

Thứ tự: 
		positiveComment/totalComment ---> 1
        totalComment/(currentDate - Date) --->2 # Trong đó currentDate - Date tính theo năm, bởi vì trong 1 năm, sự tương tác nhiều ít giữa các phim mình bỏ qua.
         										# Mình chỉ xét sự khác biệt trong các năm khác nhau, để giảm trọng số khi chuyên lệch qúa lớn
        
        AVG star ---> 3
        star ---> 4
'''
*/	







