# Elixir parser

An ANTLR 4 grammar and parser for Elixir.

To generate the parser classes, do a `mvn clean install` and then do something
like this:

```java
String source = "defmodule Math do\n" +
        "  def sum(a, b) do\n" +
        "    a + b\n" +
        "  end\n" +
        "end\n" +
        "\n" +
        "IO.puts Math.sum(1, 2)\n";

ElixirLexer lexer = new ElixirLexer(CharStreams.fromString(source));
ElixirParser parser = new ElixirParser(new CommonTokenStream(lexer));

ParseTree root = parser.parse();

System.out.println(root.toStringTree(parser));
```

Or use the `stringTree(...)` method in the Main class:

```java
System.out.println(stringTree(source));
```

resulting in a nice ASCII tree of the input:

```
'- parse
   |- block
   |  |- moduleDefExpr
   |  |  '- module_def
   |  |     |- defmodule (DEFMODULE)
   |  |     |- Math (ALIAS)
   |  |     '- do_block
   |  |        |- do (DO)
   |  |        |- \n (NL)
   |  |        |- block
   |  |        |  |- functionDefExpr
   |  |        |  |  '- function_def
   |  |        |  |     |- def (DEF)
   |  |        |  |     |- variable
   |  |        |  |     |  '- sum (VARIABLE)
   |  |        |  |     |- ( (OPAR)
   |  |        |  |     |- expressions
   |  |        |  |     |  '- variablesExpr
   |  |        |  |     |     '- variables
   |  |        |  |     |        |- variable
   |  |        |  |     |        |  '- a (VARIABLE)
   |  |        |  |     |        |- , (COMMA)
   |  |        |  |     |        '- variable
   |  |        |  |     |           '- b (VARIABLE)
   |  |        |  |     |- ) (CPAR)
   |  |        |  |     '- do_block
   |  |        |  |        |- do (DO)
   |  |        |  |        |- \n (NL)
   |  |        |  |        |- block
   |  |        |  |        |  |- addExpr
   |  |        |  |        |  |  |- variablesExpr
   |  |        |  |        |  |  |  '- variables
   |  |        |  |        |  |  |     '- variable
   |  |        |  |        |  |  |        '- a (VARIABLE)
   |  |        |  |        |  |  |- addOp
   |  |        |  |        |  |  |  '- + (ADD)
   |  |        |  |        |  |  '- variablesExpr
   |  |        |  |        |  |     '- variables
   |  |        |  |        |  |        '- variable
   |  |        |  |        |  |           '- b (VARIABLE)
   |  |        |  |        |  '- eoe
   |  |        |  |        |     '- \n (NL)
   |  |        |  |        '- end (END)
   |  |        |  '- eoe
   |  |        |     '- \n (NL)
   |  |        '- end (END)
   |  |- eoe
   |  |  |- \n (NL)
   |  |  '- \n (NL)
   |  |- dotExpr
   |  |  |- aliasExpr
   |  |  |  '- IO (ALIAS)
   |  |  '- expression_tail
   |  |     |- . (DOT)
   |  |     '- functionCallExpr
   |  |        |- variablesExpr
   |  |        |  '- variables
   |  |        |     '- variable
   |  |        |        '- puts (VARIABLE)
   |  |        '- expressions
   |  |           '- dotExpr
   |  |              |- aliasExpr
   |  |              |  '- Math (ALIAS)
   |  |              '- expression_tail
   |  |                 |- . (DOT)
   |  |                 '- functionCallExpr
   |  |                    |- variablesExpr
   |  |                    |  '- variables
   |  |                    |     '- variable
   |  |                    |        '- sum (VARIABLE)
   |  |                    |- ( (OPAR)
   |  |                    |- expressions
   |  |                    |  |- integerExpr
   |  |                    |  |  '- 1 (INTEGER)
   |  |                    |  |- , (COMMA)
   |  |                    |  '- integerExpr
   |  |                    |     '- 2 (INTEGER)
   |  |                    '- ) (CPAR)
   |  '- eoe
   |     '- \n (NL)
   '- <EOF>
```