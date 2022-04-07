package main;

import language.visitor.EvalExpVisitor;

public class Main {

	public static void main(String[] args) {
		
		/*String txt = "if ( 1 == 1 ) 20 % 3 ; "
				+ "int a = 20 ; "
				+ "a = $ a - 1 ; "
				+ "if ( a > 30 ) 10 * 0 ; else 1 ; "
				+ "while ( a > 10 ) { a ; a = $ a - 1 ; } "
				+ "EOP";*/

		String txt = "int a = 20 ; "
				+ "while ( a > 10 ) a = $ a - 1 ; "
				+ "EOP";
		
		Scanner scanner = new Scanner(txt);
		Parser parser = new Parser(scanner);
		EvalExpVisitor v = new EvalExpVisitor();
		
		SolveInfo info = new SolveInfo(parser, v);
		info.solve();
	}

}
