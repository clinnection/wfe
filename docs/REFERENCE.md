# Language Reference 

# Introduction
The workflow engine supports a domain specific language ("wfel", pronounced "Waffle") to enable users to examine and preform activities 
based on the contents on a JSON payload.

# Data Types
The workflow engine language supports String, Integer, Decimal and Boolean data types.
TODO: Add date types

## String
A string consists of zero or more characters enclosed in double quotes. It is an error for a line terminator to appear after the opening " and before the closing matching ". 
To use a double quote (```"```) within a string it is escaped with a back slash character (```\ ```), for example ```\"```.
To use a back slash (```\ ```) within a string it is escaped with a back slash character (```\ ```), for example ```\\ ```.


## Numeric

### Integer
The workflow engine language supports integers, from -2147483648 to 2147483647, inclusive.

### Decimal
The workflow engine language supports floating point numbers as double-precision 64-bit format IEEE 754 values and operations as specified in IEEE Standard for Binary Floating-Point Arithmetic, ANSI/IEEE Standard 754-1985

## Boolean
The boolean type represents a logical quantity with two possible values, indicated by the literals ```true``` and ```false```

# Blocks
All program statements exist within a "block". A block is one or more declarations followed by one or more statements.

## Declarations
A variable is a storage location and has an associated type. 
```
declare [name] [type]
```
Where "name" is the name of the variable, and "type" is one of:
* string
* integer
* decimal
* boolean

Variable names are case sensitive, and must start with a letter. After the first character of a variable name, the name can also contain numbers and the underscore (```_```) character. 

Variables may be declared at the beginning of a block, before any statements. The keyword "declare" is used to declare a variable. For example, the following declares a variable ```i``` as an integer:
```
declare i integer
```
All variables must be defined before they can be used.

### Scope
A variable declarations are hierarchical. Variables declared in ascendant blocks are visible from within descendant blocks. For example:
```
declare i integer

if i > 0 then
    declare x integer 
    x := i - 1
    ...
end if
```
The variable ```i``` is accessable from within the ```if``` statement block. However, the variable ```x``` goes out of scope after the ```if``` statement. 
Variables with the same name of an ancestor will hide the ancestor variable until it goes out of scope. 

```
declare i integer

i := 5

if true then
    declare i integer 
    i := 10
    ...
end if
```
The variable ```i``` will contain 5 after the ```if``` statement. 

# Statements

## if
The if statement allows conditional execution of a block.
```
if [expr] then
    [block]
end if
```
The ```if``` statement is executed by first evaluating "expr", and if it evaluates to boolean "true", then the "block" shall be executed. 
If the "expr" is evaluated to be boolean "false" then the statements following the "end if" are executed. 

### else if
```
if [expr_1] then
    [block_1]
else if [expr_2] then
    [block_2]
else if [expr_n] then
    [block_n]
end if
```
Each of the successive ```if``` statement's expressions are evaluated until the first expression that is boolean "true", then the
corresponding "block" will be executed. Execution of the ```if``` stops when either the first expression is true, or the end of the ```if``` statement. 
### else
```
if [expr] then
    [block_1]
else
    [block_2]
end if
```
The ```if``` statement is executed by first evaluating "expr", and if it evaluates to boolean "true", then the "block_1" shall be executed.
If the "expr" is evaluated to be boolean "false" then the "block_2" shall be executed.
```
if [expr_1] then
    [block_1]
else if [expr_2] then
    [block_2]
else 
    [block_3]
end if
```

Each of the successive ```if``` statement's expressions are evaluated until the first expression that is boolean "true", then the
corresponding "block" will be executed. If none of the expressions evaluate to be true, then the ```else``` block is executed.

## while
The while statement executes an expression and a block repeatedly until the value of the expression is false.
```
while expr do
    [block]
end while
```

### continue
TODO: add continue
### break
TODO: add break

# Expressions





