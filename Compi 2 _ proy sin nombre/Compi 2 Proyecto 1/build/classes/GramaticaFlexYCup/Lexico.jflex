package GramaticaFlexYCup;
import java_cup.runtime.*;
import GramaticaFlexYCup.sym;
%%

%class Lexer
%unicode
%cup
%line
%column
%public
%ignorecase
%state STRING
%{
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
    StringBuilder NuevoString = new StringBuilder();
    char NuevoChar;
%}

FinLinea        =   \r|\n|\r\n
InputCharacter  =   [^\r\n]
WhiteSpace      =   {FinLinea} | [ \t\f]
entero          =   [0-9] [0-9]*
numerico          =   [0-9]+\.[0-9]+
Letra           =   [a-zA-Z]
iden   =   [_a-zA-Z]([_a-zA-Z]|{numerico})*
ComentarioLinea =   "//" ~"\n"
ComentarioMulti =   "/*" ~"*/"
%%

<YYINITIAL> {
    {entero}            { return symbol(sym.entero, yytext());  }
    {numerico}            { return symbol(sym.numerico, yytext());  }
    "true"                 { return symbol(sym.True, yytext()); }
    "false"                 { return symbol(sym.False, yytext()); }
    ","                 { return symbol(sym.coma, yytext()); }
    "="                 { return symbol(sym.igual, yytext()); }
    
    "=="                 { return symbol(sym.igualigual, yytext()); }
    "!="                 { return symbol(sym.noigual, yytext()); }
    "<="                 { return symbol(sym.menorigual, yytext()); }
    ">="                 { return symbol(sym.mayorigual, yytext()); }
    "<"                 { return symbol(sym.menorque, yytext()); }
    ">"                 { return symbol(sym.mayorque, yytext()); }
    
    "|"                 { return symbol(sym.or, yytext()); }
    "&"                 { return symbol(sym.and, yytext()); }
    
    
    "+"                 { return symbol(sym.mas, yytext()); }
    "-"                 { return symbol(sym.menos, yytext()); }
    "!"                 { return symbol(sym.not, yytext()); }
    "*"                 { return symbol(sym.por, yytext()); }
    "/"                 { return symbol(sym.division, yytext()); }
    "^"                 { return symbol(sym.potencia, yytext()); }
    "%%"                 { return symbol(sym.modular, yytext()); }
    ";"                 { return symbol(sym.pComa, yytext()); }
    
    "?"                 { return symbol(sym.preg, yytext()); }
    ":"                 { return symbol(sym.dosPuntos, yytext()); }

    "["                 { return symbol(sym.cori, yytext()); }
    "]"                 { return symbol(sym.cord, yytext()); }
    
    "("                 { return symbol(sym.parenI, yytext()); }
    ")"                 { return symbol(sym.parenD, yytext()); }
    \"                  { yybegin(STRING); NuevoString.setLength(0);}
    {iden}            { return symbol(sym.iden, yytext());  }
    {ComentarioLinea}   { /* ignore */}
    {ComentarioMulti}   { /* ignore */}
    {WhiteSpace}        {}
    /* Cualquier Otro */
    .                   { 
                          System.out.println("El caracter '"+yytext()+"' no pertenece al lenguaje.");
                        }
}


<STRING> {
    \"                           { yybegin(YYINITIAL); return symbol(sym.cadena, NuevoString.toString());}
    \\\"                         { NuevoString.append('\"'); }
    \\\\                         { NuevoString.append("\\"); } 
    \\n                          { NuevoString.append('\n'); }
    \\r                          { NuevoString.append('\r'); }
    \\t                          { NuevoString.append('\t'); }
    {FinLinea}                   { yybegin(YYINITIAL);
                                   System.out.println("String sin finalizar."); }
    .                            { NuevoString.append(yytext()); }
}