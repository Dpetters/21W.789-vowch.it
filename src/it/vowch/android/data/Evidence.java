package it.vowch.android.data;

public class Evidence {
	private User user;
	private String action;

	Evidence() {}

	public User getUser() {
		return user;
	}

	protected void setUser(User user) {
		this.user = user;
	}

	public String getAction() {
		return action;
	}

	protected void setAction(String action) {
		this.action = action;
	}
}