/* Generated By:JavaCC: Do not edit this line. Gramatica.java */
/** Analizador de expresiones aritmeticas sencillas. */
package GramaticaJavaCC;

//clases de java :D
import java.util.ArrayList;
//clases mias de mi :D

import FuncionesDelLenguaje.If;
import FuncionesDelLenguaje.Else;

import objetos.Instrucciones_cuerpo;

import ClasesAuxiliares.contenedorEnum.Tipos;

import ClasesAuxiliares.contenedorEnum.Tipos;

import ClasesAuxiliares.Nodo;
import FuncionesDelLenguaje.Print;

import Comparadores.Ternario;

import objetos.Iden;
import objetos.Asignacion;


import objetos.Acceso;
import objetos.AccesoMatriz;
import objetos.Var_acceso;

import objetos.Primitivo;

import objetos.OperadorBinario;
import objetos.OperadorBinario.Operando;

import objetos.OperadorUnario;
import objetos.OperadorUnario.Op;


public class Gramatica implements GramaticaConstants {

  public ArrayList arr = new ArrayList<Nodo>();

/** Fin Lexico */


/** Producción inicial 
    Analizar -> (Instruccion)+ EOF
*/
  final public void Analizar() throws ParseException {
  Nodo n;
    label_1:
    while (true) {
      n = InstruccionGlobal();
                              arr.add(n);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IMPRIMIR:
      case SI:
      case IDENTIFICADOR:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
    }
    jj_consume_token(0);

  }

