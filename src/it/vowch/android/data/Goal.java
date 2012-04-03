package it.vowch.android.data;

public class Goal {
	private String title;
	private String schedule;
	private int maxOccurences;
	private int occurences;
	private int minutesLeft;
	private String grade;
	private int successRate;
	
	Goal() {}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public int getMaxOccurences() {
		return maxOccurences;
	}

	public void setMaxOccurences(int maxOccurences) {
		this.maxOccurences = maxOccurences;
	}

	public int getOccurences() {
		return occurences;
	}

	public void setOccurences(int occurences) {
		this.occurences = occurences;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(int successRate) {
		this.successRate = successRate;
	}

	public int getMinutesLeft() {
		return minutesLeft;
	}

	public void setMinutesLeft(int minutesLeft) {
		this.minutesLeft = minutesLeft;
	}
}