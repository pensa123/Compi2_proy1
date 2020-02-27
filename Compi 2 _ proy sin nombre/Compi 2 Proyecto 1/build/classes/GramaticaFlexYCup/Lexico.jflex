package Lexico;
import java_cup.runtime.*;
import Sintactico.sym;
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
numero          =   [0-9] [0-9]*
real            =   [0-9]+\.[0-9]+
Letra           =   [a-zA-Z]
Identificador   =   [_a-zA-Z]([_a-zA-Z]|{numero})*
ComentarioLinea =   "//" ~"\n"
ComentarioMulti =   "/*" ~"*/"
%%

<YYINITIAL> {
    {numero}            { return symbol(sym.numero, yytext().toLowerCase());  }
    "="                 { return symbol(sym.igual, ":="); }
    "+"                 { return symbol(sym.mas, "+"); }
    "-"                 { return symbol(sym.menos, "-"); }
    "*"                 { return symbol(sym.por, "*"); }
    "/"                 { return symbol(sym.division, "/"); }
    ";"                 { return symbol(sym.pComa, ";"); }
    ":"                 { return symbol(sym.dosPuntos, ":"); }
    "("                 { return symbol(sym.parenI, "("); }
    ")"                 { return symbol(sym.parenD, ")"); }
    "print"          { return symbol(sym.imprimir, ")"); }
    \"                  { yybegin(STRING); NuevoString.setLength(0);}
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