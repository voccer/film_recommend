package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class ranking các bộ film
 * @author ThanhTuan
 *
 */
public class TOPSIS {
	float[][] matrix = null ; // ma trận quyết đinh
	
	/**
	 * Hàm nhận đầu vào là một đường dẫn tới file.CVS và trả về danh sách các film đã được đánh giá và sắp xếp
	 * @param path Đường dẫn đến file.csv chứa sanh sách các film
	 * @return Trả về một danh sách (Arraylist) các film đã được ranking , có chấm điểm ranking và xếp thứ tự
	 */
	public ArrayList<FilmDetail> ListFilmRanked(String path) {
		ConversetListFilm conversetListFilm = new ConversetListFilm() ;
		ArrayList<FilmDetail> list_film_ranked = conversetListFilm.ReadCSV(path) ;
		Ranking(list_film_ranked);
		return list_film_ranked ;
	}
	
	/**
	 * Hàm Tính toán điểm ranking cho film và sắp xếp nó , hàm không có kết quả trả về , 
	 * nó sẽ chấm điểm và lưu vào trong danh sách film truyền vào luôn , sau đó sắp xếp danh sách đó luôn , do đó nó ko cần trả về gì cả
	 * @param arrayList tham số truyền vào là một danh sách các bộ film
	 */
	public void Ranking(ArrayList<FilmDetail> arrayList){
		int totalrow = arrayList.size() ;
		matrix = ConverMatrixDecide(arrayList) ;
		float[] A_max = new float[4] ; // Giải pháp lý tưởng
		float[] A_min = new float[4] ; // giải pháp tồi nhất
		float[] S_max = new float[totalrow] ; // khoảng cách tới giải pháp tốt nhất
		float[] S_min = new float[totalrow] ; // khoảng cách tới giải pháp tồi nhất
		float[] C = new float[totalrow] ; // độ đo tương tự tới giải pháp lý tưởng
		// tính A_max , A_min
		for (int i = 0; i < 4; i++) {
			A_max[i] = Max(matrix[i]) ;
			A_min[i] = Min(matrix[i]);
		}
		// Tính S_max , S_min
		for (int i = 0; i < totalrow; i++) {
			float sum_max = 0 ;
			float sum_min = 0 ;
			for (int j = 0; j < 4; j++) {
				sum_max += (matrix[j][i] - A_max[j])*(matrix[j][i] - A_max[j]) ;
				sum_min += (matrix[j][i] - A_min[j])*(matrix[j][i] - A_min[j]) ;
			}
			S_max[i] = (float) Math.sqrt(sum_max) ;
			S_min[i] = (float) Math.sqrt(sum_min) ;
		}
		// Tính C
		
		for (int i = 0; i < totalrow; i++) {
			C[i] = S_min[i]/(S_min[i]+S_max[i]) ;
		}
		// gán các giá trị tính toán được cho film
		for (int i = 0; i < totalrow; i++) {
			arrayList.get(i).setScore_rank(C[i]*100);
		}
		// Sắp xếp các film theo giá trị Score_rank
		Collections.sort(arrayList);
		
		
	}
	
	
	/**
	 * hàm chuyển đổi ArrayList các film thành ma trận quyết đinh tương ứng
	 * lưu ý : ma trận quyết định này có hàng với cột ngược nhau so với lý thuyết , lý do để ngược là để tiện tính toán
	 * ma trận quyết đinh trả về đã được chuẩn hóa và nhân trọng số
	 * @param arrayList chuỗi các film cần ranking
	 * @return ma trận quyết định
	 */
	private float[][] ConverMatrixDecide(ArrayList<FilmDetail> arrayList) {
		int tolrow = arrayList.size();
		float[] weight = {(float) 0.4,(float) 0.3,(float) 0.2,(float) 0.1} ;
		float[][] matrix = new float[4][tolrow] ;
		// Gán giá trị
		// thuộc tính đầu tiên và quan trọng nhất của ma trận là số bình luận tốt trên tổng số bình luộn
		for (int i = 0; i < tolrow ; i++) {
			matrix[0][i] = arrayList.get(i).getPositivecomment()/(arrayList.get(i).getTotalcomment()+1) ;
			
		}
		// thuộc tính quan trọng thứ 2 là điểm trung bình đánh giá của người xem
		for (int i = 0; i < tolrow; i++) {
			matrix[1][i] = arrayList.get(i).getAvgstar() ;
			
		}
		// thuộc tính quan trọng thứ 3 là tổng số bình luận chia cho tuổi đời của film
		for (int i = 0; i < tolrow; i++) {
			float old = 2018-arrayList.get(i).getYear() ;
			old = old!= 0?old:1; 
			matrix[2][i] = arrayList.get(i).getTotalcomment()/old;
			
		}
		// thuộc tính quan trọng thứ 4 là điểm đánh giá của chuyên gia
		for (int i = 0; i < tolrow; i++) {
			matrix[3][i] = arrayList.get(i).getScore_star() ;
		}
		
		
		// chuẩn hóa ma trận và nhân trọng số
		for (int i = 0; i < 4; i++) {
			// Chuẩn hóa ma trận
			NormalizationVector(matrix[i]);
			// Nhân trọng số
			for (int j = 0; j < tolrow; j++) {
				matrix[i][j] = matrix[i][j]*weight[i] ;
			}
		}
		
		
		return matrix;
	}
	
	/**
	 * Hàm chuẩn hóa một vector
	 * @param vector
	 */
	private void NormalizationVector(float[] vector) {
		float sum = 0 ;
		for (int i = 0; i < vector.length; i++) {
			sum += vector[i]*vector[i] ;
		}
		sum = (float) Math.sqrt(sum) ;
		for (int i = 0; i < vector.length; i++) {
			vector[i] = vector[i]/sum ;
		}
	}
	
	/**
	 * hàm tính min của một mảng
	 * @param x
	 * @return
	 */
	private float Min(float[] x) {
		float min=10000 ;
		for (int i = 0; i < x.length; i++) {
			min = min<=x[i]?min:x[i] ;
		}
		return min ;
	}

	/**
	 * hàm tính max của một mảng
	 * @param x
	 * @return
	 */
	private float Max(float[] x) {
		float max=-1 ;
		for (int i = 0; i < x.length; i++) {
			max = max>=x[i]?max:x[i] ;
		}
		return max ;
	}
}
