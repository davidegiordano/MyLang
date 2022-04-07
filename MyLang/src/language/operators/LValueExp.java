package language.operators;

import language.visitor.ExpVisitor;

public class LValueExp extends Exp {
	private String name;
	
	public LValueExp(String name) {
		this.name = name;
	}
	
	public String toString() { return name; }
	public String getName() { return name; }

	@Override
	public void accept(ExpVisitor v) { v.visit(this); }

}
