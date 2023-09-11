import Data.Array -- vetores e matrizes

-- AULA 23
--cláusula where

quad :: Int -> Int
quad n = quad_n 
            where quad_n = n * n -- onde 
            

-- AULA 26

--import Data.Array
-- get_array = array (1,4) [(1, 'a'), (2, 'b') ] -- ... e por ai vai, posição e elemento da posição
-- get2_array = array ((1,1) (2,2)) [((1,1), 'A'), (1,2), 'B'] -- W uma matriz 2 x 2, linha um coluna dois e vai..

-- AULA 26 
--PILHAS

push :: [Int] -> Int -> [Int]
push pilha x = pilha ++ [x] 

 -- aqui nessa aula tem funções de retorno sem elementos e etc de uma pilha

--AULA 28 
--TIPOS ALGÉBRICOS 

-- Definição de tipos 
deriving(Show) --  instÂncia ser imprimida no terminal


--AULA 31
-- Mapas e filtros

-- map aplica algo em todos os elem de uma lista 
-- filter filtra elementos

--FUNÇÃO SORT
--  a sort ordena uma lista 
-- import data.list 