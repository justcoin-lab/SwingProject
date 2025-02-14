package match;

import java.util.Date;

public class MatchDto {

	private String matchName;
	private Date matchDate;
	private String opposing;
	private int ourScore;
	private int oppScore;
	private int yellowCard;
	private int redCard;
	private String mvp;
	
	public MatchDto(String matchName, Date matchDate, String opposing, int ourScore, int oppScore, int yellowCard, int redCard, String mvp) {
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
	public Date getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(Date matchDate) {
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
	public String getMvp() {
		return mvp;
	}
	public void setMvp(String mvp) {
		this.mvp = mvp;
	}
	
}
