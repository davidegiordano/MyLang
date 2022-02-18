package main;

import language.visitor.EvalExpVisitor;

public class Main {

	public static void main(String[] args) {
		
		String txt = "if ( 1 == 1 ) 20 % 3 ; "
				+ "int a = 20 ; EOP";
		
		Scanner scanner = new Scanner(txt);
		Parser parser = new Parser(scanner);
		EvalExpVisitor v = new EvalExpVisitor();
		
		SolveInfo info = new SolveInfo(parser, v);
		info.solve();
	}

}
