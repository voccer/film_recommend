package model;

import java.io.IOException;
import java.util.ArrayList;

import test.ReaderCSV;

/**
 * Class chuyển đổi ArrayList<ArrayList<String>> ReadCsv() của thánh Thuyên về ArrayList<FilmDetail> ReadCSV() cho dễ đọc
 * @author ThanhTuan
 *
 */
public class ConversetListFilm {
	
	/**
	 * Hàm đọc file CSV
	 * @param path : đường dẫn tới file.csv
	 * @return hàm trả về một ArrayList các FilmDetail
	 */
	public ArrayList<FilmDetail> ReadCSV(String path) {
		ArrayList<FilmDetail> list_film = new ArrayList<FilmDetail>();
		ReaderCSV readerCSV = new ReaderCSV() ;
		ArrayList<ArrayList<String>> list_col = null;
		try {
			list_col = readerCSV.ReadCsv(path);
		} catch (IOException e) {
			System.out.println("Lỗi đọc file , kiểm tra lại đường dẫn");
			e.printStackTrace();
		}
		
		if (list_col==null) {
			return null ;
		}
		for (int i = 0; i < list_col.get(0).size(); i++) {
			FilmDetail filmDetail = new FilmDetail();
			filmDetail.setId(String.valueOf(i+1));
			filmDetail.setLink(list_col.get(0).get(i));
			filmDetail.setName(list_col.get(1).get(i));
			try {
				filmDetail.setScore_star(Float.valueOf(list_col.get(2).get(i)));
			} catch (NumberFormatException e) {
				System.out.println("có ngoại lệ ở cột điểm chuyên gia");
				filmDetail.setScore_star(0);
			}
			
			filmDetail.setYear(ConverYear(list_col.get(3).get(i)));
			
			filmDetail.setDescription(list_col.get(4).get(i));
			
			try {
				filmDetail.setTotalcomment(Float.valueOf(list_col.get(5).get(i)));
			} catch (NumberFormatException e) {
				System.out.println("có ngoại lệ ở cột tổng comment");
				filmDetail.setTotalcomment(0);
			}
			try {
				filmDetail.setPositivecomment(Float.valueOf(list_col.get(6).get(i)));
			} catch (NumberFormatException e) {
				System.out.println("có ngoại lệ ở cột số comment tốt");
				filmDetail.setPositivecomment(0);
			} 
			try {
				filmDetail.setAvgstar(Float.valueOf(list_col.get(7).get(i)));
			} catch (NumberFormatException e) {
				System.out.println("có ngoại lệ ở cột số sao trung bình");
				filmDetail.setAvgstar(0);
			}
			filmDetail.setScore_rank(0);
			list_film.add(filmDetail);
		}
		return list_film;
	}
	/**
	 * Hàm tìm và chuyển đổi một chuỗi thành năm tương ứng
	 * @param year_string chuỗi có chứa năm trong đó
	 * @return
	 */
	private int ConverYear(String year_string) {
		int index = 0 ;
		int year ;
		for (int i = 0; i < year_string.length(); i++) {
			if (isNumber(year_string.charAt(i))) {
				index ++ ;
			} else {
				index = 0 ;
			}
			if (index==4) {
				year = Integer.valueOf(year_string.substring(i-3, i+1)) ;
				if (year<=2019 && year > 1980) {
					return year ;
				}
			}
		}
		return 2010;
		
	}
	
	private boolean isNumber(char c) {
		if (c >= '0' && c <= '9' ) {
			return true ;
		}
		return false ;
	}
	

	
}
