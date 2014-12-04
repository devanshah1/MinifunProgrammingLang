/* Generated By:JJTree&JavaCC: Do not edit this line. Parser.java */
package main;

import java.io.*;
import java.util.*;

public class Parser/*@bgen(jjtree)*/implements ParserTreeConstants, ParserConstants {/*@bgen(jjtree)*/
  protected static JJTParserState jjtree = new JJTParserState();/* main function that is used to start the parsing of the inputed file and also deliver   * any error messages that many have occurred during the process.   */
  public static SimpleNode parser(String filename)
  {
    SimpleNode start = null;

    if (filename.length() < 1)
    {
      System.out.println("Please pass in the filename for a parameter.");
    }

    try
    {
                try
                {
                        Parser parser = new Parser(new FileInputStream(filename));
                        start = parser.start();
                }

                catch (FileNotFoundException err)
        {
                System.err.println("-2: Entered file name is not corrent, check name and re-enter");
                System.err.println("You entered the file name as: \u005cn" + err.getMessage());
                System.exit(-1);
        }
     }

     catch (ParseException e)
     {
      System.err.println("-1: The input file is not lexically/syntactically valid Minifun");
      System.err.println("The Error occured because of: \u005cn" + e.getMessage());
      System.exit(-1);
     }

    return start;

  }
  public static void print_AST(SimpleNode root, String prefix)
  {
    if (root != null)
    {
      if (root.value != null) System.out.println(prefix + root.value);
      for (int i = 0; i < root.jjtGetNumChildren(); ++i)
      {
        SimpleNode n = (SimpleNode) root.jjtGetChild(i);
        if (n != null)
        {
          print_AST(n, prefix + "   ");
        }
      }
    }
  }

/* -------------------------------------------------------------------------------------- * <S>     ::= <pro> <EOF> * -- > the start() function represents the starting point of parsing ----------------------------------------------------------------------------------------*/
  static final public SimpleNode start() throws ParseException {
 /*@bgen(jjtree) root */
  SimpleNode jjtn000 = new SimpleNode(JJTROOT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      program();
      jj_consume_token(0);
    jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
    {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

/* -------------------------------------------------------------------------------------- * <pro>   ::= <s_exp> <prog> * -- > the prog() function represents below the above grammar in javacc, since the above grammar *      is calling <prog> which is basically a recursive call so instead of representing *      it as same as above, I represented it by using the + operation which means to *		allow for 1 or more occurrences of the <s_exp>. ----------------------------------------------------------------------------------------*/
  static final public void program() throws ParseException {
    label_1:
    while (true) {
      symbolic_expression();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VARIABLE:
      case LEFT_PARENTHESIS:
      case CONSTANT:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
    }
  }

/* -------------------------------------------------------------------------------------- * <s_exp> ::= <def>  *			| <exp> * -- > the symbolic_expression() function below represents the above grammar in javacc. ----------------------------------------------------------------------------------------*/
  static final public void symbolic_expression() throws ParseException {
    if (jj_2_1(2)) {
      define();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VARIABLE:
      case LEFT_PARENTHESIS:
      case CONSTANT:
        expression();
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

/* -------------------------------------------------------------------------------------- * <def>   ::= (<def> <def_P> * -- > the define() function below represents the above grammar in javacc. ----------------------------------------------------------------------------------------*/
  static final public void define() throws ParseException {
 /*@bgen(jjtree) define */
  SimpleNode jjtn000 = new SimpleNode(JJTDEFINE);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token d;
  Token e;
  int arguments = 0;
    try {
      if (jj_2_2(3)) {
        jj_consume_token(LEFT_PARENTHESIS);
        d = jj_consume_token(DEFINE_KEYWORD);
        jj_consume_token(LEFT_PARENTHESIS);
        e = jj_consume_token(VARIABLE);
        label_2:
        while (true) {
          variable();
                                                                                              arguments++;
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case VARIABLE:
            ;
            break;
          default:
            jj_la1[2] = jj_gen;
            break label_2;
          }
        }
                                                                                                                SimpleNode jjtn001 = new SimpleNode(JJTVAR);
                                                                                                                boolean jjtc001 = true;
                                                                                                                jjtree.openNodeScope(jjtn001);
        try {
                                                                                                                jjtree.closeNodeScope(jjtn001,  arguments);
                                                                                                                jjtc001 = false;
                                                                                                               jjtn001.value = e.image;
        } finally {
                                                                                                                if (jjtc001) {
                                                                                                                  jjtree.closeNodeScope(jjtn001,  arguments);
                                                                                                                }
        }
        jj_consume_token(RIGHT_PARENTHESIS);
        expression();
                                                                                                                                                                                            jjtn000.value = d.image;
        jj_consume_token(RIGHT_PARENTHESIS);
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LEFT_PARENTHESIS:
          jj_consume_token(LEFT_PARENTHESIS);
          d = jj_consume_token(DEFINE_KEYWORD);
                                               jjtn000.value = d.image;
          variable();
          expression();
          jj_consume_token(RIGHT_PARENTHESIS);
          break;
        default:
          jj_la1[3] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

/* -------------------------------------------------------------------------------------- * <exp>   ::= <var> *			| <con> *			| (<exp_P>) * -- > the expression() function below represents the above grammar in javacc. ----------------------------------------------------------------------------------------*/
  static final public void expression() throws ParseException {
 /*@bgen(jjtree) expression */
  SimpleNode jjtn000 = new SimpleNode(JJTEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);Token e;
  Token d;
  int arguments = 0;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case VARIABLE:
        d = jj_consume_token(VARIABLE);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                   jjtn000.value = d.image;
        break;
      case CONSTANT:
        d = jj_consume_token(CONSTANT);
                     jjtree.closeNodeScope(jjtn000, true);
                     jjtc000 = false;
                    jjtn000.value = d.image;
        break;
      default:
        jj_la1[7] = jj_gen;
        if (jj_2_4(3)) {
          jj_consume_token(LEFT_PARENTHESIS);
          d = jj_consume_token(PRIMITIVE_FUNCTIONS);
          label_3:
          while (true) {
            expression();
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case VARIABLE:
            case LEFT_PARENTHESIS:
            case CONSTANT:
              ;
              break;
            default:
              jj_la1[4] = jj_gen;
              break label_3;
            }
          }
                                                                               jjtn000.value = d.image;
          jj_consume_token(RIGHT_PARENTHESIS);
        } else if (jj_2_5(3)) {
          jj_consume_token(LEFT_PARENTHESIS);
          variable();
          label_4:
          while (true) {
            expression();
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case VARIABLE:
            case LEFT_PARENTHESIS:
            case CONSTANT:
              ;
              break;
            default:
              jj_la1[5] = jj_gen;
              break label_4;
            }
          }
          jj_consume_token(RIGHT_PARENTHESIS);
        } else {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case LEFT_PARENTHESIS:
            jj_consume_token(LEFT_PARENTHESIS);
            d = jj_consume_token(CONDITION_KEYWORD);
            label_5:
            while (true) {
              jj_consume_token(LEFT_PARENTHESIS);
              expression();
              expression();
              jj_consume_token(RIGHT_PARENTHESIS);
 arguments += 2;
              if (jj_2_3(3)) {
                ;
              } else {
                break label_5;
              }
            }
                      SimpleNode jjtn001 = new SimpleNode(JJTCOND);
                      boolean jjtc001 = true;
                      jjtree.openNodeScope(jjtn001);
            try {
                      jjtree.closeNodeScope(jjtn001,  arguments);
                      jjtc001 = false;
                     jjtn001.value = d.image;
            } finally {
                      if (jjtc001) {
                        jjtree.closeNodeScope(jjtn001,  arguments);
                      }
            }
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case LEFT_PARENTHESIS:
              jj_consume_token(LEFT_PARENTHESIS);
              e = jj_consume_token(ELSE_KEYWORD);
              expression();
                                                                                                                      SimpleNode jjtn002 = new SimpleNode(JJTELS);
                                                                                                                      boolean jjtc002 = true;
                                                                                                                      jjtree.openNodeScope(jjtn002);
              try {
                                                                                                                      jjtree.closeNodeScope(jjtn002,  1);
                                                                                                                      jjtc002 = false;
                                                                                                                     jjtn002.value = e.image;
              } finally {
                                                                                                                      if (jjtc002) {
                                                                                                                        jjtree.closeNodeScope(jjtn002,  1);
                                                                                                                      }
              }
              jj_consume_token(RIGHT_PARENTHESIS);
              break;
            default:
              jj_la1[6] = jj_gen;
              ;
            }
            jj_consume_token(RIGHT_PARENTHESIS);
            break;
          default:
            jj_la1[8] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

/* ---------------------------------------------------------------------------------------- * Represents the grammar for the variables ------------------------------------------------------------------------------------------*/
  static final public void variable() throws ParseException {
 /*@bgen(jjtree) variable */
 SimpleNode jjtn000 = new SimpleNode(JJTVARIABLE);
 boolean jjtc000 = true;
 jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(VARIABLE);
                     jjtree.closeNodeScope(jjtn000, true);
                     jjtc000 = false;
                    jjtn000.value = t.image;
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  static private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  static private boolean jj_3_2() {
    if (jj_scan_token(LEFT_PARENTHESIS)) return true;
    if (jj_scan_token(DEFINE_KEYWORD)) return true;
    if (jj_scan_token(LEFT_PARENTHESIS)) return true;
    return false;
  }

  static private boolean jj_3R_6() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_2()) {
    jj_scanpos = xsp;
    if (jj_3R_11()) return true;
    }
    return false;
  }

  static private boolean jj_3R_14() {
    if (jj_scan_token(LEFT_PARENTHESIS)) return true;
    if (jj_scan_token(CONDITION_KEYWORD)) return true;
    return false;
  }

  static private boolean jj_3_3() {
    if (jj_scan_token(LEFT_PARENTHESIS)) return true;
    if (jj_3R_7()) return true;
    if (jj_3R_7()) return true;
    return false;
  }

  static private boolean jj_3_5() {
    if (jj_scan_token(LEFT_PARENTHESIS)) return true;
    if (jj_3R_9()) return true;
    Token xsp;
    if (jj_3R_10()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_10()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_10() {
    if (jj_3R_7()) return true;
    return false;
  }

  static private boolean jj_3_4() {
    if (jj_scan_token(LEFT_PARENTHESIS)) return true;
    if (jj_scan_token(PRIMITIVE_FUNCTIONS)) return true;
    Token xsp;
    if (jj_3R_8()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_8()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  static private boolean jj_3R_13() {
    if (jj_scan_token(CONSTANT)) return true;
    return false;
  }

  static private boolean jj_3R_12() {
    if (jj_scan_token(VARIABLE)) return true;
    return false;
  }

  static private boolean jj_3R_7() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_12()) {
    jj_scanpos = xsp;
    if (jj_3R_13()) {
    jj_scanpos = xsp;
    if (jj_3_4()) {
    jj_scanpos = xsp;
    if (jj_3_5()) {
    jj_scanpos = xsp;
    if (jj_3R_14()) return true;
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_8() {
    if (jj_3R_7()) return true;
    return false;
  }

  static private boolean jj_3R_9() {
    if (jj_scan_token(VARIABLE)) return true;
    return false;
  }

  static private boolean jj_3_1() {
    if (jj_3R_6()) return true;
    return false;
  }

  static private boolean jj_3R_11() {
    if (jj_scan_token(LEFT_PARENTHESIS)) return true;
    if (jj_scan_token(DEFINE_KEYWORD)) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[9];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x2c00,0x2c00,0x400,0x800,0x2c00,0x2c00,0x800,0x2400,0x800,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[5];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 9; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[14];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 9; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 14; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 5; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
