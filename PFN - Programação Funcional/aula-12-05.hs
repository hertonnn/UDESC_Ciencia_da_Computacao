primol :: Int -> Bool
primol 1 =
         True

primol n =
         verifica n (n-1)

verifica n 1 = 
    True
verifica n m = 
        if n `mod` m == 0  then
                False
        else
          verifica n (m-1)

concatena :: [a] -> [a] -> [a]
concatena xs ys = 
    [ ]

