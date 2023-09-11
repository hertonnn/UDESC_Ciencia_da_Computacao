--Tuplas
{- 

[] :: [a]  VAZIA 

(:) :: a -> [a] -> [a] UMA CÉLULA 

TUPLA:
 (,) :: a -> b -> (a,b) construção de dois elementos *uma forma de construção 
 (,,) :: a -> b -> c -> (a,b,c) construção de trÊs elementos *uma forma de construção 

#recebe uma objt a e b e retorna (a,b)

ex.1

   (10, True) :: (Int, Bool)

ex.2

   (10,20,30) :: (Int, Int, Int)


-}
--reescreve para primeiro elemento da tupla
primeiro :: (a,b) -> a
primero (x,y) = 
    -- x :: a
    -- y :: b
    x 
-- segundo elemento
segundo :: (a,b) -> b
segundo (x,y) = 
    y
--Listas 
tamanho :: [a] -> Int
tamanho [] =
    0
tamanho (x:xs) = 
    1 + tamanho xs
    -- x é a lista e xs são os elementos
{-tamanho [1,2,3,4]
    1 + tamanho [2,3,4]
    1 + 1 tamanho [3,4]
    1 + 1 + 1 tamanho [4]
    1 + 1 + 1 + 1 tamanho []
    1 + 1 + 1 + 1 + 0 =
    4

-}
{-- a funão zipar da biblioteca padrão do haskell é capaz de unir 
duas listas em uma única lista de tuplas; por exemplo: 

  zip :: [1,2,3,4] ['a' 'b' 'c' 'd'] =
         [()]
        --}

zipar :: [a] -> [b] -> [(a,b)]
zipar [] [] = 
    []
zipar [] (y,ys) =
    -- y :: b
    -- ys :: [b]