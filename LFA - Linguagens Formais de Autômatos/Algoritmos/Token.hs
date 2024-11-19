module Token where

data Token
  = NUM Double
  | ADD
  | SUB
  | MUL
  | DIV
  | LPAR
  | RPAR
  | MORE
  | MOREEQ
  | LESS
  | LESSEQ
  | EQUAL
  | AND
  | OR
  | DIFF
  deriving (Eq, Show)
  