  final public Nodo instrucciones_cuerpos() throws ParseException {
  ArrayList<Nodo> arr = new ArrayList<Nodo>(); Nodo n ;
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IMPRIMIR:
      case SI:
      case IDENTIFICADOR:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      n = InstruccionCuerpo();
                               arr.add(n);
    }
    {if (true) return new Instrucciones_cuerpo(0 , 0 , arr);}
    throw new Error("Missing return statement in function");
  }

  final public Nodo InstruccionGlobal() throws ParseException {
    Nodo n;
    n = InstruccionCuerpo();
       {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo InstruccionCuerpo() throws ParseException {
    Nodo n;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SI:
      n = ciclos_if();
      break;
    case IMPRIMIR:
      n = Imprimir();
      break;
    case IDENTIFICADOR:
      n = Asignacion_llamadaMetodo();
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
        {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo ciclos_if() throws ParseException {
    Nodo n ;
    n = si();
      {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo si() throws ParseException {
    Nodo n; Token t;
    ArrayList<Nodo> arr = new ArrayList<Nodo>();
    t = jj_consume_token(SI);
    jj_consume_token(PARENI);
    n = Expresion();
                                          arr.add(n);
    jj_consume_token(PAREND);
    jj_consume_token(LLAVEI);
    n = instrucciones_cuerpos();
                                                                                                         arr.add(n);
    jj_consume_token(LLAVED);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SINO:
      n = sino_si();
                                    arr.add(n);
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
      {if (true) return new If(t.beginLine , t.beginColumn ,arr );}
    throw new Error("Missing return statement in function");
  }

  final public Nodo sino_si() throws ParseException {
    Token t;
    Nodo n , n2 ;
    t = jj_consume_token(SINO);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SI:
      n = si();
      break;
    case LLAVEI:
      jj_consume_token(LLAVEI);
      n = instrucciones_cuerpos();
                                               n = new Else(t.beginLine, t.beginColumn , n);
      jj_consume_token(LLAVED);
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
      {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo Asignacion_llamadaMetodo() throws ParseException {
    Nodo n1 , n2; Token t;
    jj_consume_token(IDENTIFICADOR);
                        n1 = new Iden(token.beginLine, token.beginColumn , token.image);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CORI:
    case IGUAL:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CORI:
        jj_consume_token(CORI);
        n1 = Acceso_vec_mat(n1);
        break;
      default:
        jj_la1[5] = jj_gen;
        ;
      }
      t = jj_consume_token(IGUAL);
      n2 = Expresion();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PCOMA:
        jj_consume_token(PCOMA);
        break;
      default:
        jj_la1[6] = jj_gen;
        ;
      }
          {if (true) return new Asignacion(t.beginLine, t.beginColumn , n1 , n2);}
      break;
    case PARENI:
      params_metodo();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PCOMA:
        jj_consume_token(PCOMA);
        break;
      default:
        jj_la1[7] = jj_gen;
        ;
      }
          {if (true) return null;}
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Nodo Imprimir() throws ParseException {
    Nodo n;
    jj_consume_token(IMPRIMIR);
    jj_consume_token(PARENI);
    n = Expresion();
    jj_consume_token(PAREND);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PCOMA:
      jj_consume_token(PCOMA);
      break;
    default:
      jj_la1[9] = jj_gen;
      ;
    }
                                                               {if (true) return new Print(token.beginLine, token.beginColumn , n);}
    throw new Error("Missing return statement in function");
  }

  final public Nodo Expresion() throws ParseException {
    Nodo n , n2 , n3; Token t;
    ArrayList<Nodo> arr = new ArrayList<Nodo>();
    n = CondicionOR();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PREG:
      t = jj_consume_token(PREG);
      n2 = Expresion();
      jj_consume_token(DOSPUNTOS);
      n3 = Expresion();
             arr.add(n); arr.add(n2); arr.add(n3); n = new Ternario(t.beginLine , t.beginColumn , arr);
      break;
    default:
      jj_la1[10] = jj_gen;
      ;
    }
        {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo CondicionOR() throws ParseException {
    Nodo n , n2;
    n = CondicionAnd();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_3;
      }
      jj_consume_token(OR);
      n2 = CondicionAnd();
                                   n = new OperadorBinario(1 , 1, n , n2, Operando.or);
    }
      {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo CondicionAnd() throws ParseException {
    Nodo n , n2;
    n = ExpresionIgualdad();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_4;
      }
      jj_consume_token(AND);
      n2 = ExpresionIgualdad();
                                           n = new OperadorBinario(1 , 1, n , n2, Operando.and);
    }
        {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo ExpresionIgualdad() throws ParseException {
    Nodo n , n2;
    n = ExpresionRelacional();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IGUALACION:
      case DIFERENCIACION:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_5;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IGUALACION:
        jj_consume_token(IGUALACION);
        n2 = ExpresionRelacional();
                                                   n = new OperadorBinario(1 , 1, n , n2, Operando.comparacion);
        break;
      case DIFERENCIACION:
        jj_consume_token(DIFERENCIACION);
        n2 = ExpresionRelacional();
                                                        n = new OperadorBinario(1 , 1, n , n2, Operando.desigualdad);
        break;
      default:
        jj_la1[14] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
        {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo ExpresionRelacional() throws ParseException {
    Nodo n , n2;
    n = ExpresionAditiva();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MENORQUE:
      case MAYORQUE:
      case MENORIGUAL:
      case MAYORIGUAL:
        ;
        break;
      default:
        jj_la1[15] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MAYORQUE:
        jj_consume_token(MAYORQUE);
        n2 = ExpresionAditiva();
                                               n = new OperadorBinario(1 , 1, n , n2, Operando.mayorque);
        break;
      case MENORQUE:
        jj_consume_token(MENORQUE);
        n2 = ExpresionAditiva();
                                               n = new OperadorBinario(1 , 1, n , n2, Operando.menorque);
        break;
      case MAYORIGUAL:
        jj_consume_token(MAYORIGUAL);
        n2 = ExpresionAditiva();
                                                 n = new OperadorBinario(1 , 1, n , n2, Operando.mayorigual);
        break;
      case MENORIGUAL:
        jj_consume_token(MENORIGUAL);
        n2 = ExpresionAditiva();
                                                 n = new OperadorBinario(1 , 1, n , n2, Operando.menorigual);
        break;
      default:
        jj_la1[16] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
        {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo ExpresionAditiva() throws ParseException {
    Nodo n , n2;
    n = ExpresionMultiplicativa();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MAS:
      case MENOS:
        ;
        break;
      default:
        jj_la1[17] = jj_gen;
        break label_7;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MAS:
        jj_consume_token(MAS);
        n2 = ExpresionMultiplicativa();
                                                 n = new OperadorBinario(1 , 1, n , n2, Operando.mas);
        break;
      case MENOS:
        jj_consume_token(MENOS);
        n2 = ExpresionMultiplicativa();
                                                  n = new OperadorBinario(1 , 1, n , n2, Operando.menos);
        break;
      default:
        jj_la1[18] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
        {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo ExpresionMultiplicativa() throws ParseException {
    Nodo n , n2;
    n = ExpresionUnaria();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case POR:
      case DIV:
        ;
        break;
      default:
        jj_la1[19] = jj_gen;
        break label_8;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case POR:
        jj_consume_token(POR);
        n2 = ExpresionUnaria();
                                        n = new OperadorBinario(1 , 1, n , n2, Operando.por);
        break;
      case DIV:
        jj_consume_token(DIV);
        n2 = ExpresionUnaria();
                                         n = new OperadorBinario(1 , 1, n , n2, Operando.div);
        break;
      default:
        jj_la1[20] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
        {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo ExpresionUnaria() throws ParseException {
    Nodo n ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MENOS:
      jj_consume_token(MENOS);
      n = ExpresionUnaria();
                                     n = new OperadorUnario(1 , 1 , n, Op.neg);
      break;
    case NOT:
      jj_consume_token(NOT);
      n = ExpresionUnaria();
                                    n = new OperadorUnario(1 , 1 , n, Op.not);
      break;
    case NUMERO:
    case DECIMAL:
    case TRUE:
    case FALSE:
    case PARENI:
    case IDENTIFICADOR:
    case STRING:
      n = Primitivo();

      break;
    default:
      jj_la1[21] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
      {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo Primitivo() throws ParseException {
    Nodo n; Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUMERO:
      jj_consume_token(NUMERO);
                 n = new Primitivo( token.beginLine, token.beginColumn , Tipos.entero , Double.parseDouble(token.image)  );
      break;
    case DECIMAL:
      jj_consume_token(DECIMAL);
                 n = new Primitivo( token.beginLine, token.beginColumn , Tipos.numerico , Double.parseDouble(token.image)  );
      break;
    case STRING:
      t = jj_consume_token(STRING);
                    n = new Primitivo( token.beginLine, token.beginColumn , Tipos.cadena , t.image.substring(1,t.image.length()-1)  );
      break;
    case TRUE:
      jj_consume_token(TRUE);
                 n = new Primitivo( token.beginLine, token.beginColumn , Tipos.booleano , true  );
      break;
    case FALSE:
      jj_consume_token(FALSE);
                n = new Primitivo( token.beginLine, token.beginColumn , Tipos.booleano , false  );
      break;
    case IDENTIFICADOR:
      jj_consume_token(IDENTIFICADOR);
                        n = new Iden(token.beginLine, token.beginColumn , token.image);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PARENI:
      case CORI:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case CORI:
          jj_consume_token(CORI);
          n = Acceso_vec_mat(n);
          break;
        case PARENI:
          params_metodo();
          break;
        default:
          jj_la1[22] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[23] = jj_gen;
        ;
      }
      break;
    case PARENI:
      jj_consume_token(PARENI);
      n = Expresion();
      jj_consume_token(PAREND);

      break;
    default:
      jj_la1[24] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
      {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public void params_metodo() throws ParseException {
    jj_consume_token(PARENI);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUMERO:
    case DECIMAL:
    case TRUE:
    case FALSE:
    case PARENI:
    case MENOS:
    case NOT:
    case IDENTIFICADOR:
    case STRING:
      Expresion();
      label_9:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMA:
          ;
          break;
        default:
          jj_la1[25] = jj_gen;
          break label_9;
        }
        jj_consume_token(COMA);
        Expresion();
      }
      break;
    default:
      jj_la1[26] = jj_gen;
      ;
    }
    jj_consume_token(PAREND);
  }

  // E , E    |   E ,   |  , E 
  final public Nodo Acceso_vec_mat(Nodo n) throws ParseException {
    Nodo  n1 , n2; Token t;
    ArrayList<Nodo> arr = new ArrayList<Nodo>();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUMERO:
    case DECIMAL:
    case TRUE:
    case FALSE:
    case PARENI:
    case MENOS:
    case NOT:
    case IDENTIFICADOR:
    case STRING:
      n1 = Expresion();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CORD:
        jj_consume_token(CORD);
                                  arr.add(n); arr.add(new Acceso(n1.fila , n1.columna , n1 , false ) );
        label_10:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case CORI:
            ;
            break;
          default:
            jj_la1[27] = jj_gen;
            break label_10;
          }
          n1 = Acceso_lista();
                                                                                                                                     arr.add(n1);
        }
       {if (true) return new Var_acceso(n.fila, n.columna , arr);}
        break;
      case COMA:
        t = jj_consume_token(COMA);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case NUMERO:
        case DECIMAL:
        case TRUE:
        case FALSE:
        case PARENI:
        case MENOS:
        case NOT:
        case IDENTIFICADOR:
        case STRING:
          n2 = Expresion();
          jj_consume_token(CORD);
                                                     n1 =  new AccesoMatriz(t.beginLine, t.beginColumn , n1 , n2 );
                                                    {if (true) return new Var_acceso(n.fila, n.columna , n , n1);}
          break;
        case CORD:
          jj_consume_token(CORD);
                                       n1 =  new AccesoMatriz(t.beginLine, t.beginColumn , n1 , true );
                                        {if (true) return new Var_acceso(n.fila, n.columna , n , n1);}
          break;
        default:
          jj_la1[28] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[29] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    case COMA:
      t = jj_consume_token(COMA);
      n1 = Expresion();
      jj_consume_token(CORD);
                                   n1 =  new AccesoMatriz(t.beginLine, t.beginColumn , n1 , false );
                                    {if (true) return new Var_acceso(n.fila, n.columna , n , n1);}
      break;
    case CORI:
      jj_consume_token(CORI);
      n1 = Expresion();
                              arr.add(n);  arr.add(new Acceso(n1.fila , n1.columna , n1 , true ) );
      jj_consume_token(CORD);
      jj_consume_token(CORD);
      label_11:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case CORI:
          ;
          break;
        default:
          jj_la1[30] = jj_gen;
          break label_11;
        }
        n1 = Acceso_lista();
                                                                                                                                          arr.add(n1);
      }
      {if (true) return new Var_acceso(n.fila, n.columna , arr);}
      break;
    default:
      jj_la1[31] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
      {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public Nodo Acceso_lista() throws ParseException {
    Nodo n1;
    jj_consume_token(CORI);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case NUMERO:
    case DECIMAL:
    case TRUE:
    case FALSE:
    case PARENI:
    case MENOS:
    case NOT:
    case IDENTIFICADOR:
    case STRING:
      n1 = Expresion();
      jj_consume_token(CORD);
                                     n1 =  new Acceso(n1.fila , n1.columna , n1 , false );
      break;
    case CORI:
      jj_consume_token(CORI);
      n1 = Expresion();
      jj_consume_token(CORD);
      jj_consume_token(CORD);
                                                 n1 = new Acceso(n1.fila , n1.columna , n1 , true );
      break;
    default:
      jj_la1[32] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
      {if (true) return n1;}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public GramaticaTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[33];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x82000,0x82000,0x82000,0x100000,0x8080000,0x2000000,0x200000,0x200000,0x2800000,0x200000,0x40000000,0x0,0x0,0x0,0x0,0x0,0x0,0xa0000000,0xa0000000,0x0,0x0,0x80801980,0x2800000,0x2800000,0x801980,0x0,0x80801980,0x2000000,0x84801980,0x4000000,0x2000000,0x82801980,0x82801980,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x2000,0x2000,0x2000,0x0,0x0,0x0,0x0,0x0,0x4,0x0,0x0,0x800,0x400,0x180,0x180,0x78,0x78,0x0,0x0,0x3,0x3,0x13000,0x0,0x0,0x12000,0x200,0x13000,0x0,0x13000,0x200,0x0,0x13200,0x13000,};
   }

  /** Constructor with InputStream. */
  public Gramatica(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Gramatica(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new GramaticaTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 33; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 33; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Gramatica(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new GramaticaTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 33; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 33; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Gramatica(GramaticaTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 33; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(GramaticaTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 33; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List jj_expentries = new java.util.ArrayList();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[49];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 33; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 49; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
