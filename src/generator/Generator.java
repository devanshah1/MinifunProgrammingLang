/************************************************************************************************
* Author: Devan Shah
* Student ID : 100428864
* Assignment: Assignment 3
* Course: Programming Language and Compilers ENGR-3960U
* Submitted to: Dr. Jorge Landa
************************************************************************************************/
package generator;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import main.Node;
import main.SimpleNode;
import generator.print;

public class Generator {
	int numberofLoads = 0;
	Boolean condBlock = false;
	
	HashMap<String, Object> variableTable = new HashMap<String, Object>();
	
	ArrayList<String> headerEntries = new ArrayList<String>();
	ArrayList<String> dataEntries = new ArrayList<String>();
	ArrayList<String> mainEntries = new ArrayList<String>();

	public Generator(){
		// Adding generic entries into the headerEntries ArrayList
		headerEntries.add("# Devan Shah 100428864");
		headerEntries.add("# Generated .asm from from the AST with the use of java clases");
		headerEntries.add("# Declare main as a global function");
		headerEntries.add("    .globl main");
		headerEntries.add("    # All program code is placed after the");
		headerEntries.add("    # .text assembler directive");
		headerEntries.add("    .text");
		headerEntries.add("    # The label 'main' represents the starting point");
		// Adding generic entries into the dataEntries ArrayList
		dataEntries.add("    .data");
		dataEntries.add("newLine: .asciiz \"\\n\"");
		// Adding generic entries into the mainEntries ArrayList
		mainEntries.add("main:");
	}

	public void interpeter(SimpleNode rootNode) throws FileNotFoundException, ParseException {

		for (int i = 0; i < rootNode.jjtGetNumChildren(); i++) {
			evaluateMain(rootNode.jjtGetChild(i));
		}
		
		if (((SimpleNode)rootNode.jjtGetChild(0)).jjtGetValue().toString().equals("+")){
			mainEntries.add("    li $v0, 1" + "         # Sets $v0 to 1 to select exit syscall");
			mainEntries.add("    move $a0, $t0     # Move the rigester value in $t0 to $a0");
			mainEntries.add("    syscall           # Display the result in the console \n");
		}
		
		print.printConsole(headerEntries, mainEntries, dataEntries);
		print.printFile(headerEntries, mainEntries, dataEntries);
	}

	public void evaluateMain(Node rootNodeChild) {// Beginning the evaluation at the reference node

		if (rootNodeChild instanceof SimpleNode) {
			SimpleNode castingChildNodeToSimpleNode = (SimpleNode) rootNodeChild;// jjtGetChild returns a Node and must be casted to SimpleNode
			try{
				String valueofChildNode = castingChildNodeToSimpleNode.jjtGetValue().toString();

				if (valueofChildNode.equals("define")) {
					SimpleNode firstChild = (SimpleNode) castingChildNodeToSimpleNode.jjtGetChild(0);
					SimpleNode secondChild = (SimpleNode) castingChildNodeToSimpleNode.jjtGetChild(1);
					evalDefine(firstChild, secondChild);
				}

				else
					System.out.println(evaluateExpression(castingChildNodeToSimpleNode).toString());
			}catch(Exception e){
				evaluateExpression((SimpleNode)castingChildNodeToSimpleNode.jjtGetChild(0));
			}
		}
	}
	
