package de.drake.tetris.view.input.util;

public class Key {
	
	private final int id;
	
	private final String description;

	public Key(final int id, final String description) {
		this.id = id;
		this.description = description;
	}

	@Override
	public int hashCode() {
		return this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Key other = (Key) obj;
		if (this.id != other.id)
			return false;
		return true;
	}

	public String getDescription() {
		return this.description;
	}
	
	public int getID() {
		return this.id;
	}
	
}