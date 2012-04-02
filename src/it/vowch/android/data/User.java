package it.vowch.android.data;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable
public class User implements Serializable {

	private static final long serialVersionUID = -6582623980712135028L;

	public static final String DATE_FIELD_NAME = "lastClickDate";

	@DatabaseField(generatedId = true)
	private Integer id;

	@DatabaseField
	private String first_name;

	@DatabaseField
	private String last_name;
	
	@DatabaseField(canBeNull = true, foreign = true)
	private ClickGroup group;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClickGroup getGroup() {
		return group;
	}

	public void setGroup(ClickGroup group) {
		this.group = group;
	}

	public Date getLastClickDate() {
		return lastClickDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return first_name + " " + last_name;
	}
}
