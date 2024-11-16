package language.operators;

import language.visitor.ExpVisitor;

public class GteExp extends OpExp {
	
	public GteExp(Exp l, Exp r) { super(l, r); }
	
	@Override
	String myOp() { return ">="; }
	
	public void accept(ExpVisitor v) { v.visit(this); }
}
