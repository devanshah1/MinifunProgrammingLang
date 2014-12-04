package generator;
/************************************************************************************************
* Author: Devan Shah
* Student ID : 100428864
* Assignment: Assignment 3
* Course: Programming Language and Compilers ENGR-3960U
* Submitted to: Dr. Jorge Landa
************************************************************************************************/
import java.io.*;
import java.text.ParseException;

import main.Parser;
import main.SimpleNode;
/**
 * This is the main method used to determine what to parse and print and start the generation of the code
 * @author Devan Shah 100428864
 *
 */
public class mFun {

	public static void main(String[] args) throws ParseException, FileNotFoundException {
		SimpleNode root = Parser.parser(args[0]); //Parsing using the jjt file with a provided test file
		//root.dump("\t");
		Parser.print_AST(root, "   "); //Printing the AST
		Generator e = new Generator(); 
		e.interpeter(root); // Interpreting the program and generating code via generator class
	}	
}
