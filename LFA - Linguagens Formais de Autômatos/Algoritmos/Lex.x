{
module Lex where

import Token
}

%wrapper "basic"

$digit = [0-9]          -- digits
@num = $digit+(\.$digit+)?

tokens :-

<0> $white+ ;
<0> @num {\s -> NUM (read s)}
<0> "+" {\s -> ADD}  
<0> "-" {\s -> SUB}  
<0> "*" {\s -> MUL}  
<0> "/" {\s -> DIV}  
<0> "(" {\s -> LPAR}  
<0> ")" {\s -> RPAR}  
<0> ">" {\s -> MORE}
<0> ">=" {\s -> MOREEQ}
<0> "<" {\s -> LESS}
<0> "<=" {\s -> LESSEQ}
<0> "!=" {\s -> DIFF}
<0> "==" {\s -> EQUAL}
<0> "&&" {\s -> AND}
<0> "||" {\s -> OR}

{
-- As acoes tem tipo :: String -> Token

testLex = do s <- getLine
             print (alexScanTokens s)
}