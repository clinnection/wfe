grammar wf;

program
    : block EOF
    ;

block
    : stmt*
    ;

stmt
    : assignment_stmt
    | if_stmt
    | while_stmt
    ;

assignment_stmt
    : VAR_IDENTIFIER  ':=' expr                          # varAssignStmt
    | json_identifier ':=' expr                          # jsonAssignStmt
    ;

if_stmt
    : 'if' expr if_then block else_if_stmt* else_stmt? 'end' 'if' # ifStmt
    ;

else_if_stmt
    : 'else' 'if' expr if_then block                 # elseIfStmt
    ;

else_stmt
    : 'else' block                                  # elseStmt
    ;

if_then
    : 'then'                                        # ifThen
    ;

while_stmt
    : 'while' expr while_do block 'end' 'while'         # whileStmt
    ;

while_do
    : 'do'                                          # whileDo
    ;

expr
    : '-' expr                                                  # negExpr
    | '!' expr                                                  # notExpr
    | '(' expr ')'                                              # parenExpr
    | expr op=('*' | '/' | '%' ) expr                           # multExpr
    | expr op=('+' | '-' ) expr                                 # addExpr
    | expr op=('>=' | '<=' | '<' | '>' | '==' | '!=') expr      # compExpr
    | expr op=('&&' | '||' ) expr                               # logicalExpr
    | atom                                                      # atomExpr
    ;

json_identifier
    : '{{'  json_attr ('.' json_attr)*   '}}'       # jsonPath
    ;

json_attr
    : expr                                          # jsonObject
    | expr ('[' expr ']')?                          # jsonArray
    ;

atom
    : BOOLEAN                                       # boolAtom
    | INTEGER                                       # intAtom
    | DECIMAL                                       # numAtom
    | STRING                                        # strAtom
    | VAR_IDENTIFIER                                # varAtom
    | json_identifier                               # jsonAtom
    ;

BOOLEAN
    : 'true'
    | 'false'
    ;

INTEGER
    : [0-9][0-9]*
    ;

DECIMAL
    : [0-9][0-9]*  '.' [0-9][0-9]*
    ;

STRING
    : ["] ( ~["\r\n\\] | '\\' ~[\r\n] )* ["]
    ;

VAR_IDENTIFIER
    : [A-Za-z][A-Za-z0-9_]*
    ;

OR       : '||';
AND      : '&&';
EQ       : '==';
NE       : '!=';
GTE      : '>=';
LTE      : '<=';
NOT      : '!';
GT       : '>';
LT       : '<';
ADD      : '+';
SUB      : '-';
MUL      : '*';
DIV      : '/';
MOD      : '%';

SPACE
    : [ \t\n\r] -> skip
    ;
