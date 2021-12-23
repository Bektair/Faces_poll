package entities;

public class Alternative {
	String alt_txt;
	String emoji_id;
	Boolean isEmoji;
	int alt_id;
	Boolean hasAnswered;
	
	public Alternative(String alt_txt, String emoji_id, boolean isEmoji, int alt_id, boolean hasAnswered) {
		this.alt_txt = alt_txt;
		this.emoji_id = emoji_id;
		this.isEmoji = isEmoji;
		this.alt_id = alt_id;
		this.hasAnswered = hasAnswered;
	}
	
	public String getAlt_txt() {
		return alt_txt;
	}


	public String getEmoji_id() {
		return emoji_id;
	}

	public Boolean getHasAnswered() {
		return hasAnswered;
	}


	public void setHasAnswered(Boolean hasAnswered) {
		this.hasAnswered = hasAnswered;
	}
	public Boolean getIsEmoji() {
		return isEmoji;
	}



	
	public int getAlt_id() {
		return alt_id;
	}


	public void setAlt_id(int alt_id) {
		this.alt_id = alt_id;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
