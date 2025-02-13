package match;

public class MatchDto {

	private String matchName;
	private String matchDate;
	private String opposing;
	private int ourScore;
	private int oppScore;
	private int yellowCard;
	private int redCard;
	private int mvp;
	
	public MatchDto(String matchName, String matchDate, String opposing, int ourScore, int oppScore, int yellowCard, int redCard, int mvp) {
		this.matchName = matchName;
		this.matchDate = matchDate;
		this.opposing = opposing;
		this.ourScore = ourScore;
		this.oppScore = oppScore;
		this.yellowCard = yellowCard;
		this.redCard = redCard;
		this.mvp = mvp;
	}
	
	public String getMatchName() {
		return matchName;
	}
	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}
	public String getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	public String getOpposing() {
		return opposing;
	}
	public void setOpposing(String opposing) {
		this.opposing = opposing;
	}
	public int getOurScore() {
		return ourScore;
	}
	public void setOurScore(int ourScore) {
		this.ourScore = ourScore;
	}
	public int getOppScore() {
		return oppScore;
	}
	public void setOppScore(int oppScore) {
		this.oppScore = oppScore;
	}
	public int getYellowCard() {
		return yellowCard;
	}
	public void setYellowCard(int yellowCard) {
		this.yellowCard = yellowCard;
	}
	public int getRedCard() {
		return redCard;
	}
	public void setRedCard(int redCard) {
		this.redCard = redCard;
	}
	public int getMvp() {
		return mvp;
	}
	public void setMvp(int mvp) {
		this.mvp = mvp;
	}
	
}
