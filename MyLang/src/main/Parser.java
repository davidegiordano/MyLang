package main;

import java.util.Vector;
import language.operators.*;

public class Parser extends Thread {
	private Vector<Exp> bufferExp;
	private Scanner scanner;
	private Token currentToken;
	private boolean endDeclarations = false;
	
	public Parser(Scanner scanner) {
		this.scanner = scanner;
		this.currentToken = scanner.getNextToken();
		this.bufferExp = new Vector<Exp>();
	}

	public Vector<Exp> getBufferExp() {
		return bufferExp;
	}
	
	public void run() {
		Exp exp = null;
		//boolean end = false;
		
		//currentToken = scanner.getNextToken();
		
		while(!(currentToken.equals("EOP"))) {
			exp = parseCompilationUnit();
			if(exp == null) {
				System.out.println("Parsing impossibile");
				continue;
			}
			if(exp instanceof EmptyExp) continue;
			bufferExp.add(exp);
		}
		//end = true;
	}
	
	public Exp parseCompilationUnit() {
		Exp exp = null;
		exp = parseDeclaration();
		if(exp != null) return exp;
		exp = parseStatement();
		if(exp != null) return exp;
		return exp;
	}
	
	/*
	 * Grammatica:
	 * 		
	 * 		DECL ::= int ID = EXP;
	 * 
	 * 		STAT ::= ; | EXP; | BLOCK | IFSTAT | WHILESTAT
	 * 		BLOCKSTAT ::= { {DECL}{STAT} }	
	 * 		IFSTAT ::= if( EXP ) STAT [else STAT]
	 * 		WHILESTAT ::= while( EXP ) STAT
	 * 
	 * 		EXP ::= TERM
	 * 		EXP ::= EXP [+|-|&&||||<|<=|>|<=|==|!=] TERM
	 * 
	 * 		TERM ::= POT
	 * 		TERM ::= FACTOR * POT
	 * 		TERM ::= FACTOR / POT
	 * 		TERM ::= FACTOR % POT
	 * 		POT ::= FACTOR
	 * 		POT ::= FACTOR ^ POT
	 * 		FACTOR ::= id
	 * 		FACTOR ::= num
	 * 		FACTOR ::= ! EXP
	 * 		FACTOR ::= ( EXP )
	 * }
	 */
	
	private Exp parseStatement() {
		Exp st = null;
		
		if(currentToken.equals(";")) {
			st = new EmptyExp();
			currentToken = scanner.getNextToken();
		}
		else if(currentToken.equals("{")) {
			currentToken = scanner.getNextToken();
			st = parseBlock();
		}
		else if(currentToken.equals("if")) {
			currentToken = scanner.getNextToken();
			st = parseIf();
		}
		else if(currentToken.equals("while")) {
			currentToken = scanner.getNextToken();
			st = parseWhile();
		}
		else { //espressioni
			st = parseExp();
			if(!currentToken.equals(";")) {
				System.out.println("Manca ;");
			}
			currentToken = scanner.getNextToken();
		}
		endDeclarations  = true;
		return st;
	}
	
	private Exp parseBlock() {
		BlockExp blockExp = new BlockExp();
		Vector <DeclExp> declarations = new Vector<>();
		
		if(currentToken.equals("int")) {
			while(!endDeclarations)
				declarations.add((DeclExp) parseDeclaration());
		}
		currentToken = scanner.getNextToken();
		blockExp.add(parseStatement());
		
		return blockExp;
	}
	
	private Exp parseDeclaration() {
		DeclExp declExp = null;
		
		if(currentToken.equals("int")) {
			currentToken = scanner.getNextToken();
			Exp left = parseExp();
			
			if(!currentToken.equals("=")) {
				System.out.println("Manca =");
			}

			currentToken = scanner.getNextToken();
			Exp right = parseExp();
			
			declExp = new DeclExp("int", left, right);
		}
		
		
		
		endDeclarations = true;
		
		return declExp;
	}

	private Exp parseIf() {
		IfExp ifExp;
		
		if(!currentToken.equals("(")) {
			System.out.println("Manca (");
		}
		currentToken = scanner.getNextToken();
		Exp cond = parseExp();
		if(cond == null) return null;
		if(!currentToken.equals(")")) {
			System.out.println("Manca )");
		}
		currentToken = scanner.getNextToken();
		
		ifExp = new IfExp(cond);
		Exp action = parseStatement();
		if(action == null) {
			System.out.println("Manca azione if");
		}
		ifExp.setAction(action);
		
		if(currentToken.equals("else")) {
			currentToken = scanner.getNextToken();
			Exp elseStat = parseElse();
			if(elseStat == null) {
				System.out.println("Manca else");
			}
			ifExp.setElseStatement(elseStat);
		}
		return ifExp;
	}
	
