package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		File file = new File("D:\\Documentos D\\Projetos\\Turing Machine\\entrada2.txt");
		TuringMachine tm = new TuringMachine(file);
		tm.printStates();
		tm.printInstructions();
		System.out.println("Input:");
		tm.printTape();
		System.out.println("Starting...\n");
		Thread.currentThread().sleep(1000);
		tm.run();

	}

}
