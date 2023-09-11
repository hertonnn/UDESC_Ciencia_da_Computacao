--
-- Para concatenar duas listas, percorremos e reconstruímos
-- uma delas, substituindo seu final, uma lista vazia, pela
-- outra, criando uma nova lista como resultado!
--
concatena :: [a] -> [a] -> [a]
concatena [] ys = -- (1)
    ys
concatena (x:xs) ys = -- (2)
    x : concatena xs ys

{-
    concatena [1, 2, 3] [7, 8] =
        1 : concatena [2, 3] [7, 8] =
        1 : 2 : concatena [3] [7, 8] =
        1 : 2 : 3 : concatena [] [7, 8] =
        1 : 2 : 3 : [7, 8] =
        [1, 2, 3, 7, 8]
-}

--
-- Dado um elemento, queremos percorrer uma lista e decidir se
-- tal elemento existe ou não dentro dessa lista.
--
-- Vamos definir a função de forma recursiva no segundo elemento.
--
pertence :: Eq a => a -> [a] -> Bool
pertence x [] = -- (1)
    False
pertence x (y:ys) = -- (2)
    -- x :: a
    -- y :: a
    -- ys :: [a]
    -- if x == y then
    --     True
    -- else
    --     pertence x ys
    x == y || pertence x ys

{-
    pertence 3 [1, 2, 3, 4, 5] =
        3 == 1 || pertence 3 [2, 3, 4, 5] =
        False || pertence 3 [2, 3, 4, 5] =
        False || 3 == 2 || pertence 3 [3, 4, 5] =
        False || False || pertence 3 [3, 4, 5] =
        False || False || 3 == 3 || pertence 3 [4, 5] =
        False || False || True || pertence 3 [4, 5] =
        False || False || True =
        False || True =
        True
-}

--
-- Vamos receber duas listas de a, tal que podemos comparar elementos
-- de a. Queremos ter a interseção de duas listas: isto é, uma lista
-- com os elementos que pertencem a ambas!
--
-- Pooor exemplo:
--   intersecao [1, 3, 5, 7] [3, 4, 5, 6, 7, 8, 9] = [3, 5, 7]
--
intersecao :: Eq a => [a] -> [a] -> [a]
intersecao [] ys = -- (1)
    []
intersecao (x:xs) ys = -- (2)
    -- x :: a
    -- xs :: [a]
    -- ys :: [a]
    if x `pertence` ys then
        x : intersecao xs ys
    else
        intersecao xs ys

{-
    intersecao [1, 3, 5] [2, 3, 4] =

        if False then
            1 : intersecao [3, 5] [2, 3, 4]
        else
            intersecao [3, 5] [2, 3, 4] =

        intersecao [3, 5] [2, 3, 4] =

        if 3 `pertence` [2, 3, 4] then
            3 : intersecao [5] [2, 3, 4]
        else
            intersecao [5] [2, 3, 4] =

        if True then
            3 : intersecao [5] [2, 3, 4]
        else
            intersecao [5] [2, 3, 4] =
        
        3 : intersecao [5] [2, 3, 4] =

        3 : if 5 `pertence` [2, 3, 4] then
                5 : intersecao [] [2, 3, 4]
            else
                intersecao [] [2, 3, 4] =
        
        3 : if False then
                5 : intersecao [] [2, 3, 4]
            else
                intersecao [] [2, 3, 4] =

        3 : intersecao [] [2, 3, 4] =

        3 : [] =

        [3]
-}

--
-- Vamos fazer uma função que recebe uma lista e retorna
-- o seu inverso, trocando a ordem dos elementos!
--
-- Por exemplo,
--
--   inverso [0, 1, 1, 2, 3, 5, 8] =
--     [8, 5, 3, 2, 1, 1, 0]
--
inverso :: [a] -> [a]
inverso [] = -- (1)
    []
inverso (x:xs) = -- (2)
    concatena (inverso xs) [x]

{-
    inverso [1, 2, 3] =
        concatena (inverso [2, 3]) [1] =
        concatena (concatena (inverso [3]) [2]) [1] =
        concatena (concatena (concatena (inverso []) [3]) [2]) [1] =
        concatena (concatena (concatena [] [3]) [2]) [1] =
        concatena (concatena [3] [2]) [1] =
        concatena (3 : concatena [] [2]) [1] =
        3 : concatena (concatena [] [2]) [1] =
        3 : concatena [2] [1] =
        3 : 2 : concatena [] [1] =
        3 : 2 : [1] =
        [3, 2, 1]
-}