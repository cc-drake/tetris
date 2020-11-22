package de.drake.tetris.model.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class PositionHashMap<ContentClass> {
	
	private HashMap<Integer, HashMap<Integer, ContentClass>> content = new HashMap<Integer, HashMap<Integer, ContentClass>>();
	
	private int yMin() {
		if (this.content.isEmpty())
			return Integer.MAX_VALUE;
		return Collections.min(this.content.keySet());
	}
	
	private int yMax() {
		if (this.content.isEmpty())
			return Integer.MIN_VALUE;
		return Collections.max(this.content.keySet());
	}

	public void put(final int x, final int y, final ContentClass value) {
		if (!this.content.containsKey(y)) {
			this.content.put(y, new HashMap<Integer, ContentClass>());
		}
		this.content.get(y).put(x, value);
	}
	
	public void put(final Position position, final ContentClass value) {
		this.put(position.getX(), position.getY(), value);
	}
	
	public ContentClass remove(final int x, final int y) {
		if (!this.content.containsKey(y)) {
			return null;
		}
		ContentClass result = this.content.get(y).remove(x);
		if (this.content.get(y).isEmpty())
			this.content.remove(y);
		return result;
	}
	
	public ContentClass remove(final Position position) {
		return this.remove(position.getX(), position.getY());
	}
	
	public void insertRow(final int zeile) {
		HashMap<Integer, HashMap<Integer, ContentClass>> newContent = new HashMap<Integer, HashMap<Integer, ContentClass>>();
		for (int y : this.content.keySet()) {
			if (y > zeile) {
				newContent.put(y, this.content.get(y));
			} else {
				newContent.put(y - 1, this.content.get(y));
			}
		}
		this.content = newContent;
	}
	
	public void cut(final int spalte, final int zeile) {
		this.remove(spalte, zeile);
		
		for (int y = zeile - 1; y >= this.yMin(); y--) {
			ContentClass value = this.remove(spalte, y);
			if (value != null)
				this.put(spalte, y + 1, value);
		}
	}
	
	public void cutRow(final int zeile) {
		this.content.remove(zeile);
		HashMap<Integer, HashMap<Integer, ContentClass>> newContent = new HashMap<Integer, HashMap<Integer, ContentClass>>();
		for (int y : this.content.keySet()) {
			if (y > zeile) {
				newContent.put(y, this.content.get(y));
			} else {
				newContent.put(y + 1, this.content.get(y));
			}
		}
		this.content = newContent;
	}
	
	public void cutColumn(final int spalte) {
		for (int y = this.yMin(); y <= this.yMax(); y++) {
			this.remove(spalte, y);
		}
	}
	
	public ContentClass get(final int x, final int y) {
		if (!this.content.containsKey(y)) {
			return null;
		}
		return this.content.get(y).get(x);
	}
	
	public ContentClass get(final Position position) {
		return this.get(position.getX(), position.getY());
	}
	
	public Set<Integer> getRows() {
		return this.content.keySet();
	}

	public boolean containsKey(final int x, final int y) {
		if (!this.content.containsKey(y)) {
			return false;
		}
		return this.content.get(y).containsKey(x);
	}
	
	public boolean containsKey(final Position position) {
		return this.containsKey(position.getX(), position.getY());
	}
	
}