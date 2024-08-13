	.text
	.globl main
main:
	li $t3, 1 # carrega o valor imediatamente
	li $t0, 1 # i = 1

	li $v0, 5 # ler inteiro
	syscall
	#move $t1, $v0

fat: 
	beq $t0, $t1, saida # se t0 == t1, pula para a saida
	addi $t0, $t0, 1 # i = i + 1
	mult $t3, $t0 # fat = i *(i+1)
		mflo $t3 # move de lo para $t3
		j fat #pula para fat
saida:
	move $a0, $t3 # $a0 = $t3
	li $v0, 1 #load imediato: $v0 recebe 1 que indica que argumento é do tipo inteiro
     syscall  # função de saida
    

