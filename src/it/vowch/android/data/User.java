package it.vowch.android.data;

import android.net.Uri;

public class User {
	private String username;
	private String firstName;
	private String lastName;
	private Uri imageUri;
	
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

	public Uri getImageUri() {
		return imageUri;
	}

	protected void setImageUrl(Uri imageUri) {
		this.imageUri = imageUri;
	}
}