import Data.Char
import System.IO
import Data.List

main :: IO ()
main = do
    -- Abre um arquivo!
    h <- openFile "teste.txt" ReadMode
    -- Pega o conteúdo do arquivo!
    conteudo <- hGetContents h
    -- Imprime o resultado!
    putStrLn (final(shorten(almalgamate(sortLs(inverte(allNumWords(numLines(lines(tirasimbolos conteudo)))))))))
    
    -- Fecha o arquivo!
    hClose h

numLines [] = []
numLines (x:xs) = zip [1..] (x:xs)

allNumWords xs = foldr (\(n,s) -> (++) (separa_palavras n (words s))) [] xs


sortLs [] = []
sortLs xs = sort xs


almalgamate [] = []
almalgamate (x:xs) = ((verifica (fst x) (x:xs)),fst x):almalgamate xs


shorten [] = []
shorten (x:xs) = x:shorten (verifica' (snd x) xs)

-- Auxiliares

separa_palavras _ [] = []
separa_palavras n (x:xs) = (n,x):separa_palavras n xs

inverte [] = []
inverte ((n,x):xs) = (x,n):inverte xs

verifica _ [] = []
verifica x ((a,b):xs) = if x == a then b:verifica x xs else verifica x xs

verifica' _ [] = []
verifica' x ((a,b):xs) = if x == b then verifica' x xs else (a,b):verifica' x xs

tirasimbolos [] = []
tirasimbolos (x:xs)
                   |isSpace x == True = x:tirasimbolos xs
                   |isLetter x == False = tirasimbolos xs
                   |otherwise = x:tirasimbolos xs

final [] = []
final ((a,b):xs) = (b ++ ": " ++ (show a)) ++ "\n" ++ final xs