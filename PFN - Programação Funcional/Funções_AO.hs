

filtro :: (Int -> Bool) -> [Int] -> [Int]
filtro f [] = []
filtro f (x:xs)
    | (f x) == True = x : (filtro f xs)
    | otherwise = filtro f xs

pares :: Int -> Bool
pares x = (mod x 2 == 0)

impares :: Int -> Bool 
impares x = (mod x 2 == 1)

filtro2 :: (Int -> Int) -> [Int] -> [Int]
filtro2 f [] =
    []
filtro2 f (x:xs) = 
    (f x) : ( filtro2 f xs) 


dobra :: Int -> Int
dobra x =
    x + x

buscaLista :: (Int -> Int -> Bool) -> [Int] -> Int
buscaLista _ [] = 
    -1
buscaLista _ (x:[]) =
    x
buscaLista f (x:xs) = 
    if (f x r ) then x else r
        where 
            r = buscaLista f xs

maior :: Int -> Int -> Bool
maior a b =
    if (a > b) then True else False

menor :: Int -> Int -> Bool
menor a b =
    if (a < b) then True else False




qsort :: [Int] -> [Int] 
qsort [] =
    []
qsort (x:xs) =
    qsort esquerda_x ++ [x] ++ qsort direita_x  
        where 
            esquerda_x = (filter (<x) xs)
            direita_x = (filter (>=x) xs)




