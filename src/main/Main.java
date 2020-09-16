package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		File file = new File("D:\\Documentos D\\Projetos\\Turing Machine\\entrada2.txt");
		TuringMachine tm = new TuringMachine(file);
//		tm.printTape();
//		System.out.println();
//		tm.printStates();
//		System.out.println();
//		tm.printInstructions();
		tm.run();

	}

}
