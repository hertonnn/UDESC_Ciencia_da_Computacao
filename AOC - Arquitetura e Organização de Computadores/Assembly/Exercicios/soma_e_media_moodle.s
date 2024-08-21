.data
    spacer: .asciiz "\n"
    error: .asciiz "ERRO"
.text
.globl main

main:
	move $t2, $zero # iterator i = 0
	move $t1, $zero # var que terá a soma

while:
	li $v0, 5 # leitura de inteiros 
    	syscall  
	
	move $t0, $v0 # valor fornecido esta em $t0 
	
	beq $t0, -1, med
	add $t1, $t1, $t0
	addi $t2, $t2, 1
	j while	
med: 
	div $t1, $t2
	mflo $t0
saida:
	li $v0, 1
	move $a0, $t1
	syscall
	
	li $v0, 4 # imprimir uma string 
	la $a0, spacer
	syscall
	
	beq $t0, 0, err
	
	li $v0, 1
	move $a0, $t0
	syscall
	
	li $v0,10
    	syscall
err:
    	li $v0, 4 # imprimir uma string 
	la $a0, error
	syscall
	
	li $v0,10
    	syscall
