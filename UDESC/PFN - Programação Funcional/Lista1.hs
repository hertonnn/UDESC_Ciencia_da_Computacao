module Lista1 where

import GHC.Float

ehTriangulo :: Int -> Int -> Int -> Bool
ehTriangulo a b c  | a >= b+c = False
                   | b >= a+c = False 
                   | c >= a+b = False
                   | otherwise = True

tipoTriangulo :: Int -> Int -> Int -> String 
tipoTriangulo a b c | a == b && b == c  = "equilatero"
                    | a == b || b == c || a == b = "isosceles" 
                    | otherwise = "escaleno"

triangulo :: Int -> Int -> Int -> String
triangulo a b c | ehTriangulo a b c == True = tipoTriangulo a b c 
                | otherwise = "nao eh um triangulo"


--fatorial :: Int -> Int
--fatorial n | n == 0 = 1
         --  | n > 0 = n*fatorial(n-1)


somaPares :: Int -> Int 
somaPares n | n == 0 = 0
            | n > 0 && mod n 2 == 0 = n + somaPares(n-2)
            | otherwise = n-1 + somaPares(n-3)
            
somaPot2m :: Int -> Int -> Int
somaPot2m m n | n == 0 = m
              | n > 0 = 2^(n) * m + somaPot2m m (n-1)  


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

{-
        primo 7 =
                verificaPrimo 7 6 =
                verificaPrimo 7 5 =
                verificaPrimo 7 4 =
                verificaPrimo 7 3 =
                ...
                verificaPrimo 7 1 =
                True
        
        primo 6 =
                verificaPrimo 6 5 =
                verificaPrimo 6 4 =
                verificaPrimo 6 3 =
                False
-}


seriePI :: Int -> Double 
seriePI 1 = 4
seriePI n = 
        
        if n `mod` 2 == 0 then 
              seriePI (n-1)
       
        else
           
            if (floor((fromIntegral n)/2)) `mod` 2 == 0 then
              
              4/fromIntegral(n) + (seriePI (n-2))
        else 
             -4/fromIntegral(n) + (seriePI (n-2))


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
             


                     

   


        
        
          
          
          







                    


                   
                   
     
    
                       
   
