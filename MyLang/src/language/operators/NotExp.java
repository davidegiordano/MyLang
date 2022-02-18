package language.operators;

import language.visitor.ExpVisitor;

public class NotExp extends UnaryExp {
	public NotExp(Exp o) { super(o); }
	
	String myOp() { return "!"; }

	public void accept(ExpVisitor v) { v.visit(this); }
}