	//Evaluating expression 
	public Object evaluateExpression(SimpleNode rootNodeChild) { 
		
		String valueofRootNodeChild = rootNodeChild.jjtGetValue().toString();
		
		if (valueofRootNodeChild.equals("cond")) {// Evaluate condition by evaluating its children
			for (int c = 0; c < rootNodeChild.jjtGetNumChildren(); c += 2) {
				if (Integer.parseInt(evaluateExpression((SimpleNode) rootNodeChild.jjtGetChild(c)).toString()) == 1) {
					evaluateExpression((SimpleNode) rootNodeChild.jjtGetChild(c+1));
					mainEntries.add("    li $t0, " + evaluateExpression((SimpleNode) rootNodeChild.jjtGetChild(c+1)) + "        # Load immediate value: " + evaluateExpression((SimpleNode) rootNodeChild.jjtGetChild(c+1)));
					mainEntries.add("    li $v0, 1" + "         # Sets $v0 to 1 to select exit syscall");
					mainEntries.add("    move $a0, $t0     # Move the rigester value in $t0 to $a0");
					mainEntries.add("    syscall           # Display the result in the console \n");
					break;
				}
				
				else if (Integer.parseInt(evaluateExpression((SimpleNode) rootNodeChild.jjtGetChild(c)).toString()) == 0) {
					condBlock = true;
				}
			}
		}
		
		if (valueofRootNodeChild.equals("else")){
			if(condBlock){
				mainEntries.add("    li $t0, " + evaluateExpression((SimpleNode) rootNodeChild.jjtGetChild(0)) + "        # Load immediate value: " + evaluateExpression((SimpleNode) rootNodeChild.jjtGetChild(0)));
				mainEntries.add("    li $v0, 1" + "         # Sets $v0 to 1 to select exit syscall");
				mainEntries.add("    move $a0, $t0     # Move the rigester value in $t0 to $a0");
				mainEntries.add("    syscall           # Display the result in the console \n");
				condBlock = false;
			}
		}
		
		if (rootNodeChild.toString().equals("variable") || rootNodeChild.toString().equals("expression")){
			
			// Retrieving the variables value from the symbol table
			if (variableTable.containsKey(rootNodeChild.jjtGetValue().toString())){
				
				mainEntries.add("    lw $t0" + ", " + rootNodeChild.jjtGetValue().toString() + "         # Load immediate value: " + rootNodeChild.jjtGetValue().toString());
				return variableTable.get(rootNodeChild.jjtGetValue().toString());
			}
		}
		
		if (valueofRootNodeChild.equals("+")){
			int addation = 0;
			for (int e = 0; e < rootNodeChild.jjtGetNumChildren(); e++){
			   addation += Integer.parseInt( evaluateExpression((SimpleNode)rootNodeChild.jjtGetChild(e)).toString());   
			}
			return addation;
		}
		
		if(((SimpleNode) rootNodeChild.jjtGetParent()).jjtGetValue() != null){
			if( ((SimpleNode) rootNodeChild.jjtGetParent()).jjtGetValue().toString().equals("+")){
				
				mainEntries.add("    li $t" + numberofLoads + ", " + rootNodeChild.jjtGetValue() + "         # Load immediate value: " + rootNodeChild.jjtGetValue());
				numberofLoads++;
			
				if (numberofLoads > 1){
					mainEntries.add("    add $t0, $t0, $t" + (numberofLoads-1) + " # ADD $t" + (numberofLoads-1) + " to $t0" );
				}
			}	
		}
			
		return rootNodeChild.jjtGetValue();
	}
	
	// Generating the MIPS code for define
	private void evalDefine(SimpleNode leftC, SimpleNode rightC) {
		
		// Storing variable value in the symbol table with the variable name as the hash key 
		if (leftC.toString().equals("variable")) {
			variableTable.put(leftC.jjtGetValue().toString(), evaluateExpression(rightC));
		}
		if(!rightC.jjtGetValue().toString().equals("+")){
			mainEntries.add("    li $t0" + ", " + evaluateExpression(rightC) + "         # Load immediate value: " + evaluateExpression(rightC));
			numberofLoads++;
		}
		// Storing the print to console in QtSpim 	
		mainEntries.add("    sw $t0, " + leftC.jjtGetValue().toString() + "         # Store $t0 into: " + leftC.jjtGetValue().toString());
		mainEntries.add("    li $v0, 1" + "         # Sets $v0 to 1 to select exit syscall");
		mainEntries.add("    move $a0, $t0     # Move the rigester value in $t0 to $a0");
		mainEntries.add("    syscall           # Display the result in the console \n");
		mainEntries.add("    li $v0, 4         # Sets $v0 to 4 to select exit syscall");
		mainEntries.add("    la $a0, newLine   # Load address value of: newLine");
		mainEntries.add("    syscall           # Display the result in the console \n");
		dataEntries.add(leftC.jjtGetValue().toString() + ": .word " + "0" + "     # Initialzing " + leftC.jjtGetValue().toString() + " to 0" );
	}
}
