package language.operators;

import language.visitor.ExpVisitor;

public class LastExp extends Exp {
	
	public String toString() { return "LAST_EXP"; }
	
	@Override
	public void accept(ExpVisitor v) {}

}
