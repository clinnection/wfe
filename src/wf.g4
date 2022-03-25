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
    : 'if' expr 'then' block elseif_stmt* else_stmt? 'end' 'if' # ifStmt
    ;

elseif_stmt
    : 'else' 'if' expr 'then' block                 # elseifStmt
    ;

else_stmt
    : 'else' block                                  # elseStmt
    ;

while_stmt
    : 'while' expr 'do' block 'end' 'while'         # whileStmt
    ;

expr
    : '-' expr                                      # negExpr
    | '!' expr                                      # notExpr
    | expr op=('*' | '/' | '%' ) expr               # multExpr
    | expr op=('+' | '-' ) expr                     # addExpr
    | expr op=('>=' | '<=' | '<' | '>' ) expr       # compExpr
    | expr op=('==' | '!=' ) expr                   # eqExpr
    | expr op=('&&' | '||' ) expr                   # logicalExpr
    | func_expr                                     # funcExpr
    | json_expr                                     # jsonExpr
    | atom                                          # atomExpr
    ;

expr_list
    : expr ( ',' expr )*
    ;

func_expr
    : VAR_IDENTIFIER '(' expr_list ')'
    ;

json_identifier
    : '{{'  json_attr ('.' json_attr)*   '}}'       # jsonPath
    ;

json_expr
    : '{!'  json_attr ('.' json_attr)*   '}}'       # jsonPathMust
    | '{?'  json_attr ('.' json_attr)*   '}}'       # jsonPathCond
    | json_identifier                               # jsonPathExpr
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

SPACE
    : [ \t\n\r] -> skip
    ;