package language.operators;

import language.visitor.ExpVisitor;

public class ModExp extends OpExp {
	
	public ModExp(Exp l, Exp r) { super(l, r); }
	
	@Override
	String myOp() { return "%"; }
	
	public void accept(ExpVisitor v) { v.visit(this); }

}