	private Exp parseElse() {
		Exp elseExp = null;
		
		if(currentToken.equals("if")) {
			currentToken = scanner.getNextToken();
			elseExp = parseIf();
		}
		else
		elseExp = parseStatement();
		return elseExp;
	}
	
	private Exp parseWhile() {
		WhileExp whileExp;
		
		if(!currentToken.equals("(")) {
			System.out.println("Manca (");
		}
		currentToken = scanner.getNextToken();
		Exp cond = parseExp();
		if(cond == null) return null;
		if(!currentToken.equals(")")) {
			System.out.println("Manca )");
		}
		currentToken = scanner.getNextToken();
		
		whileExp = new WhileExp(cond);
		Exp action = parseStatement();
		if(action == null) {
			System.out.println("Manca azione if");
		}
		whileExp.setAction(action);
		
		return whileExp;
	}

	private Exp parseExp() {
		Exp t1 = parseTerm();
		
		while (currentToken != null && !(currentToken.equals("EOP"))) {
			if (currentToken.equals("&&")) {
				currentToken = scanner.getNextToken();
				Exp t2 = parseTerm(); 
				t1 = new AndExp(t1, t2);
			}
			else if (currentToken.equals("||")) {
				currentToken = scanner.getNextToken();
				Exp t2 = parseTerm(); 
				t1 = new OrExp(t1, t2);
			}
			else if (currentToken.equals("<")) {
				currentToken = scanner.getNextToken();
				Exp t2 = parseTerm(); 
				t1 = new LtExp(t1, t2);
			}
			else if (currentToken.equals("<=")) {
				currentToken = scanner.getNextToken();
				Exp t2 = parseTerm(); 
				t1 = new LteExp(t1, t2);
			}
			else if (currentToken.equals(">")) {
				currentToken = scanner.getNextToken();
				Exp t2 = parseTerm(); 
				t1 = new GtExp(t1, t2);
			}
			else if (currentToken.equals(">=")) {
				currentToken = scanner.getNextToken();
				Exp t2 = parseTerm(); 
				t1 = new GteExp(t1, t2);
			}
			else if (currentToken.equals("==")) {
				currentToken = scanner.getNextToken();
				Exp t2 = parseTerm(); 
				t1 = new EqExp(t1, t2);
			}
			else if (currentToken.equals("!=")) {
				currentToken = scanner.getNextToken();
				Exp t2 = parseTerm(); 
				t1 = new NeqExp(t1, t2);
			}
			else if (currentToken.equals("+")) {
				currentToken = scanner.getNextToken();
				Exp t2 = parseTerm();
				t1 = new PlusExp(t1, t2);
			}
			else if (currentToken.equals("-")) {
				currentToken = scanner.getNextToken();
				Exp t2 = parseTerm(); 
				t1 = new MinusExp(t1, t2);
			}
			else return t1;
		}
		return t1;
	}
	
	private Exp parseTerm() {
		Exp f1 = parsePot();
		
		while (currentToken != null && !(currentToken.equals("EOP"))) {
			if (currentToken.equals("*")) {
				currentToken = scanner.getNextToken();
				Exp f2 = parsePot();
				f1 = new MulExp(f1, f2);
			}
			else if (currentToken.equals("/")) {
				currentToken = scanner.getNextToken();
				Exp f2 = parsePot(); 
				f1 = new DivExp(f1, f2);
			}
			else if (currentToken.equals("%")) {
				currentToken = scanner.getNextToken();
				Exp f2 = parsePot(); 
				f1 = new ModExp(f1, f2);
			}
			else return f1;
		}
		return f1;
	}
	
	private Exp parsePot() {
		Exp f1 = parseFactor();
		
		if (currentToken != null && !currentToken.equals("")) {
			if (currentToken.equals("^")) {
				currentToken = scanner.getNextToken();
				Exp p2 = parsePot();
				f1 = new PowExp(f1, p2);
			}
			else return f1;
		}
		return f1;
	}
	
	private Exp parseFactor() {
		if (currentToken.equals("(")) {
			currentToken = scanner.getNextToken();
			Exp innerExp = parseExp(); //self-embedding
			if (currentToken.equals(")")) {
				currentToken = scanner.getNextToken();
				return innerExp;
			}
			else return null; //manca parentesi chiusa
		}
		else if (currentToken.equals("!")) { //Not
			currentToken = scanner.getNextToken();
			Exp innerExp = parseExp(); //self-embedding
			return innerExp;
		}
		else if (currentToken.isIdentifier()) {
			String id = this.currentToken.toString();
			
			this.currentToken = this.scanner.getNextToken();
			return new GenExp(id);
		}
		else if (currentToken.isNumber()) {
			int val = this.currentToken.getAsInt();
			currentToken = scanner.getNextToken();
			return new NumExp(val);
		}
		
		else return null; //non è un fattore, è qualcosa di sconosciuto
	}
}
