package main;

import java.util.ArrayList;

public class Instruction {
	
	private String readState;
	private String writeState;
	private String readElement;
	private String writeElement;
	private boolean direction;
	
	public String getReadState() {
		return readState;
	}
	
	public void setReadState(String readState) {
		this.readState = readState;
	}
	
	public String getWriteState() {
		return writeState;
	}
	
	public void setWriteState(String writeState) {
		this.writeState = writeState;
	}
	
	public String getReadElement() {
		return readElement;
	}
	
	public void setReadElement(String readElement) {
		this.readElement = readElement;
	}
	
	public String getWriteElement() {
		return writeElement;
	}
	
	public void setWriteElement(String writeElement) {
		this.writeElement = writeElement;
	}
	
	public boolean getDirection() {
		return direction;
	}
	
	public void setDirection(boolean direction) {
		this.direction = direction;
	}
	
	public boolean valid (String readState, String readElement) {
		if (this.readState.equals(readState) && this.readElement.contentEquals(readElement)) {
			return false;
		}
		return true;
	}

}
