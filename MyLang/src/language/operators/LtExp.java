package language.operators;

import language.visitor.ExpVisitor;

public class LtExp extends OpExp {
	
	public LtExp(Exp l, Exp r) { super(l, r); }
	
	@Override
	String myOp() { return "<"; }
	
	public void accept(ExpVisitor v) { v.visit(this); }
}
