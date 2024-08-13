    .text
    .globl main
main:
    li $s0, 1 # $s0 = 1
    
    li $v0, 5
    syscall
    move $s1, $v0
while:
    mul $s0, $s0, $s1 
    sub $s1, $s1, 1 
    bnez $s1, while
print:
    la $a0, 0($s0)
    li $v0, 1
    syscall
end:
    li $v0, 10
    syscall
