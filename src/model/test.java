package model;

import java.util.ArrayList;

/**
 * hàm test chức năng ranking film
 * @author ThanhTuan
 *
 */
public class test {
	public static void main(String[] args) {
		TOPSIS topsis = new TOPSIS();
		//Lấy danh sách đã đánh giá
		ArrayList<FilmDetail> list = topsis.ListFilmRanked("./Data/file.csv") ;	
		// In thử một số thuộc tính để kiểm tra
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Id= "+list.get(i).getId()+" / "+list.get(i).getName()+" / "+list.get(i).getYear()+" /" + list.get(i).getScore_rank());
		}
		
		
		
	}

}
