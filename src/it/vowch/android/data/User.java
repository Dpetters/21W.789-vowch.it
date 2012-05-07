package it.vowch.android.data;

public class User {
	private String username;
	private String firstName;
	private String lastName;
	private String imageUrl;
	
	User() {}
	
	protected String getUsername() {
		return username;
	}

	protected void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	protected void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	protected void setPath_to_image(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}