## Ponto Flutuante IEEE 754
## Eu não me responsabilizo por essa tristeza da programação, esse lixo em 
## forma de código que é isso aqui escrito.
## - não funcionando para 1.0 (não sei nem pq), 0.01 (valores no formato n * 10^x, onde n é um inteiro qualquer e x é um valor negativo), e provavelmento para outros formatos
## - Masss ele funciona para grande parte dos doubles que vc testar, acho.

num = (float)(input("Numero: "))
var = abs(num)
binario = []

## Sinal
if(num < 0):
    binario.append("1")
else:
    binario.append("0")
binario.append("-")

div = 0

print("Etapa 1 - Transformar o número em algo como 1,### ∗ 2^###\n")
while(var > 2):
    var = var / 2
    print("{}/2 = {}".format(var*2, var))
    div += 1

print("\nlogo {} = {} * 2^{} (valor absoluto)\n".format(num, var, div))

## Parte inteira
bin_inteiro = div + 127
bin_lista = []
while(bin_inteiro != 0):
    cara = bin_inteiro % 2
    if(cara  == 1):
        bin_lista.append("1")
    else:
        bin_lista.append("0")
    bin_inteiro = bin_inteiro // 2
bin_lista.reverse()

for item in bin_lista:
    binario.append(item)

binario.append("-")
## Mantissa
print("\nEtapa 2 - Calcular a mantissa\n")
print("Multiplicações sucessivas na parte fracionária até dar 0 ou atingir 23 multiplicações.\n")
mult = 0
var = var - 1
while(mult != 23):
    var = var * 2
    print(f"{var:,.4f} * 2   = {var/2:,.4f}")
    if(var < 1):
        binario.append("0")
    else:
        var = var - 1
        binario.append("1")
    mult += 1

print("\nEtapa 3 - Montar representação Binária")
print("Expoente: {}\n".format(div))
lista = ' '.join(bin_lista)
print("- Fazer {} + 127 = {} => {}".format(div, div + 127, lista))

print("\n\nO número {} em Ponto Flutuante IEEE 754 é:\n".format(num))
binario_final = ' '.join(binario)
print(binario_final)

