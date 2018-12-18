package model;


/**
 * Class chứa thông tin chi tiết về một bộ phim , bao gồm id , link , tên , điểm chuyên gia , năm , miêu tả , tổng comment , số comment tốt , 
 * trung bình vote , điểm đánh giá dùng TOPSIS
 * @author ThanhTuan
 *
 */
public class FilmDetail implements Comparable<FilmDetail> {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	private String id ;
	private String link ;
	private String name ;
	private float score_star ;
	private int year ;
	private String description ;
	private float totalcomment ;
	private float positivecomment ;
	private float avgstar ;
	private float score_rank ;
	
	
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getScore_star() {
		return score_star;
	}
	public void setScore_star(float score_star) {
		this.score_star = score_star;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getTotalcomment() {
		return totalcomment;
	}
	public void setTotalcomment(float totalcomment) {
		this.totalcomment = totalcomment;
	}
	public float getPositivecomment() {
		return positivecomment;
	}
	public void setPositivecomment(float positivecomment) {
		this.positivecomment = positivecomment;
	}
	public float getAvgstar() {
		return avgstar;
	}
	public void setAvgstar(float avgstar) {
		this.avgstar = avgstar;
	}
	public float getScore_rank() {
		return score_rank;
	}
	public void setScore_rank(float score_rank) {
		this.score_rank = score_rank;
	}
	/**
	 * Hàm so sánh sách FildmDetail , mục đích để sắp xếp các film
	 */
	@Override
	public int compareTo(FilmDetail filmDetail) {
		if (score_rank == filmDetail.getScore_rank()) {
			return 0 ;
		}else if (score_rank > filmDetail.getScore_rank()) {
			return -1 ;
		} else {
			return 1 ;
		}
	}
	
}
