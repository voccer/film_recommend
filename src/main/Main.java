package main;

import java.io.File;

import ui.ResultUI;
import ui.SearchUI;


public class Main {
	public static void main(String[] args) {
		
		SearchUI ui = new SearchUI("Searching...");
		ui.showWindow();
		
		String dirPath = System.getProperty("user.dir");
		File csv = new File(dirPath + "/Data/file.csv");
		while (true) {
			System.out.println("Waiting UI complete");
			if (!csv.exists()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else break;
		}
		//System.out.println("da co file csv");
		ResultUI rs = new ResultUI("Result");
		rs.showWindow();
		
		
		// dừng 0.5 s để load xong csv
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// xóa file csv để thực hiện lần sau
		
		csv.delete();
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







