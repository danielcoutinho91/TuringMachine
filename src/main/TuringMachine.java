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
	private String initState = "";
	private String acceptState = "";
	private String rejectState = "";
	private String currentState = "";
	
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
	    					throw new Exception("Estado de aceitação inválido");
	    				}
	    			}
	    			
	    			if(line.startsWith("reject")) {
	    				String rejectState = line.split("reject")[1].trim();
	    				if (!this.states.contains(rejectState)) {
	    					addState(rejectState);
	    					this.rejectState = rejectState; 
	    				} else {
	    					throw new Exception("Estado de rejeição inválido");
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
		this.tape.add(0, '_');
		this.tape.add('_');		
	}
	
	public void printTape() {
		Iterator<Character> tapeIterator = this.tape.iterator();
		while(tapeIterator.hasNext()) {
			System.out.print(tapeIterator.next());
		}
		System.out.println("\n");
	}
	
	public void addState(String state) {
		if (!this.states.contains(state)) {
			this.states.add(state);			
		}
	}
	
	public void printStates() {
		Iterator<String> statesIterator = this.states.iterator();
		System.out.println("States:");
		while(statesIterator.hasNext()) {
			System.out.print(statesIterator.next() + " ");
		}
		System.out.println("\n");
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
			if (direction.equals(">")) {
				i.setDirection(true);
			} else {
				i.setDirection(false);
			}
			this.instructions.add(i);
		}
	}
	
	public void printInstructions() {
		Iterator<Instruction> instructionsIterator = this.instructions.iterator();
		System.out.println("Instructions: ");
		while(instructionsIterator.hasNext()) {
			Instruction i = instructionsIterator.next();
			System.out.print(i.getReadState() 
					+ " " + i.getReadElement()
					+ " " + i.getWriteState() 
					+ " " + i.getWriteElement());
			if (i.getDirection()) {
				System.out.println(" >");
			} else {
				System.out.println(" <");
			}
			
		}
		System.out.println();
	}
	
	public void run() throws InterruptedException {
		currentState = initState;
		char currentElement;
		Instruction instruction;
		int index = 1;
		boolean validInstruction;
		
		while((!currentState.equals(acceptState)) && (!currentState.contentEquals(rejectState))) {
			validInstruction = false;
			instruction = null;			
			
			System.out.println("State: " + currentState);
			for(int i = 0; i < index; i++) {
				System.out.print(this.tape.get(i));
			}
			System.out.print("->");
			for(int j = index; j < this.tape.size(); j++) {
				System.out.print(this.tape.get(j));
			}
			System.out.println("\n");
			
			Thread.currentThread().sleep(1000);
			
			Iterator<Instruction> instructionIterator = instructions.iterator();
			
			while(instructionIterator.hasNext() && validInstruction == false) {
				instruction = instructionIterator.next();
				currentElement = this.tape.get(index);
				
				if (instruction.getReadState().equals(currentState) && instruction.getReadElement().charAt(0) == currentElement) {
					validInstruction = true;
				}
			}
			
			if (validInstruction == false) {
				System.out.println("Não foi encontrado uma instrução válida");
				currentState = rejectState;
			} else {
				String writeState = instruction.getWriteState();
				char writeElement = instruction.getWriteElement().charAt(0);
				
				currentState = writeState;
				this.tape.set(index, writeElement);				
				
				if (instruction.getDirection()) {
					index++;					
				} else {
					index--;
				}
				
				if(index == 1 && this.tape.get(0) != '_') {
					this.tape.add(0, '_');
				}
				
				if( (index == this.tape.size() || index == this.tape.size() - 2) && this.tape.get(this.tape.size()-1) != '_') {
					this.tape.add('_');
				}
			
			
				if (currentState.equals(acceptState)) {
					System.out.println("State: " + currentState);
					printTape();
					System.out.println("A entrada foi aceita");
				}
				
				if (currentState.equals(rejectState)) {
					System.out.println("State: " + currentState);
					printTape();
					System.out.println("A entrada foi rejeitada");
				}
				
			}		
		}
	}

}
