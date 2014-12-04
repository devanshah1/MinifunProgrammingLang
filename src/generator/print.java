package generator;
/************************************************************************************************
* Author: Devan Shah
* Student ID : 100428864
* Assignment: Assignment 3
* Course: Programming Language and Compilers ENGR-3960U
* Submitted to: Dr. Jorge Landa
************************************************************************************************/
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class print {
	/**
	 * This method prints the ArrayLists to the console
	 * @param headerEntries contains the header of the .asm file
	 * @param mainEntries contains the entries that will be run 
	 * @param dataEntries contains the data entries (such as the word storage)
	 */
	public static void printConsole(ArrayList<String> headerEntries, ArrayList<String> mainEntries, ArrayList<String> dataEntries){
		mainEntries.add("    li $v0, 10        # Sets $v0 to 10 to select exit syscall");
		mainEntries.add("    syscall           # Exit \n");
		
		for (int i = 0; i < headerEntries.size(); i++) {
			System.out.println(headerEntries.get(i));
		}
		for (int i = 0; i < mainEntries.size(); i++){
			
			System.out.println(mainEntries.get(i));
		}
		
		dataEntries = new ArrayList<String>(new LinkedHashSet<String>(dataEntries));
		
		for (int i = 0; i < dataEntries.size(); i++){
			System.out.println(dataEntries.get(i));
		}
	}
	
	/**
	 * This method prints the .asm file in to the GeneratedASmFile with a file name of Mfun.asm
	 * @param headerEntries contains the header of the .asm file
	 * @param mainEntries contains the entries that will be run 
	 * @param dataEntries contains the data entries (such as the word storage)
	 */
	public static void printFile(ArrayList<String> headerEntries, ArrayList<String> mainEntries, ArrayList<String> dataEntries){
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter("GeneratedASMFile/Mfun.asm"));
			// Used to loop through the userAccountInformation arrayList and outputting that to the corresponding file
			for (int i = 0; i < headerEntries.size(); i++){
				out.write(headerEntries.get(i));
				out.newLine(); // Used to output each of the entries in the arraylist onto a new line 
			}
			
			for (int i = 0; i < mainEntries.size(); i++){
				out.write(mainEntries.get(i));
				out.newLine(); // Used to output each of the entries in the arraylist onto a new line 
			}
			
			dataEntries = new ArrayList<String>(new LinkedHashSet<String>(dataEntries));
			
			for (int i = 0; i < dataEntries.size(); i++){
				out.write(dataEntries.get(i));
				out.newLine(); // Used to output each of the entries in the arraylist onto a new line 
			}
			out.close();
		} catch (IOException e) {
			System.err.println("Error:" + e.getMessage());
		}
	}
}
