package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class TuringMachine {
	
	private ArrayList<Character> tape = new ArrayList<Character>();
	private Set<String> states = new HashSet<String>();
	private Set<Instruction> instructions = new HashSet<Instruction>();
	private String initState;
	private String acceptState;
	private String rejectState;
	
	public TuringMachine(File file) throws Exception {
		
		try {
		    Scanner scan = new Scanner(file);
			
		    while (scan.hasNextLine()) {
		    	
	    		String line = scan.nextLine();
	    		
	    		if(!line.startsWith("#")) {
	    			if(line.startsWith("fita")) {
	    				String input = line.split(" ")[1].trim();
	    				addTape(input);
	    			}
	    			
	    			if(line.startsWith("init")) {
	    				String initState = line.split("init")[1].trim();
	    				addState(initState);
	    				this.initState = initState; 
	    			}
	    			
	    			if(line.startsWith("accept")) {
	    				String acceptState = line.split("accept")[1].trim();
	    				if (!this.states.contains(acceptState)) {
	    					addState(acceptState);
	    					this.acceptState = acceptState; 
	    				} else {
	    					throw new Exception("Estado de aceita��o inv�lido");
	    				}
	    			}
	    			
	    			if(line.startsWith("reject")) {
	    				String rejectState = line.split("reject")[1].trim();
	    				if (!this.states.contains(rejectState)) {
	    					addState(rejectState);
	    					this.rejectState = rejectState; 
	    				} else {
	    					throw new Exception("Estado de rejei��o inv�lido");
	    				}
	    			}
	    			
	    			if(line.startsWith("q")) {
	    				String readState = line.split(",")[0].trim();
	    				String readElement = line.split(",")[1].trim();
	    				String writeState = line.split(",")[2].trim();
	    				String writeElement = line.split(",")[3].trim();
	    				String direction = line.split(",")[4].trim();
	    				addState(readState);
	    				addInstruction(readState, readElement, writeState, writeElement, direction);
	    				
	    			}
	    		}	    		
		    	
		    }
		    scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addTape(String input) {
		char[] charInput = input.toCharArray();
		for (char c : charInput) {
			this.tape.add(c);
		}		
	}
	
	public void printTape() {
		Iterator<Character> tapeIterator = this.tape.iterator();
		while(tapeIterator.hasNext()) {
			System.out.print(tapeIterator.next());
		}
	}
	
	public void addState(String state) {
		if (!this.states.contains(state)) {
			this.states.add(state);			
		}
	}
	
	public void printStates() {
		Iterator<String> statesIterator = this.states.iterator();
		while(statesIterator.hasNext()) {
			System.out.print(statesIterator.next() + " ");
		}
	}
	
	public void addInstruction(String readState, String readElement, String writeState, String writeElement, String direction) {
		Iterator<Instruction> instructionsIterator = this.instructions.iterator();
		boolean valid = true;
		while(instructionsIterator.hasNext() && valid) {
			valid = instructionsIterator.next().valid(readState, readElement);
		}
		if (valid) {
			Instruction i = new Instruction();
			i.setReadState(readState);
			i.setReadElement(readElement);
			i.setWriteState(writeState);
			i.setWriteElement(writeElement);
			if (direction == ">") {
				i.setDirection(true);
			} else {
				i.setDirection(false);
			}
			this.instructions.add(i);
		}
	}
	
	public void printInstructions() {
		Iterator<Instruction> instructionsIterator = this.instructions.iterator();
		while(instructionsIterator.hasNext()) {
			Instruction i = instructionsIterator.next();
			System.out.println(i.getReadState() 
					+ " " + i.getReadElement()
					+ " " + i.getWriteState() 
					+ " " + i.getWriteElement() 
					+ " " + i.getDirection());
		}
	}

}