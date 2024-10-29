import Text.Parsec

{- Um exemplo trivial usando o Parsec e a gramática modificada vista em aula.
Olhar exemplo2.

S  ->E#
E  ->TE'
E' ->+TE'
     |-TE'
     |vazio
T  ->FT'
T' ->*FT'
     |/FT'
     |vazio
F  ->(E)
     |CONST -}

partida :: Parsec String u Integer
partida = do {e <- expr; eof; return e}


expr = do {t <- termo; e <- expr'; return (e t)}

expr' = do {char '+'; t <- termo; e <- expr'; return (e.(+t))}
        <|> do {char '-'; t <- termo; e <- expr'; return (e.(flip (-) t))}
        <|> return id

termo = do {f <- fator; t <- termo'; return (t f)}
        
termo' = do {char '*'; f <- fator; t <- termo'; return (t.(*f))}
        <|> do {char '/'; f <- fator; t <- termo'; return (t.(flip div f))}
        <|> return id

fator = do {spaces; v <- fator'; spaces; return v}

fator'    =  do {char '('; e <-expr; char ')'; return e}
            <|> do {char '-'; f <- fator; return f}
            <|> constante
            <?> "term"
       
          
constante = do {c<- many1 digit; return (read c) }

parserE e = runParser partida [] "Expressões" e

parserExpr s = case parserE s of
                     Left er -> print er
                     Right v -> print v
                     
main = do putStr "Expressão:"
          e <- getLine 
          parserExpr e
          
