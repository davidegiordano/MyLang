package language.operators;

import language.visitor.ExpVisitor;

public class AndExp extends OpExp {
	public AndExp(Exp l, Exp r) { super(l, r); }
	
	@Override
	String myOp() { return "&&"; }
	
	public void accept(ExpVisitor v) { v.visit(this); }
}
