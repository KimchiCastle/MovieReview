package vo;

public class MovieVo {
	
	int movieidx;
	String title;
	
	public MovieVo(int movieidx, String title) {
		this.movieidx = movieidx;
		this.title = title;
	}
	
	public MovieVo() {
		
	}
	
	public int getMovieidx() {
		return movieidx;
	}
	public void setMovieidx(int movieidx) {
		this.movieidx = movieidx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
