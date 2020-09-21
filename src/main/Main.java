package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		int DELAY = 500;
		File file = new File("D:\\Documentos D\\Projetos\\Turing Machine\\entrada4.txt");
		System.out.println("Reading file...\n");
		TuringMachine tm = new TuringMachine(file);
		Thread.currentThread().sleep(DELAY);
		tm.printStates();
		Thread.currentThread().sleep(DELAY);
		tm.printInstructions();
		Thread.currentThread().sleep(DELAY);
		System.out.println("Input:");
		tm.printTape();
		Thread.currentThread().sleep(DELAY);
		System.out.println("Starting...\n");
		Thread.currentThread().sleep(DELAY);
		tm.run();
	}
	
}
