package language.operators;

import language.visitor.ExpVisitor;

public class DivExp extends OpExp {

	public DivExp(Exp l, Exp r) { super(l, r); }
	
	@Override
	String myOp() { return "/"; }
	
	public void accept(ExpVisitor v) { v.visit(this); }
}
