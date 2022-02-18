package language.operators;

import language.visitor.ExpVisitor;

public class PowExp extends OpExp {

	public PowExp(Exp l, Exp r) { super(l, r); }
	
	@Override
	String myOp() { return "^"; }
	
	public void accept(ExpVisitor v) { v.visit(this); }
}
