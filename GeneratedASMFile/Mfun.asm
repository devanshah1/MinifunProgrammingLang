# Devan Shah 100428864
# Generated .asm from from the AST with the use of java clases
# Declare main as a global function
    .globl main
    # All program code is placed after the
    # .text assembler directive
    .text
    # The label 'main' represents the starting point
main:
    li $t0, 0         # Load immediate value: 0
    sw $t0, x         # Store $t0 into: x
    li $v0, 1         # Sets $v0 to 1 to select exit syscall
    move $a0, $t0     # Move the rigester value in $t0 to $a0
    syscall           # Display the result in the console 

    li $v0, 4         # Sets $v0 to 4 to select exit syscall
    la $a0, newLine   # Load address value of: newLine
    syscall           # Display the result in the console 

    lw $t0, x         # Load immediate value: x
    lw $t0, x         # Load immediate value: x
    li $t0, 1         # Load immediate value: 1
    sw $t0, x         # Store $t0 into: x
    li $v0, 1         # Sets $v0 to 1 to select exit syscall
    move $a0, $t0     # Move the rigester value in $t0 to $a0
    syscall           # Display the result in the console 

    li $v0, 4         # Sets $v0 to 4 to select exit syscall
    la $a0, newLine   # Load address value of: newLine
    syscall           # Display the result in the console 

    lw $t0, x         # Load immediate value: x
    li $t0, 10        # Load immediate value: 10
    sw $t0, 4
    li $v0, 1         # Sets $v0 to 1 to select exit syscall
    move $a0, $t0     # Move the rigester value in $t0 to $a0
    syscall           # Display the result in the console 

    li $v0, 10        # Sets $v0 to 10 to select exit syscall
    syscall           # Exit 

    .data
newLine: .asciiz "\n"
x: .word 0     # Initialzing x to 0
