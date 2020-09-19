package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		File file = new File("D:\\Documentos D\\Projetos\\Turing Machine\\entrada4.txt");
		System.out.println("Reading file...\n");
		TuringMachine tm = new TuringMachine(file);
		Thread.currentThread().sleep(1000);
		tm.printStates();
		Thread.currentThread().sleep(1000);
		tm.printInstructions();
		Thread.currentThread().sleep(1000);
		System.out.println("Input:");
		tm.printTape();
		Thread.currentThread().sleep(1000);
		System.out.println("Starting...\n");
		Thread.currentThread().sleep(1000);
		tm.run();
	}
	
}
