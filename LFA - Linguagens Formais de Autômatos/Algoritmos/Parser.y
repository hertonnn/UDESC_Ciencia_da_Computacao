{
module Parser where

import Token
import qualified Lex as L

}


%name calc
%tokentype { Token }
%error { parseError }
%token 
  '+' {ADD}
  '-' {SUB}
  '*' {MUL}
  '/' {DIV}
  '(' {LPAR}
  ')' {RPAR}
  '>' {MORE}
  '>=' {MOREEQ}
  '<' {LESS}
  '<=' {LESSEQ}
  '!=' {DIFF}
  '==' {EQUAL}
  '&&' {AND}
  '||' {OR}
  Num {NUM $$}


%%

Code: Expr {$1}
     | LogicExpr {$1}

Expr: Expr '+' Term       {$1 + $3}
     | Expr '-' Term       {$1 - $3}
     | Term                {$1}

Term: Term '*' Factor     {$1 * $3}
     | Term '/' Factor     {$1 / $3}
     | Factor              {$1}

Factor: Num               {$1}
       | '(' Expr ')'     {$2}

LogicExpr: LogicExpr '>' LogicTerm {$1 > $3}
          | LogicExpr '<' LogicTerm {$1 < $3}
          | LogicExpr '>=' LogicTerm {$1 >= $3}
          | LogicExpr '<=' LogicTerm {$1 <= $3}
          | LogicExpr '!=' LogicTerm {$1 != $3}
          | LogicExpr '==' LogicTerm {$1 == $3}
          | LogicTerm {$1}

LogicTerm: LogicTerm '&&' LogicFactor {$1 && $3}
          | LogicTerm '||' LogicFactor {$1 || $3}
          | LogicFactor {$1}

LogicFactor: Num {$1}
            | '(' LogicExpr ')' {$2}



{
parseError :: [Token] -> a
parseError s = error ("Parse error:" ++ show s)

main = do putStr "Expressão:"
          s <- getLine
          print (calc (L.alexScanTokens s))
}