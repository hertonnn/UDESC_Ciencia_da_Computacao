
-- FILTRAR E FUNçÕES QUE EU POSSO USAR NELA 

filtrar :: (a -> Bool) -> [a] -> [a]
filtrar x [] =
    []
filtrar a (x:xs) =
    if (a x) then x : (filtrar a xs) 
    else 
        filtrar a xs

pares :: Int -> Bool 
pares 1 =
    False
pares x =
    if (mod x 2 == 0) then
        True
    else
        False

impares :: Int -> Bool
impares 1 =
    True
impares x =
    if (mod x 2 == 1) then
        True
    else
        False

primo :: Int -> Bool
primo 1 =
        True
primo n =
        verificaPrimo n (n - 1)

verificaPrimo n 1 =
        True
verificaPrimo n m =
        if n `mod` m == 0 then
                -- n é divisível por m, e 1 < m < n
                False
        else
                verificaPrimo n (m-1)


-- MAPEAR E FUNÇÕES QUE EU POSSO USAR NELA 

mapear :: (a -> a) -> [a] -> [a]
mapear f [] =
    []
mapear f (x:xs) =
    f x : mapear f xs
  

        
