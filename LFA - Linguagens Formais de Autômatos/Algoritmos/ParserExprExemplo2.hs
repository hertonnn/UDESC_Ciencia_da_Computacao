import Text.Parsec
import Text.Parsec.Expr
import qualified Text.Parsec.Token as T
import Text.Parsec.Language

lingDef = emptyDef
          { T.commentStart      = "{-"
            , T.commentEnd      = "-}"
            , T.commentLine     = "--"
            , T.reservedOpNames = ["+", "-", "/", "*"]
          }  

lexico = T.makeTokenParser lingDef

natural       = T.natural lexico
symbol        = T.symbol lexico
parens        = T.parens lexico
reservedOp    = T.reservedOp lexico

tabela   = [[prefix "-" negate]
            , [binario "*" (*) AssocLeft, binario "/" div AssocLeft ]
            , [binario "+" (+) AssocLeft, binario "-" (-)   AssocLeft ]
           ]

binario  name fun assoc = Infix (do{reservedOp name; return fun }) assoc
prefix   name fun       = Prefix (do{reservedOp name; return fun })
   
expr = buildExpressionParser tabela fator
       <?> "expression"   
        
fator = parens expr
       <|> natural
       <?> "simple expression"
            
partida :: Parsec String u Integer
partida = do {e <- expr; eof; return e}

parserE e = runParser partida [] "Expressoes" e

parserExpr s = case parserE s of
                     Left er -> print er
                     Right v -> (print v)
                     
main = do putStr "Expressão:"
          e <- getLine 
          parserExpr e
