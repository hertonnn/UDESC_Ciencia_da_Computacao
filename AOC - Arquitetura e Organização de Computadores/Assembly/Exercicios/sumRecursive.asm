.text
.globl main

main:
    li $v0, 5         # Carrega o valor input do usuario em $v0
    syscall
    
    move $a0, $v0 # move para $a0
    
    jal sumRecursive    # Chama a função recursiva sumRecursive
    move $a0, $v0       # Move o valor de retorno ($v0) para $a0
    
    li $v0, 1           # Código de serviço para imprimir inteiro
    syscall             # Chama o sistema para imprimir $a0
    
end:
    li $v0, 10          # Código de serviço para sair
    syscall             # Chama o sistema para finalizar o programa

sumRecursive:
    beq $a0, $zero, endRecursive  # Se $a0 == 0, fim da recursão
    
    # Salvar contexto (empilha $ra e $s0)
    addi $sp, $sp, -8
    sw $s0, 0($sp)       # Salva $s0 no topo da pilha
    sw $ra, 4($sp)       # Salva $ra na pilha
    
    # Código principal da função recursiva
    move $s0, $a0        # Salva o valor de $a0 em $s0
    addi $a0, $a0, -1    # Decrementa $a0 para a chamada recursiva
    
    jal sumRecursive     # Chama sumRecursive recursivamente
    add $v0, $v0, $s0    # $v0 += $s0 (soma o resultado da recursão)
    
    # Restaurar contexto (desempilha $ra e $s0)
    lw $s0, 0($sp)       # Restaura $s0 da pilha
    lw $ra, 4($sp)       # Restaura $ra da pilha
    addi $sp, $sp, 8     # Desempilha (incrementa o $sp)
    
    # Retornar
    jr $ra               # Retorna para o endereço armazenado em $ra

endRecursive:
    li $v0, 0            # Caso base da recursão: retorna 0
    jr $ra               # Retorna para o endereço armazenado em $ra
