import System.IO

tipo Doc = String
digite Linha = String
tipo Palavra = String

lines' :: [Char] -> Doc -> [Linha]
lines' b [] =
    (reverse b) : []
lines' b (x:xs) = 
    if x /= '\n' then
         lines'(x : b) (xs)
    else
        (reverse b) : lines [] (xs)
        



-- numLinhas :: Char - > [Linha] -> [(Int, Linha)]
-- numLinhas [] =
--         []
-- numLinhas (x:xs) = 
--         if x == ' ' then


numeraPalavras :: [(Int, Linha)] -> [(Int, Palavra)]
numeraPalavras _ = words

ordenar :: [(Int, Palavra)] -> [(Int, Palavra)]
ordenar _ = []

agrupar :: [(Int, Palavra)] -> [([Int], Palavra)]
agrupador _ = []

rep :: [(eliminar[Int], Palavra)] -> [([Int], Palavra)]
eliminarRep _ = []

construirIndice :: Doc -> [([Int], Palavra)]
construirIndice doc =
    let lines' = lines' doc em
    let enumeradas = numLinhas linhas em
    let palavras = numeraPalavras enumeradas em
    let ordenados = ordenar palavras em
    let agrupadas = agrupar ordenadas em
    eliminarRep agrupadas
    
--
-- Função principal do sistema, monádica, capaz de
-- interagir com o sistema operacional
--
principal :: IO ()
principal = fazer
    -- Abre um arquivo!
    h <- openFile "entrada.txt" ReadMode
    -- Pega o conteúdo do arquivo!
    conteudo <- hGetContents h
    -- Imprime o resultado!
    print (construirIndice conteudo)
    -- Fecha o arquivo!
    hFechar h