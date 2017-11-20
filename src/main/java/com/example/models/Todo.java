package com.example.models;

public class Todo {
	private String id;
	private String title;
	private String contents;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		if (!(obj instanceof Todo)) {
			return false;
		}

		Todo other = (Todo) obj;
		return id == null ? other.getId() == null : id.equals(other.getId());
	}
	
	@Override
	public int hashCode() {
		return id == null ? Integer.MIN_VALUE : Integer.parseInt(id);
	}
}
