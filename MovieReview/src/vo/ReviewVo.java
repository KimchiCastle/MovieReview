package Vo;

public class ReviewVo {
	
	//¸®ºäÅ×ÀÌºí »ðÀÔ ¹× ¼öÁ¤ ¿ë
	int geulno;
	int movieidx;
	String geultext;
	String userId;
	String geulDate;
	
	
	//¸®ºä ºä Á¶È¸¿ë
	String title;
	String nickname;
	
	public String getTitle() {
		return title;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ReviewVo(int movieidx, String geultext, String userId, String geulDate) {
		this.movieidx = movieidx;
		this.geultext = geultext;
		this.userId = userId;
		this.geulDate = geulDate;
	}
	
	public ReviewVo(String nickname) {
		super();
		this.nickname = nickname;
	}

	public ReviewVo() {
		
	}

	public int getGeulno() {
		return geulno;
	}

	public void setGeulno(int geulno) {
		this.geulno = geulno;
	}

	public int getMovieidx() {
		return movieidx;
	}

	public void setMovieidx(int movieidx) {
		this.movieidx = movieidx;
	}

	public String getGeultext() {
		return geultext;
	}

	public void setGeultext(String geultext) {
		this.geultext = geultext;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGeulDate() {
		return geulDate;
	}

	public void setGeulDate(String geulDate) {
		this.geulDate = geulDate;
	}
	
	
	
}
