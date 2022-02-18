package language.operators;

import language.visitor.ExpVisitor;

public class EmptyExp extends Exp {

	public String toString() { return "EMPTY_EXP"; }
	
	@Override
	public void accept(ExpVisitor v) {}

}
